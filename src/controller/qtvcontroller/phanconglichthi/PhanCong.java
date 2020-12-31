package controller.phanconglichthi;

import controller.DBController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import model.Supervisor;
import model.TestSchedule;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhanCong {
    static DBController dbController = new DBController();
    static DataControler dataControler = new DataControler();
    public static void phanCong(ComboBox<String> hocKyPC) throws SQLException {
        if(hocKyPC.getValue().length() != 0 && dbController.checkExistTable("GiamThi" +
                hocKyPC.getValue()) && dbController.checkExistTable("LichThi" + hocKyPC.getValue())){
            if(!dataControler.isCheckDataLock("GiamThi" + hocKyPC.getValue())) {
                String tableNameGT = "GiamThi" + hocKyPC.getValue();
                String tableNameLT = "LichThi" + hocKyPC.getValue();
                String tableNamePhanCong="PhanCong" + hocKyPC.getValue();
                if(!dbController.checkExistTable(tableNamePhanCong)){
                    ArrayList<Supervisor> supervisors = dbController.supervisorList(tableNameGT);
                    ArrayList<TestSchedule> testSchedules = dbController.testScheduleList(tableNameLT);
                    int testNumber = testSchedules.size();
                    int GTNumber = supervisors.size();
                    for(int j = 0; j < testNumber; j++){
                        int sldk = testSchedules.get(j).getSLDK();
                        int giamThiNumber = (sldk>=60) ? 2 : 1;
                        int count = 0;
                        ArrayList<Supervisor> list = new ArrayList<>();
                        for(int i = 0 ; i < GTNumber; i++){
                            if(!dataControler.isCheckGT(supervisors.get(i).getNameLecturer(),testSchedules.get(j),
                                    hocKyPC.getValue())){
                                list.add(supervisors.get(i));
                                count++;
                            }
                            if(count == giamThiNumber) break;
                        }
                        dbController.addPhanCong(testSchedules.get(j), list , tableNamePhanCong);
                    }
                    Information.ThongBaoThongTin("Bạn đã phân công thành công");
                }else{
                    Error.ThongBaoLoi("Bạn đã phân công giám thị cho học kỳ này");
                }
            } else {
                Error.ThongBaoLoi("Chưa thể phân công khi chưa chốt danh sách giám thị");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập tên học kỳ bạn muốn phân công hoặc dữ liệu kỳ này không tồn tại");
        }
    }
}
