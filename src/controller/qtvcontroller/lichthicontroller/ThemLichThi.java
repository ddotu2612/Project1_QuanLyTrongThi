package controller.lichthicontroller;

import controller.DBConnection;
import controller.DBController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.TestSchedule;
import view.alert.Error;
import view.alert.Information;
import view.alert.Warning;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThemLichThi {
    DBConnection conn = new DBConnection();
    DBController dbController = new DBController();
    DataControler dataControler = new DataControler();
    public void ThemLich(ComboBox<String> hocKyLT, TextField maLopTextField, TextField maHPTextField,
                         TextField tenHPTextField, TextField ghiChuTextField, TextField nhomTextField, TextField dotMoTextField,
    TextField tuanTextField, TextField thuTextField, TextField kipTextField,TextField sldkTextField, TextField phongTextField,
       DatePicker ngayThiLT) throws SQLException, ParseException {
        if(hocKyLT.getValue().length() !=0){
            String tableName = "LichThi" + hocKyLT.getValue();
            if(dataControler.isCheckDataLock(tableName) && dbController.checkExistTable(tableName)){
                int maLop=Integer.parseInt(maLopTextField.getText());
                String maHP=maHPTextField.getText();
                String tenHP=tenHPTextField.getText();
                String ghiChu=ghiChuTextField.getText();
                String nhom=nhomTextField.getText();
                String dotMo=dotMoTextField.getText();
                String tuan=tuanTextField.getText();
                String thu=thuTextField.getText();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                //Date ngayThi=simpleDateFormat.parse(ngayThiTextField.getText());
                Date ngayThi=simpleDateFormat.parse(ngayThiLT.getValue().toString());
                String kip = kipTextField.getText();
                int SLDK= Integer.parseInt(sldkTextField.getText());
                String phong=phongTextField.getText();
                TestSchedule testSchedule=new TestSchedule(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                boolean check=dataControler.isValidTestSchedule(testSchedule,tableName);//Kiểm tra xem lịch đã đc thêm vào hay chưa
                if(check){
                    if(dbController.addTestScheduleToDatabase(testSchedule,tableName)) {
                        Information.ThongBaoThongTin("Bạn đã thêm lịch thi thành công");
                    }else {
                        Error.ThongBaoLoi("Lịch thi chưa được thêm thành công");
                    }
                }else{
                    Error.ThongBaoLoi("Lịch thi đã tồn tại trong database");
                }
            }else{
                Warning.CanhBao("Lịch thi đã bị khóa hoặc bảng dữ liệu lịch thi học kỳ bạn nhập không tồn tại");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ ");
        }
    }
}
