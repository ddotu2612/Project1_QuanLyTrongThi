package controller.giamthicontroller;

import controller.DBController;
import javafx.scene.control.*;
import model.Supervisor;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;

public class SuaThongTinGiamThi {
    static DBController dbController = new DBController();
    public static void Sua(ComboBox<String> hocKyGT, TextField hoVaTenGTTextField, TextField boMonGTTextField,
                           TextField phoneNumberGTTextField, TextField emailGTTextField,
                           TextField phongGTTextField, Spinner soBuoi, TableView<Supervisor> supervisorTableView) throws SQLException {
        if(hocKyGT.getValue().length() !=0 && dbController.checkExistTable("GiamThi" + hocKyGT.getValue())){
            String tableName = "GiamThi" + hocKyGT.getValue();
            Supervisor supervisor = supervisorTableView.getSelectionModel().getSelectedItem();
            String name = hoVaTenGTTextField.getText();
            String boMon = boMonGTTextField.getText();
            String phone = phoneNumberGTTextField.getText();
            String email = emailGTTextField.getText();
            String phong = phongGTTextField.getText();
            int soBuoi1 = (int) soBuoi.getValue();
            Supervisor supervisor1=new Supervisor(name,boMon,phone,email,phong,soBuoi1);
            if(dbController.updateeSupervisorToDatabase(tableName,supervisor,supervisor1)){
                Information.ThongBaoThongTin("Bạn đã cập nhật thông tin giám thị thành công");
            }else{
                Error.ThongBaoLoi("Thông tin giám thị chưa được cập nhật thành công");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa học kỳ hoặc bảng không tồn tại ");
        }
    }
}
