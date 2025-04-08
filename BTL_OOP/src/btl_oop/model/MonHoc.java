
package btl_oop.model;


public class MonHoc {
    private int id, soTc;
    private String tenMH;

    public MonHoc(int id, int soTc, String tenMH) {
        this.id = id;
        this.soTc = soTc;
        this.tenMH = tenMH;
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
