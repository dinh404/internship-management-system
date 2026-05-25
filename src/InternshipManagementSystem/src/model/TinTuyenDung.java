package model;

public class TinTuyenDung {

    private String maTinTuyenDung;
    private String tenViTri;
    private String tenDoanhNghiep;
    private String diaDiem;
    private int soLuong;
    private String hanNop;
    private String trangThai;   // "Chờ duyệt" / "Đã duyệt" / "Từ chối" / "Đang mở" / "Đã đóng"

    private String moTaCongViec;
    private String yeuCauKyNang;
    private String mucLuong;
    private String quyenLoi;
    private String hinhThucLamViec;
    private String nganhPhuHop;

    public TinTuyenDung() {
    }

    public TinTuyenDung(String maTinTuyenDung, String tenViTri, String tenDoanhNghiep,
            String diaDiem, int soLuong, String hanNop, String trangThai) {
        this(
                maTinTuyenDung,
                tenViTri,
                tenDoanhNghiep,
                diaDiem,
                soLuong,
                hanNop,
                trangThai,
                "Chưa cập nhật mô tả công việc.",
                "Chưa cập nhật yêu cầu kỹ năng.",
                "Thỏa thuận",
                "Chưa cập nhật quyền lợi.",
                "Chưa cập nhật hình thức làm việc.",
                "Chưa cập nhật ngành phù hợp."
        );
    }

    public TinTuyenDung(String maTinTuyenDung, String tenViTri, String tenDoanhNghiep,
            String diaDiem, int soLuong, String hanNop, String trangThai,
            String moTaCongViec, String yeuCauKyNang, String mucLuong,
            String quyenLoi, String hinhThucLamViec, String nganhPhuHop) {
        this.maTinTuyenDung = maTinTuyenDung;
        this.tenViTri = tenViTri;
        this.tenDoanhNghiep = tenDoanhNghiep;
        this.diaDiem = diaDiem;
        this.soLuong = soLuong;
        this.hanNop = hanNop;
        this.trangThai = trangThai;
        this.moTaCongViec = moTaCongViec;
        this.yeuCauKyNang = yeuCauKyNang;
        this.mucLuong = mucLuong;
        this.quyenLoi = quyenLoi;
        this.hinhThucLamViec = hinhThucLamViec;
        this.nganhPhuHop = nganhPhuHop;
    }

    public String getMaTinTuyenDung() {
        return maTinTuyenDung;
    }

    public void setMaTinTuyenDung(String maTinTuyenDung) {
        this.maTinTuyenDung = maTinTuyenDung;
    }

    public String getTenViTri() {
        return tenViTri;
    }

    public void setTenViTri(String tenViTri) {
        this.tenViTri = tenViTri;
    }

    public String getTenDoanhNghiep() {
        return tenDoanhNghiep;
    }

    public void setTenDoanhNghiep(String tenDoanhNghiep) {
        this.tenDoanhNghiep = tenDoanhNghiep;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHanNop() {
        return hanNop;
    }

    public void setHanNop(String hanNop) {
        this.hanNop = hanNop;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTaCongViec() {
        return moTaCongViec;
    }

    public void setMoTaCongViec(String moTaCongViec) {
        this.moTaCongViec = moTaCongViec;
    }

    public String getYeuCauKyNang() {
        return yeuCauKyNang;
    }

    public void setYeuCauKyNang(String yeuCauKyNang) {
        this.yeuCauKyNang = yeuCauKyNang;
    }

    public String getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(String mucLuong) {
        this.mucLuong = mucLuong;
    }

    public String getQuyenLoi() {
        return quyenLoi;
    }

    public void setQuyenLoi(String quyenLoi) {
        this.quyenLoi = quyenLoi;
    }

    public String getHinhThucLamViec() {
        return hinhThucLamViec;
    }

    public void setHinhThucLamViec(String hinhThucLamViec) {
        this.hinhThucLamViec = hinhThucLamViec;
    }

    public String getNganhPhuHop() {
        return nganhPhuHop;
    }

    public void setNganhPhuHop(String nganhPhuHop) {
        this.nganhPhuHop = nganhPhuHop;
    }
}
