
package btl_oop.controller;

import btl_oop.dao.KetQuaDAO;
import btl_oop.dao.ThamGiaDAO;
import btl_oop.model.KetQua;
import btl_oop.model.ThamGia;
import java.util.List;


public class KetQuaController {
    private KetQuaDAO ketQuaDAO;
    private ThamGiaDAO thamGiaDAO;

    public KetQuaController() {
        this.ketQuaDAO = new KetQuaDAO();
        this.thamGiaDAO = new ThamGiaDAO();
    }
    
    public ThamGia getThamGiaByThanhVienAndLopHocPhan(int thanhVienId, int lopHocPhanId){
        return thamGiaDAO.getByThanhVienAndLopHocPhan(thanhVienId, lopHocPhanId);
    }
    
    public void themKetQua(KetQua ketQua){
        if(ketQuaDAO.addObject(ketQua)){
            System.out.println("Thêm thành công "+ ketQua.getMonHocDauDiem().getTenDauDiem());
        }
        else{
            System.out.println("Thêm thất bại");
        }
    }
    public List<KetQua> getAllKetQuaByThamGiaAndMonHocId(int thamGiaid, int monHocid){
        return ketQuaDAO.getAllKetQuaByThamGiaAndMonHocId(thamGiaid, monHocid);
    }
    public void xoaKetQua(int id){
        if(ketQuaDAO.deleteObject(id)){
            System.out.println("Xóa thành công!");
        }
        else{
            System.out.println("Xóa thất bại!");
        }
    }
    public void suaKetQua(KetQua kq){
        if(ketQuaDAO.updateObject(kq)){
            System.out.println("Sửa điểm thành công!");
            System.out.println(kq);
        }
        else{
            System.out.println("Sửa điểm thất bại!");
        }
    }
    public void suaKetQuaNoprint(KetQua kq){
        if(ketQuaDAO.updateObject(kq)){
            System.out.println("Sửa điểm thành công!");
        }
        else{
            System.out.println("Sửa điểm thất bại!");
        }
    } 
    public List<KetQua> getAllKetQuaByThamGiaId(int thamGiaid){
        return ketQuaDAO.getAllKetQuaByThamGiaId(thamGiaid);
    }
}
