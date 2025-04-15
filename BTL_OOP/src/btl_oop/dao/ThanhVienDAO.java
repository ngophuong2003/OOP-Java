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
                Khoa khoa = new Khoa(rs.getInt("tblKhoaid"),rs.getString("tenKhoa"), rs.getString("maKhoa"));
                ThanhVien thanhvien = new ThanhVien(
                        id, 
                        chucvu,
                        khoa, 
                        rs.getString("maSV"), 
                        rs.getString("matKhau"), 
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
                Khoa khoa = new Khoa(rs.getInt("tblKhoaid"),rs.getString("tenKhoa"), rs.getString("maKhoa"));
                ThanhVien thanhvien = new ThanhVien(
                        rs.getInt("id"), 
                        chucvu,
                        khoa, 
                        rs.getString("maSV"), 
                        rs.getString("matKhau"), 
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
                Khoa khoa = new Khoa(rs.getInt("tblKhoaid"),rs.getString("tenKhoa"), rs.getString("maKhoa"));
                ThanhVien thanhvien = new ThanhVien(
                        rs.getInt("id"), 
                        chucvu,khoa, 
                        rs.getString("maSV"), 
                        rs.getString("matKhau"), 
                        rs.getString("lop"), 
                        rs.getString("hoTen"), 
                        rs.getString("diaChi"), 
                        rs.getString("ngaySinh"));
                return  thanhvien; 
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getByMa: " + e.getMessage());
            return new ThanhVien (-1);
        }
        return new ThanhVien(-1);
    }

    @Override
    public boolean addObject(Object thanhVien) {
        try{
            ThanhVien tv=(ThanhVien) thanhVien;
            String sql="insert into tblthanhvien( tblChucVuid, tblKhoaid, maSV, matKhau, lop, hoTen, diaChi, ngaySinh) "
                    + "values(?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,tv.getChucVu().getId());
            st.setInt(2,tv.getKhoa().getId());
            st.setString(3,tv.getMaSV());
            st.setString(4,tv.getMatKhau());
            st.setString(5,tv.getLop());
            st.setString(6,tv.getHoTen());
            st.setString(7, tv.getDiaChi());
            st.setString(8,tv.getNgaySinh());
            st.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Lỗi SQL trong addThanhVien"+ e.getMessage());
            return false;
        }
    }

@Override
public boolean updateObject(Object object) {
    ThanhVien tv = (ThanhVien) object;
    try {
        String sql = "UPDATE tblthanhvien SET matkhau = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, tv.getMatKhau());
        st.setInt(2, tv.getId());
        st.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Lỗi cập nhật mật khẩu: " + e.getMessage());
        return false;
    }
}

    @Override
    public boolean deleteObject(int id) {
        try{
            String sql="delete from tblthanhvien where id =?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return true; 
        } catch (SQLException e){
            System.out.println("Lỗi SQL trong deleteThanhVien"+ e.getMessage());
            return false;
        }
    }
    
}
