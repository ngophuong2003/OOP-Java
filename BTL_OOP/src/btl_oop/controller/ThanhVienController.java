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
    
    
    public boolean login(String maSV, String password) {
        ThanhVien tv = thanhVienDAO.getByMa(maSV);
        if (tv.getId() == -1) {
            System.out.println("️Tài khoản không tồn tại!");
            return false;
        }

        if (tv.getMatKhau().equals(password)) {
            System.out.println("Đăng nhập thành công!");
            return true;
        } else {
            System.out.println("Sai mật khẩu!");
            return false;
        }
    }

   public boolean resetMatKhau(String maSV, String matKhauMoi) {
        ThanhVien tv = thanhVienDAO.getByMa(maSV);
        if (tv.getId() == -1) {
            System.out.println("Không tìm thấy tài khoản.");
            return false;
        }
        tv.setMatKhau(matKhauMoi);
        return thanhVienDAO.updateObject(tv);
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
