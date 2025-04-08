
package btl_oop.model;


public class ThamGia {
    private int id;
    private ThanhVien thanhVien;
    private LopHocPhan lopHocPhan;

    public ThamGia(int id, ThanhVien thanhVien, LopHocPhan lopHocPhan) {
        this.id = id;
        this.thanhVien = thanhVien;
        this.lopHocPhan = lopHocPhan;
    }

    public int getId() {
        return id;
    }
    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public LopHocPhan getLopHocPhan() {
        return lopHocPhan;
    }

    public void setLopHocPhan(LopHocPhan lopHocPhan) {
        this.lopHocPhan = lopHocPhan;
    }

    @Override
    public String toString() {
        return "ThamGia{" + "id=" + id + ", thanhVien=" + thanhVien + ", lopHocPhan=" + lopHocPhan + '}';
    }
    
}
