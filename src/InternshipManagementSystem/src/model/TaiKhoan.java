package model;

public class TaiKhoan {

    private String username;
    private String password;
    private String role;
    private String maNguoiDung;
    private String tenHienThi;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String password, String role, String maNguoiDung, String tenHienThi) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.maNguoiDung = maNguoiDung;
        this.tenHienThi = tenHienThi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenHienThi() {
        return tenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }

    public boolean isSinhVien() {
        return "SINH_VIEN".equalsIgnoreCase(role);
    }

    public boolean isDoanhNghiep() {
        return "DOANH_NGHIEP".equalsIgnoreCase(role);
    }

    public boolean isHR() {
        return "HR".equalsIgnoreCase(role);
    }
}