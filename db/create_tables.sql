

-- =====================================================
-- 1. TÀI KHOẢN (hỗ trợ đăng nhập + liên kết người dùng)
-- =====================================================
CREATE TABLE TaiKhoan (
    maTaiKhoan   VARCHAR2(10) PRIMARY KEY,
    tenDangNhap  VARCHAR2(50) UNIQUE NOT NULL,
    matKhau      VARCHAR2(100) NOT NULL,
    vaiTro       VARCHAR2(20) NOT NULL CHECK (vaiTro IN ('ADMIN','SINHVIEN','DOANHNGHIEP','HR')),
    trangThai    VARCHAR2(20) DEFAULT 'HOAT_DONG',
    maNguoiDung  VARCHAR2(10),           
    tenHienThi   VARCHAR2(100)
);

-- =====================================================
-- 2. SINH VIÊN (bổ sung gpa, trangThai theo model + các trường mở rộng hữu ích)
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
    gpa          NUMBER(3,2),
    trangThai    VARCHAR2(20) DEFAULT 'DANG_HOC',
    maTaiKhoan   VARCHAR2(10),
    CONSTRAINT fk_sinhvien_taikhoan FOREIGN KEY (maTaiKhoan) REFERENCES TaiKhoan(maTaiKhoan)
);

-- =====================================================
-- 3. DOANH NGHIỆP (giữ nguyên theo model)
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
-- 4. CV (bổ sung theo ví dụ trong model + mở rộng)
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
-- 5. TIN TUYỂN DỤNG (ĐẦY ĐỦ theo model Java - bổ sung tất cả trường chi tiết)
-- =====================================================
CREATE TABLE TinTuyenDung (
    maTinTuyenDung   VARCHAR2(10) PRIMARY KEY,
    maDoanhNghiep    VARCHAR2(10),
    tenViTri         VARCHAR2(100) NOT NULL,
    tenDoanhNghiep   VARCHAR2(100),
    diaDiem          VARCHAR2(100),
    soLuong          INT,
    hanNop           DATE,
    trangThai        VARCHAR2(20),
    moTaCongViec     CLOB,
    yeuCauKyNang     CLOB,
    mucLuong         VARCHAR2(100),
    quyenLoi         CLOB,
    hinhThucLamViec  VARCHAR2(50),
    nganhPhuHop      VARCHAR2(100),
    CONSTRAINT fk_tintuyendung_doanhnghiep FOREIGN KEY (maDoanhNghiep) REFERENCES DoanhNghiep(maDoanhNghiep)
);

-- =====================================================
-- 6. ỨNG TUYỂN (giữ nguyên + bổ sung maCV)
-- =====================================================
CREATE TABLE UngTuyen (
    maUngTuyen       VARCHAR2(10) PRIMARY KEY,
    maSinhVien       VARCHAR2(10),
    hoTenSinhVien    VARCHAR2(100),
    maTinTuyenDung   VARCHAR2(10),
    tenViTri         VARCHAR2(100),
    ngayUngTuyen     DATE,
    trangThai        VARCHAR2(20),
    maCV             VARCHAR2(10),
    CONSTRAINT fk_ungtuyen_sinhvien FOREIGN KEY (maSinhVien) REFERENCES SinhVien(maSinhVien),
    CONSTRAINT fk_ungtuyen_tintuyendung FOREIGN KEY (maTinTuyenDung) REFERENCES TinTuyenDung(maTinTuyenDung),
    CONSTRAINT fk_ungtuyen_cv FOREIGN KEY (maCV) REFERENCES CV(maCV)
);

-- =====================================================
-- 7. THÔNG BÁO (bổ sung theo ví dụ model)
-- =====================================================
CREATE TABLE ThongBao (
    maThongBao   VARCHAR2(10) PRIMARY KEY,
    maNguoiNhan  VARCHAR2(10),
    tieuDe       VARCHAR2(200),
    noiDung      CLOB,
    ngayGui      DATE DEFAULT SYSDATE,
    daDoc        CHAR(1) DEFAULT 'N'
);
