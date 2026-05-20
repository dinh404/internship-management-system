package view;

import model.TinTuyenDung;
import service.TinTuyenDungService;
import util.MessageUtil;
import util.ValidationUtil;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TinTuyenDungFrame extends JFrame {

    private TinTuyenDungService service = new TinTuyenDungService();
    private DefaultTableModel tableModel;
    private JTable tblTinTuyenDung;
    private JTextField txtMaTinTuyenDung, txtTenViTri, txtTenDoanhNghiep, txtDiaDiem, txtSoLuong, txtHanNop, txtTuKhoa;
    private JComboBox<String> cboTrangThai;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;

    public TinTuyenDungFrame() {
        setTitle("Quản lý Tin Tuyển Dụng");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitle = new JLabel("QUẢN LÝ TIN TUYỂN DỤNG", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        txtMaTinTuyenDung = new JTextField(18);
        txtTenViTri = new JTextField(35);
        txtTenDoanhNghiep = new JTextField(30);
        txtDiaDiem = new JTextField(30);
        txtSoLuong = new JTextField(12);
        txtHanNop = new JTextField(18);
        cboTrangThai = new JComboBox<>(new String[]{"Đang mở", "Đã đóng"});
        txtTuKhoa = new JTextField(20);

        formPanel.add(createField("Mã tin:", txtMaTinTuyenDung));
        formPanel.add(createField("Tên vị trí:", txtTenViTri));
        formPanel.add(createField("Tên doanh nghiệp:", txtTenDoanhNghiep));
        formPanel.add(createField("Địa điểm:", txtDiaDiem));
        formPanel.add(createField("Số lượng:", txtSoLuong));
        formPanel.add(createField("Hạn nộp:", txtHanNop));
        formPanel.add(createField("Trạng thái:", cboTrangThai));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        btnTimKiem = new JButton("Tìm kiếm");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        buttonPanel.add(new JLabel("Từ khóa:"));
        buttonPanel.add(txtTuKhoa);
        buttonPanel.add(btnTimKiem);

        // Table
        tableModel = new DefaultTableModel(new Object[]{
            "Mã tin", "Tên vị trí", "Tên DN", "Địa điểm", "Số lượng", "Hạn nộp", "Trạng thái"}, 0);
        tblTinTuyenDung = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblTinTuyenDung);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.WEST);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Events
        btnThem.addActionListener(e -> btnThemActionPerformed());
        btnSua.addActionListener(e -> btnSuaActionPerformed());
        btnXoa.addActionListener(e -> btnXoaActionPerformed());
        btnLamMoi.addActionListener(e -> btnLamMoiActionPerformed());
        btnTimKiem.addActionListener(e -> btnTimKiemActionPerformed());

        tblTinTuyenDung.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblTinTuyenDung.getSelectedRow() >= 0) {
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
        for (TinTuyenDung td : service.getAll()) {
            tableModel.addRow(new Object[]{
                td.getMaTinTuyenDung(), td.getTenViTri(), td.getTenDoanhNghiep(),
                td.getDiaDiem(), td.getSoLuong(), td.getHanNop(), td.getTrangThai()
            });
        }
    }

    private void clearForm() {
        txtMaTinTuyenDung.setText("");
        txtTenViTri.setText("");
        txtTenDoanhNghiep.setText("");
        txtDiaDiem.setText("");
        txtSoLuong.setText("");
        txtHanNop.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblTinTuyenDung.clearSelection();
    }

    private TinTuyenDung getDataFromForm() {
        return new TinTuyenDung(
            txtMaTinTuyenDung.getText().trim(),
            txtTenViTri.getText().trim(),
            txtTenDoanhNghiep.getText().trim(),
            txtDiaDiem.getText().trim(),
            Integer.parseInt(txtSoLuong.getText().trim()),
            txtHanNop.getText().trim(),
            cboTrangThai.getSelectedItem().toString()
        );
    }

    private void fillFormFromSelectedRow() {
        int row = tblTinTuyenDung.getSelectedRow();
        if (row < 0) return;
        txtMaTinTuyenDung.setText(tableModel.getValueAt(row, 0).toString());
        txtTenViTri.setText(tableModel.getValueAt(row, 1).toString());
        txtTenDoanhNghiep.setText(tableModel.getValueAt(row, 2).toString());
        txtDiaDiem.setText(tableModel.getValueAt(row, 3).toString());
        txtSoLuong.setText(tableModel.getValueAt(row, 4).toString());
        txtHanNop.setText(tableModel.getValueAt(row, 5).toString());
        cboTrangThai.setSelectedItem(tableModel.getValueAt(row, 6).toString());
    }

    private boolean validateForm() {
        if (ValidationUtil.isEmpty(txtMaTinTuyenDung.getText()) || 
            ValidationUtil.isEmpty(txtTenViTri.getText())) {
            MessageUtil.showError(this, "Mã tin và Tên vị trí không được trống!");
            return false;
        }
        try {
            Integer.parseInt(txtSoLuong.getText().trim());
        } catch (Exception e) {
            MessageUtil.showError(this, "Số lượng phải là số!");
            return false;
        }
        return true;
    }

    private void btnThemActionPerformed() {
        if (!validateForm()) return;
        try {
            if (service.add(getDataFromForm())) {
                loadDataToTable();
                clearForm();
                MessageUtil.showInfo(this, "Thêm tin tuyển dụng thành công!");
            } else {
                MessageUtil.showError(this, "Mã tin đã tồn tại!");
            }
        } catch (Exception e) {
            MessageUtil.showError(this, "Lỗi: " + e.getMessage());
        }
    }

    private void btnSuaActionPerformed() {
        if (!validateForm()) return;
        try {
            if (service.update(getDataFromForm())) {
                loadDataToTable();
                MessageUtil.showInfo(this, "Cập nhật thành công!");
            } else {
                MessageUtil.showError(this, "Không tìm thấy tin tuyển dụng!");
            }
        } catch (Exception e) {
            MessageUtil.showError(this, "Lỗi: " + e.getMessage());
        }
    }

    private void btnXoaActionPerformed() {
        int row = tblTinTuyenDung.getSelectedRow();
        if (row < 0) {
            MessageUtil.showError(this, "Vui lòng chọn tin để xóa!");
            return;
        }
        String ma = tableModel.getValueAt(row, 0).toString();
        if (MessageUtil.showConfirm(this, "Xóa tin " + ma + "?")) {
            service.delete(ma);
            loadDataToTable();
            clearForm();
            MessageUtil.showInfo(this, "Xóa thành công!");
        }
    }

    private void btnTimKiemActionPerformed() {
        String kw = txtTuKhoa.getText().trim();
        tableModel.setRowCount(0);
        List<TinTuyenDung> ketQua = kw.isEmpty() ? service.getAll() : service.search(kw);
        for (TinTuyenDung td : ketQua) {
            tableModel.addRow(new Object[]{
                td.getMaTinTuyenDung(), td.getTenViTri(), td.getTenDoanhNghiep(),
                td.getDiaDiem(), td.getSoLuong(), td.getHanNop(), td.getTrangThai()
            });
        }
    }

    private void btnLamMoiActionPerformed() {
        clearForm();
        loadDataToTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TinTuyenDungFrame().setVisible(true));
    }
}