package controller.phanconglichthi;

import controller.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InfoTrongThi;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CapNhatThongTinTrongThi {
    static DBController dbController = new DBController();
    public static void CapNhat(ComboBox<String> hocKyPC, TableView<InfoTrongThi> tableViewTrongThi,
                               TableColumn<InfoTrongThi,Integer> malopTTColumn, TableColumn<InfoTrongThi,String> giamThi1TTColumn,
                               TableColumn<InfoTrongThi,String> giamThi2TTColumn, TableColumn<InfoTrongThi,Date> ngayThiTTColumn,
                               TableColumn<InfoTrongThi,String> kipThiTTColumn, ObservableList<InfoTrongThi> trongThiList) {
        ArrayList<InfoTrongThi> trongThi=null;
        try {
            trongThi = dbController.trongThiList("PhanCong" + hocKyPC.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(trongThi != null){
            trongThiList= FXCollections.observableArrayList(trongThi);
            malopTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Integer>("maLop"));
            giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi1"));
            giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi2"));
            ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi, Date>("ngayThi"));
            kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("kipThi"));
            tableViewTrongThi.setItems(trongThiList);
        }
    }
}
