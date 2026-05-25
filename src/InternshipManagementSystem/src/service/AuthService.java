package service;

import java.util.ArrayList;
import java.util.List;
import model.TaiKhoan;

public class AuthService {

    private final List<TaiKhoan> danhSachTaiKhoan;

    public AuthService() {
        danhSachTaiKhoan = new ArrayList<>();
        initData();
    }

    private void initData() {
        danhSachTaiKhoan.add(new TaiKhoan(
                "admin",
                "123456",
                "ADMIN",
                "ADMIN001",
                "Quản trị viên"
        ));

        danhSachTaiKhoan.add(new TaiKhoan(
                "sv001",
                "123456",
                "SINH_VIEN",
                "SV001",
                "Nguyễn Văn A"
        ));

        danhSachTaiKhoan.add(new TaiKhoan(
                "dn001",
                "123456",
                "DOANH_NGHIEP",
                "DN001",
                "Công ty ABC"
        ));

        danhSachTaiKhoan.add(new TaiKhoan(
                "hr001",
                "123456",
                "HR",
                "DN001",
                "Nhân viên HR - Công ty ABC"
        ));

        danhSachTaiKhoan.add(new TaiKhoan(
                "pdt001",
                "123456",
                "PHONG_DAO_TAO",
                "PDT001",
                "Phòng Đào tạo"
        ));
    }

    public TaiKhoan login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        String inputUsername = username.trim();

        for (TaiKhoan taiKhoan : danhSachTaiKhoan) {
            boolean sameUsername = taiKhoan.getUsername().equalsIgnoreCase(inputUsername);
            boolean samePassword = taiKhoan.getPassword().equals(password);

            if (sameUsername && samePassword) {
                return taiKhoan;
            }
        }

        return null;
    }

    public boolean isValidLogin(String username, String password) {
        return login(username, password) != null;
    }

    public List<TaiKhoan> getDanhSachTaiKhoan() {
        return danhSachTaiKhoan;
    }
}