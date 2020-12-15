package view;

import controller.DataControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    }
    public void Exit(){
        System.exit(0);
    }

    public void DangNhap(ActionEvent e) throws IOException {
        DataControler dataControler = new DataControler();
        String s = entitle.getValue();
        if(dataControler.isValidTK(userName.getText(),passwordField.getText(), s) && s.equals("Quản lý")){
            QLTT.stage.setX(100);
            QLTT.stage.setY(15);
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("QTVSample.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
//            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            QLTT.stage.setScene(scene);
            QLTT.stage.setTitle("Quản lý ");

            QLTT.stage.show();
            userName.setText(entitle.getValue());
        } else if(dataControler.isValidTK(userName.getText(),passwordField.getText(), s) && s.equals("Giảng viên/Giám thị")) {
            QLTT.stage.setX(100);
            QLTT.stage.setY(15);
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("GVSample.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            GVController gvController = loader.getController();
            gvController.SetThongTin(userName.getText(), passwordField.getText());
            QLTT.stage.setScene(scene);
            QLTT.stage.setTitle("Giảng viên/Giám thị");
            QLTT.stage.show();
            userName.setText(entitle.getValue());
        } else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setContentText("Bạn nhập sai tài khoản hoặc mật khẩu hoặc quyền truy nhập");
            alert.showAndWait();
        }
    }

    public void DangKy(ActionEvent e) {
        QLTT.stage.setX(420);
        QLTT.stage.setY(120);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DangKySample.fxml"));
        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            QLTT.stage.setScene(scene);
            QLTT.stage.setTitle("Đăng ký");
            QLTT.stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
