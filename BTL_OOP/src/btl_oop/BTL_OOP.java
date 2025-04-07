
package btl_oop;

import btl_oop.dao.ChucVuDAO;
import btl_oop.dao.KhoaDAO;
import btl_oop.dao.ThanhVienDAO;
import btl_oop.model.ChucVu;
import btl_oop.model.Khoa;
import btl_oop.model.ThanhVien;

public class BTL_OOP {

    public static void main(String[] args) {
        ChucVuDAO cvDao= new ChucVuDAO();
        ChucVu cv= (ChucVu) cvDao.getById(1);
        System.out.println(cv);
        KhoaDAO khoadao=new KhoaDAO();
        Khoa khoa= (Khoa) khoadao.getById(1);
        System.out.println(khoa);
        ThanhVienDAO tvdao= new ThanhVienDAO();
        ThanhVien tv=(ThanhVien) tvdao.getById(1);
        System.out.println(tv);
        
        
    }
    
}
