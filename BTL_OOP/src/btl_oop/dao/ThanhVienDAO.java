package btl_oop.dao;
import btl_oop.model.ChucVu;
import btl_oop.model.Khoa;
import btl_oop.model.ThanhVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThanhVienDAO extends DAO {

    @Override
    public Object getById(int id) {
        String sql ="select * from tblthanhvien where id = "+id;
        try{
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            if(rs.next()) {
                ChucVu chucvu = (ChucVu) new ChucVuDAO().getById(rs.getInt("tblChucVuid"));
                Khoa khoa = (Khoa) new KhoaDAO().getById(rs.getInt("tblKhoaid"));
                ThanhVien thanhvien = new ThanhVien(rs.getInt("id"), chucvu,khoa, rs.getString("maSV"), rs.getString("lop"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("ngaySinh"));
                return  thanhvien; 
            }
        } catch (SQLException e) {
            return e;
        }
        return null;
    }

    @Override
    public boolean addObject(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean updateObject(Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteObject(int objectId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
