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

public class UngTuyenCuaToiPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblUngTuyen;
    private DefaultTableModel tableModel;

    private JLabel lblTongDon;
    private JLabel lblChoDuyet;
    private JLabel lblDaDuyet;
    private JLabel lblTuChoi;

    private JLabel lblMaUngTuyen;
    private JLabel lblMaTin;
    private JLabel lblViTri;
    private JLabel lblDoanhNghiep;
    private JLabel lblNgayUngTuyen;
    private JLabel lblCV;
    private JLabel lblTrangThai;

    private JButton btnHuyUngTuyen;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private final TaiKhoan currentUser;
    private final List<DonUngTuyenSinhVien> danhSachUngTuyen = new ArrayList<>();

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

    public UngTuyenCuaToiPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
    }

    private void initData() {
        String maSinhVien = currentUser != null ? currentUser.getMaNguoiDung() : "SV001";

        danhSachUngTuyen.add(new DonUngTuyenSinhVien(
                "UT001",
                maSinhVien,
                "TD001",
                "Thực tập Java Developer",
                "Công ty ABC",
                "CV Java Backend Intern",
                "21/05/2026",
                "Chờ duyệt"
        ));

        danhSachUngTuyen.add(new DonUngTuyenSinhVien(
                "UT002",
                maSinhVien,
                "TD002",
                "Thực tập Frontend Developer",
                "Công ty Công nghệ Sao Việt",
                "CV Frontend Intern",
                "20/05/2026",
                "Đã duyệt"
        ));

        danhSachUngTuyen.add(new DonUngTuyenSinhVien(
                "UT003",
                maSinhVien,
                "TD003",
                "Thực tập Tester",
                "Công ty Giải pháp Phần mềm Minh Long",
                "CV Java Backend Intern",
                "18/05/2026",
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

        JLabel lblTitle = new JLabel("ỨNG TUYỂN CỦA TÔI");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Sinh viên tra cứu trạng thái hồ sơ đã nộp và hủy ứng tuyển khi hồ sơ còn chờ duyệt");
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
        detailCard.setPreferredSize(new Dimension(340, 560));
        detailCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblTitle = new JLabel("Chi tiết đơn ứng tuyển");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        lblMaUngTuyen = createValueLabel();
        lblMaTin = createValueLabel();
        lblViTri = createValueLabel();
        lblDoanhNghiep = createValueLabel();
        lblNgayUngTuyen = createValueLabel();
        lblCV = createValueLabel();
        lblTrangThai = createValueLabel();

        addInfoRow(infoPanel, "Mã ứng tuyển", lblMaUngTuyen, 0);
        addInfoRow(infoPanel, "Mã tin", lblMaTin, 1);
        addInfoRow(infoPanel, "Vị trí", lblViTri, 2);
        addInfoRow(infoPanel, "Doanh nghiệp", lblDoanhNghiep, 3);
        addInfoRow(infoPanel, "Ngày ứng tuyển", lblNgayUngTuyen, 4);
        addInfoRow(infoPanel, "CV đã nộp", lblCV, 5);
        addInfoRow(infoPanel, "Trạng thái", lblTrangThai, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);

        btnHuyUngTuyen = createActionButton("Hủy đơn", COLOR_DANGER, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnHuyUngTuyen);
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

        lblTongDon = new JLabel("0");
        lblChoDuyet = new JLabel("0");
        lblDaDuyet = new JLabel("0");
        lblTuChoi = new JLabel("0");

        badgePanel.add(createBadge("Tổng đơn", lblTongDon, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Chờ duyệt", lblChoDuyet, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Đã duyệt", lblDaDuyet, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Từ chối", lblTuChoi, Color.decode("#FEE2E2"), COLOR_DANGER));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(380, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập vị trí, doanh nghiệp, trạng thái...");
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
            "Mã tin",
            "Vị trí",
            "Doanh nghiệp",
            "CV đã nộp",
            "Ngày ứng tuyển",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblUngTuyen = new JTable(tableModel);
        tblUngTuyen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblUngTuyen.setRowHeight(36);
        tblUngTuyen.setForeground(COLOR_TEXT);
        tblUngTuyen.setGridColor(Color.decode("#CBD5E1"));
        tblUngTuyen.setShowVerticalLines(true);
        tblUngTuyen.setShowHorizontalLines(true);
        tblUngTuyen.setIntercellSpacing(new Dimension(1, 1));
        tblUngTuyen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblUngTuyen.setSelectionBackground(COLOR_ROW_SELECTED);
        tblUngTuyen.setSelectionForeground(COLOR_TEXT);

        tblUngTuyen.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblUngTuyen.getTableHeader().setForeground(Color.WHITE);
        tblUngTuyen.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblUngTuyen.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblUngTuyen.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblUngTuyen.getColumnCount(); i++) {
            tblUngTuyen.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblUngTuyen.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblUngTuyen.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblUngTuyen.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblUngTuyen.getColumnModel().getColumn(3).setPreferredWidth(170);
        tblUngTuyen.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblUngTuyen.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblUngTuyen.getColumnModel().getColumn(6).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblUngTuyen);
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
            tblUngTuyen.clearSelection();
            clearDetail();
            loadDataToTable();
        });

        btnHuyUngTuyen.addActionListener(e -> handleHuyUngTuyen());

        tblUngTuyen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(danhSachUngTuyen);
    }

    private void loadDataToTable(List<DonUngTuyenSinhVien> danhSach) {
        tableModel.setRowCount(0);

        for (DonUngTuyenSinhVien don : danhSach) {
            tableModel.addRow(new Object[]{
                don.getMaUngTuyen(),
                don.getMaTin(),
                don.getViTri(),
                don.getDoanhNghiep(),
                don.getTenCV(),
                don.getNgayUngTuyen(),
                don.getTrangThai()
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        int tong = danhSachUngTuyen.size();
        int choDuyet = 0;
        int daDuyet = 0;
        int tuChoi = 0;

        for (DonUngTuyenSinhVien don : danhSachUngTuyen) {
            if ("Chờ duyệt".equalsIgnoreCase(don.getTrangThai())) {
                choDuyet++;
            } else if ("Đã duyệt".equalsIgnoreCase(don.getTrangThai())) {
                daDuyet++;
            } else if ("Từ chối".equalsIgnoreCase(don.getTrangThai())) {
                tuChoi++;
            }
        }

        lblTongDon.setText(String.valueOf(tong));
        lblChoDuyet.setText(String.valueOf(choDuyet));
        lblDaDuyet.setText(String.valueOf(daDuyet));
        lblTuChoi.setText(String.valueOf(tuChoi));
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        List<DonUngTuyenSinhVien> result = new ArrayList<>();

        for (DonUngTuyenSinhVien don : danhSachUngTuyen) {
            String searchText = (
                    don.getMaUngTuyen() + " "
                    + don.getMaTin() + " "
                    + don.getViTri() + " "
                    + don.getDoanhNghiep() + " "
                    + don.getTenCV() + " "
                    + don.getNgayUngTuyen() + " "
                    + don.getTrangThai()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(don);
            }
        }

        loadDataToTable(result);
    }

    private void handleHuyUngTuyen() {
        int selectedRow = tblUngTuyen.getSelectedRow();

        if (selectedRow < 0) {
            MessageUtil.showError(this, "Vui lòng chọn đơn ứng tuyển cần hủy!");
            return;
        }

        String maUngTuyen = tableModel.getValueAt(selectedRow, 0).toString();
        DonUngTuyenSinhVien don = findByMaUngTuyen(maUngTuyen);

        if (don == null) {
            MessageUtil.showError(this, "Không tìm thấy đơn ứng tuyển cần hủy!");
            return;
        }

        if (!"Chờ duyệt".equalsIgnoreCase(don.getTrangThai())) {
            MessageUtil.showError(this, "Chỉ có thể hủy đơn ứng tuyển đang ở trạng thái Chờ duyệt!");
            return;
        }

        boolean confirm = MessageUtil.showConfirm(
                this,
                "Bạn có chắc chắn muốn hủy đơn ứng tuyển " + maUngTuyen + "?"
        );

        if (!confirm) {
            return;
        }

        danhSachUngTuyen.remove(don);
        MessageUtil.showInfo(this, "Hủy ứng tuyển thành công!");

        txtTuKhoa.setText("");
        clearDetail();
        loadDataToTable();
    }

    private void fillDetailFromSelectedRow() {
        int selectedRow = tblUngTuyen.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        lblMaUngTuyen.setText(tableModel.getValueAt(selectedRow, 0).toString());
        lblMaTin.setText(tableModel.getValueAt(selectedRow, 1).toString());
        lblViTri.setText(tableModel.getValueAt(selectedRow, 2).toString());
        lblDoanhNghiep.setText(tableModel.getValueAt(selectedRow, 3).toString());
        lblCV.setText(tableModel.getValueAt(selectedRow, 4).toString());
        lblNgayUngTuyen.setText(tableModel.getValueAt(selectedRow, 5).toString());
        lblTrangThai.setText(tableModel.getValueAt(selectedRow, 6).toString());
    }

    private void clearDetail() {
        lblMaUngTuyen.setText("Chưa chọn");
        lblMaTin.setText("Chưa chọn");
        lblViTri.setText("Chưa chọn");
        lblDoanhNghiep.setText("Chưa chọn");
        lblNgayUngTuyen.setText("Chưa chọn");
        lblCV.setText("Chưa chọn");
        lblTrangThai.setText("Chưa chọn");
    }

    private DonUngTuyenSinhVien findByMaUngTuyen(String maUngTuyen) {
        for (DonUngTuyenSinhVien don : danhSachUngTuyen) {
            if (don.getMaUngTuyen().equalsIgnoreCase(maUngTuyen)) {
                return don;
            }
        }

        return null;
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel("Chưa chọn");
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(COLOR_TEXT);
        label.setOpaque(true);
        label.setBackground(Color.decode("#F1F5F9"));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
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
        gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 12, 0);
        panel.add(valueLabel, gbc);
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

    private static class DonUngTuyenSinhVien {

        private String maUngTuyen;
        private String maSinhVien;
        private String maTin;
        private String viTri;
        private String doanhNghiep;
        private String tenCV;
        private String ngayUngTuyen;
        private String trangThai;

        public DonUngTuyenSinhVien(String maUngTuyen, String maSinhVien, String maTin,
                String viTri, String doanhNghiep, String tenCV, String ngayUngTuyen, String trangThai) {
            this.maUngTuyen = maUngTuyen;
            this.maSinhVien = maSinhVien;
            this.maTin = maTin;
            this.viTri = viTri;
            this.doanhNghiep = doanhNghiep;
            this.tenCV = tenCV;
            this.ngayUngTuyen = ngayUngTuyen;
            this.trangThai = trangThai;
        }

        public String getMaUngTuyen() {
            return maUngTuyen;
        }

        public String getMaSinhVien() {
            return maSinhVien;
        }

        public String getMaTin() {
            return maTin;
        }

        public String getViTri() {
            return viTri;
        }

        public String getDoanhNghiep() {
            return doanhNghiep;
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