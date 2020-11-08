package view;

import controller.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InfoTrongThi;
import org.apache.poi.ss.util.SSCellRange;

import java.io.IOException;
import java.security.PublicKey;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UpdatePCController {
    private InfoTrongThi trongThi;
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
    TextField hocKy;

    public void setTrongThi(InfoTrongThi trongThi){
        this.trongThi=trongThi;
        maLop.setText(String.valueOf(trongThi.getMaLop()));
        giamThi1.setText(trongThi.getGiamThi1());
        giamThi2.setText(trongThi.getGiamThi2());
        ngayThi.setText(new SimpleDateFormat("yyyy-MM-dd").format(trongThi.getNgayThi()));
        kipThi.setText(trongThi.getKipThi());
    }

    public void Update(ActionEvent e) throws SQLException {
        if(hocKy.getText().length() != 0 ){
            String tableName="PhanCong" + hocKy.getText();
            if(maLop.getText().length()!=0 && giamThi1.getText().length() != 0 && giamThi2.getText().length() != 0 && ngayThi.getText().length() != 0 && kipThi.getText().length() != 0){
                String sql="Update "+tableName+ " set maLop=? , giamThi1=? ,giamThi2=?,ngayThi=?,kipThi=? where maLop=? and giamThi1=? and giamThi2=? and ngayThi=? and kipThi=?";
                var prepare=new DBConnection().getConnection().prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(maLop.getText()));
                prepare.setString(2,giamThi1.getText());
                prepare.setString(3,giamThi2.getText());
                prepare.setDate(4, Date.valueOf(ngayThi.getText()));
                prepare.setString(5,kipThi.getText());
                prepare.setInt(6, trongThi.getMaLop());
                prepare.setString(7,trongThi.getGiamThi1());
                prepare.setString(8,trongThi.getGiamThi2());
                prepare.setDate(9, java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(trongThi.getNgayThi())));
                prepare.setString(10,kipThi.getText());
                var res=prepare.executeUpdate();
                if(res>0){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã cập nhật thành công");
                    alert.showAndWait();
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã cập nhật không thành công");
                    alert.showAndWait();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn hãy nhập đầy đủ các thông tin");
                alert.showAndWait();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Bạn hãy nhập học kỳ");
            alert.showAndWait();
        }
    }
    public void back(ActionEvent e) throws IOException {
        Stage stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("QTVSample.fxml"));
        Parent parent=loader.load();
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
