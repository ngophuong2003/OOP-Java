package btl_oop.controller;

import btl_oop.dao.ChucVuDAO;
import btl_oop.model.ChucVu;
import java.util.List;

public class ChucVuController {

    private ChucVuDAO chucVuDAO;

    public ChucVuController() {
        this.chucVuDAO = new ChucVuDAO();
    }

    public ChucVu getById(int id) {
        return (ChucVu) chucVuDAO.getById(id);
    }
    
    public ChucVu getByMa(String maCV) {
        return chucVuDAO.getByMa(maCV);
    }
      
    public void getByAllChucVu() {
        List<ChucVu> cv = chucVuDAO.getAllChucVu();
        System.out.println("Danh sách các chức vụ trong hệ thống:");
        for (ChucVu x : cv) {
            System.out.println(x);
        }
    }

}
