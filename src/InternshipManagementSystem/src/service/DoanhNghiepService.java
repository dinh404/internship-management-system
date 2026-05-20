package service;

import model.DoanhNghiep;
import java.util.*;

public class DoanhNghiepService {
    private List<DoanhNghiep> danhSach = new ArrayList<>();

    public DoanhNghiepService() {
        // Dữ liệu mẫu theo yêu cầu
        danhSach.add(new DoanhNghiep("DN001", "Công ty ABC", "123 Nguyễn Trãi, Q1, TP.HCM", 
            "abc@company.com", "0901234567", "Công nghệ thông tin", "Doanh nghiệp hàng đầu về phần mềm"));
        danhSach.add(new DoanhNghiep("DN002", "Công ty XYZ", "456 Trần Hưng Đạo, Hà Nội", 
            "xyz@company.com", "0912345678", "E-commerce", "Chuyên về thương mại điện tử"));
        danhSach.add(new DoanhNghiep("DN003", "Công ty MNO", "789 Lê Lợi, Đà Nẵng", 
            "mno@company.com", "0923456789", "Tài chính - Ngân hàng", "Ngân hàng số hàng đầu"));
    }

    public List<DoanhNghiep> getAll() {
        return new ArrayList<>(danhSach);
    }

    public boolean add(DoanhNghiep dn) {
        if (getByMa(dn.getMaDoanhNghiep()) != null) return false;
        danhSach.add(dn);
        return true;
    }

    public boolean update(DoanhNghiep dn) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaDoanhNghiep().equals(dn.getMaDoanhNghiep())) {
                danhSach.set(i, dn);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String ma) {
        return danhSach.removeIf(dn -> dn.getMaDoanhNghiep().equals(ma));
    }

    public List<DoanhNghiep> search(String keyword) {
        List<DoanhNghiep> result = new ArrayList<>();
        String kw = keyword.toLowerCase().trim();
        for (DoanhNghiep dn : danhSach) {
            if (dn.getMaDoanhNghiep().toLowerCase().contains(kw) ||
                dn.getTenDoanhNghiep().toLowerCase().contains(kw)) {
                result.add(dn);
            }
        }
        return result;
    }

    public DoanhNghiep getByMa(String ma) {
        for (DoanhNghiep dn : danhSach) {
            if (dn.getMaDoanhNghiep().equals(ma)) return dn;
        }
        return null;
    }
}