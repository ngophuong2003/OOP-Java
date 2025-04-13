
package btl_oop.dao;
import btl_oop.model.Khoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoaDAO extends DAO {

    @Override
    public Object getById(int id) {
       String sql= "select * from tblkhoa where id = " +id;
       try{
           PreparedStatement st=con.prepareStatement(sql);
           ResultSet rs= st.executeQuery();
           if(rs.next()) {
               Khoa khoa=new Khoa(rs.getInt("id"), rs.getString("tenKhoa"), rs.getString("maKhoa"));
               return khoa;
           }
           
       } catch (SQLException e){
           return e;
       }
       return null;
    }
       public Khoa getByMa(String maKhoa) {
       String sql= "select * from tblkhoa where maKhoa =? ";
       try{
           PreparedStatement st=con.prepareStatement(sql);
           st.setString(1, maKhoa);
           ResultSet rs= st.executeQuery();
           if(rs.next()) {
               Khoa khoa=new Khoa(rs.getInt("id"), rs.getString("tenKhoa"), rs.getString("maKhoa"));
               return khoa;
           }
           
       } catch (SQLException e){
           System.out.println("Lỗi SQL trong getByMa: " + e.getMessage());
           return new Khoa(-1);
       }
       return new Khoa(-1);
    }
    public List<Khoa> getAllKhoa(){
       String sql ="select * from tblkhoa ";
        List<Khoa> khoa = new ArrayList<>();
        try{
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                Khoa kh = new Khoa(rs.getInt("id"),rs.getString("tenKhoa"), rs.getString("maKhoa"));
                khoa.add(kh);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi getAllKhoa "+e.getMessage());
        }
        return khoa;
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
