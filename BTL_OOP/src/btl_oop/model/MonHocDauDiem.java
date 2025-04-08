
package btl_oop.model;


public class MonHocDauDiem {
    private int id;
    private String tenDauDiem;
    private int heSo;
    private MonHoc monHoc;

    public MonHocDauDiem(int id, String tenDauDiem, int heSo, MonHoc monHoc) {
        this.id = id;
        this.tenDauDiem = tenDauDiem;
        this.heSo = heSo;
        this.monHoc = monHoc;
    }

    public int getId() {
        return id;
    }
    public String getTenDauDiem() {
        return tenDauDiem;
    }

    public void setTenDauDiem(String tenDauDiem) {
        this.tenDauDiem = tenDauDiem;
    }

    public int getHeSo() {
        return heSo;
    }

    public void setHeSo(int heSo) {
        this.heSo = heSo;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    @Override
    public String toString() {
        return "MonHocDauDiem{" + "id=" + id + ", tenDauDiem=" + tenDauDiem + ", heSo=" + heSo + ", monHoc=" + monHoc + '}';
    }
    
}
