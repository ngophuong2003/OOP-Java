
package btl_oop.model;

public class Khoa {
   private int id;
   private String tenKhoa;

    public Khoa(int id, String tenKhoa) {
        this.id = id;
        this.tenKhoa = tenKhoa;
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
        return "Khoa{" + "id=" + id + ", tenKhoa=" + tenKhoa + '}';
    }
   
}
