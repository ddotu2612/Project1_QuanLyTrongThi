package view.alert;

import javafx.scene.control.Alert;

public class Error {
    public static void ThongBaoLoi(String massage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("LỖI");
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.show();
    }
}
