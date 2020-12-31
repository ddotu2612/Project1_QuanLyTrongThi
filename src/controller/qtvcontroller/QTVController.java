package controller;

import controller.baocaothongke.BaoCaoChiTiet;
import controller.baocaothongke.ThongKe;
import controller.giamthicontroller.*;
import controller.giaoviencontroller.*;
import controller.lichthicontroller.*;
import controller.phanconglichthi.CapNhatThongTinTrongThi;
import controller.phanconglichthi.ChiTietPhanCong;
import controller.phanconglichthi.PhanCong;
import controller.phanconglichthi.TimKiemPhanCong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import view.Logout;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class QTVController implements Initializable {
    DBController dbController = new DBController();
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
    Spinner soBuoi;
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
    TableColumn<Supervisor, Integer> soBuoiColum;
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
    @FXML TableColumn<KinhPhi,String> nhomKPTableColumn;
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
        hocKyGV.setItems(listHKGV);
        hocKyGT.setItems(listHKGT);
        hocKyPC.setItems(listHKPC);
        hocKyKP.setItems(listHKKP);
        hocKyBCTK.setItems(listHKBCTK);
        hocKyTK.setItems(listHKTK);
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        soBuoi.setValueFactory(spinnerValueFactory);

        //Lịch thi
        hocKyLT.setOnAction(e->{
            if(!hocKyLT.getSelectionModel().isEmpty()) {
                new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                        tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, testScheduleList, hocKyLT);
            }
        });
        testScheduleTableView.setOnMousePressed(e->{
            TestSchedule lichThi = testScheduleTableView.getSelectionModel().getSelectedItem();
            try {
                maLopTextField.setText(String.valueOf(lichThi.getMaLop()));
                maHPTextField.setText(lichThi.getMaHP());
                tenHPTextField.setText(lichThi.getTenHP());
                ghiChuTextField.setText(lichThi.getGhiChu());
                nhomTextField.setText(lichThi.getNhom());
                dotMoTextField.setText(lichThi.getDotMo());
                tuanTextField.setText(lichThi.getTuan());
                thuTextField.setText(lichThi.getThu());
                kipTextField.setText(lichThi.getKip());
                sldkTextField.setText(String.valueOf(lichThi.getSLDK()));
                phongTextField.setText(lichThi.getPhong());
            } catch (Exception ex) {
                Error.ThongBaoLoi("Không có dữ liệu");
            }
        });

        //config Giao Vien
        hocKyGV.setOnAction(e->{
            if(!hocKyGV.getSelectionModel().isEmpty()) {
                new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                        maLopGVColumn, lecturersList, hocKyGV);
            }
        });
        lecturerTableView.setOnMousePressed(e->{
            Lecturers gv = lecturerTableView.getSelectionModel().getSelectedItem();
            try{
                hoVaTenGVTextField.setText(gv.getNameLecturer());
                boMonGVTextField.setText(gv.getFaculty());
                phoneNumberGVTextField.setText(gv.getPhoneNumber());
                emailGVTextField.setText(gv.getEmail());
                phongGVTextField.setText(gv.getWorkPlace());
                maLopGVTextField.setText(String.valueOf(gv.getMaLop()));
            } catch (Exception ex) {
                Error.ThongBaoLoi("Không có dữ liệu");
            }
        });

        //config Giám thị
        hocKyGT.setOnAction(e->{
            if(!hocKyGT.getSelectionModel().isEmpty()) {
                CapNhatGT.CapNhat(supervisorTableView, hoVaTenGTColumn, boMonGTColumn,
                        phoneNumberGTColumn,emailGTColumn,phongGTColumn,soBuoiColum,supervisorsList,hocKyGT);
            }
        });
        supervisorTableView.setOnMousePressed(e->{
            Supervisor gt = supervisorTableView.getSelectionModel().getSelectedItem();
            try {
                hoVaTenGTTextField.setText(gt.getNameLecturer());
                boMonGTTextField.setText(gt.getFaculty());
                phoneNumberGTTextField.setText(gt.getPhoneNumber());
                emailGTTextField.setText(gt.getEmail());
                phongGTTextField.setText(gt.getWorkPlace());
            } catch (Exception ex) {
                Error.ThongBaoLoi("Không có dữ liệu");
            }
        });

        //config phân công trông thi
        hocKyPC.setOnAction(e->{
            if(!hocKyPC.getSelectionModel().isEmpty()) {
                CapNhatThongTinTrongThi.CapNhat(hocKyPC, tableViewTrongThi, malopTTColumn, giamThi1TTColumn, giamThi2TTColumn,
                        ngayThiTTColumn, kipThiTTColumn,trongThiList);
            }
        });
        tableViewTrongThi.setOnMousePressed(e->{
            InfoTrongThi tt = tableViewTrongThi.getSelectionModel().getSelectedItem();
            try {
                maLopTT.setText(String.valueOf(tt.getMaLop()));
                ChiTietPhanCong.ChiTiet(hocKyPC, tableViewTrongThi, detailPC);
            } catch (Exception ex) {
                Error.ThongBaoLoi("Không có thông tin");
            }
        });

        //config kinh phí
        tenDonGiaKP.setItems(listKP);
        //Bảng Đơn giá
        hocKyKP.setOnAction(e->{
            if(!hocKyKP.getSelectionModel().isEmpty()) {
                controller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                        giaTableColumn,donGiaList,hocKyKP);
                controller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                        phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                        kinhPhiList,hocKyKP);
            }
        });
        donGiaTableView.setOnMousePressed(e->{
            DonGia donGia = donGiaTableView.getSelectionModel().getSelectedItem();
            try {
                tenDonGiaKP.setValue(donGia.getTenDonGia());
                giaKP.setText(String.valueOf(donGia.getGia()));
            } catch (Exception ex) {
                Error.ThongBaoLoi("Không có thông tin");
            }
        });
        //Bảng kinh phí
        kinhPhiTableView.setOnMousePressed(e->{
            KinhPhi kp = kinhPhiTableView.getSelectionModel().getSelectedItem();
            try {
                maLopKP.setText(String.valueOf(kp.getMaLop()));
            } catch (Exception ex) {
                Error.ThongBaoLoi("Không có dữ liệu");
            }
        });
        gvCheckBox.setOnAction(event -> {
            if(gvCheckBox.isSelected()) gtCheckBox.setSelected(false);
        });
        gtCheckBox.setOnAction(event -> {
            if(gtCheckBox.isSelected()) gvCheckBox.setSelected(false);
        });
        gvCheckBox.setSelected(true);
    }

    public void Logout(){
        try{
            new Logout().Excute_Logout();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //QUẢN LÝ TẠO LỊCH THI

    public void importFileExcel(ActionEvent e) {
        try{
            new TaoLichThi().TaoLich(hocKyLT, testScheduleAnchorPane);
            new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, testScheduleList, hocKyLT);
        } catch (Exception ex) {
//            Error.ThongBaoLoi("Chưa nhập học kỳ");
            ex.printStackTrace();
        }
    }

    public void Add(ActionEvent e) {
        try {
            new ThemLichThi().ThemLich(hocKyLT, maLopTextField,maHPTextField,tenHPTextField,ghiChuTextField,nhomTextField,
                    dotMoTextField,tuanTextField,thuTextField,kipTextField,sldkTextField,phongTextField,ngayThiLT);
            new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, testScheduleList, hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ các thông tin hoặc thông tin không đúng định dạng");
        }
    }

    public void resetTestSchedule(ActionEvent e) {
        try {
            if(hocKyLT.getValue().length() != 0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
                String tableName = "LichThi" + hocKyLT.getValue();
                new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                        tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, testScheduleList, hocKyLT);
            }else{
                Error.ThongBaoLoi("bảng dữ liệu không tồn tại");
            }
            maLopTextField.setText("");
            maHPTextField.setText("");
            tenHPTextField.setText("");
            ghiChuTextField.setText("");
            nhomTextField.setText("");
            dotMoTextField.setText("");
            tuanTextField.setText("");
            thuTextField.setText("");
            kipTextField.setText("");
            sldkTextField.setText("");
            phongTextField.setText("");
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }

    public void updateTestSchedule(ActionEvent e){
        try{
            new SuaLichThi().sua(hocKyLT, maLopTextField,maHPTextField,tenHPTextField,ghiChuTextField,nhomTextField,
                    dotMoTextField,tuanTextField,thuTextField,kipTextField,sldkTextField,phongTextField,ngayThiLT,testScheduleTableView);
            new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, testScheduleList, hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa đầy đủ thông tin ");
        }
    }

    public void searchTestSchedule(ActionEvent e){
        try{
            if(maLopTextField.getText().length() != 0 && hocKyLT.getValue().length() != 0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
                int maLop= Integer.parseInt(maLopTextField.getText());
                String tableName = "LichThi" + hocKyLT.getValue();
                ArrayList<TestSchedule> list=dbController.searchTestScheduleFromDatabase(maLop,tableName);
                if(list.size() ==0){
                    Information.ThongBaoThongTin("Không có mã lớp mà bạn tìm kiếm");
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
                Error.ThongBaoLoi("Bạn chưa nhập mã lớp hoặc học kỳ mà bạn tìm kiếm hoặc dữ liệu học kỳ bạn nhập không tồn tại");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn nhập thiếu hoặc chưa đúng định dạng dữ liệu");
        }

    }
    public void deleteTestSchedule(ActionEvent e) throws SQLException {
        try {
            new XoaLichThi().xoa(hocKyLT,testScheduleTableView);
            new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, testScheduleList, hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Lỗi! Bạn chưa nhập chọn kỳ hoặc hãy chọn lịch thi mà bạn muốn xóa ( có thể tìm kiếm theo mã lớp )");
        }
    }

    public void dataTestScheduleLock(ActionEvent e) {
        try{
            new KhoaLichThi().Khoa(hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Chưa nhập học kỳ hoặc khóa lịch thi không thành công");
        }
     }

     //QUẢN lÝ THÔNG TIN GIẢNG VIÊN

    public void importFileExcelGV(ActionEvent e) {
         try {
             new TaoThongTinGV().TaoTT(hocKyGV, lecturerAnchorPane);
             new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                     maLopGVColumn, lecturersList, hocKyGV);
         } catch (Exception ex) {
             Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa chọn file");
         }
    }

    public void AddGV(ActionEvent e) {
        try {
            new ThemGiangVien().Them(hoVaTenGVTextField, boMonGVTextField,phoneNumberGVTextField,
                    emailGVTextField,phongGVTextField,maLopGVTextField,hocKyGV);
            new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                    maLopGVColumn, lecturersList, hocKyGV);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Nhập chưa đủ thông tin và sai định dạng");
        }
    }

    public void resetLecturer(ActionEvent e) {
        try{
            if(hocKyGV.getValue().length() != 0 && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
                String tableName = "GiangVien" + hocKyGV.getValue();
                new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                        maLopGVColumn, lecturersList, hocKyGV);
            }else{
                Error.ThongBaoLoi("Bạn chưa nhập học kỳ bạn muốn reset hoặc bảng dữ liệu không tồn tại");
            }
            hoVaTenGVTextField.setText("");
            boMonGVTextField.setText("");
            phoneNumberGVTextField.setText("");
            emailGVTextField.setText("");
            phongGVTextField.setText("");
            maLopGVTextField.setText("");
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }

    public void updateLecturer(ActionEvent e) {
        try {
            new SuaGiaoVien().Sua(hoVaTenGVTextField, boMonGVTextField, phoneNumberGVTextField, emailGVTextField, phongGVTextField,
                    maLopGVTextField, hocKyGV, lecturerTableView);
            new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                    maLopGVColumn, lecturersList, hocKyGV);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ thông tin và chưa đúng định dạng");
        }
    }

    public void searchLecturer(ActionEvent e) {
        try{
            if(hoVaTenGVTextField.getText().length() != 0 && hocKyGV.getValue().length() != 0 &&dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
                String hoTen = hoVaTenGVTextField.getText();
                String tableName = "GiangVien" + hocKyGV.getValue() ;
                ArrayList<Lecturers> list = dbController.searchLecturerFromDatabase(hoTen,tableName);
                if(list.size() == 0){
                    Information.ThongBaoThongTin("Không có tên giảng viên  mà bạn tìm kiếm");
                }else{
                    lecturersList = FXCollections.observableArrayList(list);
                    hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("nameLecturer"));
                    boMonGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("faculty"));
                    phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("phoneNumber"));
                    emailGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("email"));
                    phongGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("workPlace"));
                    maLopGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,Integer>("maLop"));
                    lecturerTableView.setItems(lecturersList);
                }
            }else {
                Error.ThongBaoLoi("Bạn chưa nhập tên giảng viên và học kỳ mà bạn tìm kiếm hoặc" +
                        " bảng dữ liệu cho học kỳ này không tồn tại");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ các thông tin và đúng định dạng");
        }
    }

    public void deleteLecturer(ActionEvent e) {
        try{
            new XoaGiangVien().Xoa(hocKyGV, lecturerTableView);
            new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                    maLopGVColumn, lecturersList, hocKyGV);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa chọn 1 thông tin giảng viên " +
                    "để xóa (gợi ý là có thể tìm kiếm trước bằng họ tên)");
        }
    }
    
    public void dataLecturerLock(ActionEvent e) {
        try{
            new KhoaGV().Khoa(hocKyGV);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }

    //#giám thị
    public void importFileExcelGT(ActionEvent e) {
        try {
            new TaoThongTinGiamThi().TaoThongTin(hocKyGT, supervisorAnchorPane);
            CapNhatGT.CapNhat(supervisorTableView, hoVaTenGTColumn, boMonGTColumn,
                    phoneNumberGTColumn,emailGTColumn,phongGTColumn,soBuoiColum,supervisorsList,hocKyGT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa chọn file hoặc sai định dạng file");
        }
    }

    public void AddGT(ActionEvent e) {
        try {
            new ThemGiamThi().Them(hocKyGT, hoVaTenGTTextField, boMonGTTextField, phoneNumberGTTextField,
                    emailGTTextField,phongGTTextField, soBuoi);
            CapNhatGT.CapNhat(supervisorTableView, hoVaTenGTColumn, boMonGTColumn,
                    phoneNumberGTColumn,emailGTColumn,phongGTColumn,soBuoiColum,supervisorsList,hocKyGT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa đầy đủ các thông tin hoặc lỗi định dạng");
        }
    }

    public void resetSupervisor(ActionEvent e) {
        try{
            if(hocKyGT.getValue().length() != 0 && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
                CapNhatGT.CapNhat(supervisorTableView, hoVaTenGTColumn, boMonGTColumn,
                        phoneNumberGTColumn,emailGTColumn,phongGTColumn,soBuoiColum,supervisorsList,hocKyGT);
            }else{
                Error.ThongBaoLoi("Bạn chưa chọn học kỳ hoặc bảng dữ liệu không tồn tại");
            }
            hoVaTenGTTextField.setText("");
            boMonGTTextField.setText("");
            phoneNumberGTTextField.setText("");
            emailGTTextField.setText("");
            phongGTTextField.setText("");
            SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
            soBuoi.setValueFactory(spinnerValueFactory);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa chọn học kỳ");
        }
    }

    public void updateSupervisor(ActionEvent e) {
        try{
            SuaThongTinGiamThi.Sua(hocKyGT, hoVaTenGTTextField, boMonGTTextField, phoneNumberGTTextField,
                    emailGTTextField,phongGTTextField, soBuoi, supervisorTableView);
            CapNhatGT.CapNhat(supervisorTableView, hoVaTenGTColumn, boMonGTColumn,
                    phoneNumberGTColumn,emailGTColumn,phongGTColumn,soBuoiColum,supervisorsList,hocKyGT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa học kỳ hoặc lỗi kết nối dữ liệu hoặc chưa chọn 1 thông " +
                    "tin trong bảng để cập nhật");
        }
    }

    public void searchSupervisor(ActionEvent e) {
        try {
            if(hoVaTenGTTextField.getText().length() != 0 && hocKyGT.getValue().length() != 0 &&dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
                String hoTen = hoVaTenGTTextField.getText();
                String tableName = "GiamThi" + hocKyGT.getValue();
                Supervisor list = dbController.searchSupervisorFromDatabase(hoTen,tableName);
                if(list== null){
                    Information.ThongBaoThongTin("Không có tên giám thị mà bạn tìm kiếm");
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
                Error.ThongBaoLoi("Bạn chưa nhập tên giám thị và bảng lưu thông tin giám thị mà bạn " +
                        "tìm kiếm hoặc bảng bạn nhập không tồn tại");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc lỗi kết nối");
        }
    }

    public void deleteSupervisor(ActionEvent e) {
        try {
            XoaGiamThi.Xoa(hocKyGT, supervisorTableView);
        } catch (Exception ex) {
            Warning.CanhBao("Bạn chưa nhập học kỳ hoặc chưa chọn thông tin ");
        }
    }

    public void dataSupervisorLock(ActionEvent e) {
        try{
            KhoaGT.Khoa(hocKyGT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }

    //Phân công lịch thi
    public void phanCong(ActionEvent e) {
        try {
            PhanCong.phanCong(hocKyPC);
            CapNhatThongTinTrongThi.CapNhat(hocKyPC, tableViewTrongThi, malopTTColumn, giamThi1TTColumn, giamThi2TTColumn,
                    ngayThiTTColumn, kipThiTTColumn,trongThiList);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập tên học kỳ hoặc lỗi kết nối");
        }
    }

    public void UpdatePhanCong(ActionEvent e) {
        try{
            InfoTrongThi infoTrongThi = tableViewTrongThi.getSelectionModel().getSelectedItem();
            if(infoTrongThi != null) {
                Stage stage = new Stage();
                stage.setX(440);
                stage.setY(170);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/UpdatePCSample.fxml"));
                Parent parent=loader.load();
                Scene scene=new Scene(parent);
                stage.setScene(scene);
                UpdatePCController controller = loader.getController();
                controller.setTrongThi(infoTrongThi, hocKyPC.getValue());
                stage.setTitle("Cập nhật thông tin phân công trông thi");
                stage.show();
            } else {
                Error.ThongBaoLoi("Bạn chưa chọn một thông tin phân công để sửa");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Không load được trang update");
        }
    }

    public void ResetPC(ActionEvent e) {
        try{
            if(hocKyPC.getValue().length() !=0){
                CapNhatThongTinTrongThi.CapNhat(hocKyPC, tableViewTrongThi, malopTTColumn, giamThi1TTColumn, giamThi2TTColumn,
                        ngayThiTTColumn, kipThiTTColumn,trongThiList);
            }else{
                Error.ThongBaoLoi("Bạn chưa nhập tên học kỳ bạn muốn phân công");
            }
            maLopTT.setText("");
        } catch (Exception ex) {
//            Error.ThongBaoLoi("Chưa nhập học kỳ hoặc không tồn tại thông tin của học kỳ này");
            ex.printStackTrace();
        }
    }

    public void SearchPC(ActionEvent e) {
        try {
            TimKiemPhanCong.TimKiem(maLopTT, hocKyPC, tableViewTrongThi, malopTTColumn,giamThi1TTColumn,
                    giamThi2TTColumn, ngayThiTTColumn,kipThiTTColumn,trongThiList);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc mã lớp hoặc nhập chưa đúng định dạng " +
                    "hoặc dữ liệu học kỳ này không tồn tại");
        }
    }

    public void detailPC(ActionEvent e) {
        try{
            ChiTietPhanCong.ChiTietLop(hocKyPC,detailPC,maLopTT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa chọn học kỳ hoặc chưa nhập mã lớp hoặc ");
        }
    }

    //chức năng quản lý kinh phí
    public void AddDG(ActionEvent e) {
        try{
            controller.quanlykinhphi.DonGia.ThemDonGia(hocKyKP, giaKP, tenDonGiaKP);
            controller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                    giaTableColumn,donGiaList,hocKyKP);
        } catch (Exception ex)  {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ thông tin hoặc không đúng định " +
                    "dạng hoặc không có thông tin về học kỳ");
        }
    }

    public void updateDG(ActionEvent e) {
        try{
            controller.quanlykinhphi.DonGia.Sua(hocKyKP,giaKP,tenDonGiaKP,donGiaTableView);
            controller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                    giaTableColumn,donGiaList,hocKyKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp đầy đủ thông tin hoặc " +
                    "chưa đúng định dạng hoặc chưa có thông tin về học kỳ này hoặc chưa chọn 1 đơn giá để cập nhật");
        }
    }

    public void tinhToanChiPhi(ActionEvent e) {
        try {
            controller.quanlykinhphi.KinhPhi.TinhToanKinhPhi(hocKyKP);
            controller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                    phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                    kinhPhiList,hocKyKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ hoặc lỗi kết nối cơ sở dữ liêu");
        }
    }

    public void ThanhToanChiPhi(ActionEvent e) {
        try{
            controller.quanlykinhphi.KinhPhi.ThanhToanKinhPhi(hocKyKP, maLopKP);
            controller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                    phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                    kinhPhiList,hocKyKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa nhập mã lớp để thanh toán hoặc dữ liệu học kỳ này không tồn tại");
        }
    }

    public void chiTietThanhToan(ActionEvent e) {
        try {
            controller.quanlykinhphi.KinhPhi.ChiTietThanhToan(hocKyKP,maLopKP, detailKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ hoặc chưa đủ dữ liệu hoặc dữ liệu không hợp lệ");
        }
    }

    public void resetKP(ActionEvent e) throws SQLException {
        try {
            if(hocKyKP.getValue() != null){
                controller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                        giaTableColumn,donGiaList,hocKyKP);
                controller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                        phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                        kinhPhiList,hocKyKP);
            }else{
                Error.ThongBaoLoi("Bạn chưa nhâp học kỳ ");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ ");
        }

    }
    //Báo cáo thống kê
    public void DetailBC(ActionEvent e) {
        try {
            BaoCaoChiTiet.ChiTiet(gvCheckBox, gtCheckBox, hotenGV_GT, hocKyBCTK, baoCaoTableView, maLopTableColumn,cot2,
                    cot3,baoCaoList);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Chưa nhập đủ và đúng định dạng hoặc không có thông tin");
        }
    }
    @FXML
    BarChart<String, Number> barChart;
    public void ThongKe(ActionEvent e) {
        try {
            ThongKe.thongKe(barChart, hocKyTK);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc lỗi kết nối");
        }
    }
}
