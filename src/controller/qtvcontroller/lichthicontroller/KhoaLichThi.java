package controller.lichthicontroller;

import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import view.alert.Error;
import view.alert.Information;

import java.sql.Connection;
import java.sql.SQLException;

public class KhoaLichThi {
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    Connection conn = DBConnection.getInstance().getConnection();
    public void Khoa(ComboBox<String> hocKyLT) throws SQLException {
        if(hocKyLT.getValue().length() != 0 && dataControler.isCheckDataLock("LichThi" + hocKyLT.getValue()) && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            String sql="insert into dbo.LockData values(?,?) ;";
            var prepare=conn.prepareStatement(sql) ;
            prepare.setString(1,tableName);
            prepare.setInt(2,1);
            var result=prepare.executeUpdate();
            if(result>0) {
                Information.ThongBaoThongTin("Bạn đã khóa lịch thi thành công ");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ mà bạn muốn khóa hoặc" + "\n" +
                    " dữ liệu học kỳ này đã bị khóa hoặc dữ liệu học kỳ không tồn tại");
        }
    }
}
