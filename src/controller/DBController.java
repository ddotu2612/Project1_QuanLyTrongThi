package controller;

import com.sun.xml.bind.v2.runtime.output.C14nXmlOutput;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBController {
    //Kiểm tra sự tồn tại của một bảng bất kỳ
    public boolean checkExistTable(String tableName) throws SQLException {
        if(tableName == null) return true;
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String query = "SELECT COUNT(*) dem FROM information_schema.tables WHERE table_schema='dbo' AND table_name = '"+tableName+"'";
        var prepare=conn.prepareStatement(query);
        var rs=prepare.executeQuery();
        rs.next();
        boolean exists = rs.getInt("dem") > 0;
        // Close connection, statement, and result set.
        return exists;
    }
    //Đưa ra danh sách lịch thi từ database
    public ArrayList<TestSchedule> testScheduleList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<TestSchedule> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName;
        PreparedStatement prepare = null;
        try {
            prepare = conn.prepareStatement(sql);
            var result = prepare.executeQuery();
            while(result.next()){
                int maLop=result.getInt("maLop");
                String maHP=result.getString("maHP");
                String tenHP=result.getString("tenHP");
                String ghiChu=result.getString("ghiChu");
                String nhom=result.getString("nhom");
                String dotMo=result.getString("dotMo");
                String tuan=result.getString("tuan");
                String thu=result.getString("thu");
                Date ngayThi=result.getDate("ngayThi");
                String kip=result.getString("kip");
                int SLDK=result.getInt("SLDK");
                String phong=result.getString("phong");
                TestSchedule testSchedule=new TestSchedule(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                list.add(testSchedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //Thêm một lịch thi đơn vào database
    public boolean addTestScheduleToDatabase(TestSchedule testSchedule,String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1,testSchedule.getMaLop());
        prepare.setString(2,testSchedule.getMaHP());
        prepare.setString(3,testSchedule.getTenHP());
        prepare.setString(4,testSchedule.getGhiChu());
        prepare.setString(5,testSchedule.getNhom());
        prepare.setString(6,testSchedule.getDotMo());
        prepare.setString(7,testSchedule.getTuan());
        prepare.setString(8,testSchedule.getThu());
        prepare.setDate(9,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
        prepare.setString(10,testSchedule.getKip());
        prepare.setInt(11,testSchedule.getSLDK());
        prepare.setString(12,testSchedule.getPhong());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }
    //Update lịch thi vào database
    public boolean updateTestScheduleToDatabase(String tableName,TestSchedule testSchedule,TestSchedule testSchedule1) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="UPDATE "+tableName+" SET maLop=?,maHP=?,tenHP=?,ghiChu=?,nhom=?,dotMo=?,tuan=?,thu=?,ngayThi=?,kip=?,SLDK=?,phong=?"+
                " where maLop=? and maHP=? and tenHP=? and ghiChu=? and nhom=? and dotMo=? and tuan=? and thu=? and ngayThi=? and kip=? and SLDK=? and phong=? ";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1,testSchedule.getMaLop());
        prepare.setString(2,testSchedule.getMaHP());
        prepare.setString(3,testSchedule.getTenHP());
        prepare.setString(4,testSchedule.getGhiChu());
        prepare.setString(5,testSchedule.getNhom());
        prepare.setString(6,testSchedule.getDotMo());
        prepare.setString(7,testSchedule.getTuan());
        prepare.setString(8,testSchedule.getThu());
        prepare.setDate(9,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
        prepare.setString(10,testSchedule.getKip());
        prepare.setInt(11,testSchedule.getSLDK());
        prepare.setString(12,testSchedule.getPhong());
        prepare.setInt(13,testSchedule1.getMaLop());
        prepare.setString(14,testSchedule1.getMaHP());
        prepare.setString(15,testSchedule1.getTenHP());
        prepare.setString(16,testSchedule1.getGhiChu());
        prepare.setString(17,testSchedule1.getNhom());
        prepare.setString(18,testSchedule1.getDotMo());
        prepare.setString(19,testSchedule1.getTuan());
        prepare.setString(20,testSchedule1.getThu());
        prepare.setDate(21,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule1.getNgayThi())));
        prepare.setString(22,testSchedule1.getKip());
        prepare.setInt(23,testSchedule1.getSLDK());
        prepare.setString(24,testSchedule1.getPhong());
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }
    //Tìm kiếm thông tin lịch thi
    public ArrayList<TestSchedule> searchTestScheduleFromDatabase(int maLop,String tableName) throws SQLException {
        ArrayList<TestSchedule> list=new ArrayList<>();
        Connection conn=new DBConnection().getConnection();
        String sql="select * from "+tableName+" where maLop= ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1,maLop);
        var result=prepare.executeQuery();
        while(result.next()){
            int maLop1=result.getInt("maLop");
            String maHP=result.getString("maHP");
            String tenHP=result.getString("tenHP");
            String ghiChu=result.getString("ghiChu");
            String nhom=result.getString("nhom");
            String dotMo=result.getString("dotMo");
            String tuan=result.getString("tuan");
            String thu=result.getString("thu");
            Date ngayThi=result.getDate("ngayThi");
            String kip=result.getString("kip");
            int SLDK=result.getInt("SLDK");
            String phong=result.getString("phong");
            TestSchedule testSchedule=new TestSchedule(maLop1,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
            list.add(testSchedule);
        }
        return list;
    }
    //Xóa một lịch thi trong danh sách
    public boolean deleteTestScheduleFromDatabase(TestSchedule testSchedule,String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="delete from "+tableName+" where maLop=? and maHP=? and tenHP=? and ghiChu=? and nhom=? and dotMo=? and tuan=? and thu=? and ngayThi=? and kip=? and SLDK=? and phong=? ";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1,testSchedule.getMaLop());
        prepare.setString(2,testSchedule.getMaHP());
        prepare.setString(3,testSchedule.getTenHP());
        prepare.setString(4,testSchedule.getGhiChu());
        prepare.setString(5,testSchedule.getNhom());
        prepare.setString(6,testSchedule.getDotMo());
        prepare.setString(7,testSchedule.getTuan());
        prepare.setString(8,testSchedule.getThu());
        prepare.setDate(9,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
        prepare.setString(10,testSchedule.getKip());
        prepare.setInt(11,testSchedule.getSLDK());
        prepare.setString(12,testSchedule.getPhong());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    //giảng viên


    //Đưa ra danh sách lịch thi từ database
    public ArrayList<Lecturers> lecturerList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<Lecturers> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName;
        PreparedStatement prepare = null;
        try {
            prepare = conn.prepareStatement(sql);
            var result=prepare.executeQuery();
            while(result.next()){
                String nameLecturer=result.getString("hoTen");
                String faculty=result.getString("boMon");
                String phoneNumber=result.getString("phone");
                String email=result.getString("email");
                String workPlace=result.getString("phong");
                int maLop=result.getInt("maLop");
                Lecturers lecturers=new Lecturers(nameLecturer,faculty,phoneNumber,email,workPlace,maLop);
                list.add(lecturers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addLecturerToDatabase(Lecturers lecturers, String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?)";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,lecturers.getNameLecturer());
        prepare.setString(2,lecturers.getFaculty());
        prepare.setLong(3, Long.parseLong(lecturers.getPhoneNumber()));
        prepare.setString(4,lecturers.getEmail());
        prepare.setString(5,lecturers.getWorkPlace());
        prepare.setInt(6,lecturers.getMaLop());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    public boolean updateeLecturerToDatabase(String tableName, Lecturers lecturers, Lecturers lecturers1) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="UPDATE "+tableName+" SET hoTen=? ,boMon=?,phone=?,email=?,phong=?,maLop=? where hoTen=? and boMon=? and phone=? and email=? and phong=? and maLop=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,lecturers1.getNameLecturer());
        prepare.setString(2,lecturers1.getFaculty());
        prepare.setString(3,lecturers1.getPhoneNumber());
        prepare.setString(4,lecturers1.getEmail());
        prepare.setString(5,lecturers1.getWorkPlace());
        prepare.setInt(6,lecturers1.getMaLop());
        prepare.setString(7,lecturers.getNameLecturer());
        prepare.setString(8,lecturers.getFaculty());
        prepare.setString(9,lecturers.getPhoneNumber());
        prepare.setString(10,lecturers.getEmail());
        prepare.setString(11,lecturers.getWorkPlace());
        prepare.setInt(12,lecturers.getMaLop());
       
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }

    public ArrayList<Lecturers> searchLecturerFromDatabase(String hoTen, String tableName) throws SQLException {
        ArrayList<Lecturers> list=new ArrayList<>();
        Connection conn=new DBConnection().getConnection();
        String sql="select * from "+tableName+" where hoTen= ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,hoTen);
        var result=prepare.executeQuery();
        while(result.next()){
            String nameLecturer=result.getString("hoTen");
            String faculty=result.getString("boMon");
            String phoneNumber=result.getString("phone");
            String email=result.getString("email");
            String workPlace=result.getString("phong");
            int maLop=result.getInt("maLop");
            Lecturers lecturers=new Lecturers(nameLecturer,faculty,phoneNumber,email,workPlace,maLop);
            list.add(lecturers);
        }
        return list;
    }

    public boolean deleteLecturerFromDatabase(Lecturers lecturers, String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="delete from "+tableName+" where  hoTen=? and boMon=? and phone=? and email=? and phong=? and maLop=? ";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,lecturers.getNameLecturer());
        prepare.setString(2,lecturers.getFaculty());
        prepare.setLong(3, Long.parseLong(lecturers.getPhoneNumber()));
        prepare.setString(4,lecturers.getEmail());
        prepare.setString(5,lecturers.getWorkPlace());
        prepare.setInt(6,lecturers.getMaLop());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }
//Giám thị
    public ArrayList<Supervisor> supervisorList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<Supervisor> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName;
        PreparedStatement prepare ;
        try {
            prepare = conn.prepareStatement(sql);
            var result=prepare.executeQuery();
            while(result.next()){
                String nameLecturer=result.getString("hoTen");
                String faculty=result.getString("boMon");
                String phoneNumber=result.getString("phone");
                String email=result.getString("email");
                String workPlace=result.getString("phong");
                Supervisor supervisor=new Supervisor(nameLecturer,faculty,phoneNumber,email,workPlace);
                list.add(supervisor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addSupervisorToDatabase(Supervisor supervisor, String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="INSERT INTO "+tableName+" VALUES(?,?,?,?,?)";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,supervisor.getNameLecturer());
        prepare.setString(2,supervisor.getFaculty());
        prepare.setLong(3, Long.parseLong(supervisor.getPhoneNumber()));
        prepare.setString(4,supervisor.getEmail());
        prepare.setString(5,supervisor.getWorkPlace());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    public boolean updateeSupervisorToDatabase(String tableName, Supervisor supervisor, Supervisor supervisor1) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="UPDATE "+tableName+" SET hoTen=? ,boMon=?,phone=?,email=?,phong=? where hoTen=? and boMon=? and phone=? and email=? and phong=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,supervisor1.getNameLecturer());
        prepare.setString(2,supervisor1.getFaculty());
        prepare.setString(3,supervisor1.getPhoneNumber());
        prepare.setString(4,supervisor1.getEmail());
        prepare.setString(5,supervisor1.getWorkPlace());
        prepare.setString(6,supervisor.getNameLecturer());
        prepare.setString(7,supervisor.getFaculty());
        prepare.setString(8,supervisor.getPhoneNumber());
        prepare.setString(9,supervisor.getEmail());
        prepare.setString(10,supervisor.getWorkPlace());
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }

    public Supervisor searchSupervisorFromDatabase(String hoTen, String tableName) throws SQLException {
        //ArrayList<Supervisor> list=new ArrayList<>();
        Supervisor supervisor=null;
        Connection conn=new DBConnection().getConnection();
        String sql="select * from "+tableName+" where hoTen= ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,hoTen);
        var result=prepare.executeQuery();
        if(result.next()){
            String nameLecturer=result.getString("hoTen");
            String faculty=result.getString("boMon");
            String phoneNumber=result.getString("phone");
            String email=result.getString("email");
            String workPlace=result.getString("phong");
            supervisor=new Supervisor(nameLecturer,faculty,phoneNumber,email,workPlace);
        }
        return supervisor;
    }

    public boolean deleteSupervisorFromDatabase(Supervisor supervisor, String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="delete from "+tableName+" where  hoTen=? and boMon=? and phone=? and email=? and phong=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,supervisor.getNameLecturer());
        prepare.setString(2,supervisor.getFaculty());
        prepare.setLong(3, Long.parseLong(supervisor.getPhoneNumber()));
        prepare.setString(4,supervisor.getEmail());
        prepare.setString(5,supervisor.getWorkPlace());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    public void addPhanCong(TestSchedule testSchedule, ArrayList<Supervisor> list, String tableNamePhanCong) throws SQLException {
        String sql="insert into "+ tableNamePhanCong+" values (? , ? , ? , ?, ?)";
        var prepare=new DBConnection().getConnection().prepareStatement(sql);
        if(list.size()==1){
            prepare.setInt(1,testSchedule.getMaLop());
            prepare.setString(2,list.get(0).getNameLecturer());
            prepare.setString(3,"Không có");
            prepare.setDate(4,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
            prepare.setString(5,testSchedule.getKip());
        }else{
            prepare.setInt(1,testSchedule.getMaLop());
            prepare.setString(2,list.get(0).getNameLecturer());
            prepare.setString(3,list.get(1).getNameLecturer());
            prepare.setDate(4,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(testSchedule.getNgayThi())));
            prepare.setString(5,testSchedule.getKip());
        }
        prepare.executeUpdate();
    }

    //phân công
    public ArrayList<InfoTrongThi> trongThiList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<InfoTrongThi> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName;
        PreparedStatement prepare ;
        prepare = conn.prepareStatement(sql);
        var result=prepare.executeQuery();
        while(result.next()){
            int maLop=result.getInt("maLop");
            String giamThi1=result.getString("giamThi1");
            String giamThi2=result.getString("giamThi2");
            //Date date=new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("ngayThi").toString());
            Date ngayThi=result.getDate("ngayThi");
            String kipThi=result.getString("kipThi");
            InfoTrongThi infoTrongThi=new InfoTrongThi(maLop,giamThi1,giamThi2,ngayThi,kipThi);
            list.add(infoTrongThi);
        }
        return list;
    }

    public ArrayList<InfoTrongThi> searchInfoTrongThiFromDatabase(int maLop, String tableName) throws SQLException {
        ArrayList<InfoTrongThi> list=new ArrayList<>();
        Connection conn=new DBConnection().getConnection();
        String sql="select * from "+tableName+" where maLop= ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1,maLop);
        var result=prepare.executeQuery();
        while(result.next()){
            int maLop1=result.getInt("maLop");
            String giamThi1=result.getString("giamThi1");
            String giamThi2=result.getString("giamThi2");
            //Date date=new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("ngayThi").toString());
            Date ngayThi=result.getDate("ngayThi");
            String kipThi=result.getString("kipThi");
            InfoTrongThi infoTrongThi=new InfoTrongThi(maLop1,giamThi1,giamThi2,ngayThi,kipThi);
            list.add(infoTrongThi);
        }
        return list;
    }
    //Quản lý phân công trông thi
    public ArrayList<DonGia> donGiaList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<DonGia> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName;
        PreparedStatement prepare ;
        try {
            prepare = conn.prepareStatement(sql);
            var result=prepare.executeQuery();
            while(result.next()){
                String tenDonGia=result.getString("tenDonGia");
                Long gia=result.getLong("gia");
                DonGia donGia=new DonGia(tenDonGia,gia);
                list.add(donGia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//Quan ly kinh phi
    public ArrayList<KinhPhi> kinhPhiList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<KinhPhi> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName;
        PreparedStatement prepare ;
        try {
            prepare = conn.prepareStatement(sql);
            var result=prepare.executeQuery();
            while(result.next()){
                int maLop=result.getInt("maLop");
                String tenGV=result.getString("tenGV");
                long inAn=result.getLong("inAn");
                long phoTo=result.getLong("phoTo");
                long toChucThi=result.getLong("toChucThi");
                long kinhPhiGT=result.getLong("kinhPhiGT");
                int checkThanhToan=result.getInt("checkThanhToan");
                String ghiChu=result.getString("ghiChu");
                KinhPhi kinhPhi=new KinhPhi(maLop,tenGV,inAn,phoTo,toChucThi,kinhPhiGT,checkThanhToan,ghiChu);
                list.add(kinhPhi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addDonGia(String tenDonGia, long gia, String tableName) throws SQLException {
        if(!checkExistTable(tableName)){
            String sql="create table "+tableName+" (tenDonGia nvarchar(50),gia bigint);";
            var prepare=new DBConnection().getConnection().prepareStatement(sql);
            prepare.executeUpdate();
        }
        if(!new DataControler().isValidDonGia(tenDonGia,tableName)) return false;
        String sql="insert into "+tableName+ " values(?,?)";
        var prepare = new DBConnection().getConnection().prepareStatement(sql);
        prepare.setString(1,tenDonGia);
        prepare.setLong(2,gia);
        if(prepare.executeUpdate()>0) return true;
        return false;
    }


    public boolean updateDonGia(DonGia donGia, DonGia donGia1, String tableName) throws SQLException {
        Connection conn=new DBConnection().getConnection();
        String sql="UPDATE "+tableName+" SET tenDonGia=? ,gia=? where tenDonGia=? and gia=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,donGia1.getTenDonGia());
        prepare.setLong(2,donGia1.getGia());
        prepare.setString(3,donGia.getTenDonGia());
        prepare.setLong(4,donGia.getGia());
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }

    public String searchNameLecturer(int maLop, String tableName) throws SQLException {
        String sql="select * from "+tableName+" where maLop=? ";
        var prepare=new DBConnection().getConnection().prepareStatement(sql);
        prepare.setInt(1,maLop);
        var res=prepare.executeQuery();
        if(res.next()) return res.getString("hoTen");
        return null;
    }

    public long getGia(String tenDonGia, String tableNameDG) throws SQLException {
        String sql="select gia from " +tableNameDG+" where tenDonGia=?";
        var prepare=new DBConnection().getConnection().prepareStatement(sql);
        prepare.setString(1,tenDonGia);
        var res=prepare.executeQuery();
        if(res.next()) return res.getLong("gia");
        return 0;
    }

    public boolean addKinhPhi(KinhPhi kinhPhi, String tableNameKP) throws SQLException {
        String sql="insert into "+tableNameKP+ " values(?,?,?,?,?,?,?,?);";
        var prepare=new DBConnection().getConnection().prepareStatement(sql);
        prepare.setInt(1,kinhPhi.getMaLop());
        prepare.setString(2,kinhPhi.getTenGV());
        prepare.setLong(3,kinhPhi.getInAn());
        prepare.setLong(4,kinhPhi.getPhoTo());
        prepare.setLong(5,kinhPhi.getToChucThi());
        prepare.setLong(6,kinhPhi.getKinhPhiGT());
        prepare.setInt(7,kinhPhi.getCheckThanhToan());
        prepare.setString(8,kinhPhi.getGhiChu());
        var res=prepare.executeUpdate();
        if(res>0 ) return true;
        return false;
    }

    public ArrayList<KinhPhi> searchKinhPhiList(int maLop, String tableName) throws SQLException {
        ArrayList<KinhPhi> list=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        Connection conn=dbConnection.getConnection();
        String sql="select * from "+tableName+" where maLop=?";
        PreparedStatement prepare ;
        try {
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1,maLop);
            var result=prepare.executeQuery();
            while(result.next()){
                int maLop1=result.getInt("maLop");
                String tenGV=result.getString("tenGV");
                long inAn=result.getLong("inAn");
                long phoTo=result.getLong("phoTo");
                long toChucThi=result.getLong("toChucThi");
                long kinhPhiGT=result.getLong("kinhPhiGT");
                int checkThanhToan=result.getInt("checkThanhToan");
                String ghiChu=result.getString("ghiChu");
                KinhPhi kinhPhi=new KinhPhi(maLop1,tenGV,inAn,phoTo,toChucThi,kinhPhiGT,checkThanhToan,ghiChu);
                list.add(kinhPhi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
