package model;

import javafx.scene.control.Button;

public class ThongTinLopGiangDay {
    private int maLop;
    private long chiPhi;
    private Button button;

    public ThongTinLopGiangDay(int maLop, long chiPhi, Button button) {
        this.maLop = maLop;
        this.chiPhi = chiPhi;
        this.button = button;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public long getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(long chiPhi) {
        this.chiPhi = chiPhi;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
