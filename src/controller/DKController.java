package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.alert.Error;
import view.alert.Information;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class DKController implements Initializable {
    @FXML
    TextField tenDN_DK;
    @FXML TextField mk_DK;
    @FXML
    private TextField mk_DK1;
    @FXML
    Label error_MK, phone, emailLabel;
    @FXML
    private CheckBox checkDieuKhoan;
    @FXML
    private TextField hoTen;
    @FXML
    private TextField boMon;
    @FXML
    private TextField soDienThoai;
    @FXML
    private TextField email;
    @FXML
    private TextField phongLamViec;
    @FXML
    ComboBox<String> quyen_DK;
    ObservableList<String> list= FXCollections.observableArrayList("Giảng viên/Giám thị");
    Connection conn = DataBaseConnection.getInstance().getConnection();

    //Kiếm tra tính hợp lệ của tài khoản
    public boolean Check(String soDienThoai, String email) {
        long sdt = 0;
        try {
            sdt = Long.parseLong(soDienThoai);
        } catch (Exception ex) {
            phone.setText("*Sai định dạng số điện thoại (Bao gồm 10 số)");
        }
        if(!email.contains("@")) {
            emailLabel.setText("*Chưa đúng định dạng email (Ex: xxx@.xxx)");
            return false;
        }
        if(sdt < Math.pow(10, 10) || sdt > Math.pow(10, 11) - 1) return false;
        return true;
    }

    public void dangKy(ActionEvent e) {
        try {
            if(checkDieuKhoan.isSelected() ) {
                if(!quyen_DK.getSelectionModel().isEmpty()) {
                    if(tenDN_DK.getText().length() != 0 && mk_DK.getText().length() != 0  &&
                            hoTen.getText().length() != 0  && boMon.getText().length() != 0
                            && soDienThoai.getText().length() != 0  && email.getText().length() != 0 &&
                            phongLamViec.getText().length() != 0 ) {
                        if( mk_DK.getText().equals(mk_DK1.getText())) {
                            error_MK.setText("");
                            if(Check(soDienThoai.getText(), email.getText())) {
                                if(!new DataControler().isValidTK(tenDN_DK.getText(), mk_DK.getText(),
                                        quyen_DK.getValue(), "TaiKhoan_GVGT")) {
                                    phone.setText("");
                                    emailLabel.setText("");
                                    String sql = "insert into TaiKhoan_GVGT values (?, ?, ?, ?, ?, ? , ?, ?)";
                                    var prepare = conn.prepareStatement(sql);
                                    prepare.setString(1, tenDN_DK.getText());
                                    prepare.setString(2, mk_DK.getText());
                                    prepare.setString(3, quyen_DK.getValue());
                                    prepare.setString(4, hoTen.getText());
                                    prepare.setString(5, boMon.getText());
                                    prepare.setString(6, soDienThoai.getText());
                                    prepare.setString(7, email.getText());
                                    prepare.setString(8, phongLamViec.getText());
                                    var result = prepare.executeUpdate();

                                    if(result > 0) {
                                        Information.ThongBaoThongTin("Bạn đã đăng ký tài khoản thành công");
                                    }
                                } else {
                                    Error.ThongBaoLoi("Tài khoản đã tồn tại");
                                }
                            }
                        } else {
                            error_MK.setText("*Mật khẩu nhập lại không khớp");
                        }
                    } else {
                        Error.ThongBaoLoi("Hãy nhập đủ các thông tin");
                    }
                } else {
                    Error.ThongBaoLoi("Hãy chọn quyền đăng ký");
                }
            } else {
                Error.ThongBaoLoi("Hãy đồng ý với các điều khoản trước khi đăng ký");
            }
        } catch (Exception ex) {
            Error.ThongBaoLoi("Chưa nhập đầy đủ các thông tin");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quyen_DK.setItems(list);
        tenDN_DK.setDisable(true);
        mk_DK.setDisable(true);
        mk_DK1.setDisable(true);
        hoTen.setDisable(true);
        boMon.setDisable(true);
        soDienThoai.setDisable(true);
        email.setDisable(true);
        phongLamViec.setDisable(true);
        quyen_DK.setOnAction(e-> {
            if(quyen_DK.getValue().equals("Quản lý")) {
                tenDN_DK.setDisable(false);
                mk_DK.setDisable(false);
                mk_DK1.setDisable(false);
                hoTen.setDisable(true);
                boMon.setDisable(true);
                soDienThoai.setDisable(true);
                email.setDisable(true);
                phongLamViec.setDisable(true);
            } else if( quyen_DK.getValue().equals("Giảng viên/Giám thị")) {
                tenDN_DK.setDisable(false);
                mk_DK.setDisable(false);
                mk_DK1.setDisable(false);
                hoTen.setDisable(false);
                boMon.setDisable(false);
                soDienThoai.setDisable(false);
                email.setDisable(false);
                phongLamViec.setDisable(false);
            }
        });
    }
}
