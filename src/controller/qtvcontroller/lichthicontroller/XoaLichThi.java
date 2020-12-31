package controller.lichthicontroller;

import controller.DBController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.TestSchedule;
import view.alert.Confirmation;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;

public class XoaLichThi {
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    public void xoa(ComboBox<String> hocKyLT, TableView<TestSchedule> testScheduleTableView) throws SQLException {
        if(hocKyLT.getValue().length() != 0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName)){
                TestSchedule testSchedule=testScheduleTableView.getSelectionModel().getSelectedItem();
                if(Confirmation.ThongBaoXacNhan("Bạn có chắc muốn xóa lịch thi này ?")) {
                    dbController.deleteTestScheduleFromDatabase(testSchedule,tableName);
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
