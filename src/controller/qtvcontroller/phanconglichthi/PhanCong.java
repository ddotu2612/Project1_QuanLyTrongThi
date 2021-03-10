package controller.qtvcontroller.phanconglichthi;

import controller.DataBaseController;
import controller.DataControler;
import javafx.scene.control.ComboBox;
import model.GiamThi;
import model.LichThi;
import view.alert.Error;
import view.alert.Information;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhanCong {
    static DataBaseController dataBaseController = new DataBaseController();
    static DataControler dataControler = new DataControler();
    public static void phanCong(ComboBox<String> hocKyPC) throws SQLException {
        if(hocKyPC.getValue().length() != 0 && dataBaseController.checkExistTable("GiamThi" +
                hocKyPC.getValue()) && dataBaseController.checkExistTable("LichThi" + hocKyPC.getValue())){
            if(!dataControler.isCheckDataLock("GiamThi" + hocKyPC.getValue())
                   && !dataControler.isCheckDataLock("LichThi" + hocKyPC.getValue())
             && !dataControler.isCheckDataLock("GiangVien" + hocKyPC.getValue())) {
                String tableNameGT = "GiamThi" + hocKyPC.getValue();
                String tableNameLT = "LichThi" + hocKyPC.getValue();
                String tableNamePhanCong="PhanCong" + hocKyPC.getValue();
                if(!dataBaseController.checkExistTable(tableNamePhanCong)){
                    ArrayList<GiamThi> giamThis = dataBaseController.supervisorList(tableNameGT);
                    ArrayList<LichThi> lichThis = dataBaseController.testScheduleList(tableNameLT);
                    int testNumber = lichThis.size();
                    int GTNumber = giamThis.size();
                    for(int j = 0; j < testNumber; j++){
                        int sldk = lichThis.get(j).getSLDK();
                        int giamThiNumber = (sldk >= 60) ? 2 : 1;
                        int count = 0;
                        ArrayList<GiamThi> list = new ArrayList<>();
                        for(int i = 0 ; i < GTNumber; i++){
                            if(!dataControler.isCheckGT(giamThis.get(i).getNameLecturer(), lichThis.get(j),
                                    hocKyPC.getValue()) ){
                                if(list.size() == 0) {
                                    list.add(giamThis.get(i));
                                    count++;
                                    i = 0;
                                } else {
                                    if(!giamThis.get(i).getNameLecturer().equals(list.get(0).getNameLecturer())) {
                                        list.add(giamThis.get(i));
                                        count ++;
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            if(count == giamThiNumber) break;
                        }
                        dataBaseController.addPhanCong(lichThis.get(j), list , tableNamePhanCong);
                    }
                    Information.ThongBaoThongTin("Bạn đã phân công thành công");
                }else{
                    Error.ThongBaoLoi("Bạn đã phân công giám thị cho học kỳ này");
                }
            } else {
                Error.ThongBaoLoi("Chưa thể phân công khi chưa chốt danh sách giám thị, giảng viên, lịch thi");
            }
        }else{
            Error.ThongBaoLoi("Bạn chưa nhập tên học kỳ bạn muốn phân công hoặc dữ liệu kỳ này không tồn tại");
        }
    }
}
