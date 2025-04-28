package btl_oop;

import btl_oop.controller.*;
import btl_oop.model.ChucVu;
import btl_oop.model.KetQua;
import btl_oop.model.Khoa;
import btl_oop.model.LopHocPhan;
import btl_oop.model.MonHoc;
import btl_oop.model.MonHocDauDiem;
import btl_oop.model.ThamGia;
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
    private KetQuaController ketQuaController;

    private SinhVienController sinhVienController;
    private NguoiXLController nguoiXLController;

    public BTL_OOP() {
        scanner = new Scanner(System.in);
        thanhVienController = new ThanhVienController();
        khoaController = new KhoaController();
        chucVuController = new ChucVuController();
        monHocController = new MonHocController();
        kiHocController = new KiHocController();
        lopHocPhanController = new LopHocPhanController();
        sinhVienController = new SinhVienController();
        nguoiXLController = new NguoiXLController();
        ketQuaController = new KetQuaController();
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
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Quên mật khẩu");
        System.out.println("3. Thoát");
        System.out.print("Lựa chọn của bạn: ");

    }

    private void menuTrangChuAdmin() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
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
            } else if (currentUser.getChucVu().getTenCV().equals("Giảng viên")) {
                runGV();
                System.out.println("Xin chào Giảng viên!");
                GiangVienquanLyDiemGUI();
            } else if (currentUser.getChucVu().getTenCV().equals("SV")) {
                runSV(currentUser.getMaSV());
                System.out.println("Xin chào Sinh viên!");
            } else {
                runXL();
                System.out.println("Xin chào Người XL!");
            }
        } else {
            System.out.println("Tài khoản hoặc mật khẩu sai!");
            menuDangNhap();

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
        List<ThanhVien> tv = thanhVienController.getAllThanhVienByChucVu("GV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }
        System.out.print("Nhập ID giảng viên muốn sửa thông tin:");
        int idGV = scanner.nextInt();
        scanner.nextLine();
        ThanhVien tvUpdate = thanhVienController.getThanhVienById(idGV);

        khoaController.getByAllKhoa();
        System.out.println("Khoa hiện tại:" + tvUpdate.getKhoa());
        System.out.print("Nhập mã khoa mới( Enter để bỏ qua):");
        String maKhoa = scanner.nextLine().trim();
        if (!maKhoa.equals("")) {
            Khoa khoa = khoaController.getByMaKhoa(maKhoa);
            tvUpdate.setKhoa(khoa);
        }

        System.out.println("Mật khẩu hiện tại:" + tvUpdate.getMatKhau());
        System.out.print("Nhập mật khẩu mới( Enter để bỏ qua):");
        String matKhau = scanner.nextLine().trim();
        if (!matKhau.equals("")) {
            tvUpdate.setMatKhau(matKhau);
        }

        System.out.println("Lớp hiện tại:" + tvUpdate.getLop());
        System.out.print("Nhập lớp mới( Enter để bỏ qua):");
        String lop = scanner.nextLine().trim();
        if (!lop.equals("")) {
            tvUpdate.setLop(lop);
        }

        System.out.println("Họ và tên hiện tại:" + tvUpdate.getHoTen());
        System.out.print("Nhập họ và tên mới( Enter để bỏ qua):");
        String hoTen = scanner.nextLine().trim();
        if (!hoTen.equals("")) {
            tvUpdate.setHoTen(hoTen);
        }

        System.out.println("Địa chỉ hiện tại:" + tvUpdate.getDiaChi());
        System.out.print("Nhập địa chỉ mới( Enter để bỏ qua):");
        String diaChi = scanner.nextLine().trim();
        if (!diaChi.equals("")) {
            tvUpdate.setDiaChi(diaChi);
        }

        System.out.println("Ngày sinh hiện tại:" + tvUpdate.getDiaChi());
        System.out.print("Nhập ngày sinh mới( Enter để bỏ qua):");
        String ngaySinh = scanner.nextLine().trim();
        if (!ngaySinh.equals("")) {
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
        List<MonHoc> mh = monHocController.getAllMonHoc();
        for (MonHoc x : mh) {
            System.out.println(x);
        }
        System.out.println("Nhập ID môn học muốn sửa thông tin:");
        int idMH = scanner.nextInt();
        scanner.nextLine();
        MonHoc mhUpdate = monHocController.getMonHocById(idMH);

        System.out.println("Tên môn học hiện tại:" + mhUpdate.getTenMH());
        System.out.print("Nhập tên môn học mới( Enter để bỏ qua):");
        String tenMH = scanner.nextLine().trim();
        if (!tenMH.equals("")) {
            mhUpdate.setTenMH(tenMH);
        }

        System.out.println("Số tín chỉ hiện tại:" + mhUpdate.getSoTc());
        System.out.print("Nhập số tín chỉ mới( Enter để bỏ qua):");
        String soTC = scanner.nextLine().trim();
        if (!soTC.equals("")) {
            int t = Integer.parseInt(soTC);
            mhUpdate.setSoTc(t);
        }

        System.out.println("Mã môn học hiện tại:" + mhUpdate.getMaMH());
        System.out.print("Nhập mã môn học mới( Enter để bỏ qua):");
        String maMH = scanner.nextLine().trim();
        if (!maMH.equals("")) {
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
        kiHocController.getAllKiHoc();
        System.out.print("Nhập tên kì học: ");
        String tenKiHoc = scanner.nextLine();

        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        for (MonHoc x : monHoc) {
            System.out.println(x);
        }
        System.out.print("Nhập Id môn học muốn thêm điểm: ");
        int monHocId = scanner.nextInt();
        scanner.nextLine();

        List<LopHocPhan> lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for (LopHocPhan x : lhp) {
            System.out.println(x);
        }

        System.out.print("Nhập Id lớp học phần muốn thêm điểm: ");
        int lopHocPhanId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }

        System.out.print("Nhập Id sinh viên muốn thêm điểm: ");
        int svId = scanner.nextInt();
        scanner.nextLine();

        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(monHocId, lopHocPhanId);

        List<MonHocDauDiem> mhdd = monHocController.getMonHocDauDiemByMonHoc(monHocId);
        System.out.println("Nhập lần lượt theo các đầu điểm sau: ");
        for (MonHocDauDiem x : mhdd) {
            float diemNhap = 0;
            boolean hopLe = false;
            while (!hopLe) {
                System.out.print(x.getTenDauDiem() + " " + x.getHeSo() + ": ");
                try {
                    diemNhap = Float.parseFloat(scanner.nextLine());
                    hopLe = true;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
                }
            }
            KetQua kq = new KetQua(x, tg, diemNhap);
            ketQuaController.themKetQua(kq);
        }
        System.out.println("Thêm xong!");

    }

    private void xoaDiemMonHocGUI() {
        kiHocController.getAllKiHoc();
        System.out.print("Nhập tên kì học: ");
        String tenKiHoc = scanner.nextLine();

        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        for (MonHoc x : monHoc) {
            System.out.println(x);
        }
        System.out.print("Nhập Id môn học muốn xóa điểm: ");
        int monHocId = scanner.nextInt();
        scanner.nextLine();

        List<LopHocPhan> lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for (LopHocPhan x : lhp) {
            System.out.println(x);
        }

        System.out.print("Nhập Id lớp học phần muốn xóa điểm: ");
        int lopHocPhanId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }
        System.out.print("Nhập Id sinh viên muốn xóa điểm: ");
        int svId = scanner.nextInt();
        scanner.nextLine();
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(monHocId, lopHocPhanId);
        System.out.println("Kết quả các đầu điểm môn học của sinh viên: ");
        List<KetQua> kq=ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(),monHocId );
        for(KetQua x:kq){
            System.out.println(x);
        }
        System.out.println("Nhập Id kết quả đầu điểm muốn xóa: ");
        int kqid=scanner.nextInt();
        scanner.nextLine();
        ketQuaController.xoaKetQua(kqid);
    }

    private void suaDiemMonHocGUI() {
        kiHocController.getAllKiHoc();
        System.out.print("Nhập tên kì học: ");
        String tenKiHoc = scanner.nextLine();

        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        for (MonHoc x : monHoc) {
            System.out.println(x);
        }
        System.out.print("Nhập Id môn học muốn sửa điểm: ");
        int monHocId = scanner.nextInt();
        scanner.nextLine();

        List<LopHocPhan> lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for (LopHocPhan x : lhp) {
            System.out.println(x);
        }

        System.out.print("Nhập Id lớp học phần muốn sửa điểm: ");
        int lopHocPhanId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }
        System.out.print("Nhập Id sinh viên muốn sửa điểm: ");
        int svId = scanner.nextInt();
        scanner.nextLine();
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(monHocId, lopHocPhanId);
        System.out.println("Kết quả các đầu điểm môn học của sinh viên: ");
        List<KetQua> kq=ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(),monHocId );
        for(KetQua x:kq){
            System.out.println(x);
        }
        
        System.out.println("Nhập Id kết quả đầu điểm muốn sửa: ");
        int kqid=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập điểm sửa: ");
        float diemNhap = 0;
            boolean hopLe = false;
            while (!hopLe) {
                try {
                    diemNhap = Float.parseFloat(scanner.nextLine());
                    hopLe = true;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
                }
            }
        for(KetQua x:kq){
            if(x.getId()==kqid){
                x.setDiem(diemNhap);
                ketQuaController.suaKetQua(x);
            }
        }
    }

    private void menuQuanLyGiangVien() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
        System.out.println("===== QUẢN LÝ GIẢNG VIÊN =====");
        System.out.println("1. Thêm giảng viên");
        System.out.println("2. Xóa giảng viên");
        System.out.println("3. Sửa thông tin giảng viên");
        System.out.println("4. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    private void menuQuanLyMonHoc() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
        System.out.println("===== QUẢN LÝ MÔN HỌC =====");
        System.out.println("1. Thêm môn học");
        System.out.println("2. Xóa môn học");
        System.out.println("3. Sửa thông tin môn học");
        System.out.println("4. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    private void menuQuanLyDiem() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
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
        boolean running = true;
        while (running) {
            System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
            System.out.println("===== ADMIN QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Xem danh sách sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    themSinhVienGUI();
                    break;
                case 2:
                    suaThongTinSinhVienGUI();
                    break;
                case 3:
                    xoaSinhVienGUI();
                    break;
                case 4:
                    xemDanhSachSinhVien();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }

    //Usecase Thêm,sửa,xóa,xem danh sách sinh viên
    private void xemDanhSachSinhVien() {
        List<ThanhVien> list = thanhVienController.getAllThanhVienByChucVu("SV");
        System.out.println("===== Danh sách sinh viên =====");
        for (ThanhVien sv : list) {
            System.out.println("Mã Sinh Viên: " +sv.getMaSV()+ " | Họ tên: " +sv.getHoTen()+" | Địa chỉ: "+sv.getDiaChi()+"| Lớp: " + sv.getLop()+ " | Ngày sinh: " + sv.getNgaySinh());
        }
    }

    private void themSinhVienGUI() {
        khoaController.getByAllKhoa();
        System.out.print("Nhập mã khoa cho sinh viên: ");
        String maKhoa = scanner.nextLine().trim();

        System.out.print("Nhập mã sinh viên: ");
        String maSV = scanner.nextLine().trim();

        System.out.print("Nhập mật khẩu cho sinh viên: ");
        String matKhau = scanner.nextLine().trim();

        System.out.print("Nhập tên lớp cho sinh viên: ");
        String lop = scanner.nextLine().trim();

        System.out.print("Nhập họ tên sinh viên: ");
        String hoTen = scanner.nextLine().trim();

        System.out.print("Nhập địa chỉ của sinh viên: ");
        String diaChi = scanner.nextLine().trim();

        System.out.print("Nhập ngày sinh của sinh viên: ");
        String ngaySinh = scanner.nextLine().trim();

        ChucVu chucvuSinhvien = chucVuController.getByMa("SV");
        Khoa khoa = khoaController.getByMaKhoa(maKhoa);
        ThanhVien sv = new ThanhVien(chucvuSinhvien, khoa, maSV, matKhau, lop, hoTen, diaChi, ngaySinh);
        thanhVienController.addSinhVien(sv);
    }

    private void suaThongTinSinhVienGUI() {
        xemDanhSachSinhVien();
        System.out.print("Nhập mã sinh viên muốn sửa: ");
        String maSV = scanner.nextLine().trim();

        ThanhVien sv = thanhVienController.getThanhVienByMa(maSV);
        if (sv.getId() == -1) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }
        System.out.print("Nhập mã sinh viên mới: ");
    String newMaSV = scanner.nextLine().trim();
    
     System.out.print("Nhập họ và tên mới: ");
    String hoTen = scanner.nextLine().trim();
    
        System.out.print("Nhập địa chỉ mới: ");
        String diaChi = scanner.nextLine().trim();
        
        System.out.print("Nhập mật khẩu mới: ");
        String matKhau = scanner.nextLine().trim();

        System.out.print("Nhập lớp mới: ");
        String lop = scanner.nextLine().trim();

        System.out.print("Nhập ngày sinh mới (YYYY-MM-DD): ");
        String ngaySinh = scanner.nextLine().trim();

    sv.setMaSV(newMaSV);
    sv.setHoTen(hoTen);
    sv.setLop(lop);
    sv.setMatKhau(matKhau);
    sv.setDiaChi(diaChi);
    sv.setNgaySinh(ngaySinh);
        thanhVienController.updateSinhVien(maSV,newMaSV,hoTen,matKhau,diaChi,lop,ngaySinh);
    }

    private void xoaSinhVienGUI() {
        xemDanhSachSinhVien();
        System.out.print("Nhập mã sinh viên muốn xóa: ");
        String maSV = scanner.nextLine().trim();

        ThanhVien Sv = thanhVienController.getThanhVienByMa(maSV);
        if (Sv.getId() == -1) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        thanhVienController.deleteSinhVientheoMa(Sv.getMaSV());
    }
    
        private void GiangVienquanLyDiemGUI() {
        boolean running = true;
        while (running) {
            System.out.println("===== GIẢNG VIÊN QUẢN LÝ ĐIỂM =====");
            System.out.println("1. Xem danh sách lớp học phần giảng dạy");
            System.out.println("2. Thêm điểm lớp học phần");
            System.out.println("3. Cập nhật điểm lớp học phần");
            System.out.println("4. Xem thống kê điểm lớp học phần");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    listLopHocPhanCuaGiangVien();
                    break;
                case 2:
                    themdiemSinhvienbyGiangvien();
                    break;
                case 3:
                   suaDiemSinhvienbyGIangvien();
                    break;
                case 4:
                    xemthongkediemLophocphan();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }
   private void listLopHocPhanCuaGiangVien() {
    List<LopHocPhan> list = lopHocPhanController.getLopHocPhanbyGiangVien(currentUser.getId());

    if (list.isEmpty()) {
        System.out.println("Bạn hiện không giảng dạy lớp học phần nào.");
        return;
    }

    System.out.println("===== Danh sách lớp học phần đang giảng dạy =====");
    for (LopHocPhan lhp : list) {
        System.out.println("ID lớp học phần: "+lhp.getId()
                +"| Môn học: " + lhp.getMonHoc().getTenMH()
                + " | Mã môn học: " + lhp.getMonHoc().getMaMH()
                + " | Nhóm: " + lhp.getNhomMonHoc()
                + " | Năm học: " + lhp.getNamHoc()
                + " | Kì: " + lhp.getKiHoc().getTenKiHoc());
    }
}
     
   private void themdiemSinhvienbyGiangvien(){
       listLopHocPhanCuaGiangVien();
       System.out.print("Nhập Id lớp học phần muốn thêm điểm: ");
       int lopHocPhanId = scanner.nextInt();
       scanner.nextLine();
       List<LopHocPhan> checklist = lopHocPhanController.getLopHocPhanbyGiangVien(currentUser.getId());
boolean check = false;
for (LopHocPhan lhp : checklist) {
    if (lhp.getId() == lopHocPhanId) {
        check = true;
        break;
    }
}
if (!check) {
    System.out.println("Lớp học phần này không thuộc quyền giảng dạy của bạn.");
    return;
}
       System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> list = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien sv : list) {
            System.out.println("Mã Sinh Viên: " +sv.getMaSV()
                    + " | Họ tên: " +sv.getHoTen()
                    +" | Địa chỉ: "+sv.getDiaChi()
                    +"| Lớp: " + sv.getLop()
                    + " | Ngày sinh: " + sv.getNgaySinh());
        }
        System.out.print("Nhập mã sinh viên muốn thêm điểm: ");
        String maSV = scanner.nextLine();
        ThanhVien sv=thanhVienController.getThanhVienByMa(maSV);
        if (sv == null) {
        System.out.println("Mã sinh viên không hợp lệ hoặc không thuộc lớp học phần này.");
         return;
         }
        
        LopHocPhan lhp = lopHocPhanController.getById(lopHocPhanId);
   
    
    MonHoc monHoc = lhp.getMonHoc(); 
       int monHocId = monHoc.getId();  
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(monHocId, lopHocPhanId);

        List<MonHocDauDiem> mhdd = monHocController.getMonHocDauDiemByMonHoc(monHocId);
        System.out.println("Nhập lần lượt theo các đầu điểm sau: ");
        for (MonHocDauDiem x : mhdd) {
            float diemNhap = 0;
            boolean hopLe = false;
            while (!hopLe) {
                System.out.print(x.getTenDauDiem() + " " + x.getHeSo() + ": ");
                try {
                    diemNhap = Float.parseFloat(scanner.nextLine());
                    hopLe = true;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
                }
            }
            KetQua kq = new KetQua(x, tg, diemNhap);
            ketQuaController.themKetQua(kq);
        }
        System.out.println("Thêm xong!");
   }

   private void suaDiemSinhvienbyGIangvien() {
       listLopHocPhanCuaGiangVien();
      
       System.out.print("Nhập Id lớp học phần muốn sửa điểm: ");
       int lopHocPhanId = scanner.nextInt();
       scanner.nextLine();
       List<LopHocPhan> checklist = lopHocPhanController.getLopHocPhanbyGiangVien(currentUser.getId());
boolean check = false;
for (LopHocPhan lhp : checklist) {
    if (lhp.getId() == lopHocPhanId) {
        check = true;
        break;
    }
}
if (!check) {
    System.out.println("Lớp học phần này không thuộc quyền giảng dạy của bạn.");
    return;
}

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> list = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "Sinh viên");
        for (ThanhVien sv : list) {
            System.out.println("Mã Sinh Viên: " +sv.getMaSV()
                    + " | Họ tên: " +sv.getHoTen()
                    +" | Địa chỉ: "+sv.getDiaChi()
                    +"| Lớp: " + sv.getLop()
                    + " | Ngày sinh: " + sv.getNgaySinh());
        }
         System.out.print("Nhập mã sinh viên muốn sửa điểm: ");
        String maSV = scanner.nextLine();
        ThanhVien sv=thanhVienController.getThanhVienByMa(maSV);
        if (sv == null) {
        System.out.println("Mã sinh viên không hợp lệ hoặc không thuộc lớp học phần này.");
         return;
         }LopHocPhan lhp = lopHocPhanController.getById(lopHocPhanId);
   
    
    MonHoc monHoc = lhp.getMonHoc(); 
       int monHocId = monHoc.getId();  
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(monHocId, lopHocPhanId);

        
        List<KetQua> kq=ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(),monHocId );
        System.out.println("Kết quả các đầu điểm môn học của sinh viên: ");
    for (KetQua x : kq) {
    System.out.println("ID đầu điểm: "+ x.getMonHocDauDiem().getId()
            +"Đầu điểm: " + x.getMonHocDauDiem().getTenDauDiem()
                     + " | Hệ số: " + x.getMonHocDauDiem().getHeSo() 
                     + " | Điểm: " + x.getDiem());
}

        System.out.println("Nhập Id kết quả đầu điểm muốn sửa: ");
        int kqid=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập điểm sửa: ");
        float diemNhap = 0;
            boolean hopLe = false;
            while (!hopLe) {
                try {
                    diemNhap = Float.parseFloat(scanner.nextLine());
                    hopLe = true;
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
                }
            }
        for (KetQua x : kq) {
    System.out.println("ID đầu điểm: "+ x.getMonHocDauDiem().getId()
            +" | Đầu điểm: " + x.getMonHocDauDiem().getTenDauDiem()
                     + " | Hệ số: " + x.getMonHocDauDiem().getHeSo() 
                     + " | Điểm: " + x.getDiem());
}
    }

private void xemthongkediemLophocphan() {
    listLopHocPhanCuaGiangVien();

    System.out.print("Nhập Id lớp học phần muốn xem thống kê điểm: ");
    int lopHocPhanId = scanner.nextInt();
    scanner.nextLine();

    List<LopHocPhan> checkList = lopHocPhanController.getLopHocPhanbyGiangVien(currentUser.getId());
    boolean check = false;
    for (LopHocPhan lhp : checkList) {
        if (lhp.getId() == lopHocPhanId) {
            check = true;
            break;
        }
    }

    if (!check) {
        System.out.println("Lớp học phần này không thuộc quyền giảng dạy của bạn.");
        return;
    }

    LopHocPhan lhp = lopHocPhanController.getById(lopHocPhanId);
    int monHocId = lhp.getMonHoc().getId();
    List<MonHocDauDiem> dauDiemList = monHocController.getMonHocDauDiemByMonHoc(monHocId);

    System.out.println("Chọn đầu điểm cần xếp hạng:");
    for (int i = 0; i < dauDiemList.size(); i++) {
        System.out.println((i + 1) + ". " + dauDiemList.get(i).getTenDauDiem());
    }

    int chon = getChoice(dauDiemList.size());
    MonHocDauDiem dauDiemDuocChon = dauDiemList.get(chon - 1);

    List<ThanhVien> danhSachSV = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "Sinh viên");

    if (danhSachSV.isEmpty()) {
        System.out.println("Không có sinh viên nào trong lớp học phần này.");
        return;
    }

    float tongDiem = 0;
    int cnt = 0;

    System.out.println("\n===== THỐNG KÊ ĐIỂM '" + dauDiemDuocChon.getTenDauDiem() + "' =====");
    for (ThanhVien sv : danhSachSV) {
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(monHocId, lopHocPhanId);
        if (tg != null) {
            List<KetQua> kqList = ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(), monHocId);
            for (KetQua kq : kqList) {
                if (kq.getMonHocDauDiem().getId() == dauDiemDuocChon.getId()) {
                    System.out.println("Mã SV: " + sv.getMaSV() 
                            + " | Họ tên: " + sv.getHoTen()
                            + " | Điểm: " + kq.getDiem());
                    tongDiem += kq.getDiem();
                    cnt++;
                    break;
                }
            }
        }
    }

    if (cnt == 0) {
        System.out.println("Chưa có điểm nào được nhập cho đầu điểm này.");
        return;
    }

    float diemTB = tongDiem / cnt;
    String capDo;

    if (diemTB >= 8) capDo = "A";
    else if (diemTB >= 7) capDo = "B";
    else if (diemTB >= 6) capDo = "C";
    else if (diemTB >= 5) capDo = "D";
    else capDo = "F";

    System.out.printf(">> Trung bình điểm '%s' của lớp: %.2f → Xếp hạng: %s\n",
            dauDiemDuocChon.getTenDauDiem(), diemTB, capDo);
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

    public void runXL() {
        nguoiXLController.start();
    }
}
