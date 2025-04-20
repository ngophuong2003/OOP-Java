package btl_oop.controller;

import btl_oop.dao.KiHocDAO;
import btl_oop.model.KiHoc;

public class KiHocController {

    private KiHocDAO kiHocDAO;

    public KiHocController() {
        this.kiHocDAO = new KiHocDAO();
    }
    public KiHoc getById(int id){
        return (KiHoc) kiHocDAO.getById(id);
    }
    
    public void getAllKiHoc(){
        System.out.println("Danh sách kì học: ");
        for(KiHoc x:kiHocDAO.getAllKiHoc()){
            System.out.println(x);
        }
    }

}
