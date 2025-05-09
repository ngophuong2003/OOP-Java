package btl_oop.view;

import btl_oop.model.Diem;
import btl_oop.model.PhacHoi;

import java.util.List;
import java.util.Scanner;

public class NguoiXLView {
    private final Scanner sc = new Scanner(System.in);

    private void printOut(String s) {
        System.out.print(s);
    }

    public double inputDiem() {
        double diem;
        while (true) {
            printOut("Nhập điểm: ");
            String line = sc.nextLine().trim();
            try {
                diem = Double.parseDouble(line);
                if (diem < 0 || diem > 10) {
                    printOut("Vui lòng nhập số từ 0 đến 10\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                printOut("Vui lòng nhập một số hợp lệ (từ 0 đến 10)\n");
            }
        }
        return diem;
    }

    public int inputIDPH() {
        while (true) {
            printOut("Nhập ID Phản Hồi: ");
            String line = sc.nextLine().trim();
            if (!line.matches("\\d+")) {
                printOut("Vui lòng nhập đầu vào chỉ có số\n");
                continue;
            }
            return Integer.parseInt(line);
        }
    }

    public String inputND() {
        String nd;
        while (true) {
            printOut("Nhập Nội Dung Xử Lý: ");
            nd = sc.nextLine().trim();
            if (nd.isEmpty()) {
                printOut("Nội dung không được để trống. Vui lòng nhập lại.\n");
                continue;
            }
            break;
        }
        return nd;
    }

    public int inputIDKQ() {
        while (true) {
            printOut("Nhập ID KQ Cần Chỉnh: ");
            String line = sc.nextLine().trim();
            if (!line.matches("\\d+")) {
                printOut("Vui lòng nhập đầu vào chỉ có số\n");
                continue;
            }
            return Integer.parseInt(line);
        }
    }

    public int menu() {
        System.out.print("""
                
                1. Xem Phản Hồi Chưa Xử Lý
                2. Xem Phản Hồi Đã Xử Lý
                0. Thoát
                """);
        while (true) {
            System.out.print("Chọn: ");
            String line = sc.nextLine().trim();
            if (line.matches("\\d+")) {
                return Integer.parseInt(line);
            }
            printOut("Vui lòng nhập lựa chọn chỉ có số.");
        }
    }

    public int ViewPhanHoiChuaXL() {
        System.out.print("""
                
                1. Xem Phản Hồi Cơ Bản
                2. Xem Phản Hồi Chi Tiết
                3. Xem Điểm Bị Phản Hồi
                4. Sửa Điểm
                5. Xác Nhận Phản Hồi Đã Xử Lý
                0. Thoát
                """);
        while (true) {
            System.out.print("Chọn: ");
            String line = sc.nextLine().trim();
            if (line.matches("\\d+")) {
                return Integer.parseInt(line);
            }
            printOut("Vui lòng nhập lựa chọn chỉ có số.");
        }
    }

    public int ViewPhanHoiDaXL() {
        System.out.print("""
                
                1. Xem Cơ Bản
                2. Xem Chi Tiết
                0. Thoát
                """);
        while (true) {
            System.out.print("Chọn: ");
            String line = sc.nextLine().trim();
            if (line.matches("\\d+")) {
                return Integer.parseInt(line);
            }
            printOut("Vui lòng nhập lựa chọn chỉ có số.");
        }
    }

    public void showPHCB(List<PhacHoi> reviewList) {
        printOut("Những Phản Hồi\n");
        for (PhacHoi review : reviewList) {
            System.out.printf("IDPH: %d ,Mã SV: %s, Kết Quả ID: %d\n", review.getIdPhanHoi(), review.getMaSV(), review.getIDKetQua());
        }
    }

    public void showPHCT(PhacHoi review) {
        System.out.printf("\nIDPH: %d ,Mã SV: %s, Kết Quả ID: %d\nNội Dung: %s \nNội Dung Xử Lý: %s\n", review.getIdPhanHoi(), review.getMaSV(), review.getIDKetQua(), review.getNoiDungPK(), review.getNoiDungXl());
    }

    public void showDiem(Diem diem) {
        System.out.println("\n|  ID  |           Môn Học          |  Đầu Điểm  |  Năm  |  Kỳ Học  |  Điểm  |");
        System.out.println(diem);
    }

    public void errorChoose() {
        printOut("Nhập lại lựa chọn của bạn\n");
    }

    public void checkIDKQ() {
        printOut("IDKQ Chưa Tồn Tại.. Vui Lòng Nhập Lại\n");
    }
}
