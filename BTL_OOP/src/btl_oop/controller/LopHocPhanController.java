
package btl_oop.controller;

import btl_oop.dao.LopHocPhanDAO;
import btl_oop.dao.MonHocDauDiemDAO;
import btl_oop.model.LopHocPhan;
import btl_oop.model.MonHocDauDiem;
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
    public List<LopHocPhan> getLopHocPhanbyGiangVien(int giangVienId) {
    return lopHocPhanDAO.getLopHocPhanGiangVienDay(giangVienId);
    }
    
    public void AddLopHocPhan(LopHocPhan lopHocPhan){
        lopHocPhanDAO.addObject(lopHocPhan);

    }
    public void DeleteLopHocPhan(int ID){
        lopHocPhanDAO.deleteObject(ID);

    }
    public void UpdateLopHocPhan(LopHocPhan lopHocPhan){
        lopHocPhanDAO.updateObject(lopHocPhan);

    }
    public void showAllLopHocPhan(){
        List<LopHocPhan> lopHocPhan =lopHocPhanDAO.getAllLopHocPhan();
        for(LopHocPhan x : lopHocPhan){
            System.out.println(x.toString());
        }
    }
}
