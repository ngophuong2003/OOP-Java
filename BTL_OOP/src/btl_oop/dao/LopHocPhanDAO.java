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
public List<LopHocPhan> getLopHocPhanGiangVienDay(int giangVienId) {
    List<LopHocPhan> list = new ArrayList<>();
    String sql = "SELECT lhp.*, kh.tenKiHoc, mh.tenMH, mh.soTc, mh.maMH " +
             "FROM tblThamGia tg " +
             "JOIN tblThanhVien tv ON tg.tblThanhVienid = tv.id " +
             "JOIN tblLopHocPhan lhp ON tg.tblLopHocPhanid = lhp.id " +
             "JOIN tblKiHoc kh ON lhp.tblKiHocid = kh.id " +
             "JOIN tblMonHoc mh ON lhp.tblMonHocid = mh.id " +
             "WHERE tv.tblChucVuid = 1 AND tv.id = ?";

    try {
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, giangVienId);
        ResultSet rs = st.executeQuery();
while (rs.next()) {
    KiHoc kiHoc = new KiHoc(rs.getInt("tblKiHocid"), rs.getString("tenKiHoc"));
    MonHoc monHoc = new MonHoc(
        rs.getInt("tblMonHocid"), 
        rs.getString("tenMH"),    
        rs.getInt("soTc"),         
        rs.getString("maMH")
    );

    LopHocPhan lopHocPhan = new LopHocPhan(
        rs.getInt("id"),
        kiHoc,
        monHoc,
        rs.getString("nhomMonHoc"),
        rs.getInt("siSoToiDa"), // Không kiểm tra NULL
        rs.getInt("namHoc")
    );
    list.add(lopHocPhan);
}
    } catch (SQLException e) {
        System.out.println("Lỗi getLopHocPhanGiangVienDay: " + e.getMessage());
    }
    return list;
}
 public List<LopHocPhan> getAllLopHocPhan() {
        MonHocDAO monHocDAO = new MonHocDAO();
        KiHocDAO kiHocDAO = new KiHocDAO();
        String sql = "SELECT * FROM tblLopHocPhan";
        List<LopHocPhan> lopHocPhans = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int monHocId = rs.getInt("tblMonHocid");
                int kiHocId = rs.getInt("tblKiHocid"); 

                MonHoc monHoc = (MonHoc) monHocDAO.getById(monHocId);
                KiHoc kiHoc = (KiHoc) kiHocDAO.getById(kiHocId); 

                LopHocPhan lopHocPhan = new LopHocPhan(
                    rs.getInt("id"),
                    kiHoc,
                    monHoc,
                    rs.getString("nhomMonHoc"),
                    rs.getInt("siSoToiDa"),
                    rs.getInt("namHoc")
                );
                lopHocPhans.add(lopHocPhan);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getAllLopHocPhan: " + e.getMessage());
        }
        return lopHocPhans;
    }




    @Override
    public boolean addObject(Object object) {
        try {
            LopHocPhan lopHocPhan = (LopHocPhan) object;
            String sql = "INSERT INTO tblLopHocPhan (id, tblKiHocid, tblMonHocid, nhomMonHoc, siSoToiDa, namHoc) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, lopHocPhan.getId());
            st.setInt(2, lopHocPhan.getKiHoc().getId());
            st.setInt(3, lopHocPhan.getMonHoc().getId());
            st.setString(4, lopHocPhan.getNhomMonHoc());
            st.setInt(5, lopHocPhan.getSiSoToiDa());
            st.setInt(6, lopHocPhan.getNamHoc());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong addLopHocPhan: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateObject(Object object) {
        try {
            LopHocPhan lopHocPhan = (LopHocPhan) object;
            String sql = "UPDATE tblLopHocPhan SET  siSoToiDa = ?, nhomMonHoc = ?, namHoc = ?  WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, lopHocPhan.getSiSoToiDa());
            st.setString(2, lopHocPhan.getNhomMonHoc());
            st.setInt(3, lopHocPhan.getNamHoc());
            st.setInt(4, lopHocPhan.getId());
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong updateLopHocPhan: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteObject(int objectId) {
       try {
            String sql = "DELETE FROM tblLopHocPhan WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, objectId);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong deleteLopHocPhan: " + e.getMessage());
            return false;
        }
    }



   
}
