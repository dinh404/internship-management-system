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
    private JTextField txtMaUngTuyen, txtGhiChu, txtTuKhoa;
    private JComboBox<String> cboTrangThai;
    private JButton btnCapNhat, btnLamMoi, btnTimKiem;

    public UngTuyenFrame() {
        setTitle("Quản lý Ứng tuyển");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitle = new JLabel("QUẢN LÝ ỨNG TUYỂN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitle, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{
            "Mã UT", "Mã tin", "Mã SV", "Ngày nộp", "Trạng thái", "Ghi chú"}, 0);
        tblUngTuyen = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblUngTuyen);

        JPanel updatePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        txtMaUngTuyen = new JTextField(12);
        txtMaUngTuyen.setEditable(false);
        cboTrangThai = new JComboBox<>(new String[]{"Chờ duyệt", "Đã duyệt", "Từ chối"});
        txtGhiChu = new JTextField(30);
        txtTuKhoa = new JTextField(15);

        btnCapNhat = new JButton("Cập nhật trạng thái");
        btnLamMoi = new JButton("Làm mới");
        btnTimKiem = new JButton("Tìm kiếm");

        gbc.gridx = 0; gbc.gridy = 0;
        updatePanel.add(new JLabel("Mã ứng tuyển:"), gbc);
        gbc.gridx = 1; updatePanel.add(txtMaUngTuyen, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        updatePanel.add(new JLabel("Trạng thái mới:"), gbc);
        gbc.gridx = 1; updatePanel.add(cboTrangThai, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        updatePanel.add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1; updatePanel.add(txtGhiChu, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnLamMoi);
        buttonPanel.add(new JLabel("Từ khóa:"));
        buttonPanel.add(txtTuKhoa);
        buttonPanel.add(btnTimKiem);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(updatePanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        btnCapNhat.addActionListener(e -> btnCapNhatActionPerformed());
        btnLamMoi.addActionListener(e -> btnLamMoiActionPerformed());
        btnTimKiem.addActionListener(e -> btnTimKiemActionPerformed());

        tblUngTuyen.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblUngTuyen.getSelectedRow() >= 0) {
                fillUpdateForm();
            }
        });

        loadDataToTable();
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);
        for (UngTuyen ut : service.getAll()) {
            tableModel.addRow(new Object[]{
                ut.getMaUngTuyen(), ut.getMaTinTuyenDung(), ut.getMaSinhVien(),
                ut.getNgayUngTuyen(), ut.getTrangThai(), ut.getGhiChu()
            });
        }
    }

    private void fillUpdateForm() {
        int row = tblUngTuyen.getSelectedRow();
        if (row < 0) return;
        txtMaUngTuyen.setText(tableModel.getValueAt(row, 0).toString());
        cboTrangThai.setSelectedItem(tableModel.getValueAt(row, 4).toString());
        txtGhiChu.setText(tableModel.getValueAt(row, 5).toString());
    }

    private void btnCapNhatActionPerformed() {
        String ma = txtMaUngTuyen.getText().trim();
        if (ma.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn ứng tuyển từ bảng!");
            return;
        }
        String trangThai = cboTrangThai.getSelectedItem().toString();
        String ghiChu = txtGhiChu.getText().trim();

        if (service.capNhatTrangThai(ma, trangThai, ghiChu)) {
            loadDataToTable();
            MessageUtil.showInfo(this, "Cập nhật trạng thái thành công!");
        } else {
            MessageUtil.showError(this, "Cập nhật thất bại!");
        }
    }

    private void btnLamMoiActionPerformed() {
        txtMaUngTuyen.setText("");
        txtGhiChu.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblUngTuyen.clearSelection();
        loadDataToTable();
    }

    private void btnTimKiemActionPerformed() {
        String kw = txtTuKhoa.getText().trim().toLowerCase();
        tableModel.setRowCount(0);
        for (UngTuyen ut : service.getAll()) {
            if (ut.getMaUngTuyen().toLowerCase().contains(kw) ||
                ut.getMaSinhVien().toLowerCase().contains(kw) ||
                ut.getTrangThai().toLowerCase().contains(kw)) {
                tableModel.addRow(new Object[]{
                    ut.getMaUngTuyen(), ut.getMaTinTuyenDung(), ut.getMaSinhVien(),
                    ut.getNgayUngTuyen(), ut.getTrangThai(), ut.getGhiChu()
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UngTuyenFrame().setVisible(true));
    }
}
