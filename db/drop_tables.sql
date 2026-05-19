-- Dữ liệu Doanh Nghiệp
INSERT INTO DoanhNghiep (maDoanhNghiep, tenDoanhNghiep, diaChi, email, soDienThoai, linhVuc, moTa) VALUES 
('DN001', 'Công ty ABC', '123 Nguyễn Trãi, Q1, TP.HCM', 'abc@company.com', '0901234567', 'Công nghệ thông tin', 'Doanh nghiệp hàng đầu về phần mềm'),
('DN002', 'Công ty XYZ', '456 Trần Hưng Đạo, Hà Nội', 'xyz@company.com', '0912345678', 'E-commerce', 'Chuyên về thương mại điện tử'),
('DN003', 'Công ty MNO', '789 Lê Lợi, Đà Nẵng', 'mno@company.com', '0923456789', 'Tài chính - Ngân hàng', 'Ngân hàng số hàng đầu');

-- Dữ liệu Tin Tuyển Dụng
INSERT INTO TinTuyenDung (maTinTuyenDung, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai) VALUES 
('TD001', 'Java Developer', 'Công ty ABC', 'TP.HCM', 5, '2026-06-30', 'Đang mở'),
('TD002', 'Frontend React', 'Công ty XYZ', 'Hà Nội', 3, '2026-07-15', 'Đang mở'),
('TD003', 'Data Analyst', 'Công ty MNO', 'Đà Nẵng', 2, '2026-06-20', 'Đã đóng');

-- Dữ liệu Ứng Tuyển
INSERT INTO UngTuyen (maUngTuyen, maSinhVien, hoTenSinhVien, maTinTuyenDung, tenViTri, ngayUngTuyen, trangThai) VALUES 
('UT001', 'SV001', 'Nguyễn Văn A', 'TD001', 'Java Developer', '2026-05-10', 'Chờ duyệt'),
('UT002', 'SV002', 'Trần Thị B', 'TD001', 'Java Developer', '2026-05-12', 'Đã duyệt'),
('UT003', 'SV003', 'Lê Văn C', 'TD002', 'Frontend React', '2026-05-14', 'Từ chối'),
('UT004', 'SV001', 'Nguyễn Văn A', 'TD003', 'Data Analyst', '2026-05-16', 'Phỏng vấn');