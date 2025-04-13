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
    public void addGiangVien(ThanhVien thanhvien){
        ThanhVien tv = thanhVienDAO.getByMa(thanhvien.getMaSV());
        if(tv.getId()==-1){
            if(thanhVienDAO.addObject(thanhvien)){
                System.out.println("Thêm giáo viên thành công!");
            }
            else{
                System.out.println("Thêm thất bại!");
            }
        }
        else{
            System.out.println("Giáo viên đã tồn tại trong hệ thống!");
        }
        
    }
    public void deleteGiangVien(int id){
        if(thanhVienDAO.deleteObject(id)){
            System.out.println("Xóa giảng viên thành công!");
        }
        else{
            System.out.println("Xóa giảng viên thất bại!");
        }
    }
   
    
    
    
    
}
