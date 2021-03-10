package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GiangVien;
import view.QLTT;
import view.alert.Error;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QLTTController implements Initializable {
    @FXML
    AnchorPane anchorpane;
    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;
    @FXML
    ComboBox<String> entitle;
    ObservableList<String> list= FXCollections.observableArrayList("Quản lý", "Giảng viên/Giám thị");

    @FXML
    ImageView anh1;
    @FXML
    ImageView anh2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entitle.setItems(list);
        entitle.setValue("Quản lý");
        anh2.setImage(new Image("view/img/write.png", true));
        anh1.setImage(new Image("view/img/school.png", true));
        userName.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER) DangNhap();
        });
        passwordField.setOnKeyPressed(ex->{
            if(ex.getCode() == KeyCode.ENTER) DangNhap();
        });
    }
    public void DangNhap(ActionEvent e) {
        DangNhap();
    }

    public void DangNhap() {
        try {
            DataControler dataControler = new DataControler();
            String s = entitle.getValue();
            if(dataControler.isValidTK(userName.getText(),passwordField.getText(), s, "TaiKhoan_QTV")
                    && s.equals("Quản lý")){
                QLTT.stage.setX(100);
                QLTT.stage.setY(15);
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/QTVSample.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                QLTT.stage.setScene(scene);
                QLTT.stage.setTitle("Quản lý ");
                QLTT.stage.show();
                userName.setText(entitle.getValue());
            } else if(dataControler.isValidTK(userName.getText(),passwordField.getText(), s, "TaiKhoan_GVGT")
                    && s.equals("Giảng viên/Giám thị")) {
                QLTT.stage.setX(200);
                QLTT.stage.setY(80);
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/GVSample.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                GVController gvController = loader.getController();
                gvController.SetThongTin(userName.getText(), passwordField.getText());
                GiangVien lec = dataControler.ThongTinCaNhanGiangVien(userName.getText(),passwordField.getText());
                gvController.setLecturers(lec);
                QLTT.stage.setScene(scene);
                QLTT.stage.setTitle("Giảng viên/Giám thị");
                QLTT.stage.show();
                userName.setText(entitle.getValue());
            } else{
                Error.ThongBaoLoi("Bạn nhập sai tài khoản hoặc mật khẩu hoặc quyền truy nhập hoặc chưa có tài khoản");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa đủ các thông tin cần thiết");
            ex.printStackTrace();
        }
    }

    public void DangKy(ActionEvent e) {
        Stage stage = new Stage();
        stage.setX(420);
        stage.setY(120);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DangKySample.fxml"));
        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Đăng ký");
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
