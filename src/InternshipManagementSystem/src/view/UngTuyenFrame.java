package view;

import model.UngTuyen;
import service.UngTuyenService;
import util.MessageUtil;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UngTuyenFrame extends JFrame {

    private UngTuyenService service = new UngTuyenService();
    private DefaultTableModel tableModel;
    private JTable tblUngTuyen;
    private JTextField txtMaUngTuyen, txtMaSinhVien, txtHoTenSinhVien, txtMaTinTuyenDung, txtTenViTri, txtNgayUngTuyen, txtGhiChu, txtTuKhoa;
    private JComboBox<String> cboTrangThai;
    private JButton btnThem, btnCapNhat, btnXoa, btnLamMoi, btnTimKiem, btnLoc;

    public UngTuyenFrame() {
        setTitle("Quản lý Ứng tuyển");
        setSize(1300, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitle = new JLabel("QUẢN LÝ ỨNG TUYỂN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        txtMaUngTuyen = new JTextField(15);
        txtMaSinhVien = new JTextField(12);
        txtHoTenSinhVien = new JTextField(25);
        txtMaTinTuyenDung = new JTextField(12);
        txtTenViTri = new JTextField(25);
        txtNgayUngTuyen = new JTextField(12);
        cboTrangThai = new JComboBox<>(new String[]{"Chờ duyệt", "Đã duyệt", "Từ chối", "Phỏng vấn", "Trúng tuyển"});
        txtGhiChu = new JTextField(30);
        txtTuKhoa = new JTextField(18);

        formPanel.add(createField("Mã ứng tuyển:", txtMaUngTuyen));
        formPanel.add(createField("Mã sinh viên:", txtMaSinhVien));
        formPanel.add(createField("Họ tên sinh viên:", txtHoTenSinhVien));
        formPanel.add(createField("Mã tin tuyển dụng:", txtMaTinTuyenDung));
        formPanel.add(createField("Tên vị trí:", txtTenViTri));
        formPanel.add(createField("Ngày ứng tuyển:", txtNgayUngTuyen));
        formPanel.add(createField("Trạng thái:", cboTrangThai));
        formPanel.add(createField("Ghi chú:", txtGhiChu));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        btnThem = new JButton("Thêm");
        btnCapNhat = new JButton("Cập nhật trạng thái");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        btnTimKiem = new JButton("Tìm kiếm");
        btnLoc = new JButton("Lọc theo trạng thái");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        buttonPanel.add(new JLabel("Từ khóa:"));
        buttonPanel.add(txtTuKhoa);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnLoc);

        // Table
        tableModel = new DefaultTableModel(new Object[]{
            "Mã UT", "Mã SV", "Họ tên", "Mã tin", "Vị trí", "Ngày nộp", "Trạng thái"}, 0);
        tblUngTuyen = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblUngTuyen);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.WEST);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Events
        btnThem.addActionListener(e -> btnThemActionPerformed());
        btnCapNhat.addActionListener(e -> btnCapNhatActionPerformed());
        btnXoa.addActionListener(e -> btnXoaActionPerformed());
        btnLamMoi.addActionListener(e -> btnLamMoiActionPerformed());
        btnTimKiem.addActionListener(e -> btnTimKiemActionPerformed());
        btnLoc.addActionListener(e -> btnLocActionPerformed());

        tblUngTuyen.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblUngTuyen.getSelectedRow() >= 0) {
                fillFormFromSelectedRow();
            }
        });

        loadDataToTable();
    }

    private JPanel createField(String label, JComponent comp) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        p.add(new JLabel(label));
        p.add(comp);
        return p;
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);
        for (UngTuyen ut : service.getAll()) {
            tableModel.addRow(new Object[]{
                ut.getMaUngTuyen(), ut.getMaSinhVien(), ut.getHoTenSinhVien(),
                ut.getMaTinTuyenDung(), ut.getTenViTri(), ut.getNgayUngTuyen(), ut.getTrangThai()
            });
        }
    }

    private void fillFormFromSelectedRow() {
        int row = tblUngTuyen.getSelectedRow();
        if (row < 0) return;
        txtMaUngTuyen.setText(tableModel.getValueAt(row, 0).toString());
        txtMaSinhVien.setText(tableModel.getValueAt(row, 1).toString());
        txtHoTenSinhVien.setText(tableModel.getValueAt(row, 2).toString());
        txtMaTinTuyenDung.setText(tableModel.getValueAt(row, 3).toString());
        txtTenViTri.setText(tableModel.getValueAt(row, 4).toString());
        txtNgayUngTuyen.setText(tableModel.getValueAt(row, 5).toString());
        cboTrangThai.setSelectedItem(tableModel.getValueAt(row, 6).toString());
    }

    private void btnThemActionPerformed() {
        // Thêm logic thêm mới (tương tự TinTuyenDungFrame)
        MessageUtil.showInfo(this, "Chức năng Thêm đang được hoàn thiện...");
    }

    private void btnCapNhatActionPerformed() {
        String ma = txtMaUngTuyen.getText().trim();
        if (ma.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn ứng tuyển từ bảng!");
            return;
        }
        String trangThai = cboTrangThai.getSelectedItem().toString();
        if (service.updateStatus(ma, trangThai)) {
            loadDataToTable();
            MessageUtil.showInfo(this, "Cập nhật trạng thái thành công!");
        } else {
            MessageUtil.showError(this, "Cập nhật thất bại!");
        }
    }

    private void btnXoaActionPerformed() {
        int row = tblUngTuyen.getSelectedRow();
        if (row < 0) return;
        String ma = tableModel.getValueAt(row, 0).toString();
        if (MessageUtil.showConfirm(this, "Xóa ứng tuyển " + ma + "?")) {
            service.delete(ma);
            loadDataToTable();
            MessageUtil.showInfo(this, "Xóa thành công!");
        }
    }

    private void btnTimKiemActionPerformed() {
        String kw = txtTuKhoa.getText().trim();
        tableModel.setRowCount(0);
        List<UngTuyen> ketQua = kw.isEmpty() ? service.getAll() : service.search(kw);
        for (UngTuyen ut : ketQua) {
            tableModel.addRow(new Object[]{
                ut.getMaUngTuyen(), ut.getMaSinhVien(), ut.getHoTenSinhVien(),
                ut.getMaTinTuyenDung(), ut.getTenViTri(), ut.getNgayUngTuyen(), ut.getTrangThai()
            });
        }
    }

    private void btnLocActionPerformed() {
        String status = cboTrangThai.getSelectedItem().toString();
        tableModel.setRowCount(0);
        List<UngTuyen> ketQua = service.filterByStatus(status);
        for (UngTuyen ut : ketQua) {
            tableModel.addRow(new Object[]{
                ut.getMaUngTuyen(), ut.getMaSinhVien(), ut.getHoTenSinhVien(),
                ut.getMaTinTuyenDung(), ut.getTenViTri(), ut.getNgayUngTuyen(), ut.getTrangThai()
            });
        }
    }

    private void btnLamMoiActionPerformed() {
        txtMaUngTuyen.setText("");
        txtMaSinhVien.setText("");
        txtHoTenSinhVien.setText("");
        txtMaTinTuyenDung.setText("");
        txtTenViTri.setText("");
        txtNgayUngTuyen.setText("");
        txtGhiChu.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblUngTuyen.clearSelection();
        loadDataToTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UngTuyenFrame().setVisible(true));
    }
}