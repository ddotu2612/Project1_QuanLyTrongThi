package controller.lichthicontroller;

import controller.DBController;
import javafx.scene.control.*;
import model.TestSchedule;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuaLichThi {
    DBController dbController = new DBController();
    public void sua(ComboBox<String> hocKyLT, TextField maLopTextField, TextField maHPTextField,
                    TextField tenHPTextField, TextField ghiChuTextField, TextField nhomTextField, TextField dotMoTextField,
                    TextField tuanTextField, TextField thuTextField, TextField kipTextField, TextField sldkTextField, TextField phongTextField,
                    DatePicker ngayThiLT, TableView<TestSchedule> testScheduleTableView) throws SQLException, ParseException {
        if(hocKyLT.getValue().length() !=0 && dbController.checkExistTable("LichThi" + hocKyLT.getValue())){
            String tableName = "LichThi" + hocKyLT.getValue();
            TestSchedule testSchedule = testScheduleTableView.getSelectionModel().getSelectedItem();
            if(ngayThiLT.getValue() != null) {
                int maLop=Integer.parseInt(maLopTextField.getText());
                String maHP=maHPTextField.getText();
                String tenHP=tenHPTextField.getText();
                String ghiChu=ghiChuTextField.getText();
                String nhom=nhomTextField.getText();
                String dotMo=dotMoTextField.getText();
                String tuan=tuanTextField.getText();
                String thu=thuTextField.getText();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                Date ngayThi=simpleDateFormat.parse(ngayThiLT.getValue().toString());
                String kip=kipTextField.getText();
                int SLDK= Integer.parseInt(sldkTextField.getText());
                String phong=phongTextField.getText();
                TestSchedule testSchedule1=new TestSchedule(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                if(dbController.updateTestScheduleToDatabase(tableName,testSchedule1,testSchedule)){
                      Information.ThongBaoThongTin("Bạn đã cập nhật lịch thi thành công");
                }else{
                    Error.ThongBaoLoi("Lịch thi chưa được cập nhật thành công");
                }
            } else {
                Error.ThongBaoLoi("Bạn chưa nhập đầy đủ thông tin ");
            }
        }else{
            Error.ThongBaoLoi("Bảng dữ liệu không tồn tại");
        }
    }
}
