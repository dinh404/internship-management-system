package service;

import java.util.ArrayList;
import java.util.List;
import model.SinhVien;

public class SinhVienService {

    private final List<SinhVien> danhSachSinhVien;

    public SinhVienService() {
        danhSachSinhVien = new ArrayList<>();
    }

    public List<SinhVien> getAll() {
        return danhSachSinhVien;
    }
}