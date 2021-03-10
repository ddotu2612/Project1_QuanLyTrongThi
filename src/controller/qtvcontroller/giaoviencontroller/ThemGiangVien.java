package controller.qtvcontroller.giaoviencontroller;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.GiangVien;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;
public class ThemGiangVien {
    DataBaseController dataBaseController = new DataBaseController();
    DataControler dataControler = new DataControler();
    public void Them(TextField hoVaTenGVTextField, TextField boMonGVTextField, TextField phoneNumberGVTextField,
                     TextField emailGVTextField, TextField phongGVTextField, TextField maLopGVTextField, ComboBox<String> hocKyGV) throws SQLException {
        if(hocKyGV.getValue().length() !=0){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)  && dataBaseController.checkExistTable(tableName)){
                String name = hoVaTenGVTextField.getText();
                String boMon = boMonGVTextField.getText();
                String phone = phoneNumberGVTextField.getText();
                String email = emailGVTextField.getText();
                String phong = phongGVTextField.getText();
                int maLop= Integer.parseInt(maLopGVTextField.getText());
                GiangVien giangVien =new GiangVien(name,boMon,phone,email,phong,maLop);
                boolean check = dataControler.isValidLecturer(giangVien,tableName);//Kiểm tra xem lịch đã đc thêm vào hay chưa
                if(check){
                    if(dataBaseController.addLecturerToDatabase(giangVien,tableName)) {
                        Information.ThongBaoThongTin("Bạn đã thêm thông tin giảng viên thành công");
                    }else {
                        Error.ThongBaoLoi("Thông tin giảng viên chưa được thêm thành công");
                    }
                }else{
                    Error.ThongBaoLoi("Thông tin giảng viên đã tồn tại trong database");
                }
            }else{
                Warning.CanhBao("Thông tin giảng viên đã bị khóa hoặc bảng không tồn tại");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }
}
