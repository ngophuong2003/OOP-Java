
package btl_oop.dao;

import btl_oop.model.ThamGia;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThamGiaDAO extends DAO{

    public ThamGia getByThanhVienAndLopHocPhan(int thanhVienId, int lopHocPhanId){
       String sql= "select * from tblthamgia where tblThanhVienid = ? AND tblLopHocPhanid = ?";
       try{
           java.sql.PreparedStatement st=con.prepareStatement(sql);
           st.setInt(1, thanhVienId);
           st.setInt(2, lopHocPhanId);
           ResultSet rs= st.executeQuery();
           if(rs.next()) {
               ThamGia tg = new ThamGia(rs.getInt("id"), thanhVienId, lopHocPhanId);
               return tg;
           }
           
       } catch (SQLException e){
           System.out.println("Lỗi getByThanhVienAndLopHocPhan" + e.getMessage());
           return new ThamGia(-1);
       }
       return new ThamGia(-1);
    }
    
    
    @Override
    public Object getById(int Record_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addObject(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateObject(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteObject(int objectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
