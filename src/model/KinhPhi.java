package model;

public class KinhPhi {
    private int maLop;
    private String tenGV;
    private long inAn;
    private long phoTo;
    private long toChucThi;
    private long kinhPhiGT;
    private int checkThanhToan;
    private String ghiChu;

    public KinhPhi() {
    }

    public KinhPhi(int maLop, String tenGV, long inAn, long phoTo, long toChucThi, long kinhPhiGT, int checkThanhToan,String ghiChu) {
        this.maLop = maLop;
        this.tenGV = tenGV;
        this.inAn = inAn;
        this.phoTo = phoTo;
        this.toChucThi = toChucThi;
        this.kinhPhiGT = kinhPhiGT;
        this.checkThanhToan = checkThanhToan;
        this.ghiChu=ghiChu;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public long getInAn() {
        return inAn;
    }

    public void setInAn(long inAn) {
        this.inAn = inAn;
    }

    public long getPhoTo() {
        return phoTo;
    }

    public void setPhoTo(long phoTo) {
        this.phoTo = phoTo;
    }

    public long getToChucThi() {
        return toChucThi;
    }

    public void setToChucThi(long toChucThi) {
        this.toChucThi = toChucThi;
    }

    public long getKinhPhiGT() {
        return kinhPhiGT;
    }

    public void setKinhPhiGT(long kinhPhiGT) {
        this.kinhPhiGT = kinhPhiGT;
    }

    public int getCheckThanhToan() {
        return checkThanhToan;
    }

    public void setCheckThanhToan(int checkThanhToan) {
        this.checkThanhToan = checkThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
