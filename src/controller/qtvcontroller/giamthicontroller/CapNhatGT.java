package controller.giamthicontroller;

import controller.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supervisor;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;

public class CapNhatGT {
    static DBController  dbController = new DBController();
    public static void CapNhat(TableView<Supervisor> supervisorTableView,TableColumn<Supervisor,String> hoVaTenGTColumn ,
                               TableColumn<Supervisor,String> boMonGTColumn, TableColumn<Supervisor,String> phoneNumberGTColumn,
                               TableColumn<Supervisor,String> emailGTColumn, TableColumn<Supervisor,String> phongGTColumn,
                               TableColumn<Supervisor, Integer> soBuoiColum, ObservableList<Supervisor> supervisorsList,
                               ComboBox<String> hocKyGT) {
        ArrayList<Supervisor> supervisor = null;
        try {
            supervisor = dbController.supervisorList("GiamThi" + hocKyGT.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config giám thị
        if(supervisor != null){
            supervisorsList= FXCollections.observableArrayList(supervisor);
            hoVaTenGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("nameLecturer"));
            boMonGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("faculty"));
            phoneNumberGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("phoneNumber"));
            emailGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("email"));
            phongGTColumn.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("workPlace"));
            soBuoiColum.setCellValueFactory(new PropertyValueFactory<Supervisor, Integer>("soBuoiToiDa"));
            supervisorTableView.setItems(supervisorsList);
        }
    }
}
