package btl_oop.model;

public class PhacHoi {
    private int idPhanHoi;
    private String maSV;
    private int IDKetQua;
    private String noiDungPK;
    private String noiDungXl = "Chưa XL";

    public PhacHoi(int idPhanHoi, String maSV, int thamGiaId, String noiDungPK, String noiDungXl) {
        this.idPhanHoi = idPhanHoi;
        this.maSV = maSV;
        this.IDKetQua = thamGiaId;
        this.noiDungPK = noiDungPK;
        this.noiDungXl = noiDungXl;
    }

    public PhacHoi(int idPhanHoi, String maSV, int thamGiaId, String noiDung) {
        this.idPhanHoi = idPhanHoi;
        this.maSV = maSV;
        this.IDKetQua = thamGiaId;
        this.noiDungPK = noiDung;
    }

    public PhacHoi(int thamGiaId, String noiDung, String maSV) {
        this.IDKetQua = thamGiaId;
        this.noiDungPK = noiDung;
        this.maSV = maSV;
    }

    public int getIdPhanHoi() {
        return idPhanHoi;
    }

    public String getNoiDungXl() {
        return noiDungXl;
    }

    public String getMaSV() {
        return maSV;
    }

    public int getIDKetQua() {
        return IDKetQua;
    }

    public String getNoiDungPK() {
        return noiDungPK;
    }
}
