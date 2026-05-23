-- =====================================================
-- insert_sample_data.sql - Phiên bản cập nhật (7 bảng)
-- Chạy sau khi đã tạo bảng bằng create_tables_updated.sql
-- =====================================================

SET SERVEROUTPUT ON;

PROMPT 'Đang chèn dữ liệu mẫu...';

-- 1. TÀI KHOẢN
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES 
('TK001', 'admin', '123456', 'ADMIN', 'HOAT_DONG');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES 
('TK002', 'sv001', '123456', 'SINHVIEN', 'HOAT_DONG');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES 
('TK003', 'dn001', '123456', 'DOANHNGHIEP', 'HOAT_DONG');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES 
('TK004', 'hr001', '123456', 'HR', 'HOAT_DONG');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES 
('TK005', 'sv002', '123456', 'SINHVIEN', 'HOAT_DONG');
INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau, vaiTro, trangThai) VALUES 
('TK006', 'sv003', '123456', 'SINHVIEN', 'HOAT_DONG');

-- 2. SINH VIÊN
INSERT INTO SinhVien (maSinhVien, hoTen, ngaySinh, gioiTinh, email, soDienThoai, diaChi, maNganh, maTaiKhoan) VALUES 
('SV001', 'Nguyễn Văn A', TO_DATE('2003-05-15','YYYY-MM-DD'), 'Nam', 'nguyenvana@student.edu.vn', '0901234567', '123 Nguyễn Trãi, Q1, TP.HCM', 'CNTT', 'TK002');
INSERT INTO SinhVien (maSinhVien, hoTen, ngaySinh, gioiTinh, email, soDienThoai, diaChi, maNganh, maTaiKhoan) VALUES 
('SV002', 'Trần Thị B', TO_DATE('2004-08-22','YYYY-MM-DD'), 'Nữ', 'tranthib@student.edu.vn', '0912345678', '456 Trần Hưng Đạo, Q5, TP.HCM', 'CNTT', 'TK005');
INSERT INTO SinhVien (maSinhVien, hoTen, ngaySinh, gioiTinh, email, soDienThoai, diaChi, maNganh, maTaiKhoan) VALUES 
('SV003', 'Lê Văn C', TO_DATE('2003-11-10','YYYY-MM-DD'), 'Nam', 'levanc@student.edu.vn', '0923456789', '789 Lê Lợi, Q3, TP.HCM', 'KTPM', 'TK006');

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

-- 5. TIN TUYỂN DỤNG
INSERT INTO TinTuyenDung (maTinTuyenDung, maDoanhNghiep, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai) VALUES 
('TD001', 'DN001', 'Java Backend Developer (Intern)', 'Công ty ABC', 'TP. Hồ Chí Minh', 5, TO_DATE('2026-06-30','YYYY-MM-DD'), 'DANG_MO');
INSERT INTO TinTuyenDung (maTinTuyenDung, maDoanhNghiep, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai) VALUES 
('TD002', 'DN002', 'Frontend React Developer (Intern)', 'Công ty XYZ', 'TP. Hồ Chí Minh (Remote)', 3, TO_DATE('2026-07-15','YYYY-MM-DD'), 'DANG_MO');
INSERT INTO TinTuyenDung (maTinTuyenDung, maDoanhNghiep, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai) VALUES 
('TD003', 'DN001', 'Data Analyst Intern', 'Công ty ABC', 'TP. Hồ Chí Minh', 2, TO_DATE('2026-06-20','YYYY-MM-DD'), 'DA_DONG');

-- 6. ỨNG TUYỂN
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai) VALUES 
('UT001', 'SV001', 'Nguyễn Văn A', 'TD001', 'Java Backend Developer (Intern)', TO_DATE('2026-05-10','YYYY-MM-DD'), 'CHO_DUYET');
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai) VALUES 
('UT002', 'SV002', 'Trần Thị B', 'TD001', 'Java Backend Developer (Intern)', TO_DATE('2026-05-12','YYYY-MM-DD'), 'DA_DUYET');
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai) VALUES 
('UT003', 'SV003', 'Lê Văn C', 'TD002', 'Frontend React Developer (Intern)', TO_DATE('2026-05-14','YYYY-MM-DD'), 'TU_CHOI');
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai) VALUES 
('UT004', 'SV001', 'Nguyễn Văn A', 'TD003', 'Data Analyst Intern', TO_DATE('2026-05-16','YYYY-MM-DD'), 'PHONG_VAN');

-- 7. THÔNG BÁO
INSERT INTO ThongBao (maThongBao, maNguoiNhan, tieuDe, noiDung, ngayGui, daDoc) VALUES 
('TB001', 'TK002', 'Ứng tuyển thành công', 'Bạn đã ứng tuyển thành công vị trí Java Backend Developer tại Công ty ABC.', SYSDATE-2, 'N');
INSERT INTO ThongBao (maThongBao, maNguoiNhan, tieuDe, noiDung, ngayGui, daDoc) VALUES 
('TB002', 'TK003', 'Có ứng viên mới', 'Vị trí Java Backend Developer có 2 ứng viên mới. Vui lòng vào hệ thống để xem hồ sơ.', SYSDATE-1, 'N');

PROMPT '✅ Đã chèn thành công dữ liệu mẫu cho 7 bảng!';
PROMPT 'Tài khoản demo: admin / sv001 / dn001 / hr001 (mật khẩu: 123456)';
