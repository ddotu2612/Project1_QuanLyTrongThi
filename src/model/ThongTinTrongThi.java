package model;


import java.util.Date;

public class ThongTinTrongThi {
    private int maLop;
    private String giamThi1;
    private String giamThi2;
    private Date ngayThi;
    private String kipThi;

    public ThongTinTrongThi() {
    }

    public ThongTinTrongThi(int maLop, String giamThi1, String giamThi2, Date ngayThi, String kipThi) {
        this.maLop = maLop;
        this.giamThi1 = giamThi1;
        this.giamThi2 = giamThi2;
        this.ngayThi = ngayThi;
        this.kipThi = kipThi;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getGiamThi1() {
        return giamThi1;
    }

    public void setGiamThi1(String giamThi1) {
        this.giamThi1 = giamThi1;
    }

    public String getGiamThi2() {
        return giamThi2;
    }

    public void setGiamThi2(String giamThi2) {
        this.giamThi2 = giamThi2;
    }

    public Date getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(Date ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getKipThi() {
        return kipThi;
    }

    public void setKipThi(String kipThi) {
        this.kipThi = kipThi;
    }
}
