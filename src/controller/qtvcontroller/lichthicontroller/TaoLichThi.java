package controller.lichthicontroller;
import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TaoLichThi {
    private DBController dbController = new DBController();
    private DataControler dataControler = new DataControler();
    Connection conn = DBConnection.getInstance().getConnection();

    public void TaoLich(ComboBox<String> hocKyLT, AnchorPane testScheduleAnchorPane) throws SQLException, IOException {
        final int MAX_ROW = 1000000;
        final int MIN_COLUMN = 12;
        final int START_ROW = 2;
        if(hocKyLT.getValue().length() != 0 && !dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                //choose a file excel
                Stage stage=(Stage) testScheduleAnchorPane.getScene().getWindow();
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("Import file Excel");
                File file = fileChooser.showOpenDialog(stage);
                //settings
                String sourceFile=null;
                if(file != null){
                    sourceFile = file.getPath();
                }
                String sheetName = "LichThi" + hocKyLT.getValue();
                //read file excel
                FileInputStream fileInputStream=new FileInputStream(sourceFile);
                XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet=workbook.getSheet(sheetName);

                if(sheet != null) {
                    //create tableName
                    String sql="CREATE TABLE " + tableName + "(maLop int ,maHP nvarchar(500),tenHP nvarchar(500),ghiChu nvarchar(500)" +
                            ",nhom nvarchar(500),dotMo nvarchar(500),tuan nvarchar(500),thu nvarchar(500),ngayThi date," +
                            "kip nvarchar(500),SLDK int,phong nvarchar(500));";
                    var prepare= conn.prepareStatement(sql);
                    prepare.executeUpdate();
                    //read file to database
                    String sqlAdd = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                    var prepareAdd = conn.prepareStatement(sqlAdd);
                    int startRow = Math.max(START_ROW - 1, sheet.getFirstRowNum());
                    int lastRow = Math.max(MAX_ROW, sheet.getLastRowNum());
                    for (int i = startRow; i < lastRow; i++) {
                        Row row = sheet.getRow(i);
                        if (row == null) {
                            continue;
                        }
                        int lastColumn = Math.min(MIN_COLUMN, row.getLastCellNum());
                        for (int cn = 0; cn < lastColumn; cn++) {
                            Cell c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                            if (c == null) {
                                //nothing do
                            } else {
                                switch (c.getCellType()) {
                                    case STRING:
                                        prepareAdd.setObject(cn + 1, c.getRichStringCellValue().toString());
                                        break;
                                    case NUMERIC:
                                        if (DateUtil.isCellDateFormatted(c)) {
                                            prepareAdd.setObject(cn + 1, c.getDateCellValue());
                                        } else {
                                            prepareAdd.setObject(cn + 1, c.getNumericCellValue());
                                        }
                                        break;
                                    case BOOLEAN:
                                        prepareAdd.setObject(cn + 1, c.getBooleanCellValue());
                                        break;
                                }
                            }
                        }
                        prepareAdd.addBatch();
                    }
                    prepareAdd.executeBatch();
                    conn.commit();
                    Information.ThongBaoThongTin("Bạn đã nhập thành công");
                } else {
                    Error.ThongBaoLoi("Lỗi định dạnh trong file excel ");
                }
            }else{
                Warning.CanhBao("Lịch thi đã bị khóa ");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập vào học kỳ hoặc lịch thi đã tồn tại ");
        }
    }
}
