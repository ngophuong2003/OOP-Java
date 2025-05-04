DROP DATABASE IF EXISTS QuanLySinhVien;

CREATE DATABASE QuanLySinhVien;
USE QuanLySinhVien;

-- Tạo cấu trúc cơ sở dữ liệu
CREATE TABLE tblChucVu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tenCV VARCHAR(55)
);

CREATE TABLE tblKhoa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tenKhoa VARCHAR(255),
    maKhoa VARCHAR(45) NOT NULL
);

CREATE TABLE tblThanhVien (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tblChucVuid INT,
    tblKhoaid INT,
    maSV VARCHAR(255) UNIQUE,
    lop VARCHAR(55),
    hoTen VARCHAR(255),
    diaChi VARCHAR(255),
    ngaySinh VARCHAR(255),
    matKhau VARCHAR(255) NOT NULL DEFAULT '1',
    FOREIGN KEY (tblChucVuid) REFERENCES tblChucVu(id),
    FOREIGN KEY (tblKhoaid) REFERENCES tblKhoa(id)
);

CREATE TABLE tblKiHoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tenKiHoc VARCHAR(255)
);

CREATE TABLE tblMonHoc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tenMH VARCHAR(255),
    soTc INT,
    maMH VARCHAR(255) NULL,
    UNIQUE INDEX maMH_UNIQUE (maMH ASC) 
);

CREATE TABLE tblMonHocDauDiem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tenDauDiem VARCHAR(255),
    heSo INT,
    tblMonHocid INT,
    FOREIGN KEY (tblMonHocid) REFERENCES tblMonHoc(id)
);

CREATE TABLE tblLopHocPhan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tblKiHocid INT,
    tblMonHocid INT,
    nhomMonHoc VARCHAR(255),
    siSoToiDa INT NULL DEFAULT NULL,
    namHoc INT,
    FOREIGN KEY (tblKiHocid) REFERENCES tblKiHoc(id),
    FOREIGN KEY (tblMonHocid) REFERENCES tblMonHoc(id)
);

CREATE TABLE tblThamGia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tblThanhVienid INT,
    tblLopHocPhanid INT,
    FOREIGN KEY (tblThanhVienid) REFERENCES tblThanhVien(id),
    FOREIGN KEY (tblLopHocPhanid) REFERENCES tblLopHocPhan(id)
);

CREATE TABLE tblKetQua (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tblMonHocDauDiemid INT,
    tblThamGiaid INT,
    diem FLOAT,
    FOREIGN KEY (tblMonHocDauDiemid) REFERENCES tblMonHocDauDiem(id),
    FOREIGN KEY (tblThamGiaid) REFERENCES tblThamGia(id)
);

CREATE TABLE tblDiemChu (
    id INT PRIMARY KEY AUTO_INCREMENT,
    diemChu VARCHAR(255),
    diemHe10ToiThieu FLOAT,
    diemHe10ToiDa FLOAT,
    diemthang4 FLOAT
);

CREATE TABLE tblPhucKhao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    maSV VARCHAR(255),
    tblKetQuaId INT,
    noiDungPK VARCHAR(500),
    noiDungXL VARCHAR(500) DEFAULT 'Chưa xử lý',
    trangThaiXL VARCHAR(50) DEFAULT 'Chưa xử lý',
    FOREIGN KEY (tblKetQuaId) REFERENCES tblKetQua(id),
    FOREIGN KEY (maSV) REFERENCES tblThanhVien(maSV)
);

-- Thêm dữ liệu cho bảng tblChucVu
INSERT INTO tblChucVu (id, tenCV) VALUES 
(1, 'GV'), -- Giảng viên
(2, 'SV'), -- Sinh viên
(3, 'Admin'), -- Quản trị viên
(4, 'XL'); -- Xử lý (phúc khảo)

-- Thêm dữ liệu cho bảng tblKhoa
INSERT INTO tblKhoa (id, tenKhoa, maKhoa) VALUES 
(1, 'Công nghệ thông tin', 'CNTT'),
(2, 'Quản trị kinh doanh', 'QTKD');

-- Thêm dữ liệu kì học
INSERT INTO tblKiHoc (id, tenKiHoc) VALUES 
(1, '12025'), -- Kì 1 năm học 2025
(2, '22025'); -- Kì 2 năm học 2025

-- Thêm dữ liệu bảng tblMonHoc
INSERT INTO tblMonHoc (id, tenMH, soTc, maMH) VALUES 
(1, 'Cơ sở dữ liệu', 3, 'CSDL001'),
(2, 'Lập trình web', 4, 'LTWD001'),
(3, 'Cấu trúc dữ liệu và giải thuật', 4, 'CTDL001'),
(4, 'Trí tuệ nhân tạo', 3, 'TTNT001');

-- Thêm dữ liệu bảng tblThanhVien
INSERT INTO tblThanhVien (id, tblChucVuid, tblKhoaid, maSV, lop, hoTen, diaChi, ngaySinh, matKhau) VALUES
-- Giảng viên
(1, 1, 1, 'GV001', NULL, 'Nguyễn Thị Minh', 'Hà Nội', '1985-05-15', 'password123'),
(2, 1, 1, 'GV002', NULL, 'Trần Văn Hùng', 'Hà Nội', '1980-08-21', 'password123'),

-- Admin
(3, 3, 1, 'AD001', NULL, 'Trần Minh Quân', 'Hà Nội', '1975-03-12', 'admin123'),

-- Xử lý phúc khảo
(4, 4, 1, 'XL001', NULL, 'Phạm Văn Chính', 'Hà Nội', '1980-10-05', 'password123'),

-- Sinh viên CNTT
(5, 2, 1, 'SV001', 'CNTT01', 'Nguyễn Văn An', 'Hà Nội', '2002-05-10', 'student123'),
(6, 2, 1, 'SV002', 'CNTT01', 'Trần Thị Bình', 'Nam Định', '2002-08-20', 'student123'),
(7, 2, 1, 'SV003', 'CNTT01', 'Lê Văn Cường', 'Hải Phòng', '2001-12-15', 'student123'),

-- Sinh viên QTKD
(8, 2, 2, 'SV004', 'QTKD01', 'Phạm Thị Dung', 'Hà Nội', '2002-03-25', 'student123'),
(9, 2, 2, 'SV005', 'QTKD01', 'Hoàng Văn Đức', 'Bắc Ninh', '2001-07-18', 'student123');

-- Thêm dữ liệu cho bảng tblMonHocDauDiem
INSERT INTO tblMonHocDauDiem (id, tenDauDiem, heSo, tblMonHocid) VALUES
-- Đầu điểm cho môn Cơ sở dữ liệu
(1, 'Điểm chuyên cần', 10, 1),
(2, 'Điểm bài tập', 20, 1),
(3, 'Điểm giữa kỳ', 20, 1),
(4, 'Điểm cuối kỳ', 50, 1),

-- Đầu điểm cho môn Lập trình web
(5, 'Điểm chuyên cần', 10, 2),
(6, 'Điểm bài tập', 20, 2),
(7, 'Điểm đồ án', 30, 2),
(8, 'Điểm cuối kỳ', 40, 2),

-- Đầu điểm cho môn CTDL và giải thuật
(9, 'Điểm chuyên cần', 10, 3),
(10, 'Điểm bài tập', 20, 3),
(11, 'Điểm giữa kỳ', 30, 3),
(12, 'Điểm cuối kỳ', 40, 3);

-- Thêm dữ liệu cho bảng tblLopHocPhan
INSERT INTO tblLopHocPhan (id, tblKiHocid, tblMonHocid, nhomMonHoc, siSoToiDa, namHoc) VALUES
-- Kì 1-2025
(1, 1, 1, 'CSDL.01', 40, 2025),
(2, 1, 2, 'LTWD.01', 35, 2025),
(3, 1, 3, 'CTDL.01', 40, 2025),

-- Kì 2-2025
(4, 2, 1, 'CSDL.02', 40, 2025),
(5, 2, 2, 'LTWD.02', 35, 2025);

-- Thêm dữ liệu cho bảng tblThamGia
INSERT INTO tblThamGia (id, tblThanhVienid, tblLopHocPhanid) VALUES
-- Sinh viên tham gia lớp học phần kì 1-2025
(1, 5, 1), -- SV001 học CSDL.01
(2, 6, 1), -- SV002 học CSDL.01
(3, 7, 1), -- SV003 học CSDL.01
(4, 5, 2), -- SV001 học LTWD.01
(5, 6, 2), -- SV002 học LTWD.01
(6, 7, 3), -- SV003 học CTDL.01
(7, 8, 3), -- SV004 học CTDL.01

-- Sinh viên tham gia lớp học phần kì 2-2025
(8, 7, 4), -- SV003 học CSDL.02
(9, 8, 5), -- SV004 học LTWD.02
(10, 9, 5); -- SV005 học LTWD.02

-- Thêm dữ liệu cho bảng tblKetQua
INSERT INTO tblKetQua (id, tblMonHocDauDiemid, tblThamGiaid, diem) VALUES
-- Điểm của SV001 cho môn CSDL
(1, 1, 1, 9), -- Điểm chuyên cần
(2, 2, 1, 8.5), -- Điểm bài tập
(3, 3, 1, 7.5), -- Điểm giữa kỳ
(4, 4, 1, 8), -- Điểm cuối kỳ

-- Điểm của SV002 cho môn CSDL
(5, 1, 2, 10), -- Điểm chuyên cần
(6, 2, 2, 9), -- Điểm bài tập
(7, 3, 2, 8.5), -- Điểm giữa kỳ
(8, 4, 2, 9), -- Điểm cuối kỳ

-- Điểm của SV003 cho môn CSDL
(9, 1, 3, 8), -- Điểm chuyên cần
(10, 2, 3, 7.5), -- Điểm bài tập
(11, 3, 3, 6.5), -- Điểm giữa kỳ
(12, 4, 3, 7), -- Điểm cuối kỳ

-- Điểm của SV001 cho môn LTWD
(13, 5, 4, 9), -- Điểm chuyên cần
(14, 6, 4, 7), -- Điểm bài tập
(15, 7, 4, 8), -- Điểm đồ án
(16, 8, 4, 7.5), -- Điểm cuối kỳ

-- Điểm của SV002 cho môn LTWD
(17, 5, 5, 8.5), -- Điểm chuyên cần
(18, 6, 5, 8.0), -- Điểm bài tập
(19, 7, 5, 8.5), -- Điểm đồ án
(20, 8, 5, 8.0), -- Điểm cuối kỳ

-- Điểm của SV003 cho môn CTDL
(21, 9, 6, 9.0), -- Điểm chuyên cần
(22, 10, 6, 8.5), -- Điểm bài tập
(23, 11, 6, 7.5), -- Điểm giữa kỳ
(24, 12, 6, 8.0), -- Điểm cuối kỳ

-- Điểm của SV004 cho môn CTDL
(25, 9, 7, 8.0), -- Điểm chuyên cần
(26, 10, 7, 7.5), -- Điểm bài tập
(27, 11, 7, 7.0), -- Điểm giữa kỳ
(28, 12, 7, 7.5); -- Điểm cuối kỳ


-- Thêm dữ liệu cho bảng tblDiemChu
INSERT INTO tblDiemChu (id, diemChu, diemHe10ToiThieu, diemHe10ToiDa, diemthang4) VALUES
(1, 'A+', 9.0, 10.0, 4.0),
(2, 'A', 8.5, 8.9, 3.7),
(3, 'B+', 8.0, 8.4, 3.5),
(4, 'B', 7.0, 7.9, 3.0),
(5, 'C+', 6.5, 6.9, 2.5),
(6, 'C', 5.5, 6.4, 2.0),
(7, 'D+', 5.0, 5.4, 1.5),
(8, 'D', 4.0, 4.9, 1.0),
(9, 'F', 0.0, 3.9, 0.0);

INSERT INTO tblPhucKhao (maSV, tblKetQuaId, noiDungPK)
VALUES ('SV001', 3, 'Tôi nghĩ điểm giữa kỳ chưa phản ánh đúng năng lực của tôi.');
INSERT INTO tblPhucKhao (maSV, tblKetQuaId, noiDungPK, noiDungXL, trangThaiXL)
VALUES ('SV001', 16, 'Tôi xin được xem xét lại điểm cuối kỳ môn Lập trình web.',
        'Sau khi kiểm tra bài thi, điểm đã được xác nhận là chính xác.', 'Đã xử lý');

