package controller.qtvcontroller.lichthicontroller;

import controller.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LichThi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CapNhatLichThi {
    DataBaseController dataBaseController = new DataBaseController();
    public void reset(TableView<LichThi> testScheduleTableView, TableColumn<LichThi,Integer> maLopColumn,
                      TableColumn<LichThi,String> maHPColumn, TableColumn<LichThi,String> tenHPColumn ,
                      TableColumn<LichThi,String> ghiChuColumn, TableColumn<LichThi,String> nhomColumn,
                      TableColumn<LichThi,String> dotMoColumn, TableColumn<LichThi,String> tuanColumn,
                      TableColumn<LichThi,String> thuColumn, TableColumn<LichThi, Date> ngayThiColumn,
                      TableColumn<LichThi,String> kipColumn, TableColumn<LichThi,Integer> sldkColumn,
                      TableColumn<LichThi,String> phongColumn, ObservableList<LichThi> lichThiList,
                      ComboBox<String> hocKyLT) {
        ArrayList<LichThi> lichThis = null;
        try {
            lichThis = dataBaseController.testScheduleList("LichThi" + hocKyLT.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(lichThis != null){
            lichThiList = FXCollections.observableArrayList(lichThis);
            maLopColumn.setCellValueFactory(new PropertyValueFactory<LichThi,Integer>("maLop"));
            maHPColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("maHP"));
            tenHPColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("tenHP"));
            ghiChuColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("ghiChu"));
            nhomColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("nhom"));
            dotMoColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("dotMo"));
            tuanColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("tuan"));
            thuColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("thu"));
            ngayThiColumn.setCellValueFactory(new PropertyValueFactory<LichThi, Date>("ngayThi"));
            kipColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("kip"));
            sldkColumn.setCellValueFactory(new PropertyValueFactory<LichThi,Integer>("SLDK"));
            phongColumn.setCellValueFactory(new PropertyValueFactory<LichThi,String>("phong"));
            testScheduleTableView.setItems(lichThiList);
        } else {
            testScheduleTableView.setItems(null);
        }
    }
}
