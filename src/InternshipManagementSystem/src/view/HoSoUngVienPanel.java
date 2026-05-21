package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.TaiKhoan;
import util.MessageUtil;

public class HoSoUngVienPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblUngVien;
    private DefaultTableModel tableModel;

    private JLabel lblTongHoSo;
    private JLabel lblChoDuyet;
    private JLabel lblMoiPhongVan;
    private JLabel lblDaDuyet;

    private JLabel lblMaUngTuyen;
    private JLabel lblMaSinhVien;
    private JLabel lblHoTen;
    private JLabel lblEmail;
    private JLabel lblSoDienThoai;
    private JLabel lblViTri;
    private JLabel lblCV;
    private JLabel lblNgayUngTuyen;

    private JComboBox<String> cboTrangThai;

    private JButton btnCapNhat;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private final TaiKhoan currentUser;
    private final List<HoSoUngVien> danhSachHoSo = new ArrayList<>();

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_GRAY_BUTTON = Color.decode("#F1F5F9");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_INPUT_BORDER = Color.decode("#CBD5E1");
    private final Color COLOR_ROW_SELECTED = Color.decode("#DBEAFE");
    private final Color COLOR_TABLE_HEADER = Color.decode("#1E3A8A");

    public HoSoUngVienPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
    }

    private void initData() {
        String maDoanhNghiep = currentUser != null ? currentUser.getMaNguoiDung() : "DN001";

        danhSachHoSo.add(new HoSoUngVien(
                "UT001",
                maDoanhNghiep,
                "SV001",
                "Nguyễn Văn A",
                "nguyenvana@student.imec.edu.vn",
                "0912345678",
                "TD001",
                "Thực tập Java Developer",
                "CV Java Backend Intern",
                "21/05/2026",
                "Chờ duyệt"
        ));

        danhSachHoSo.add(new HoSoUngVien(
                "UT002",
                maDoanhNghiep,
                "SV002",
                "Trần Thị B",
                "tranthib@student.imec.edu.vn",
                "0923456789",
                "TD002",
                "Thực tập Frontend Developer",
                "CV Frontend Intern",
                "20/05/2026",
                "Mời phỏng vấn"
        ));

        danhSachHoSo.add(new HoSoUngVien(
                "UT003",
                maDoanhNghiep,
                "SV003",
                "Lê Văn C",
                "levanc@student.imec.edu.vn",
                "0934567890",
                "TD001",
                "Thực tập Java Developer",
                "CV Java Backend Intern",
                "18/05/2026",
                "Đã duyệt"
        ));

        danhSachHoSo.add(new HoSoUngVien(
                "UT004",
                maDoanhNghiep,
                "SV004",
                "Phạm Thị D",
                "phamthid@student.imec.edu.vn",
                "0945678901",
                "TD003",
                "Thực tập Tester",
                "CV Tester Intern",
                "17/05/2026",
                "Từ chối"
        ));
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createDetailCard(), BorderLayout.WEST);
        add(createTableCard(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("HỒ SƠ ỨNG VIÊN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Doanh nghiệp/HR xem hồ sơ sinh viên ứng tuyển và cập nhật trạng thái xét duyệt");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(COLOR_MUTED);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);
        titlePanel.add(lblTitle, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        titlePanel.add(lblSubtitle, gbc);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        return headerPanel;
    }

    private RoundedPanel createDetailCard() {
        RoundedPanel detailCard = new RoundedPanel(24, COLOR_CARD);
        detailCard.setLayout(new BorderLayout());
        detailCard.setPreferredSize(new Dimension(360, 620));
        detailCard.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel lblTitle = new JLabel("Chi tiết hồ sơ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 10, 0));

        lblMaUngTuyen = createValueLabel();
        lblMaSinhVien = createValueLabel();
        lblHoTen = createValueLabel();
        lblEmail = createValueLabel();
        lblSoDienThoai = createValueLabel();
        lblViTri = createValueLabel();
        lblCV = createValueLabel();
        lblNgayUngTuyen = createValueLabel();

        cboTrangThai = new JComboBox<>(new String[]{
            "Chờ duyệt",
            "Mời phỏng vấn",
            "Đã duyệt",
            "Từ chối"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBackground(Color.WHITE);
        cboTrangThai.setForeground(COLOR_TEXT);
        cboTrangThai.setPreferredSize(new Dimension(312, 34));

        addInfoRow(infoPanel, "Mã ứng tuyển", lblMaUngTuyen, 0);
        addInfoRow(infoPanel, "Mã sinh viên", lblMaSinhVien, 1);
        addInfoRow(infoPanel, "Họ tên", lblHoTen, 2);
        addInfoRow(infoPanel, "Email", lblEmail, 3);
        addInfoRow(infoPanel, "Số điện thoại", lblSoDienThoai, 4);
        addInfoRow(infoPanel, "Vị trí ứng tuyển", lblViTri, 5);
        addInfoRow(infoPanel, "CV đã nộp", lblCV, 6);
        addInfoRow(infoPanel, "Ngày ứng tuyển", lblNgayUngTuyen, 7);
        addFormField(infoPanel, "Trạng thái xét duyệt", cboTrangThai, 8);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        btnCapNhat = createActionButton("Cập nhật", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnLamMoi);

        detailCard.add(lblTitle, BorderLayout.NORTH);
        detailCard.add(infoPanel, BorderLayout.CENTER);
        detailCard.add(buttonPanel, BorderLayout.SOUTH);

        return detailCard;
    }

    private RoundedPanel createTableCard() {
        RoundedPanel tableCard = new RoundedPanel(24, COLOR_CARD);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        JPanel badgePanel = new JPanel(new GridLayout(1, 4, 10, 0));
        badgePanel.setOpaque(false);
        badgePanel.setPreferredSize(new Dimension(540, 64));

        lblTongHoSo = new JLabel("0");
        lblChoDuyet = new JLabel("0");
        lblMoiPhongVan = new JLabel("0");
        lblDaDuyet = new JLabel("0");

        badgePanel.add(createBadge("Tổng hồ sơ", lblTongHoSo, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Chờ duyệt", lblChoDuyet, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Phỏng vấn", lblMoiPhongVan, Color.decode("#E0F2FE"), COLOR_TEAL));
        badgePanel.add(createBadge("Đã duyệt", lblDaDuyet, Color.decode("#ECFDF5"), Color.decode("#059669")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(380, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập tên, vị trí, CV, trạng thái...");
        txtTuKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTuKhoa.setForeground(COLOR_TEXT);
        txtTuKhoa.setCaretColor(COLOR_TEAL);
        txtTuKhoa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        GridBagConstraints topGbc = new GridBagConstraints();

        topGbc.gridx = 0;
        topGbc.gridy = 0;
        topGbc.weightx = 0;
        topGbc.fill = GridBagConstraints.NONE;
        topGbc.anchor = GridBagConstraints.WEST;
        topGbc.insets = new Insets(0, 0, 0, 16);
        topPanel.add(badgePanel, topGbc);

        topGbc.gridx = 1;
        topGbc.weightx = 1.0;
        topGbc.fill = GridBagConstraints.HORIZONTAL;
        topGbc.anchor = GridBagConstraints.EAST;
        topGbc.insets = new Insets(0, 0, 0, 0);
        topPanel.add(searchPanel, topGbc);

        String[] columns = {
            "Mã ứng tuyển",
            "Mã SV",
            "Họ tên",
            "Vị trí",
            "CV",
            "Ngày ứng tuyển",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblUngVien = new JTable(tableModel);
        tblUngVien.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblUngVien.setRowHeight(36);
        tblUngVien.setForeground(COLOR_TEXT);
        tblUngVien.setGridColor(Color.decode("#CBD5E1"));
        tblUngVien.setShowVerticalLines(true);
        tblUngVien.setShowHorizontalLines(true);
        tblUngVien.setIntercellSpacing(new Dimension(1, 1));
        tblUngVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblUngVien.setSelectionBackground(COLOR_ROW_SELECTED);
        tblUngVien.setSelectionForeground(COLOR_TEXT);

        tblUngVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblUngVien.getTableHeader().setForeground(Color.WHITE);
        tblUngVien.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblUngVien.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblUngVien.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblUngVien.getColumnCount(); i++) {
            tblUngVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblUngVien.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblUngVien.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblUngVien.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblUngVien.getColumnModel().getColumn(3).setPreferredWidth(170);
        tblUngVien.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblUngVien.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblUngVien.getColumnModel().getColumn(6).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tblUngVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCard.add(topPanel, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private void initEvents() {
        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblUngVien.clearSelection();
            clearDetail();
            loadDataToTable();
        });

        btnCapNhat.addActionListener(e -> handleCapNhatTrangThai());

        tblUngVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(danhSachHoSo);
    }

    private void loadDataToTable(List<HoSoUngVien> danhSach) {
        tableModel.setRowCount(0);

        for (HoSoUngVien hoSo : danhSach) {
            tableModel.addRow(new Object[]{
                hoSo.getMaUngTuyen(),
                hoSo.getMaSinhVien(),
                hoSo.getHoTen(),
                hoSo.getViTri(),
                hoSo.getTenCV(),
                hoSo.getNgayUngTuyen(),
                hoSo.getTrangThai()
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        int tong = danhSachHoSo.size();
        int choDuyet = 0;
        int phongVan = 0;
        int daDuyet = 0;

        for (HoSoUngVien hoSo : danhSachHoSo) {
            if ("Chờ duyệt".equalsIgnoreCase(hoSo.getTrangThai())) {
                choDuyet++;
            } else if ("Mời phỏng vấn".equalsIgnoreCase(hoSo.getTrangThai())) {
                phongVan++;
            } else if ("Đã duyệt".equalsIgnoreCase(hoSo.getTrangThai())) {
                daDuyet++;
            }
        }

        lblTongHoSo.setText(String.valueOf(tong));
        lblChoDuyet.setText(String.valueOf(choDuyet));
        lblMoiPhongVan.setText(String.valueOf(phongVan));
        lblDaDuyet.setText(String.valueOf(daDuyet));
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        List<HoSoUngVien> result = new ArrayList<>();

        for (HoSoUngVien hoSo : danhSachHoSo) {
            String searchText = (
                    hoSo.getMaUngTuyen() + " "
                    + hoSo.getMaSinhVien() + " "
                    + hoSo.getHoTen() + " "
                    + hoSo.getEmail() + " "
                    + hoSo.getSoDienThoai() + " "
                    + hoSo.getMaTin() + " "
                    + hoSo.getViTri() + " "
                    + hoSo.getTenCV() + " "
                    + hoSo.getNgayUngTuyen() + " "
                    + hoSo.getTrangThai()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(hoSo);
            }
        }

        loadDataToTable(result);
    }

    private void handleCapNhatTrangThai() {
        int selectedRow = tblUngVien.getSelectedRow();

        if (selectedRow < 0) {
            MessageUtil.showError(this, "Vui lòng chọn hồ sơ ứng viên cần cập nhật!");
            return;
        }

        String maUngTuyen = tableModel.getValueAt(selectedRow, 0).toString();
        HoSoUngVien hoSo = findByMaUngTuyen(maUngTuyen);

        if (hoSo == null) {
            MessageUtil.showError(this, "Không tìm thấy hồ sơ ứng viên cần cập nhật!");
            return;
        }

        String trangThaiMoi = cboTrangThai.getSelectedItem().toString();

        boolean confirm = MessageUtil.showConfirm(
                this,
                "Cập nhật trạng thái hồ sơ " + maUngTuyen + " thành " + trangThaiMoi + "?"
        );

        if (!confirm) {
            return;
        }

        hoSo.setTrangThai(trangThaiMoi);
        MessageUtil.showInfo(this, "Cập nhật trạng thái hồ sơ ứng viên thành công!");

        txtTuKhoa.setText("");
        loadDataToTable();
        selectRowByMaUngTuyen(maUngTuyen);
    }

    private void fillDetailFromSelectedRow() {
        int selectedRow = tblUngVien.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String maUngTuyen = tableModel.getValueAt(selectedRow, 0).toString();
        HoSoUngVien hoSo = findByMaUngTuyen(maUngTuyen);

        if (hoSo == null) {
            return;
        }

        lblMaUngTuyen.setText(hoSo.getMaUngTuyen());
        lblMaSinhVien.setText(hoSo.getMaSinhVien());
        lblHoTen.setText(hoSo.getHoTen());
        lblEmail.setText(hoSo.getEmail());
        lblSoDienThoai.setText(hoSo.getSoDienThoai());
        lblViTri.setText(hoSo.getViTri());
        lblCV.setText(hoSo.getTenCV());
        lblNgayUngTuyen.setText(hoSo.getNgayUngTuyen());
        cboTrangThai.setSelectedItem(hoSo.getTrangThai());
    }

    private void clearDetail() {
        lblMaUngTuyen.setText("Chưa chọn");
        lblMaSinhVien.setText("Chưa chọn");
        lblHoTen.setText("Chưa chọn");
        lblEmail.setText("Chưa chọn");
        lblSoDienThoai.setText("Chưa chọn");
        lblViTri.setText("Chưa chọn");
        lblCV.setText("Chưa chọn");
        lblNgayUngTuyen.setText("Chưa chọn");
        cboTrangThai.setSelectedIndex(0);
    }

    private HoSoUngVien findByMaUngTuyen(String maUngTuyen) {
        for (HoSoUngVien hoSo : danhSachHoSo) {
            if (hoSo.getMaUngTuyen().equalsIgnoreCase(maUngTuyen)) {
                return hoSo;
            }
        }

        return null;
    }

    private void selectRowByMaUngTuyen(String maUngTuyen) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (maUngTuyen.equalsIgnoreCase(tableModel.getValueAt(i, 0).toString())) {
                tblUngVien.setRowSelectionInterval(i, i);
                fillDetailFromSelectedRow();
                return;
            }
        }
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel("Chưa chọn");
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(COLOR_TEXT);
        label.setOpaque(true);
        label.setBackground(Color.decode("#F1F5F9"));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return label;
    }

    private void addInfoRow(JPanel panel, String labelText, JLabel valueLabel, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 3, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(valueLabel, gbc);
    }

    private void addFormField(JPanel panel, String labelText, java.awt.Component field, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(field, gbc);
    }

    private RoundedPanel createBadge(String title, JLabel valueLabel, Color backgroundColor, Color textColor) {
        RoundedPanel badge = new RoundedPanel(18, backgroundColor);
        badge.setLayout(new GridBagLayout());
        badge.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        badge.setPreferredSize(new Dimension(128, 60));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        valueLabel.setForeground(textColor);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);
        badge.add(lblTitle, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        badge.add(valueLabel, gbc);

        return badge;
    }

    private JButton createActionButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new HoverButton(text, backgroundColor, foregroundColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(foregroundColor);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private static class HoSoUngVien {

        private String maUngTuyen;
        private String maDoanhNghiep;
        private String maSinhVien;
        private String hoTen;
        private String email;
        private String soDienThoai;
        private String maTin;
        private String viTri;
        private String tenCV;
        private String ngayUngTuyen;
        private String trangThai;

        public HoSoUngVien(String maUngTuyen, String maDoanhNghiep, String maSinhVien,
                String hoTen, String email, String soDienThoai, String maTin,
                String viTri, String tenCV, String ngayUngTuyen, String trangThai) {
            this.maUngTuyen = maUngTuyen;
            this.maDoanhNghiep = maDoanhNghiep;
            this.maSinhVien = maSinhVien;
            this.hoTen = hoTen;
            this.email = email;
            this.soDienThoai = soDienThoai;
            this.maTin = maTin;
            this.viTri = viTri;
            this.tenCV = tenCV;
            this.ngayUngTuyen = ngayUngTuyen;
            this.trangThai = trangThai;
        }

        public String getMaUngTuyen() {
            return maUngTuyen;
        }

        public String getMaDoanhNghiep() {
            return maDoanhNghiep;
        }

        public String getMaSinhVien() {
            return maSinhVien;
        }

        public String getHoTen() {
            return hoTen;
        }

        public String getEmail() {
            return email;
        }

        public String getSoDienThoai() {
            return soDienThoai;
        }

        public String getMaTin() {
            return maTin;
        }

        public String getViTri() {
            return viTri;
        }

        public String getTenCV() {
            return tenCV;
        }

        public String getNgayUngTuyen() {
            return ngayUngTuyen;
        }

        public String getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(String trangThai) {
            this.trangThai = trangThai;
        }
    }

    private static class PlaceholderTextField extends JTextField {

        private final String placeholder;
        private final Color placeholderColor = Color.decode("#94A3B8");

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            if (getText().isEmpty() && !isFocusOwner()) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(placeholderColor);
                g2.setFont(getFont().deriveFont(Font.PLAIN));
                g2.drawString(placeholder, 12, getHeight() / 2 + 5);
                g2.dispose();
            }
        }
    }

    private static class RoundedPanel extends JPanel {

        private final int radius;
        private final Color backgroundColor;

        public RoundedPanel(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(15, 23, 42, 16));
            g2.fillRoundRect(4, 6, getWidth() - 8, getHeight() - 8, radius, radius);

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth() - 6, getHeight() - 6, radius, radius);

            g2.setColor(new Color(226, 232, 240, 140));
            g2.setStroke(new BasicStroke(1.1f));
            g2.drawRoundRect(0, 0, getWidth() - 7, getHeight() - 7, radius, radius);

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class HoverButton extends JButton {

        private final Color backgroundColor;
        private final Color foregroundColor;
        private boolean hovered = false;

        public HoverButton(String text, Color backgroundColor, Color foregroundColor) {
            super(text);
            this.backgroundColor = backgroundColor;
            this.foregroundColor = foregroundColor;

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setForeground(foregroundColor);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color bg = hovered ? backgroundColor.brighter() : backgroundColor;

            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

            g2.setFont(getFont());
            g2.setColor(foregroundColor);
            drawCenteredText(g2, getText(), getWidth(), getHeight());

            g2.dispose();
        }
    }

    private static void drawCenteredText(Graphics2D g2, String text, int width, int height) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2.drawString(text, x, y);
    }
}