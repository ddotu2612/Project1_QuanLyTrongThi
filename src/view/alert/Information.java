package view.alert;

import javafx.scene.control.Alert;

public class Information {
    public static void ThongBaoThongTin(String massage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("THÔNG BÁO");
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.show();
    }
}
