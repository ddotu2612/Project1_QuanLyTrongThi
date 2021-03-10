package controller.qtvcontroller.giaoviencontroller;

import controller.DataBaseController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.GiangVien;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;

public class SuaGiaoVien {
    DataBaseController dataBaseController = new DataBaseController();
    public void Sua(TextField hoVaTenGVTextField, TextField boMonGVTextField, TextField phoneNumberGVTextField,
                    TextField emailGVTextField, TextField phongGVTextField, TextField maLopGVTextField, ComboBox<String> hocKyGV
    , TableView<GiangVien> lecturerTableView) throws SQLException {
        if(hocKyGV.getValue().length() !=0 && dataBaseController.checkExistTable("GiangVien" + hocKyGV.getValue())){
            String tableName = "GiangVien" + hocKyGV.getValue() ;
            GiangVien giangVien = lecturerTableView.getSelectionModel().getSelectedItem();
            if(phoneNumberGVTextField.getText().length() != 0 && emailGVTextField.getText().length() != 0) {
                String name = hoVaTenGVTextField.getText();
                String boMon = boMonGVTextField.getText();
                String phone = phoneNumberGVTextField.getText();
                String email = emailGVTextField.getText();
                String phong = phongGVTextField.getText();
                int maLop= Integer.parseInt(maLopGVTextField.getText());
                GiangVien giangVien1 =new GiangVien(name,boMon,phone,email,phong,maLop);
                if(dataBaseController.updateeLecturerToDatabase(tableName, giangVien, giangVien1)){
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
