package service;

import java.util.ArrayList;
import java.util.List;
import model.SinhVien;

public class SinhVienService {

    private final List<SinhVien> danhSachSinhVien;

    public SinhVienService() {
        danhSachSinhVien = new ArrayList<>();
        khoiTaoDuLieuMau();
    }

    private void khoiTaoDuLieuMau() {
        danhSachSinhVien.add(new SinhVien("SV001", "Nguyễn Văn A", "nguyenvana@gmail.com", "0901111222", "Công nghệ thông tin", 3.2, "Đang thực tập"));
        danhSachSinhVien.add(new SinhVien("SV002", "Trần Thị B", "tranthib@gmail.com", "0902222333", "Hệ thống thông tin", 3.5, "Đang ứng tuyển"));
        danhSachSinhVien.add(new SinhVien("SV003", "Lê Văn C", "levanc@gmail.com", "0903333444", "Khoa học dữ liệu", 3.0, "Chưa thực tập"));
    }

    public List<SinhVien> getAll() {
        return danhSachSinhVien;
    }

    public boolean add(SinhVien sinhVien) {
        if (findById(sinhVien.getMaSinhVien()) != null) {
            return false;
        }

        danhSachSinhVien.add(sinhVien);
        return true;
    }

    public boolean update(SinhVien sinhVien) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getMaSinhVien().equalsIgnoreCase(sinhVien.getMaSinhVien())) {
                danhSachSinhVien.set(i, sinhVien);
                return true;
            }
        }

        return false;
    }

    public boolean delete(String maSinhVien) {
        SinhVien sinhVien = findById(maSinhVien);

        if (sinhVien == null) {
            return false;
        }

        danhSachSinhVien.remove(sinhVien);
        return true;
    }

    public List<SinhVien> search(String keyword) {
        List<SinhVien> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAll();
        }

        String lowerKeyword = keyword.toLowerCase().trim();

        for (SinhVien sinhVien : danhSachSinhVien) {
            if (sinhVien.getMaSinhVien().toLowerCase().contains(lowerKeyword)
                    || sinhVien.getHoTen().toLowerCase().contains(lowerKeyword)
                    || sinhVien.getEmail().toLowerCase().contains(lowerKeyword)
                    || sinhVien.getNganhHoc().toLowerCase().contains(lowerKeyword)
                    || sinhVien.getTrangThai().toLowerCase().contains(lowerKeyword)) {
                result.add(sinhVien);
            }
        }

        return result;
    }

    public SinhVien findById(String maSinhVien) {
        if (maSinhVien == null) {
            return null;
        }

        for (SinhVien sinhVien : danhSachSinhVien) {
            if (sinhVien.getMaSinhVien().equalsIgnoreCase(maSinhVien.trim())) {
                return sinhVien;
            }
        }

        return null;
    }
}