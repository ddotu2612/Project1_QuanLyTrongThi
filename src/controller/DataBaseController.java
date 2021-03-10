package controller;
import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseController {
    //Kiểm tra sự tồn tại của một bảng bất kỳ
    public boolean checkExistTable(String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String query = "SELECT COUNT(*) dem FROM information_schema.tables WHERE table_schema='dbo' AND table_name = '" + tableName+ "'";
        var prepare=conn.prepareStatement(query);
        var rs=prepare.executeQuery();
        rs.next();
        boolean exists = rs.getInt("dem") > 0;
        // Close connection, statement, and result set.
        return exists;
    }
    //Đưa ra danh sách lịch thi từ database
    public ArrayList<LichThi> testScheduleList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<LichThi> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
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
                LichThi lichThi =new LichThi(maLop,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
                list.add(lichThi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //Thêm một lịch thi đơn vào database
    public boolean addTestScheduleToDatabase(LichThi lichThi, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1, lichThi.getMaLop());
        prepare.setString(2, lichThi.getMaHP());
        prepare.setString(3, lichThi.getTenHP());
        prepare.setString(4, lichThi.getGhiChu());
        prepare.setString(5, lichThi.getNhom());
        prepare.setString(6, lichThi.getDotMo());
        prepare.setString(7, lichThi.getTuan());
        prepare.setString(8, lichThi.getThu());
        prepare.setDate(9,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
        prepare.setString(10, lichThi.getKip());
        prepare.setInt(11, lichThi.getSLDK());
        prepare.setString(12, lichThi.getPhong());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }
    //Update lịch thi vào database
    public boolean updateTestScheduleToDatabase(String tableName, LichThi lichThi, LichThi lichThi1) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="UPDATE "+tableName+" SET maLop=?,maHP=?,tenHP=?,ghiChu=?,nhom=?,dotMo=?,tuan=?,thu=?,ngayThi=?,kip=?,SLDK=?,phong=?"+
                " where maLop=? and maHP=? and tenHP=? and ghiChu=? and nhom=? and dotMo=? and tuan=? and thu=? and ngayThi=? and kip=? and SLDK=? and phong=? ";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1, lichThi.getMaLop());
        prepare.setString(2, lichThi.getMaHP());
        prepare.setString(3, lichThi.getTenHP());
        prepare.setString(4, lichThi.getGhiChu());
        prepare.setString(5, lichThi.getNhom());
        prepare.setString(6, lichThi.getDotMo());
        prepare.setString(7, lichThi.getTuan());
        prepare.setString(8, lichThi.getThu());
        prepare.setDate(9,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
        prepare.setString(10, lichThi.getKip());
        prepare.setInt(11, lichThi.getSLDK());
        prepare.setString(12, lichThi.getPhong());
        prepare.setInt(13, lichThi1.getMaLop());
        prepare.setString(14, lichThi1.getMaHP());
        prepare.setString(15, lichThi1.getTenHP());
        prepare.setString(16, lichThi1.getGhiChu());
        prepare.setString(17, lichThi1.getNhom());
        prepare.setString(18, lichThi1.getDotMo());
        prepare.setString(19, lichThi1.getTuan());
        prepare.setString(20, lichThi1.getThu());
        prepare.setDate(21,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi1.getNgayThi())));
        prepare.setString(22, lichThi1.getKip());
        prepare.setInt(23, lichThi1.getSLDK());
        prepare.setString(24, lichThi1.getPhong());
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }
    //Tìm kiếm thông tin lịch thi
    public ArrayList<LichThi> searchTestScheduleFromDatabase(int maLop, String tableName) throws SQLException {
        ArrayList<LichThi> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
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
            LichThi lichThi =new LichThi(maLop1,maHP,tenHP,ghiChu,nhom,dotMo,tuan,thu,ngayThi,kip,SLDK,phong);
            list.add(lichThi);
        }
        return list;
    }
    //Xóa một lịch thi trong danh sách
    public boolean deleteTestScheduleFromDatabase(LichThi lichThi, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="delete from "+tableName+" where maLop=? and maHP=? and tenHP=? and ghiChu=? and nhom=? and dotMo=? and tuan=? and thu=? and ngayThi=? and kip=? and SLDK=? and phong=? ";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1, lichThi.getMaLop());
        prepare.setString(2, lichThi.getMaHP());
        prepare.setString(3, lichThi.getTenHP());
        prepare.setString(4, lichThi.getGhiChu());
        prepare.setString(5, lichThi.getNhom());
        prepare.setString(6, lichThi.getDotMo());
        prepare.setString(7, lichThi.getTuan());
        prepare.setString(8, lichThi.getThu());
        prepare.setDate(9,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
        prepare.setString(10, lichThi.getKip());
        prepare.setInt(11, lichThi.getSLDK());
        prepare.setString(12, lichThi.getPhong());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    //giảng viên


    //Đưa ra danh sách lịch thi từ database
    public ArrayList<GiangVien> lecturerList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<GiangVien> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from " + tableName;
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
                GiangVien giangVien =new GiangVien(nameLecturer,faculty,phoneNumber,email,workPlace,maLop);
                list.add(giangVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addLecturerToDatabase(GiangVien giangVien, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?)";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giangVien.getNameLecturer());
        prepare.setString(2, giangVien.getFaculty());
        prepare.setLong(3, Long.parseLong(giangVien.getPhoneNumber()));
        prepare.setString(4, giangVien.getEmail());
        prepare.setString(5, giangVien.getWorkPlace());
        prepare.setInt(6, giangVien.getMaLop());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    public boolean updateeLecturerToDatabase(String tableName, GiangVien giangVien, GiangVien giangVien1) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="UPDATE "+tableName+" SET hoTen=? ,boMon=?,phone=?,email=?,phong=?,maLop=? where hoTen=? and boMon=? and phone=? and email=? and phong=? and maLop=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giangVien1.getNameLecturer());
        prepare.setString(2, giangVien1.getFaculty());
        prepare.setString(3, giangVien1.getPhoneNumber());
        prepare.setString(4, giangVien1.getEmail());
        prepare.setString(5, giangVien1.getWorkPlace());
        prepare.setInt(6, giangVien1.getMaLop());
        prepare.setString(7, giangVien.getNameLecturer());
        prepare.setString(8, giangVien.getFaculty());
        prepare.setString(9, giangVien.getPhoneNumber());
        prepare.setString(10, giangVien.getEmail());
        prepare.setString(11, giangVien.getWorkPlace());
        prepare.setInt(12, giangVien.getMaLop());
       
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }

    public ArrayList<GiangVien> searchLecturerFromDatabase(String hoTen, String tableName) throws SQLException {
        if(!new DataBaseController().checkExistTable(tableName)) return null;
        ArrayList<GiangVien> list = new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from " + tableName + " where hoTen = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,hoTen);
        var result = prepare.executeQuery();
        while(result.next()){
            String nameLecturer=result.getString("hoTen");
            String faculty=result.getString("boMon");
            String phoneNumber=result.getString("phone");
            String email=result.getString("email");
            String workPlace=result.getString("phong");
            int maLop=result.getInt("maLop");
            GiangVien giangVien =new GiangVien(nameLecturer,faculty,phoneNumber,email,workPlace,maLop);
            list.add(giangVien);
        }
        return list;
    }

    public boolean deleteLecturerFromDatabase(GiangVien giangVien, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="delete from "+tableName+" where  hoTen=? and boMon=? and phone=? and email=? and phong=? and maLop=? ";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giangVien.getNameLecturer());
        prepare.setString(2, giangVien.getFaculty());
        prepare.setLong(3, Long.parseLong(giangVien.getPhoneNumber()));
        prepare.setString(4, giangVien.getEmail());
        prepare.setString(5, giangVien.getWorkPlace());
        prepare.setInt(6, giangVien.getMaLop());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }
//Giám thị
    public ArrayList<GiamThi> supervisorList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<GiamThi> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from " + tableName;
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
                int soBuoi = result.getInt("soBuoi");
                GiamThi giamThi =new GiamThi(nameLecturer,faculty,phoneNumber,email,workPlace,soBuoi);
                list.add(giamThi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addSupervisorToDatabase(GiamThi giamThi, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="INSERT INTO "+tableName+" VALUES(?,?,?,?,?,?)";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giamThi.getNameLecturer());
        prepare.setString(2, giamThi.getFaculty());
        prepare.setLong(3, Long.parseLong(giamThi.getPhoneNumber()));
        prepare.setString(4, giamThi.getEmail());
        prepare.setString(5, giamThi.getWorkPlace());
        prepare.setInt(6, giamThi.getSoBuoiToiDa());
        var res=prepare.executeUpdate();
        if(res>0) return true;
        return false;
    }

    public boolean updateeSupervisorToDatabase(String tableName, GiamThi giamThi, GiamThi giamThi1) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="UPDATE "+tableName+" SET hoTen=? ,boMon=?,phone=?,email=?,phong=?,soBuoi = ? where hoTen=? and boMon=? and phone=? and email=? and phong=? and soBuoi = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giamThi1.getNameLecturer());
        prepare.setString(2, giamThi1.getFaculty());
        prepare.setString(3, giamThi1.getPhoneNumber());
        prepare.setString(4, giamThi1.getEmail());
        prepare.setString(5, giamThi1.getWorkPlace());
        prepare.setInt(6, giamThi1.getSoBuoiToiDa());
        prepare.setString(7, giamThi.getNameLecturer());
        prepare.setString(8, giamThi.getFaculty());
        prepare.setString(9, giamThi.getPhoneNumber());
        prepare.setString(10, giamThi.getEmail());
        prepare.setString(11, giamThi.getWorkPlace());
        prepare.setInt(12, giamThi.getSoBuoiToiDa());
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }

    public GiamThi searchSupervisorFromDatabase(String hoTen, String tableName) throws SQLException {
        //ArrayList<Supervisor> list=new ArrayList<>();
        GiamThi giamThi =null;
        Connection conn = DataBaseConnection.getInstance().getConnection();
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
            int soBuoi = result.getInt("soBuoi");
            giamThi =new GiamThi(nameLecturer,faculty,phoneNumber,email,workPlace,soBuoi);
        }
        return giamThi;
    }

    public boolean deleteSupervisorFromDatabase(GiamThi giamThi, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="delete from "+tableName+" where  hoTen=? and boMon=? and phone=? and email=? and phong=? and soBuoi = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giamThi.getNameLecturer());
        prepare.setString(2, giamThi.getFaculty());
        prepare.setLong(3, Long.parseLong(giamThi.getPhoneNumber()));
        prepare.setString(4, giamThi.getEmail());
        prepare.setString(5, giamThi.getWorkPlace());
        prepare.setInt(6, giamThi.getSoBuoiToiDa());
        var res=prepare.executeUpdate();
        if(res > 0) return true;
        return false;
    }

    public void addPhanCong(LichThi lichThi, ArrayList<GiamThi> list, String tableNamePhanCong) throws SQLException {
        String sql="insert into "+ tableNamePhanCong+" values (? , ? , ? , ?, ?)";
        Connection conn = DataBaseConnection.getInstance().getConnection();
        var prepare= conn.prepareStatement(sql);
        if(list.size() == 1){
            prepare.setInt(1, lichThi.getMaLop());
            prepare.setString(2,list.get(0).getNameLecturer());
            prepare.setString(3,"Không có");
            prepare.setDate(4,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
            prepare.setString(5, lichThi.getKip());
        }else{
            prepare.setInt(1, lichThi.getMaLop());
            prepare.setString(2,list.get(0).getNameLecturer());
            prepare.setString(3,list.get(1).getNameLecturer());
            prepare.setDate(4,java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
            prepare.setString(5, lichThi.getKip());
        }
        prepare.executeUpdate();
    }

    //phân công
    public ArrayList<ThongTinTrongThi> trongThiList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<ThongTinTrongThi> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from " + tableName;
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
            ThongTinTrongThi thongTinTrongThi =new ThongTinTrongThi(maLop,giamThi1,giamThi2,ngayThi,kipThi);
            list.add(thongTinTrongThi);
        }
        return list;
    }

    public ArrayList<ThongTinTrongThi> searchInfoTrongThiFromDatabase(int maLop, String tableName) throws SQLException {
        ArrayList<ThongTinTrongThi> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
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
            ThongTinTrongThi thongTinTrongThi =new ThongTinTrongThi(maLop1,giamThi1,giamThi2,ngayThi,kipThi);
            list.add(thongTinTrongThi);
        }
        return list;
    }

    //Quản lý phân công trông thi
    public ArrayList<DonGia> donGiaList(String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<DonGia> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from " + tableName;
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
        ArrayList<KinhPhi> list = new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql = "select * from " + tableName;
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
                String ghiChu=result.getString("nhom");
                KinhPhi kinhPhi=new KinhPhi(maLop,tenGV,inAn,phoTo,toChucThi,kinhPhiGT,checkThanhToan,ghiChu);
                list.add(kinhPhi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addDonGia(String tenDonGia, long gia, String tableName) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        if(!checkExistTable(tableName)){
            String sql="create table "+tableName+" (tenDonGia nvarchar(50),gia bigint);";
            var prepare= conn.prepareStatement(sql);
            prepare.executeUpdate();
        }
        if(!new DataControler().isValidDonGia(tenDonGia,tableName)) return false;
        String sql="insert into "+tableName+ " values(?,?)";
        var prepare = conn.prepareStatement(sql);
        prepare.setString(1,tenDonGia);
        prepare.setLong(2,gia);
        if(prepare.executeUpdate()>0) return true;
        return false;
    }


    public boolean updateDonGia(DonGia donGia, DonGia donGia1, String tableName) throws SQLException {
        if(!donGia.getTenDonGia().equals(donGia1.getTenDonGia())) return false;
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="UPDATE " + tableName + " SET tenDonGia = ? , gia = ? where tenDonGia = ? and gia = ?";
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
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from "+tableName+" where maLop=? ";
        var prepare= conn.prepareStatement(sql);
        prepare.setInt(1,maLop);
        var res=prepare.executeQuery();
        if(res.next()) return res.getString("hoTen");
        return null;
    }

    public long getGia(String tenDonGia, String tableNameDG) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select gia from " +tableNameDG+" where tenDonGia=?";
        var prepare= conn.prepareStatement(sql);
        prepare.setString(1,tenDonGia);
        var res=prepare.executeQuery();
        if(res.next()) return res.getLong("gia");
        return 0;
    }

    public boolean addKinhPhi(KinhPhi kinhPhi, String tableNameKP) throws SQLException {
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="insert into "+tableNameKP+ " values(?,?,?,?,?,?,?,?);";
        var prepare= conn.prepareStatement(sql);
        prepare.setInt(1,kinhPhi.getMaLop());
        prepare.setString(2,kinhPhi.getTenGV());
        prepare.setLong(3,kinhPhi.getInAn());
        prepare.setLong(4,kinhPhi.getPhoTo());
        prepare.setLong(5,kinhPhi.getToChucThi());
        prepare.setLong(6,kinhPhi.getKinhPhiGT());
        prepare.setInt(7,kinhPhi.getCheckThanhToan());
        prepare.setString(8,kinhPhi.getNhom());
        var res=prepare.executeUpdate();
        if(res>0 ) return true;
        return false;
    }

    public ArrayList<KinhPhi> searchKinhPhiList(int maLop, String tableName) throws SQLException {

        ArrayList<KinhPhi> list=new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from "+tableName+" where maLop=?";
        PreparedStatement prepare ;
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
            String nhom=result.getString("nhom");
            KinhPhi kinhPhi=new KinhPhi(maLop1,tenGV,inAn,phoTo,toChucThi,kinhPhiGT,checkThanhToan,nhom);
            list.add(kinhPhi);
        }
        return list;
    }
    //Báo cáo thống kê

    public ArrayList<BaoCao> BaoCaoGV(String name, String tableName) throws SQLException {
        if(!checkExistTable(tableName)) return null;
        ArrayList<BaoCao> list = new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql = "select * from " + tableName + " where tenGV = ?";
        PreparedStatement prepare ;
        try {
            prepare = conn.prepareStatement(sql);
            prepare.setString(1 , name);
            var result=prepare.executeQuery();
            //if(!result.next()) return null;
            while(result.next()){
                int maLop=result.getInt("maLop");
                long tongCP = result.getLong("phoTo") + result.getLong("toChucThi")
                        + result.getLong("kinhPhiGT");
                String cot2 = String.valueOf(tongCP);
                String cot3 = (result.getInt("checkThanhToan") == 1) ? "Đã thanh toán" : "Chưa thanh toán";
                BaoCao baoCao = new BaoCao(maLop, cot2, cot3);
                list.add(baoCao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<BaoCao> BaoCaoGT(String name, String hocKy) throws SQLException, ParseException {
        String tableName = "PhanCong" + hocKy;
        if(!checkExistTable(tableName)) return null;
        ArrayList<BaoCao> list = new ArrayList<>();
        Connection conn = DataBaseConnection.getInstance().getConnection();
        String sql="select * from " + tableName + " where giamThi1 = ? or giamThi2 = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, name);
        prepare.setString(2, name);
        var result=prepare.executeQuery();
        while(result.next()){
            int maLop=result.getInt("maLop");
            ArrayList<KinhPhi> kinhPhi = searchKinhPhiList(maLop, "KinhPhi" + hocKy);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(result.getDate("ngayThi").toString());
            String cot2 = (date.compareTo(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())) > 0) ? "Chưa tổ chức thi" : "Đã tổ chức thi";
            String cot3 = (kinhPhi.get(0).getCheckThanhToan() == 1) ? "Đã được thanh toán" : "Chưa được thanh toán";
            BaoCao baoCao = new BaoCao(maLop, cot2, cot3);
            list.add(baoCao);
        }
        return list;
    }
}
