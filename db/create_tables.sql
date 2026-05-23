-- =====================================================
-- create_tables.sql - Phiên bản CẬP NHẬT (gần với bản gốc trên Git)
-- Đã bổ sung đầy đủ bảng theo model Java + foreign key
-- Tương thích với code hiện tại của project
-- =====================================================

-- Xóa bảng cũ (nếu cần reset)
DROP TABLE ThongBao CASCADE CONSTRAINTS;
DROP TABLE UngTuyen CASCADE CONSTRAINTS;
DROP TABLE CV CASCADE CONSTRAINTS;
DROP TABLE TinTuyenDung CASCADE CONSTRAINTS;
DROP TABLE SinhVien CASCADE CONSTRAINTS;
DROP TABLE DoanhNghiep CASCADE CONSTRAINTS;
DROP TABLE TaiKhoan CASCADE CONSTRAINTS;

-- =====================================================
-- 1. TÀI KHOẢN (bổ sung để hỗ trợ đăng nhập)
-- =====================================================
CREATE TABLE TaiKhoan (
    maTaiKhoan   VARCHAR2(10) PRIMARY KEY,
    tenDangNhap  VARCHAR2(50) UNIQUE NOT NULL,
    matKhau      VARCHAR2(100) NOT NULL,
    vaiTro       VARCHAR2(20) NOT NULL CHECK (vaiTro IN ('ADMIN','SINHVIEN','DOANHNGHIEP','HR')),
    trangThai    VARCHAR2(20) DEFAULT 'HOAT_DONG'
);

-- =====================================================
-- 2. SINH VIÊN (bổ sung theo model)
-- =====================================================
CREATE TABLE SinhVien (
    maSinhVien   VARCHAR2(10) PRIMARY KEY,
    hoTen        VARCHAR2(100) NOT NULL,
    ngaySinh     DATE,
    gioiTinh     VARCHAR2(10),
    email        VARCHAR2(100),
    soDienThoai  VARCHAR2(15),
    diaChi       VARCHAR2(200),
    maNganh      VARCHAR2(20),
    maTaiKhoan   VARCHAR2(10),
    CONSTRAINT fk_sinhvien_taikhoan FOREIGN KEY (maTaiKhoan) REFERENCES TaiKhoan(maTaiKhoan)
);

-- =====================================================
-- 3. DOANH NGHIỆP (giữ gần như bản gốc + bổ sung)
-- =====================================================
CREATE TABLE DoanhNghiep (
    maDoanhNghiep   VARCHAR2(10) PRIMARY KEY,
    tenDoanhNghiep  VARCHAR2(100) NOT NULL,
    diaChi          VARCHAR2(200),
    email           VARCHAR2(100),
    soDienThoai     VARCHAR2(15),
    linhVuc         VARCHAR2(100),
    moTa            CLOB
);

-- =====================================================
-- 4. CV (bổ sung theo model)
-- =====================================================
CREATE TABLE CV (
    maCV         VARCHAR2(10) PRIMARY KEY,
    maSinhVien   VARCHAR2(10) NOT NULL,
    tenFile      VARCHAR2(200),
    duongDan     VARCHAR2(500),
    ngayCapNhat  DATE DEFAULT SYSDATE,
    CONSTRAINT fk_cv_sinhvien FOREIGN KEY (maSinhVien) REFERENCES SinhVien(maSinhVien)
);

-- =====================================================
-- 5. TIN TUYỂN DỤNG (giữ nguyên cấu trúc bản gốc + thêm FK)
-- =====================================================
CREATE TABLE TinTuyenDung (
    maTinTuyenDung   VARCHAR2(10) PRIMARY KEY,
    maDoanhNghiep    VARCHAR2(10),                    -- Bổ sung FK
    tenViTri         VARCHAR2(100) NOT NULL,
    tenDoanhNghiep   VARCHAR2(100),                   -- Giữ lại như bản gốc
    diaDiem          VARCHAR2(100),
    soLuong          INT,
    hanNop           DATE,
    trangThai        VARCHAR2(20),
    CONSTRAINT fk_tintuyendung_doanhnghiep FOREIGN KEY (maDoanhNghiep) REFERENCES DoanhNghiep(maDoanhNghiep)
);

-- =====================================================
-- 6. ỨNG TUYỂN (giữ nguyên cấu trúc bản gốc + thêm FK)
-- =====================================================
CREATE TABLE UngTuyen (
    maUngTuyen       VARCHAR2(10) PRIMARY KEY,
    maSinhVien       VARCHAR2(10),
    hoTenSinhVien    VARCHAR2(100),                   -- Giữ lại như bản gốc
    maTinTuyenDung   VARCHAR2(10),
    tenViTri         VARCHAR2(100),                   -- Giữ lại như bản gốc
    ngayUngTuyen     DATE,
    trangThai        VARCHAR2(20),
    maCV             VARCHAR2(10),                    -- Bổ sung
    CONSTRAINT fk_ungtuyen_sinhvien FOREIGN KEY (maSinhVien) REFERENCES SinhVien(maSinhVien),
    CONSTRAINT fk_ungtuyen_tintuyendung FOREIGN KEY (maTinTuyenDung) REFERENCES TinTuyenDung(maTinTuyenDung),
    CONSTRAINT fk_ungtuyen_cv FOREIGN KEY (maCV) REFERENCES CV(maCV)
);

-- =====================================================
-- 7. THÔNG BÁO (bổ sung theo model)
-- =====================================================
CREATE TABLE ThongBao (
    maThongBao   VARCHAR2(10) PRIMARY KEY,
    maNguoiNhan  VARCHAR2(10),
    tieuDe       VARCHAR2(200),
    noiDung      CLOB,
    ngayGui      DATE DEFAULT SYSDATE,
    daDoc        CHAR(1) DEFAULT 'N'
);
