package controller.qtvcontroller.giaoviencontroller;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.*;
import model.GiangVien;
import view.alert.Confirmation;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;

public class XoaGiangVien {
    DataBaseController dataBaseController = new DataBaseController();
    DataControler dataControler = new DataControler();
    public void Xoa(ComboBox<String> hocKyGV, TableView<GiangVien> lecturerTableView) throws SQLException {
        if(hocKyGV.getValue().length() != 0 && dataBaseController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                GiangVien giangVien = lecturerTableView.getSelectionModel().getSelectedItem();
                if(Confirmation.ThongBaoXacNhan("Bạn có chắc muốn xóa thông tin giảng viên này hay không")) {
                    dataBaseController.deleteLecturerFromDatabase(giangVien,tableName);
                    Information.ThongBaoThongTin("Bạn đã xoá thông tin giảng viên thành công ");
                }
            }else{
                Error.ThongBaoLoi("Thông tin đã bị khóa ");
            }
        }else {
            Warning.CanhBao("Bạn chưa nhập học kỳ mà bạn muốn xóa hoặc bảng không tồn tại");
        }
    }
}
