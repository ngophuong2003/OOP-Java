
package btl_oop.model;


public class LopHocPhan {
    private int id;
    private KiHoc kiHoc;
    private MonHoc monHoc;
    private String nhomMonHoc, siSoToiDa;
    private int namHoc;

    public LopHocPhan(int id, KiHoc kiHoc, MonHoc monHoc, String nhomMonHoc, String siSoToiDa, int namHoc) {
        this.id = id;
        this.kiHoc = kiHoc;
        this.monHoc = monHoc;
        this.nhomMonHoc = nhomMonHoc;
        this.siSoToiDa = siSoToiDa;
        this.namHoc = namHoc;
    }

    public int getId() {
        return id;
    }


    public KiHoc getKiHoc() {
        return kiHoc;
    }

    public void setKiHoc(KiHoc kiHoc) {
        this.kiHoc = kiHoc;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public String getNhomMonHoc() {
        return nhomMonHoc;
    }

    public void setNhomMonHoc(String nhomMonHoc) {
        this.nhomMonHoc = nhomMonHoc;
    }

    public String getSiSoToiDa() {
        return siSoToiDa;
    }

    public void setSiSoToiDa(String siSoToiDa) {
        this.siSoToiDa = siSoToiDa;
    }

    public int getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    @Override
    public String toString() {
        return "LopHocPhan{" + "id=" + id + ", kiHoc=" + kiHoc + ", monHoc=" + monHoc + ", nhomMonHoc=" + nhomMonHoc + ", siSoToiDa=" + siSoToiDa + ", namHoc=" + namHoc + '}';
    }
    
    
}
