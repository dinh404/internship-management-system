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

public class TheoDoiThucTapPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblNhatKy;
    private DefaultTableModel tableModel;

    private JLabel lblTongNhatKy;
    private JLabel lblChoDuyet;
    private JLabel lblDaDuyet;
    private JLabel lblCanBoSung;

    private JLabel lblMaNhatKy;
    private JLabel lblSinhVien;
    private JLabel lblTuan;
    private JLabel lblThoiGian;
    private JLabel lblTrangThai;
    private JTextArea txtCongViec;
    private JTextArea txtKetQua;
    private JTextArea txtNhanXetMentor;

    private JComboBox<String> cboTrangThai;
    private JTextArea txtNhanXetMoi;

    private JButton btnTimKiem;
    private JButton btnDuyet;
    private JButton btnGuiNhanXet;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<NhatKyThucTap> danhSachNhatKy = new ArrayList<>();

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
    private final Color COLOR_ORANGE = Color.decode("#F97316");

    public TheoDoiThucTapPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
        updateBadges();
    }

    private void initData() {
        danhSachNhatKy.add(new NhatKyThucTap(
                "NK001",
                "SV001 - Nguyễn Văn A",
                "Tuần 1",
                "01/07/2026 - 07/07/2026",
                "Tìm hiểu quy trình dự án, cài đặt môi trường Java, đọc tài liệu hệ thống và chạy thử module quản lý tuyển dụng.",
                "Hoàn thành cài đặt môi trường, hiểu cấu trúc source code và viết được báo cáo setup ban đầu.",
                "Đã duyệt",
                "Mentor ghi nhận sinh viên chủ động, cần tiếp tục luyện thêm Git workflow."
        ));

        danhSachNhatKy.add(new NhatKyThucTap(
                "NK002",
                "SV002 - Trần Thị B",
                "Tuần 1",
                "01/07/2026 - 07/07/2026",
                "Tham gia thiết kế giao diện React, dựng layout trang danh sách tin tuyển dụng và form lọc dữ liệu.",
                "Hoàn thành layout cơ bản, còn cần chỉnh responsive cho màn hình nhỏ.",
                "Chờ duyệt",
                ""
        ));

        danhSachNhatKy.add(new NhatKyThucTap(
                "NK003",
                "SV003 - Lê Văn C",
                "Tuần 2",
                "08/07/2026 - 14/07/2026",
                "Làm sạch dữ liệu ứng tuyển, tạo báo cáo thống kê số lượng hồ sơ theo doanh nghiệp và trạng thái.",
                "Báo cáo đã chạy được trên dữ liệu mẫu, cần bổ sung biểu đồ trực quan.",
                "Cần bổ sung",
                "Cần mô tả rõ nguồn dữ liệu và kết quả đầu ra."
        ));

        danhSachNhatKy.add(new NhatKyThucTap(
                "NK004",
                "SV005 - Hoàng Minh E",
                "Tuần 1",
                "01/07/2026 - 07/07/2026",
                "Học quy trình hỗ trợ IT, ghi nhận sự cố máy trạm, phân loại ticket và theo dõi phản hồi người dùng.",
                "Đã xử lý thử 3 ticket nội bộ dưới sự hướng dẫn của mentor.",
                "Chờ duyệt",
                ""
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

        JLabel lblTitle = new JLabel("THEO DÕI THỰC TẬP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("HR/Mentor xem nhật ký làm việc hằng tuần, duyệt tiến độ và gửi nhận xét cho sinh viên");
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

        JLabel lblTitle = new JLabel("Chi tiết nhật ký");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);

        lblMaNhatKy = createValueLabel();
        lblSinhVien = createValueLabel();
        lblTuan = createValueLabel();
        lblThoiGian = createValueLabel();
        lblTrangThai = createValueLabel();

        txtCongViec = createReadOnlyTextArea(76);
        txtKetQua = createReadOnlyTextArea(72);
        txtNhanXetMentor = createReadOnlyTextArea(68);

        addInfoRow(infoPanel, "Mã nhật ký", lblMaNhatKy, 0);
        addInfoRow(infoPanel, "Sinh viên", lblSinhVien, 1);
        addInfoRow(infoPanel, "Tuần thực tập", lblTuan, 2);
        addInfoRow(infoPanel, "Thời gian", lblThoiGian, 3);
        addInfoRow(infoPanel, "Trạng thái", lblTrangThai, 4);
        addTextAreaRow(infoPanel, "Công việc đã thực hiện", txtCongViec, 5);
        addTextAreaRow(infoPanel, "Kết quả đạt được", txtKetQua, 6);
        addTextAreaRow(infoPanel, "Nhận xét hiện tại", txtNhanXetMentor, 7);

        JScrollPane detailScrollPane = new JScrollPane(infoPanel);
        detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
        detailScrollPane.getViewport().setBackground(COLOR_CARD);

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setOpaque(false);

        cboTrangThai = createComboBox(new String[]{
            "Đã duyệt",
            "Cần bổ sung"
        });

        txtNhanXetMoi = new JTextArea();
        txtNhanXetMoi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNhanXetMoi.setForeground(COLOR_TEXT);
        txtNhanXetMoi.setLineWrap(true);
        txtNhanXetMoi.setWrapStyleWord(true);
        txtNhanXetMoi.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtNhanXetMoi.setPreferredSize(new Dimension(330, 70));

        addFormRow(actionPanel, "Cập nhật trạng thái", cboTrangThai, 0);
        addFormRow(actionPanel, "Nhận xét mới", txtNhanXetMoi, 1);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(350, 42));

        btnDuyet = createActionButton("Duyệt", COLOR_TEAL, Color.WHITE);
        btnGuiNhanXet = createActionButton("Nhận xét", COLOR_NAVY, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnDuyet);
        buttonPanel.add(btnGuiNhanXet);
        buttonPanel.add(btnLamMoi);

        JPanel bottomPanel = new JPanel(new BorderLayout(0, 12));
        bottomPanel.setOpaque(false);
        bottomPanel.add(actionPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        detailCard.add(lblTitle, BorderLayout.NORTH);
        detailCard.add(detailScrollPane, BorderLayout.CENTER);
        detailCard.add(bottomPanel, BorderLayout.SOUTH);

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

        lblTongNhatKy = new JLabel("0");
        lblChoDuyet = new JLabel("0");
        lblDaDuyet = new JLabel("0");
        lblCanBoSung = new JLabel("0");

        badgePanel.add(createBadge("Tổng nhật ký", lblTongNhatKy, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Chờ duyệt", lblChoDuyet, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Đã duyệt", lblDaDuyet, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Cần bổ sung", lblCanBoSung, Color.decode("#FEF2F2"), Color.decode("#DC2626")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(720, 42));

        txtTuKhoa = createTextField("Tìm sinh viên, tuần, trạng thái, nội dung nhật ký...");
        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        String[] columns = {
            "Mã NK",
            "Sinh viên",
            "Tuần",
            "Thời gian",
            "Công việc",
            "Kết quả",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblNhatKy = createStyledTable(tableModel);
        tblNhatKy.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblNhatKy.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblNhatKy.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblNhatKy.getColumnModel().getColumn(3).setPreferredWidth(140);
        tblNhatKy.getColumnModel().getColumn(4).setPreferredWidth(250);
        tblNhatKy.getColumnModel().getColumn(5).setPreferredWidth(220);
        tblNhatKy.getColumnModel().getColumn(6).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblNhatKy);
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
        tblNhatKy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });

        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnDuyet.addActionListener(e -> handleCapNhat("Đã duyệt"));
        btnGuiNhanXet.addActionListener(e -> handleCapNhat(cboTrangThai.getSelectedItem().toString()));

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblNhatKy.clearSelection();
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

        for (NhatKyThucTap item : danhSachNhatKy) {
            String searchText = (
                    item.getMaNhatKy() + " "
                    + item.getSinhVien() + " "
                    + item.getTuan() + " "
                    + item.getThoiGian() + " "
                    + item.getCongViec() + " "
                    + item.getKetQua() + " "
                    + item.getTrangThai() + " "
                    + item.getNhanXetMentor()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                addRow(item);
            }
        }
    }

    private void handleCapNhat(String trangThaiMoi) {
        NhatKyThucTap item = getSelectedNhatKy();

        if (item == null) {
            MessageUtil.showError(this, "Vui lòng chọn nhật ký cần xử lý!");
            return;
        }

        String nhanXet = txtNhanXetMoi.getText().trim();

        if (nhanXet.isEmpty()) {
            nhanXet = "Mentor đã xem và cập nhật trạng thái nhật ký.";
        }

        item.setTrangThai(trangThaiMoi);
        item.setNhanXetMentor(nhanXet);

        loadDataToTable();
        updateBadges();
        fillDetail(item);

        MessageUtil.showInfo(this, "Đã cập nhật nhật ký thực tập và gửi thông báo mô phỏng cho sinh viên.");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (NhatKyThucTap item : danhSachNhatKy) {
            addRow(item);
        }

        updateBadges();
    }

    private void addRow(NhatKyThucTap item) {
        tableModel.addRow(new Object[]{
            item.getMaNhatKy(),
            item.getSinhVien(),
            item.getTuan(),
            item.getThoiGian(),
            item.getCongViec(),
            item.getKetQua(),
            item.getTrangThai()
        });
    }

    private void updateBadges() {
        int tong = danhSachNhatKy.size();
        int choDuyet = 0;
        int daDuyet = 0;
        int canBoSung = 0;

        for (NhatKyThucTap item : danhSachNhatKy) {
            if ("Chờ duyệt".equalsIgnoreCase(item.getTrangThai())) {
                choDuyet++;
            }

            if ("Đã duyệt".equalsIgnoreCase(item.getTrangThai())) {
                daDuyet++;
            }

            if ("Cần bổ sung".equalsIgnoreCase(item.getTrangThai())) {
                canBoSung++;
            }
        }

        lblTongNhatKy.setText(String.valueOf(tong));
        lblChoDuyet.setText(String.valueOf(choDuyet));
        lblDaDuyet.setText(String.valueOf(daDuyet));
        lblCanBoSung.setText(String.valueOf(canBoSung));
    }

    private void fillDetailFromSelectedRow() {
        NhatKyThucTap item = getSelectedNhatKy();

        if (item != null) {
            fillDetail(item);
        }
    }

    private NhatKyThucTap getSelectedNhatKy() {
        int selectedRow = tblNhatKy.getSelectedRow();

        if (selectedRow < 0) {
            return null;
        }

        String maNhatKy = tableModel.getValueAt(selectedRow, 0).toString();

        for (NhatKyThucTap item : danhSachNhatKy) {
            if (item.getMaNhatKy().equalsIgnoreCase(maNhatKy)) {
                return item;
            }
        }

        return null;
    }

    private void fillDetail(NhatKyThucTap item) {
        lblMaNhatKy.setText(item.getMaNhatKy());
        lblSinhVien.setText(item.getSinhVien());
        lblTuan.setText(item.getTuan());
        lblThoiGian.setText(item.getThoiGian());
        lblTrangThai.setText(item.getTrangThai());
        txtCongViec.setText(item.getCongViec());
        txtKetQua.setText(item.getKetQua());
        txtNhanXetMentor.setText(item.getNhanXetMentor().isEmpty() ? "Chưa có nhận xét" : item.getNhanXetMentor());
        cboTrangThai.setSelectedItem(item.getTrangThai().equals("Đã duyệt") ? "Đã duyệt" : "Cần bổ sung");
        txtNhanXetMoi.setText("");
    }

    private void clearDetail() {
        lblMaNhatKy.setText("Chưa chọn");
        lblSinhVien.setText("Chưa chọn");
        lblTuan.setText("Chưa chọn");
        lblThoiGian.setText("Chưa chọn");
        lblTrangThai.setText("Chưa chọn");
        txtCongViec.setText("Chưa chọn");
        txtKetQua.setText("Chưa chọn");
        txtNhanXetMentor.setText("Chưa chọn");
        txtNhanXetMoi.setText("");
        cboTrangThai.setSelectedIndex(0);
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
        textArea.setPreferredSize(new Dimension(330, height));
        return textArea;
    }

    private JComboBox<String> createComboBox(String[] values) {
        JComboBox<String> comboBox = new JComboBox<>(values);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setForeground(COLOR_TEXT);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1));
        comboBox.setPreferredSize(new Dimension(330, 38));
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

    private static class NhatKyThucTap {
        private String maNhatKy;
        private String sinhVien;
        private String tuan;
        private String thoiGian;
        private String congViec;
        private String ketQua;
        private String trangThai;
        private String nhanXetMentor;

        public NhatKyThucTap(String maNhatKy, String sinhVien, String tuan, String thoiGian,
                String congViec, String ketQua, String trangThai, String nhanXetMentor) {
            this.maNhatKy = maNhatKy;
            this.sinhVien = sinhVien;
            this.tuan = tuan;
            this.thoiGian = thoiGian;
            this.congViec = congViec;
            this.ketQua = ketQua;
            this.trangThai = trangThai;
            this.nhanXetMentor = nhanXetMentor;
        }

        public String getMaNhatKy() { return maNhatKy; }
        public String getSinhVien() { return sinhVien; }
        public String getTuan() { return tuan; }
        public String getThoiGian() { return thoiGian; }
        public String getCongViec() { return congViec; }
        public String getKetQua() { return ketQua; }
        public String getTrangThai() { return trangThai; }
        public String getNhanXetMentor() { return nhanXetMentor; }
        public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
        public void setNhanXetMentor(String nhanXetMentor) { this.nhanXetMentor = nhanXetMentor; }
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
