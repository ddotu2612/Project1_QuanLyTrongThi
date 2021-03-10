package controller;

import model.GiangVien;
import model.GiamThi;
import model.LichThi;
import view.alert.Error;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DataControler {
    Connection conn = DataBaseConnection.getInstance().getConnection();
    //Kiểm tra tính hơp lệ của tài khoản
    public boolean isValidTK(String userName, String password, String role, String tableName) {
        String sql = "select * from " + tableName + " where tenDN = ? and matKhau = ? and quyen = ?";
        try {
            var prepare = conn.prepareStatement(sql);
            prepare.setString(1, userName);
            prepare.setString(2, password);
            prepare.setString(3, role);
            var result = prepare.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            Error.ThongBaoLoi("Lỗi định dạng");
        }
        return false;
    }

    //Kiểm tra tránh trùng lặp dữ liệu lịch thi
    public boolean isValidTestSchedule(LichThi lichThi, String tableName) throws SQLException {
        String sql="select * from "+tableName+" where maLop=? and maHP=? and tenHP=? and ghiChu=? and nhom=? and dotMo=?" +
                " and tuan=? and thu=? and ngayThi=? and kip=? and SLDK=? and phong=?";
        var prepare=conn.prepareStatement(sql);
        prepare.setInt(1, lichThi.getMaLop());
        prepare.setString(2, lichThi.getMaHP());
        prepare.setString(3, lichThi.getTenHP());
        prepare.setString(4, lichThi.getGhiChu());
        prepare.setString(5, lichThi.getNhom());
        prepare.setString(6, lichThi.getDotMo());
        prepare.setString(7, lichThi.getTuan());
        prepare.setString(8, lichThi.getThu());
        prepare.setDate(9,Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
        prepare.setString(10, lichThi.getKip());
        prepare.setInt(11, lichThi.getSLDK());
        prepare.setString(12, lichThi.getPhong());
        var res=prepare.executeQuery();
        if(res.next()) return false;
        return true;
    }
    //Kiểm tra xem bảng lịch thi đã bị khóa hay chưa
    public boolean isCheckDataLock(String tableName) throws SQLException {
        String sql = "select * from dbo.LockData where tableName = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1,tableName);
        var res=prepare.executeQuery();
        if(res.next() && res.getInt("dataLock") == 1) return false;
        return true;
    }

    public boolean isValidLecturer(GiangVien giangVien, String tableName) throws SQLException {
        String sql="select * from " + tableName + " where hoTen =? and boMon =? and phone =? and email =? and phong =? " +
                "and maLop =?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giangVien.getNameLecturer());
        prepare.setString(2, giangVien.getFaculty());
        prepare.setLong(3, Long.parseLong(giangVien.getPhoneNumber()));
        prepare.setString(4, giangVien.getEmail());
        prepare.setString(5, giangVien.getWorkPlace());
        prepare.setInt(6, giangVien.getMaLop());
        var res=prepare.executeQuery();
        if(res.next()) return false;
        return true;
    }

    public boolean isValidSupervisor(GiamThi giamThi, String tableName) throws SQLException {
        String sql="select * from "+tableName+" where hoTen =? and boMon =? and phone =? and email =? and phong =? and soBuoi = ?";
        var prepare=conn.prepareStatement(sql);
        prepare.setString(1, giamThi.getNameLecturer());
        prepare.setString(2, giamThi.getFaculty());
        prepare.setLong(3, Long.parseLong(giamThi.getPhoneNumber()));
        prepare.setString(4, giamThi.getEmail());
        prepare.setString(5, giamThi.getWorkPlace());
        prepare.setInt(6, giamThi.getSoBuoiToiDa());
        var res=prepare.executeQuery();
        if(res.next()) return false;
        return true;
    }

    public boolean isCheckGT(String nameLecturer, LichThi lichThi, String hocKy) throws SQLException {
        DataBaseController dataBaseController =new DataBaseController();
        String tableNamePhanCong = "PhanCong" + hocKy;
        //Nếu chưa có bảng phân công -> Tạo bảng
        if(!dataBaseController.checkExistTable(tableNamePhanCong)){
            String sql1="Create table "+ tableNamePhanCong +" (maLop int , giamThi1 nvarchar(50), giamThi2 nvarchar(50), ngayThi date, kipThi nvarchar(50));";
            var prepare= conn.prepareStatement(sql1);
            prepare.executeUpdate();
            return true;
        }

        //đếm số buổi mà giám thị đã trông thi
        GiamThi sup = dataBaseController.searchSupervisorFromDatabase(nameLecturer, "GiamThi" + hocKy);
        String sql="select count(*) dem from " + tableNamePhanCong +" where ( giamThi1 = ? or giamThi2 = ?)";
        var pre = conn.prepareStatement(sql);
        pre.setString(1,nameLecturer);
        pre.setString(2,nameLecturer);
        var res=pre.executeQuery();
        int count = 0;
        if(res.next())  count = res.getInt("dem");

        //Kiểm tra xem có trùng ngày thi và kíp thi hay không
        String sql1="select * from " + tableNamePhanCong +" where ( giamThi1=? or giamThi2=? ) and kipThi = ? and ngayThi = ?";
        var prepare= conn.prepareStatement(sql1);
        prepare.setString(1,nameLecturer);
        prepare.setString(2,nameLecturer);
        prepare.setString(3, lichThi.getKip());
        prepare.setDate(4, Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(lichThi.getNgayThi())));
        var result= prepare.executeQuery();

        if(result.next() || (count + 1 > sup.getSoBuoiToiDa())) return true;
        return false;
    }

    public boolean isValidDonGia(String tenDonGia, String tableName) throws SQLException {
        String sql = "select * from "+tableName+" where tenDonGia=? ";
        var prepare= conn.prepareStatement(sql);
        prepare.setString(1,tenDonGia);
        if(prepare.executeQuery().next()) return false;
        return true;
    }

    public GiangVien ThongTinCaNhanGiangVien(String tenDN, String matKhau) throws SQLException {
        String sql = "Select * from TaiKhoan_GVGT where tenDN = ? and matKhau = ?";
        var prepare = conn.prepareStatement(sql);
        GiangVien lec = new GiangVien();
        prepare.setString(1, tenDN);
        prepare.setString(2, matKhau);
        var res = prepare.executeQuery();
        if(res.next()) {
            String ten = res.getString("hoTen");
            String boMon = res.getString("boMon");
            String soDienThoai = res.getString("soDienThoai");
            String email = res.getString("email");
            String phong = res.getString("phongLamViec");
            lec = new GiangVien(ten, boMon, soDienThoai, email,phong, 0);
        }
        return lec;
    }
}
