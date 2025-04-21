
package btl_oop.dao;

import btl_oop.model.KetQua;
import btl_oop.model.MonHocDauDiem;
import btl_oop.model.ThamGia;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KetQuaDAO extends DAO{

    @Override
    public Object getById(int Record_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addObject(Object ketQua) {
        try{
            KetQua kq=(KetQua) ketQua;
            String sql="insert into tblketqua( tblMonHocDauDiemid, tblThamGiaid, diem ) "
                    + "values(?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,kq.getMonHocDauDiem().getId());
            st.setInt(2, kq.getThamGia().getId());
            st.setFloat(3,kq.getDiem());
            st.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Lỗi SQL trong addObject"+ e.getMessage());
            return false;
        } 
    
    }

    @Override
    public boolean updateObject(Object ketqua) {
        try {
            KetQua kq = (KetQua) ketqua;
            String sql = "update tblketqua set diem=? where id=? ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setFloat(1,kq.getDiem());
            st.setInt(2, kq.getId());
            st.executeUpdate();
            return true;
        }catch(SQLException e ){
            System.out.println("Lỗi SQL trong updateDiem"+e.getMessage());
            return false;
        }

    }

    @Override
    public boolean deleteObject(int id) {
        try{
            String sql="delete from tblketqua where id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e ){
            System.out.println("Lỗi SQL trong deleteKetQua"+e.getMessage());
            return false;
        }
    }
    public List<KetQua> getAllKetQuaByThamGiaAndMonHocId(int thamGiaid, int monHocid){
        String sql=" select *from tblketqua as kq "
                + "join tblthamgia as tg "
                + "join tblmonhocdaudiem as mhdd "
                + "on kq.tblMonHocDauDiemid = mhdd.id "
                + "and kq.tblThamGiaid = tg.id "
                + "where tg.id=? and mhdd.tblMonHocid=?";
        List<KetQua> kq=new ArrayList<>();
        try{
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,thamGiaid);
            st.setInt(2,monHocid);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                ThamGia thamgia = new ThamGia(rs.getInt("id"),rs.getInt("tblThanhVienid"),rs.getInt("tblLopHocPhanid") );
                MonHocDauDiem mhdd=new MonHocDauDiem(rs.getInt("id"),rs.getString("tenDauDiem"),rs.getInt("heSo"));
                KetQua ketQua=new KetQua(
                      rs.getInt("id"),
                      mhdd,
                      thamgia,
                      rs.getFloat("diem") );
                kq.add(ketQua);
            }
        } catch (SQLException e ){
            System.out.println("Lỗi getAllKetQuaByThamGiaAndMonHocId"+e.getMessage());
        }
        return kq;
    }
    
}
