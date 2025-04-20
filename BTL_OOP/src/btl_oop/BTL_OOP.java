package btl_oop;

import btl_oop.controller.*;
import btl_oop.model.ChucVu;
import btl_oop.model.Khoa;
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
    private SinhVienController sinhVienController;
    private NguoiXLController nguoiXLController;
    public BTL_OOP() {
        scanner = new Scanner(System.in);
        thanhVienController = new ThanhVienController();
        khoaController = new KhoaController();
        chucVuController = new ChucVuController();
        monHocController = new MonHocController();
        sinhVienController = new SinhVienController();
        nguoiXLController = new NguoiXLController();
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
                        resetMatKhauGUI();
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
        System.out.println("2. Quên mật khẩu");
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
                System.out.println("Xin chào Admin!");
                runAdmin();
            } else if (currentUser.getChucVu().getTenCV().equals("GV")) {
                runGV();
                System.out.println("Xin chào Giảng viên!");
            } else if (currentUser.getChucVu().getTenCV().equals("SV")){
                runSV(currentUser.getMaSV());
                System.out.println("Xin chào Sinh viên!");
            } else {
                runXL();
                System.out.println("Xin chào Người XL!");
            }
        } else {
            menuDangNhap();
            System.out.println("Tài khoản hoặc mật khẩu sai!");
        }
    }


    //===================== LUONG =========================

    private void resetMatKhauGUI() {
        System.out.println("\n===== RESET MẬT KHẨU =====");
        System.out.print("Nhập mã sinh viên (tên đăng nhập): ");
        String maSV = scanner.nextLine().trim();

        ThanhVien tv = thanhVienController.getThanhVienByMa(maSV);
        if (tv.getId() == -1) {
            System.out.println("Không tìm thấy tài khoản.");
            return;
        }

        System.out.print("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine().trim();

        boolean success = thanhVienController.resetMatKhau(maSV, newPassword);
        if (success) {
            System.out.println("Mật khẩu đã được cập nhật thành công!");
            loginGUI();
        } else {
            System.out.println("Lỗi khi cập nhật mật khẩu.");
        }
    }


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
            menuQuanLiGiangVien();
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

    }

    private void quanLyMonHocGUI() {
        boolean running = true;
        while (running) {
            menuQuanLiMonHoc();
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

    private void themMonHocGUI() {
        System.out.print("Nhập tên môn học:");
        String tenMH = scanner.nextLine().trim();

        System.out.print("Nhập số tín chỉ của môn học:");
        int soTC = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập mã môn học:");
        String maMH = scanner.nextLine().trim();
        MonHoc mh = new MonHoc(tenMH, soTC, maMH);
        monHocController.addMonHoc(mh);
    }

    private void xoaMonHocGUI() {
        List<MonHoc> mh = monHocController.getAllMonHoc();
        for (MonHoc x : mh) {
            System.out.println(x);
        }
        System.out.print("Nhập ID môn học muốn xóa:");
        int idMH = scanner.nextInt();
        scanner.nextLine();
        monHocController.deleteMonHoc(idMH);

    }

    private void suaThongTinMonHocGUI() {

    }

    private void quanLyDiemGUI() {
        System.out.println("Quản lý điểm");

    }

    private void menuQuanLiGiangVien() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("===== QUẢN LÍ GIẢNG VIÊN =====");
        System.out.println("1. Thêm giảng viên");
        System.out.println("2. Xóa giảng viên");
        System.out.println("3. Sửa thông tin giảng viên");
        System.out.println("4. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    private void menuQuanLiMonHoc() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("===== QUẢN LÍ MÔN HỌC =====");
        System.out.println("1. Thêm môn học");
        System.out.println("2. Xóa môn học");
        System.out.println("3. Sửa thông tin môn học");
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

    //===================== PHUC =========================

    public void runSV(String maSV) {
        sinhVienController.start(maSV);
    }
    public void runXL(){
        nguoiXLController.start();
    }
}
