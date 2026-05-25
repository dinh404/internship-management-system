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

public class AdminHoTroSuCoPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblSuCo;
    private DefaultTableModel tableModel;

    private JLabel lblTongSuCo;
    private JLabel lblMoi;
    private JLabel lblDangXuLy;
    private JLabel lblDaXuLy;

    private JLabel lblMaSuCo;
    private JLabel lblNguoiGui;
    private JLabel lblVaiTro;
    private JLabel lblTieuDe;
    private JLabel lblMucDo;
    private JLabel lblNgayGui;
    private JLabel lblTrangThai;
    private JTextArea txtNoiDung;
    private JTextArea txtPhanHoi;

    private JComboBox<String> cboTrangThai;
    private JTextArea txtPhanHoiMoi;

    private JButton btnTimKiem;
    private JButton btnCapNhat;
    private JButton btnDanhDauXong;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<SuCoHoTro> danhSachSuCo = new ArrayList<>();

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

    public AdminHoTroSuCoPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
        updateBadges();
    }

    private void initData() {
        danhSachSuCo.add(new SuCoHoTro(
                "SC001",
                "Nguyễn Văn A",
                "Sinh viên",
                "Không tải được CV",
                "Cao",
                "22/07/2026",
                "Mới",
                "Khi bấm tải CV mới trong màn ứng tuyển, hệ thống không nhận file PDF.",
                "Chưa có phản hồi."
        ));

        danhSachSuCo.add(new SuCoHoTro(
                "SC002",
                "Nhân viên HR - Công ty ABC",
                "HR",
                "Không cập nhật được lịch phỏng vấn",
                "Trung bình",
                "23/07/2026",
                "Đang xử lý",
                "Sau khi tạo lịch phỏng vấn, HR muốn sửa giờ nhưng chưa thấy chức năng chỉnh sửa.",
                "Admin đang kiểm tra và sẽ bổ sung vào phiên bản tiếp theo."
        ));

        danhSachSuCo.add(new SuCoHoTro(
                "SC003",
                "Phòng Đào tạo",
                "Phòng Đào tạo",
                "Cần xuất báo cáo theo ngành",
                "Thấp",
                "24/07/2026",
                "Đã xử lý",
                "Phòng Đào tạo cần màn thống kê hiển thị số sinh viên đã có nơi thực tập theo từng ngành.",
                "Đã bổ sung bảng tổng hợp theo ngành trong màn Thống kê & báo cáo."
        ));

        danhSachSuCo.add(new SuCoHoTro(
                "SC004",
                "Công ty MNO",
                "Doanh nghiệp",
                "Tài khoản chờ xác thực quá lâu",
                "Cao",
                "24/07/2026",
                "Mới",
                "Doanh nghiệp đã đăng ký nhưng chưa được cấp quyền đăng tin tuyển dụng.",
                "Chưa có phản hồi."
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

        JLabel lblTitle = new JLabel("HỖ TRỢ & XỬ LÝ SỰ CỐ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Admin tiếp nhận phản hồi, lỗi kỹ thuật và cập nhật trạng thái xử lý cho người dùng hệ thống");
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
        detailCard.setPreferredSize(new Dimension(420, 600));
        detailCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Chi tiết phản hồi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);

        lblMaSuCo = createValueLabel();
        lblNguoiGui = createValueLabel();
        lblVaiTro = createValueLabel();
        lblTieuDe = createValueLabel();
        lblMucDo = createValueLabel();
        lblNgayGui = createValueLabel();
        lblTrangThai = createValueLabel();
        txtNoiDung = createReadOnlyTextArea(82);
        txtPhanHoi = createReadOnlyTextArea(72);

        addInfoRow(infoPanel, "Mã sự cố", lblMaSuCo, 0);
        addInfoRow(infoPanel, "Người gửi", lblNguoiGui, 1);
        addInfoRow(infoPanel, "Vai trò", lblVaiTro, 2);
        addInfoRow(infoPanel, "Tiêu đề", lblTieuDe, 3);
        addInfoRow(infoPanel, "Mức độ", lblMucDo, 4);
        addInfoRow(infoPanel, "Ngày gửi", lblNgayGui, 5);
        addInfoRow(infoPanel, "Trạng thái", lblTrangThai, 6);
        addTextAreaRow(infoPanel, "Nội dung sự cố", txtNoiDung, 7);
        addTextAreaRow(infoPanel, "Phản hồi hiện tại", txtPhanHoi, 8);

        JScrollPane detailScrollPane = new JScrollPane(infoPanel);
        detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
        detailScrollPane.getViewport().setBackground(COLOR_CARD);

        JPanel actionPanel = new JPanel(new BorderLayout(0, 10));
        actionPanel.setOpaque(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        cboTrangThai = createComboBox(new String[]{
            "Mới",
            "Đang xử lý",
            "Đã xử lý"
        });

        txtPhanHoiMoi = new JTextArea();
        txtPhanHoiMoi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtPhanHoiMoi.setForeground(COLOR_TEXT);
        txtPhanHoiMoi.setLineWrap(true);
        txtPhanHoiMoi.setWrapStyleWord(true);
        txtPhanHoiMoi.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtPhanHoiMoi.setPreferredSize(new Dimension(340, 72));

        addFormRow(formPanel, "Trạng thái xử lý", cboTrangThai, 0);
        addFormRow(formPanel, "Phản hồi mới", txtPhanHoiMoi, 1);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(350, 42));

        btnCapNhat = createActionButton("Cập nhật", COLOR_NAVY, Color.WHITE);
        btnDanhDauXong = createActionButton("Xong", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnDanhDauXong);
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

        lblTongSuCo = new JLabel("0");
        lblMoi = new JLabel("0");
        lblDangXuLy = new JLabel("0");
        lblDaXuLy = new JLabel("0");

        badgePanel.add(createBadge("Tổng phản hồi", lblTongSuCo, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Mới", lblMoi, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Đang xử lý", lblDangXuLy, Color.decode("#E0F2FE"), Color.decode("#0369A1")));
        badgePanel.add(createBadge("Đã xử lý", lblDaXuLy, Color.decode("#ECFDF5"), COLOR_TEAL));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(720, 42));

        txtTuKhoa = createTextField("Tìm mã, người gửi, vai trò, tiêu đề, mức độ, trạng thái...");
        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        String[] columns = {
            "Mã SC",
            "Người gửi",
            "Vai trò",
            "Tiêu đề",
            "Mức độ",
            "Ngày gửi",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSuCo = createStyledTable(tableModel);
        tblSuCo.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblSuCo.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblSuCo.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblSuCo.getColumnModel().getColumn(3).setPreferredWidth(220);
        tblSuCo.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblSuCo.getColumnModel().getColumn(5).setPreferredWidth(95);
        tblSuCo.getColumnModel().getColumn(6).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblSuCo);
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
        tblSuCo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });

        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnCapNhat.addActionListener(e -> handleCapNhat(cboTrangThai.getSelectedItem().toString()));
        btnDanhDauXong.addActionListener(e -> handleCapNhat("Đã xử lý"));

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblSuCo.clearSelection();
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

        for (SuCoHoTro item : danhSachSuCo) {
            String searchText = (
                    item.getMaSuCo() + " "
                    + item.getNguoiGui() + " "
                    + item.getVaiTro() + " "
                    + item.getTieuDe() + " "
                    + item.getMucDo() + " "
                    + item.getNgayGui() + " "
                    + item.getTrangThai() + " "
                    + item.getNoiDung()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                addRow(item);
            }
        }
    }

    private void handleCapNhat(String trangThaiMoi) {
        SuCoHoTro item = getSelectedSuCo();

        if (item == null) {
            MessageUtil.showError(this, "Vui lòng chọn phản hồi/sự cố cần xử lý!");
            return;
        }

        String phanHoi = txtPhanHoiMoi.getText().trim();

        if (phanHoi.isEmpty()) {
            phanHoi = "Admin đã tiếp nhận và cập nhật trạng thái xử lý.";
        }

        item.setTrangThai(trangThaiMoi);
        item.setPhanHoi(phanHoi);

        loadDataToTable();
        updateBadges();
        fillDetail(item);

        MessageUtil.showInfo(this, "Đã cập nhật trạng thái hỗ trợ và gửi phản hồi mô phỏng cho người dùng.");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (SuCoHoTro item : danhSachSuCo) {
            addRow(item);
        }

        updateBadges();
    }

    private void addRow(SuCoHoTro item) {
        tableModel.addRow(new Object[]{
            item.getMaSuCo(),
            item.getNguoiGui(),
            item.getVaiTro(),
            item.getTieuDe(),
            item.getMucDo(),
            item.getNgayGui(),
            item.getTrangThai()
        });
    }

    private void updateBadges() {
        int tong = danhSachSuCo.size();
        int moi = 0;
        int dangXuLy = 0;
        int daXuLy = 0;

        for (SuCoHoTro item : danhSachSuCo) {
            if ("Mới".equalsIgnoreCase(item.getTrangThai())) {
                moi++;
            }

            if ("Đang xử lý".equalsIgnoreCase(item.getTrangThai())) {
                dangXuLy++;
            }

            if ("Đã xử lý".equalsIgnoreCase(item.getTrangThai())) {
                daXuLy++;
            }
        }

        lblTongSuCo.setText(String.valueOf(tong));
        lblMoi.setText(String.valueOf(moi));
        lblDangXuLy.setText(String.valueOf(dangXuLy));
        lblDaXuLy.setText(String.valueOf(daXuLy));
    }

    private void fillDetailFromSelectedRow() {
        SuCoHoTro item = getSelectedSuCo();

        if (item != null) {
            fillDetail(item);
        }
    }

    private SuCoHoTro getSelectedSuCo() {
        int selectedRow = tblSuCo.getSelectedRow();

        if (selectedRow < 0) {
            return null;
        }

        String maSuCo = tableModel.getValueAt(selectedRow, 0).toString();

        for (SuCoHoTro item : danhSachSuCo) {
            if (item.getMaSuCo().equalsIgnoreCase(maSuCo)) {
                return item;
            }
        }

        return null;
    }

    private void fillDetail(SuCoHoTro item) {
        lblMaSuCo.setText(item.getMaSuCo());
        lblNguoiGui.setText(item.getNguoiGui());
        lblVaiTro.setText(item.getVaiTro());
        lblTieuDe.setText(item.getTieuDe());
        lblMucDo.setText(item.getMucDo());
        lblNgayGui.setText(item.getNgayGui());
        lblTrangThai.setText(item.getTrangThai());
        txtNoiDung.setText(item.getNoiDung());
        txtPhanHoi.setText(item.getPhanHoi());
        cboTrangThai.setSelectedItem(item.getTrangThai());
        txtPhanHoiMoi.setText("");
    }

    private void clearDetail() {
        lblMaSuCo.setText("Chưa chọn");
        lblNguoiGui.setText("Chưa chọn");
        lblVaiTro.setText("Chưa chọn");
        lblTieuDe.setText("Chưa chọn");
        lblMucDo.setText("Chưa chọn");
        lblNgayGui.setText("Chưa chọn");
        lblTrangThai.setText("Chưa chọn");
        txtNoiDung.setText("Chưa chọn");
        txtPhanHoi.setText("Chưa chọn");
        txtPhanHoiMoi.setText("");
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

    private static class SuCoHoTro {
        private String maSuCo;
        private String nguoiGui;
        private String vaiTro;
        private String tieuDe;
        private String mucDo;
        private String ngayGui;
        private String trangThai;
        private String noiDung;
        private String phanHoi;

        public SuCoHoTro(String maSuCo, String nguoiGui, String vaiTro, String tieuDe,
                String mucDo, String ngayGui, String trangThai, String noiDung, String phanHoi) {
            this.maSuCo = maSuCo;
            this.nguoiGui = nguoiGui;
            this.vaiTro = vaiTro;
            this.tieuDe = tieuDe;
            this.mucDo = mucDo;
            this.ngayGui = ngayGui;
            this.trangThai = trangThai;
            this.noiDung = noiDung;
            this.phanHoi = phanHoi;
        }

        public String getMaSuCo() { return maSuCo; }
        public String getNguoiGui() { return nguoiGui; }
        public String getVaiTro() { return vaiTro; }
        public String getTieuDe() { return tieuDe; }
        public String getMucDo() { return mucDo; }
        public String getNgayGui() { return ngayGui; }
        public String getTrangThai() { return trangThai; }
        public String getNoiDung() { return noiDung; }
        public String getPhanHoi() { return phanHoi; }
        public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
        public void setPhanHoi(String phanHoi) { this.phanHoi = phanHoi; }
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
