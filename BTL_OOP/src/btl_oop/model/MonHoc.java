package btl_oop.model;

public class MonHoc {

    private int id, soTc;
    private String tenMH, maMH;

    public MonHoc(int id,  String tenMH,int soTC, String maMH) {
        this.id = id;
        this.tenMH = tenMH;
        this.soTc = soTc;
        this.maMH = maMH;
    }
    public MonHoc(  String tenMH,int soTC, String maMH) {
        this.tenMH = tenMH;
        this.soTc = soTc;
        this.maMH = maMH;
    }
    public MonHoc(int id){
        this.id=id;
    }

    public String getMaMH() {
        return maMH;
    }

    public int getId() {
        return id;
    }

    public int getSoTc() {
        return soTc;
    }

    public void setSoTc(int soTc) {
        this.soTc = soTc;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    @Override
    public String toString() {
        return "MonHoc{" + "id=" + id + ", soTc=" + soTc + ", tenMH=" + tenMH + '}';
    }

}
