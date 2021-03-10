package controller.qtvcontroller;

import controller.DataBaseConnection;
import controller.DataBaseController;
import controller.DataControler;
import controller.UpdatePCController;
import controller.qtvcontroller.baocaothongke.BaoCaoChiTiet;
import controller.qtvcontroller.baocaothongke.ThongKe;
import controller.qtvcontroller.giamthicontroller.*;
import controller.qtvcontroller.giaoviencontroller.*;
import controller.qtvcontroller.lichthicontroller.*;
import controller.qtvcontroller.phanconglichthi.CapNhatThongTinTrongThi;
import controller.qtvcontroller.phanconglichthi.ChiTietPhanCong;
import controller.qtvcontroller.phanconglichthi.PhanCong;
import controller.qtvcontroller.phanconglichthi.TimKiemPhanCong;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class QTVController implements Initializable {
    DataBaseController dataBaseController = new DataBaseController();
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
    TableView<LichThi> testScheduleTableView;
    @FXML
    TableColumn<LichThi,Integer> maLopColumn;
    @FXML
    TableColumn<LichThi,String> maHPColumn;
    @FXML
    TableColumn<LichThi,String> tenHPColumn;
    @FXML
    TableColumn<LichThi,String> ghiChuColumn;
    @FXML
    TableColumn<LichThi,String> nhomColumn;
    @FXML
    TableColumn<LichThi,String> dotMoColumn;
    @FXML
    TableColumn<LichThi,String> tuanColumn;
    @FXML
    TableColumn<LichThi,String> thuColumn;
    @FXML
    TableColumn<LichThi, Date> ngayThiColumn;
    @FXML
    TableColumn<LichThi,String> kipColumn;
    @FXML
    TableColumn<LichThi,Integer> sldkColumn;
    @FXML
    TableColumn<LichThi,String> phongColumn;
    @FXML
    AnchorPane testScheduleAnchorPane;
    ObservableList<LichThi> lichThiList;
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
    TableView<GiangVien> lecturerTableView;
    @FXML
    TableColumn<GiangVien,String> hoVaTenGVColumn;
    @FXML
    TableColumn<GiangVien,String> boMonGVColumn;
    @FXML
    TableColumn<GiangVien,String> phoneNumberGVColumn;
    @FXML
    TableColumn<GiangVien,String> emailGVColumn;
    @FXML
    TableColumn<GiangVien,String> phongGVColumn;
    @FXML
    TableColumn<GiangVien,Integer> maLopGVColumn;
    @FXML
    AnchorPane lecturerAnchorPane;
    ObservableList<GiangVien> giangVienList;

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
    TableView<GiamThi> supervisorTableView;
    @FXML
    TableColumn<GiamThi,String> hoVaTenGTColumn;
    @FXML
    TableColumn<GiamThi,String> boMonGTColumn;
    @FXML
    TableColumn<GiamThi,String> phoneNumberGTColumn;
    @FXML
    TableColumn<GiamThi,String> emailGTColumn;
    @FXML
    TableColumn<GiamThi,String> phongGTColumn;
    @FXML
    TableColumn<GiamThi, Integer> soBuoiColum;
    @FXML
    AnchorPane supervisorAnchorPane;
    ObservableList<GiamThi> supervisorsList;

    //phan công trông thi
    @FXML TextArea detailPC;
    @FXML
    TextField maLopTT;
    @FXML
    ComboBox<String> hocKyPC;
    ObservableList<String> listHKPC = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    @FXML
    TableView<ThongTinTrongThi> tableViewTrongThi;
    @FXML
    TableColumn<ThongTinTrongThi,Integer> malopTTColumn;
    @FXML
    TableColumn<ThongTinTrongThi,String> giamThi1TTColumn;
    @FXML
    TableColumn<ThongTinTrongThi,String> giamThi2TTColumn;
    @FXML
    TableColumn<ThongTinTrongThi,Date> ngayThiTTColumn;
    @FXML
    TableColumn<ThongTinTrongThi,String> kipThiTTColumn;
    ObservableList<ThongTinTrongThi> trongThiList;

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
                new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                        tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, lichThiList, hocKyLT);
            }
        });
        testScheduleTableView.setOnMousePressed(e->{
            LichThi lichThi = testScheduleTableView.getSelectionModel().getSelectedItem();
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
                hoVaTenGVTextField.setText("");
                boMonGVTextField.setText("");
                phoneNumberGVTextField.setText("");
                emailGVTextField.setText("");
                phongGVTextField.setText("");
                maLopGVTextField.setText("");
                new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                        maLopGVColumn, giangVienList, hocKyGV);
            }
        });
        lecturerTableView.setOnMousePressed(e->{
            GiangVien gv = lecturerTableView.getSelectionModel().getSelectedItem();
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
            hoVaTenGTTextField.setText("");
            boMonGTTextField.setText("");
            phoneNumberGTTextField.setText("");
            emailGTTextField.setText("");
            phongGTTextField.setText("");
            if(!hocKyGT.getSelectionModel().isEmpty()) {
                CapNhatGT.CapNhat(supervisorTableView, hoVaTenGTColumn, boMonGTColumn,
                        phoneNumberGTColumn,emailGTColumn,phongGTColumn,soBuoiColum,supervisorsList,hocKyGT);
            }
        });
        supervisorTableView.setOnMousePressed(e->{
            GiamThi gt = supervisorTableView.getSelectionModel().getSelectedItem();
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
            ThongTinTrongThi tt = tableViewTrongThi.getSelectionModel().getSelectedItem();
            try {
                maLopTT.setText(String.valueOf(tt.getMaLop()));
//                ChiTietPhanCong.ChiTietLop(hocKyPC,detailPC,maLopTT);
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
                controller.qtvcontroller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                        giaTableColumn,donGiaList,hocKyKP);
                controller.qtvcontroller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
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
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, lichThiList, hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Chưa nhập học kỳ hoặc chưa chọn file");
        }
    }

    public void Add(ActionEvent e) {
        try {
            new ThemLichThi().ThemLich(hocKyLT, maLopTextField,maHPTextField,tenHPTextField,ghiChuTextField,nhomTextField,
                    dotMoTextField,tuanTextField,thuTextField,kipTextField,sldkTextField,phongTextField,ngayThiLT);
            new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, lichThiList, hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ các thông tin hoặc thông tin không đúng định dạng");
        }
    }

    public void resetTestSchedule(ActionEvent e) {
        try {
            if(hocKyLT.getValue().length() != 0 && dataBaseController.checkExistTable("LichThi" + hocKyLT.getValue())){
                String tableName = "LichThi" + hocKyLT.getValue();
                new CapNhatLichThi().reset(testScheduleTableView,maLopColumn,maHPColumn,tenHPColumn,ghiChuColumn,nhomColumn,dotMoColumn,
                        tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, lichThiList, hocKyLT);
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
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, lichThiList, hocKyLT);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa đầy đủ thông tin ");
        }
    }

    public void searchTestSchedule(ActionEvent e){
        try{
            if(maLopTextField.getText().length() != 0 && hocKyLT.getValue().length() != 0 && dataBaseController.checkExistTable("LichThi" + hocKyLT.getValue())){
                int maLop= Integer.parseInt(maLopTextField.getText());
                String tableName = "LichThi" + hocKyLT.getValue();
                ArrayList<LichThi> list= dataBaseController.searchTestScheduleFromDatabase(maLop,tableName);
                if(list.size() ==0){
                    Information.ThongBaoThongTin("Không có mã lớp mà bạn tìm kiếm");
                }else{
                    lichThiList =FXCollections.observableArrayList(list);
                    maLopColumn.setCellValueFactory(new PropertyValueFactory<LichThi,Integer>("maLop"));
                    maHPColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("maHP"));
                    tenHPColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("tenHP"));
                    ghiChuColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("ghiChu"));
                    nhomColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("nhom"));
                    dotMoColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("dotMo"));
                    tuanColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("tuan"));
                    thuColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("thu"));
                    ngayThiColumn.setCellValueFactory(new PropertyValueFactory<LichThi,Date>("ngayThi"));
                    kipColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("kip"));
                    sldkColumn.setCellValueFactory(new PropertyValueFactory<LichThi,Integer>("SLDK"));
                    phongColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("phong"));
                    testScheduleTableView.setItems(lichThiList);
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
                    tuanColumn,thuColumn,ngayThiColumn,kipColumn,sldkColumn,phongColumn, lichThiList, hocKyLT);
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
                     maLopGVColumn, giangVienList, hocKyGV);
         } catch (Exception ex) {
             Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa chọn file");
         }
    }

    public void AddGV(ActionEvent e) {
        try {
            new ThemGiangVien().Them(hoVaTenGVTextField, boMonGVTextField,phoneNumberGVTextField,
                    emailGVTextField,phongGVTextField,maLopGVTextField,hocKyGV);
            new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                    maLopGVColumn, giangVienList, hocKyGV);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Nhập chưa đủ thông tin và sai định dạng");
        }
    }

    public void resetLecturer(ActionEvent e) {
        try{
            if(hocKyGV.getValue().length() != 0 && dataBaseController.checkExistTable("GiangVien" + hocKyGV.getValue())){
                String tableName = "GiangVien" + hocKyGV.getValue();
                new CapNhatGiangVien().CapNhat(lecturerTableView, hoVaTenGVColumn, boMonGVColumn,phoneNumberGVColumn,emailGVColumn,phongGVColumn,
                        maLopGVColumn, giangVienList, hocKyGV);
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
                    maLopGVColumn, giangVienList, hocKyGV);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ thông tin và chưa đúng định dạng");
        }
    }

    public void searchLecturer(ActionEvent e) {
        try{
            if(hoVaTenGVTextField.getText().length() != 0 && hocKyGV.getValue().length() != 0 && dataBaseController.checkExistTable("GiangVien" + hocKyGV.getValue())){
                String hoTen = hoVaTenGVTextField.getText();
                String tableName = "GiangVien" + hocKyGV.getValue() ;
                ArrayList<GiangVien> list = dataBaseController.searchLecturerFromDatabase(hoTen,tableName);
                if(list.size() == 0){
                    Information.ThongBaoThongTin("Không có tên giảng viên  mà bạn tìm kiếm");
                }else{
                    giangVienList = FXCollections.observableArrayList(list);
                    hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("nameLecturer"));
                    boMonGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("faculty"));
                    phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("phoneNumber"));
                    emailGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("email"));
                    phongGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("workPlace"));
                    maLopGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,Integer>("maLop"));
                    lecturerTableView.setItems(giangVienList);
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
                    maLopGVColumn, giangVienList, hocKyGV);
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
            if(hocKyGT.getValue().length() != 0 && dataBaseController.checkExistTable("GiamThi" + hocKyGT.getValue())){
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
            if(hoVaTenGTTextField.getText().length() != 0 && hocKyGT.getValue().length() != 0 && dataBaseController.checkExistTable("GiamThi" + hocKyGT.getValue())){
                String hoTen = hoVaTenGTTextField.getText();
                String tableName = "GiamThi" + hocKyGT.getValue();
                GiamThi list = dataBaseController.searchSupervisorFromDatabase(hoTen,tableName);
                if(list== null){
                    Information.ThongBaoThongTin("Không có tên giám thị mà bạn tìm kiếm");
                }else{
                    supervisorsList=FXCollections.observableArrayList(list);
                    hoVaTenGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("nameLecturer"));
                    boMonGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("faculty"));
                    phoneNumberGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("phoneNumber"));
                    emailGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("email"));
                    phongGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("workPlace"));
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
            Error.ThongBaoLoi("Bạn chưa nhập tên học kỳ hoặc lỗi kết nối hoặc không đủ giám thị để phân công");
        }
    }

    public void UpdatePhanCong(ActionEvent e) {
        try{
            ThongTinTrongThi thongTinTrongThi = tableViewTrongThi.getSelectionModel().getSelectedItem();
            if(thongTinTrongThi != null) {
                Stage stage = new Stage();
                stage.setX(440);
                stage.setY(170);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/UpdatePCSample.fxml"));
                Parent parent=loader.load();
                Scene scene=new Scene(parent);
                stage.setScene(scene);
                UpdatePCController controller = loader.getController();
                controller.setTrongThi(thongTinTrongThi, hocKyPC.getValue());
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

    public void DataLockPC(ActionEvent e) {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        DataControler dataControler = new DataControler();
        try{
            if(hocKyPC.getValue().length() != 0 && dataControler.isCheckDataLock(
                    "PhanCong" + hocKyPC.getValue())){
                String tableName = "PhanCong" + hocKyPC.getValue();
                String sql="insert into dbo.LockData values(?,?) ;";
                var prepare= conn.prepareStatement(sql) ;
                prepare.setString(1,tableName);
                prepare.setInt(2,1);
                var result=prepare.executeUpdate();
                if(result>0){
                    Information.ThongBaoThongTin("Bạn đã khóa thông tin phân công giám thị thành công ");
                }else{
                    Error.ThongBaoLoi("Khóa không thành công");
                }
            }else{
                Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc bảng đã bị khóa hoặc bảng không tồn tại");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa chọn học kỳ ");
        }
    }

    //chức năng quản lý kinh phí
    public void AddDG(ActionEvent e) {
        try{
            controller.qtvcontroller.quanlykinhphi.DonGia.ThemDonGia(hocKyKP, giaKP, tenDonGiaKP);
            controller.qtvcontroller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                    giaTableColumn,donGiaList,hocKyKP);
        } catch (Exception ex)  {
            Error.ThongBaoLoi("Bạn chưa nhập đầy đủ thông tin hoặc không đúng định " +
                    "dạng hoặc không có thông tin về học kỳ");
        }
    }

    public void updateDG(ActionEvent e) {
        try{
            controller.qtvcontroller.quanlykinhphi.DonGia.Sua(hocKyKP,giaKP,tenDonGiaKP,donGiaTableView);
            controller.qtvcontroller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                    giaTableColumn,donGiaList,hocKyKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp đầy đủ thông tin hoặc " +
                    "chưa đúng định dạng hoặc chưa có thông tin về học kỳ này hoặc chưa chọn 1 đơn giá để cập nhật");
        }
    }

    public void tinhToanChiPhi(ActionEvent e) {
        try {
            controller.qtvcontroller.quanlykinhphi.KinhPhi.TinhToanKinhPhi(hocKyKP);
            controller.qtvcontroller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                    phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                    kinhPhiList,hocKyKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ hoặc lỗi kết nối cơ sở dữ liêu");
        }
    }

    public void ThanhToanChiPhi(ActionEvent e) {
        try{
            controller.qtvcontroller.quanlykinhphi.KinhPhi.ThanhToanKinhPhi(hocKyKP, maLopKP);
            controller.qtvcontroller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                    phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                    kinhPhiList,hocKyKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc chưa nhập mã lớp để thanh toán hoặc dữ liệu học kỳ này không tồn tại");
        }
    }

    public void chiTietThanhToan(ActionEvent e) {
        try {
            controller.qtvcontroller.quanlykinhphi.KinhPhi.ChiTietThanhToan(hocKyKP,maLopKP, detailKP);
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ hoặc chưa đủ dữ liệu hoặc dữ liệu không hợp lệ");
        }
    }

    public void resetKP(ActionEvent e){
        try {
            if(hocKyKP.getValue() != null){
                controller.qtvcontroller.quanlykinhphi.DonGia.CapNhatDonGia(donGiaTableView, tenDonGiaTableColumn,
                        giaTableColumn,donGiaList,hocKyKP);
                controller.qtvcontroller.quanlykinhphi.KinhPhi.CapNhatKinhPhi(kinhPhiTableView,maLopKPTableColumn,tenGVTableColumn,inAnTableColumn,
                        phoToTableColumn,toChucThiTableColumn,kinhPhiGTTableColumn,checkThanhToanTableColumn,nhomKPTableColumn,
                        kinhPhiList,hocKyKP);
                maLopKP.setText("");
                detailKP.clear();
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
