package model;

public class DonGia {
    private String tenDonGia;
    private long gia;

    public DonGia() {
    }

    public DonGia(String tenDonGia, long gia) {
        this.tenDonGia = tenDonGia;
        this.gia = gia;
    }

    public String getTenDonGia() {
        return tenDonGia;
    }

    public void setTenDonGia(String tenDonGia) {
        this.tenDonGia = tenDonGia;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }
}
