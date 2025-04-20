
package btl_oop.model;

public class KetQua {
    private int id;
    private MonHocDauDiem monHocDauDiem;
    private ThamGia thamGia;
    private float diem;

    public KetQua(int id, MonHocDauDiem monHocDauDiem, ThamGia thamGia, float diem) {
        this.id = id;
        this.monHocDauDiem = monHocDauDiem;
        this.thamGia = thamGia;
        this.diem = diem;
    }
    
    public KetQua(MonHocDauDiem monHocDauDiem, ThamGia thamGia, float diem) {
        this.monHocDauDiem = monHocDauDiem;
        this.thamGia = thamGia;
        this.diem = diem;
    }

    public int getId() {
        return id;
    }
    public MonHocDauDiem getMonHocDauDiem() {
        return monHocDauDiem;
    }

    public void setMonHocDauDiem(MonHocDauDiem monHocDauDiem) {
        this.monHocDauDiem = monHocDauDiem;
    }

    public ThamGia getThamGia() {
        return thamGia;
    }

    public void setThamGia(ThamGia thamGia) {
        this.thamGia = thamGia;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "KetQua{" + "id=" + id + ", monHocDauDiem=" + monHocDauDiem + ", thamGia=" + thamGia + ", diem=" + diem + '}';
    }
    
}
