package controller.phanconglichthi;

import controller.DBController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.InfoTrongThi;
import model.Supervisor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietPhanCong {
    static DBController dbController = new DBController();
    public static void ChiTiet(ComboBox<String> hocKyPC, TableView<InfoTrongThi> tableViewTrongThi,
                               TextArea detailPC) throws SQLException {
        if( hocKyPC.getValue().length() != 0){
            String tableSupervisor = "GiamThi"+hocKyPC.getValue();
            InfoTrongThi infoTrongThi = tableViewTrongThi.getSelectionModel().getSelectedItem();
            Supervisor supervisor1 = dbController.searchSupervisorFromDatabase(infoTrongThi.getGiamThi1(),tableSupervisor);
            Supervisor supervisor2 = dbController.searchSupervisorFromDatabase(infoTrongThi.getGiamThi2(),tableSupervisor);
            detailPC.clear();
            detailPC.appendText("____________THÔNG TIN CHI TIẾT_______________\n");
            detailPC.appendText("______ Mã Lớp: " + infoTrongThi.getMaLop()+"\n");
            detailPC.appendText("______Ngày thi: "+infoTrongThi.getNgayThi()+"\n");
            detailPC.appendText("______Kíp thi: "+infoTrongThi.getKipThi()+"\n");
            detailPC.appendText("____________Thông tin giám thị_______________"+"\n");
            detailPC.appendText("_________________Giám thị 1__________________\n");
            detailPC.appendText("______Họ tên: "+supervisor1.getNameLecturer()+"\n");
            detailPC.appendText("______Bộ môn: "+supervisor1.getFaculty()+"\n");
            detailPC.appendText("______Số điện thoại: "+supervisor1.getPhoneNumber()+"\n");
            detailPC.appendText("______Email: "+supervisor1.getEmail()+"\n");
            detailPC.appendText("______Phòng làm việc: "+supervisor1.getWorkPlace()+"\n");
            if(supervisor2 != null){
                detailPC.appendText("_________________Giám thị 2__________________\n");
                detailPC.appendText("______Họ tên: "+supervisor2.getNameLecturer()+"\n");
                detailPC.appendText("______Bộ môn: "+supervisor2.getFaculty()+"\n");
                detailPC.appendText("______Số điện thoại: "+supervisor2.getPhoneNumber()+"\n");
                detailPC.appendText("______Email: "+supervisor2.getEmail()+"\n");
                detailPC.appendText("______Phòng làm việc: "+supervisor2.getWorkPlace()+"\n");
            }else{
                detailPC.appendText("__________________Giám thị 2___________________\n");
                detailPC.appendText("___________________Không có____________________\n");
                detailPC.appendText("______________________HẾT_______________________\n");
            }
        }
    }

    public static void ChiTietLop(ComboBox<String> hocKyPC, TextArea detailPC, TextField maLopTT) throws SQLException {
        if( hocKyPC.getValue().length() != 0){
            int maLop = Integer.parseInt(maLopTT.getText());
            String tableName = "PhanCong" + hocKyPC.getValue();
            String tableSupervisor = "GiamThi"+hocKyPC.getValue();
            ArrayList<InfoTrongThi> list = dbController.searchInfoTrongThiFromDatabase(maLop, tableName);
            for(InfoTrongThi infoTrongThi : list) {
                Supervisor supervisor1 = dbController.searchSupervisorFromDatabase(infoTrongThi.getGiamThi1(),tableSupervisor);
                Supervisor supervisor2 = dbController.searchSupervisorFromDatabase(infoTrongThi.getGiamThi2(),tableSupervisor);
                detailPC.appendText("____________THÔNG TIN CHI TIẾT_______________\n");
                detailPC.appendText("______ Mã Lớp: " + infoTrongThi.getMaLop()+"\n");
                detailPC.appendText("______Ngày thi: "+infoTrongThi.getNgayThi()+"\n");
                detailPC.appendText("______Kíp thi: "+infoTrongThi.getKipThi()+"\n");
                detailPC.appendText("____________Thông tin giám thị_______________"+"\n");
                detailPC.appendText("_________________Giám thị 1__________________\n");
                detailPC.appendText("______Họ tên: "+supervisor1.getNameLecturer()+"\n");
                detailPC.appendText("______Bộ môn: "+supervisor1.getFaculty()+"\n");
                detailPC.appendText("______Số điện thoại: "+supervisor1.getPhoneNumber()+"\n");
                detailPC.appendText("______Email: "+supervisor1.getEmail()+"\n");
                detailPC.appendText("______Phòng làm việc: "+supervisor1.getWorkPlace()+"\n");
                if(supervisor2 != null){
                    detailPC.appendText("_________________Giám thị 2__________________\n");
                    detailPC.appendText("______Họ tên: "+supervisor2.getNameLecturer()+"\n");
                    detailPC.appendText("______Bộ môn: "+supervisor2.getFaculty()+"\n");
                    detailPC.appendText("______Số điện thoại: "+supervisor2.getPhoneNumber()+"\n");
                    detailPC.appendText("______Email: "+supervisor2.getEmail()+"\n");
                    detailPC.appendText("______Phòng làm việc: "+supervisor2.getWorkPlace()+"\n");
                }else{
                    detailPC.appendText("__________________Giám thị 2___________________\n");
                    detailPC.appendText("___________________Không có____________________\n");
                    detailPC.appendText("______________________HẾT_______________________\n");
                }
            }
        }
    }
}
