package btl_oop;

import btl_oop.controller.ChucVuController;
import btl_oop.controller.KhoaController;
import btl_oop.controller.KiHocController;
import btl_oop.controller.LopHocPhanController;
import btl_oop.controller.MonHocController;
import btl_oop.controller.ThanhVienController;
import btl_oop.dao.ThanhVienDAO;
import btl_oop.model.ChucVu;
import btl_oop.model.Khoa;
import btl_oop.model.LopHocPhan;
import btl_oop.model.MonHoc;
import btl_oop.model.ThanhVien;
import java.util.List;
import java.util.Scanner;

public class BTL_OOP {

    private Scanner scanner;
    private ThanhVien currentUser;
    private ThanhVienController thanhVienController;
    private KhoaController khoaController;
    private ChucVuController chucVuController;
    private MonHocController monHocController;
    private LopHocPhanController lopHocPhanController;
    private KiHocController kiHocController;

    public BTL_OOP() {
        scanner = new Scanner(System.in);
        thanhVienController = new ThanhVienController();
        khoaController = new KhoaController();
        chucVuController = new ChucVuController();
        monHocController = new MonHocController();
        kiHocController = new KiHocController();
        lopHocPhanController = new LopHocPhanController();
    }

    public static void main(String[] args) {
        BTL_OOP app = new BTL_OOP();
        app.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                menuDangNhap();
                int choice = getChoice(3);
                switch (choice) {
                    case 1:
                        loginGUI();
                        break;
                    case 2:
                        registerGUI();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Cảm ơn bạn đã sử dụng hệ thống. Tạm biệt!");
                        break;
                }
            }
        }
    }

    private void menuDangNhap() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ SINH VIÊN =====");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký (chỉ dành cho sinh viên)");
        System.out.println("3. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    private void menuTrangChuAdmin() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ SINH VIÊN =====");
        System.out.println("===== TRANG CHỦ ADMIN =====");
        System.out.println("1. Quản lý sinh viên");
        System.out.println("2. Quản lý giảng viên");
        System.out.println("3. Quản lý môn học");
        System.out.println("4. Quản lý điểm");
        System.out.println("5. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===================== LUONG =========================
    private void loginGUI() {
        System.out.println("Đăng nhập");
        System.out.print("Nhập tài khoản: ");
        String username = scanner.next();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.next();
        boolean isLogin = thanhVienController.login(username, password);
        if (isLogin) {
            currentUser = thanhVienController.getThanhVienByMa(username);
            if (currentUser.getChucVu().getTenCV().equals("Admin")) {
                runAdmin();
            } else if (currentUser.getChucVu().getTenCV().equals("GV")) {
                runGV();
                System.out.println("GV");
            } else {
                runSV();
                System.out.println("SV");
            }
        } else {
            menuDangNhap();
        }
    }

    private void registerGUI() {
        System.out.println("Đăng ký");
    }

    //===================== LUONG =========================
    //===================== PHUONG =========================
    public void runAdmin() {
        boolean running = true;
        scanner.nextLine();
        while (running) {
            menuTrangChuAdmin();
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    quanLySinhVienGUI();
                    break;
                case 2:
                    quanLyGiangVienGUI();
                    break;
                case 3:
                    quanLyMonHocGUI();
                    break;
                case 4:
                    quanLyDiemGUI();
                    break;
                case 5:
                    running = false;
                    currentUser = null;
                    menuDangNhap();
                    break;
            }
        }
    }

    private void quanLyGiangVienGUI() {
        boolean running = true;
        while (running) {
            menuQuanLyGiangVien();
            int choice = getChoice(4);
            switch (choice) {
                case 1:
                    themGiangVienGUI();
                    break;
                case 2:
                    xoaGiangVienGUI();
                    break;
                case 3:
                    suaThongTinGiangVienGUI();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }

    private void themGiangVienGUI() {

        khoaController.getByAllKhoa();
        System.out.print("Nhập mã khoa:");
        String maKhoa = scanner.nextLine().trim();

        System.out.print("Nhập mã giảng viên:");
        String maGV = scanner.nextLine().trim();

        System.out.print("Nhập mật khẩu:");
        String matKhau = scanner.nextLine().trim();

        System.out.print("Nhập tên lớp:");
        String tenLop = scanner.nextLine().trim();

        System.out.print("Nhập tên đầy đủ:");
        String hoTen = scanner.nextLine().trim();

        System.out.print("Nhập địa chỉ:");
        String diaChi = scanner.nextLine().trim();

        System.out.print("Nhập ngày sinh:");
        String ngaySinh = scanner.nextLine().trim();

        ChucVu giaoVienCV = chucVuController.getByMa("GV");
        Khoa khoa = khoaController.getByMaKhoa(maKhoa);

        ThanhVien giaoVien = new ThanhVien(giaoVienCV, khoa, maGV, matKhau, tenLop, hoTen, diaChi, ngaySinh);
        thanhVienController.addGiangVien(giaoVien);

    }

    private void xoaGiangVienGUI() {
        List<ThanhVien> tv = thanhVienController.getAllThanhVienByChucVu("GV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }
        System.out.print("Nhập ID giảng viên muốn xóa:");
        int idGV = scanner.nextInt();
        scanner.nextLine();
        thanhVienController.deleteGiangVien(idGV);
    }

    private void suaThongTinGiangVienGUI() {
        List<ThanhVien> tv=thanhVienController.getAllThanhVienByChucVu("GV");
        for(ThanhVien x:tv){
            System.out.println(x);
        }
        System.out.print("Nhập ID giảng viên muốn sửa thông tin:");
        int idGV=scanner.nextInt();
        scanner.nextLine();
        ThanhVien tvUpdate= thanhVienController.getThanhVienById(idGV);
      
        khoaController.getByAllKhoa();
        System.out.println("Khoa hiện tại:" + tvUpdate.getKhoa());
        System.out.print("Nhập mã khoa mới( Enter để bỏ qua):");
        String maKhoa = scanner.nextLine().trim();
        if(!maKhoa.equals("")){
            Khoa khoa = khoaController.getByMaKhoa(maKhoa);
            tvUpdate.setKhoa(khoa);
        }
        
        System.out.println("Mật khẩu hiện tại:" + tvUpdate.getMatKhau());
        System.out.print("Nhập mật khẩu mới( Enter để bỏ qua):");
        String matKhau = scanner.nextLine().trim();
        if(!matKhau.equals("")){
            tvUpdate.setMatKhau(matKhau);
        }
        
        System.out.println("Lớp hiện tại:" +tvUpdate.getLop());
        System.out.print("Nhập lớp mới( Enter để bỏ qua):");
        String lop=scanner.nextLine().trim();
        if(!lop.equals("")) {
            tvUpdate.setLop(lop);
        }
        
        System.out.println("Họ và tên hiện tại:"+tvUpdate.getHoTen());
        System.out.print("Nhập họ và tên mới( Enter để bỏ qua):");
        String hoTen=scanner.nextLine().trim();
        if(!hoTen.equals("")) {
            tvUpdate.setHoTen(hoTen);
        }
        
        System.out.println("Địa chỉ hiện tại:"+tvUpdate.getDiaChi());
        System.out.print("Nhập địa chỉ mới( Enter để bỏ qua):");
        String diaChi=scanner.nextLine().trim();
        if(!diaChi.equals("")) {
            tvUpdate.setDiaChi(diaChi);
        }
        
        System.out.println("Ngày sinh hiện tại:"+tvUpdate.getDiaChi());
        System.out.print("Nhập ngày sinh mới( Enter để bỏ qua):");
        String ngaySinh=scanner.nextLine().trim();
        if(!ngaySinh.equals("")) {
            tvUpdate.setNgaySinh(ngaySinh);
        }
        
        System.out.println(tvUpdate);
        thanhVienController.updateGiangVien(tvUpdate);

    }

    private void quanLyMonHocGUI() {
        boolean running = true;
        while (running) {
            menuQuanLyMonHoc();
            int choice = getChoice(4);
            switch (choice) {
                case 1:
                    themMonHocGUI();
                    break;
                case 2:
                    xoaMonHocGUI();
                    break;
                case 3:
                    suaThongTinMonHocGUI();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }
    private void themMonHocGUI(){
        System.out.print("Nhập tên môn học:");
        String tenMH = scanner.nextLine().trim();

        System.out.print("Nhập số tín chỉ của môn học:");
        int soTC = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập mã môn học:");
        String maMH = scanner.nextLine().trim();
        MonHoc mh = new MonHoc(tenMH,soTC,maMH);
        monHocController.addMonHoc(mh);
    }
    private void xoaMonHocGUI(){
        List<MonHoc> mh=monHocController.getAllMonHoc();
        for (MonHoc x : mh) {
            System.out.println(x);
        }
        System.out.print("Nhập ID môn học muốn xóa:");
        int idMH = scanner.nextInt();
        scanner.nextLine();
        monHocController.deleteMonHoc(idMH);
        
    }
    private void suaThongTinMonHocGUI(){
        List<MonHoc> mh=monHocController.getAllMonHoc();
        for(MonHoc x:mh){
            System.out.println(x);
        }
        System.out.println("Nhập ID môn học muốn sửa thông tin:");
        int idMH=scanner.nextInt();
        scanner.nextLine();
        MonHoc mhUpdate=monHocController.getMonHocById(idMH);
    
        System.out.println("Tên môn học hiện tại:" + mhUpdate.getTenMH());
        System.out.print("Nhập tên môn học mới( Enter để bỏ qua):");
        String tenMH = scanner.nextLine().trim();
        if(!tenMH.equals("")){
            mhUpdate.setTenMH(tenMH);
        }
        
        System.out.println("Số tín chỉ hiện tại:" +mhUpdate.getSoTc());
        System.out.print("Nhập số tín chỉ mới( Enter để bỏ qua):");
        String soTC=scanner.nextLine().trim();
        if(!soTC.equals("")) {
            int t=Integer.parseInt(soTC);
            mhUpdate.setSoTc(t);
        }
        
        System.out.println("Mã môn học hiện tại:" + mhUpdate.getMaMH());
        System.out.print("Nhập mã môn học mới( Enter để bỏ qua):");
        String maMH = scanner.nextLine().trim();
        if(!maMH.equals("")){
            mhUpdate.setMaMH(maMH);
        }
        
        System.out.println(mhUpdate);
        monHocController.updateMonHoc(mhUpdate);

    }
    private void quanLyDiemGUI() {
        boolean running = true;
        while (running) {
            menuQuanLyDiem();
            int choice = getChoice(4);
            switch (choice) {
                case 1:
                    themDiemMonHocGUI();
                    break;
                case 2:
                    xoaDiemMonHocGUI();
                    break;
                case 3:
                    suaDiemMonHocGUI();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }
    private void themDiemMonHocGUI() {
        hienThiDanhSachSinhVienThuocLopHocPhan();
        
        System.out.print("Nhập Id sinh viên phần muốn thêm điểm: ");
        int svId = scanner.nextInt();
        scanner.nextLine();
        
        
        
        
      
        
        
//       1
    }
    private void xoaDiemMonHocGUI() {
        hienThiDanhSachSinhVienThuocLopHocPhan();
    }
    private void suaDiemMonHocGUI() {
        hienThiDanhSachSinhVienThuocLopHocPhan();
    }
    
    private void hienThiDanhSachSinhVienThuocLopHocPhan(){
        kiHocController.getAllKiHoc();
        System.out.print("Nhập tên kì học: ");
        String tenKiHoc = scanner.nextLine();
        
        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        for(MonHoc x:monHoc){
            System.out.println(x);
        }
        System.out.print("Nhập Id môn học muốn thêm điểm: ");
        int monHocId = scanner.nextInt();
        scanner.nextLine();
        
        List<LopHocPhan>  lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for(LopHocPhan x:lhp){
            System.out.println(x);
        }
        
        System.out.print("Nhập Id lớp học phần muốn thêm điểm: ");
        int lopHocPhanId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Danh sách sinh viên thuộc lớp học phần: "+ lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for(ThanhVien x:tv){
            System.out.println(x);
        }
    }

   

    private void menuQuanLyGiangVien() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("===== QUẢN LÝ GIẢNG VIÊN =====");
        System.out.println("1. Thêm giảng viên");
        System.out.println("2. Xóa giảng viên");
        System.out.println("3. Sửa thông tin giảng viên");
        System.out.println("4. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }
    private void menuQuanLyMonHoc() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("===== QUẢN LÝ MÔN HỌC =====");
        System.out.println("1. Thêm môn học");
        System.out.println("2. Xóa môn học");
        System.out.println("3. Sửa thông tin môn học");
        System.out.println("4. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }
    private void menuQuanLyDiem() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("===== QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("1. Thêm điểm môn học của sinh viên");
        System.out.println("2. Xóa điểm môn học của sinh viên");
        System.out.println("3. Sửa điểm môn học của sinh viên");
        System.out.println("4. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    //===================== PHUONG =========================
    //===================== THANH =========================
    private void quanLySinhVienGUI() {
        System.out.println("Quản lý sinh viên");
    }

    //===================== THANH =========================
    public int getChoice(int maxChoice) {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > maxChoice) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số.");
            return -1;
        }
        return choice;
    }

    public void runGV() {
    }

    public void runSV() {
    }

}
