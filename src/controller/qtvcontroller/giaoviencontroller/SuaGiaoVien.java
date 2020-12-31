package controller.giaoviencontroller;

import controller.DBController;
import controller.DataControler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Lecturers;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;

public class SuaGiaoVien {
    DBController dbController = new DBController();
    public void Sua(TextField hoVaTenGVTextField, TextField boMonGVTextField, TextField phoneNumberGVTextField,
                    TextField emailGVTextField, TextField phongGVTextField, TextField maLopGVTextField, ComboBox<String> hocKyGV
    , TableView<Lecturers> lecturerTableView) throws SQLException {
        if(hocKyGV.getValue().length() !=0 && dbController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue() ;
            Lecturers lecturers = lecturerTableView.getSelectionModel().getSelectedItem();
            if(phoneNumberGVTextField.getText().length() != 0 && emailGVTextField.getText().length() != 0) {
                String name = hoVaTenGVTextField.getText();
                String boMon = boMonGVTextField.getText();
                String phone = phoneNumberGVTextField.getText();
                String email = emailGVTextField.getText();
                String phong = phongGVTextField.getText();
                int maLop= Integer.parseInt(maLopGVTextField.getText());
                Lecturers lecturers1=new Lecturers(name,boMon,phone,email,phong,maLop);
                if(dbController.updateeLecturerToDatabase(tableName,lecturers,lecturers1)){
                    Information.ThongBaoThongTin("Bạn đã cập nhật thông tin giảng viên thành công");
                }else{
                    Error.ThongBaoLoi("Thông tin giảng viên chưa được cập nhật thành công");
                }
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ hoặc bảng dữ liệu học kỳ này không tồn tại ");
        }
    }
}
