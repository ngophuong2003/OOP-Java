package btl_oop;

import btl_oop.controller.*;
import btl_oop.model.ChucVu;
import btl_oop.model.KetQua;
import btl_oop.model.Khoa;
import btl_oop.model.KiHoc;
import btl_oop.model.LopHocPhan;
import btl_oop.model.MonHoc;
import btl_oop.model.MonHocDauDiem;
import btl_oop.model.ThamGia;
import btl_oop.model.ThanhVien;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        System.out.println("5. Quản lý lớp học phần");
        System.out.println("6. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    private void menuQuanLyLopHocPhan(){
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ ĐIỂM SINH VIÊN =====");
        System.out.println("===== QUẢN LÍ LỚP HỌC PHẦN =====");
        System.out.println("1. Xem Lớp Học Phần");
        System.out.println("2. Xóa Lớp Học Phần");
        System.out.println("3. Update Lớp Học Phần");
        System.out.println("4. Thêm Lớp Học Phần");
        System.out.println("5. Xem Lớp Học Phần theo ID");
        System.out.println("6. Thoát");
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
                System.out.println("Xin chào Giảng viên!");
                runGV();

            } else if (currentUser.getChucVu().getTenCV().equals("SV")) {
                System.out.println("Xin chào Sinh viên!");
                runSV(currentUser.getMaSV());

            } else {
                System.out.println("Xin chào Người XL!");
                runXL();

            }
        } else {
            System.out.println("Tài khoản hoặc mật khẩu sai!");
            menuDangNhap();

        }
    }

    //===================== LUONG =========================
    private void resetMatKhauGUI() {
        System.out.println("\n===== RESET MẬT KHẨU =====");
        System.out.print("Nhập tài khoản (tên đăng nhập): ");
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


    private void quanLyLopHocPhanGUI() {
        boolean running = true;
        while(running){
            menuQuanLyLopHocPhan();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showLopHocPhan();
                    break;
                case 2:
                    deleteLophocPhan();
                    break;
                case 3:
                    updateLopHocPhan();
                    break;
                case 4:
                    addLopHocPhan();
                    break;
                case 5:
                    showLopHocPhanByID();
                    break;
                case 6:
                    running = false;
                    runAdmin();
            }
        }
    }

    private void showLopHocPhan() {
        System.out.println("Danh sách các lớp học phần:");
        lopHocPhanController.showAllLopHocPhan();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quanLyLopHocPhanGUI();
    }

    private void deleteLophocPhan() {
        System.out.println("Danh sách các lớp học phần:");
        lopHocPhanController.showAllLopHocPhan();
        System.out.println("Nhập ID lớp Học Phần cần xóa:");
        int id = scanner.nextInt();
        lopHocPhanController.DeleteLopHocPhan(id);
        System.out.println("Xóa thành công!");
        lopHocPhanController.showAllLopHocPhan();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quanLyLopHocPhanGUI();
    }

    private void updateLopHocPhan() {
        System.out.println("Danh sách các lớp học phần:");
        lopHocPhanController.showAllLopHocPhan();
        System.out.println("Nhập ID lớp Học Phần cần update:");
        int id = scanner.nextInt();
        LopHocPhan lopHocPhan = lopHocPhanController.getById(id);
        scanner.nextLine();
        System.out.println("Bạn có muốn update sĩ số không? Yes/No");
        String answer = scanner.nextLine();
        answer = answer.toLowerCase();
        if(answer.equals("yes")){
            System.out.println("Nhập sĩ số: ");
            int newss = scanner.nextInt();
            lopHocPhan.setSiSoToiDa(newss);
        }
        scanner.nextLine();
        System.out.println("Bạn có muốn update nhóm môn học không?Yes/No");
        answer = scanner.nextLine();
        answer = answer.toLowerCase();
        if(answer.equals("yes")){
            System.out.println("Nhập nhóm môn học mới: ");
            String newNhomMH = scanner.nextLine();
            lopHocPhan.setNhomMonHoc(newNhomMH);
        }
        System.out.println("Bạn có muốn update năm học không? Yes/No");
        answer = scanner.nextLine();
        answer = answer.toLowerCase();
        if(answer.equals("yes")){
            System.out.println("Nhập năm học mới: ");
            int newyear = scanner.nextInt();
            lopHocPhan.setNamHoc(newyear);
        }
        lopHocPhanController.UpdateLopHocPhan(lopHocPhan);
        System.out.println("Danh sách các lớp học phần sau khi update:");
        lopHocPhanController.showAllLopHocPhan();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quanLyLopHocPhanGUI();
    }

    private void addLopHocPhan() {
        System.out.println("Danh sách các kì học");
        kiHocController.getAllKiHoc();
        System.out.println("Nhập kỳ học ID");
        int kyHocID = scanner.nextInt();
        scanner.nextLine();
        KiHoc kiHoc = (KiHoc) kiHocController.getById(kyHocID);
        System.out.println("Danh sách các môn học: ");
        for (MonHoc x :  monHocController.getAllMonHoc()){
            System.out.println(x.toString());
        }
        System.out.println("Nhập mã môn học: ");
        String maMonHoc = scanner.nextLine();
        MonHoc monHoc = (MonHoc) monHocController.getMonHocByMaMonHoc(maMonHoc);
        System.out.println(monHoc);
        System.out.println("Nhập nhóm môn học: ");
        String nhomMonhoc = scanner.nextLine();
        System.out.println("Nhập sĩ số: ");
        int siso = scanner.nextInt();
        System.out.println("Nhập năm học: ");
        int namHoc = scanner.nextInt();
        LopHocPhan lopHocPhan = new LopHocPhan(kiHoc, monHoc, nhomMonhoc, siso, namHoc);
        lopHocPhanController.AddLopHocPhan(lopHocPhan);
        System.out.println("Thêm thành công");
        System.out.println("Danh sách lớp học phần sau khi thêm");
        lopHocPhanController.showAllLopHocPhan();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quanLyLopHocPhanGUI();
    }

    private void showLopHocPhanByID() {
        System.out.println("Nhập ID lớp Học Phần:");
        int ID = scanner.nextInt();
        LopHocPhan lopHocPhan = lopHocPhanController.getById(ID);
        System.out.println(lopHocPhan.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quanLyLopHocPhanGUI();
    }


    //===================== PHUONG =========================
    public void runAdmin() {
        boolean running = true;
        scanner.nextLine();
        while (running) {
            menuTrangChuAdmin();
            int choice = getChoice(6);
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
                    quanLyLopHocPhanGUI();
                    break;
                case 6:
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
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    xemDanhSachGiangVienGUI();
                    break;
                case 2:
                    themGiangVienGUI();
                    break;
                case 3:
                    xoaGiangVienGUI();
                    break;
                case 4:
                    suaThongTinGiangVienGUI();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }

    private void xemDanhSachGiangVienGUI() {
        System.out.println("Danh sách giảng viên: ");
        List<ThanhVien> tv = thanhVienController.getAllThanhVienByChucVu("GV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }
    }

    private void themGiangVienGUI() {

        khoaController.getByAllKhoa();
        System.out.print("Nhập mã khoa:");
        String maKhoa = "";
        boolean hopLe = false;
        while (!hopLe) {
            maKhoa = scanner.nextLine().trim();
            Khoa tmp = khoaController.getByMaKhoa(maKhoa);
            if (tmp.getId() != -1) {
                hopLe = true;
            } else {
                System.out.print("Nhập lại mã khoa cho đúng: ");
            }
        }

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

        String ngaySinh = "";
        boolean hopLe1 = false;
        while (!hopLe1) {
            ngaySinh = scanner.nextLine().trim();
            if(kiemTraNgaySinh(ngaySinh)) {
                hopLe1 = true;
            } else {
                System.out.print("Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd: ");
            }
        }

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
        System.out.print("Nhập mã giảng viên muốn xóa:");
        int idGV = 0;
        boolean hopLe = false;
        while (!hopLe) {
            String maGV = scanner.nextLine().trim();
            ThanhVien tmp = thanhVienController.getThanhVienByMa(maGV);
            if (tmp.getId() != -1) {
                idGV = tmp.getId();
                hopLe = true;
            } else {
                System.out.print("Nhập lại mã giảng viên cho đúng: ");
            }

        }
        thanhVienController.deleteGiangVien(idGV);
    }

    private void suaThongTinGiangVienGUI() {
        List<ThanhVien> tv = thanhVienController.getAllThanhVienByChucVu("GV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }
        System.out.print("Nhập mã giảng viên muốn sửa thông tin:");
        ThanhVien tvUpdate = new ThanhVien();
        boolean hopLe = false;
        while (!hopLe) {
            String maGV = scanner.nextLine().trim();
            ThanhVien tmp = thanhVienController.getThanhVienByMa(maGV);
            if (tmp.getId() != -1) {
                tvUpdate.setId(tmp.getId());
                tvUpdate.setChucVu(tmp.getChucVu());
                tvUpdate.setKhoa(tmp.getKhoa());
                tvUpdate.setMaSV(tmp.getMaSV());
                tvUpdate.setLop(tmp.getLop());
                tvUpdate.setHoTen(tmp.getHoTen());
                tvUpdate.setDiaChi(tmp.getDiaChi());
                tvUpdate.setNgaySinh(tmp.getNgaySinh());
                tvUpdate.setMatKhau(tmp.getMatKhau());
                hopLe = true;
            } else {
                System.out.print("Nhập lại mã giảng viên cho đúng: ");
            }

        }

        khoaController.getByAllKhoa();
        System.out.println("Khoa hiện tại:" + tvUpdate.getKhoa());
        System.out.print("Nhập mã khoa mới( Enter để bỏ qua):");
        String maKhoa = "";
        boolean hopLe1 = false;
        while (!hopLe1) {
            maKhoa = scanner.nextLine().trim();
            if(maKhoa.equals("")){
                hopLe1=true;
                break;
            }
            Khoa khoa = khoaController.getByMaKhoa(maKhoa);
            if(khoa.getId()!= -1) {
                tvUpdate.setKhoa(khoa);
                hopLe1 = true;
            } else {
                System.out.print("Nhập lại mã khoa cho đúng: ");
            }
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

        System.out.println("Ngày sinh hiện tại:" + tvUpdate.getNgaySinh());
        System.out.print("Nhập ngày sinh mới( Enter để bỏ qua):");
        String ngaySinh = "";
        boolean hopLe2 = false;
        while (!hopLe2) {
            ngaySinh = scanner.nextLine().trim();
            if(ngaySinh.equals("")){
                hopLe2=true;
                break;
            }
            if(kiemTraNgaySinh(ngaySinh)) {
                hopLe2 = true;
                tvUpdate.setNgaySinh(ngaySinh);
            } else {
                System.out.print("Ngày sinh không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd: ");
            }
        }

        System.out.println(tvUpdate);
        thanhVienController.updateGiangVien(tvUpdate);

    }

    private void quanLyMonHocGUI() {
        boolean running = true;
        while (running) {
            menuQuanLyMonHoc();
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    xemDanhSachMonHocGUI();
                    break;
                case 2:
                    themMonHocGUI();
                    break;
                case 3:
                    xoaMonHocGUI();
                    break;
                case 4:
                    suaThongTinMonHocGUI();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
    }

    private void xemDanhSachMonHocGUI() {
        System.out.println("Danh sách môn học: ");
        List<MonHoc> mh = monHocController.getAllMonHoc();
        for (MonHoc x : mh) {
            System.out.println(x);
        }
    }

    private void themMonHocGUI() {
        System.out.print("Nhập tên môn học:");
        String tenMH = scanner.nextLine().trim();

        System.out.print("Nhập số tín chỉ của môn học:");
        int soTC = 0;

        boolean hopLe = false;
        while (!hopLe) {
            try {
                soTC = Integer.parseInt(scanner.nextLine());
                hopLe = true;
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }

        System.out.print("Nhập mã môn học:");
        String maMH ="";
        boolean hopLe1 = false;
        while (!hopLe1) {
            maMH= scanner.nextLine().trim();
            MonHoc mh= monHocController.getMonHocByMaMonHoc(maMH);
            if(mh.getId()==-1){
                hopLe1=true;
            }
            else{
                System.out.print("Mã môn học đã tồn tại, vui lòng nhập mã khác: ");
            }

        }
        MonHoc mh = new MonHoc(tenMH, soTC, maMH);
        monHocController.addMonHoc(mh);
    }

    private void xoaMonHocGUI() {
        List<MonHoc> mh = monHocController.getAllMonHoc();
        for (MonHoc x : mh) {
            System.out.println(x);
        }
        System.out.print("Nhập mã môn học muốn xóa:");
        int idMH = 0;
        boolean hopLe = false;
        while (!hopLe) {
            String maMonHoc = scanner.nextLine();
            MonHoc tmp = monHocController.getMonHocByMaMonHoc(maMonHoc);
            if (tmp.getId() != -1) {
                idMH = tmp.getId();
                hopLe = true;
            } else {
                System.out.print("Nhập lại mã môn học cho đúng: ");
            }

        }
        monHocController.deleteMonHoc(idMH);

    }

    private void suaThongTinMonHocGUI() {
        List<MonHoc> mh = monHocController.getAllMonHoc();
        for (MonHoc x : mh) {
            System.out.println(x);
        }
        System.out.print("Nhập mã môn học muốn sửa thông tin: ");
        MonHoc mhUpdate = new MonHoc();
        boolean hopLe = false;
        while (!hopLe) {
            String maMonHoc = scanner.nextLine();
            MonHoc tmp = monHocController.getMonHocByMaMonHoc(maMonHoc);
            if (tmp.getId() != -1) {
                mhUpdate.setMaMH(tmp.getMaMH());
                mhUpdate.setSoTc(tmp.getSoTc());
                mhUpdate.setTenMH(tmp.getTenMH());
                mhUpdate.setId(tmp.getId());
                hopLe = true;
            } else {
                System.out.print("Nhập lại mã môn học cho đúng: ");
            }

        }

        System.out.println("Tên môn học hiện tại:" + mhUpdate.getTenMH());
        System.out.print("Nhập tên môn học mới( Enter để bỏ qua):");
        String tenMH = scanner.nextLine().trim();
        if (!tenMH.equals("")) {
            mhUpdate.setTenMH(tenMH);
        }

        System.out.println("Số tín chỉ hiện tại:" + mhUpdate.getSoTc());
        System.out.print("Nhập số tín chỉ mới( Enter để bỏ qua):");
        int soTC = 0;
        boolean hopLe1 = false;
        while (!hopLe1) {
            String tmp = scanner.nextLine().trim();
            if (tmp.equals("")) {
                hopLe1 = true;
                break;
            }
            try {
                soTC = Integer.parseInt(tmp);
                mhUpdate.setSoTc(soTC);
                hopLe1 = true;
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }

        System.out.println("Mã môn học hiện tại:" + mhUpdate.getMaMH());
        System.out.print("Nhập mã môn học mới( Enter để bỏ qua):");


        String maMH = "";
        boolean hopLe2 = false;
        while (!hopLe2) {
            maMH = scanner.nextLine().trim();
            if(maMH.equals("")){
                hopLe2 = true;
                break;
            }
            MonHoc tmp= monHocController.getMonHocByMaMonHoc(maMH);
            if(tmp.getId()== -1) {
                mhUpdate.setMaMH(maMH);
                hopLe2 = true;
            } else {
                System.out.print("Mã môn học đã tồn tại, vui lòng nhập mã môn học khác: ");
            }
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
        String tenKiHoc = "";
        boolean hopLe = false;
        while (!hopLe) {
            tenKiHoc = scanner.nextLine().trim();
            boolean tenKiHocTonTai = kiHocController.tenKiHocDaTonTai(tenKiHoc);
            if (tenKiHocTonTai) {
                hopLe = true;
            } else {
                System.out.print("Nhập lại tên kì học cho đúng: ");
            }
        }

        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        System.out.println("Danh sách các môn học: ");
        for (MonHoc x : monHoc) {
            System.out.println(x);
        }
        System.out.print("Nhập mã môn học muốn thêm điểm: ");
        int monHocId = 0;
        boolean hopLe1 = false;
        while (!hopLe1) {
            String maMH = scanner.nextLine().trim();
            MonHoc tmp = monHocController.getMonHocByMaMonHoc(maMH);
            if (tmp.getId() != -1) {
                monHocId = tmp.getId();
                hopLe1 = true;
            } else {
                System.out.print("Nhập lại mã môn học cho đúng: ");
            }
        }

        List<LopHocPhan> lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for (LopHocPhan x : lhp) {
            System.out.println(x);
        }

        System.out.print("Nhập Id lớp học phần muốn thêm điểm: ");
        int lopHocPhanId = 0;
        boolean hopLe2 = false;
        while (!hopLe2) {
            String idLopHocPhanStr =  scanner.nextLine().trim();
            try{
                lopHocPhanId = Integer.parseInt(idLopHocPhanStr);
                for(LopHocPhan x:lhp){
                    if(lopHocPhanId==x.getId()){
                        hopLe2=true;
                    }
                }
                if(!hopLe2){
                    System.out.print("Nhập lại Id lớp học phần cho đúng: ");
                }
            }
            catch(NumberFormatException e){
                System.out.print("Nhập lại Id lớp học phần đúng định dạng số: ");
            }
        }

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }

        System.out.print("Nhập mã sinh viên muốn thêm điểm: ");

       int sinhVienId=0;
        boolean hopLe3 = false;
        while (!hopLe3) {
            int check=0;
            String maSinhVien= scanner.nextLine().trim();
            for(ThanhVien x:tv){
                if(maSinhVien.equals(x.getMaSV())){
                    sinhVienId=x.getId();
                    hopLe3=true;
                    check=1;
                }
            }
            if(check==0){
                System.out.print("Nhập lại mã sinh viên cho đúng: ");
            }
        }

        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(sinhVienId, lopHocPhanId);

        List<MonHocDauDiem> mhdd = monHocController.getMonHocDauDiemByMonHoc(monHocId);
        System.out.println("Nhập lần lượt theo các đầu điểm sau: ");
        for (MonHocDauDiem x : mhdd) {
            float diemNhap = 0;
            boolean hopLe4 = false;
            while (!hopLe4) {
                System.out.print(x.getTenDauDiem() + " " + x.getHeSo() + ": ");
                try {
                    diemNhap = Float.parseFloat(scanner.nextLine());
                    hopLe4 = true;
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
        String tenKiHoc = "";
        boolean hopLe = false;
        while (!hopLe) {
            tenKiHoc = scanner.nextLine().trim();
            boolean tenKiHocTonTai = kiHocController.tenKiHocDaTonTai(tenKiHoc);
            if (tenKiHocTonTai) {
                hopLe = true;
            } else {
                System.out.print("Nhập lại tên kì học cho đúng: ");
            }
        }

        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        System.out.println("Danh sách các môn học: ");
        for (MonHoc x : monHoc) {
            System.out.println(x);
        }
        System.out.print("Nhập mã môn học muốn thêm điểm: ");
        int monHocId = 0;
        boolean hopLe1 = false;
        while (!hopLe1) {
            String maMH = scanner.nextLine().trim();
            MonHoc tmp = monHocController.getMonHocByMaMonHoc(maMH);
            if (tmp.getId() != -1) {
                monHocId = tmp.getId();
                hopLe1 = true;
            } else {
                System.out.print("Nhập lại mã môn học cho đúng: ");
            }
        }

        List<LopHocPhan> lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for (LopHocPhan x : lhp) {
            System.out.println(x);
        }

        System.out.print("Nhập Id lớp học phần muốn thêm điểm: ");
        int lopHocPhanId = 0;
        boolean hopLe2 = false;
        while (!hopLe2) {
            String idLopHocPhanStr =  scanner.nextLine().trim();
            try{
                lopHocPhanId = Integer.parseInt(idLopHocPhanStr);
                for(LopHocPhan x:lhp){
                    if(lopHocPhanId==x.getId()){
                        hopLe2=true;
                    }
                }
                if(!hopLe2){
                    System.out.print("Nhập lại Id lớp học phần cho đúng: ");
                }
            }
            catch(NumberFormatException e){
                System.out.print("Nhập lại Id lớp học phần đúng định dạng số: ");
            }
        }

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }

        System.out.print("Nhập mã sinh viên muốn thêm điểm: ");

        int sinhVienId=0;
        boolean hopLe3 = false;
        while (!hopLe3) {
            int check=0;
            String maSinhVien= scanner.nextLine().trim();
            for(ThanhVien x:tv){
                if(maSinhVien.equals(x.getMaSV())){
                    sinhVienId=x.getId();
                    hopLe3=true;
                    check=1;
                }
            }
            if(check==0){
                System.out.print("Nhập lại mã sinh viên cho đúng: ");
            }
        }
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(sinhVienId, lopHocPhanId);
        System.out.println("Kết quả các đầu điểm môn học của sinh viên: ");
        List<KetQua> kq = ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(), monHocId);
        for (KetQua x : kq) {
            System.out.println(x);
        }
        System.out.print("Nhập Id kết quả đầu điểm muốn xóa: ");
        int kqid=0;
        boolean hopLe4 = false;
        while (!hopLe4) {
            String tmp=scanner.nextLine().trim();
            try{
                kqid = Integer.parseInt(tmp);
                for(KetQua x:kq){
                    if(kqid==x.getId()){
                        hopLe4=true;
                    }
                }
                if(!hopLe4){
                    System.out.print("Nhập lại Id kết quả đầu điểm muốn xóa cho đúng: ");
                }
            }
            catch(NumberFormatException e){
                System.out.print("Nhập lại Id kết quả đầu điểm muốn sửa đúng định dạng số: ");
            }
        }
        ketQuaController.xoaKetQua(kqid);
    }

    private void suaDiemMonHocGUI() {
        kiHocController.getAllKiHoc();
        System.out.print("Nhập tên kì học: ");
        String tenKiHoc = "";
        boolean hopLe = false;
        while (!hopLe) {
            tenKiHoc = scanner.nextLine().trim();
            boolean tenKiHocTonTai = kiHocController.tenKiHocDaTonTai(tenKiHoc);
            if (tenKiHocTonTai) {
                hopLe = true;
            } else {
                System.out.print("Nhập lại tên kì học cho đúng: ");
            }
        }

        List<MonHoc> monHoc = monHocController.getAllMonHoc();
        System.out.println("Danh sách các môn học: ");
        for (MonHoc x : monHoc) {
            System.out.println(x);
        }
        System.out.print("Nhập mã môn học muốn thêm điểm: ");
        int monHocId = 0;
        boolean hopLe1 = false;
        while (!hopLe1) {
            String maMH = scanner.nextLine().trim();
            MonHoc tmp = monHocController.getMonHocByMaMonHoc(maMH);
            if (tmp.getId() != -1) {
                monHocId = tmp.getId();
                hopLe1 = true;
            } else {
                System.out.print("Nhập lại mã môn học cho đúng: ");
            }

        }

        List<LopHocPhan> lhp = lopHocPhanController.getLopHocPhanByMonHocKiHoc(monHocId, tenKiHoc);
        for (LopHocPhan x : lhp) {
            System.out.println(x);
        }

        System.out.print("Nhập Id lớp học phần muốn thêm điểm: ");
        int lopHocPhanId = 0;
        boolean hopLe2 = false;
        while (!hopLe2) {
            String idLopHocPhanStr =  scanner.nextLine().trim();
            try{
                lopHocPhanId = Integer.parseInt(idLopHocPhanStr);
                for(LopHocPhan x:lhp){
                    if(lopHocPhanId==x.getId()){
                        hopLe2=true;
                    }
                }
                if(!hopLe2){
                    System.out.print("Nhập lại Id lớp học phần cho đúng: ");
                }
            }
            catch(NumberFormatException e){
                System.out.print("Nhập lại Id lớp học phần đúng định dạng số: ");
            }
        }

        System.out.println("Danh sách sinh viên thuộc lớp học phần: " + lopHocPhanId);
        List<ThanhVien> tv = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien x : tv) {
            System.out.println(x);
        }

        System.out.print("Nhập mã sinh viên muốn thêm điểm: ");

        int sinhVienId=0;
        boolean hopLe3 = false;
        while (!hopLe3) {
            int check=0;
            String maSinhVien= scanner.nextLine().trim();
            for(ThanhVien x:tv){
                if(maSinhVien.equals(x.getMaSV())){
                    sinhVienId=x.getId();
                    hopLe3=true;
                    check=1;
                }
            }
            if(check==0){
                System.out.print("Nhập lại mã sinh viên cho đúng: ");
            }
        }


        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(sinhVienId, lopHocPhanId);
        System.out.println("Kết quả các đầu điểm môn học của sinh viên: ");
        List<KetQua> kq = ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(), monHocId);
        for (KetQua x : kq) {
            System.out.println(x);
        }

        System.out.print("Nhập Id kết quả đầu điểm muốn sửa: ");
        int kqid=0;
        boolean hopLe4 = false;
        while (!hopLe4) {
            String tmp=scanner.nextLine().trim();
            try{
                kqid = Integer.parseInt(tmp);
                for(KetQua x:kq){
                    if(kqid==x.getId()){
                        hopLe4=true;
                    }
                }
                if(!hopLe4){
                    System.out.print("Nhập lại Id kết quả đầu điểm cho đúng: ");
                }
            }
            catch(NumberFormatException e){
                System.out.print("Nhập lại Id kết quả đầu điểm muốn sửa đúng định dạng số: ");
            }
        }
        System.out.println("Nhập điểm sửa: ");
        float diemNhap = 0;
        boolean hopLe5 = false;
        while (!hopLe5) {
            try {
                diemNhap = Float.parseFloat(scanner.nextLine());
                hopLe5 = true;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
            }
        }
        for (KetQua x : kq) {
            if (x.getId() == kqid) {
                x.setDiem(diemNhap);
                ketQuaController.suaKetQua(x);
            }
        }
    }

    private void menuQuanLyGiangVien() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
        System.out.println("===== QUẢN LÝ GIẢNG VIÊN =====");
        System.out.println("1. Xem danh sách giảng viên");
        System.out.println("2. Thêm giảng viên");
        System.out.println("3. Xóa giảng viên");
        System.out.println("4. Sửa thông tin giảng viên");
        System.out.println("5. Thoát");
        System.out.print("Lựa chọn của bạn: ");
    }

    private void menuQuanLyMonHoc() {
        System.out.println("\n===== HỆ THỐNG QUẢN LÝ HỌC TẬP =====");
        System.out.println("===== QUẢN LÝ MÔN HỌC =====");
        System.out.println("1. Xem danh sách môn học");
        System.out.println("2. Thêm môn học");
        System.out.println("3. Xóa môn học");
        System.out.println("4. Sửa thông tin môn học");
        System.out.println("5. Thoát");
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
                    currentUser = null;
                    menuDangNhap();
                    break;
            }
        }
    }

    //Usecase Thêm,sửa,xóa,xem danh sách sinh viên
    private void xemDanhSachSinhVien() {
        List<ThanhVien> list = thanhVienController.getAllThanhVienByChucVu("SV");
        System.out.println("===== Danh sách sinh viên =====");
        for (ThanhVien sv : list) {
            System.out.println("Mã Sinh Viên: " + sv.getMaSV()
                    + " | Họ tên: " + sv.getHoTen()
                    + " | Địa chỉ: " + sv.getDiaChi()
                    + " | Lớp: " + sv.getLop()
                    + " | Ngày sinh: " + sv.getNgaySinh());
        }
    }

    private void themSinhVienGUI() {
        khoaController.getByAllKhoa();
        String maKhoa = "";
        while (true) {
            System.out.print("Nhập mã khoa cho sinh viên: ");
            String input = scanner.nextLine().trim();
            if (input.equals("")) {
                System.out.println("Mã khoa không được để trống. Xin vui lòng nhập lại!");
                continue;
            }
            List<Khoa> danhSachKhoa = khoaController.getlistKhoa();

            boolean check = false;
            for (Khoa x : danhSachKhoa) {
                if (input.equalsIgnoreCase(x.getMaKhoa())) {
                    check = true;
                    maKhoa = x.getMaKhoa();
                    break;
                }
            }
            if (check) {
                break;
            } else {
                System.out.println("Mã khoa không tồn tại. Xin vui lòng nhập lại!");
            }
        }
        String maSV = "";
        while (true) {
            System.out.print("Nhập mã sinh viên: ");
            String input = scanner.nextLine().trim();
            if (input.equals("")) {
                System.out.println("Mã sinh viên không được để trống. Xin vui lòng nhập lại!");
                continue;
            }
            List<ThanhVien> danhSachSV = thanhVienController.getAllSinhVien();
            boolean check = false;
            for (ThanhVien sv : danhSachSV) {
                if (input.equals(sv.getMaSV())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("Mã sinh viên đã tồn tại. Vui lòng nhập mã khác!");
            } else {
                maSV = input;
                break;
            }
        }
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

        ThanhVien sv = null;
        String maSV = "";

        while (true) {
            System.out.print("Nhập mã sinh viên muốn sửa: ");
            maSV = scanner.nextLine().trim();

            sv = thanhVienController.getThanhVienByMa(maSV);
            if (sv != null && sv.getId() != -1) {
                break;
            } else {
                System.out.println("Không tìm thấy sinh viên có mã này. Vui lòng nhập lại!");
            }
        }

        System.out.print("Nhập mã sinh viên mới: ");
        String newMaSV = scanner.nextLine().trim();
        if (!newMaSV.isEmpty()) {
            sv.setMaSV(newMaSV);
        } else {
            newMaSV = sv.getMaSV();
        }

        System.out.print("Nhập họ và tên mới: ");
        String hoTen = scanner.nextLine().trim();
        if (!hoTen.isEmpty()) {
            sv.setHoTen(hoTen);
        } else {
            hoTen = sv.getHoTen();
        }

        System.out.print("Nhập địa chỉ mới: ");
        String diaChi = scanner.nextLine().trim();
        if (!diaChi.isEmpty()) {
            sv.setDiaChi(diaChi);
        } else {
            diaChi = sv.getDiaChi();
        }

        System.out.print("Nhập mật khẩu mới: ");
        String matKhau = scanner.nextLine().trim();
        if (!matKhau.isEmpty()) {
            sv.setMatKhau(matKhau);
        } else {
            matKhau = sv.getMatKhau();
        }

        System.out.print("Nhập lớp mới: ");
        String lop = scanner.nextLine().trim();
        if (!lop.isEmpty()) {
            sv.setLop(lop);
        } else {
            lop = sv.getLop();
        }

        System.out.print("Nhập ngày sinh mới: ");
        String ngaySinh = scanner.nextLine().trim();
        if (!ngaySinh.isEmpty()) {
            sv.setNgaySinh(ngaySinh);
        } else {
            ngaySinh = sv.getNgaySinh();
        }

        thanhVienController.updateSinhVien(maSV, newMaSV, hoTen, matKhau, diaChi, lop, ngaySinh);
        System.out.println("Cập nhật thông tin sinh viên thành công!");
    }

    private void xoaSinhVienGUI() {
        xemDanhSachSinhVien();
        System.out.print("Nhập mã sinh viên muốn xóa: ");
        String maSV = scanner.nextLine().trim();

        if (!maSV.isEmpty()) {
            ThanhVien sv = thanhVienController.getThanhVienByMa(maSV);
            if (sv == null || sv.getId() == -1) {
                System.out.println("Không tìm thấy sinh viên !");
                return;
            }

            System.out.print("Bạn có chắc chắn muốn xóa sinh viên này? (y/n): ");
            String confirm = scanner.nextLine().trim();
            if (confirm.equals("y")) {
                thanhVienController.deleteSinhVientheoMa(sv.getMaSV());
                
            }
        } else {
            System.out.println("Mã sinh viên không được để trống.");
        }
    }

    private void runGV() {
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
                    currentUser = null;
                    menuDangNhap();
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
            System.out.println("ID lớp học phần: " + lhp.getId()
                    + "| Môn học: " + lhp.getMonHoc().getTenMH()
                    + " | Mã môn học: " + lhp.getMonHoc().getMaMH()
                    + " | Nhóm: " + lhp.getNhomMonHoc()
                    + " | Năm học: " + lhp.getNamHoc()
                    + " | Kì: " + lhp.getKiHoc().getTenKiHoc());
        }

    }

    private void themdiemSinhvienbyGiangvien() {
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
            System.out.println("Mã Sinh Viên: " + sv.getMaSV()
                    + " | Họ tên: " + sv.getHoTen()
                    + " | Địa chỉ: " + sv.getDiaChi()
                    + " | Lớp: " + sv.getLop()
                    + " | Ngày sinh: " + sv.getNgaySinh());
        }
        System.out.print("Nhập mã sinh viên muốn thêm điểm: ");
        String maSV = scanner.nextLine();
        ThanhVien sv = null;
        for (ThanhVien svd : list) {
            if (svd.getMaSV().equals(maSV)) {
                sv = svd;
                break;
            }
        }
        if (sv == null) {
            System.out.println("Mã sinh viên không thuộc lớp học phần này.");
            return;
        }
        LopHocPhan lhp = lopHocPhanController.getById(lopHocPhanId);

        MonHoc monHoc = lhp.getMonHoc();
        int monHocId = monHoc.getId();
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(sv.getId(), lopHocPhanId);
        
        List<MonHocDauDiem> mhdd = monHocController.getMonHocDauDiemByMonHoc(monHocId);
        List<KetQua> exsist = ketQuaController.getAllKetQuaByThamGiaId(tg.getId());
        System.out.println("Nhập lần lượt theo các đầu điểm sau: ");
        for (MonHocDauDiem x : mhdd) {
            boolean isCapnhat = false;
            for (KetQua kqcheck : exsist) {
                if (x.getId() == kqcheck.getMonHocDauDiem().getId()) {
                    System.out.println("Đầu điểm '" + x.getTenDauDiem() + "' đã tồn tại với điểm: " + kqcheck.getDiem());
                    System.out.print("Bạn có muốn cập nhật điểm này không? (y/n): ");
                    String res = scanner.nextLine();
                    if (res.equals("y")) {
                        float diemNhapMoi = 0;
                        boolean hopLeCapNhat = false;
                        while (!hopLeCapNhat) {
                            System.out.print("Nhập điểm mới cho " + x.getTenDauDiem() + ": ");
                            try {
                                diemNhapMoi = Float.parseFloat(scanner.nextLine());
                                hopLeCapNhat = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
                            }
                        }

                        kqcheck.setDiem(diemNhapMoi);
                        ketQuaController.suaKetQua(kqcheck);
                        isCapnhat = true;
                        break;
                    } else {
                        isCapnhat = true;
                        break;
                    }
                }
            }

            if (!isCapnhat) {
                float diemNhap = 0;
                boolean hopLe = false;
                while (!hopLe) {
                    System.out.print("Nhập điểm mới cho " + x.getTenDauDiem() + " " + x.getHeSo() + ": ");
                    try {
                        diemNhap = Float.parseFloat(scanner.nextLine());
                        hopLe = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Vui lòng nhập số hợp lệ (ví dụ: 8.5).");
                    }
                }
                KetQua kqMoi = new KetQua(x, tg, diemNhap);
                ketQuaController.themKetQua(kqMoi);

            }
        }
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
        List<ThanhVien> list = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");
        for (ThanhVien sv : list) {
            System.out.println("Mã Sinh Viên: " + sv.getMaSV()
                    + " | Họ tên: " + sv.getHoTen()
                    + " | Địa chỉ: " + sv.getDiaChi()
                    + " | Lớp: " + sv.getLop()
                    + " | Ngày sinh: " + sv.getNgaySinh());
        }
        System.out.print("Nhập mã sinh viên muốn sửa điểm: ");
        String maSV = scanner.nextLine();
        boolean legit1 = false;
        for (ThanhVien checkSv : list) {
            if (checkSv.getMaSV().equals(maSV)) {
                legit1 = true;
            }
        }
        ThanhVien sv = thanhVienController.getThanhVienByMa(maSV);
        if (sv.getId() == -1 || legit1 == false) {
            System.out.println("Mã sinh viên không hợp lệ hoặc không thuộc lớp học phần này.");
            return;
        }
        LopHocPhan lhp = lopHocPhanController.getById(lopHocPhanId);

        MonHoc monHoc = lhp.getMonHoc();
        int monHocId = monHoc.getId();
        ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(sv.getId(), lopHocPhanId);
        List<KetQua> kq = ketQuaController.getAllKetQuaByThamGiaAndMonHocId(tg.getId(), monHocId);
        if (kq.isEmpty()) {
            System.out.println("Sinh viên này chưa có đầu điểm nào !");
            return;
        } else {

            System.out.println("Kết quả các đầu điểm môn học của sinh viên: ");
            for (KetQua x : kq) {
                System.out.println("Id đầu điểm: " + x.getMonHocDauDiem().getId()
                        + "| Đầu điểm: " + x.getMonHocDauDiem().getTenDauDiem()
                        + " | Hệ số: " + x.getMonHocDauDiem().getHeSo()
                        + " | Điểm: " + x.getDiem());
            }
        }
        int kqid;
        while (true) {
            System.out.println("Nhập Id đầu điểm muốn sửa: ");
            kqid = scanner.nextInt();
            boolean legit = false;
            scanner.nextLine();
            for (KetQua checkkq : kq) {
                if (kqid == checkkq.getMonHocDauDiem().getId()) {
                    legit = true;
                    break;
                }
            }
            if (!legit) {
                System.out.println("Không tìm thấy Id này hãy nhập lại!");
            } else {
                break;
            }
        }
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
            if (x.getId() == kqid) {
                x.setDiem(diemNhap);
                ketQuaController.suaKetQuaNoprint(x);
            }
        }
        for (KetQua x : kq) {
            System.out.println("ID đầu điểm: " + x.getMonHocDauDiem().getId()
                    + " | Đầu điểm: " + x.getMonHocDauDiem().getTenDauDiem()
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

        System.out.println("Chọn đầu điểm cần xem thống kê:");
        for (int i = 0; i < dauDiemList.size(); i++) {
            System.out.println((i + 1) + ". " + dauDiemList.get(i).getTenDauDiem());
        }
        System.out.print("Chọn đầu điểm: ");
        int chon = getChoice(dauDiemList.size());
        MonHocDauDiem dauDiemDuocChon = dauDiemList.get(chon - 1);

        List<ThanhVien> danhSachSV = thanhVienController.getThanhVienThamGiaLopHocPhan(lopHocPhanId, "SV");

        if (danhSachSV.isEmpty()) {
            System.out.println("Không có sinh viên nào trong lớp học phần này.");
            return;
        }

        float tongDiem = 0;
        int cnt = 0;

        System.out.println("\n===== THỐNG KÊ ĐIỂM '" + dauDiemDuocChon.getTenDauDiem() + "' =====");
        for (ThanhVien sv : danhSachSV) {
            ThamGia tg = ketQuaController.getThamGiaByThanhVienAndLopHocPhan(sv.getId(), lopHocPhanId);

            if (tg != null) {
                List<KetQua> kqList = ketQuaController.getAllKetQuaByThamGiaId(tg.getId());
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

        if (diemTB >= 8) {
            capDo = "A";
        } else if (diemTB >= 7) {
            capDo = "B";
        } else if (diemTB >= 6) {
            capDo = "C";
        } else if (diemTB >= 5) {
            capDo = "D";
        } else {
            capDo = "F";
        }

        System.out.printf(">> Trung bình điểm %s của lớp: %.2f → Loại: %s\n", dauDiemDuocChon.getTenDauDiem(), diemTB, capDo);
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



    //===================== PHUC =========================
    public void runSV(String maSV) {
        sinhVienController.start(maSV);
        scanner.nextLine();
        currentUser = null;
        run();
    }

    public void runXL() {
        nguoiXLController.start();
        scanner.nextLine();
        currentUser = null;
        run();
    }

    public static boolean kiemTraNgaySinh(String ngaySinh) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(ngaySinh);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}