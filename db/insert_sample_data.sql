

SET SERVEROUTPUT ON;


-- 1. TÀI KHOẢN
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai, maNguoiDung, tenHienThi) VALUES 
('TK001', 'admin', '123456', 'ADMIN', 'HOAT_DONG', NULL, 'Quản trị viên');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai, maNguoiDung, tenHienThi) VALUES 
('TK002', 'sv001', '123456', 'SINHVIEN', 'HOAT_DONG', 'SV001', 'Nguyễn Văn A');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai, maNguoiDung, tenHienThi) VALUES 
('TK003', 'dn001', '123456', 'DOANHNGHIEP', 'HOAT_DONG', 'DN001', 'Công ty ABC');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai, maNguoiDung, tenHienThi) VALUES 
('TK004', 'hr001', '123456', 'HR', 'HOAT_DONG', 'DN001', 'HR - Công ty ABC');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai, maNguoiDung, tenHienThi) VALUES 
('TK005', 'sv002', '123456', 'SINHVIEN', 'HOAT_DONG', 'SV002', 'Trần Thị B');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai, maNguoiDung, tenHienThi) VALUES 
('TK006', 'sv003', '123456', 'SINHVIEN', 'HOAT_DONG', 'SV003', 'Lê Văn C');

-- 2. SINH VIÊN
INSERT INTO SinhVien (maSinhVien, hoTen, ngaySinh, gioiTinh, email, soDienThoai, diaChi, maNganh, gpa, trangThai, maTaiKhoan) VALUES 
('SV001', 'Nguyễn Văn A', TO_DATE('2003-05-15','YYYY-MM-DD'), 'Nam', 'nguyenvana@student.edu.vn', '0901234567', '123 Nguyễn Trãi, Q1, TP.HCM', 'CNTT', 3.45, 'DANG_HOC', 'TK002');
INSERT INTO SinhVien (maSinhVien, hoTen, ngaySinh, gioiTinh, email, soDienThoai, diaChi, maNganh, gpa, trangThai, maTaiKhoan) VALUES 
('SV002', 'Trần Thị B', TO_DATE('2004-08-22','YYYY-MM-DD'), 'Nữ', 'tranthib@student.edu.vn', '0912345678', '456 Trần Hưng Đạo, Q5, TP.HCM', 'CNTT', 3.72, 'DANG_HOC', 'TK005');
INSERT INTO SinhVien (maSinhVien, hoTen, ngaySinh, gioiTinh, email, soDienThoai, diaChi, maNganh, gpa, trangThai, maTaiKhoan) VALUES 
('SV003', 'Lê Văn C', TO_DATE('2003-11-10','YYYY-MM-DD'), 'Nam', 'levanc@student.edu.vn', '0923456789', '789 Lê Lợi, Q3, TP.HCM', 'KTPM', 3.28, 'DANG_HOC', 'TK006');

-- 3. DOANH NGHIỆP
INSERT INTO DoanhNghiep (maDoanhNghiep, tenDoanhNghiep, diaChi, email, soDienThoai, linhVuc, moTa) VALUES 
('DN001', 'Công ty Công nghệ ABC', 'Tầng 15, Tòa nhà Bitexco, Q1, TP.HCM', 'hr@abc-tech.vn', '02812345678', 'Công nghệ thông tin', 'Công ty hàng đầu về phát triển phần mềm và AI');
INSERT INTO DoanhNghiep (maDoanhNghiep, tenDoanhNghiep, diaChi, email, soDienThoai, linhVuc, moTa) VALUES 
('DN002', 'Công ty XYZ E-commerce', 'Tòa nhà Landmark 81, Bình Thạnh, TP.HCM', 'tuyendung@xyz.vn', '02898765432', 'Thương mại điện tử', 'Nền tảng thương mại điện tử lớn nhất Việt Nam');

-- 4. CV
INSERT INTO CV (maCV, maSinhVien, tenFile, duongDan, ngayCapNhat) VALUES 
('CV001', 'SV001', 'CV_NguyenVanA_2026.pdf', '/uploads/cv/CV_NguyenVanA_2026.pdf', SYSDATE);
INSERT INTO CV (maCV, maSinhVien, tenFile, duongDan, ngayCapNhat) VALUES 
('CV002', 'SV002', 'CV_TranThiB_2026.pdf', '/uploads/cv/CV_TranThiB_2026.pdf', SYSDATE);
INSERT INTO CV (maCV, maSinhVien, tenFile, duongDan, ngayCapNhat) VALUES 
('CV003', 'SV003', 'CV_LeVanC_2026.pdf', '/uploads/cv/CV_LeVanC_2026.pdf', SYSDATE);

-- 5. TIN TUYỂN DỤNG (đầy đủ các trường chi tiết)
INSERT INTO TinTuyenDung (maTinTuyenDung, maDoanhNghiep, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai, 
    moTaCongViec, yeuCauKyNang, mucLuong, quyenLoi, hinhThucLamViec, nganhPhuHop) VALUES 
('TD001', 'DN001', 'Java Backend Developer (Intern)', 'Công ty ABC', 'TP. Hồ Chí Minh', 5, TO_DATE('2026-06-30','YYYY-MM-DD'), 'DANG_MO',
    'Phát triển backend Java Spring Boot, làm việc với database Oracle.', 
    'Java, Spring Boot, SQL, Git. Ưu tiên biết Oracle.', 
    'Thỏa thuận (8-12 triệu VND/tháng)', 
    'Laptop, bảo hiểm, cơ hội chuyển chính thức.', 
    'Full-time onsite', 'CNTT');

INSERT INTO TinTuyenDung (maTinTuyenDung, maDoanhNghiep, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai, 
    moTaCongViec, yeuCauKyNang, mucLuong, quyenLoi, hinhThucLamViec, nganhPhuHop) VALUES 
('TD002', 'DN002', 'Frontend React Developer (Intern)', 'Công ty XYZ', 'TP. Hồ Chí Minh (Remote)', 3, TO_DATE('2026-07-15','YYYY-MM-DD'), 'DANG_MO',
    'Xây dựng giao diện React cho nền tảng e-commerce.', 
    'ReactJS, TypeScript, TailwindCSS, REST API.', 
    'Thỏa thuận (7-10 triệu VND/tháng)', 
    'Remote, linh hoạt thời gian, thưởng performance.', 
    'Remote/Hybrid', 'CNTT');

INSERT INTO TinTuyenDung (maTinTuyenDung, maDoanhNghiep, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai, 
    moTaCongViec, yeuCauKyNang, mucLuong, quyenLoi, hinhThucLamViec, nganhPhuHop) VALUES 
('TD003', 'DN001', 'Data Analyst Intern', 'Công ty ABC', 'TP. Hồ Chí Minh', 2, TO_DATE('2026-06-20','YYYY-MM-DD'), 'DA_DONG',
    'Phân tích dữ liệu, xây dựng dashboard Power BI.', 
    'SQL, Python, Power BI, Excel nâng cao.', 
    'Thỏa thuận (6-9 triệu VND/tháng)', 
    'Mentor 1-1, cơ hội full-time.', 
    'Full-time onsite', 'Khoa học dữ liệu');

-- 6. ỨNG TUYỂN
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai, maCV) VALUES 
('UT001', 'SV001', 'Nguyễn Văn A', 'TD001', 'Java Backend Developer (Intern)', TO_DATE('2026-05-10','YYYY-MM-DD'), 'CHO_DUYET', 'CV001');
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai, maCV) VALUES 
('UT002', 'SV002', 'Trần Thị B', 'TD001', 'Java Backend Developer (Intern)', TO_DATE('2026-05-12','YYYY-MM-DD'), 'DA_DUYET', 'CV002');
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai, maCV) VALUES 
('UT003', 'SV003', 'Lê Văn C', 'TD002', 'Frontend React Developer (Intern)', TO_DATE('2026-05-14','YYYY-MM-DD'), 'TU_CHOI', 'CV003');
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai, maCV) VALUES 
('UT004', 'SV001', 'Nguyễn Văn A', 'TD003', 'Data Analyst Intern', TO_DATE('2026-05-16','YYYY-MM-DD'), 'PHONG_VAN', 'CV001');

-- 7. THÔNG BÁO
INSERT INTO ThongBao (maThongBao, maNguoiNhan, tieuDe, noiDung, ngayGui, daDoc) VALUES 
('TB001', 'TK002', 'Ứng tuyển thành công', 'Bạn đã ứng tuyển thành công vị trí Java Backend Developer tại Công ty ABC.', SYSDATE-2, 'N');
INSERT INTO ThongBao (maThongBao, maNguoiNhan, tieuDe, noiDung, ngayGui, daDoc) VALUES 
('TB002', 'TK003', 'Có ứng viên mới', 'Vị trí Java Backend Developer có 2 ứng viên mới. Vui lòng vào hệ thống để xem hồ sơ.', SYSDATE-1, 'N');
INSERT INTO ThongBao (maThongBao, maNguoiNhan, tieuDe, noiDung, ngayGui, daDoc) VALUES 
('TB003', 'TK002', 'Kết quả ứng tuyển', 'Chúc mừng! Bạn đã được mời phỏng vấn cho vị trí Data Analyst Intern.', SYSDATE, 'N');
