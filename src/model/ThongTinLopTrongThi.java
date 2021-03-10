package model;

public class ThongTinLopTrongThi {
    private int maLop;
    private long tienCong;
    private String xacNhanThanhToan;

    public ThongTinLopTrongThi(int maLop, long tienCong, String xacNhanThanhToan) {
        this.maLop = maLop;
        this.tienCong = tienCong;
        this.xacNhanThanhToan = xacNhanThanhToan;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public long getTienCong() {
        return tienCong;
    }

    public void setTienCong(long tienCong) {
        this.tienCong = tienCong;
    }

    public String getXacNhanThanhToan() {
        return xacNhanThanhToan;
    }

    public void setXacNhanThanhToan(String xacNhanThanhToan) {
        this.xacNhanThanhToan = xacNhanThanhToan;
    }
}
