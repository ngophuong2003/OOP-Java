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

    public ThanhVien getThanhVienByMa(String maTV){
        return (ThanhVien) thanhVienDAO.getByMa(maTV);
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

    public List<ThanhVien> getThanhVienThamGiaLopHocPhan(int lopHocPhanId, String tenCV){
        List<ThanhVien> tv = thanhVienDAO.getThanhVienThamGiaLopHocPhan(lopHocPhanId, tenCV);
        return tv;
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

    // ========== Giảng viên ==========
    public void addGiangVien(ThanhVien thanhvien){
        ThanhVien tv = thanhVienDAO.getByMa(thanhvien.getMaSV());
        if(tv.getId() == -1){
            if(thanhVienDAO.addObject(thanhvien)){
                System.out.println("Thêm giáo viên thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }
        } else {
            System.out.println("Giáo viên đã tồn tại trong hệ thống!");
        }
    }

    public void deleteGiangVien(int id){
        if(thanhVienDAO.deleteObject(id)){
            System.out.println("Xóa giảng viên thành công!");
        } else {
            System.out.println("Xóa giảng viên thất bại!");
        }
    }
    public void updateGiangVien(ThanhVien thanhvien){
        if(thanhVienDAO.updateObject(thanhvien)){
            System.out.println("Sửa thông tin giảng viên thành công");
        }
        else{
            System.out.println("Sửa thông tin giảng viên thất bại!");
        }
    }

    // ========== Sinh viên ==========
    public void addSinhVien(ThanhVien thanhVien) {
        ThanhVien tv = thanhVienDAO.getByMa(thanhVien.getMaSV());
        if(tv.getId() == -1){
            if(thanhVienDAO.addObject(thanhVien)){
                System.out.println("Thêm sinh viên thành công!");
            } else {
                System.out.println("Thêm sinh viên thất bại!");
            }
        } else {
            System.out.println("Sinh viên đã tồn tại trong hệ thống!");
        }
    }

    public void updateSinhVien(String maSV, String diaChiMoi, String lopMoi, String ngaySinhmoi) {
        ThanhVien tv = thanhVienDAO.getByMa(maSV);
        if(tv.getId() != -1){
            tv.setDiaChi(diaChiMoi);
            tv.setLop(lopMoi);
            tv.setNgaySinh(ngaySinhmoi);
            if(thanhVienDAO.updateObject(tv)){
                System.out.println("Cập nhật thông tin sinh viên thành công!");
            } else {
                System.out.println("Cập nhật thất bại!");
            }
        } else {
            System.out.println("Không tìm thấy sinh viên này!");
        }
    }

    public void deleteSinhVientheoMa(String maSV) {
        ThanhVien tv = thanhVienDAO.getByMa(maSV);
        if(tv.getId() != -1){
            if(thanhVienDAO.deleteObject(tv.getId())){
                System.out.println("Xóa sinh viên thành công!");
            } else {
                System.out.println("Xóa sinh viên thất bại!");
            }
        } else {
            System.out.println("Không tìm thấy sinh viên này!");
        }
    }

    public List<ThanhVien> getAllSinhVien() {
        return thanhVienDAO.getAllThanhVienByChucVu("SV");
    }
}
