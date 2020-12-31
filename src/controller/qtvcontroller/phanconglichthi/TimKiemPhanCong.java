package controller.phanconglichthi;

import controller.DBController;
import controller.DataControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InfoTrongThi;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TimKiemPhanCong {
    static DBController dbController = new DBController();
    static DataControler dataControler = new DataControler();
    public static void TimKiem(TextField maLopTT, ComboBox<String> hocKyPC , TableView<InfoTrongThi> tableViewTrongThi
    , TableColumn<InfoTrongThi,Integer> malopTTColumn, TableColumn<InfoTrongThi,String> giamThi1TTColumn,
       TableColumn<InfoTrongThi,String> giamThi2TTColumn, TableColumn<InfoTrongThi,Date> ngayThiTTColumn,
       TableColumn<InfoTrongThi,String> kipThiTTColumn, ObservableList<InfoTrongThi> trongThiList) throws SQLException {
        if(dataControler.isCheckDataLock("PhanCong" + hocKyPC.getValue()) ) {
            int maLop= Integer.parseInt(maLopTT.getText());
            String tableName = "PhanCong"+hocKyPC.getValue();
            ArrayList<InfoTrongThi> list = dbController.searchInfoTrongThiFromDatabase(maLop,tableName);
            if(list.size() == 0){
                Information.ThongBaoThongTin("Không có mã lớp mà bạn tìm kiếm");
            }else{
                trongThiList= FXCollections.observableArrayList(list);
                malopTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,Integer>("maLop"));
                giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi1"));
                giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("giamThi2"));
                ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi, Date>("ngayThi"));
                kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<InfoTrongThi,String>("kipThi"));
                tableViewTrongThi.setItems(trongThiList);
            }
        }else {
            Error.ThongBaoLoi("Bạn chưa nhập mã lớp và học kỳ hoặc học kỳ bạn nhập không tồn tại");
        }
    }
}
