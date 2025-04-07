
package btl_oop.model;

public class ChucVu {
    private int id;
    private String tenCV;

    public ChucVu(int id, String tenCV) {
        this.id = id;
        this.tenCV = tenCV;
    }

    public int getId() {
        return id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    @Override
    public String toString() {
        return "ChucVu{" + "id=" + id + ", tenCV=" + tenCV + '}';
    }
    
}
