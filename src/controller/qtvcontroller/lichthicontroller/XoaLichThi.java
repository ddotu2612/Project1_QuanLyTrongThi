package controller.qtvcontroller.lichthicontroller;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.LichThi;
import view.alert.Confirmation;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;

public class XoaLichThi {
    DataBaseController dataBaseController = new DataBaseController();
    DataControler dataControler = new DataControler();
    public void xoa(ComboBox<String> hocKyLT, TableView<LichThi> testScheduleTableView) throws SQLException {
        if(hocKyLT.getValue().length() != 0 && dataBaseController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                LichThi lichThi =testScheduleTableView.getSelectionModel().getSelectedItem();
                if(Confirmation.ThongBaoXacNhan("Bạn có chắc muốn xóa lịch thi này ?")) {
                    dataBaseController.deleteTestScheduleFromDatabase(lichThi,tableName);
                    Information.ThongBaoThongTin("Bạn đã xóa lịch thi thành công");
                }
            }else{
                Error.ThongBaoLoi("Lịch thi đã bị khóa ");
            }
        }else {
            Warning.CanhBao("Bạn chưa nhập học kỳ mà bạn muốn xóa hoặc dữ liệu học kỳ này không tồn tại");
        }
    }
}
