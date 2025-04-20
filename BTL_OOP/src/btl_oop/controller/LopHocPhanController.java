
package btl_oop.controller;

import btl_oop.dao.LopHocPhanDAO;
import btl_oop.model.LopHocPhan;
import java.util.List;


public class LopHocPhanController {
    private LopHocPhanDAO lopHocPhanDAO;
    public LopHocPhanController(){
        this.lopHocPhanDAO=new LopHocPhanDAO();
    }
    public LopHocPhan getById(int id){
        return (LopHocPhan) lopHocPhanDAO.getById(id);
    }
    
    public List<LopHocPhan> getLopHocPhanByMonHocKiHoc(int monHocId, String tenKiHoc){
        return lopHocPhanDAO.getAllLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
    }
}
