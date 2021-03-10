package controller.qtvcontroller.giamthicontroller;

import controller.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GiamThi;

import java.sql.SQLException;
import java.util.ArrayList;

public class CapNhatGT {
    static DataBaseController dataBaseController = new DataBaseController();
    public static void CapNhat(TableView<GiamThi> supervisorTableView, TableColumn<GiamThi,String> hoVaTenGTColumn ,
                               TableColumn<GiamThi,String> boMonGTColumn, TableColumn<GiamThi,String> phoneNumberGTColumn,
                               TableColumn<GiamThi,String> emailGTColumn, TableColumn<GiamThi,String> phongGTColumn,
                               TableColumn<GiamThi, Integer> soBuoiColum, ObservableList<GiamThi> supervisorsList,
                               ComboBox<String> hocKyGT) {
        ArrayList<GiamThi> giamThi = null;
        try {
            giamThi = dataBaseController.supervisorList("GiamThi" + hocKyGT.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config giám thị
        if(giamThi != null){
            supervisorsList= FXCollections.observableArrayList(giamThi);
            hoVaTenGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("nameLecturer"));
            boMonGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("faculty"));
            phoneNumberGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("phoneNumber"));
            emailGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("email"));
            phongGTColumn.setCellValueFactory(new PropertyValueFactory<GiamThi,String>("workPlace"));
            soBuoiColum.setCellValueFactory(new PropertyValueFactory<GiamThi, Integer>("soBuoiToiDa"));
            supervisorTableView.setItems(supervisorsList);
        } else {
            supervisorTableView.setItems(null);
        }
    }
}
