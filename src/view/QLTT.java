package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class QLTT extends Application {
    public static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        Parent parent = FXMLLoader.load(getClass().getResource("QLTTSample.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Quản lý trông thi học kỳ");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("./icon/icon.png")));
        stage.show();
    }
}
