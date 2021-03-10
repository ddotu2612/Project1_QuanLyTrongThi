package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import view.Logout;
import view.alert.Error;
import view.alert.Information;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GVController implements Initializable {
    private String pass, user;
    private static GiangVien lec ;
    public void setLecturers(GiangVien l) {
        lec = l;
        tenGiangVien.setText(lec.getNameLecturer());
        SetThongTinGV();
    }

    @FXML
    private TableView<ThongTinLopGiangDay> lopTable;

    @FXML
    private TableColumn<ThongTinLopGiangDay, Integer> maLopColumn;

    @FXML
    private TableColumn<ThongTinLopGiangDay, Long> chiPhiColumn;

    @FXML
    private TableColumn<ThongTinLopGiangDay, Button> chiTietColumn;

    @FXML
    TextField tenGiangVien;

    @FXML
    private ComboBox<Integer> hocKy;
    ObservableList<Integer> listHK = FXCollections.observableArrayList(20191, 20192, 20193, 20201, 20202, 20203);

    @FXML Button btnXemThongTin;

    @FXML
    private TextField hoTenGT;

    @FXML
    private TextField boMonGT;

    @FXML
    private TextField phoneGT;

    @FXML
    private TextField emailGT;

    @FXML
    private TextField phongGT;

    @FXML
    private Spinner soBuoiGT;

    @FXML
    private TableView<ThongTinLopTrongThi> lopTTTable;

    @FXML
    private TableColumn<ThongTinLopTrongThi, Integer> maLopTT;

    @FXML
    private TableColumn<ThongTinLopTrongThi, Long> tienCong;

    @FXML
    private TableColumn<ThongTinLopTrongThi, String> xacNhanTT;
    ObservableList<ThongTinLopTrongThi> listLopTable;

    @FXML
    private TextField hoTenCN;

    @FXML
    private TextField boMonCN;

    @FXML
    private TextField sdtCN;

    @FXML
    private TextField emailCN;

    @FXML
    private TextField phongCN;

    @FXML
    private TextField taiKhoanCN;

    @FXML
    private PasswordField matKhauCN;

    @FXML Button suaThongTin;
    @FXML Label gvDangXuat;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hocKy.setItems(listHK);

        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                100, 1);
        soBuoiGT.setValueFactory(spinnerValueFactory);

        gvDangXuat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new Logout().Excute_Logout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tenGiangVien.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                try {
                    XemThongTin();
                    XemGT();
                } catch (Exception ex) {
                    Error.ThongBaoLoi("Chưa chọn học kỳ hoặc chưa nhập thông tin giảng viên hoặc " +
                            "không có thông tin giảng viên mà bạn nhập");
                }
            }
        });

        btnXemThongTin.setOnMouseClicked(e->{
            try {
                XemThongTin();
                XemGT();
            } catch (Exception ex) {
                Error.ThongBaoLoi("Chưa chọn học kỳ hoặc không" +
                        " có thông tin giảng viên mà bạn nhập");
            }
        });

        phongCN.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER) {
                try {
                    SuaThongTinCN();
                } catch (Exception ex) {
                    Error.ThongBaoLoi("Chưa chọn học kỳ hoặc chưa nhập thông tin giảng viên hoặc không có thông " +
                            "tin giảng viên mà bạn nhập");
                }
            }
        });

        matKhauCN.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER) {
                try {
                    SuaThongTinTK();
                } catch (Exception ex) {
                    Error.ThongBaoLoi("Chưa chọn học kỳ hoặc chưa nhập thông tin giảng viên hoặc không có thông " +
                            "tin giảng viên mà bạn nhập");
                }
            }
        });

        suaThongTin.setOnMouseClicked(e->{
            try {
                SuaThongTinCN();
                SuaThongTinTK();
            } catch (Exception ex) {
                Error.ThongBaoLoi("Chưa chọn học kỳ hoặc chưa nhập thông tin giảng viên hoặc không có thông tin " +
                        "giảng viên mà bạn nhập");
//                ex.printStackTrace();
            }
        });
    }

    public void SetThongTinGV(){
        hoTenCN.setText(lec.getNameLecturer());
        boMonCN.setText(lec.getFaculty());
        sdtCN.setText(lec.getPhoneNumber());
        emailCN.setText(lec.getEmail());
        phongCN.setText(lec.getWorkPlace());
    }

    public void SetThongTin(String taiKhoan, String matKhau) {
        taiKhoanCN.setText(taiKhoan);
        matKhauCN.setText(matKhau);
        user = taiKhoan;
        pass = matKhau;
    }

    DataBaseController dataBaseController = new DataBaseController();
    public void SuaThongTinTK() throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql = "update TaiKhoan_GVGT set tenDN = ?, matKhau = ?  where tenDN = ? " +
                "and matKhau = ?";
        var prepare = conn.prepareStatement(sql);
        prepare.setString(1, taiKhoanCN.getText());
        prepare.setString(2, matKhauCN.getText());
        prepare.setString(3, user);
        prepare.setString(4, pass);
        var res = prepare.executeUpdate();
        if(res > 0) {
            Information.ThongBaoThongTin("Cập nhật tài khoản thành công");
        }
    }

    public void SuaThongTinCN() throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql = "update GiangVien" + hocKy.getValue() + " set hoTen=? , boMon=?, phone=?, email=?, phong=?" +
                " where hoTen = ? and boMon = ? and phone = ? and email = ? and phong = ?";
        var prepare = conn.prepareStatement(sql);
        prepare.setString(1, hoTenCN.getText());
        prepare.setString(2, boMonCN.getText());
        prepare.setString(3, sdtCN.getText());
        prepare.setString(4, emailCN.getText());
        prepare.setString(5, phongCN.getText());
        prepare.setString(6, lec.getNameLecturer());
        prepare.setString(7, lec.getFaculty());
        prepare.setString(8, lec.getPhoneNumber());
        prepare.setString(9, lec.getEmail());
        prepare.setString(10, lec.getWorkPlace());
        prepare.executeUpdate();

        //Cập nhật thông tin cá nhân trong tài khoản
        String sql1 = "update TaiKhoan_GVGT set hoTen = ?, boMon = ?, soDienThoai = ?, email = ?, phongLamViec = ? " +
                " where hoTen = ? and boMon = ? and soDienThoai = ? and email = ? and phongLamViec = ? ";
        var prepare1= conn.prepareStatement(sql1);
        prepare1.setString(1, hoTenCN.getText());
        prepare1.setString(2, boMonCN.getText());
        prepare1.setString(3, sdtCN.getText());
        prepare1.setString(4, emailCN.getText());
        prepare1.setString(5, phongCN.getText());
        prepare1.setString(6, lec.getNameLecturer());
        prepare1.setString(7, lec.getFaculty());
        prepare1.setString(8, lec.getPhoneNumber());
        prepare1.setString(9, lec.getEmail());
        prepare1.setString(10, lec.getWorkPlace());
        prepare1.executeUpdate();
    }

    public void XemGT() throws SQLException {
        //lấy tiền công giám thị
        long tien = 0;
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql1 = "select gia from DonGia" + hocKy.getValue() + " where tenDonGia = ?";
        var prepare1 = conn.prepareStatement(sql1);
        prepare1.setString(1, "Tiền giám thị");
        var result = prepare1.executeQuery();
        if(result.next()) {
            tien = result.getLong("gia");
        }
        ArrayList<ThongTinLopTrongThi> listTT =  new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        String sql = "select maLop from " + "PhanCong" + hocKy.getValue() + " where giamThi1 = ? or giamThi2 = ?";
        var prepare = conn.prepareStatement(sql);
        prepare.setString(1, tenGiangVien.getText());
        prepare.setString(2, tenGiangVien.getText());
        var res = prepare.executeQuery();
        while(res.next()) {
            list.add(res.getInt("maLop"));
        }
        for(int i = 0; i < list.size(); i++) {
            ArrayList<KinhPhi> listKP = dataBaseController.searchKinhPhiList(list.get(i), "KinhPhi" + hocKy.getValue());
            String xacNhan = (listKP.get(0).getCheckThanhToan() == 1) ? "Đã được thanh toán" : "Chưa được thanh toán";
            ThongTinLopTrongThi lopTrongThi = new ThongTinLopTrongThi(list.get(i), list.size() * tien, xacNhan);
            listTT.add(lopTrongThi);
        }
        listLopTable = FXCollections.observableArrayList(listTT);
        maLopTT.setCellValueFactory(new PropertyValueFactory<ThongTinLopTrongThi, Integer>("maLop"));
        tienCong.setCellValueFactory(new PropertyValueFactory<ThongTinLopTrongThi, Long>("tienCong"));
        xacNhanTT.setCellValueFactory(new PropertyValueFactory<ThongTinLopTrongThi, String>("xacNhanThanhToan"));
        lopTTTable.setItems(listLopTable);
    }

    public void XemThongTin() throws SQLException {
        ArrayList<ThongTinLopGiangDay> list = new ArrayList<>();
        String hocKy1 = String.valueOf(hocKy.getValue());
        String name = tenGiangVien.getText();
        ArrayList<GiangVien> lecturers = dataBaseController.searchLecturerFromDatabase(name, "GiangVien" + hocKy1);

        for(int i  = 0; i < lecturers.size(); i++) {
            int maLop = lecturers.get(i).getMaLop();
            ArrayList<KinhPhi> listKP = dataBaseController.searchKinhPhiList(maLop , "KinhPhi" + hocKy1);
            long sum = 0;
            for(int j = 0; j < listKP.size(); j++) {
                sum += listKP.get(j).getInAn() + listKP.get(j).getPhoTo() + listKP.get(j).getToChucThi() + listKP.get(j).getKinhPhiGT();
            }
            Button button = new Button("Detail");
            button.setMaxSize(200, 400);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/Detail.fxml"));
                        Parent root = loader.load();
                        DetailController detailController = loader.getController();
                        detailController.setInfo(maLop, Integer.parseInt(hocKy1));
                        Scene scene = new Scene(root);
                        stage.setTitle("Detail");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            ThongTinLopGiangDay thongTinLopGiangDay = new ThongTinLopGiangDay(lecturers.get(i).getMaLop(), sum, button);
            list.add(thongTinLopGiangDay);
        }
        maLopColumn.setCellValueFactory(new PropertyValueFactory<>("maLop"));
        chiPhiColumn.setCellValueFactory(new PropertyValueFactory<>("chiPhi"));
        chiTietColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        ObservableList<ThongTinLopGiangDay> listTT = FXCollections.observableArrayList(list);
        lopTable.setItems(listTT);
    }

    DataControler dataControler = new DataControler();
    public void DangKy(ActionEvent e) throws SQLException {
        String tableName = "GiamThi" + hocKy.getValue();
        if(dataControler.isCheckDataLock(tableName)  && dataBaseController.checkExistTable(tableName)){
            String name = hoTenGT.getText();
            String boMon = boMonGT.getText();
            String phone = phoneGT.getText();
            String email = emailGT.getText();
            String phong = phongGT.getText();
            int soBuoi1 = (int) soBuoiGT.getValue();
            GiamThi giamThi = new GiamThi(name, boMon, phone, email, phong, soBuoi1);
            boolean check=dataControler.isValidSupervisor(giamThi,tableName);//Kiểm tra xem giám thị  đã đc thêm vào hay chưa
            if(check){
                if(dataBaseController.addSupervisorToDatabase(giamThi,tableName)) {
                    Information.ThongBaoThongTin("Bạn đã đăng ký thành công");
                }else {
                    Error.ThongBaoLoi("Bạn chưa đăng ký thành công");
                }
            }else{
                Error.ThongBaoLoi("Thông tin giám thị đã tồn tại trong database");
            }
        }else{
            Error.ThongBaoLoi("Thông tin giám thị đã bị khóa hoặc chưa tổ chức phân công cho học kỳ này");
        }
    }
}
