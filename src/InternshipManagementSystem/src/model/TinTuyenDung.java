package model;

public class TinTuyenDung {
    private String maTinTuyenDung;
    private String tieuDe;
    private String moTa;
    private String yeuCau;
    private int soLuong;
    private String hanNop;
    private String maDoanhNghiep;
    private String trangThai;   // "Đang mở" hoặc "Đã đóng"

    public TinTuyenDung() {}

    public TinTuyenDung(String maTinTuyenDung, String tieuDe, String moTa, String yeuCau,
                        int soLuong, String hanNop, String maDoanhNghiep, String trangThai) {
        this.maTinTuyenDung = maTinTuyenDung;
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.yeuCau = yeuCau;
        this.soLuong = soLuong;
        this.hanNop = hanNop;
        this.maDoanhNghiep = maDoanhNghiep;
        this.trangThai = trangThai;
    }

    public String getMaTinTuyenDung() { return maTinTuyenDung; }
    public void setMaTinTuyenDung(String maTinTuyenDung) { this.maTinTuyenDung = maTinTuyenDung; }
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getYeuCau() { return yeuCau; }
    public void setYeuCau(String yeuCau) { this.yeuCau = yeuCau; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getHanNop() { return hanNop; }
    public void setHanNop(String hanNop) { this.hanNop = hanNop; }
    public String getMaDoanhNghiep() { return maDoanhNghiep; }
    public void setMaDoanhNghiep(String maDoanhNghiep) { this.maDoanhNghiep = maDoanhNghiep; }
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}