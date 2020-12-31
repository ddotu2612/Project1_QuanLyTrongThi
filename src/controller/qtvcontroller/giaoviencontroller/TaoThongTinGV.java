package controller.giaoviencontroller;

import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
import javafx.scene.control.Alert;
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

public class TaoThongTinGV {
    //connect to database
    Connection conn = DBConnection.getInstance().getConnection();
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    public void TaoTT(ComboBox<String> hocKyGV, AnchorPane lecturerAnchorPane) throws IOException, SQLException {
        final int MAX_ROW=1000000;
        if(hocKyGV.getValue().length() !=0  && !dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                //choose a file excel
                Stage stage=(Stage) lecturerAnchorPane.getScene().getWindow();
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("Import file Excel");
                File file=fileChooser.showOpenDialog(stage);

                //settings
                String sourceFile=null;
                if(file != null){
                    sourceFile=file.getPath();
                }
                String sheetName = "GiangVien" + hocKyGV.getValue();
                //read file excel
                FileInputStream fileInputStream=new FileInputStream(sourceFile);
                XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet=workbook.getSheet(sheetName);
                if(sheet != null) {
                    //create tableName
                    String sql="CREATE TABLE "+tableName +"(hoTen nvarchar(50) ,boMon nvarchar(50),phone bigint,email nvarchar(50),phong nvarchar(50),maLop int);";
                    var prepare=conn.prepareStatement(sql);
                    prepare.executeUpdate();
                    //read file to database
                    String sqlAdd = "INSERT INTO "+tableName+" VALUES (?, ?, ?, ?, ?, ?);";
                    var prepareAdd=conn.prepareStatement(sqlAdd);
                    System.out.println(sheet.getLastRowNum());
                    int lastRow = Math.max(MAX_ROW, sheet.getLastRowNum());
                    for(int i=1 ; i < lastRow ; i++){
                        Row row = sheet.getRow(i);
                        if(row == null){
                            continue;
                        }
                        if(row.equals(sheet.getRow(i - 1))) break;
                        //int lastColumn=Math.max(MIN_COLUMN,row.getLastCellNum());
                        for(int cn=0 ;cn < 6; cn++){
                            Cell c=row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) ;
                            if (c == null) {
                                //nothing do
                            }else {
                                switch (c.getCellType()){
                                    case STRING:
                                        prepareAdd.setObject(cn+1,c.getRichStringCellValue().toString());
                                        break;
                                    case NUMERIC:
                                        if(DateUtil.isCellDateFormatted(c)){
                                            prepareAdd.setObject(cn+1,c.getDateCellValue());
                                        }else{
                                            prepareAdd.setObject(cn+1,c.getNumericCellValue());
                                        }
                                        break;
                                    case BOOLEAN:
                                        prepareAdd.setObject(cn+1,c.getBooleanCellValue());
                                        break;
                                }
                            }
                        }
                        prepareAdd.addBatch();
                    }
                    prepareAdd.executeBatch();
                    conn.commit();
                    Information.ThongBaoThongTin("Bạn đã import dữ liệu thành công");
                } else {
                    Error.ThongBaoLoi("Lỗi định dạng file excel (sheetName)");
                }
            }else{
                Warning.CanhBao("Thông tin giảng viên đã bị khóa ");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc bảng dữ liệu học kỳ này đã tồn tại");
        }
    }
}
