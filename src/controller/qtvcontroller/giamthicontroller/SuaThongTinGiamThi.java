package controller.qtvcontroller.giamthicontroller;

import controller.DataBaseController;
import javafx.scene.control.*;
import model.GiamThi;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;

public class SuaThongTinGiamThi {
    static DataBaseController dataBaseController = new DataBaseController();
    public static void Sua(ComboBox<String> hocKyGT, TextField hoVaTenGTTextField, TextField boMonGTTextField,
                           TextField phoneNumberGTTextField, TextField emailGTTextField,
                           TextField phongGTTextField, Spinner soBuoi, TableView<GiamThi> supervisorTableView) throws SQLException {
        if(hocKyGT.getValue().length() !=0 && dataBaseController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            GiamThi giamThi = supervisorTableView.getSelectionModel().getSelectedItem();
            String name = hoVaTenGTTextField.getText();
            String boMon = boMonGTTextField.getText();
            String phone = phoneNumberGTTextField.getText();
            String email = emailGTTextField.getText();
            String phong = phongGTTextField.getText();
            int soBuoi1 = (int) soBuoi.getValue();
            GiamThi giamThi1 =new GiamThi(name,boMon,phone,email,phong,soBuoi1);
            if(dataBaseController.updateeSupervisorToDatabase(tableName, giamThi, giamThi1)){
                Information.ThongBaoThongTin("Bạn đã cập nhật thông tin giám thị thành công");
            }else{
                Error.ThongBaoLoi("Thông tin giám thị chưa được cập nhật thành công");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa học kỳ hoặc bảng không tồn tại ");
        }
    }
}
