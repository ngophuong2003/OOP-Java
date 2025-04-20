
package btl_oop.model;

public class KiHoc {
    private int id;
    private String tenKiHoc;
    public KiHoc(int id, String tenKiHoc){
        this.id=id;
        this.tenKiHoc=tenKiHoc;
    }
    public KiHoc(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getTenKiHoc() {
        return tenKiHoc;
    }

    public void setTenKiHoc(String tenKiHoc) {
        this.tenKiHoc = tenKiHoc;
    }

    @Override
    public String toString() {
        return "KiHoc{" + "id=" + id + ", tenKiHoc=" + tenKiHoc + '}';
    }
    
    
}
