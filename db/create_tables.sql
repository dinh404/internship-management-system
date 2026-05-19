-- Tạo bảng Doanh Nghiệp
CREATE TABLE DoanhNghiep (
    maDoanhNghiep VARCHAR(10) PRIMARY KEY,
    tenDoanhNghiep VARCHAR(100) NOT NULL,
    diaChi VARCHAR(200),
    email VARCHAR(100),
    soDienThoai VARCHAR(15),
    linhVuc VARCHAR(100),
    moTa TEXT
);

-- Tạo bảng Tin Tuyển Dụng
CREATE TABLE TinTuyenDung (
    maTinTuyenDung VARCHAR(10) PRIMARY KEY,
    tenViTri VARCHAR(100) NOT NULL,
    tenDoanhNghiep VARCHAR(100),
    diaDiem VARCHAR(100),
    soLuong INT,
    hanNop DATE,
    trangThai VARCHAR(20)
);

-- Tạo bảng Ứng Tuyển
CREATE TABLE UngTuyen (
    maUngTuyen VARCHAR(10) PRIMARY KEY,
    maSinhVien VARCHAR(10),
    hoTenSinhVien VARCHAR(100),
    maTinTuyenDung VARCHAR(10),
    tenViTri VARCHAR(100),
    ngayUngTuyen DATE,
    trangThai VARCHAR(20)
);