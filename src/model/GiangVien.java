package model;

public class GiangVien extends GiangVienGoc {
    private int maLop;

    public GiangVien() {
    }

    public GiangVien(String nameLecturer, String faculty, String phoneNumber, String email, String workPlace, int maLop) {
        super(nameLecturer, faculty, phoneNumber, email, workPlace);
        this.maLop = maLop;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
}
