package model;

public class BaoCao {
    private int maLop;
    private String cot2;
    private String cot3;

    public BaoCao() {
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getCot2() {
        return cot2;
    }

    public void setCot2(String cot2) {
        this.cot2 = cot2;
    }

    public String getCot3() {
        return cot3;
    }

    public void setCot3(String cot3) {
        this.cot3 = cot3;
    }

    public BaoCao(int maLop, String cot2, String cot3) {
        this.maLop = maLop;
        this.cot2 = cot2;
        this.cot3 = cot3;
    }
}
