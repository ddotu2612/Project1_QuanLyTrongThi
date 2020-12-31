package controller.giaoviencontroller;

import controller.DBController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Lecturers;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;
public class ThemGiangVien {
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    public void Them(TextField hoVaTenGVTextField, TextField boMonGVTextField, TextField phoneNumberGVTextField,
                     TextField emailGVTextField, TextField phongGVTextField, TextField maLopGVTextField, ComboBox<String> hocKyGV) throws SQLException {
        if(hocKyGV.getValue().length() !=0){
            String tableName = "GiangVien" + hocKyGV.getValue();
            if(dataControler.isCheckDataLock(tableName)  && dbController.checkExistTable(tableName)){
                String name = hoVaTenGVTextField.getText();
                String boMon = boMonGVTextField.getText();
                String phone = phoneNumberGVTextField.getText();
                String email = emailGVTextField.getText();
                String phong = phongGVTextField.getText();
                int maLop= Integer.parseInt(maLopGVTextField.getText());
                Lecturers lecturers=new Lecturers(name,boMon,phone,email,phong,maLop);
                boolean check = dataControler.isValidLecturer(lecturers,tableName);//Kiểm tra xem lịch đã đc thêm vào hay chưa
                if(check){
                    if(dbController.addLecturerToDatabase(lecturers,tableName)) {
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
