package view;

import controller.DataControler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import javax.swing.text.Style;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QLTTController implements Initializable {
    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;
    @FXML
    ComboBox<String> entitle;
    //public static Stage stage;
    ObservableList<String> list= FXCollections.observableArrayList("Quan ly","Giảng viên","Giám thị");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entitle.setItems(list);
        entitle.setValue("Quan ly");
    }
    public void Exit(){
        System.exit(0);
    }

    public void DangNhap(ActionEvent e) throws IOException {
        DataControler dataControler = new DataControler();
        String s = entitle.getValue();
        if(dataControler.isValidTK(userName.getText(),passwordField.getText(), s) && s.equals("Quan ly")){
//            QLTT.stage =(Stage) ((Node)e.getSource()).getScene().getWindow();
//            QLTT.stage = new Stage();
            QLTT.stage.setX(100);
            QLTT.stage.setY(15);
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("QTVSample.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            QLTT.stage.setScene(scene);
            QLTT.stage.setTitle("Quản lý ");

            QLTT.stage.show();
            userName.setText(entitle.getValue());
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Bạn nhập sai tài khoản hoặc mật khẩu");
            alert.showAndWait();
        }
    }
}
