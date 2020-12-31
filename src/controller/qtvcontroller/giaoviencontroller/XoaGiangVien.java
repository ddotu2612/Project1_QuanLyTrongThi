package controller.giaoviencontroller;

import controller.DBController;
import controller.DataControler;
import javafx.scene.control.*;
import model.Lecturers;
import view.alert.Confirmation;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;

public class XoaGiangVien {
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    public void Xoa(ComboBox<String> hocKyGV, TableView<Lecturers> lecturerTableView) throws SQLException {
        if(hocKyGV.getValue().length() != 0 && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                Lecturers lecturers = lecturerTableView.getSelectionModel().getSelectedItem();
                if(Confirmation.ThongBaoXacNhan("Bạn có chắc muốn xóa thông tin giảng viên này hay không")) {
                    dbController.deleteLecturerFromDatabase(lecturers,tableName);
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
