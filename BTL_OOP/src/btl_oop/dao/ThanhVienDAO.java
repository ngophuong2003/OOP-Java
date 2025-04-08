package btl_oop.dao;
import btl_oop.model.ChucVu;
import btl_oop.model.Khoa;
import btl_oop.model.ThanhVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO extends DAO {

    @Override
    public Object getById(int id) {
        String sql ="SELECT * FROM tblthanhvien as tv "
                + "JOIN tblchucvu as cv "
                + "JOIN tblkhoa as khoa "
                + "ON tv.tblChucVuid = cv.id  "
                + "and tv.tblKhoaid = khoa.id "
                + "WHERE tv.id = ?";
        try{
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs= st.executeQuery();
            if(rs.next()) {
                ChucVu chucvu = new ChucVu(rs.getInt("tblChucVuid"),rs.getString("tenCV"));
                Khoa khoa = new Khoa(rs.getInt("tblKhoaid"),rs.getString("tenKhoa"));
                ThanhVien thanhvien = new ThanhVien(
                        id, 
                        chucvu,
                        khoa, 
                        rs.getString("maSV"), 
                        rs.getString("lop"), 
                        rs.getString("hoTen"), 
                        rs.getString("diaChi"), 
                        rs.getString("ngaySinh"));
                return  thanhvien; 
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getById: " + e.getMessage());
             
        }
        return null;
    }
    
    public List<ThanhVien> getAllThanhVienByChucVu(String tenCV){
       String sql ="SELECT * FROM tblthanhvien as tv "
               + "JOIN tblchucvu as cv "
               + "JOIN tblkhoa as khoa "
               + "ON tv.tblChucVuid = cv.id  "
               + "and tv.tblKhoaid = khoa.id "
               + "WHERE cv.tenCV = ?";
        List<ThanhVien> thanhVien = new ArrayList<>();
        try{
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, tenCV);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                ChucVu chucvu = new ChucVu(rs.getInt("tblChucVuid"),rs.getString("tenCV"));
                Khoa khoa = new Khoa(rs.getInt("tblKhoaid"),rs.getString("tenKhoa"));
                ThanhVien thanhvien = new ThanhVien(
                        rs.getInt("id"), 
                        chucvu,
                        khoa, 
                        rs.getString("maSV"), 
                        rs.getString("lop"), 
                        rs.getString("hoTen"), 
                        rs.getString("diaChi"), 
                        rs.getString("ngaySinh"));
                thanhVien.add(thanhvien);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi getAllThanhVienByChucVu ");
        }
        return thanhVien;
    }
    
    public ThanhVien getByMa(String ma) {
       String sql ="SELECT * FROM tblthanhvien as tv "
               + "JOIN tblchucvu as cv "
               + "JOIN tblkhoa as khoa "
               + "ON tv.tblChucVuid = cv.id  "
               + "and tv.tblKhoaid = khoa.id "
               + "WHERE tv.maSV = ?";
        try{
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, ma);
            ResultSet rs= st.executeQuery();
            if(rs.next()) {
                ChucVu chucvu = new ChucVu(rs.getInt("tblChucVuid"),rs.getString("tenCV"));
                Khoa khoa = new Khoa(rs.getInt("tblKhoaid"),rs.getString("tenKhoa"));
                ThanhVien thanhvien = new ThanhVien(
                        rs.getInt("id"), 
                        chucvu,khoa, 
                        rs.getString("maSV"), 
                        rs.getString("lop"), 
                        rs.getString("hoTen"), 
                        rs.getString("diaChi"), 
                        rs.getString("ngaySinh"));
                return  thanhvien; 
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getByMa: " + e.getMessage());   
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
