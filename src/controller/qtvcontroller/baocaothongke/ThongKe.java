package controller.qtvcontroller.baocaothongke;

import controller.DataBaseController;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import model.KinhPhi;
import view.alert.Error;

import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKe {
    static DataBaseController dataBaseController = new DataBaseController();
    public static void thongKe(BarChart<String, Number> barChart, ComboBox<String> hocKyTK) throws SQLException {
        if(hocKyTK.getValue() != null) {
            String tableName = "KinhPhi" + hocKyTK.getValue();
            ArrayList<KinhPhi> list = dataBaseController.kinhPhiList(tableName);
            if(list != null) {
                long inAn = 0, phoTo = 0, toChuc = 0, giamThi = 0;
                for(var i : list) {
                    inAn += i.getInAn();
                    phoTo += i.getPhoTo();
                    toChuc += i.getToChucThi();
                    giamThi += i.getKinhPhiGT();
                }
                XYChart.Series<String, Number> chiPhi = new XYChart.Series<>();
                XYChart.Data<String, Number> chiPhiInAn = new XYChart.Data<>("IN ẤN", inAn);
                XYChart.Data<String, Number> chiPhiPhoTo = new XYChart.Data<>("PHÔ TÔ", phoTo);
                XYChart.Data<String, Number> chiPhitoChuc = new XYChart.Data<>("CHI PHÍ TỔ CHỨC", toChuc);
                XYChart.Data<String, Number> chiPhiGiamThi = new XYChart.Data<>("CHI PHÍ GIÁM THỊ", giamThi);
                chiPhi.getData().addAll(chiPhiInAn, chiPhiPhoTo, chiPhitoChuc, chiPhiGiamThi);
                chiPhi.setName("Thống kê các chi phí của cả học kỳ " + hocKyTK.getValue());
                barChart.getData().clear();
                barChart.getData().addAll(chiPhi);
                for(var i : chiPhi.getData()) {
                    i.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("CHI PHÍ");
                        alert.setHeaderText(null);
                        alert.setContentText("TIỀN " + i.getXValue() + " : " + i.getYValue().longValue() + " VND");
                        alert.showAndWait();
                    });
                }
            } else {
                Error.ThongBaoLoi("Không có thông tin học kỳ bạn nhập");
            }
        } else {
            Error.ThongBaoLoi("Bạn chưa nhập học kỳ");
        }
    }
}
