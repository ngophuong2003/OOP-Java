
package btl_oop.controller;

import btl_oop.dao.ThanhVienDAO;
import btl_oop.model.ThanhVien;
import java.util.List;

public class ThanhVienController {
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienController() {
        thanhVienDAO = new ThanhVienDAO();
    }
    
    public ThanhVien getThanhVienById(int thanhVienId){
        return (ThanhVien) thanhVienDAO.getById(thanhVienId);
    }
    
    public ThanhVien getThanhVienByMa(String ma){
        return (ThanhVien) thanhVienDAO.getByMa(ma);
    }
    
    public List<ThanhVien> getAllThanhVienByChucVu(String tenCV){
        return thanhVienDAO.getAllThanhVienByChucVu(tenCV);
    }
    
    public boolean login(String maSV, String password){
        return true;
    }
    
    public boolean register(String maSV, String password ){
        return true;
    }
    
   
    
    
    
    
}
