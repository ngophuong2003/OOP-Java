
package btl_oop.model;


public class ThamGia {
    private int id;
    private int thanhVienId;
    private int lopHocPhanId;

    public ThamGia(int id, int thanhVienId, int lopHocPhanId) {
        this.id = id;
        this.thanhVienId = thanhVienId;
        this.lopHocPhanId = lopHocPhanId;
    }
     public ThamGia(int id) {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public int getThanhVienId() {
        return thanhVienId;
    }

    public void setThanhVienId(int thanhVienId) {
        this.thanhVienId = thanhVienId;
    }

    public int getLopHocPhanId() {
        return lopHocPhanId;
    }

    public void setLopHocPhanId(int lopHocPhanId) {
        this.lopHocPhanId = lopHocPhanId;
    }

    @Override
    public String toString() {
        return "ThamGia{" + "id=" + id + ", thanhVienId=" + thanhVienId + ", lopHocPhanId=" + lopHocPhanId + '}';
    }

   
}
