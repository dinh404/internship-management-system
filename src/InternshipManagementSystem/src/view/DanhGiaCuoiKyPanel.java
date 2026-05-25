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

public class DanhGiaCuoiKyPanel extends JPanel {

    private JComboBox<String> cboSinhVien;
    private JComboBox<String> cboThaiDo;
    private JComboBox<String> cboChuyenMon;
    private JComboBox<String> cboTienDo;
    private JComboBox<String> cboKyNangMem;
    private JTextArea txtNhanXet;
    private JTextField txtDeXuat;

    private JLabel lblTongDanhGia;
    private JLabel lblDiemTrungBinh;
    private JLabel lblDatYeuCau;
    private JLabel lblCanHoTro;

    private JTable tblDanhGia;
    private DefaultTableModel tableModel;

    private JButton btnGuiDanhGia;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<DanhGiaCuoiKy> danhSachDanhGia = new ArrayList<>();

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

    public DanhGiaCuoiKyPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        updateBadges();
    }

    private void initData() {
        danhSachDanhGia.add(new DanhGiaCuoiKy(
                "SV001 - Nguyễn Văn A",
                "Java Developer",
                "5",
                "4",
                "5",
                "4",
                "Chủ động học hỏi, hoàn thành tốt nhiệm vụ được giao, giao tiếp tốt với mentor.",
                "Đề xuất tiếp tục hợp tác hoặc xem xét tuyển chính thức.",
                "Đã gửi về trường"
        ));

        danhSachDanhGia.add(new DanhGiaCuoiKy(
                "SV002 - Trần Thị B",
                "Frontend React",
                "4",
                "4",
                "4",
                "5",
                "Thái độ tốt, giao diện hoàn thiện đúng yêu cầu, cần luyện thêm tối ưu responsive.",
                "Đạt yêu cầu thực tập.",
                "Đã gửi về trường"
        ));

        danhSachDanhGia.add(new DanhGiaCuoiKy(
                "SV003 - Lê Văn C",
                "Data Analyst",
                "3",
                "3",
                "3",
                "3",
                "Hoàn thành nhiệm vụ cơ bản nhưng cần cải thiện tính chủ động và khả năng trình bày báo cáo.",
                "Cần giảng viên hướng dẫn hỗ trợ thêm khi tổng kết.",
                "Chờ gửi"
        ));
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFormCard(), BorderLayout.WEST);
        add(createTableCard(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("ĐÁNH GIÁ CUỐI KỲ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Doanh nghiệp/HR chấm thái độ, chuyên môn, tiến độ và gửi kết quả đánh giá về nhà trường");
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

    private RoundedPanel createFormCard() {
        RoundedPanel formCard = new RoundedPanel(24, COLOR_CARD);
        formCard.setLayout(new BorderLayout(0, 14));
        formCard.setPreferredSize(new Dimension(400, 600));
        formCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Phiếu đánh giá năng lực");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblNote = new JLabel("<html><span style='color:#64748B;'>Điểm số được mô phỏng đồng bộ về giảng viên/Phòng Đào tạo làm căn cứ chấm điểm</span></html>");
        lblNote.setFont(new Font("Segoe UI", Font.PLAIN, 12));

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

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        cboSinhVien = createComboBox(new String[]{
            "SV001 - Nguyễn Văn A",
            "SV002 - Trần Thị B",
            "SV003 - Lê Văn C",
            "SV005 - Hoàng Minh E"
        });

        cboThaiDo = createScoreComboBox();
        cboChuyenMon = createScoreComboBox();
        cboTienDo = createScoreComboBox();
        cboKyNangMem = createScoreComboBox();

        txtNhanXet = new JTextArea();
        txtNhanXet.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNhanXet.setForeground(COLOR_TEXT);
        txtNhanXet.setLineWrap(true);
        txtNhanXet.setWrapStyleWord(true);
        txtNhanXet.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(9, 10, 9, 10)
        ));
        txtNhanXet.setPreferredSize(new Dimension(330, 90));

        txtDeXuat = createTextField("VD: Đạt yêu cầu / Đề xuất tuyển chính thức");

        addFormRow(formPanel, "Sinh viên thực tập", cboSinhVien, 0);
        addFormRow(formPanel, "Thái độ", cboThaiDo, 1);
        addFormRow(formPanel, "Chuyên môn", cboChuyenMon, 2);
        addFormRow(formPanel, "Tiến độ công việc", cboTienDo, 3);
        addFormRow(formPanel, "Kỹ năng mềm", cboKyNangMem, 4);
        addFormRow(formPanel, "Nhận xét", new JScrollPane(txtNhanXet), 5);
        addFormRow(formPanel, "Đề xuất", txtDeXuat, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(340, 42));

        btnGuiDanhGia = createActionButton("Gửi đánh giá", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnGuiDanhGia);
        buttonPanel.add(btnLamMoi);

        formCard.add(titlePanel, BorderLayout.NORTH);
        formCard.add(formPanel, BorderLayout.CENTER);
        formCard.add(buttonPanel, BorderLayout.SOUTH);

        return formCard;
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

        lblTongDanhGia = new JLabel("0");
        lblDiemTrungBinh = new JLabel("0.0");
        lblDatYeuCau = new JLabel("0");
        lblCanHoTro = new JLabel("0");

        badgePanel.add(createBadge("Tổng đánh giá", lblTongDanhGia, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Điểm TB", lblDiemTrungBinh, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Đạt yêu cầu", lblDatYeuCau, Color.decode("#E0F2FE"), Color.decode("#0369A1")));
        badgePanel.add(createBadge("Cần hỗ trợ", lblCanHoTro, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JLabel tableTitle = new JLabel("Danh sách đánh giá cuối kỳ");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(COLOR_TEXT);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(tableTitle, BorderLayout.SOUTH);

        String[] columns = {
            "Sinh viên",
            "Vị trí",
            "Thái độ",
            "Chuyên môn",
            "Tiến độ",
            "Kỹ năng",
            "Điểm TB",
            "Trạng thái gửi",
            "Đề xuất"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDanhGia = new JTable(tableModel);
        tblDanhGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblDanhGia.setRowHeight(38);
        tblDanhGia.setForeground(COLOR_TEXT);
        tblDanhGia.setGridColor(Color.decode("#CBD5E1"));
        tblDanhGia.setShowVerticalLines(true);
        tblDanhGia.setShowHorizontalLines(true);
        tblDanhGia.setIntercellSpacing(new Dimension(1, 1));
        tblDanhGia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDanhGia.setSelectionBackground(COLOR_ROW_SELECTED);
        tblDanhGia.setSelectionForeground(COLOR_TEXT);

        tblDanhGia.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblDanhGia.getTableHeader().setForeground(Color.WHITE);
        tblDanhGia.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblDanhGia.getTableHeader().setPreferredSize(new Dimension(0, 42));
        tblDanhGia.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblDanhGia.getColumnCount(); i++) {
            tblDanhGia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblDanhGia.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblDanhGia.getColumnModel().getColumn(1).setPreferredWidth(130);
        tblDanhGia.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblDanhGia.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblDanhGia.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblDanhGia.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblDanhGia.getColumnModel().getColumn(6).setPreferredWidth(75);
        tblDanhGia.getColumnModel().getColumn(7).setPreferredWidth(115);
        tblDanhGia.getColumnModel().getColumn(8).setPreferredWidth(220);

        JScrollPane scrollPane = new JScrollPane(tblDanhGia);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCard.add(topPanel, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private void initEvents() {
        btnGuiDanhGia.addActionListener(e -> handleGuiDanhGia());
        btnLamMoi.addActionListener(e -> clearForm());
    }

    private void handleGuiDanhGia() {
        String nhanXet = txtNhanXet.getText().trim();
        String deXuat = txtDeXuat.getText().trim();

        if (nhanXet.isEmpty() || deXuat.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập nhận xét và đề xuất trước khi gửi đánh giá!");
            return;
        }

        String sinhVien = cboSinhVien.getSelectedItem().toString();
        String viTri = inferViTriBySinhVien(sinhVien);

        danhSachDanhGia.add(new DanhGiaCuoiKy(
                sinhVien,
                viTri,
                cboThaiDo.getSelectedItem().toString(),
                cboChuyenMon.getSelectedItem().toString(),
                cboTienDo.getSelectedItem().toString(),
                cboKyNangMem.getSelectedItem().toString(),
                nhanXet,
                deXuat,
                "Đã gửi về trường"
        ));

        loadDataToTable();
        updateBadges();
        clearForm();

        MessageUtil.showInfo(this, "Đã gửi đánh giá cuối kỳ về giảng viên/Phòng Đào tạo để làm căn cứ chấm điểm.");
    }

    private String inferViTriBySinhVien(String sinhVien) {
        if (sinhVien.contains("SV001")) {
            return "Java Developer";
        }

        if (sinhVien.contains("SV002")) {
            return "Frontend React";
        }

        if (sinhVien.contains("SV003")) {
            return "Data Analyst";
        }

        return "IT Support Intern";
    }

    private void clearForm() {
        cboSinhVien.setSelectedIndex(0);
        cboThaiDo.setSelectedIndex(4);
        cboChuyenMon.setSelectedIndex(3);
        cboTienDo.setSelectedIndex(3);
        cboKyNangMem.setSelectedIndex(3);
        txtNhanXet.setText("");
        txtDeXuat.setText("");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (DanhGiaCuoiKy item : danhSachDanhGia) {
            tableModel.addRow(new Object[]{
                item.getSinhVien(),
                item.getViTri(),
                item.getThaiDo(),
                item.getChuyenMon(),
                item.getTienDo(),
                item.getKyNangMem(),
                String.format("%.1f", item.getDiemTrungBinh()),
                item.getTrangThaiGui(),
                item.getDeXuat()
            });
        }
    }

    private void updateBadges() {
        int tong = danhSachDanhGia.size();
        double tongDiem = 0;
        int datYeuCau = 0;
        int canHoTro = 0;

        for (DanhGiaCuoiKy item : danhSachDanhGia) {
            double diem = item.getDiemTrungBinh();
            tongDiem += diem;

            if (diem >= 3.5) {
                datYeuCau++;
            } else {
                canHoTro++;
            }
        }

        double diemTrungBinh = tong == 0 ? 0 : tongDiem / tong;

        lblTongDanhGia.setText(String.valueOf(tong));
        lblDiemTrungBinh.setText(String.format("%.1f", diemTrungBinh));
        lblDatYeuCau.setText(String.valueOf(datYeuCau));
        lblCanHoTro.setText(String.valueOf(canHoTro));
    }

    private JComboBox<String> createScoreComboBox() {
        return createComboBox(new String[]{"1", "2", "3", "4", "5"});
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
        textField.setPreferredSize(new Dimension(340, 40));
        return textField;
    }

    private void addFormRow(JPanel panel, String labelText, java.awt.Component component, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 12, 0);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(foregroundColor);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private static class DanhGiaCuoiKy {

        private String sinhVien;
        private String viTri;
        private String thaiDo;
        private String chuyenMon;
        private String tienDo;
        private String kyNangMem;
        private String nhanXet;
        private String deXuat;
        private String trangThaiGui;

        public DanhGiaCuoiKy(String sinhVien, String viTri, String thaiDo,
                String chuyenMon, String tienDo, String kyNangMem,
                String nhanXet, String deXuat, String trangThaiGui) {
            this.sinhVien = sinhVien;
            this.viTri = viTri;
            this.thaiDo = thaiDo;
            this.chuyenMon = chuyenMon;
            this.tienDo = tienDo;
            this.kyNangMem = kyNangMem;
            this.nhanXet = nhanXet;
            this.deXuat = deXuat;
            this.trangThaiGui = trangThaiGui;
        }

        public String getSinhVien() {
            return sinhVien;
        }

        public String getViTri() {
            return viTri;
        }

        public String getThaiDo() {
            return thaiDo;
        }

        public String getChuyenMon() {
            return chuyenMon;
        }

        public String getTienDo() {
            return tienDo;
        }

        public String getKyNangMem() {
            return kyNangMem;
        }

        public String getNhanXet() {
            return nhanXet;
        }

        public String getDeXuat() {
            return deXuat;
        }

        public String getTrangThaiGui() {
            return trangThaiGui;
        }

        public double getDiemTrungBinh() {
            return (
                    Integer.parseInt(thaiDo)
                    + Integer.parseInt(chuyenMon)
                    + Integer.parseInt(tienDo)
                    + Integer.parseInt(kyNangMem)
            ) / 4.0;
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
