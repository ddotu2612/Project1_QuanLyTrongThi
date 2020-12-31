package view.alert;

import javafx.scene.control.Alert;

public class Warning {
    public static void CanhBao(String massage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("CẢNH BÁO");
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.show();
    }
}
