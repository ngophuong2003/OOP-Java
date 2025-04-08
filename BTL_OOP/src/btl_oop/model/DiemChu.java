
package btl_oop.model;

public class DiemChu {
    private int id;
    private String diemChu;
    private float diemHe10ToiThieu, diemHe10ToiDa;

    public DiemChu(int id, String diemChu, float diemHe10ToiThieu, float diemHe10ToiDa) {
        this.id = id;
        this.diemChu = diemChu;
        this.diemHe10ToiThieu = diemHe10ToiThieu;
        this.diemHe10ToiDa = diemHe10ToiDa;
    }

    public int getId() {
        return id;
    }
    public String getDiemChu() {
        return diemChu;
    }

    public void setDiemChu(String diemChu) {
        this.diemChu = diemChu;
    }

    public float getDiemHe10ToiThieu() {
        return diemHe10ToiThieu;
    }

    public void setDiemHe10ToiThieu(float diemHe10ToiThieu) {
        this.diemHe10ToiThieu = diemHe10ToiThieu;
    }

    public float getDiemHe10ToiDa() {
        return diemHe10ToiDa;
    }

    public void setDiemHe10ToiDa(float diemHe10ToiDa) {
        this.diemHe10ToiDa = diemHe10ToiDa;
    }

    @Override
    public String toString() {
        return "DiemChu{" + "id=" + id + ", diemChu=" + diemChu + ", diemHe10ToiThieu=" + diemHe10ToiThieu + ", diemHe10ToiDa=" + diemHe10ToiDa + '}';
    }
    
    
}
