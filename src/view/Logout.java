package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.QLTT;
import view.QLTTController;

import java.io.IOException;

public class Logout {
    // XỬ LÝ THOÁT ĐĂNG NHẬP CHO QUẢN TRỊ VIÊN
    public void Excute_Logout() throws IOException {
        QLTT.stage.close();
        QLTT.stage.setX(400);
        QLTT.stage.setY(65);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(".\\QLTTSample.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource(".\\application.css").toExternalForm());
        QLTT.stage.setScene(scene);
//        QLTTController.stage.setTitle("Quản lý trông thi học kỳ");
        QLTT.stage.show();
    }
}
