package btl_oop.model;

public class ThanhVien {

    private int id;
    private ChucVu chucVu;
    private Khoa khoa;
    private String maSV,matKhau, lop, hoTen, diaChi, ngaySinh;

    public ThanhVien(int id, ChucVu chucVu, Khoa khoa, String maSV,String matKhau, String lop, String hoTen, String diaChi, String ngaySinh) {
        this.id = id;
        this.chucVu = chucVu;
        this.khoa = khoa;
        this.maSV = maSV;
        this.matKhau= matKhau;
        this.lop = lop;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
    }
    
    public ThanhVien( ChucVu chucVu, Khoa khoa, String maSV,String matKhau, String lop, String hoTen, String diaChi, String ngaySinh) {
        this.chucVu = chucVu;
        this.khoa = khoa;
        this.maSV = maSV;
        this.matKhau= matKhau;
        this.lop = lop;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
    }
    
    
    public ThanhVien(int id){
        this.id=id;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" + "id=" + id + ", chucVu=" + chucVu + ", khoa=" + khoa + ", maSV=" + maSV + ", lop=" + lop + ", hoTen=" + hoTen + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh + '}';
    }
}
