package controller.qtvcontroller.quanlykinhphi;

import controller.DataBaseConnection;
import controller.DataBaseController;
import controller.DataControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LichThi;
import view.alert.Error;
import view.alert.Information;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class KinhPhi {
    static DataBaseController dataBaseController = new DataBaseController();
    public static void CapNhatKinhPhi(TableView<model.KinhPhi> kinhPhiTableView, TableColumn<model.KinhPhi,Integer> maLopKPTableColumn,
                                      TableColumn<model.KinhPhi,String> tenGVTableColumn,TableColumn<model.KinhPhi,Long> inAnTableColumn,
                                      TableColumn<model.KinhPhi,Long> phoToTableColumn,TableColumn<model.KinhPhi,Long> toChucThiTableColumn,
                                      TableColumn<model.KinhPhi,Long> kinhPhiGTTableColumn,TableColumn<model.KinhPhi,Integer> checkThanhToanTableColumn,
                                      TableColumn<model.KinhPhi,String> nhomKPTableColumn,ObservableList<model.KinhPhi> kinhPhiList,
                                      ComboBox<String> hocKyKP) {
        ArrayList<model.KinhPhi> kinhPhi = null;
        try {
            kinhPhi = dataBaseController.kinhPhiList("KinhPhi" + hocKyKP.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(kinhPhi != null){
            kinhPhiList = FXCollections.observableArrayList(kinhPhi);
            maLopKPTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,Integer>("maLop"));
            tenGVTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,String>("tenGV"));
            inAnTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,Long>("inAn"));
            phoToTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,Long>("phoTo"));
            toChucThiTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,Long>("toChucThi"));
            kinhPhiGTTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,Long>("kinhPhiGT"));
            checkThanhToanTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,Integer>("checkThanhToan"));
            nhomKPTableColumn.setCellValueFactory(new PropertyValueFactory<model.KinhPhi,String>("nhom"));
            kinhPhiTableView.setItems(kinhPhiList);
        } else {
            kinhPhiTableView.setItems(null);
        }
    }
    static Connection conn = DataBaseConnection.getInstance().getConnection();
    public static void TinhToanKinhPhi(ComboBox<String> hocKyKP) throws SQLException {
        if(!new DataControler().isCheckDataLock("PhanCong" + hocKyKP.getValue())) {
            if(hocKyKP.getValue().length() != 0 && !dataBaseController.checkExistTable("KinhPhi" + hocKyKP.getValue())){
                String tableNameGV = "GiangVien" + hocKyKP.getValue();
                String tableNameDG = "DonGia" + hocKyKP.getValue();
                String tableNameKP = "KinhPhi" + hocKyKP.getValue();
                String tableNameLT = "LichThi" + hocKyKP.getValue();
                ArrayList<LichThi> listLT = dataBaseController.testScheduleList(tableNameLT);
                if(listLT != null){
                    if(dataBaseController.checkExistTable(tableNameKP)){
                        String sql="drop table " + tableNameKP;
                        var prepare= conn.prepareStatement(sql);
                        prepare.executeUpdate();
                    }
                    String sql1="create table " + tableNameKP + " (maLop int,tenGV nvarchar(50) ,inAn bigint " +
                            ",phoTo bigint,toChucThi bigint,kinhPhiGT bigint,checkThanhToan int,nhom nvarchar(50));";
                    var prepare = conn.prepareStatement(sql1);
                    prepare.executeUpdate();
                    for (LichThi test:listLT) {
                        int maLop=test.getMaLop();
                        String tenGV= dataBaseController.searchNameLecturer(maLop,tableNameGV);
                        long inAn= dataBaseController.getGia("in ấn",tableNameDG)*test.getSLDK();
                        long phoTo= dataBaseController.getGia("Phô tô đề thi",tableNameDG)*test.getSLDK();
                        long toChucThi= dataBaseController.getGia("Kinh phí tổ chức",tableNameDG)*test.getSLDK();
                        long kinhPhiGT= dataBaseController.getGia("Tiền giám thị",tableNameDG)*((test.getSLDK()>=60) ? 2:1) ;
                        model.KinhPhi kinhPhi=new model.KinhPhi(maLop,tenGV,inAn,phoTo,toChucThi,kinhPhiGT,0,test.getNhom());
                        dataBaseController.addKinhPhi(kinhPhi,tableNameKP);
                    }
                    Information.ThongBaoThongTin("Tính toán kinh phí thành công");
                }else{
                    Error.ThongBaoLoi("Học kỳ bạn nhập không tồn tại trong cơ sở dữ liệu");
                }
            }else{
                Error.ThongBaoLoi("Bạn chưa nhâp học kỳ hoặc đã tính toán chi phí cho học kỳ này rồi");
            }
        } else {
            Error.ThongBaoLoi("Chưa thể tính toán kinh phí khi chưa phân công giám thị và tổ chức thi");
        }
    }

    public static void ThanhToanKinhPhi(ComboBox<String> hocKyKP, TextField maLopKP) throws SQLException {
        if(hocKyKP.getValue().length() != 0){
            String tableName = "KinhPhi" + hocKyKP.getValue();
            String sql = "update "+ tableName+ " set checkThanhToan=? where maLop = ?";
            var prepare= conn.prepareStatement(sql);
            prepare.setInt(1,1);
            prepare.setInt(2, Integer.parseInt(maLopKP.getText()));
            if(prepare.executeUpdate()>0) {
                Information.ThongBaoThongTin("Bạn thanh toán thành công cho maLop: "+maLopKP.getText());
            }else{
                Error.ThongBaoLoi("Bạn chưa thanh toán thành công cho maLop: "+maLopKP.getText());
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ ");
        }
    }

    public static void ChiTietThanhToan(ComboBox<String> hocKyKP, TextField maLopKP,
                                        TextArea detailKP) throws SQLException {
        if(hocKyKP.getValue().length() != 0){
            String tableNameKP="KinhPhi" + hocKyKP.getValue();
            if(detailKP.getText().length() !=0) detailKP.setText("");
            int maLop= Integer.parseInt(maLopKP.getText());
            ArrayList<model.KinhPhi> listKP = dataBaseController.searchKinhPhiList(maLop,tableNameKP);
            if(listKP != null) {
                detailKP.appendText("Chi tiết thanh toán mã lớp: "+ maLopKP.getText()+"\n");
                detailKP.appendText("Giảng viên giảng dạy: "+listKP.get(0).getTenGV()+"\n");
                long sum=0;
                for(model.KinhPhi kp : listKP){
                    detailKP.appendText("Kinh Phí chi tiết cho nhóm: "+kp.getNhom()+"_Mã lớp: "+kp.getMaLop()+"\n");
                    detailKP.appendText("In ấn: "+kp.getInAn()+"\n");
                    sum+=kp.getInAn();
                    detailKP.appendText("Phô tô đề thi: "+kp.getPhoTo()+"\n");
                    sum+=kp.getPhoTo();
                    detailKP.appendText("Kinh phí tổ chức thi: "+kp.getToChucThi()+"\n");
                    sum+=kp.getToChucThi();
                    detailKP.appendText("Kinh phí giám thị : "+kp.getKinhPhiGT()+"\n");
                    sum+=kp.getKinhPhiGT();
                    detailKP.appendText("             ------------             "+"\n");
                }
                detailKP.appendText(" Tổng kinh phí: " + sum + " VND"+"\n");
                detailKP.appendText(" Đã thanh toán chi phí chưa: "+
                        ((listKP.get(0).getCheckThanhToan()==1) ? "Đã thanh toán":"Chưa thanh toán"));
            } else {
                Error.ThongBaoLoi("Dữ liệu học kỳ này không tồn tại hoặc không có mã lớp bạn chọn");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhâp học kỳ ");
        }
    }
}
