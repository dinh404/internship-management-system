package service;

import model.TinTuyenDung;
import java.util.*;

public class TinTuyenDungService {
    private List<TinTuyenDung> danhSach = new ArrayList<>();

    public TinTuyenDungService() {
        // Dữ liệu mẫu mới (theo yêu cầu)
        danhSach.add(new TinTuyenDung("TD001", "Java Developer", "Công ty ABC", 
            "TP.HCM", 5, "30/06/2026", "Đang mở"));
        danhSach.add(new TinTuyenDung("TD002", "Frontend React", "Công ty XYZ", 
            "Hà Nội", 3, "15/07/2026", "Đang mở"));
        danhSach.add(new TinTuyenDung("TD003", "Data Analyst", "Công ty MNO", 
            "Đà Nẵng", 2, "20/06/2026", "Đã đóng"));
    }

    public List<TinTuyenDung> getAll() {
        return new ArrayList<>(danhSach);
    }

    public boolean add(TinTuyenDung td) {
        if (getByMa(td.getMaTinTuyenDung()) != null) return false;
        danhSach.add(td);
        return true;
    }

    public boolean update(TinTuyenDung td) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaTinTuyenDung().equals(td.getMaTinTuyenDung())) {
                danhSach.set(i, td);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String ma) {
        return danhSach.removeIf(td -> td.getMaTinTuyenDung().equals(ma));
    }

    public List<TinTuyenDung> search(String keyword) {
        List<TinTuyenDung> result = new ArrayList<>();
        String kw = keyword.toLowerCase().trim();
        for (TinTuyenDung td : danhSach) {
            if (td.getMaTinTuyenDung().toLowerCase().contains(kw) ||
                td.getTenViTri().toLowerCase().contains(kw) ||
                td.getTenDoanhNghiep().toLowerCase().contains(kw)) {
                result.add(td);
            }
        }
        return result;
    }

    public TinTuyenDung getByMa(String ma) {
        for (TinTuyenDung td : danhSach) {
            if (td.getMaTinTuyenDung().equals(ma)) return td;
        }
        return null;
    }
}