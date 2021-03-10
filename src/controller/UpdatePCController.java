package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.ThongTinTrongThi;
import view.alert.Error;
import view.alert.Information;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class UpdatePCController implements Initializable {
    private ThongTinTrongThi trongThi;
    @FXML
    TextField maLop;
    @FXML
    TextField giamThi1;
    @FXML
    TextField giamThi2;
    @FXML
    TextField ngayThi;
    @FXML
    TextField kipThi;
    @FXML
    ComboBox<String> hocKy;
    ObservableList<String>  list = FXCollections.observableArrayList("20191", "20192", "20193", "20201", "20202", "20203");
    Connection conn = DataBaseConnection.getInstance().getConnection();
    public void setTrongThi(ThongTinTrongThi trongThi, String hocKy1){
        this.trongThi=trongThi;
        maLop.setText(String.valueOf(trongThi.getMaLop()));
        giamThi1.setText(trongThi.getGiamThi1());
        giamThi2.setText(trongThi.getGiamThi2());
        ngayThi.setText(new SimpleDateFormat("yyyy-MM-dd").format(trongThi.getNgayThi()));
        kipThi.setText(trongThi.getKipThi());
        hocKy.setValue(hocKy1);
    }

    public void Update(ActionEvent e) throws SQLException {
        try{
            if(hocKy.getValue().length() != 0 ){
                String tableName="PhanCong" + hocKy.getValue();
                String sql="Update " + tableName + " set maLop=? , giamThi1=? ,giamThi2=?,ngayThi=?,kipThi=? where maLop=?" +
                        " and giamThi1=? and giamThi2=? and ngayThi=? and kipThi=?";
                var prepare= conn.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(maLop.getText()));
                prepare.setString(2,giamThi1.getText());
                prepare.setString(3,giamThi2.getText());
                prepare.setDate(4, Date.valueOf(ngayThi.getText()));
                prepare.setString(5,kipThi.getText());
                prepare.setInt(6, trongThi.getMaLop());
                prepare.setString(7,trongThi.getGiamThi1());
                prepare.setString(8,trongThi.getGiamThi2());
                prepare.setDate(9, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(
                        trongThi.getNgayThi())));
                prepare.setString(10,kipThi.getText());
                var res=prepare.executeUpdate();
                if(res > 0){
                    Information.ThongBaoThongTin("Bạn đã cập nhật thành công");
                }else{
                    Error.ThongBaoLoi("Bạn đã cập nhật không thành công");
                }
            }else{
                Error.ThongBaoLoi("Bạn hãy nhập học kỳ");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Bạn chưa chọn học kỳ hoặc chưa đầy đủ các thông tin hoặc chưa đúng định dạng");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hocKy.setItems(list);
    }
}
