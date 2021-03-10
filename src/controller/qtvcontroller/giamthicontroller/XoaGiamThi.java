package controller.qtvcontroller.giamthicontroller;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.*;
import model.GiamThi;
import view.alert.Confirmation;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;

public class XoaGiamThi {
    static DataControler dataControler = new DataControler();
    static DataBaseController dataBaseController = new DataBaseController();
    public static void Xoa(ComboBox<String> hocKyGT, TableView<GiamThi> supervisorTableView) throws SQLException {
        if(hocKyGT.getValue().length() != 0 && dataBaseController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                GiamThi giamThi = supervisorTableView.getSelectionModel().getSelectedItem();
                if(Confirmation.ThongBaoXacNhan("Bạn có chắc muốn xóa thông tin này không?")) {
                    if (dataBaseController.deleteSupervisorFromDatabase(giamThi,tableName)) {
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
