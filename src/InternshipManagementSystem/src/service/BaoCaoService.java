package service;

public class BaoCaoService {
    
    private SinhVienService svService = new SinhVienService();
    private TinTuyenDungService tdService = new TinTuyenDungService();
    private UngTuyenService utService = new UngTuyenService();

    public int getTongSinhVien() {
        return svService.getAll().size();
    }

    public int getTongTinTuyenDung() {
        return tdService.getAll().size();
    }

    public int getTongUngTuyen() {
        return utService.getAll().size();
    }

    public int getUngTuyenChoDuyet() {
        int count = 0;
        for (model.UngTuyen ut : utService.getAll()) {
            if ("Chờ duyệt".equals(ut.getTrangThai())) count++;
        }
        return count;
    }

    public int getUngTuyenDaDuyet() {
        int count = 0;
        for (model.UngTuyen ut : utService.getAll()) {
            if ("Đã duyệt".equals(ut.getTrangThai())) count++;
        }
        return count;
    }
}