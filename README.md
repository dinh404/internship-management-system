# Internship Management & Enterprise Connection - IMEC

Ứng dụng Java Swing mô phỏng hệ thống quản lý thực tập và kết nối doanh nghiệp. Hệ thống hỗ trợ phân quyền giao diện theo vai trò: Quản trị viên, Sinh viên và Doanh nghiệp/HR.

## Chức năng chính

### Quản trị viên
- Quản lý sinh viên
- Quản lý tin tuyển dụng
- Quản lý ứng tuyển
- Xem báo cáo thống kê

### Sinh viên
- Xem tin tuyển dụng
- Quản lý CV
- Theo dõi ứng tuyển của tôi
- Cập nhật thông tin cá nhân

### Doanh nghiệp/HR
- Quản lý tin tuyển dụng của doanh nghiệp
- Xem và xét duyệt hồ sơ ứng viên
- Cập nhật thông tin doanh nghiệp

## Công nghệ sử dụng
- Java
- Java Swing
- Apache NetBeans
- Git/GitHub

## Trạng thái dữ liệu
Phiên bản hiện tại sử dụng dữ liệu mẫu bằng ArrayList để phục vụ demo giao diện và nghiệp vụ. Cơ sở dữ liệu sẽ được tích hợp ở giai đoạn phát triển tiếp theo.

## Tài khoản demo
| Vai trò | Tài khoản | Mật khẩu |
|---|---|---|
| Admin | admin | 123456 |
| Sinh viên | sv001 | 123456 |
| Doanh nghiệp | dn001 | 123456 |
| HR | hr001 | 123456 |

## Hướng dẫn chạy project
1. Clone repository.
2. Mở Apache NetBeans.
3. Chọn Open Project.
4. Mở thư mục:
src/InternshipManagementSystem
5. Chạy Clean and Build.
6. Run project.

## Cấu trúc thư mục
src/InternshipManagementSystem/src/model: lớp thực thể
src/InternshipManagementSystem/src/service: xử lý nghiệp vụ và dữ liệu mẫu
src/InternshipManagementSystem/src/view: giao diện Java Swing
src/InternshipManagementSystem/src/util: tiện ích dùng chung

## Ghi chú
Đây là phiên bản demo phục vụ đồ án Phân tích thiết kế hệ thống. Một số chức năng hiện sử dụng dữ liệu mẫu và chưa kết nối cơ sở dữ liệu thật.
