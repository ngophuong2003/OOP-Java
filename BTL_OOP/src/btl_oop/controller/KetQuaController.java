/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btl_oop.controller;

import btl_oop.dao.KetQuaDAO;
import btl_oop.dao.ThamGiaDAO;
import btl_oop.model.KetQua;
import btl_oop.model.ThamGia;

/**
 *
 * @author Ngo Phuong
 */
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
            System.out.println("Thêm thành công điểm "+ ketQua.getMonHocDauDiem().getTenDauDiem());
        }
        else{
            System.out.println("Thêm thất bại");
        }
    }
    
    
    
}
