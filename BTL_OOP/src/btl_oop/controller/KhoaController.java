
package btl_oop.controller;

import btl_oop.dao.KhoaDAO;
import btl_oop.model.Khoa;
import java.util.List;


public class KhoaController {
    private KhoaDAO khoaDAO;
    public KhoaController(){
        this.khoaDAO=new KhoaDAO();
    }
    public Khoa getByMaKhoa(String maKhoa){
        return khoaDAO.getByMa(maKhoa);
    }
    public void getByAllKhoa(){
        List<Khoa> khoa= khoaDAO.getAllKhoa();
        System.out.println("Danh sách các khoa trong hệ thống:");
        for(Khoa x: khoa){
            System.out.println("Tên khoa: "+ x.getTenKhoa()
                               +"| Mã khoa: "+ x.getMaKhoa());
        }
    }
    
}
