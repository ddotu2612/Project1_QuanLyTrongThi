package controller.lichthicontroller;

import controller.DBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TestSchedule;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CapNhatLichThi {
    DBController dbController = new DBController();
    public void reset(TableView<TestSchedule> testScheduleTableView, TableColumn<TestSchedule,Integer> maLopColumn,
                      TableColumn<TestSchedule,String> maHPColumn, TableColumn<TestSchedule,String> tenHPColumn ,
                      TableColumn<TestSchedule,String> ghiChuColumn, TableColumn<TestSchedule,String> nhomColumn,
                      TableColumn<TestSchedule,String> dotMoColumn,  TableColumn<TestSchedule,String> tuanColumn,
                      TableColumn<TestSchedule,String> thuColumn,TableColumn<TestSchedule, Date> ngayThiColumn,
                      TableColumn<TestSchedule,String> kipColumn, TableColumn<TestSchedule,Integer> sldkColumn,
                      TableColumn<TestSchedule,String> phongColumn, ObservableList<TestSchedule> testScheduleList,
                       ComboBox<String> hocKyLT) {
        ArrayList<TestSchedule> testSchedules = null;
        try {
            testSchedules = dbController.testScheduleList("LichThi" + hocKyLT.getValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Config lichThi
        if(testSchedules != null){
            testScheduleList= FXCollections.observableArrayList(testSchedules);
            maLopColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("maLop"));
            maHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("maHP"));
            tenHPColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tenHP"));
            ghiChuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("ghiChu"));
            nhomColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("nhom"));
            dotMoColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("dotMo"));
            tuanColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("tuan"));
            thuColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("thu"));
            ngayThiColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule, Date>("ngayThi"));
            kipColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("kip"));
            sldkColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,Integer>("SLDK"));
            phongColumn.setCellValueFactory(new PropertyValueFactory<TestSchedule,String>("phong"));
            testScheduleTableView.setItems(testScheduleList);
        }
    }
}
