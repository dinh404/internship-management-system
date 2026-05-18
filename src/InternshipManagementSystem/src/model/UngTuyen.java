package model;

public class UngTuyen {
    private String maUngTuyen;
    private String maTinTuyenDung;
    private String maSinhVien;
    private String ngayUngTuyen;
    private String trangThai;   // "Chờ duyệt", "Đã duyệt", "Từ chối"
    private String ghiChu;

    public UngTuyen() {}

    public UngTuyen(String maUngTuyen, String maTinTuyenDung, String maSinhVien,
                    String ngayUngTuyen, String trangThai, String ghiChu) {
        this.maUngTuyen = maUngTuyen;
        this.maTinTuyenDung = maTinTuyenDung;
        this.maSinhVien = maSinhVien;
        this.ngayUngTuyen = ngayUngTuyen;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    // Getter & Setter
    public String getMaUngTuyen() { return maUngTuyen; }
    public void setMaUngTuyen(String maUngTuyen) { this.maUngTuyen = maUngTuyen; }
    public String getMaTinTuyenDung() { return maTinTuyenDung; }
    public void setMaTinTuyenDung(String maTinTuyenDung) { this.maTinTuyenDung = maTinTuyenDung; }
    public String getMaSinhVien() { return maSinhVien; }
    public void setMaSinhVien(String maSinhVien) { this.maSinhVien = maSinhVien; }
    public String getNgayUngTuyen() { return ngayUngTuyen; }
    public void setNgayUngTuyen(String ngayUngTuyen) { this.ngayUngTuyen = ngayUngTuyen; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}