package view;

import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QTVController implements Initializable {
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    DBConnection dbConnection = new DBConnection();
    //config lịch thi
    @FXML
    TextField maLopTextField;
    @FXML
    TextField maHPTextField;
    @FXML
    TextField tenHPTextField;
    @FXML
    TextField ghiChuTextField;
    @FXML
    TextField nhomTextField;
    @FXML
    TextField dotMoTextField;
    @FXML
    TextField tuanTextField;
    @FXML
    TextField thuTextField;
    @FXML
    DatePicker ngayThiLT;
    @FXML
    TextField kipTextField;
    @FXML
    TextField sldkTextField;
    @FXML
    TextField phongTextField;
    @FXML
    ComboBox<String> hocKyLT;
    ObservableList<String> listHKLT = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML
    TableView<TestSchedule> testScheduleTableView;
    @FXML
    TableColumn<TestSchedule,Integer> maLopColumn;
    @FXML
    TableColumn<TestSchedule,String> maHPColumn;
    @FXML
    TableColumn<TestSchedule,String> tenHPColumn;
    @FXML
    TableColumn<TestSchedule,String> ghiChuColumn;
    @FXML
    TableColumn<TestSchedule,String> nhomColumn;
    @FXML
    TableColumn<TestSchedule,String> dotMoColumn;
    @FXML
    TableColumn<TestSchedule,String> tuanColumn;
    @FXML
    TableColumn<TestSchedule,String> thuColumn;
    @FXML
    TableColumn<TestSchedule, Date> ngayThiColumn;
    @FXML
    TableColumn<TestSchedule,String> kipColumn;
    @FXML
    TableColumn<TestSchedule,Integer> sldkColumn;
    @FXML
    TableColumn<TestSchedule,String> phongColumn;
    @FXML
    AnchorPane testScheduleAnchorPane;
    ObservableList<TestSchedule> testScheduleList;

    //config giảng viên
    @FXML
    TextField hoVaTenGVTextField;
    @FXML
    TextField boMonGVTextField;
    @FXML
    TextField phoneNumberGVTextField;
    @FXML
    TextField emailGVTextField;
    @FXML
    TextField phongGVTextField;
    @FXML
    TextField maLopGVTextField;
    @FXML
    ComboBox<String> hocKyGV;
    ObservableList<String> listHKGV = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML
    TableView<Lecturers> lecturerTableView;
    @FXML
    TableColumn<Lecturers,String> hoVaTenGVColumn;
    @FXML
    TableColumn<Lecturers,String> boMonGVColumn;
    @FXML
    TableColumn<Lecturers,String> phoneNumberGVColumn;
    @FXML
    TableColumn<Lecturers,String> emailGVColumn;
    @FXML
    TableColumn<Lecturers,String> phongGVColumn;
    @FXML
    TableColumn<Lecturers,Integer> maLopGVColumn;
    @FXML
    AnchorPane lecturerAnchorPane;
    ObservableList<Lecturers> lecturersList;

    //config giám thị
    @FXML
    TextField hoVaTenGTTextField;
    @FXML
    TextField boMonGTTextField;
    @FXML
    TextField phoneNumberGTTextField;
    @FXML
    TextField emailGTTextField;
    @FXML
    TextField phongGTTextField;
    @FXML
    ComboBox<String> hocKyGT;
    ObservableList<String> listHKGT = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML
    TableView<Supervisor> supervisorTableView;
    @FXML
    TableColumn<Supervisor,String> hoVaTenGTColumn;
    @FXML
    TableColumn<Supervisor,String> boMonGTColumn;
    @FXML
    TableColumn<Supervisor,String> phoneNumberGTColumn;
    @FXML
    TableColumn<Supervisor,String> emailGTColumn;
    @FXML
    TableColumn<Supervisor,String> phongGTColumn;
    @FXML
    AnchorPane supervisorAnchorPane;
    ObservableList<Supervisor> supervisorsList;

    //phan công trông thi
    @FXML TextArea detailPC;
    @FXML
    TextField maLopTT;
    @FXML
    ComboBox<String> hocKyPC;
    ObservableList<String> listHKPC = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML
    TableView<InfoTrongThi> tableViewTrongThi;
    @FXML
    TableColumn<InfoTrongThi,Integer> malopTTColumn;
    @FXML
    TableColumn<InfoTrongThi,String> giamThi1TTColumn;
    @FXML
    TableColumn<InfoTrongThi,String> giamThi2TTColumn;
    @FXML
    TableColumn<InfoTrongThi,Date> ngayThiTTColumn;
    @FXML
    TableColumn<InfoTrongThi,String> kipThiTTColumn;
    ObservableList<InfoTrongThi> trongThiList;

    //quản lý kinh phí
    @FXML TextField maLopKP;
    @FXML TextArea detailKP;
    @FXML ComboBox<String> hocKyKP;
    ObservableList<String> listHKKP = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML TextField giaKP;
    @FXML ComboBox<String> tenDonGiaKP;
    ObservableList<String> listKP=FXCollections.observableArrayList("in ấn","Phô tô đề thi","Kinh phí tổ chức","Tiền giám thị");
    @FXML TableView<DonGia> donGiaTableView;
    @FXML TableColumn<DonGia,String> tenDonGiaTableColumn;
    @FXML TableColumn<DonGia,Long> giaTableColumn;
    ObservableList<DonGia> donGiaList;

    @FXML TableView<KinhPhi> kinhPhiTableView;
    @FXML TableColumn<KinhPhi,Integer> maLopKPTableColumn;
    @FXML TableColumn<KinhPhi,String> tenGVTableColumn;
    @FXML TableColumn<KinhPhi,Long> inAnTableColumn;
    @FXML TableColumn<KinhPhi,Long> phoToTableColumn;
    @FXML TableColumn<KinhPhi,Long> toChucThiTableColumn;
    @FXML TableColumn<KinhPhi,Long> kinhPhiGTTableColumn;
    @FXML TableColumn<KinhPhi,Integer> checkThanhToanTableColumn;
    @FXML TableColumn<KinhPhi,String> ghiChuKPTableColumn;
    ObservableList<KinhPhi> kinhPhiList;

    //Báo cáo thống kê
    @FXML CheckBox gvCheckBox;
    @FXML CheckBox gtCheckBox;
    @FXML TextField hotenGV_GT;
    @FXML ComboBox<String> hocKyBCTK;
    ObservableList<String> listHKBCTK = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML TableView<BaoCao> baoCaoTableView;
    @FXML TableColumn<BaoCao, Integer> maLopTableColumn;
    @FXML TableColumn<BaoCao, String> cot2;
    @FXML TableColumn<BaoCao, String> cot3;
    ObservableList<BaoCao> baoCaoList;
    @FXML ComboBox<String> hocKyTK;
    ObservableList<String> listHKTK = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hocKyLT.setItems(listHKLT);
        hocKyLT.setPromptText("20201");
        hocKyGV.setItems(listHKGV);
        hocKyGT.setItems(listHKGT);
        hocKyPC.setItems(listHKPC);
        hocKyKP.setItems(listHKKP);
        hocKyBCTK.setItems(listHKBCTK);
        hocKyTK.setItems(listHKTK);
        ArrayList<TestSchedule> testSchedules=null;
        try {
            testSchedules=dbController.testScheduleList("LichThi20192");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(testSchedules==null){
            //
        }else{
            testScheduleList=FXCollections.observableArrayList(testSchedules);
            maLopColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("maLop"));
            maHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("maHP"));
            tenHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tenHP"));
            ghiChuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("ghiChu"));
            nhomColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("nhom"));
            dotMoColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("dotMo"));
            tuanColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tuan"));
            thuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("thu"));
            ngayThiColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Date>("ngayThi"));
            kipColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("kip"));
            sldkColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("SLDK"));
            phongColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("phong"));
            testScheduleTableView.setItems(testScheduleList);
        }
        //config Giao Vien
        ArrayList<Lecturers> Lecturer=null;
        try {
            Lecturer=dbController.lecturerList("GiangVien20192");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(Lecturer==null){
            //
        }else{
            lecturersList=FXCollections.observableArrayList(Lecturer);
            hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("nameLecturer"));
            boMonGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("faculty"));
            phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("phoneNumber"));
            emailGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("email"));
            phongGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("workPlace"));
            maLopGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,Integer>("maLop"));
            lecturerTableView.setItems(lecturersList);
        }
        //config Giám thị
        ArrayList<Supervisor> supervisor=null;
        try {
            supervisor=dbController.supervisorList("GiamThi20192");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(supervisor==null){
            //
        }else{
            supervisorsList=FXCollections.observableArrayList(supervisor);
            hoVaTenGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("nameLecturer"));
            boMonGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("faculty"));
            phoneNumberGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("phoneNumber"));
            emailGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("email"));
            phongGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("workPlace"));
            supervisorTableView.setItems(supervisorsList);
        }
        //config phân công trông thi
        ArrayList<InfoTrongThi> trongThi=null;
        try {
            trongThi=dbController.trongThiList("PhanCong20192");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(trongThi==null){
            //
        }else{
            trongThiList=FXCollections.observableArrayList(trongThi);
            malopTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Integer>("maLop"));
            giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi1"));
            giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi2"));
            ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Date>("ngayThi"));
            kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("kipThi"));
            tableViewTrongThi.setItems(trongThiList);
        }
        //config kinh phí
        tenDonGiaKP.setItems(listKP);
        //Bảng Đơn giá
        ArrayList<DonGia> donGia=null;
        try {
            donGia=dbController.donGiaList("DonGia20192");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(donGia==null){
            //
        }else{
            donGiaList=FXCollections.observableArrayList(donGia);
            tenDonGiaTableColumn.setCellValueFactory(new PropertyValueFactory<DonGia,String>("tenDonGia"));
            giaTableColumn.setCellValueFactory(new PropertyValueFactory<DonGia,Long>("gia"));
            donGiaTableView.setItems(donGiaList);
        }
        //Bảng kinh phí
        ArrayList<KinhPhi> kinhPhi=null;
        try {
            kinhPhi=dbController.kinhPhiList("KinhPhi20192");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(kinhPhi==null){
            //
        }else{
            kinhPhiList=FXCollections.observableArrayList(kinhPhi);
            maLopKPTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Integer>("maLop"));
            tenGVTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,String>("tenGV"));
            inAnTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("inAn"));
            phoToTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("phoTo"));
            toChucThiTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("toChucThi"));
            kinhPhiGTTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("kinhPhiGT"));
            checkThanhToanTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Integer>("checkThanhToan"));
            ghiChuKPTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,String>("ghiChu"));
            kinhPhiTableView.setItems(kinhPhiList);
        }
    }
    public void Logout(){
        try{
            new Logout().Excute_Logout();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void importFileExcel(ActionEvent e) throws IOException, SQLException {
        final int MAX_ROW=1000000;
        final int MIN_COLUMN=12;
        final int START_ROW=2;
        //connect to database
        Connection conn=dbConnection.getConnection();
        if(hocKyLT.getValue().length() !=0 && !dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                //Delete table
                String sql1="IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES Where Table_Schema = 'dbo'  AND Table_Name = '"+tableName+"') "+
                        "BEGIN drop table "+tableName+" END;";
                try {
                    var prepareExists=conn.prepareStatement(sql1);
                    prepareExists.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                //create tableName
                String sql="CREATE TABLE "+tableName +"(maLop int ,maHP nvarchar(50),tenHP nvarchar(50),ghiChu nvarchar(50),nhom nvarchar(50),dotMo nvarchar(50),tuan nvarchar(50),thu nvarchar(50),ngayThi date,kip nvarchar(50),SLDK int,phong nvarchar(50));";
                try {
                    var prepare=conn.prepareStatement(sql);
                    prepare.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //choose a file excel
                Stage stage=(Stage) testScheduleAnchorPane.getScene().getWindow();
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("Import file Excel");
                File file=fileChooser.showOpenDialog(stage);

                //settings
                String sourceFile=null;
                if(file != null){
                    sourceFile=file.getPath();
                }
                String sheetName="LichThi20192";
                try {
                    //read file excel
                    FileInputStream fileInputStream=new FileInputStream(sourceFile);
                    XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
                    XSSFSheet sheet=workbook.getSheet(sheetName);

                    //read file to database
                    String sqlAdd = "INSERT INTO "+tableName+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                    var prepareAdd=conn.prepareStatement(sqlAdd);
                    int startRow=Math.max(START_ROW-1,sheet.getFirstRowNum());
                    int lastRow=Math.max(MAX_ROW,sheet.getLastRowNum());
                    for(int i=startRow;i<lastRow;i++){
                        Row row=sheet.getRow(i);
                        if(row==null){
                            continue;
                        }
                        int lastColumn=Math.min(MIN_COLUMN,row.getLastCellNum());
                        for(int cn=0;cn<lastColumn;cn++){
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
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công ");
                    alert.setContentText("Bạn đã import dữ liệu thành công");
                    alert.showAndWait();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Lịch thi đã bị khóa ");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập vào học kỳ hoặc lịch thi đã tồn tại ");
            alert.showAndWait();
        }
    }
    public void Add(ActionEvent e) throws ParseException, SQLException {
        if(hocKyLT.getValue().length() !=0){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName) && dbController.checkExistTable(tableName)){
                int maLop=Integer.parseInt(maLopTextField.getText());
                String maHP=maHPTextField.getText();
                String tenHP=tenHPTextField.getText();
                String ghiChu=ghiChuTextField.getText();
                String nhom=nhomTextField.getText();
                String dotMo=dotMoTextField.getText();
                String tuan=tuanTextField.getText();
                String thu=thuTextField.getText();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                //Date ngayThi=simpleDateFormat.parse(ngayThiTextField.getText());
                Date ngayThi=simpleDateFormat.parse(ngayThiLT.getValue().toString());
                String kip=kipTextField.getText();
                int SLDK= Integer.parseInt(sldkTextField.getText());
                String phong=phongTextField.getText();
                TestSchedule testSchedule=new TestSchedule(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                boolean check=dataControler.isValidTestSchedule(testSchedule,tableName);//Kiểm tra xem lịch đã đc thêm vào hay chưa
                if(check){
                    if(dbController.addTestScheduleToDatabase(testSchedule,tableName)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công ");
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn đã thêm lịch thi thành công");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Lịch thi chưa được thêm thành công");
                        alert.showAndWait();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Lịch thi đã tồn tại trong database");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Lịch thi đã bị khóa hoặc bảng dữ liệu học kỳ bạn nhập không tồn tại");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc các thông tin khác");
            alert.showAndWait();
        }
    }

    public void resetTestSchedule(ActionEvent e) throws SQLException {
        if(hocKyLT.getValue().length() != 0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            //DBController dbController=new DBController();
            ArrayList<TestSchedule> testSchedules=null;
            try {
                testSchedules=dbController.testScheduleList(tableName);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //Config lichThi
            if(testSchedules==null){
                //
            }else{
                testScheduleList=FXCollections.observableArrayList(testSchedules);
                maLopColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("maLop"));
                maHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("maHP"));
                tenHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tenHP"));
                ghiChuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("ghiChu"));
                nhomColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("nhom"));
                dotMoColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("dotMo"));
                tuanColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tuan"));
                thuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("thu"));
                ngayThiColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Date>("ngayThi"));
                kipColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("kip"));
                sldkColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("SLDK"));
                phongColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("phong"));
                testScheduleTableView.setItems(testScheduleList);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ bạn muốn reset hoặc bảng dữ liệu không tồn tại");
            alert.showAndWait();
        }
        maLopTextField.setText("");
        maHPTextField.setText("");
        tenHPTextField.setText("");
        ghiChuTextField.setText("");
        nhomTextField.setText("");
        dotMoTextField.setText("");
        tuanTextField.setText("");
        thuTextField.setText("");
        //ngayThiTextField.setText("");
        kipTextField.setText("");
        sldkTextField.setText("");
        phongTextField.setText("");
    }

    public void updateTestSchedule(ActionEvent e) throws ParseException, SQLException {
        //DBController dbController=new DBController();
        if(hocKyLT.getValue().length() !=0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            TestSchedule testSchedule=testScheduleTableView.getSelectionModel().getSelectedItem();
            if(maLopTextField.getText().matches("\\d+") && sldkTextField.getText().matches("\\d+") ){
                int maLop=Integer.parseInt(maLopTextField.getText());
                String maHP=maHPTextField.getText();
                String tenHP=tenHPTextField.getText();
                String ghiChu=ghiChuTextField.getText();
                String nhom=nhomTextField.getText();
                String dotMo=dotMoTextField.getText();
                String tuan=tuanTextField.getText();
                String thu=thuTextField.getText();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//                Date ngayThi=simpleDateFormat.parse(ngayThiTextField.getText());
                Date ngayThi=simpleDateFormat.parse(ngayThiLT.getValue().toString());
                String kip=kipTextField.getText();
                int SLDK= Integer.parseInt(sldkTextField.getText());
                String phong=phongTextField.getText();
                TestSchedule testSchedule1=new TestSchedule(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                if(dbController.updateTestScheduleToDatabase(tableName,testSchedule1,testSchedule)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công ");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã cập nhật lịch thi thành công");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Lịch thi chưa được cập nhật thành công");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhập đầy đủ và đúng định dạng thông tin");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc bảng không tồn tại ");
            alert.showAndWait();
        }
    }
    public void searchTestSchedule(ActionEvent e) throws SQLException {
        if(maLopTextField.getText().length() != 0 && hocKyLT.getValue().length() != 0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            int maLop= Integer.parseInt(maLopTextField.getText());
            String tableName = "LichThi" + hocKyLT.getValue();
            ArrayList<TestSchedule> list=dbController.searchTestScheduleFromDatabase(maLop,tableName);
            if(list.size() ==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Không có mã lớp mà bạn tìm kiếm");
                alert.showAndWait();
            }else{
                testScheduleList=FXCollections.observableArrayList(list);
                maLopColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("maLop"));
                maHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("maHP"));
                tenHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tenHP"));
                ghiChuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("ghiChu"));
                nhomColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("nhom"));
                dotMoColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("dotMo"));
                tuanColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tuan"));
                thuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("thu"));
                ngayThiColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Date>("ngayThi"));
                kipColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("kip"));
                sldkColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("SLDK"));
                phongColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("phong"));
                testScheduleTableView.setItems(testScheduleList);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập mã lớp hoặc học kỳ mà bạn tìm kiếm hoặc dữ liệu học kỳ bạn nhập không tồn tại");
            alert.showAndWait();
        }
    }
    public void deleteTestSchedule(ActionEvent e) throws SQLException {
        if(hocKyLT.getValue().length() != 0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                TestSchedule testSchedule=testScheduleTableView.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Xóa lịch thi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc muốn xóa lịch thi này không?");
                ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                    if (dbController.deleteTestScheduleFromDatabase(testSchedule,tableName)) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Thành Công");
                        alert1.setContentText("Bạn đã xoá lịch thi thành công ");
                        alert1.showAndWait();
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Lỗi");
                        alert1.setContentText("Xóa không thành công");
                        alert1.showAndWait();
                    }
                } else {
                    alert.close();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Lịch thi đã bị khóa ");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ mà bạn muốn xóa hoặc dữ liệu học kỳ này không tồn tại");
            alert.showAndWait();
        }
    }
    public void dataTestScheduleLock(ActionEvent e) throws SQLException {
        if(hocKyLT.getValue().length() != 0 && dataControler.isCheckDataLock("LichThi" + hocKyLT.getValue()) && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            Connection conn =dbConnection.getConnection();
            String sql="insert into dbo.LockData values(?,?) ;";
            var prepare=conn.prepareStatement(sql) ;
            prepare.setString(1,tableName);
            prepare.setInt(2,1);
            var result=prepare.executeUpdate();
            if(result>0){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thành Công");
                alert1.setContentText("Bạn đã khóa lịch thi thành công ");
                alert1.showAndWait();
            }else{
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Lỗi");
                alert1.setContentText("Khóa không thành công");
                alert1.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ mà bạn muốn khóa hoặc dữ liệu học kỳ này đã bị khóa hoặc dữ liệu học kỳ không tồn tại");
            alert.showAndWait();
        }
        
     }

     //giảng viên: cho giang vien nayf
     public void importFileExcelGV(ActionEvent e) throws IOException, SQLException {
         final int MAX_ROW=1000000;
         //connect to database
         //DBConnection dbConnection=new DBConnection();
         Connection conn=dbConnection.getConnection();
         if(hocKyGV.getValue().length() !=0  && !dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
             String tableName="GiangVien" + hocKyGV.getValue();
             if(dataControler.isCheckDataLock(tableName)){
                 //Delete table
                 String sql1="IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES Where Table_Schema = 'dbo'  AND Table_Name = '"+tableName+"') "+
                         "BEGIN drop table "+tableName+" END;";
                 try {
                     var prepareExists=conn.prepareStatement(sql1);
                     prepareExists.executeUpdate();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
                 //create tableName
                 String sql="CREATE TABLE "+tableName +"(hoTen nvarchar(50) ,boMon nvarchar(50),phone bigint,email nvarchar(50),phong nvarchar(50),maLop int);";
                 try {
                     var prepare=conn.prepareStatement(sql);
                     prepare.executeUpdate();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }

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
                 String sheetName="GiangVien20192";
                 try {
                     //read file excel
                     FileInputStream fileInputStream=new FileInputStream(sourceFile);
                     XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
                     XSSFSheet sheet=workbook.getSheet(sheetName);

                     //read file to database
                     String sqlAdd = "INSERT INTO "+tableName+" VALUES (?, ?, ?, ?, ?, ?);";
                     var prepareAdd=conn.prepareStatement(sqlAdd);
//                     int startRow=Math.max(START_ROW-1,sheet.getFirstRowNum());
                     System.out.println(sheet.getLastRowNum());
                    int lastRow=Math.max(MAX_ROW,sheet.getLastRowNum());
                     for(int i=1;i<lastRow;i++){
                         Row row=sheet.getRow(i);
                         if(row==null){
                             continue;
                         }
                         //int lastColumn=Math.max(MIN_COLUMN,row.getLastCellNum());
                         for(int cn=0;cn<6;cn++){
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
//                         prepareAdd.setString(1, String.valueOf(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(2, String.valueOf(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(3, String.valueOf(row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(4, String.valueOf(row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(5, String.valueOf(row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setInt(6, (int) row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue());
                         prepareAdd.addBatch();
                     }
                     prepareAdd.executeBatch();
                     conn.commit();
                     Alert alert=new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Thành công ");
                     alert.setContentText("Bạn đã import dữ liệu thành công");
                     alert.showAndWait();
                 } catch (SQLException exception) {
                     exception.printStackTrace();
                 }
             }else{
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setTitle("WARNING");
                 alert.setHeaderText(null);
                 alert.setContentText("Thông tin giảng viên đã bị khóa ");
                 alert.showAndWait();
             }
         }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("ERROR");
             alert.setHeaderText(null);
             alert.setContentText("Bạn chưa nhập học kỳ hoặc bảng dữ liệu học kỳ này đã tồn tại");
             alert.showAndWait();
         }
     }
    public void AddGV(ActionEvent e) throws ParseException, SQLException {
        if(hocKyGV.getValue().length() !=0){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)  && dbController.checkExistTable(tableName)){
                String name=hoVaTenGVTextField.getText();
                String boMon=boMonGVTextField.getText();
                String phone=phoneNumberGVTextField.getText();
                String email=emailGVTextField.getText();
                String phong=phongGVTextField.getText();
                int maLop= Integer.parseInt(maLopGVTextField.getText());
                Lecturers lecturers=new Lecturers(name,boMon,phone,email,phong,maLop);
                boolean check=dataControler.isValidLecturer(lecturers,tableName);//Kiểm tra xem lịch đã đc thêm vào hay chưa
                if(check){
                    if(dbController.addLecturerToDatabase(lecturers,tableName)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công ");
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn đã thêm thông tin giảng viên thành công");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Thông tin giảng viên chưa được thêm thành công");
                        alert.showAndWait();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Thông tin giảng viên đã tồn tại trong database");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Thông tin giảng viên đã bị khóa hoặc bảng không tồn tại");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc các thông tin khác");
            alert.showAndWait();
        }
    }
    public void resetLecturer(ActionEvent e) throws SQLException {
        if(hocKyGV.getValue().length() != 0 && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            //DBController dbController=new DBController();
            ArrayList<Lecturers> lecturers=null;
            try {
                lecturers=dbController.lecturerList(tableName);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //Config lichThi
            if(lecturers.size()==0){
                //
            }else{
                lecturersList=FXCollections.observableArrayList(lecturers);
                hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("nameLecturer"));
                boMonGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("faculty"));
                phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("phoneNumber"));
                emailGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("email"));
                phongGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("workPlace"));
                maLopGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,Integer>("maLop"));
                lecturerTableView.setItems(lecturersList);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ bạn muốn reset hoặc bảng dữ liệu không tồn tại");
            alert.showAndWait();
        }
        hoVaTenGVTextField.setText("");
        boMonGVTextField.setText("");
        phoneNumberGVTextField.setText("");
        emailGVTextField.setText("");
        phongGVTextField.setText("");
        maLopGVTextField.setText("");
    }
    public void updateLecturer(ActionEvent e) throws ParseException, SQLException {
        if(hocKyGV.getValue().length() !=0 && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue() ;
            Lecturers lecturers=lecturerTableView.getSelectionModel().getSelectedItem();
            if(maLopGVTextField.getText().matches("\\d+") ){
                String name=hoVaTenGVTextField.getText();
                String boMon=boMonGVTextField.getText();
                String phone=phoneNumberGVTextField.getText();
                String email=emailGVTextField.getText();
                String phong=phongGVTextField.getText();
                int maLop= Integer.parseInt(maLopGVTextField.getText());
                Lecturers lecturers1=new Lecturers(name,boMon,phone,email,phong,maLop);
                if(dbController.updateeLecturerToDatabase(tableName,lecturers,lecturers1)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công ");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã cập nhật thông tin giảng viên thành công");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Thông tin giảng viên chưa được cập nhật thành công");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhập đầy đủ và đúng định dạng thông tin");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc bảng dữ liệu học kỳ này không tồn tại ");
            alert.showAndWait();
        }
    }
    public void searchLecturer(ActionEvent e) throws SQLException {
        if(hoVaTenGVTextField.getText().length() != 0 && hocKyGV.getValue().length() != 0 &&dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
           String hoTen=hoVaTenGVTextField.getText();
            String tableName = "GiangVien" + hocKyGV.getValue() ;
            ArrayList<Lecturers> list=dbController.searchLecturerFromDatabase(hoTen,tableName);
            if(list.size() ==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Không có tên giảng viên  mà bạn tìm kiếm");
                alert.showAndWait();
            }else{
                lecturersList=FXCollections.observableArrayList(list);
                hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("nameLecturer"));
                boMonGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("faculty"));
                phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("phoneNumber"));
                emailGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("email"));
                phongGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("workPlace"));
                maLopGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,Integer>("maLop"));
                lecturerTableView.setItems(lecturersList);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập tên giảng viên và học kỳ mà bạn tìm kiếm hoặc bảng dữ liệu cho học kỳ này không tồn tại");
            alert.showAndWait();
        }
    }
    public void deleteLecturer(ActionEvent e) throws SQLException {
        if(hocKyGV.getValue().length() != 0 && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                Lecturers lecturers=lecturerTableView.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Xóa thông tin giảng viên");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc muốn xóa thông tin này không?");
                ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                    if (dbController.deleteLecturerFromDatabase(lecturers,tableName)) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Thành Công");
                        alert1.setContentText("Bạn đã xoá thông tin giảng viên thành công ");
                        alert1.showAndWait();
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Lỗi");
                        alert1.setContentText("Xóa không thành công");
                        alert1.showAndWait();
                    }
                } else {
                    alert.close();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Thông tin đã bị khóa ");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ mà bạn muốn xóa hoặc bảng không tồn tại");
            alert.showAndWait();
        }
    }
    public void dataLecturerLock(ActionEvent e) throws SQLException {
        if(hocKyGV.getValue().length() != 0 && dataControler.isCheckDataLock("GiangVien" + hocKyGV.getValue()) && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            Connection conn =dbConnection.getConnection();
            String sql="insert into dbo.LockData values(?,?) ;";
            var prepare=conn.prepareStatement(sql) ;
            prepare.setString(1,tableName);
            prepare.setInt(2,1);
            var result=prepare.executeUpdate();
            if(result>0){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thành Công");
                alert1.setContentText("Bạn đã khóa thông tin giảng viên thành công ");
                alert1.showAndWait();
            }else{
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Lỗi");
                alert1.setContentText("Khóa không thành công");
                alert1.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc bảng dữ liệu học kỳ này đã bị khóa hoặc bảng không tồn tại");
            alert.showAndWait();
        }
    }
    //giám thị
    public void importFileExcelGT(ActionEvent e) throws IOException, SQLException {
         final int MAX_ROW=1000000;
//         final int MIN_COLUMN=6;
//         final int START_ROW=2;
        //connect to database
        //DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        if(hocKyGT.getValue().length() !=0  && !dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                //Delete table
                String sql1="IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES Where Table_Schema = 'dbo'  AND Table_Name = '"+tableName+"') "+
                        "BEGIN drop table "+tableName+" END;";
                try {
                    var prepareExists=conn.prepareStatement(sql1);
                    prepareExists.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                //create tableName
                String sql="CREATE TABLE "+tableName +"(hoTen nvarchar(50) ,boMon nvarchar(50),phone bigint,email nvarchar(50),phong nvarchar(50));";
                try {
                    var prepare=conn.prepareStatement(sql);
                    prepare.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                //choose a file excel
                Stage stage=(Stage) supervisorAnchorPane.getScene().getWindow();
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("Import file Excel");
                File file=fileChooser.showOpenDialog(stage);

                //settings
                String sourceFile=null;
                if(file != null){
                    sourceFile=file.getPath();
                }
                String sheetName="GiamThi" + hocKyGT.getValue();
                try {
                    //read file excel
                    FileInputStream fileInputStream=new FileInputStream(sourceFile);
                    XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
                    XSSFSheet sheet=workbook.getSheet(sheetName);

                    //read file to database
                    String sqlAdd = "INSERT INTO "+tableName+" VALUES (?, ?, ?, ?, ?);";
                    var prepareAdd=conn.prepareStatement(sqlAdd);
//                     int startRow=Math.max(START_ROW-1,sheet.getFirstRowNum());
                    int lastRow=Math.max(MAX_ROW,sheet.getLastRowNum());
                    for(int i=1;i<lastRow;i++){
                        Row row=sheet.getRow(i);
                        if(row==null){
                            continue;
                        }
                        //int lastColumn=Math.max(MIN_COLUMN,row.getLastCellNum());
                        for(int cn=0;cn<5;cn++){
                            Cell c=row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
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
//                         prepareAdd.setString(1, String.valueOf(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(2, String.valueOf(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(3, String.valueOf(row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(4, String.valueOf(row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setString(5, String.valueOf(row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getRichStringCellValue()));
//                         prepareAdd.setInt(6, (int) row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue());
                        prepareAdd.addBatch();
                    }
                    prepareAdd.executeBatch();
                    conn.commit();
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công ");
                    alert.setContentText("Bạn đã import dữ liệu thành công");
                    alert.showAndWait();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Thông tin giám thị đã bị khóa ");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc bảng đã tồn tại");
            alert.showAndWait();
        }
    }
    public void AddGT(ActionEvent e) throws ParseException, SQLException {
        if(hocKyGT.getValue().length() !=0){
            String tableName = "GiamThi" + hocKyGT.getValue();
            if(dataControler.isCheckDataLock(tableName)  && dbController.checkExistTable(tableName)){
                String name=hoVaTenGTTextField.getText();
                String boMon=boMonGTTextField.getText();
                String phone=phoneNumberGTTextField.getText();
                String email=emailGTTextField.getText();
                String phong=phongGTTextField.getText();
                Supervisor supervisor=new Supervisor(name,boMon,phone,email,phong);
                boolean check=dataControler.isValidSupervisor(supervisor,tableName);//Kiểm tra xem giám thị  đã đc thêm vào hay chưa
                if(check){
                    if(dbController.addSupervisorToDatabase(supervisor,tableName)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công ");
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn đã thêm thông tin giám thị thành công");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Thông tin giám thị chưa được thêm thành công");
                        alert.showAndWait();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Thông tin giám thị đã tồn tại trong database");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Thông tin giám thị đã bị khóa hoặc bảng không tồn tại");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc các thông tin khác");
            alert.showAndWait();
        }
    }
    public void resetSupervisor(ActionEvent e) throws SQLException {
        if(hocKyGT.getValue().length() != 0 && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            //DBController dbController=new DBController();
            ArrayList<Supervisor> supervisors =null;
            try {
                supervisors=dbController.supervisorList(tableName);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //Config lichThi
            if(supervisors.size()==0){
                //
            }else{
                supervisorsList=FXCollections.observableArrayList(supervisors);
                hoVaTenGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("nameLecturer"));
                boMonGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("faculty"));
                phoneNumberGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("phoneNumber"));
                emailGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("email"));
                phongGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("workPlace"));
                supervisorTableView.setItems(supervisorsList);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập tên bảng cơ sở dữ liệu bạn muốn reset hoặc bảng dữ liệu không tồn tại");
            alert.showAndWait();
        }
        hoVaTenGTTextField.setText("");
        boMonGTTextField.setText("");
        phoneNumberGTTextField.setText("");
        emailGTTextField.setText("");
        phongGTTextField.setText("");
    }
    public void updateSupervisor(ActionEvent e) throws ParseException, SQLException {
        if(hocKyGT.getValue().length() !=0 && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            Supervisor supervisor=supervisorTableView.getSelectionModel().getSelectedItem();
            if(phoneNumberGTTextField.getText().matches("\\d+") ){
                String name=hoVaTenGTTextField.getText();
                String boMon=boMonGTTextField.getText();
                String phone=phoneNumberGTTextField.getText();
                String email=emailGTTextField.getText();
                String phong=phongGTTextField.getText();
                 Supervisor supervisor1=new Supervisor(name,boMon,phone,email,phong);
                if(dbController.updateeSupervisorToDatabase(tableName,supervisor,supervisor1)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công ");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã cập nhật thông tin giám thị thành công");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Thông tin giám thị chưa được cập nhật thành công");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhập đầy đủ và đúng định dạng thông tin");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa học kỳ hoặc bảng không tồn tại ");
            alert.showAndWait();
        }
    }
    public void searchSupervisor(ActionEvent e) throws SQLException {
        if(hoVaTenGTTextField.getText().length() != 0 && hocKyGT.getValue().length() != 0 &&dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String hoTen=hoVaTenGTTextField.getText();
            String tableName = "GiamThi" + hocKyGT.getValue();
            Supervisor list=dbController.searchSupervisorFromDatabase(hoTen,tableName);
            if(list== null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Không có tên giám thị mà bạn tìm kiếm");
                alert.showAndWait();
            }else{
                supervisorsList=FXCollections.observableArrayList(list);
                hoVaTenGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("nameLecturer"));
                boMonGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("faculty"));
                phoneNumberGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("phoneNumber"));
                emailGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("email"));
                phongGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("workPlace"));
                supervisorTableView.setItems(supervisorsList);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập tên giám thị và bảng lưu thông tin giám thị mà bạn tìm kiếm hoặc bảng bạn nhập không tồn tại");
            alert.showAndWait();
        }
    }
    public void deleteSupervisor(ActionEvent e) throws SQLException {
        if(hocKyGT.getValue().length() != 0 && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                Supervisor supervisor=supervisorTableView.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Xóa thông tin giám thị");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc muốn xóa thông tin này không?");
                ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
                    if (dbController.deleteSupervisorFromDatabase(supervisor,tableName)) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Thành Công");
                        alert1.setContentText("Bạn đã xoá thông tin giám thị thành công ");
                        alert1.showAndWait();
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Lỗi");
                        alert1.setContentText("Xóa không thành công");
                        alert1.showAndWait();
                    }
                } else {
                    alert.close();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Thông tin đã bị khóa ");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ mà bạn muốn xóa hoặc bảng không tồn tại");
            alert.showAndWait();
        }
    }
    public void dataSupervisorLock(ActionEvent e) throws SQLException {
        if(hocKyGT.getValue().length() != 0 && dataControler.isCheckDataLock("GiamThi" + hocKyGT.getValue()) && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            Connection conn =dbConnection.getConnection();
            String sql="insert into dbo.LockData values(?,?) ;";
            var prepare=conn.prepareStatement(sql) ;
            prepare.setString(1,tableName);
            prepare.setInt(2,1);
            var result=prepare.executeUpdate();
            if(result>0){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thành Công");
                alert1.setContentText("Bạn đã khóa thông tin giám thị thành công ");
                alert1.showAndWait();
            }else{
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Lỗi");
                alert1.setContentText("Khóa không thành công");
                alert1.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ hoặc bảng đã bị khóa hoặc bảng không tồn tại");
            alert.showAndWait();
        }
    }
    //Phân công lịch thi
    public void phanCong(ActionEvent e) throws SQLException {
        if(hocKyPC.getValue().length() != 0 && dbController.checkExistTable("GiamThi" +
                hocKyPC.getValue()) && dbController.checkExistTable("LichThi" + hocKyPC.getValue())){
            String tableNameGT = "GiamThi" + hocKyPC.getValue();
            String tableNameLT = "LichThi" + hocKyPC.getValue();
            String tableNamePhanCong="PhanCong" + hocKyPC.getValue();
            if(!dbController.checkExistTable(tableNamePhanCong)){
                ArrayList<Supervisor> supervisors=dbController.supervisorList(tableNameGT);
                ArrayList<TestSchedule> testSchedules=dbController.testScheduleList(tableNameLT);
                int testNumber=testSchedules.size();
                int GTNumber=supervisors.size();
                for(int j=0;j<testNumber;j++){
                    int sldk=testSchedules.get(j).getSLDK();
                    int giamThiNumber=(sldk>=60) ? 2:1;
                    int count=0;
                    ArrayList<Supervisor> list=new ArrayList<>();
                    for(int i=0 ;i<GTNumber;i++){
                        if(!dataControler.isCheckGT(supervisors.get(i).getNameLecturer(),tableNamePhanCong,testSchedules.get(j))){
                            list.add(supervisors.get(i));
                            count++;
                        }
                        if(count==giamThiNumber) break;
                    }
                    dbController.addPhanCong(testSchedules.get(j),list,tableNamePhanCong);
                }
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setHeaderText(null);
                alert.setContentText("Bạn đã phân công thành công");
                alert.showAndWait();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn đã phân công giám thị cho học kỳ này");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập tên học kỳ bạn muốn phân công");
            alert.showAndWait();
        }
    }

    public void UpdatePhanCong(ActionEvent e) throws IOException {
        Stage stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdatePCSample.fxml"));
        Parent parent=loader.load();
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        InfoTrongThi infoTrongThi=tableViewTrongThi.getSelectionModel().getSelectedItem();
        UpdatePCController controller=loader.getController();
        controller.setTrongThi(infoTrongThi);
        stage.setTitle("Cập nhật thông tin phân công trông thi");
        stage.show();
    }

    public void ResetPC(ActionEvent e) throws SQLException {
        if(hocKyPC.getValue().length() !=0){
            String tableName="PhanCong"+hocKyPC.getValue();
            ArrayList<InfoTrongThi> trongThi=null;
            trongThi=dbController.trongThiList(tableName);
            if(trongThi==null){
                //
            }else{
                trongThiList=FXCollections.observableArrayList(trongThi);
                malopTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Integer>("maLop"));
                giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi1"));
                giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi2"));
                ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Date>("ngayThi"));
                kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("kipThi"));
                tableViewTrongThi.setItems(trongThiList);
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập tên học kỳ bạn muốn phân công");
            alert.showAndWait();
        }
    }
    public void SearchPC(ActionEvent e) throws SQLException {
        if(maLopTT.getText().length() !=0 && hocKyPC.getValue().length() !=0){
           int maLop= Integer.parseInt(maLopTT.getText());
            String tableName="PhanCong"+hocKyPC.getValue();
            ArrayList<InfoTrongThi> list=dbController.searchInfoTrongThiFromDatabase(maLop,tableName);
            if(list.size() == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Không có mã lớp mà bạn tìm kiếm");
                alert.showAndWait();
            }else{
                trongThiList=FXCollections.observableArrayList(list);
                malopTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Integer>("maLop"));
                giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi1"));
                giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi2"));
                ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Date>("ngayThi"));
                kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("kipThi"));
                tableViewTrongThi.setItems(trongThiList);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập mã lớp và học kỳ hoặc học kỳ bạn nhập không tồn tại");
            alert.showAndWait();
        }
    }
    public void detailPC(ActionEvent e) throws SQLException {
        if(tableViewTrongThi.getSelectionModel().getSelectedItem() != null && hocKyPC.getValue().length() != 0){
            String tableSupervisor="GiamThi"+hocKyPC.getValue();
            InfoTrongThi infoTrongThi=tableViewTrongThi.getSelectionModel().getSelectedItem();
            Supervisor supervisor1=dbController.searchSupervisorFromDatabase(infoTrongThi.getGiamThi1(),tableSupervisor);
            Supervisor supervisor2=dbController.searchSupervisorFromDatabase(infoTrongThi.getGiamThi2(),tableSupervisor);
            detailPC.appendText("____________Thông tin chi tiết_______________\n");
            detailPC.appendText("______ Mã Lớp: "+infoTrongThi.getMaLop()+"\n");
            detailPC.appendText("______Ngày thi:"+infoTrongThi.getNgayThi()+"\n");
            detailPC.appendText("______Kíp thi: "+infoTrongThi.getKipThi()+"\n");
            detailPC.appendText("____________Thông tin giám thị_______________"+"\n");
            detailPC.appendText("_________________Giám thị 1__________________\n");
            detailPC.appendText("______Họ tên: "+supervisor1.getNameLecturer()+"\n");
            detailPC.appendText("______Bộ môn: "+supervisor1.getFaculty()+"\n");
            detailPC.appendText("______Số điện thoại: "+supervisor1.getPhoneNumber()+"\n");
            detailPC.appendText("______Email: "+supervisor1.getEmail()+"\n");
            detailPC.appendText("______Phòng làm việc: "+supervisor1.getWorkPlace()+"\n");
            if(supervisor2 != null){
                detailPC.appendText("_________________Giám thị 2__________________\n");
                detailPC.appendText("______Họ tên: "+supervisor2.getNameLecturer()+"\n");
                detailPC.appendText("______Bộ môn: "+supervisor2.getFaculty()+"\n");
                detailPC.appendText("______Số điện thoại: "+supervisor2.getPhoneNumber()+"\n");
                detailPC.appendText("______Email: "+supervisor2.getEmail()+"\n");
                detailPC.appendText("______Phòng làm việc: "+supervisor2.getWorkPlace()+"\n");
            }else{
                detailPC.appendText("__________________Giám thị 2___________________\n");
                detailPC.appendText("___________________Không có____________________\n");
            }
        }
    }
    //chức năng quản lý kinh phí
    public void AddDG(ActionEvent e) throws SQLException {
        if(hocKyKP.getValue() != null ){
            String tableName="DonGia" + hocKyKP.getValue();
            if(giaKP.getText().length() != 0 ){
                long gia= Long.parseLong(giaKP.getText());
                String tenDonGia=tenDonGiaKP.getValue();
                if(dbController.addDonGia(tenDonGia,gia,tableName)){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã thêm đơn giá thành công");
                    alert.showAndWait();
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn chưa thêm đơn giá thành công ");
                    alert.showAndWait();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhập đầy đủ thông tin ");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhâp học kỳ ");
            alert.showAndWait();
        }
    }

    public void updateDG(ActionEvent e) throws SQLException {
        if(hocKyKP.getValue().length() != 0 ){
            String tableName="DonGia" + hocKyKP.getValue();
            if(giaKP.getText().length()!=0 && tenDonGiaKP.getValue().length() !=0 ){
                DonGia donGia = donGiaTableView.getSelectionModel().getSelectedItem();
                DonGia donGia1 = new DonGia(tenDonGiaKP.getValue(),Long.parseLong(giaKP.getText()));
                if(dbController.updateDonGia(donGia,donGia1,tableName)){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn cập nhật đơn giá thành công ");
                    alert.showAndWait();
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn chưa cập nhật đơn giá thành công ");
                    alert.showAndWait();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhập đầy đủ thông tin ");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhâp học kỳ ");
            alert.showAndWait();
        }
    }
    public void tinhToanChiPhi(ActionEvent e) throws SQLException {
        if(hocKyKP.getValue().length() != 0){
            String tableNameGV="GiangVien" + hocKyKP.getValue();
            String tableNameDG="DonGia" + hocKyKP.getValue();
            String tableNameKP="KinhPhi" + hocKyKP.getValue();
            if(dbController.checkExistTable(tableNameKP)){
                String sql="drop table "+tableNameKP;
                var prepare=new DBConnection().getConnection().prepareStatement(sql);
                prepare.executeUpdate();
            }
            String sql1="create table " + tableNameKP + " (maLop int,tenGV nvarchar(50) ,inAn bigint ,phoTo bigint,toChucThi bigint,kinhPhiGT bigint,checkThanhToan int,ghiChu nvarchar(50));";
            var prepare=dbConnection.getConnection().prepareStatement(sql1);
            prepare.executeUpdate();
            String tableNameLT = "LichThi" + hocKyKP.getValue();
            ArrayList<TestSchedule> listLT=dbController.testScheduleList(tableNameLT);
            if(listLT != null){
                for (TestSchedule test:listLT) {
                    int maLop=test.getMaLop();
                    String tenGV=dbController.searchNameLecturer(maLop,tableNameGV);
                    long inAn=dbController.getGia("in ấn",tableNameDG)*test.getSLDK();
                    long phoTo=dbController.getGia("Phô tô đề thi",tableNameDG)*test.getSLDK();
                    long toChucThi=dbController.getGia("Kinh phí tổ chức",tableNameDG)*test.getSLDK();
                    long kinhPhiGT=dbController.getGia("Tiền giám thị",tableNameDG)*((test.getSLDK()>=60) ? 2:1) ;
                    KinhPhi kinhPhi=new KinhPhi(maLop,tenGV,inAn,phoTo,toChucThi,kinhPhiGT,0,test.getGhiChu());
                    dbController.addKinhPhi(kinhPhi,tableNameKP);
                }
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setHeaderText(null);
                alert.setContentText("Tính toán kinh phí thành công");
                alert.showAndWait();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Học kỳ bạn nhập không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhâp học kỳ ");
            alert.showAndWait();
        }
    }

    public void ThanhToanChiPhi(ActionEvent e) throws SQLException {
        if(hocKyKP.getValue().length() != 0){
            if(maLopKP.getText().length() !=0){
                String tableName="KinhPhi" + hocKyKP.getValue();
                String sql="update "+ tableName+ " set checkThanhToan=? where maLop = ?";
                var prepare=new DBConnection().getConnection().prepareStatement(sql);
                prepare.setInt(1,1);
                prepare.setInt(2, Integer.parseInt(maLopKP.getText()));
                if(prepare.executeUpdate()>0) {
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn thanh toán thành công cho maLop: "+maLopKP.getText());
                    alert.showAndWait();
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn chưa thanh toán thành công cho maLop: "+maLopKP.getText());
                    alert.showAndWait();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn hãy nhập mã lớp để thanh toán ");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhâp học kỳ ");
            alert.showAndWait();
        }
    }
    public void chiTietThanhToan(ActionEvent e) throws SQLException {
        if(hocKyKP.getValue().length() != 0){
            String tableNameKP="KinhPhi" + hocKyKP.getValue();
            String tableNameLT="LichThi" + hocKyKP.getValue();
            if(maLopKP.getText().length() != 0 && dbController.checkExistTable(tableNameKP)){
                if(detailKP.getText().length() !=0) detailKP.setText("");
                int maLop= Integer.parseInt(maLopKP.getText());
                ArrayList<KinhPhi> listKP=dbController.searchKinhPhiList(maLop,tableNameKP);
                detailKP.appendText("Chi tiết thanh toán mã lớp: "+maLopKP.getText()+"\n");
                detailKP.appendText("Giảng viên giảng dạy: "+listKP.get(0).getTenGV()+"\n");
                long sum=0;
                for(KinhPhi kp:listKP){
                    detailKP.appendText("Kinh Phí chi tiết cho nhóm: "+kp.getGhiChu()+"_Mã lớp: "+kp.getMaLop()+"\n");
                    detailKP.appendText("In ấn: "+kp.getInAn()+"\n");
                    sum+=kp.getInAn();
                    detailKP.appendText("Phô tô đề thi: "+kp.getPhoTo()+"\n");
                    sum+=kp.getPhoTo();
                    detailKP.appendText("Kinh phí tổ chức thi: "+kp.getToChucThi()+"\n");
                    sum+=kp.getToChucThi();
                    detailKP.appendText("Kinh phí giám thị : "+kp.getKinhPhiGT()+"\n");
                    sum+=kp.getKinhPhiGT();
                    detailKP.appendText("             ------------             "+"\n");
                }
                detailKP.appendText(" Tổng kinh phí: "+sum+" VND"+"\n");
                detailKP.appendText(" Đã thanh toán chi phí chưa: "+((listKP.get(0).getCheckThanhToan()==1) ? "Đã thanh toán":"Chưa thanh toán"));
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhâp mã lớp hoặc dữ liệu học kỳ này không tồn tại");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhâp học kỳ ");
            alert.showAndWait();
        }
    }
    public void resetKP(ActionEvent e) throws SQLException {
        if(hocKyKP.getValue() != null){
            String tableNameDG="DonGia" + hocKyKP.getValue();
            String tableNameKP="KinhPhi" + hocKyKP.getValue();
            ArrayList<DonGia> donGia=dbController.donGiaList(tableNameDG);
            donGiaList=FXCollections.observableArrayList(donGia);
            tenDonGiaTableColumn.setCellValueFactory(new PropertyValueFactory<DonGia,String>("tenDonGia"));
            giaTableColumn.setCellValueFactory(new PropertyValueFactory<DonGia,Long>("gia"));
            donGiaTableView.setItems(donGiaList);

            ArrayList<KinhPhi> kinhPhi=dbController.kinhPhiList(tableNameKP);
            kinhPhiList=FXCollections.observableArrayList(kinhPhi);
            maLopKPTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Integer>("maLop"));
            tenGVTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,String>("tenGV"));
            inAnTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("inAn"));
            phoToTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("phoTo"));
            toChucThiTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("toChucThi"));
            kinhPhiGTTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Long>("kinhPhiGT"));
            checkThanhToanTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,Integer>("checkThanhToan"));
            ghiChuKPTableColumn.setCellValueFactory(new PropertyValueFactory<KinhPhi,String>("ghiChu"));
            kinhPhiTableView.setItems(kinhPhiList);
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhâp học kỳ ");
            alert.showAndWait();
        }
    }
    //Báo cáo thống kê
    public void DetailBC(ActionEvent e) throws SQLException, ParseException {
        if(gvCheckBox.isSelected() && hocKyBCTK.getValue() != null && hotenGV_GT.getText().length() != 0) {
            String name = hotenGV_GT.getText();
            ArrayList<BaoCao> baoCaos = dbController.BaoCaoGV(name, "KinhPhi" + hocKyBCTK.getValue());
            if(baoCaos.size() != 0) {
                maLopTableColumn.setText("Mã lớp");
                cot2.setText("Tổng chi phí");
                cot3.setText("Trạng thái thanh toán");
                baoCaoList =  FXCollections.observableList(baoCaos);
                maLopTableColumn.setCellValueFactory(new PropertyValueFactory<BaoCao, Integer>("maLop"));
                cot2.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot2"));
                cot3.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot3"));
                baoCaoTableView.setItems(baoCaoList);
            } else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Tên giảng viên không tồn tại ");
                alert.showAndWait();
            }
        } else if(gtCheckBox.isSelected() && hocKyBCTK.getValue() != null && hotenGV_GT.getText().length() != 0) {
            String name = hotenGV_GT.getText();
            ArrayList<BaoCao> baoCaos = dbController.BaoCaoGT(name, hocKyBCTK.getValue());
            if(baoCaos.size() != 0) {
                maLopTableColumn.setText("Mã lớp");
                cot2.setText("Trạng thái tổ chức");
                cot3.setText("Trạng thái thanh toán");
                baoCaoList =  FXCollections.observableList(baoCaos);
                maLopTableColumn.setCellValueFactory(new PropertyValueFactory<BaoCao, Integer>("maLop"));
                cot2.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot2"));
                cot3.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot3"));
                baoCaoTableView.setItems(baoCaoList);
            } else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Tên giám thị không tồn tại ");
                alert.showAndWait();
            }
        } else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Chưa nhập học kỳ hoặc chưa chọn loại thông tin hoặc họ tên giảng viên ");
            alert.showAndWait();
        }
    }
    @FXML
    BarChart<String, Number> barChart;
    public void ThongKe(ActionEvent e) throws SQLException {
        if(hocKyTK.getValue() != null) {
            String tableName = "KinhPhi" + hocKyTK.getValue();
            ArrayList<KinhPhi> list = dbController.kinhPhiList(tableName);
            long inAn = 0, phoTo = 0, toChuc = 0, giamThi = 0;
            for(var i : list) {
                inAn += i.getInAn();
                phoTo += i.getPhoTo();
                toChuc += i.getToChucThi();
                giamThi += i.getKinhPhiGT();
            }
            XYChart.Series<String, Number> chiPhi = new XYChart.Series<>();
            XYChart.Data<String, Number> chiPhiInAn = new XYChart.Data<>("IN ẤN", inAn);
            XYChart.Data<String, Number> chiPhiPhoTo = new XYChart.Data<>("PHÔ TÔ", phoTo);
            XYChart.Data<String, Number> chiPhitoChuc = new XYChart.Data<>("CHI PHÍ TỔ CHỨC", toChuc);
            XYChart.Data<String, Number> chiPhiGiamThi = new XYChart.Data<>("CHI PHÍ GIÁM THỊ", giamThi);
            chiPhi.getData().addAll(chiPhiInAn, chiPhiPhoTo, chiPhitoChuc, chiPhiGiamThi);
            chiPhi.setName("Thống kê các chi phí của cả học kỳ " + hocKyTK.getValue());
            barChart.getData().clear();
            barChart.getData().addAll(chiPhi);
            for(var i : chiPhi.getData()) {
                i.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("CHI PHÍ");
                    alert.setHeaderText(null);
                    alert.setContentText("TIỀN " + i.getXValue() + " : " + i.getYValue().longValue() + " VND");
                    alert.showAndWait();
                });
            }
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa nhập học kỳ");
            alert.showAndWait();
        }
    }
}
