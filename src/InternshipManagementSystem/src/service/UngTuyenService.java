package service;

import model.UngTuyen;
import java.util.*;

public class UngTuyenService {
    private List<UngTuyen> danhSach = new ArrayList<>();

    public UngTuyenService() {
        // Dữ liệu mẫu
        danhSach.add(new UngTuyen("UT001", "SV001", "Nguyễn Văn A", "TD001", "Java Developer", "10/05/2026", "Chờ duyệt"));
        danhSach.add(new UngTuyen("UT002", "SV002", "Trần Thị B", "TD001", "Java Developer", "12/05/2026", "Đã duyệt"));
        danhSach.add(new UngTuyen("UT003", "SV003", "Lê Văn C", "TD002", "Frontend React", "14/05/2026", "Từ chối"));
        danhSach.add(new UngTuyen("UT004", "SV001", "Nguyễn Văn A", "TD003", "Data Analyst", "16/05/2026", "Phỏng vấn"));
    }

    public List<UngTuyen> getAll() {
        return new ArrayList<>(danhSach);
    }

    public boolean add(UngTuyen ut) {
        if (getByMa(ut.getMaUngTuyen()) != null) return false;
        danhSach.add(ut);
        return true;
    }

    public boolean updateStatus(String maUngTuyen, String trangThaiMoi) {
        for (UngTuyen ut : danhSach) {
            if (ut.getMaUngTuyen().equals(maUngTuyen)) {
                ut.setTrangThai(trangThaiMoi);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String ma) {
        return danhSach.removeIf(ut -> ut.getMaUngTuyen().equals(ma));
    }

    public List<UngTuyen> search(String keyword) {
        List<UngTuyen> result = new ArrayList<>();
        String kw = keyword.toLowerCase().trim();
        for (UngTuyen ut : danhSach) {
            if (ut.getMaUngTuyen().toLowerCase().contains(kw) ||
                ut.getHoTenSinhVien().toLowerCase().contains(kw) ||
                ut.getTenViTri().toLowerCase().contains(kw)) {
                result.add(ut);
            }
        }
        return result;
    }

    public List<UngTuyen> filterByStatus(String status) {
        List<UngTuyen> result = new ArrayList<>();
        for (UngTuyen ut : danhSach) {
            if (ut.getTrangThai().equalsIgnoreCase(status)) {
                result.add(ut);
            }
        }
        return result;
    }

    public UngTuyen getByMa(String ma) {
        for (UngTuyen ut : danhSach) {
            if (ut.getMaUngTuyen().equals(ma)) return ut;
        }
        return null;
    }
}