package model;

public class DoanhNghiep {
    private String maDoanhNghiep;
    private String tenDoanhNghiep;
    private String diaChi;
    private String email;
    private String soDienThoai;
    private String linhVuc;
    private String moTa;

    public DoanhNghiep() {}

    public DoanhNghiep(String maDoanhNghiep, String tenDoanhNghiep, String diaChi, 
                       String email, String soDienThoai, String linhVuc, String moTa) {
        this.maDoanhNghiep = maDoanhNghiep;
        this.tenDoanhNghiep = tenDoanhNghiep;
        this.diaChi = diaChi;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.linhVuc = linhVuc;
        this.moTa = moTa;
    }

    // Getter & Setter (dùng Alt + Insert để tạo nhanh)
    public String getMaDoanhNghiep() { return maDoanhNghiep; }
    public void setMaDoanhNghiep(String maDoanhNghiep) { this.maDoanhNghiep = maDoanhNghiep; }
    public String getTenDoanhNghiep() { return tenDoanhNghiep; }
    public void setTenDoanhNghiep(String tenDoanhNghiep) { this.tenDoanhNghiep = tenDoanhNghiep; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getLinhVuc() { return linhVuc; }
    public void setLinhVuc(String linhVuc) { this.linhVuc = linhVuc; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}