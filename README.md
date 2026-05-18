# Internship Management System

Ứng dụng quản lý Thực tập và Kết nối doanh nghiệp.

## Công nghệ sử dụng

- Java
- Java Swing
- Oracle Database
- JDBC
- Apache NetBeans
- Oracle SQL Developer
- GitHub

## Cấu trúc thư mục

- `db/`: chứa các file SQL dùng để tạo bảng, xóa bảng và thêm dữ liệu mẫu.
- `src/`: chứa source code chương trình Java Swing.
- `screenshots/`: chứa ảnh chụp giao diện chương trình.

## Kiến trúc mã nguồn

Project được xây dựng theo hướng đối tượng, chia thành các package chính:

- `model`: chứa các lớp thực thể.
- `dao`: chứa các lớp thao tác với cơ sở dữ liệu.
- `service`: chứa logic nghiệp vụ.
- `view`: chứa giao diện Java Swing.
- `config`: chứa cấu hình kết nối cơ sở dữ liệu.
- `util`: chứa các hàm tiện ích dùng chung.