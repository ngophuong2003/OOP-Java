package btl_oop;

import btl_oop.controller.ThanhVienController;
import btl_oop.model.ThanhVien;
import java.util.List;
import java.util.Scanner;

public class BTL_OOP {
    private Scanner scanner;
    private ThanhVien currentUser;
    private ThanhVienController thanhVienController;

    public BTL_OOP() {
        scanner = new Scanner(System.in);
        thanhVienController = new ThanhVienController();
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
                int choice = getChoice(3,scanner);
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
        currentUser = new ThanhVienController().getThanhVienById(1);
//        if(currentUser.getChucVu().getTenCV().equals("Admin")){
            runAdmin();
//        }
//        else if(currentUser.getChucVu().getTenCV().equals("GV")){
//            
//        }
//        else{
//            
//        }   
    }
   

    private void registerGUI() {
        System.out.println("Đăng ký");
    }
    
    //===================== LUONG =========================
    
    
    //===================== PHUONG =========================
     public void runAdmin() {
        boolean running = true;
        while (running) {
            menuTrangChuAdmin();
            int choice = getChoice(5,scanner);
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
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống. Tạm biệt!");
                    break;
            } 
        }
    }
     
    private void quanLyGiangVienGUI(){
        System.out.println("Quản lý giảng viên");
        List<ThanhVien> thanhVien = thanhVienController.getAllThanhVienByChucVu("GV");
        for(ThanhVien x : thanhVien){
            System.out.println(x);
        }
        
    }
    private void quanLyMonHocGUI(){
        System.out.println("Quản lý môn học");
        
    }
    private void quanLyDiemGUI(){
        System.out.println("Quản lý điểm");
        
    }
    
    //===================== PHUONG =========================
    
    //===================== THANH =========================
    private void quanLySinhVienGUI(){
        System.out.println("Quản lý sinh viên");
    }
    
    
    //===================== THANH =========================
    
    public int getChoice(int maxChoice, Scanner scanner) {
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
}
