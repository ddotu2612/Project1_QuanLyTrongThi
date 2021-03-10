package controller.qtvcontroller.phanconglichthi;

import controller.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ThongTinTrongThi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CapNhatThongTinTrongThi {
    static DataBaseController dataBaseController = new DataBaseController();
    public static void CapNhat(ComboBox<String> hocKyPC, TableView<ThongTinTrongThi> tableViewTrongThi,
                               TableColumn<ThongTinTrongThi,Integer> malopTTColumn, TableColumn<ThongTinTrongThi,String> giamThi1TTColumn,
                               TableColumn<ThongTinTrongThi,String> giamThi2TTColumn, TableColumn<ThongTinTrongThi,Date> ngayThiTTColumn,
                               TableColumn<ThongTinTrongThi,String> kipThiTTColumn, ObservableList<ThongTinTrongThi> trongThiList) {
        ArrayList<ThongTinTrongThi> trongThi=null;
        try {
            trongThi = dataBaseController.trongThiList("PhanCong" + hocKyPC.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(trongThi != null){
            trongThiList= FXCollections.observableArrayList(trongThi);
            malopTTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,Integer>("maLop"));
            giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,String>("giamThi1"));
            giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,String>("giamThi2"));
            ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi, Date>("ngayThi"));
            kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,String>("kipThi"));
            tableViewTrongThi.setItems(trongThiList);
        } else {
            tableViewTrongThi.setItems(null);
        }
    }
}
