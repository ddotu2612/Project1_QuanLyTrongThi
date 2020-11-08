package controller;

import model.Lecturers;
import model.Supervisor;
import model.TestSchedule;
import org.apache.poi.ss.usermodel.DateUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DataControler {
    DBConnection dbConnection=new DBConnection();
    //Kiểm tra tính hơp lệ của tài khoản
    public boolean isValidTK(String userName,String password,String role) {
        String sql = "select * from dbo.TaikhoanU where userName= ? and password=? and role=?";

        try {
            var prepare = new DBConnection().getConnection().prepareStatement(sql);
            prepare.setString(1, userName);
            prepare.setString(2, password);
            prepare.setString(3, role);
            var result = prepare.executeQuery();
//            if(result.next()) {
//                return true;
//            }
            if (result.next()) {
                //System.out.println(result.getString("password"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //Kiểm tra tránh trùng lặp dữ liệu lịch thi
    public boolean isValidTestSchedule(TestSchedule testSchedule,String tableName) throws SQLException {
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName+" where maLop=? and maHP=? and tenHP=? and ghiChu=? and nhom=? and dotMo=? and tuan=? and thu=? and ngayThi=? and kip=? and SLDK=? and phong=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1,testSchedule.getMaLop());
        prepare.setString(2,testSchedule.getMaHP());
        prepare.setString(3,testSchedule.getTenHP());
        prepare.setString(4,testSchedule.getGhiChu());
        prepare.setString(5,testSchedule.getNhom());
        prepare.setString(6,testSchedule.getDotMo());
        prepare.setString(7,testSchedule.getTuan());
        prepare.setString(8,testSchedule.getThu());
        prepare.setDate(9,Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
        prepare.setString(10,testSchedule.getKip());
        prepare.setInt(11,testSchedule.getSLDK());
        prepare.setString(12,testSchedule.getPhong());
        var res=prepare.executeQuery();
        if(res.next()) return false;
        return true;
    }
    //Kiểm tra xem bảng lịch thi đã bị khóa hay chưa
    public boolean isCheckDataLock(String tableName) throws SQLException {
        Connection conn=dbConnection.getConnection();
        String sql="select * from dbo.LockData where tableName = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,tableName);
        var res=prepare.executeQuery();
        if(res.next() && res.getInt("dataLock")==1) return false;
        return true;
    }

    public boolean isValidLecturer(Lecturers lecturers, String tableName) throws SQLException {
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName+" where hoTen =? and boMon =? and phone =? and email =? and phong =? and maLop =?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,lecturers.getNameLecturer());
        prepare.setString(2,lecturers.getFaculty());
        prepare.setLong(3, Long.parseLong(lecturers.getPhoneNumber()));
        prepare.setString(4,lecturers.getEmail());
        prepare.setString(5,lecturers.getWorkPlace());
        prepare.setInt(6,lecturers.getMaLop());
        var res=prepare.executeQuery();
        if(res.next()) return false;
        return true;
    }

    public boolean isValidSupervisor(Supervisor supervisor, String tableName) throws SQLException {
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName+" where hoTen =? and boMon =? and phone =? and email =? and phong =?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,supervisor.getNameLecturer());
        prepare.setString(2,supervisor.getFaculty());
        prepare.setLong(3, Long.parseLong(supervisor.getPhoneNumber()));
        prepare.setString(4,supervisor.getEmail());
        prepare.setString(5,supervisor.getWorkPlace());
        var res=prepare.executeQuery();
        if(res.next()) return false;
        return true;
    }

    public boolean isCheckGT(String nameLecturer, String tableNamePhanCong,TestSchedule testSchedule) throws SQLException {
        DBController dbController=new DBController();
        if(!dbController.checkExistTable(tableNamePhanCong)){
            String sql="Create table "+tableNamePhanCong+" (maLop int ,giamThi1 nvarchar(50),giamThi2 nvarchar(50),ngayThi date,kipThi nvarchar(50));";
            var prepare=new DBConnection().getConnection().prepareStatement(sql);
            prepare.executeUpdate();
        }
        String sql1="select * from "+tableNamePhanCong+" where ( giamThi1=? or giamThi2=? ) and kipThi=? and ngayThi=?";
        var prepare=new DBConnection().getConnection().prepareStatement(sql1);
        prepare.setString(1,nameLecturer);
        prepare.setString(2,nameLecturer);
        prepare.setString(3,testSchedule.getKip());
        prepare.setDate(4, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
        var result=prepare.executeQuery();
        if(result.next()) return true;
        return false;
    }

    public boolean isValidDonGia(String tenDonGia, String tableName) throws SQLException {
        String sql="select * from "+tableName+" where tenDonGia=? ";
        var prepare=new DBConnection().getConnection().prepareStatement(sql);
        prepare.setString(1,tenDonGia);
        if(prepare.executeQuery().next()) return false;
        return true;
    }
}
