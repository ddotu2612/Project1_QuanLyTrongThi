package controller.qtvcontroller.phanconglichthi;

import controller.DataBaseController;
import controller.DataControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ThongTinTrongThi;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TimKiemPhanCong {
    static DataBaseController dataBaseController = new DataBaseController();
    static DataControler dataControler = new DataControler();
    public static void TimKiem(TextField maLopTT, ComboBox<String> hocKyPC , TableView<ThongTinTrongThi> tableViewTrongThi
    , TableColumn<ThongTinTrongThi,Integer> malopTTColumn, TableColumn<ThongTinTrongThi,String> giamThi1TTColumn,
                               TableColumn<ThongTinTrongThi,String> giamThi2TTColumn, TableColumn<ThongTinTrongThi,Date> ngayThiTTColumn,
                               TableColumn<ThongTinTrongThi,String> kipThiTTColumn, ObservableList<ThongTinTrongThi> trongThiList) throws SQLException {
        if(dataControler.isCheckDataLock("PhanCong" + hocKyPC.getValue()) ) {
            int maLop= Integer.parseInt(maLopTT.getText());
            String tableName = "PhanCong"+hocKyPC.getValue();
            ArrayList<ThongTinTrongThi> list = dataBaseController.searchInfoTrongThiFromDatabase(maLop,tableName);
            if(list.size() == 0){
                Information.ThongBaoThongTin("Không có mã lớp mà bạn tìm kiếm");
            }else{
                trongThiList= FXCollections.observableArrayList(list);
                malopTTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,Integer>("maLop"));
                giamThi1TTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,String>("giamThi1"));
                giamThi2TTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,String>("giamThi2"));
                ngayThiTTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi, Date>("ngayThi"));
                kipThiTTColumn.setCellValueFactory(new PropertyValueFactory<ThongTinTrongThi,String>("kipThi"));
                tableViewTrongThi.setItems(trongThiList);
            }
        }else {
            Error.ThongBaoLoi("Bạn chưa nhập mã lớp và học kỳ hoặc học kỳ bạn nhập không tồn tại");
        }
    }
}
