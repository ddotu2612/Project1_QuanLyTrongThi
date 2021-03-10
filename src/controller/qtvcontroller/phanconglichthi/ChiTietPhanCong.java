package controller.qtvcontroller.phanconglichthi;

import controller.DataBaseController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.GiamThi;
import model.ThongTinTrongThi;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietPhanCong {
    static DataBaseController dataBaseController = new DataBaseController();
    public static void ChiTiet(ComboBox<String> hocKyPC, TableView<ThongTinTrongThi> tableViewTrongThi,
                               TextArea detailPC) throws SQLException {
        if( hocKyPC.getValue().length() != 0){
            String tableSupervisor = "GiamThi"+hocKyPC.getValue();
            ThongTinTrongThi thongTinTrongThi = tableViewTrongThi.getSelectionModel().getSelectedItem();
            GiamThi giamThi1 = dataBaseController.searchSupervisorFromDatabase(thongTinTrongThi.getGiamThi1(),tableSupervisor);
            GiamThi giamThi2 = dataBaseController.searchSupervisorFromDatabase(thongTinTrongThi.getGiamThi2(),tableSupervisor);
            detailPC.clear();
            detailPC.appendText("____________THÔNG TIN CHI TIẾT_______________\n");
            detailPC.appendText("______ Mã Lớp: " + thongTinTrongThi.getMaLop()+"\n");
            detailPC.appendText("______Ngày thi: "+ thongTinTrongThi.getNgayThi()+"\n");
            detailPC.appendText("______Kíp thi: "+ thongTinTrongThi.getKipThi()+"\n");
            detailPC.appendText("____________Thông tin giám thị_______________"+"\n");
            detailPC.appendText("_________________Giám thị 1__________________\n");
            detailPC.appendText("______Họ tên: "+ giamThi1.getNameLecturer()+"\n");
            detailPC.appendText("______Bộ môn: "+ giamThi1.getFaculty()+"\n");
            detailPC.appendText("______Số điện thoại: "+ giamThi1.getPhoneNumber()+"\n");
            detailPC.appendText("______Email: "+ giamThi1.getEmail()+"\n");
            detailPC.appendText("______Phòng làm việc: "+ giamThi1.getWorkPlace()+"\n");
            if(giamThi2 != null){
                detailPC.appendText("_________________Giám thị 2__________________\n");
                detailPC.appendText("______Họ tên: "+ giamThi2.getNameLecturer()+"\n");
                detailPC.appendText("______Bộ môn: "+ giamThi2.getFaculty()+"\n");
                detailPC.appendText("______Số điện thoại: "+ giamThi2.getPhoneNumber()+"\n");
                detailPC.appendText("______Email: "+ giamThi2.getEmail()+"\n");
                detailPC.appendText("______Phòng làm việc: "+ giamThi2.getWorkPlace()+"\n");
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
            ArrayList<ThongTinTrongThi> list = dataBaseController.searchInfoTrongThiFromDatabase(maLop, tableName);
            for(ThongTinTrongThi thongTinTrongThi : list) {
                GiamThi giamThi1 = dataBaseController.searchSupervisorFromDatabase(thongTinTrongThi.getGiamThi1(),tableSupervisor);
                GiamThi giamThi2 = dataBaseController.searchSupervisorFromDatabase(thongTinTrongThi.getGiamThi2(),tableSupervisor);
                detailPC.appendText("____________THÔNG TIN CHI TIẾT_______________\n");
                detailPC.appendText("______ Mã Lớp: " + thongTinTrongThi.getMaLop()+"\n");
                detailPC.appendText("______Ngày thi: "+ thongTinTrongThi.getNgayThi()+"\n");
                detailPC.appendText("______Kíp thi: "+ thongTinTrongThi.getKipThi()+"\n");
                detailPC.appendText("____________Thông tin giám thị_______________"+"\n");
                detailPC.appendText("_________________Giám thị 1__________________\n");
                detailPC.appendText("______Họ tên: "+ giamThi1.getNameLecturer()+"\n");
                detailPC.appendText("______Bộ môn: "+ giamThi1.getFaculty()+"\n");
                detailPC.appendText("______Số điện thoại: "+ giamThi1.getPhoneNumber()+"\n");
                detailPC.appendText("______Email: "+ giamThi1.getEmail()+"\n");
                detailPC.appendText("______Phòng làm việc: "+ giamThi1.getWorkPlace()+"\n");
                if(giamThi2 != null){
                    detailPC.appendText("_________________Giám thị 2__________________\n");
                    detailPC.appendText("______Họ tên: "+ giamThi2.getNameLecturer()+"\n");
                    detailPC.appendText("______Bộ môn: "+ giamThi2.getFaculty()+"\n");
                    detailPC.appendText("______Số điện thoại: "+ giamThi2.getPhoneNumber()+"\n");
                    detailPC.appendText("______Email: "+ giamThi2.getEmail()+"\n");
                    detailPC.appendText("______Phòng làm việc: "+ giamThi2.getWorkPlace()+"\n");
                }else{
                    detailPC.appendText("__________________Giám thị 2___________________\n");
                    detailPC.appendText("___________________Không có____________________\n");
                    detailPC.appendText("______________________HẾT_______________________\n");
                }
            }
        }
    }
}
