CREATE TABLE USERS (
    User_ID VARCHAR2(20) PRIMARY KEY,
    HoTen VARCHAR2(50) NOT NULL,
    NgaySinh DATE NOT NULL,
    GioiTinh VARCHAR2(10)
        CHECK (GioiTinh IN ('Nam', 'Nữ', 'Khác')),
    DiaChi VARCHAR2(100) NOT NULL,
    Email VARCHAR2(50) UNIQUE NOT NULL
        CHECK (Email LIKE '%@gmail.com'),
    SDT VARCHAR2(10) UNIQUE NOT NULL
        CHECK (LENGTH(SDT) = 10)
);

CREATE TABLE ROLE (
    Role_ID VARCHAR2(20) PRIMARY KEY,
    Role_Name VARCHAR2(50) UNIQUE NOT NULL,
    MoTa VARCHAR2(200) NOT NULL
);

CREATE TABLE ACCOUNT (
    MaTK VARCHAR2(20) PRIMARY KEY,
    User_ID VARCHAR2(20),
    Role_ID VARCHAR2(20),
    MatKhau VARCHAR2(255) NOT NULL,
    TrangThai VARCHAR2(20)
        CHECK (TrangThai IN ('Hoạt động', 'Bị khóa', 'Chưa xác thực')),
    OTP VARCHAR2(10),
    HanOTP DATE,

    CONSTRAINT FK_ACCOUNT_USERS
        FOREIGN KEY (User_ID)
        REFERENCES USERS(User_ID),

    CONSTRAINT FK_ACCOUNT_ROLE
        FOREIGN KEY (Role_ID)
        REFERENCES ROLE(Role_ID)
);

CREATE TABLE SINHVIEN (
    MaSV VARCHAR2(20) PRIMARY KEY,
    User_ID VARCHAR2(20),
    HoTen VARCHAR2(100) NOT NULL,
    Lop VARCHAR2(30) NOT NULL,
    Khoa VARCHAR2(100) NOT NULL,
    TrangThaiViecLam VARCHAR2(30)
        CHECK (TrangThaiViecLam IN
        ('Đã có việc làm', 'Chưa có việc làm', 'Đang tìm việc')),
    NgaySinh DATE NOT NULL,

    CONSTRAINT FK_SINHVIEN_USERS
        FOREIGN KEY (User_ID)
        REFERENCES USERS(User_ID)
);

CREATE TABLE DOANHNGHIEP (
    MaDN VARCHAR2(20) PRIMARY KEY,
    User_ID VARCHAR2(20),
    TenDN VARCHAR2(100) NOT NULL,
    DiaChi VARCHAR2(200) NOT NULL,
    LinhVuc VARCHAR2(100) NOT NULL,
    TrangThaiDuyet VARCHAR2(20)
        CHECK (TrangThaiDuyet IN
        ('Chờ duyệt', 'Đã duyệt', 'Từ chối')),

    CONSTRAINT FK_DOANHNGHIEP_USERS
        FOREIGN KEY (User_ID)
        REFERENCES USERS(User_ID)
);

CREATE TABLE CV (
    CV_ID VARCHAR2(20) PRIMARY KEY,
    MaSV VARCHAR2(20),
    TenCV VARCHAR2(100) NOT NULL,
    FileURL VARCHAR2(255) NOT NULL,
    NgayTai DATE NOT NULL,
    TrangThai VARCHAR2(20)
        CHECK (TrangThai IN
        ('Đang sử dụng', 'Đã xóa', 'Ẩn')),

    CONSTRAINT FK_CV_SINHVIEN
        FOREIGN KEY (MaSV)
        REFERENCES SINHVIEN(MaSV)
);

CREATE TABLE TINTUYENDUNG (
    MaTin VARCHAR2(20) PRIMARY KEY,
    MaDN VARCHAR2(20),
    TenViTri VARCHAR2(100) NOT NULL,
    MoTa VARCHAR2(1000) NOT NULL,
    YeuCau VARCHAR2(1000) NOT NULL,
    SoLuongTuyen NUMBER(3)
        CHECK (SoLuongTuyen > 0),
    NgayDang DATE NOT NULL,
    Luong NUMBER(12,2)
        CHECK (Luong >= 0),
    HanNop DATE NOT NULL,
    TrangThai VARCHAR2(20)
        CHECK (TrangThai IN
        ('Đang tuyển', 'Hết hạn', 'Đã đóng')),

    CONSTRAINT CK_HANNOP
        CHECK (HanNop >= NgayDang),

    CONSTRAINT FK_TINTUYENDUNG_DOANHNGHIEP
        FOREIGN KEY (MaDN)
        REFERENCES DOANHNGHIEP(MaDN)
);

CREATE TABLE DONUNGTUYEN (
    MaDon VARCHAR2(20) PRIMARY KEY,
    MaSV VARCHAR2(20),
    MaTin VARCHAR2(20),
    NgayNop DATE NOT NULL,
    TrangThai VARCHAR2(30)
        CHECK (TrangThai IN
        ('Đang chờ duyệt', 'Đã duyệt', 'Từ chối',
         'Phỏng vấn', 'Trúng tuyển', 'Đã hủy')),
    NhanXet VARCHAR2(500),

    CONSTRAINT FK_DONUNGTUYEN_SINHVIEN
        FOREIGN KEY (MaSV)
        REFERENCES SINHVIEN(MaSV),

    CONSTRAINT FK_DONUNGTUYEN_TINTUYENDUNG
        FOREIGN KEY (MaTin)
        REFERENCES TINTUYENDUNG(MaTin)
);

CREATE TABLE HOSOUNGTUYEN (
    MaHoSo VARCHAR2(20) PRIMARY KEY,
    MaSV VARCHAR2(20),
    MaTin VARCHAR2(20),
    HoTen VARCHAR2(100) NOT NULL,
    DoanhNghiep VARCHAR2(100) NOT NULL,
    ViTriUngTuyen VARCHAR2(100) NOT NULL,
    TrangThai VARCHAR2(30)
        CHECK (TrangThai IN
        ('Đang chờ duyệt', 'Đã duyệt',
         'Từ chối', 'Phỏng vấn', 'Trúng tuyển')),
    NgayNop DATE NOT NULL,

    CONSTRAINT FK_HOSO_SINHVIEN
        FOREIGN KEY (MaSV)
        REFERENCES SINHVIEN(MaSV),

    CONSTRAINT FK_HOSO_TINTUYENDUNG
        FOREIGN KEY (MaTin)
        REFERENCES TINTUYENDUNG(MaTin)
);

CREATE TABLE UNGVIEN (
    MaUV VARCHAR2(20) PRIMARY KEY,
    MaSV VARCHAR2(20),
    CV_ID VARCHAR2(20),
    TrangThaiHoSo VARCHAR2(30)
        CHECK (TrangThaiHoSo IN
        ('Đang chờ duyệt', 'Đã duyệt',
         'Từ chối', 'Phỏng vấn', 'Trúng tuyển')),
    GPA NUMBER(3,2)
        CHECK (GPA BETWEEN 0 AND 10),
    KyNang VARCHAR2(500),

    CONSTRAINT FK_UNGVIEN_SINHVIEN
        FOREIGN KEY (MaSV)
        REFERENCES SINHVIEN(MaSV),

    CONSTRAINT FK_UNGVIEN_CV
        FOREIGN KEY (CV_ID)
        REFERENCES CV(CV_ID)
);

CREATE TABLE BAOCAOTUYENDUNG (
    MaBaoCao VARCHAR2(20) PRIMARY KEY,
    MaDN VARCHAR2(20),
    LoaiBaoCao VARCHAR2(50) NOT NULL,
    NgayTao DATE NOT NULL,
    DinhDang VARCHAR2(20)
        CHECK (DinhDang IN ('PDF', 'Excel', 'Word')),
    SoLuongUngTuyen NUMBER(5)
        CHECK (SoLuongUngTuyen >= 0),
    NoiDungBaoCao VARCHAR2(2000) NOT NULL,

    CONSTRAINT FK_BAOCAO_DOANHNGHIEP
        FOREIGN KEY (MaDN)
        REFERENCES DOANHNGHIEP(MaDN)
);

CREATE TABLE THONGKETYLEVIECLAM (
    MaSV VARCHAR2(20) PRIMARY KEY,
    HoTen VARCHAR2(100) NOT NULL,
    TrangThaiViecLam VARCHAR2(30)
        CHECK (TrangThaiViecLam IN
        ('Đã có việc làm', 'Chưa có việc làm', 'Thực tập')),
    DoanhNghiep VARCHAR2(100),
    NgayCapNhat DATE NOT NULL,

    CONSTRAINT FK_THONGKE_SINHVIEN
        FOREIGN KEY (MaSV)
        REFERENCES SINHVIEN(MaSV)
);

CREATE TABLE CAUHINHHETHONG (
    MaCauHinh VARCHAR2(20) PRIMARY KEY,
    TenCauHinh VARCHAR2(100) NOT NULL,
    NgayCapNhat DATE NOT NULL,
    GiaTri VARCHAR2(500) NOT NULL,
    TrangThai VARCHAR2(20)
        CHECK (TrangThai IN
        ('Hoạt động', 'Ngừng hoạt động'))
);

CREATE TABLE NOIDUNG (
    MaNoiDung VARCHAR2(20) PRIMARY KEY,
    LoaiNoiDung VARCHAR2(50) NOT NULL,
    NoiDungChiTiet VARCHAR2(2000) NOT NULL,
    NguoiDang VARCHAR2(20),
    NgayDang DATE NOT NULL,
    TrangThai VARCHAR2(30)
        CHECK (TrangThai IN
        ('Đang hiển thị', 'Ngừng hiển thị', 'Chờ duyệt')),
    LyDoViPham VARCHAR2(500),

    CONSTRAINT FK_NOIDUNG_USERS
        FOREIGN KEY (NguoiDang)
        REFERENCES USERS(User_ID)
);

-- ROLE
INSERT INTO ROLE VALUES ('R01', 'Admin', 'Quản trị hệ thống');
INSERT INTO ROLE VALUES ('R02', 'SinhVien', 'Sinh viên sử dụng hệ thống');
INSERT INTO ROLE VALUES ('R03', 'DoanhNghiep', 'Doanh nghiệp tuyển dụng');
INSERT INTO ROLE VALUES ('R04', 'HR', 'Nhân sự quản lý tuyển dụng');

-- USERS
INSERT INTO USERS VALUES (
    'U001',
    'Nguyen Van A',
    TO_DATE('2003-05-10','YYYY-MM-DD'),
    'Nam',
    'TP.HCM',
    'vana@gmail.com',
    '0912345678'
);

INSERT INTO USERS VALUES (
    'U002',
    'Tran Thi B',
    TO_DATE('2002-08-15','YYYY-MM-DD'),
    'Nữ',
    'Ha Noi',
    'thib@gmail.com',
    '0911111111'
);

INSERT INTO USERS VALUES (
    'U003',
    'Cong ty ABC',
    TO_DATE('2000-01-01','YYYY-MM-DD'),
    'Khác',
    'Da Nang',
    'abc@gmail.com',
    '0922222222'
);

-- ACCOUNT
INSERT INTO ACCOUNT VALUES (
    'TK001',
    'U001',
    'R02',
    '123456',
    'Hoạt động',
    NULL,
    NULL
);

INSERT INTO ACCOUNT VALUES (
    'TK002',
    'U002',
    'R01',
    '123456',
    'Hoạt động',
    NULL,
    NULL
);

INSERT INTO ACCOUNT VALUES (
    'TK003',
    'U003',
    'R03',
    '123456',
    'Hoạt động',
    NULL,
    NULL
);

-- SINHVIEN
INSERT INTO SINHVIEN VALUES (
    'SV001',
    'U001',
    'Nguyen Van A',
    'CNTT01',
    'Cong nghe thong tin',
    'Đang tìm việc',
    TO_DATE('2003-05-10','YYYY-MM-DD')
);

-- DOANHNGHIEP
INSERT INTO DOANHNGHIEP VALUES (
    'DN001',
    'U003',
    'Cong ty ABC',
    'Da Nang',
    'Cong nghe thong tin',
    'Đã duyệt'
);

-- CV
INSERT INTO CV VALUES (
    'CV001',
    'SV001',
    'CV Java Developer',
    'cv/java_cv.pdf',
    SYSDATE,
    'Đang sử dụng'
);

-- TINTUYENDUNG
INSERT INTO TINTUYENDUNG VALUES (
    'TIN001',
    'DN001',
    'Java Intern',
    'Thuc tap sinh Java',
    'Biet Java co ban',
    5,
    SYSDATE,
    5000000,
    TO_DATE('2026-12-31','YYYY-MM-DD'),
    'Đang tuyển'
);

-- DONUNGTUYEN
INSERT INTO DONUNGTUYEN VALUES (
    'DON001',
    'SV001',
    'TIN001',
    SYSDATE,
    'Đang chờ duyệt',
    NULL
);

-- HOSOUNGTUYEN
INSERT INTO HOSOUNGTUYEN VALUES (
    'HS001',
    'SV001',
    'TIN001',
    'Nguyen Van A',
    'Cong ty ABC',
    'Java Intern',
    'Đang chờ duyệt',
    SYSDATE
);

-- UNGVIEN
INSERT INTO UNGVIEN VALUES (
    'UV001',
    'SV001',
    'CV001',
    'Đang chờ duyệt',
    8.5,
    'Java, SQL, HTML'
);

-- BAOCAOTUYENDUNG
INSERT INTO BAOCAOTUYENDUNG VALUES (
    'BC001',
    'DN001',
    'Thong ke ung tuyen',
    SYSDATE,
    'PDF',
    10,
    'Bao cao tong hop ung vien'
);

-- THONGKETYLEVIECLAM
INSERT INTO THONGKETYLEVIECLAM VALUES (
    'SV001',
    'Nguyen Van A',
    'Thực tập',
    'Cong ty ABC',
    SYSDATE
);

-- CAUHINHHETHONG
INSERT INTO CAUHINHHETHONG VALUES (
    'CFG001',
    'Cho phep dang ky',
    SYSDATE,
    'TRUE',
    'Hoạt động'
);

-- NOIDUNG
INSERT INTO NOIDUNG VALUES (
    'ND001',
    'Tin tuyển dụng',
    'Tuyen dung Java Intern',
    'U003',
    SYSDATE,
    'Đang hiển thị',
    NULL
);

COMMIT;