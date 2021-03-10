package controller.qtvcontroller.lichthicontroller;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.*;
import model.LichThi;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuaLichThi {
    DataBaseController dataBaseController = new DataBaseController();
    public void sua(ComboBox<String> hocKyLT, TextField maLopTextField, TextField maHPTextField,
                    TextField tenHPTextField, TextField ghiChuTextField, TextField nhomTextField, TextField dotMoTextField,
                    TextField tuanTextField, TextField thuTextField, TextField kipTextField, TextField sldkTextField, TextField phongTextField,
                    DatePicker ngayThiLT, TableView<LichThi> testScheduleTableView) throws SQLException, ParseException {
        if(hocKyLT.getValue().length() !=0 && dataBaseController.checkExistTable("LichThi" + hocKyLT.getValue())){
            if(new DataControler().isCheckDataLock("LichThi" + hocKyLT.getValue())) {
                String tableName = "LichThi" + hocKyLT.getValue();
                LichThi lichThi = testScheduleTableView.getSelectionModel().getSelectedItem();
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
                    LichThi lichThi1 =new LichThi(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                    if(dataBaseController.updateTestScheduleToDatabase(tableName, lichThi1, lichThi)){
                        Information.ThongBaoThongTin("Bạn đã cập nhật lịch thi thành công");
                    }else{
                        Error.ThongBaoLoi("Lịch thi chưa được cập nhật thành công");
                    }
                } else {
                    Error.ThongBaoLoi("Bạn chưa nhập đầy đủ thông tin ");
                }
            } else {
                Error.ThongBaoLoi("Lịch thi đã bị khóa");
            }
        }else{
            Error.ThongBaoLoi("Bảng dữ liệu không tồn tại");
        }
    }
}
