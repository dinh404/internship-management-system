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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.TaiKhoan;
import util.MessageUtil;

public class AdminDoanhNghiepPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblDoanhNghiep;
    private DefaultTableModel tableModel;

    private JLabel lblTongDoanhNghiep;
    private JLabel lblDangHoatDong;
    private JLabel lblChoCapQuyen;
    private JLabel lblBiKhoa;

    private JLabel lblMaDoanhNghiep;
    private JLabel lblTenDoanhNghiep;
    private JLabel lblTaiKhoan;
    private JLabel lblEmail;
    private JLabel lblLinhVuc;
    private JLabel lblDiaChi;
    private JLabel lblNguoiDaiDien;
    private JLabel lblTrangThaiTaiKhoan;
    private JLabel lblTrangThaiXacThuc;

    private JTextArea txtGhiChu;
    private JTextArea txtLyDoXuLy;

    private JComboBox<String> cboTrangThaiTaiKhoan;

    private JButton btnTimKiem;
    private JButton btnCapQuyen;
    private JButton btnKhoaTaiKhoan;
    private JButton btnMoKhoa;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<DoanhNghiepTaiKhoan> danhSachDoanhNghiep = new ArrayList<>();

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
    private final Color COLOR_DANGER = Color.decode("#DC2626");

    public AdminDoanhNghiepPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
        updateBadges();
    }

    private void initData() {
        danhSachDoanhNghiep.add(new DoanhNghiepTaiKhoan(
                "DN001",
                "Công ty ABC",
                "dn001",
                "hr@abc.com",
                "Phát triển phần mềm",
                "TP. Hồ Chí Minh",
                "Nguyễn Hoàng Nam",
                "Đang hoạt động",
                "Đã xác thực",
                "Đối tác đang tuyển nhiều vị trí Java/Tester, tài khoản hoạt động ổn định."
        ));

        danhSachDoanhNghiep.add(new DoanhNghiepTaiKhoan(
                "DN002",
                "Công ty XYZ",
                "dn002",
                "contact@xyz.vn",
                "Thiết kế Web/UI",
                "Hà Nội",
                "Trần Minh Anh",
                "Chờ cấp quyền",
                "Chờ xác thực",
                "Doanh nghiệp mới đăng ký, cần kiểm tra hồ sơ trước khi kích hoạt."
        ));

        danhSachDoanhNghiep.add(new DoanhNghiepTaiKhoan(
                "DN003",
                "Công ty MNO",
                "dn003",
                "talent@mno.vn",
                "Phân tích dữ liệu",
                "Đà Nẵng",
                "Lê Quốc Huy",
                "Đang hoạt động",
                "Đã xác thực",
                "Có phản hồi cần xem xét từ sinh viên về chất lượng mentor."
        ));

        danhSachDoanhNghiep.add(new DoanhNghiepTaiKhoan(
                "DN004",
                "Công ty Demo Sai Quy Định",
                "dn004",
                "demo@invalid.vn",
                "Không xác định",
                "Không rõ",
                "Không rõ",
                "Bị khóa",
                "Từ chối xác thực",
                "Tài khoản bị khóa do thông tin đăng ký không đầy đủ."
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

        JLabel lblTitle = new JLabel("QUẢN LÝ DOANH NGHIỆP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Admin quản lý tài khoản doanh nghiệp, cấp quyền, khóa hoặc mở khóa tài khoản trên nền tảng");
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
        detailCard.setLayout(new BorderLayout(0, 14));
        detailCard.setPreferredSize(new Dimension(410, 600));
        detailCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Chi tiết tài khoản");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);

        lblMaDoanhNghiep = createValueLabel();
        lblTenDoanhNghiep = createValueLabel();
        lblTaiKhoan = createValueLabel();
        lblEmail = createValueLabel();
        lblLinhVuc = createValueLabel();
        lblDiaChi = createValueLabel();
        lblNguoiDaiDien = createValueLabel();
        lblTrangThaiTaiKhoan = createValueLabel();
        lblTrangThaiXacThuc = createValueLabel();
        txtGhiChu = createReadOnlyTextArea(80);

        addInfoRow(infoPanel, "Mã doanh nghiệp", lblMaDoanhNghiep, 0);
        addInfoRow(infoPanel, "Tên doanh nghiệp", lblTenDoanhNghiep, 1);
        addInfoRow(infoPanel, "Tài khoản", lblTaiKhoan, 2);
        addInfoRow(infoPanel, "Email", lblEmail, 3);
        addInfoRow(infoPanel, "Lĩnh vực", lblLinhVuc, 4);
        addInfoRow(infoPanel, "Địa chỉ", lblDiaChi, 5);
        addInfoRow(infoPanel, "Người đại diện", lblNguoiDaiDien, 6);
        addInfoRow(infoPanel, "Trạng thái tài khoản", lblTrangThaiTaiKhoan, 7);
        addInfoRow(infoPanel, "Trạng thái xác thực", lblTrangThaiXacThuc, 8);
        addTextAreaRow(infoPanel, "Ghi chú hệ thống", txtGhiChu, 9);

        JScrollPane detailScrollPane = new JScrollPane(infoPanel);
        detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
        detailScrollPane.getViewport().setBackground(COLOR_CARD);

        JPanel actionPanel = new JPanel(new BorderLayout(0, 10));
        actionPanel.setOpaque(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        cboTrangThaiTaiKhoan = createComboBox(new String[]{
            "Đang hoạt động",
            "Chờ cấp quyền",
            "Bị khóa"
        });

        txtLyDoXuLy = new JTextArea();
        txtLyDoXuLy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtLyDoXuLy.setForeground(COLOR_TEXT);
        txtLyDoXuLy.setLineWrap(true);
        txtLyDoXuLy.setWrapStyleWord(true);
        txtLyDoXuLy.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtLyDoXuLy.setPreferredSize(new Dimension(340, 62));

        addFormRow(formPanel, "Cập nhật trạng thái", cboTrangThaiTaiKhoan, 0);
        addFormRow(formPanel, "Lý do/Ghi chú", txtLyDoXuLy, 1);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(350, 88));

        btnCapQuyen = createActionButton("Cấp quyền", COLOR_TEAL, Color.WHITE);
        btnKhoaTaiKhoan = createActionButton("Khóa TK", COLOR_DANGER, Color.WHITE);
        btnMoKhoa = createActionButton("Mở khóa", COLOR_NAVY, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnCapQuyen);
        buttonPanel.add(btnKhoaTaiKhoan);
        buttonPanel.add(btnMoKhoa);
        buttonPanel.add(btnLamMoi);

        actionPanel.add(formPanel, BorderLayout.CENTER);
        actionPanel.add(buttonPanel, BorderLayout.SOUTH);

        detailCard.add(lblTitle, BorderLayout.NORTH);
        detailCard.add(detailScrollPane, BorderLayout.CENTER);
        detailCard.add(actionPanel, BorderLayout.SOUTH);

        return detailCard;
    }

    private RoundedPanel createTableCard() {
        RoundedPanel tableCard = new RoundedPanel(24, COLOR_CARD);
        tableCard.setLayout(new BorderLayout(0, 18));
        tableCard.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel topPanel = new JPanel(new BorderLayout(0, 18));
        topPanel.setOpaque(false);

        JPanel badgePanel = new JPanel(new GridLayout(1, 4, 12, 0));
        badgePanel.setOpaque(false);
        badgePanel.setPreferredSize(new Dimension(720, 70));

        lblTongDoanhNghiep = new JLabel("0");
        lblDangHoatDong = new JLabel("0");
        lblChoCapQuyen = new JLabel("0");
        lblBiKhoa = new JLabel("0");

        badgePanel.add(createBadge("Tổng DN", lblTongDoanhNghiep, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Hoạt động", lblDangHoatDong, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Chờ cấp quyền", lblChoCapQuyen, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Bị khóa", lblBiKhoa, Color.decode("#FEF2F2"), COLOR_DANGER));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(720, 42));

        txtTuKhoa = createTextField("Tìm mã DN, tên, tài khoản, email, lĩnh vực, trạng thái...");
        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        String[] columns = {
            "Mã DN",
            "Tên doanh nghiệp",
            "Tài khoản",
            "Email",
            "Lĩnh vực",
            "Địa chỉ",
            "Trạng thái TK",
            "Xác thực"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDoanhNghiep = createStyledTable(tableModel);
        tblDoanhNghiep.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblDoanhNghiep.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDoanhNghiep.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblDoanhNghiep.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDoanhNghiep.getColumnModel().getColumn(4).setPreferredWidth(140);
        tblDoanhNghiep.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblDoanhNghiep.getColumnModel().getColumn(6).setPreferredWidth(120);
        tblDoanhNghiep.getColumnModel().getColumn(7).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tblDoanhNghiep);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCard.add(topPanel, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(38);
        table.setForeground(COLOR_TEXT);
        table.setGridColor(Color.decode("#CBD5E1"));
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(COLOR_ROW_SELECTED);
        table.setSelectionForeground(COLOR_TEXT);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        table.getTableHeader().setPreferredSize(new Dimension(0, 42));
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }

    private void initEvents() {
        tblDoanhNghiep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });

        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnCapQuyen.addActionListener(e -> handleCapNhat("Đang hoạt động", "Đã xác thực"));
        btnKhoaTaiKhoan.addActionListener(e -> handleCapNhat("Bị khóa", "Tạm khóa"));
        btnMoKhoa.addActionListener(e -> handleCapNhat("Đang hoạt động", "Đã xác thực"));

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblDoanhNghiep.clearSelection();
            clearDetail();
            loadDataToTable();
        });
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        tableModel.setRowCount(0);

        for (DoanhNghiepTaiKhoan item : danhSachDoanhNghiep) {
            String searchText = (
                    item.getMaDoanhNghiep() + " "
                    + item.getTenDoanhNghiep() + " "
                    + item.getTaiKhoan() + " "
                    + item.getEmail() + " "
                    + item.getLinhVuc() + " "
                    + item.getDiaChi() + " "
                    + item.getNguoiDaiDien() + " "
                    + item.getTrangThaiTaiKhoan() + " "
                    + item.getTrangThaiXacThuc()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                addRow(item);
            }
        }
    }

    private void handleCapNhat(String trangThaiTaiKhoanMoi, String trangThaiXacThucMoi) {
        DoanhNghiepTaiKhoan item = getSelectedDoanhNghiep();

        if (item == null) {
            MessageUtil.showError(this, "Vui lòng chọn doanh nghiệp cần xử lý!");
            return;
        }

        String lyDo = txtLyDoXuLy.getText().trim();

        if ("Bị khóa".equals(trangThaiTaiKhoanMoi) && lyDo.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập lý do khi khóa tài khoản doanh nghiệp!");
            return;
        }

        if (lyDo.isEmpty()) {
            lyDo = "Admin đã cập nhật trạng thái tài khoản doanh nghiệp.";
        }

        item.setTrangThaiTaiKhoan(trangThaiTaiKhoanMoi);
        item.setTrangThaiXacThuc(trangThaiXacThucMoi);
        item.setGhiChu(lyDo);

        loadDataToTable();
        updateBadges();
        fillDetail(item);

        MessageUtil.showInfo(this, "Đã cập nhật tài khoản doanh nghiệp và gửi thông báo mô phỏng cho doanh nghiệp.");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (DoanhNghiepTaiKhoan item : danhSachDoanhNghiep) {
            addRow(item);
        }

        updateBadges();
    }

    private void addRow(DoanhNghiepTaiKhoan item) {
        tableModel.addRow(new Object[]{
            item.getMaDoanhNghiep(),
            item.getTenDoanhNghiep(),
            item.getTaiKhoan(),
            item.getEmail(),
            item.getLinhVuc(),
            item.getDiaChi(),
            item.getTrangThaiTaiKhoan(),
            item.getTrangThaiXacThuc()
        });
    }

    private void updateBadges() {
        int tong = danhSachDoanhNghiep.size();
        int dangHoatDong = 0;
        int choCapQuyen = 0;
        int biKhoa = 0;

        for (DoanhNghiepTaiKhoan item : danhSachDoanhNghiep) {
            if ("Đang hoạt động".equalsIgnoreCase(item.getTrangThaiTaiKhoan())) {
                dangHoatDong++;
            }

            if ("Chờ cấp quyền".equalsIgnoreCase(item.getTrangThaiTaiKhoan())) {
                choCapQuyen++;
            }

            if ("Bị khóa".equalsIgnoreCase(item.getTrangThaiTaiKhoan())) {
                biKhoa++;
            }
        }

        lblTongDoanhNghiep.setText(String.valueOf(tong));
        lblDangHoatDong.setText(String.valueOf(dangHoatDong));
        lblChoCapQuyen.setText(String.valueOf(choCapQuyen));
        lblBiKhoa.setText(String.valueOf(biKhoa));
    }

    private void fillDetailFromSelectedRow() {
        DoanhNghiepTaiKhoan item = getSelectedDoanhNghiep();

        if (item != null) {
            fillDetail(item);
        }
    }

    private DoanhNghiepTaiKhoan getSelectedDoanhNghiep() {
        int selectedRow = tblDoanhNghiep.getSelectedRow();

        if (selectedRow < 0) {
            return null;
        }

        String maDoanhNghiep = tableModel.getValueAt(selectedRow, 0).toString();

        for (DoanhNghiepTaiKhoan item : danhSachDoanhNghiep) {
            if (item.getMaDoanhNghiep().equalsIgnoreCase(maDoanhNghiep)) {
                return item;
            }
        }

        return null;
    }

    private void fillDetail(DoanhNghiepTaiKhoan item) {
        lblMaDoanhNghiep.setText(item.getMaDoanhNghiep());
        lblTenDoanhNghiep.setText(item.getTenDoanhNghiep());
        lblTaiKhoan.setText(item.getTaiKhoan());
        lblEmail.setText(item.getEmail());
        lblLinhVuc.setText(item.getLinhVuc());
        lblDiaChi.setText(item.getDiaChi());
        lblNguoiDaiDien.setText(item.getNguoiDaiDien());
        lblTrangThaiTaiKhoan.setText(item.getTrangThaiTaiKhoan());
        lblTrangThaiXacThuc.setText(item.getTrangThaiXacThuc());
        txtGhiChu.setText(item.getGhiChu());
        cboTrangThaiTaiKhoan.setSelectedItem(item.getTrangThaiTaiKhoan());
        txtLyDoXuLy.setText("");
    }

    private void clearDetail() {
        lblMaDoanhNghiep.setText("Chưa chọn");
        lblTenDoanhNghiep.setText("Chưa chọn");
        lblTaiKhoan.setText("Chưa chọn");
        lblEmail.setText("Chưa chọn");
        lblLinhVuc.setText("Chưa chọn");
        lblDiaChi.setText("Chưa chọn");
        lblNguoiDaiDien.setText("Chưa chọn");
        lblTrangThaiTaiKhoan.setText("Chưa chọn");
        lblTrangThaiXacThuc.setText("Chưa chọn");
        txtGhiChu.setText("Chưa chọn");
        txtLyDoXuLy.setText("");
        cboTrangThaiTaiKhoan.setSelectedIndex(0);
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

    private JTextArea createReadOnlyTextArea(int height) {
        JTextArea textArea = new JTextArea("Chưa chọn");
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setForeground(COLOR_TEXT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(true);
        textArea.setBackground(Color.decode("#F8FAFC"));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        textArea.setPreferredSize(new Dimension(340, height));
        return textArea;
    }

    private JComboBox<String> createComboBox(String[] values) {
        JComboBox<String> comboBox = new JComboBox<>(values);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setForeground(COLOR_TEXT);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1));
        comboBox.setPreferredSize(new Dimension(340, 38));
        return comboBox;
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new PlaceholderTextField(placeholder);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setForeground(COLOR_TEXT);
        textField.setCaretColor(COLOR_TEAL);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        textField.setPreferredSize(new Dimension(330, 40));
        return textField;
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

    private void addTextAreaRow(JPanel panel, String labelText, JTextArea textArea, int row) {
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
        gbc.insets = new Insets(0, 0, 7, 0);
        panel.add(textArea, gbc);
    }

    private void addFormRow(JPanel panel, String labelText, java.awt.Component component, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 4, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 9, 0);
        panel.add(component, gbc);
    }

    private RoundedPanel createBadge(String title, JLabel valueLabel, Color backgroundColor, Color textColor) {
        RoundedPanel badge = new RoundedPanel(18, backgroundColor);
        badge.setLayout(new GridBagLayout());
        badge.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(foregroundColor);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private static class DoanhNghiepTaiKhoan {

        private String maDoanhNghiep;
        private String tenDoanhNghiep;
        private String taiKhoan;
        private String email;
        private String linhVuc;
        private String diaChi;
        private String nguoiDaiDien;
        private String trangThaiTaiKhoan;
        private String trangThaiXacThuc;
        private String ghiChu;

        public DoanhNghiepTaiKhoan(String maDoanhNghiep, String tenDoanhNghiep, String taiKhoan,
                String email, String linhVuc, String diaChi, String nguoiDaiDien,
                String trangThaiTaiKhoan, String trangThaiXacThuc, String ghiChu) {
            this.maDoanhNghiep = maDoanhNghiep;
            this.tenDoanhNghiep = tenDoanhNghiep;
            this.taiKhoan = taiKhoan;
            this.email = email;
            this.linhVuc = linhVuc;
            this.diaChi = diaChi;
            this.nguoiDaiDien = nguoiDaiDien;
            this.trangThaiTaiKhoan = trangThaiTaiKhoan;
            this.trangThaiXacThuc = trangThaiXacThuc;
            this.ghiChu = ghiChu;
        }

        public String getMaDoanhNghiep() {
            return maDoanhNghiep;
        }

        public String getTenDoanhNghiep() {
            return tenDoanhNghiep;
        }

        public String getTaiKhoan() {
            return taiKhoan;
        }

        public String getEmail() {
            return email;
        }

        public String getLinhVuc() {
            return linhVuc;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public String getNguoiDaiDien() {
            return nguoiDaiDien;
        }

        public String getTrangThaiTaiKhoan() {
            return trangThaiTaiKhoan;
        }

        public String getTrangThaiXacThuc() {
            return trangThaiXacThuc;
        }

        public String getGhiChu() {
            return ghiChu;
        }

        public void setTrangThaiTaiKhoan(String trangThaiTaiKhoan) {
            this.trangThaiTaiKhoan = trangThaiTaiKhoan;
        }

        public void setTrangThaiXacThuc(String trangThaiXacThuc) {
            this.trangThaiXacThuc = trangThaiXacThuc;
        }

        public void setGhiChu(String ghiChu) {
            this.ghiChu = ghiChu;
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
