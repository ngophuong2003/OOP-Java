package btl_oop.view;

import btl_oop.model.*;

import java.util.List;
import java.util.Scanner;

public class SinhVienView {
    private final Scanner sc = new Scanner(System.in);

    private void printOut(String s) {
        System.out.println(s);
    }

    public String inputMSV() {
        System.out.print("Nhập mã sinh viên: ");
        return sc.nextLine().trim();
    }

    public int inputMon() {
        while (true) {
            System.out.print("Nhập ID Kết Quả Cần Phản Hồi: ");
            String line = sc.nextLine().trim();
            if (line.matches("\\d+")) {
                return Integer.parseInt(line);
            }
            printOut("Vui lòng nhập lại. Chỉ nhập số.\n");
        }
    }

    public String inputND() {
        String nd;
        while (true) {
            System.out.print("Nhập nội dung phúc khảo: ");
            nd = sc.nextLine().trim();
            if (nd.isEmpty()) {
                System.out.println("Vui lòng không để nội dung phản hồi trống.");
                continue;
            }
            return nd;
        }
    }

    public String inputKY() {
        String ky;
        while (true) {
            System.out.print("Nhập kỳ học cần xem: ");
            ky = sc.nextLine().trim();
            if (ky.isEmpty()) {
                System.out.println("Vui lòng không để thông tin trống.");
                continue;
            }
            return ky;
        }
    }

    public int inputNamHoc() {
        while (true) {
            System.out.print("Nhập năm học: ");
            String line = sc.nextLine().trim();
            if (line.matches("\\d+")) {
                return Integer.parseInt(line);
            }
            printOut("Nhập Lại Đầu Vào Chỉ Là Số\n");
        }
    }

    public int menu() {
        System.out.print("""
                
                1. Xem Tất Cả Các Đầu Điểm
                2. Xem Điểm Thành Phần (Lấy ID Điểm Để Phúc Khảo) 
                3. Gửi Yêu Cầu Phúc Khảo
                4. Xem Những Đơn Phúc Khảo Chưa XL
                5. Xem Những Đơn Phúc Khảo Đã XL
                0. Thoát
                """);
        while (true){
            System.out.print("Chọn: ");
            String line = sc.nextLine().trim();
            if (line.matches("\\d+")){
                return Integer.parseInt(line);
            }
            printOut("Vui lòng nhập lựa chọn chỉ có số.");
        }
    }

    public void xemDiem(List<TongKetDiem> diemThanhPhanList, List<DiemGpa> gpaResults) {
        System.out.println("\n|            Môn Học          |  ĐGK  |  ĐCK  |  ĐTB  | ĐC |     Kỳ     | Năm  |");

        String currentKiHoc = "";
        int currentNam = -1;

        for (int i = 0; i < diemThanhPhanList.size(); i++) {
            TongKetDiem d = diemThanhPhanList.get(i);
            if (!d.getTenKiHoc().equals(currentKiHoc) || d.getNam() != currentNam) {
                if (i != 0) {
                    inGpaChoKy(gpaResults, currentKiHoc, currentNam);
                    System.out.println();
                    System.out.println("|            Môn Học          |  ĐGK  |  ĐCK  |  ĐTB  | ĐC |     Kỳ     | Năm  |");
                }

                currentKiHoc = d.getTenKiHoc();
                currentNam = d.getNam();
            }

            System.out.println(d);
        }

        inGpaChoKy(gpaResults, currentKiHoc, currentNam);
        System.out.println();
    }

    private void inGpaChoKy(List<DiemGpa> gpaResults, String tenKiHoc, int nam) {
        for (DiemGpa g : gpaResults) {
            if (g.getTenKiHoc().equals(tenKiHoc) && g.getNam() == nam) {
                System.out.printf("=> GPA kỳ %s %d: %.2f\n%n", tenKiHoc, nam, g.getGpa());
                break;
            }
        }
    }

    public void hienThiDiemMH(List<Diem> diemList) {
        if (diemList.isEmpty()) {
            System.out.println("Không có điểm thành phần nào thuộc kỳ này.");
        } else {
            System.out.println("|  ID  |           Môn Học          |  Đầu Điểm  |  Năm  |  Kỳ Học  |  Điểm  |");
            for (Diem diem : diemList) {
                System.out.println(diem);
            }
        }
        System.out.println();
    }

    public void showPHCT(List<PhacHoi> reviewList) {
        printOut("");
        for (PhacHoi review : reviewList) {
            System.out.printf("IDPH: %d ,Mã SV: %s, Kết Quả ID: %d\nNội Dung: %s \nNội Dung Xử Lý: %s\n%n", review.getIdPhanHoi(), review.getMaSV(), review.getIDKetQua(), review.getNoiDungPK(), review.getNoiDungXl());
        }
    }

    public void checkID() {
        printOut("ID Chưa Tồn Tại");
    }
}
