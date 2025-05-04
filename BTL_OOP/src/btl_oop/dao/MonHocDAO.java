package btl_oop.dao;

import btl_oop.model.MonHoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonHocDAO extends DAO {

    @Override
    public Object getById(int id) {
        String sql = "select *from tblmonhoc where id=?";
        try {
            PreparedStatement st = con.prepareCall(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MonHoc monHoc = new MonHoc(rs.getInt("id"), rs.getString("tenMH"), rs.getInt("soTC"), rs.getString("maMH"));
                return monHoc;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getByMa: " + e.getMessage());
            return new MonHoc(-1);
        }
        return new MonHoc(-1);
    }

    public MonHoc getByMa(String maMH) {
        String sql = "select * from tblmonhoc where maMH =? ";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maMH);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MonHoc monHoc = new MonHoc(rs.getInt("id"), rs.getString("tenMH"), rs.getInt("soTC"), rs.getString("maMH"));
                return monHoc;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong getByMa: " + e.getMessage());
            return new MonHoc(-1);
        }
        return new MonHoc(-1);
    }

    public List<MonHoc> getAllMonHoc() {
        String sql = "select * from tblmonhoc ";
        List<MonHoc> monHoc = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MonHoc mh = new MonHoc(rs.getInt("id"), rs.getString("tenMH"), rs.getInt("soTc"), rs.getString("maMH"));
                monHoc.add(mh);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi getAllMonHoc " + e.getMessage());
        }
        return monHoc;
    }

    @Override
    public boolean addObject(Object monHoc) {
        try {
            MonHoc mh = (MonHoc) monHoc;
            String sql = " insert into tblmonhoc(id,tenMH,soTC,maMH)"
                    + "values(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, mh.getId());
            st.setString(2, mh.getTenMH());
            st.setInt(3, mh.getSoTc());
            st.setString(4, mh.getMaMH());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi SQL trong addThanhVien" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateObject(Object monHoc) {
        try{
            MonHoc mh= (MonHoc) monHoc;
            String sql="update tblmonhoc set tenMH=?, soTC=?, maMH=? where id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,mh.getTenMH());
            st.setInt(2,mh.getSoTc());
            st.setString(3,mh.getMaMH());
            st.setInt(4, mh.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e ){
            System.out.println("Lỗi SQL trong updateThanhVien" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteObject(int id) {
        try{
            String sql="delete from tblmonhoc where id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch(SQLException e ){
            return false;
        }
    }

}
