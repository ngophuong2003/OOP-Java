
package btl_oop.model;

public class Khoa {
   private int id;
   private String tenKhoa, maKhoa;

    public Khoa(int id, String tenKhoa, String maKhoa) {
        this.id = id;
        this.tenKhoa = tenKhoa;
        this.maKhoa=maKhoa;
    }
    public Khoa(int id){
        this.id=id;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public int getId() {
        return id;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    @Override
    public String toString() {
        return "Khoa{" + "id=" + id + ", tenKhoa=" + tenKhoa + ", maKhoa=" + maKhoa + '}';
    }

    
}
