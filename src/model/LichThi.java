package model;

import java.util.Date;

public class TestSchedule {
    private int maLop;
    private String maHP;
    private String tenHP;
    private String ghiChu;
    private String nhom;
    private String dotMo;
    private String tuan;
    private String thu;
    private Date ngayThi;
    private String kip;
    private int SLDK;
    private String phong;

    public TestSchedule() {
    }

    public TestSchedule(int maLop, String maHP, String tenHP, String ghiChu,
                        String nhom, String dotMo, String tuan, String thu,
                        Date ngayThi, String kip, int SLDK, String phong) {
        this.maLop = maLop;
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.ghiChu = ghiChu;
        this.nhom = nhom;
        this.dotMo = dotMo;
        this.tuan = tuan;
        this.thu = thu;
        this.ngayThi = ngayThi;
        this.kip = kip;
        this.SLDK = SLDK;
        this.phong = phong;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getDotMo() {
        return dotMo;
    }

    public void setDotMo(String dotMo) {
        this.dotMo = dotMo;
    }

    public String getTuan() {
        return tuan;
    }

    public void setTuan(String tuan) {
        this.tuan = tuan;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public Date getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(Date ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getKip() {
        return kip;
    }

    public void setKip(String kip) {
        this.kip = kip;
    }

    public int getSLDK() {
        return SLDK;
    }

    public void setSLDK(int SLDK) {
        this.SLDK = SLDK;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }
}
