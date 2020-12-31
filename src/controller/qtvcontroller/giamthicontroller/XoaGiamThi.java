package controller.giamthicontroller;

import controller.DBController;
import controller.DataControler;
import javafx.scene.control.*;
import model.Supervisor;
import view.alert.Confirmation;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;
import java.util.Optional;

public class XoaGiamThi {
    static DataControler dataControler = new DataControler();
    static DBController dbController = new DBController();
    public static void Xoa(ComboBox<String> hocKyGT, TableView<Supervisor> supervisorTableView) throws SQLException {
        if(hocKyGT.getValue().length() != 0 && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                Supervisor supervisor = supervisorTableView.getSelectionModel().getSelectedItem();
                if(Confirmation.ThongBaoXacNhan("Bạn có chắc muốn xóa thông tin này không?")) {
                    if (dbController.deleteSupervisorFromDatabase(supervisor,tableName)) {
                        Information.ThongBaoThongTin("Bạn đã xoá thông tin giám thị thành công ");
                    } else {
                        Error.ThongBaoLoi("Xóa không thành công");
                    }
                }
            }else{
                Error.ThongBaoLoi("Thông tin đã bị khóa ");
            }
        }else {
            Warning.CanhBao("Bạn chưa nhập học kỳ mà bạn muốn xóa hoặc bảng không tồn tại");
        }
    }
}
