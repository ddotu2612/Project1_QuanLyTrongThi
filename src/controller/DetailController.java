package view;

import controller.DBConnection;
import controller.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.KinhPhi;

import java.sql.PreparedStatement;
import java.util.ArrayList;


public class DetailController {
    @FXML
    private Label maLopDetail, hocKy;

    @FXML
    private TextArea textAreaDetail;

    DBController dbController = new DBController();
    public void setInfo(int maLop, int hocKy1) {
        try {
            maLopDetail.setText(String.valueOf(maLop));
            hocKy.setText(String.valueOf(hocKy1));
            ArrayList<KinhPhi> listKP=dbController.searchKinhPhiList(maLop, "KinhPhi" + hocKy1);
            for(int j = 0; j < listKP.size(); j++) {
                textAreaDetail.appendText( "Kinh phí cho nhóm : " + listKP.get(j).getNhom() + "\n" +"   Kinh phí in ấn : " + listKP.get(j).getInAn() + "\n" + "   Kinh phí phô tô : "+
                        listKP.get(j).getPhoTo() + "\n" + "   Kinh phí tổ chức thi : " + listKP.get(j).getToChucThi() + "\n" +
                        "   Kinh phí giám thị : " + listKP.get(j).getKinhPhiGT() + "\n");
            }
            textAreaDetail.appendText("Trạng thái thanh toán : " + (listKP.get(0).getCheckThanhToan() == 1 ? "Đã thanh toán" : "Chưa thanh toán"));
        } catch (Exception ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin học kỳ này chưa có hoặc lỗi kết nối");
            alert.showAndWait();
        }
    }

    public void ThanhToan(ActionEvent e) {
        try {
            String tableName = "KinhPhi" + hocKy.getText();
            String sql="update "+ tableName + " set checkThanhToan = ? where maLop = ? and checkThanhToan = ?";
            PreparedStatement prepare= null;
            prepare = new DBConnection().getConnection().prepareStatement(sql);
            prepare.setInt(1,1);
            prepare.setInt(2, Integer.parseInt(maLopDetail.getText()));
            prepare.setInt(3, 0);
            if(prepare.executeUpdate() >  0) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setHeaderText(null);
                alert.setContentText("Bạn thanh toán thành công cho maLop: " + maLopDetail.getText());
                alert.showAndWait();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn đã thanh toán rồi");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Thông tin học kỳ này chưa có");
            alert.showAndWait();
        }
    }
}
