
package btl_oop.model;

import java.util.List;


public class LopHocPhan {
    private int id;
    private KiHoc kiHoc;
    private MonHoc monHoc;
    private String nhomMonHoc;
    private int siSoToiDa;
    private int namHoc;

    public LopHocPhan(int id, KiHoc kiHoc, MonHoc monHoc, String nhomMonHoc, int siSoToiDa, int namHoc) {
        this.id = id;
        this.kiHoc = kiHoc;
        this.monHoc = monHoc;
        this.nhomMonHoc = nhomMonHoc;
        this.siSoToiDa = siSoToiDa;
        this.namHoc = namHoc;
    }

    public LopHocPhan( KiHoc kiHoc, MonHoc monHoc, String nhomMonHoc, int siSoToiDa, int namHoc) {
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

    public int getSiSoToiDa() {
        return siSoToiDa;
    }

    public void setSiSoToiDa(int siSoToiDa) {
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

    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
