package view;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    ObservableList<String> list= FXCollections.observableArrayList("Quan ly","Giảng viên","Giám thị");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entitle.setItems(list);
    }
    public void DangNhap(ActionEvent e) throws IOException {
        DataControler dataControler=new DataControler();
        String s= entitle.getValue();
        if(dataControler.isValidTK(userName.getText(),passwordField.getText(), s) && s=="Quan ly"){
            Stage stage =(Stage) ((Node)e.getSource()).getScene().getWindow();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("QTVSample.fxml"));
            Parent parent=loader.load();
            Scene scene=new Scene(parent);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Quản lý ");
            stage.show();
            userName.setText(entitle.getValue());
        }else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Bạn nhập sai tài khoản hoặc mật khẩu");
            alert.showAndWait();
        }
    }
}
