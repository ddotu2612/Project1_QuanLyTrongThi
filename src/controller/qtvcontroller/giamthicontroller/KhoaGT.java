package controller.qtvcontroller.giamthicontroller;

import controller.DataBaseConnection;
import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import view.alert.Error;
import view.alert.Information;

import java.sql.Connection;
import java.sql.SQLException;

public class KhoaGT {
    static DataControler dataControler = new DataControler();
    static DataBaseController dataBaseController = new DataBaseController();
    static Connection conn = DataBaseConnection.getInstance().getConnection();
    public static void Khoa(ComboBox<String> hocKyGT) throws SQLException {
        if(hocKyGT.getValue().length() != 0 && dataControler.isCheckDataLock("GiamThi" + hocKyGT.getValue())
                && dataBaseController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            String sql="insert into dbo.LockData values(?,?) ;";
            var prepare=conn.prepareStatement(sql) ;
            prepare.setString(1,tableName);
            prepare.setInt(2,1);
            var result=prepare.executeUpdate();
            if(result>0){
                Information.ThongBaoThongTin("Bạn đã khóa thông tin giám thị thành công ");
            }else{
                Error.ThongBaoLoi("Khóa không thành công");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc bảng đã bị khóa hoặc bảng không tồn tại");
        }
    }
}
