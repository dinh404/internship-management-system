package service;

import model.UngTuyen;
import java.util.*;

public class BaoCaoService {

    private TinTuyenDungService tinService = new TinTuyenDungService();
    private UngTuyenService ungTuyenService = new UngTuyenService();

    public int getTongTinTuyenDung() {
        return tinService.getAll().size();
    }

    public int getTongUngTuyen() {
        return ungTuyenService.getAll().size();
    }

    public int getSoHoSoTheoTrangThai(String trangThai) {
        int count = 0;
        for (UngTuyen ut : ungTuyenService.getAll()) {
            if (ut.getTrangThai().equalsIgnoreCase(trangThai)) {
                count++;
            }
        }
        return count;
    }
}