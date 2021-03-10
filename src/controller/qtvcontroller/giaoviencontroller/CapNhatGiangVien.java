package controller.qtvcontroller.giaoviencontroller;

import controller.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GiangVien;

import java.sql.SQLException;
import java.util.ArrayList;

public class CapNhatGiangVien {
    DataBaseController dataBaseController = new DataBaseController();
    public void CapNhat(TableView<GiangVien> lecturerTableView, TableColumn<GiangVien,String> hoVaTenGVColumn,
                        TableColumn<GiangVien,String> boMonGVColumn, TableColumn<GiangVien,String> phoneNumberGVColumn,
                        TableColumn<GiangVien,String> emailGVColumn, TableColumn<GiangVien,String> phongGVColumn,
                        TableColumn<GiangVien,Integer> maLopGVColumn, ObservableList<GiangVien> giangVienList,
                        ComboBox<String> hocKyGV) {
        ArrayList<GiangVien> Lecturer = null;
        try {
            Lecturer= dataBaseController.lecturerList("GiangVien" + hocKyGV.getValue());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(Lecturer != null){
            giangVienList = FXCollections.observableArrayList(Lecturer);
            hoVaTenGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("nameLecturer"));
            boMonGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("faculty"));
            phoneNumberGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("phoneNumber"));
            emailGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("email"));
            phongGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,String>("workPlace"));
            maLopGVColumn.setCellValueFactory(new PropertyValueFactory<GiangVien,Integer>("maLop"));
            lecturerTableView.setItems(giangVienList);
        } else {
            lecturerTableView.setItems(null);
        }
    }
}
