package controller.giaoviencontroller;

import controller.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Lecturers;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;

public class CapNhatGiangVien {
    DBController dbController = new DBController();
    public void CapNhat( TableView<Lecturers> lecturerTableView, TableColumn<Lecturers,String> hoVaTenGVColumn,
                         TableColumn<Lecturers,String> boMonGVColumn, TableColumn<Lecturers,String> phoneNumberGVColumn,
                         TableColumn<Lecturers,String> emailGVColumn, TableColumn<Lecturers,String> phongGVColumn,
                         TableColumn<Lecturers,Integer> maLopGVColumn, ObservableList<Lecturers> lecturersList,
                         ComboBox<String> hocKyGV) {
        ArrayList<Lecturers> Lecturer = null;
        try {
            Lecturer=dbController.lecturerList("GiangVien" + hocKyGV.getValue());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(Lecturer != null){
            lecturersList= FXCollections.observableArrayList(Lecturer);
            hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("nameLecturer"));
            boMonGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("faculty"));
            phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("phoneNumber"));
            emailGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("email"));
            phongGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,String>("workPlace"));
            maLopGVColumn.setCellValueFactory(new PropertyValueFactory<Lecturers,Integer>("maLop"));
            lecturerTableView.setItems(lecturersList);
        }
    }
}
