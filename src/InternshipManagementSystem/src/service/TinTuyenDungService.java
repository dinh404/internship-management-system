package service;

import model.TinTuyenDung;
import java.util.*;

public class TinTuyenDungService {
    private List<TinTuyenDung> danhSach = new ArrayList<>();

    public TinTuyenDungService() {
        // Dữ liệu mẫu
        danhSach.add(new TinTuyenDung("TD001", "Thực tập Java Developer", 
            "Phát triển ứng dụng Java Spring Boot", "Java, Spring Boot, MySQL, Git", 
            5, "30/06/2026", "DN001", "Đang mở"));
        
        danhSach.add(new TinTuyenDung("TD002", "Thực tập Frontend React", 
            "Xây dựng giao diện website hiện đại", "ReactJS, HTML5, CSS3, JavaScript", 
            3, "15/07/2026", "DN002", "Đang mở"));
        
        danhSach.add(new TinTuyenDung("TD003", "Thực tập Data Analyst", 
            "Phân tích và trực quan hóa dữ liệu", "Python, SQL, Power BI, Excel", 
            2, "20/06/2026", "DN001", "Đã đóng"));
    }

    public List<TinTuyenDung> getAll() {
        return new ArrayList<>(danhSach);
    }

    public boolean them(TinTuyenDung td) {
        if (getByMa(td.getMaTinTuyenDung()) != null) return false;
        danhSach.add(td);
        return true;
    }

    public boolean sua(TinTuyenDung td) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaTinTuyenDung().equals(td.getMaTinTuyenDung())) {
                danhSach.set(i, td);
                return true;
            }
        }
        return false;
    }

    public boolean xoa(String ma) {
        return danhSach.removeIf(td -> td.getMaTinTuyenDung().equals(ma));
    }

    public List<TinTuyenDung> timKiem(String tuKhoa) {
        List<TinTuyenDung> ketQua = new ArrayList<>();
        String kw = tuKhoa.toLowerCase().trim();
        for (TinTuyenDung td : danhSach) {
            if (td.getMaTinTuyenDung().toLowerCase().contains(kw) ||
                td.getTieuDe().toLowerCase().contains(kw) ||
                td.getMaDoanhNghiep().toLowerCase().contains(kw)) {
                ketQua.add(td);
            }
        }
        return ketQua;
    }

    public TinTuyenDung getByMa(String ma) {
        for (TinTuyenDung td : danhSach) {
            if (td.getMaTinTuyenDung().equals(ma)) return td;
        }
        return null;
    }
}