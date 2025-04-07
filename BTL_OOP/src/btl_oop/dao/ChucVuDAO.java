
package btl_oop.dao;
import btl_oop.model.ChucVu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ChucVuDAO extends DAO {

    @Override
    public Object getById(int id) {
        String sql= "select * from tblchucvu where id = "+ id;
        try{
            PreparedStatement st= con.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            if(rs.next()) {
                ChucVu chucvu=  new ChucVu(rs.getInt("id"),rs.getString("tenCV"));
                return chucvu;
            }
            
        } catch (SQLException e){
            return e;
        }
        return null;
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
