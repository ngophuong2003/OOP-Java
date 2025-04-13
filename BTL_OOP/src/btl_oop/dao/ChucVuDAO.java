package btl_oop.dao;

import btl_oop.model.ChucVu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDAO extends DAO {

    @Override
    public Object getById(int id) {
        String sql = "select * from tblchucvu where id = " + id;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ChucVu chucvu = new ChucVu(rs.getInt("id"), rs.getString("tenCV"));
                return chucvu;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getById: " + e.getMessage());
            return new ChucVu(-1);
        }
        return new ChucVu(-1);
    }
    
    public ChucVu getByMa(String maCV) {
        String sql = "select * from tblchucvu where tenCV = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,maCV);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ChucVu chucvu = new ChucVu(rs.getInt("id"), rs.getString("tenCV"));
                return chucvu;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getById: " + e.getMessage());
            return new ChucVu(-1);
        }
        return new ChucVu(-1);
    }
    
     public List<ChucVu> getAllChucVu(){
       String sql ="select * from tblchucvu ";
        List<ChucVu> cv = new ArrayList<>();
        try{
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                ChucVu chucvu = new ChucVu(rs.getInt("id"),rs.getString("tenCV"));
                cv.add(chucvu);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi getAllChucVu "+e.getMessage());
        }
        return cv;
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
