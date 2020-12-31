package controller.giaoviencontroller;

import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import view.alert.Error;
import view.alert.Information;

import java.sql.Connection;
import java.sql.SQLException;

public class KhoaGV {
    DataControler dataControler = new DataControler();
    DBController dbController = new DBController();
    Connection conn = DBConnection.getInstance().getConnection();
    public void Khoa( ComboBox<String> hocKyGV) throws SQLException {
        if(hocKyGV.getValue().length() != 0 && dataControler.isCheckDataLock("GiangVien" + hocKyGV.getValue()) && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue();
            String sql="insert into dbo.LockData values(?,?) ;";
            var prepare=conn.prepareStatement(sql) ;
            prepare.setString(1,tableName);
            prepare.setInt(2,1);
            var result=prepare.executeUpdate();
            if(result>0){
                Information.ThongBaoThongTin("Bạn đã khóa thông tin giảng viên thành công ");
            }else{
                Error.ThongBaoLoi("Khóa không thành công");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc bảng dữ liệu học kỳ này đã bị khóa hoặc bảng không tồn tại");
        }
    }
}
