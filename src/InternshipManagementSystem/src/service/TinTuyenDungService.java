package service;

import java.util.ArrayList;
import java.util.List;
import model.TinTuyenDung;

public class TinTuyenDungService {

    private final List<TinTuyenDung> danhSach = new ArrayList<>();

    public TinTuyenDungService() {
        initData();
    }

    private void initData() {
        danhSach.add(new TinTuyenDung(
                "TD001",
                "Java Developer",
                "Công ty ABC",
                "TP.HCM",
                5,
                "30/06/2026",
                "Đang mở",
                "Tham gia phát triển các module quản lý thực tập bằng Java, phối hợp với nhóm backend để xử lý nghiệp vụ, kiểm thử chức năng và tối ưu trải nghiệm người dùng.",
                "Java Core, OOP, SQL cơ bản, Git, tư duy giải quyết vấn đề tốt. Ưu tiên sinh viên đã làm project Java Swing hoặc Web.",
                "3.000.000 - 5.000.000 VNĐ/tháng",
                "Có mentor hướng dẫn, được tham gia dự án thực tế, hỗ trợ dấu mộc thực tập và có cơ hội xét tuyển chính thức sau kỳ thực tập.",
                "Full-time tại văn phòng",
                "Công nghệ thông tin"
        ));

        danhSach.add(new TinTuyenDung(
                "TD002",
                "Frontend React",
                "Công ty XYZ",
                "Hà Nội",
                3,
                "15/07/2026",
                "Đang mở",
                "Xây dựng giao diện web tuyển dụng và dashboard quản trị, phối hợp cùng UI/UX để chuyển thiết kế thành giao diện hoàn chỉnh.",
                "HTML, CSS, JavaScript, React cơ bản, biết dùng Git. Có khả năng đọc hiểu giao diện Figma là lợi thế.",
                "2.500.000 - 4.000.000 VNĐ/tháng",
                "Làm việc trong môi trường trẻ, được review code định kỳ, hỗ trợ tài liệu thực tập và đánh giá năng lực cuối kỳ.",
                "Hybrid 3 ngày/tuần",
                "Kỹ thuật phần mềm"
        ));

        danhSach.add(new TinTuyenDung(
                "TD003",
                "Data Analyst",
                "Công ty MNO",
                "Đà Nẵng",
                2,
                "20/06/2026",
                "Đã đóng",
                "Hỗ trợ tổng hợp dữ liệu, xây dựng báo cáo vận hành, trực quan hóa số liệu và đề xuất insight cho bộ phận kinh doanh.",
                "SQL, Excel, tư duy phân tích dữ liệu, biết Power BI hoặc Python là lợi thế.",
                "2.000.000 - 3.500.000 VNĐ/tháng",
                "Được hướng dẫn bởi mentor dữ liệu, tiếp cận bộ dữ liệu thực tế và tham gia xây dựng báo cáo định kỳ.",
                "Part-time linh hoạt",
                "Hệ thống thông tin"
        ));

        danhSach.add(new TinTuyenDung(
                "TD004",
                "Tester Intern",
                "Công ty ABC",
                "TP.HCM",
                4,
                "10/07/2026",
                "Đang mở",
                "Viết test case, kiểm thử chức năng hệ thống quản lý thực tập, ghi nhận lỗi và phối hợp với lập trình viên để xác nhận bản sửa.",
                "Tư duy cẩn thận, hiểu quy trình kiểm thử phần mềm, biết viết test case. Có kiến thức SQL cơ bản là lợi thế.",
                "2.000.000 - 4.000.000 VNĐ/tháng",
                "Được đào tạo quy trình QA, hỗ trợ xác nhận thực tập, có cơ hội tham gia kiểm thử sản phẩm thật.",
                "Full-time tại văn phòng",
                "Công nghệ thông tin"
        ));
    }

    public List<TinTuyenDung> getAll() {
        return new ArrayList<>(danhSach);
    }

    public List<TinTuyenDung> getTinSinhVienCoTheXem() {
        List<TinTuyenDung> result = new ArrayList<>();

        for (TinTuyenDung td : danhSach) {
            if ("Đang mở".equalsIgnoreCase(td.getTrangThai())
                    || "Đã duyệt".equalsIgnoreCase(td.getTrangThai())) {
                result.add(td);
            }
        }

        return result;
    }

    public boolean add(TinTuyenDung td) {
        if (getByMa(td.getMaTinTuyenDung()) != null) {
            return false;
        }

        danhSach.add(td);
        return true;
    }

    public boolean update(TinTuyenDung td) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaTinTuyenDung().equalsIgnoreCase(td.getMaTinTuyenDung())) {
                danhSach.set(i, td);
                return true;
            }
        }

        return false;
    }

    public boolean delete(String ma) {
        return danhSach.removeIf(td -> td.getMaTinTuyenDung().equalsIgnoreCase(ma));
    }

    public List<TinTuyenDung> search(String keyword) {
        List<TinTuyenDung> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAll();
        }

        String kw = keyword.toLowerCase().trim();

        for (TinTuyenDung td : danhSach) {
            String searchText = (
                    td.getMaTinTuyenDung() + " "
                    + td.getTenViTri() + " "
                    + td.getTenDoanhNghiep() + " "
                    + td.getDiaDiem() + " "
                    + td.getTrangThai() + " "
                    + td.getMoTaCongViec() + " "
                    + td.getYeuCauKyNang() + " "
                    + td.getMucLuong() + " "
                    + td.getQuyenLoi() + " "
                    + td.getHinhThucLamViec() + " "
                    + td.getNganhPhuHop()
            ).toLowerCase();

            if (searchText.contains(kw)) {
                result.add(td);
            }
        }

        return result;
    }

    public List<TinTuyenDung> searchTinSinhVienCoTheXem(String keyword) {
        List<TinTuyenDung> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getTinSinhVienCoTheXem();
        }

        String kw = keyword.toLowerCase().trim();

        for (TinTuyenDung td : getTinSinhVienCoTheXem()) {
            String searchText = (
                    td.getMaTinTuyenDung() + " "
                    + td.getTenViTri() + " "
                    + td.getTenDoanhNghiep() + " "
                    + td.getDiaDiem() + " "
                    + td.getTrangThai() + " "
                    + td.getMoTaCongViec() + " "
                    + td.getYeuCauKyNang() + " "
                    + td.getMucLuong() + " "
                    + td.getQuyenLoi() + " "
                    + td.getHinhThucLamViec() + " "
                    + td.getNganhPhuHop()
            ).toLowerCase();

            if (searchText.contains(kw)) {
                result.add(td);
            }
        }

        return result;
    }

    public TinTuyenDung getByMa(String ma) {
        if (ma == null) {
            return null;
        }

        for (TinTuyenDung td : danhSach) {
            if (td.getMaTinTuyenDung().equalsIgnoreCase(ma)) {
                return td;
            }
        }

        return null;
    }
}
