package btl_oop.dao;

import btl_oop.model.KiHoc;
import btl_oop.model.LopHocPhan;
import btl_oop.model.MonHoc;
import btl_oop.model.ThanhVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LopHocPhanDAO extends DAO {
    
    @Override
    public Object getById(int id) {

        String sql = "SELECT * FROM tbllophocphan as lhp "
                + "JOIN tblkihoc as kh "
                + "JOIN tblmonhoc as mh "
                + "ON lhp.tblKiHocid = kh.id  "
                + "and lhp.tblMonHocid = mh.id "
                + "WHERE lhp.id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                KiHoc kiHoc = new KiHoc(rs.getInt("tblKiHocid"), rs.getString("tenKiHoc"));
                MonHoc monHoc = new MonHoc(rs.getInt("tblMonHocid"), rs.getString("tenMH"),rs.getInt("soTc"), rs.getString("maMH"));
                LopHocPhan lopHocPhan = new LopHocPhan(
                        id,
                        kiHoc,
                        monHoc,
                        rs.getString("nhomMonHoc"),
                        rs.getInt("siSoToiDa"),
                        rs.getInt("namHoc") 
                );
                       
                return lopHocPhan;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getById: " + e.getMessage());

        }
        return null;

    }
     public List<LopHocPhan> getAllLopHocPhanByMonHocKiHoc(int id, String tenKiHoc){
       String sql ="SELECT * FROM tbllophocphan as lhp "
                + "JOIN tblkihoc as kh "
                + "JOIN tblmonhoc as mh "
                + "ON lhp.tblKiHocid = kh.id  "
                + "and lhp.tblMonHocid = mh.id "
               + "where mh.id=? "
               + "AND kh.tenKiHoc = ?";
        List<LopHocPhan> lhp = new ArrayList<>();
        try{
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2,tenKiHoc);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                KiHoc kihoc=new KiHoc(rs.getInt("tblKiHocid"), rs.getString("tenKiHoc"));
                MonHoc monHoc = new MonHoc(rs.getInt("tblMonHocid"), rs.getString("tenMH"),rs.getInt("soTc"), rs.getString("maMH"));
                LopHocPhan lopHocPhan = new LopHocPhan(rs.getInt("id"),kihoc,monHoc,rs.getString("nhomMonHoc"), rs.getInt("siSoToiDa"),rs.getInt("namHoc"));
                lhp.add(lopHocPhan);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi getAllLopHocPhanByMonHocKiHoc "+e.getMessage());
        }
        return lhp;
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
