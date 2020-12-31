package controller.quanlykinhphi;

import controller.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InfoTrongThi;
import org.apache.poi.hssf.record.chart.LineFormatRecord;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonGia {
    static DBController dbController = new DBController();
    public static void ThemDonGia(ComboBox<String> hocKyKP, TextField giaKP, ComboBox<String> tenDonGiaKP) throws SQLException {
        if(hocKyKP.getValue() != null ){
            String tableName = "DonGia" + hocKyKP.getValue();
            long gia = Long.parseLong(giaKP.getText());
            String tenDonGia = tenDonGiaKP.getValue();
            if(dbController.addDonGia(tenDonGia,gia,tableName)){
                Information.ThongBaoThongTin("Bạn đã thêm đơn giá thành công");
            }else{
                Error.ThongBaoLoi("Bạn chưa thêm đơn giá thành công ");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ ");
        }
    }
    public static void CapNhatDonGia(TableView<model.DonGia> donGiaTableView, TableColumn<model.DonGia,String> tenDonGiaTableColumn,
                                     TableColumn<model.DonGia,Long> giaTableColumn,ObservableList<model.DonGia> donGiaList ,
                                     ComboBox<String> hocKyKP) {
        ArrayList<model.DonGia> donGia=null;
        try {
            donGia=dbController.donGiaList("DonGia" + hocKyKP.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(donGia != null){
            donGiaList = FXCollections.observableArrayList(donGia);
            tenDonGiaTableColumn.setCellValueFactory(new PropertyValueFactory<model.DonGia,String>("tenDonGia"));
            giaTableColumn.setCellValueFactory(new PropertyValueFactory<model.DonGia,Long>("gia"));
            donGiaTableView.setItems(donGiaList);
        }
    }

    public static void Sua(ComboBox<String> hocKyKP, TextField giaKP, ComboBox<String> tenDonGiaKP,
                           TableView<model.DonGia> donGiaTableView) throws SQLException {
        if(hocKyKP.getValue().length() != 0 ){
            String tableName = "DonGia" + hocKyKP.getValue();
            model.DonGia donGia = donGiaTableView.getSelectionModel().getSelectedItem();
            model.DonGia donGia1 = new model.DonGia(tenDonGiaKP.getValue(),Long.parseLong(giaKP.getText()));
            if(dbController.updateDonGia(donGia,donGia1,tableName)){
                Information.ThongBaoThongTin("Bạn cập nhật đơn giá thành công ");
            }else{
                Error.ThongBaoLoi("Bạn chưa cập nhật đơn giá thành công (Hãy chọn đúng tên đơn giá)");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ ");
        }
    }
}
