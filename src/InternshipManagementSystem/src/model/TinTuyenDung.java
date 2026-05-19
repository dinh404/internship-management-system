package model;

public class TinTuyenDung {
    private String maTinTuyenDung;
    private String tenViTri;
    private String tenDoanhNghiep;
    private String diaDiem;
    private int soLuong;
    private String hanNop;
    private String trangThai;   // "Đang mở" / "Đã đóng"

    public TinTuyenDung() {}

    public TinTuyenDung(String maTinTuyenDung, String tenViTri, String tenDoanhNghiep,
                        String diaDiem, int soLuong, String hanNop, String trangThai) {
        this.maTinTuyenDung = maTinTuyenDung;
        this.tenViTri = tenViTri;
        this.tenDoanhNghiep = tenDoanhNghiep;
        this.diaDiem = diaDiem;
        this.soLuong = soLuong;
        this.hanNop = hanNop;
        this.trangThai = trangThai;
    }

    // Getter & Setter (dùng Alt + Insert)
    public String getMaTinTuyenDung() { return maTinTuyenDung; }
    public void setMaTinTuyenDung(String maTinTuyenDung) { this.maTinTuyenDung = maTinTuyenDung; }
    public String getTenViTri() { return tenViTri; }
    public void setTenViTri(String tenViTri) { this.tenViTri = tenViTri; }
    public String getTenDoanhNghiep() { return tenDoanhNghiep; }
    public void setTenDoanhNghiep(String tenDoanhNghiep) { this.tenDoanhNghiep = tenDoanhNghiep; }
    public String getDiaDiem() { return diaDiem; }
    public void setDiaDiem(String diaDiem) { this.diaDiem = diaDiem; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getHanNop() { return hanNop; }
    public void setHanNop(String hanNop) { this.hanNop = hanNop; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}