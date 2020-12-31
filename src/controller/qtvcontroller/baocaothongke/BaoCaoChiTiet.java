package controller.baocaothongke;

import controller.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BaoCao;
import view.alert.Error;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class BaoCaoChiTiet {
    static DBController dbController = new DBController();
    public static void ChiTiet(CheckBox gvCheckBox,CheckBox gtCheckBox , TextField hotenGV_GT, ComboBox<String> hocKyBCTK,
                               TableView<BaoCao> baoCaoTableView, TableColumn<BaoCao, Integer> maLopTableColumn,
                               TableColumn<BaoCao, String> cot2,TableColumn<BaoCao, String> cot3,
                               ObservableList<BaoCao> baoCaoList) throws SQLException, ParseException {
        if(gvCheckBox.isSelected() && hocKyBCTK.getValue() != null && hotenGV_GT.getText().length() != 0 ) {
            String name = hotenGV_GT.getText();
            ArrayList<BaoCao> baoCaos = dbController.BaoCaoGV(name, "KinhPhi" + hocKyBCTK.getValue());
            if(baoCaos.size() != 0) {
                maLopTableColumn.setText("Mã lớp");
                cot2.setText("Tổng chi phí");
                cot3.setText("Trạng thái thanh toán");
                baoCaoList =  FXCollections.observableList(baoCaos);
                maLopTableColumn.setCellValueFactory(new PropertyValueFactory<BaoCao, Integer>("maLop"));
                cot2.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot2"));
                cot3.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot3"));
                baoCaoTableView.setItems(baoCaoList);
            } else {
                Error.ThongBaoLoi("Tên giảng viên không tồn tại hoặc không có thông tin học kỳ bạn chọn");
            }
        } else if(gtCheckBox.isSelected() && hocKyBCTK.getValue() != null && hotenGV_GT.getText().length() != 0) {
            String name = hotenGV_GT.getText();
            ArrayList<BaoCao> baoCaos = dbController.BaoCaoGT(name, hocKyBCTK.getValue());
            if(baoCaos.size() != 0) {
                maLopTableColumn.setText("Mã lớp");
                cot2.setText("Trạng thái tổ chức");
                cot3.setText("Trạng thái thanh toán");
                baoCaoList =  FXCollections.observableList(baoCaos);
                maLopTableColumn.setCellValueFactory(new PropertyValueFactory<BaoCao, Integer>("maLop"));
                cot2.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot2"));
                cot3.setCellValueFactory(new PropertyValueFactory<BaoCao, String>("cot3"));
                baoCaoTableView.setItems(baoCaoList);
            } else {
                Error.ThongBaoLoi("Tên giám thị không tồn tại hoặc không có thông tin học kỳ bạn chọn");
            }
        } else{
            Error.ThongBaoLoi("Chưa nhập học kỳ hoặc chưa chọn loại thông tin hoặc họ tên " +
                    "giảng viên hoặc không có dữ liệu học kỳ này");
        }
    }
}
