
package btl_oop.dao;
import btl_oop.model.MonHocDauDiem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonHocDauDiemDAO extends DAO {

    @Override
    public Object getById(int id) {
        return null;
    }
    public List<MonHocDauDiem> getByMonHoc(int monhocid){
        String sql="select *from tblmonhocdaudiem"
                + "where tblMonHocid=? ";
        List<MonHocDauDiem> mhdd=new ArrayList<>();
        try{
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, monhocid);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                MonHocDauDiem x= new MonHocDauDiem(rs.getInt("id"), rs.getString("tenDauDiem"), rs.getInt("heSo"));
                mhdd.add(x);
            }
            return mhdd;
            
        }catch(SQLException e){
            System.out.println("Lỗi getByAllMonHoc "+e.getMessage());
            return mhdd;
        }
        
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
