
package btl_oop.controller;

import btl_oop.dao.MonHocDAO;
import btl_oop.model.MonHoc;
import java.util.List;

public class MonHocController {
    private MonHocDAO monHocDAO;
    public MonHocController(){
        this.monHocDAO=new MonHocDAO();
    }
    public List<MonHoc> getAllMonHoc(){
        return monHocDAO.getAllMonHoc();
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
    
}
