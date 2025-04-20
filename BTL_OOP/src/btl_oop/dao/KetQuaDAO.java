/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btl_oop.dao;

import btl_oop.model.KetQua;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Ngo Phuong
 */
public class KetQuaDAO extends DAO{

    @Override
    public Object getById(int Record_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addObject(Object ketQua) {
        try{
            KetQua kq=(KetQua) ketQua;
            String sql="insert into tblketqua( tblMonHocDauDiemid, tblThamGiaid, diem ) "
                    + "values(?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,kq.getMonHocDauDiem().getId());
            st.setInt(2, kq.getThamGia().getId());
            st.setFloat(3,kq.getDiem());
            st.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Lỗi SQL trong addObject"+ e.getMessage());
            return false;
        } 
    
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
