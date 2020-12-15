package view;

import controller.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class DKController implements Initializable {
    @FXML
    TextField tenDN_DK;
    @FXML TextField mk_DK;
    @FXML
    ComboBox<String> quyen_DK;
    ObservableList<String> list= FXCollections.observableArrayList("Quản lý","Giảng viên","Giám thị");
    @FXML
    CheckBox checkDieuKhoan;
    Connection conn = DBConnection.getInstance().getConnection();

    public void dangKy(ActionEvent e) {
        if(tenDN_DK.getText().length() != 0 && mk_DK.getText().length() != 0 && quyen_DK.getValue().length() != 0) {
            try {
                if(checkDieuKhoan.isSelected()) {
                    String sql = "insert into TaiKhoan values (?, ?, ?)";
                    var prepare = conn.prepareStatement(sql);
                    prepare.setString(1, tenDN_DK.getText());
                    prepare.setString(2, mk_DK.getText());
                    prepare.setString(3, quyen_DK.getValue());
                    var res = prepare.executeUpdate();

                    if(res > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thành công");
                        alert.setHeaderText(null);
                        alert.setContentText("Bạn đã đăng ký thành công");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Hãy đồng ý với các điều khoản trước khi đăng ký");
                    alert.showAndWait();
                }
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Chưa đăng ký thành công");
                alert.showAndWait();
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Chưa nhập đủ các thông tin");
                alert.showAndWait();
        }
    }

    public void quayLai(ActionEvent e) {
        QLTT.stage.setX(330);
        QLTT.stage.setY(70);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("QLTTSample.fxml"));
        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            QLTT.stage.setScene(scene);
            QLTT.stage.setTitle("Quản lý trông thi học kỳ");
            QLTT.stage.getIcons().add(new Image(getClass().getResourceAsStream("./icon/icon.png")));
            QLTT.stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quyen_DK.setItems(list);
    }
}
