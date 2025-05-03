
package btl_oop.controller;

import btl_oop.dao.MonHocDAO;
import btl_oop.dao.MonHocDauDiemDAO;
import btl_oop.model.MonHoc;
import btl_oop.model.MonHocDauDiem;
import java.util.List;

public class MonHocController {
    private MonHocDAO monHocDAO;
    private MonHocDauDiemDAO monHocDauDiemDAO;
    public MonHocController(){
        this.monHocDAO=new MonHocDAO();
        this.monHocDauDiemDAO = new MonHocDauDiemDAO();
    }
    public List<MonHoc> getAllMonHoc(){
        return monHocDAO.getAllMonHoc();
    }
    public MonHoc getMonHocById(int monHocId){
        return (MonHoc) monHocDAO.getById(monHocId);
    }
    public MonHoc getMonHocByMaMonHoc(String maMH){
        return monHocDAO.getByMa(maMH);
    }
    public void addMonHoc(MonHoc monHoc){
        MonHoc mh=monHocDAO.getByMa(monHoc.getMaMH());
        if(mh.getId()==-1){
            if(monHocDAO.addObject(monHoc)) {
                System.out.println("Thêm môn học thành công!");
            }
            else{
                System.out.println("Thêm thất bại!");
            }
        }
        else{
            System.out.println("Môn học đã tồn tại trong hệ thống!");
        }
    }
    public void deleteMonHoc(int id){
        if(monHocDAO.deleteObject(id)){
            System.out.println("Xóa môn học thành công!");
        }
        else{
            System.out.println("Xóa môn học thất bại!");
        }
    }
    public void updateMonHoc(MonHoc monHoc){
        if(monHocDAO.updateObject(monHoc)){
            System.out.println("Sửa thông tin môn học thành công!");
        }
        else{
            System.out.println("Sửa thông tin môn học thất bại!");
        }
    }
    
    public List<MonHocDauDiem> getMonHocDauDiemByMonHoc(int monHocId){
        return monHocDauDiemDAO.getByMonHoc(monHocId);
    }
    
}
