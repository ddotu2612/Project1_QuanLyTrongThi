package view;

import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
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
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GVController implements Initializable {
    private String pass, user;
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
    private TextField matKhauCN;

    @FXML Button suaThongTin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hocKy.setItems(listHK);
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        soBuoiGT.setValueFactory(spinnerValueFactory);
        tenGiangVien.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                XemThongTin();
                XemGT();
                SetThongTinGV(tenGiangVien.getText());
            }
        });
        btnXemThongTin.setOnMouseClicked(e->{
            XemThongTin();
            XemGT();
        });

        phongCN.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER) {
                SuaThongTinCN();
            }
        });

        matKhauCN.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER) {
                SuaThongTinTK();
            }
        });

        suaThongTin.setOnMouseClicked(e->{
            SuaThongTinCN();
            SuaThongTinTK();
        });
    }

    public void SetThongTinGV(String name) {
        try {
            ArrayList<Lecturers> lecturers = dbController.searchLecturerFromDatabase(tenGiangVien.getText(), "GiangVien" + hocKy.getValue());
            Lecturers lec = lecturers.get(0);
            hoTenCN.setText(lec.getNameLecturer());
            boMonCN.setText(lec.getFaculty());
            sdtCN.setText(lec.getPhoneNumber());
            emailCN.setText(lec.getEmail());
            phongCN.setText(lec.getWorkPlace());
        } catch (Exception ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn học kỳ hoặc nhập thông tin họ tên giảng viên hoặc lỗi định dạng");
            alert.showAndWait();
        }
    }
    public void SetThongTin(String taiKhoan, String matKhau) {
        taiKhoanCN.setText(taiKhoan);
        matKhauCN.setText(matKhau);
        user = taiKhoan;
        pass = matKhau;
    }
    DBController dbController = new DBController();

    public void SuaThongTinTK() {
        try {
            Connection conn = new DBConnection().getConnection();
            String sql = "update TaiKhoan" + hocKy.getValue() + " set userName = ?, password = ?  where userName = ? and password = ?";
            var prepare = conn.prepareStatement(sql);
            prepare.setString(1, taiKhoanCN.getText());
            prepare.setString(2, matKhauCN.getText());
            prepare.setString(3, user);
            prepare.setString(4, pass);
            var res = prepare.executeUpdate();
            if(res > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật tài khoản thành công");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Chưa chọn học kỳ hoặc chưa nhập thông tin giảng viên hoặc không có thông tin giảng viên mà bạn nhập");
            alert.showAndWait();
        }
    }

    public void SuaThongTinCN() {
        try {
             Connection conn = new DBConnection().getConnection();
             String sql = "update GiangVien" + hocKy.getValue() + "set hoTen=? ,boMon=?,phone=?,email=?,phong=? where hoTen = ?";
             var prepare = conn.prepareStatement(sql);
             prepare.setString(1, hoTenCN.getText());
             prepare.setString(2, boMonCN.getText());
             prepare.setString(3, sdtCN.getText());
             prepare.setString(4, emailCN.getText());
             prepare.setString(5, phongCN.getText());
             prepare.setString(6, tenGiangVien.getText());
             var res = prepare.executeUpdate();
             if(res > 0) {
                 Alert alert=new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Thành công");
                 alert.setHeaderText(null);
                 alert.setContentText("Cập nhật thành công");
                 alert.showAndWait();
             }
        } catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Chưa chọn học kỳ hoặc chưa nhập thông tin giảng viên hoặc không có thông tin giảng viên mà bạn nhập");
            alert.showAndWait();
        }
    }

    public void XemGT() {
        try {
            ArrayList<ThongTinLopTrongThi> listTT =  new ArrayList<>();
            ArrayList<Integer> list = new ArrayList<>();
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "select maLop from " + "PhanCong" + hocKy.getValue() + " where giamThi1 = ? or giamThi2 = ?";
            var prepare = conn.prepareStatement(sql);
            prepare.setString(1, tenGiangVien.getText());
            prepare.setString(2, tenGiangVien.getText());
            var res = prepare.executeQuery();
            while(res.next()) {
                list.add(res.getInt("maLop"));
            }
            for(int i = 0; i < list.size(); i++) {
                ArrayList<KinhPhi> listKP = dbController.searchKinhPhiList(list.get(i), "KinhPhi" + hocKy.getValue());
                String xacNhan = (listKP.get(0).getCheckThanhToan() == 1) ? "Đã được thanh toán" : "Chưa được thanh toán";
                ThongTinLopTrongThi lopTrongThi = new ThongTinLopTrongThi(list.get(i), listKP.get(0).getKinhPhiGT(), xacNhan);
                listTT.add(lopTrongThi);
            }
            listLopTable = FXCollections.observableArrayList(listTT);
            maLopTT.setCellValueFactory(new PropertyValueFactory<ThongTinLopTrongThi, Integer>("maLop"));
            tienCong.setCellValueFactory(new PropertyValueFactory<ThongTinLopTrongThi, Long>("tienCong"));
            xacNhanTT.setCellValueFactory(new PropertyValueFactory<ThongTinLopTrongThi, String>("xacNhanThanhToan"));
            lopTTTable.setItems(listLopTable);
        } catch (Exception ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn học kỳ hoặc nhập thông tin họ tên giảng viên hoặc " +
                    "thông tin không đúng định dạng hoặc chưa có thông tin về học kỳ này");
            alert.showAndWait();
        }
    }

    public void XemThongTin() {
        try {
            ArrayList<ThongTinLopGiangDay> list = new ArrayList<>();
            String hocKy1 = String.valueOf(hocKy.getValue());
            String name = tenGiangVien.getText();
            ArrayList<Lecturers> lecturers = dbController.searchLecturerFromDatabase(name, "GiangVien" + hocKy1);

            if(lecturers == null) {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Không có thông tin");
                alert.showAndWait();
            }

            for(int i  = 0; i < lecturers.size(); i++) {
                int maLop = lecturers.get(i).getMaLop();
                ArrayList<KinhPhi> listKP=dbController.searchKinhPhiList(maLop , "KinhPhi" + hocKy1);
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
                            loader.setLocation(getClass().getResource("Detail.fxml"));
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
        } catch (Exception ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn chưa chọn học kỳ hoặc nhập thông tin họ tên giảng viên hoặc " +
                    "thông tin không đúng định dạng hoặc chưa có thông tin về học kỳ này");
            alert.showAndWait();
        }
    }

    DataControler dataControler = new DataControler();
    public void DangKy(ActionEvent e) {
        try {
            String tableName = "GiamThi" + hocKy.getValue();
            if(dataControler.isCheckDataLock(tableName)  && dbController.checkExistTable(tableName)){
                String name = hoTenGT.getText();
                String boMon = boMonGT.getText();
                String phone = phoneGT.getText();
                String email = emailGT.getText();
                String phong = phongGT.getText();
                int soBuoi1 = (int) soBuoiGT.getValue();
                Supervisor supervisor = new Supervisor(name, boMon, phone, email, phong, soBuoi1);
                boolean check=dataControler.isValidSupervisor(supervisor,tableName);//Kiểm tra xem giám thị  đã đc thêm vào hay chưa
                if(check){
                    if(dbController.addSupervisorToDatabase(supervisor,tableName)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công ");
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn đã đăng ký thành công");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn chưa đăng ký thành công");
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
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Chưa nhập đủ dữ liệu hoặc sai định dạng");
            alert.showAndWait();
        }
    }

//    public void XemThongTin(ActionEvent e) {
//
//    }
}
