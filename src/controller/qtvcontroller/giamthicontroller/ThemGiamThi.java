package controller.qtvcontroller.giamthicontroller;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import model.GiamThi;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;

public class ThemGiamThi {
    DataBaseController dataBaseController = new DataBaseController();
    DataControler dataControler = new DataControler();
    public void Them(ComboBox<String> hocKyGT, TextField hoVaTenGTTextField, TextField boMonGTTextField,
                     TextField phoneNumberGTTextField, TextField emailGTTextField,
                     TextField phongGTTextField, Spinner soBuoi) throws SQLException {
        if(hocKyGT.getValue().length() !=0){
            String tableName = "GiamThi" + hocKyGT.getValue();
            if(dataControler.isCheckDataLock(tableName)  && dataBaseController.checkExistTable(tableName)){
                String name = hoVaTenGTTextField.getText();
                String boMon = boMonGTTextField.getText();
                String phone = phoneNumberGTTextField.getText();
                String email = emailGTTextField.getText();
                String phong = phongGTTextField.getText();
                int soBuoi1 = (int) soBuoi.getValue();
                GiamThi giamThi = new GiamThi(name, boMon, phone, email, phong, soBuoi1);
                boolean check=dataControler.isValidSupervisor(giamThi,tableName);//Kiểm tra xem giám thị  đã đc thêm vào hay chưa
                if(check){
                    if(dataBaseController.addSupervisorToDatabase(giamThi,tableName)) {
                        Information.ThongBaoThongTin("Bạn đã thêm thông tin giám thị thành công");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        Error.ThongBaoLoi("Thông tin giám thị chưa được thêm thành công");
                    }
                }else{
                    Error.ThongBaoLoi("Thông tin giám thị đã tồn tại trong database");
                }
            }else{
                Warning.CanhBao("Thông tin giám thị đã bị khóa hoặc bảng không tồn tại");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }
}
