package model;

public class GiamThi extends GiangVienGoc{
    private int soBuoiToiDa;

    public GiamThi() {
    }

    public int getSoBuoiToiDa() {
        return soBuoiToiDa;
    }

    public GiamThi(String nameLecturer, String faculty, String phoneNumber, String email, String workPlace, int soBuoiToiDa) {
        super(nameLecturer, faculty, phoneNumber, email, workPlace);
        this.soBuoiToiDa = soBuoiToiDa;
    }
}
