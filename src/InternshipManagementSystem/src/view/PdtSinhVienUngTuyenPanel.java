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

public class PdtSinhVienUngTuyenPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblSinhVienUngTuyen;
    private DefaultTableModel tableModel;

    private JLabel lblTongHoSo;
    private JLabel lblDaCoNoiThucTap;
    private JLabel lblChuaCoNoiThucTap;
    private JLabel lblDangChoXuLy;

    private JLabel lblMaUngTuyen;
    private JLabel lblMaSinhVien;
    private JLabel lblHoTen;
    private JLabel lblNganhHoc;
    private JLabel lblGPA;
    private JLabel lblDoanhNghiep;
    private JLabel lblViTri;
    private JLabel lblNgayUngTuyen;
    private JLabel lblTrangThaiUngTuyen;
    private JLabel lblTrangThaiThucTap;

    private JButton btnTimKiem;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<SinhVienUngTuyen> danhSach = new ArrayList<>();

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_INPUT_BORDER = Color.decode("#CBD5E1");
    private final Color COLOR_ROW_SELECTED = Color.decode("#DBEAFE");
    private final Color COLOR_TABLE_HEADER = Color.decode("#1E3A8A");
    private final Color COLOR_GRAY_BUTTON = Color.decode("#E2E8F0");

    public PdtSinhVienUngTuyenPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
    }

    private void initData() {
        danhSach.add(new SinhVienUngTuyen(
                "UT001",
                "SV001",
                "Nguyễn Văn A",
                "Công nghệ thông tin",
                "3.35",
                "Công ty ABC",
                "Java Developer",
                "21/05/2026",
                "Đã nhận",
                "Đã có nơi thực tập"
        ));

        danhSach.add(new SinhVienUngTuyen(
                "UT002",
                "SV002",
                "Trần Thị B",
                "Kỹ thuật phần mềm",
                "3.12",
                "Công ty XYZ",
                "Frontend React",
                "20/05/2026",
                "Mời phỏng vấn",
                "Chưa có nơi thực tập"
        ));

        danhSach.add(new SinhVienUngTuyen(
                "UT003",
                "SV003",
                "Lê Văn C",
                "Hệ thống thông tin",
                "2.95",
                "Công ty MNO",
                "Data Analyst",
                "18/05/2026",
                "Đã xem",
                "Chưa có nơi thực tập"
        ));

        danhSach.add(new SinhVienUngTuyen(
                "UT004",
                "SV004",
                "Phạm Thị D",
                "Công nghệ thông tin",
                "3.48",
                "Công ty ABC",
                "Tester Intern",
                "17/05/2026",
                "Từ chối",
                "Chưa có nơi thực tập"
        ));

        danhSach.add(new SinhVienUngTuyen(
                "UT005",
                "SV005",
                "Hoàng Minh E",
                "Mạng máy tính",
                "3.01",
                "Công ty KTech",
                "IT Support Intern",
                "16/05/2026",
                "Đã nộp",
                "Chưa có nơi thực tập"
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

        JLabel lblTitle = new JLabel("SINH VIÊN ỨNG TUYỂN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Phòng Đào tạo theo dõi trạng thái ứng tuyển của sinh viên, chỉ xem dữ liệu và không thay đổi hồ sơ");
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
        detailCard.setPreferredSize(new Dimension(360, 590));
        detailCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Chi tiết hồ sơ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblNote = new JLabel("<html><span style='color:#64748B;'>Chế độ xem dành cho Phòng Đào tạo</span></html>");
        lblNote.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblNote.setForeground(COLOR_MUTED);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.anchor = GridBagConstraints.WEST;
        titleGbc.fill = GridBagConstraints.HORIZONTAL;
        titleGbc.weightx = 1.0;

        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(0, 0, 4, 0);
        titlePanel.add(lblTitle, titleGbc);

        titleGbc.gridy = 1;
        titleGbc.insets = new Insets(0, 0, 0, 0);
        titlePanel.add(lblNote, titleGbc);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(14, 0, 12, 0));

        lblMaUngTuyen = createValueLabel();
        lblMaSinhVien = createValueLabel();
        lblHoTen = createValueLabel();
        lblNganhHoc = createValueLabel();
        lblGPA = createValueLabel();
        lblDoanhNghiep = createValueLabel();
        lblViTri = createValueLabel();
        lblNgayUngTuyen = createValueLabel();
        lblTrangThaiUngTuyen = createValueLabel();
        lblTrangThaiThucTap = createValueLabel();

        addInfoRow(infoPanel, "Mã ứng tuyển", lblMaUngTuyen, 0);
        addInfoRow(infoPanel, "Mã sinh viên", lblMaSinhVien, 1);
        addInfoRow(infoPanel, "Họ tên", lblHoTen, 2);
        addInfoRow(infoPanel, "Ngành học", lblNganhHoc, 3);
        addInfoRow(infoPanel, "GPA", lblGPA, 4);
        addInfoRow(infoPanel, "Doanh nghiệp", lblDoanhNghiep, 5);
        addInfoRow(infoPanel, "Vị trí ứng tuyển", lblViTri, 6);
        addInfoRow(infoPanel, "Ngày ứng tuyển", lblNgayUngTuyen, 7);
        addInfoRow(infoPanel, "Trạng thái ứng tuyển", lblTrangThaiUngTuyen, 8);
        addInfoRow(infoPanel, "Trạng thái thực tập", lblTrangThaiThucTap, 9);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel lblViewOnly = new JLabel(
                "<html>"
                + "<div style='line-height:150%; color:#64748B;'>"
                + "<b style='color:#1E3A8A;'>Lưu ý:</b> Phòng Đào tạo chỉ theo dõi dữ liệu để hỗ trợ sinh viên và tổng hợp báo cáo, không trực tiếp duyệt hay từ chối hồ sơ ứng tuyển."
                + "</div>"
                + "</html>"
        );
        lblViewOnly.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        bottomPanel.add(lblViewOnly, BorderLayout.CENTER);

        detailCard.add(titlePanel, BorderLayout.NORTH);
        detailCard.add(infoPanel, BorderLayout.CENTER);
        detailCard.add(bottomPanel, BorderLayout.SOUTH);

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
        badgePanel.setPreferredSize(new Dimension(560, 64));

        lblTongHoSo = new JLabel("0");
        lblDaCoNoiThucTap = new JLabel("0");
        lblChuaCoNoiThucTap = new JLabel("0");
        lblDangChoXuLy = new JLabel("0");

        badgePanel.add(createBadge("Tổng hồ sơ", lblTongHoSo, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Đã có nơi TT", lblDaCoNoiThucTap, Color.decode("#ECFDF5"), Color.decode("#059669")));
        badgePanel.add(createBadge("Chưa có nơi TT", lblChuaCoNoiThucTap, Color.decode("#FEF2F2"), Color.decode("#DC2626")));
        badgePanel.add(createBadge("Đang xử lý", lblDangChoXuLy, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(390, 42));

        txtTuKhoa = new PlaceholderTextField("Tìm mã SV, họ tên, ngành, doanh nghiệp, trạng thái...");
        txtTuKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTuKhoa.setForeground(COLOR_TEXT);
        txtTuKhoa.setCaretColor(COLOR_TEAL);
        txtTuKhoa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);
        btnLamMoi.setPreferredSize(new Dimension(98, 42));

        JPanel searchButtonPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        searchButtonPanel.setOpaque(false);
        searchButtonPanel.add(btnTimKiem);
        searchButtonPanel.add(btnLamMoi);

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(searchButtonPanel, BorderLayout.EAST);

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
            "Mã UT",
            "Mã SV",
            "Họ tên",
            "Ngành",
            "Doanh nghiệp",
            "Vị trí",
            "Trạng thái UT",
            "Trạng thái TT"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSinhVienUngTuyen = new JTable(tableModel);
        tblSinhVienUngTuyen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblSinhVienUngTuyen.setRowHeight(36);
        tblSinhVienUngTuyen.setForeground(COLOR_TEXT);
        tblSinhVienUngTuyen.setGridColor(Color.decode("#CBD5E1"));
        tblSinhVienUngTuyen.setShowVerticalLines(true);
        tblSinhVienUngTuyen.setShowHorizontalLines(true);
        tblSinhVienUngTuyen.setIntercellSpacing(new Dimension(1, 1));
        tblSinhVienUngTuyen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSinhVienUngTuyen.setSelectionBackground(COLOR_ROW_SELECTED);
        tblSinhVienUngTuyen.setSelectionForeground(COLOR_TEXT);

        tblSinhVienUngTuyen.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblSinhVienUngTuyen.getTableHeader().setForeground(Color.WHITE);
        tblSinhVienUngTuyen.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblSinhVienUngTuyen.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblSinhVienUngTuyen.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblSinhVienUngTuyen.getColumnCount(); i++) {
            tblSinhVienUngTuyen.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblSinhVienUngTuyen.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblSinhVienUngTuyen.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblSinhVienUngTuyen.getColumnModel().getColumn(2).setPreferredWidth(130);
        tblSinhVienUngTuyen.getColumnModel().getColumn(3).setPreferredWidth(135);
        tblSinhVienUngTuyen.getColumnModel().getColumn(4).setPreferredWidth(135);
        tblSinhVienUngTuyen.getColumnModel().getColumn(5).setPreferredWidth(140);
        tblSinhVienUngTuyen.getColumnModel().getColumn(6).setPreferredWidth(120);
        tblSinhVienUngTuyen.getColumnModel().getColumn(7).setPreferredWidth(135);

        JScrollPane scrollPane = new JScrollPane(tblSinhVienUngTuyen);
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
            tblSinhVienUngTuyen.clearSelection();
            clearDetail();
            loadDataToTable();
        });

        tblSinhVienUngTuyen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(danhSach);
    }

    private void loadDataToTable(List<SinhVienUngTuyen> data) {
        tableModel.setRowCount(0);

        for (SinhVienUngTuyen item : data) {
            tableModel.addRow(new Object[]{
                item.getMaUngTuyen(),
                item.getMaSinhVien(),
                item.getHoTen(),
                item.getNganhHoc(),
                item.getDoanhNghiep(),
                item.getViTri(),
                item.getTrangThaiUngTuyen(),
                item.getTrangThaiThucTap()
            });
        }

        updateBadges();
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        List<SinhVienUngTuyen> result = new ArrayList<>();

        for (SinhVienUngTuyen item : danhSach) {
            String searchText = (
                    item.getMaUngTuyen() + " "
                    + item.getMaSinhVien() + " "
                    + item.getHoTen() + " "
                    + item.getNganhHoc() + " "
                    + item.getGpa() + " "
                    + item.getDoanhNghiep() + " "
                    + item.getViTri() + " "
                    + item.getNgayUngTuyen() + " "
                    + item.getTrangThaiUngTuyen() + " "
                    + item.getTrangThaiThucTap()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(item);
            }
        }

        loadDataToTable(result);
    }

    private void updateBadges() {
        int tong = danhSach.size();
        int daCoNoi = 0;
        int chuaCoNoi = 0;
        int dangXuLy = 0;

        for (SinhVienUngTuyen item : danhSach) {
            if ("Đã có nơi thực tập".equalsIgnoreCase(item.getTrangThaiThucTap())) {
                daCoNoi++;
            } else {
                chuaCoNoi++;
            }

            if ("Đã nộp".equalsIgnoreCase(item.getTrangThaiUngTuyen())
                    || "Đã xem".equalsIgnoreCase(item.getTrangThaiUngTuyen())
                    || "Mời phỏng vấn".equalsIgnoreCase(item.getTrangThaiUngTuyen())) {
                dangXuLy++;
            }
        }

        lblTongHoSo.setText(String.valueOf(tong));
        lblDaCoNoiThucTap.setText(String.valueOf(daCoNoi));
        lblChuaCoNoiThucTap.setText(String.valueOf(chuaCoNoi));
        lblDangChoXuLy.setText(String.valueOf(dangXuLy));
    }

    private void fillDetailFromSelectedRow() {
        int selectedRow = tblSinhVienUngTuyen.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String maUngTuyen = tableModel.getValueAt(selectedRow, 0).toString();
        SinhVienUngTuyen item = findByMaUngTuyen(maUngTuyen);

        if (item == null) {
            return;
        }

        lblMaUngTuyen.setText(item.getMaUngTuyen());
        lblMaSinhVien.setText(item.getMaSinhVien());
        lblHoTen.setText(item.getHoTen());
        lblNganhHoc.setText(item.getNganhHoc());
        lblGPA.setText(item.getGpa());
        lblDoanhNghiep.setText(item.getDoanhNghiep());
        lblViTri.setText(item.getViTri());
        lblNgayUngTuyen.setText(item.getNgayUngTuyen());
        lblTrangThaiUngTuyen.setText(item.getTrangThaiUngTuyen());
        lblTrangThaiThucTap.setText(item.getTrangThaiThucTap());
    }

    private void clearDetail() {
        lblMaUngTuyen.setText("Chưa chọn");
        lblMaSinhVien.setText("Chưa chọn");
        lblHoTen.setText("Chưa chọn");
        lblNganhHoc.setText("Chưa chọn");
        lblGPA.setText("Chưa chọn");
        lblDoanhNghiep.setText("Chưa chọn");
        lblViTri.setText("Chưa chọn");
        lblNgayUngTuyen.setText("Chưa chọn");
        lblTrangThaiUngTuyen.setText("Chưa chọn");
        lblTrangThaiThucTap.setText("Chưa chọn");
    }

    private SinhVienUngTuyen findByMaUngTuyen(String maUngTuyen) {
        for (SinhVienUngTuyen item : danhSach) {
            if (item.getMaUngTuyen().equalsIgnoreCase(maUngTuyen)) {
                return item;
            }
        }

        return null;
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel("Chưa chọn");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
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

    private static class SinhVienUngTuyen {

        private String maUngTuyen;
        private String maSinhVien;
        private String hoTen;
        private String nganhHoc;
        private String gpa;
        private String doanhNghiep;
        private String viTri;
        private String ngayUngTuyen;
        private String trangThaiUngTuyen;
        private String trangThaiThucTap;

        public SinhVienUngTuyen(String maUngTuyen, String maSinhVien, String hoTen,
                String nganhHoc, String gpa, String doanhNghiep, String viTri,
                String ngayUngTuyen, String trangThaiUngTuyen, String trangThaiThucTap) {
            this.maUngTuyen = maUngTuyen;
            this.maSinhVien = maSinhVien;
            this.hoTen = hoTen;
            this.nganhHoc = nganhHoc;
            this.gpa = gpa;
            this.doanhNghiep = doanhNghiep;
            this.viTri = viTri;
            this.ngayUngTuyen = ngayUngTuyen;
            this.trangThaiUngTuyen = trangThaiUngTuyen;
            this.trangThaiThucTap = trangThaiThucTap;
        }

        public String getMaUngTuyen() {
            return maUngTuyen;
        }

        public String getMaSinhVien() {
            return maSinhVien;
        }

        public String getHoTen() {
            return hoTen;
        }

        public String getNganhHoc() {
            return nganhHoc;
        }

        public String getGpa() {
            return gpa;
        }

        public String getDoanhNghiep() {
            return doanhNghiep;
        }

        public String getViTri() {
            return viTri;
        }

        public String getNgayUngTuyen() {
            return ngayUngTuyen;
        }

        public String getTrangThaiUngTuyen() {
            return trangThaiUngTuyen;
        }

        public String getTrangThaiThucTap() {
            return trangThaiThucTap;
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