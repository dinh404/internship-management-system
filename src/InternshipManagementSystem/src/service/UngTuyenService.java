package service;

import model.UngTuyen;
import java.util.*;

public class UngTuyenService {
    private List<UngTuyen> danhSach = new ArrayList<>();

    public UngTuyenService() {
        danhSach.add(new UngTuyen("UT001", "TD001", "SV001", "10/05/2026", "Chờ duyệt", ""));
        danhSach.add(new UngTuyen("UT002", "TD001", "SV002", "12/05/2026", "Đã duyệt", "Phù hợp yêu cầu Java"));
        danhSach.add(new UngTuyen("UT003", "TD002", "SV003", "14/05/2026", "Từ chối", "Thiếu kinh nghiệm React"));
        danhSach.add(new UngTuyen("UT004", "TD002", "SV001", "15/05/2026", "Chờ duyệt", ""));
    }

    public List<UngTuyen> getAll() {
        return new ArrayList<>(danhSach);
    }

    public boolean capNhatTrangThai(String maUngTuyen, String trangThaiMoi, String ghiChuMoi) {
        for (UngTuyen ut : danhSach) {
            if (ut.getMaUngTuyen().equals(maUngTuyen)) {
                ut.setTrangThai(trangThaiMoi);
                ut.setGhiChu(ghiChuMoi);
                return true;
            }
        }
        return false;
    }
}