
package btl_oop.dao;
import btl_oop.model.KiHoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KiHocDAO extends DAO {

    @Override
    public Object getById(int id) {
         String sql = "select * from tblkihoc where id = " + id;
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
               KiHoc kiHoc = new KiHoc(rs.getInt("id"), rs.getString("tenKiHoc"));
                return kiHoc;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getById: " + e.getMessage());
            return new KiHoc(-1);
        }
        return new KiHoc(-1);
    }
    
    public List<KiHoc> getAllKiHoc() {
        String sql = "select * from tblkihoc ";
        List<KiHoc> kh = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               KiHoc kiHoc = new KiHoc(rs.getInt("id"), rs.getString("tenKiHoc"));
               kh.add(kiHoc);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getAllKiHoc: " + e.getMessage());
            return kh;
        }
        return kh;
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
