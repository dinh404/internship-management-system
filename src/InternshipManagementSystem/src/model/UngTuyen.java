package model;

public class UngTuyen {
    private String maUngTuyen;
    private String maSinhVien;
    private String hoTenSinhVien;
    private String maTinTuyenDung;
    private String tenViTri;
    private String ngayUngTuyen;
    private String trangThai;   // Chờ duyệt, Đã duyệt, Từ chối, Phỏng vấn, Trúng tuyển

    public UngTuyen() {}

    public UngTuyen(String maUngTuyen, String maSinhVien, String hoTenSinhVien,
                    String maTinTuyenDung, String tenViTri, String ngayUngTuyen, String trangThai) {
        this.maUngTuyen = maUngTuyen;
        this.maSinhVien = maSinhVien;
        this.hoTenSinhVien = hoTenSinhVien;
        this.maTinTuyenDung = maTinTuyenDung;
        this.tenViTri = tenViTri;
        this.ngayUngTuyen = ngayUngTuyen;
        this.trangThai = trangThai;
    }

    // ==================== GETTER & SETTER ====================
    public String getMaUngTuyen() { return maUngTuyen; }
    public void setMaUngTuyen(String maUngTuyen) { this.maUngTuyen = maUngTuyen; }

    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }

    public String getHoTenSinhVien() { return hoTenSinhVien; }
    public void setHoTenSinhVien(String hoTenSinhVien) { this.hoTenSinhVien = hoTenSinhVien; }

    public String getMaTinTuyenDung() { return maTinTuyenDung; }
    public void setMaTinTuyenDung(String maTinTuyenDung) { this.maTinTuyenDung = maTinTuyenDung; }

    public String getTenViTri() { return tenViTri; }
    public void setTenViTri(String tenViTri) { this.tenViTri = tenViTri; }

    public String getNgayUngTuyen() { return ngayUngTuyen; }
    public void setNgayUngTuyen(String ngayUngTuyen) { this.ngayUngTuyen = ngayUngTuyen; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}