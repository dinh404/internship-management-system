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

public class LichPhongVanPanel extends JPanel {

    private JComboBox<String> cboSinhVien;
    private JComboBox<String> cboViTri;
    private JComboBox<String> cboHinhThuc;
    private JTextField txtNgayPhongVan;
    private JTextField txtGioPhongVan;
    private JTextField txtDiaDiem;
    private JTextField txtGhiChu;

    private JLabel lblHomNay;
    private JLabel lblTuanNay;
    private JLabel lblOnline;
    private JLabel lblChoXacNhan;

    private JTable tblLichPhongVan;
    private DefaultTableModel tableModel;

    private JButton btnThemLich;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<LichPhongVan> danhSachLich = new ArrayList<>();

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

    public LichPhongVanPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        updateBadges();
    }

    private void initData() {
        danhSachLich.add(new LichPhongVan("PV001", "SV002 - Trần Thị B", "Frontend React", "25/07/2026", "09:00", "Online", "Google Meet", "Chờ xác nhận"));
        danhSachLich.add(new LichPhongVan("PV002", "SV001 - Nguyễn Văn A", "Java Developer", "25/07/2026", "14:00", "Offline", "Phòng họp A1", "Đã xác nhận"));
        danhSachLich.add(new LichPhongVan("PV003", "SV005 - Hoàng Minh E", "IT Support Intern", "28/07/2026", "10:30", "Online", "Microsoft Teams", "Chờ xác nhận"));
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

        JLabel lblTitle = new JLabel("LỊCH PHỎNG VẤN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Doanh nghiệp/HR lên lịch phỏng vấn, chọn hình thức và gửi thông báo mô phỏng cho sinh viên");
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
        formCard.setPreferredSize(new Dimension(390, 600));
        formCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Thiết lập lịch hẹn");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblNote = new JLabel("<html><span style='color:#64748B;'>Sau khi tạo lịch, hệ thống sẽ gửi thông báo/email mô phỏng cho sinh viên</span></html>");
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

        cboViTri = createComboBox(new String[]{
            "Java Developer",
            "Frontend React",
            "Data Analyst",
            "IT Support Intern"
        });

        cboHinhThuc = createComboBox(new String[]{
            "Online",
            "Offline"
        });

        txtNgayPhongVan = createTextField("VD: 30/07/2026");
        txtGioPhongVan = createTextField("VD: 09:30");
        txtDiaDiem = createTextField("Link meeting hoặc phòng họp");
        txtGhiChu = createTextField("Ghi chú thêm cho sinh viên");

        addFormRow(formPanel, "Sinh viên", cboSinhVien, 0);
        addFormRow(formPanel, "Vị trí", cboViTri, 1);
        addFormRow(formPanel, "Ngày phỏng vấn", txtNgayPhongVan, 2);
        addFormRow(formPanel, "Giờ phỏng vấn", txtGioPhongVan, 3);
        addFormRow(formPanel, "Hình thức", cboHinhThuc, 4);
        addFormRow(formPanel, "Địa điểm/Link", txtDiaDiem, 5);
        addFormRow(formPanel, "Ghi chú", txtGhiChu, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(330, 42));

        btnThemLich = createActionButton("Gửi lịch mời", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnThemLich);
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

        lblHomNay = new JLabel("0");
        lblTuanNay = new JLabel("0");
        lblOnline = new JLabel("0");
        lblChoXacNhan = new JLabel("0");

        badgePanel.add(createBadge("Hôm nay", lblHomNay, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Tuần này", lblTuanNay, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Online", lblOnline, Color.decode("#E0F2FE"), Color.decode("#0369A1")));
        badgePanel.add(createBadge("Chờ xác nhận", lblChoXacNhan, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JLabel tableTitle = new JLabel("Lịch biểu phỏng vấn");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(COLOR_TEXT);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(tableTitle, BorderLayout.SOUTH);

        String[] columns = {
            "Mã lịch", "Sinh viên", "Vị trí", "Ngày", "Giờ", "Hình thức", "Địa điểm/Link", "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblLichPhongVan = createStyledTable(tableModel);
        tblLichPhongVan.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblLichPhongVan.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblLichPhongVan.getColumnModel().getColumn(2).setPreferredWidth(130);
        tblLichPhongVan.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblLichPhongVan.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblLichPhongVan.getColumnModel().getColumn(5).setPreferredWidth(90);
        tblLichPhongVan.getColumnModel().getColumn(6).setPreferredWidth(150);
        tblLichPhongVan.getColumnModel().getColumn(7).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblLichPhongVan);
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
        btnThemLich.addActionListener(e -> handleThemLich());
        btnLamMoi.addActionListener(e -> clearForm());
    }

    private void handleThemLich() {
        String ngay = txtNgayPhongVan.getText().trim();
        String gio = txtGioPhongVan.getText().trim();
        String diaDiem = txtDiaDiem.getText().trim();

        if (ngay.isEmpty() || gio.isEmpty() || diaDiem.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập đầy đủ ngày, giờ và địa điểm/link phỏng vấn!");
            return;
        }

        String maLich = "PV" + String.format("%03d", danhSachLich.size() + 1);

        danhSachLich.add(new LichPhongVan(
                maLich,
                cboSinhVien.getSelectedItem().toString(),
                cboViTri.getSelectedItem().toString(),
                ngay,
                gio,
                cboHinhThuc.getSelectedItem().toString(),
                diaDiem,
                "Chờ xác nhận"
        ));

        loadDataToTable();
        updateBadges();
        clearForm();

        MessageUtil.showInfo(this, "Đã gửi lịch mời phỏng vấn cho sinh viên qua thông báo/email mô phỏng.");
    }

    private void clearForm() {
        cboSinhVien.setSelectedIndex(0);
        cboViTri.setSelectedIndex(0);
        cboHinhThuc.setSelectedIndex(0);
        txtNgayPhongVan.setText("");
        txtGioPhongVan.setText("");
        txtDiaDiem.setText("");
        txtGhiChu.setText("");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (LichPhongVan item : danhSachLich) {
            tableModel.addRow(new Object[]{
                item.getMaLich(), item.getSinhVien(), item.getViTri(), item.getNgay(),
                item.getGio(), item.getHinhThuc(), item.getDiaDiem(), item.getTrangThai()
            });
        }
    }

    private void updateBadges() {
        int homNay = 0;
        int tuanNay = danhSachLich.size();
        int online = 0;
        int choXacNhan = 0;

        for (LichPhongVan item : danhSachLich) {
            if ("25/07/2026".equals(item.getNgay())) {
                homNay++;
            }

            if ("Online".equalsIgnoreCase(item.getHinhThuc())) {
                online++;
            }

            if ("Chờ xác nhận".equalsIgnoreCase(item.getTrangThai())) {
                choXacNhan++;
            }
        }

        lblHomNay.setText(String.valueOf(homNay));
        lblTuanNay.setText(String.valueOf(tuanNay));
        lblOnline.setText(String.valueOf(online));
        lblChoXacNhan.setText(String.valueOf(choXacNhan));
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

    private static class LichPhongVan {
        private String maLich;
        private String sinhVien;
        private String viTri;
        private String ngay;
        private String gio;
        private String hinhThuc;
        private String diaDiem;
        private String trangThai;

        public LichPhongVan(String maLich, String sinhVien, String viTri, String ngay, String gio,
                String hinhThuc, String diaDiem, String trangThai) {
            this.maLich = maLich;
            this.sinhVien = sinhVien;
            this.viTri = viTri;
            this.ngay = ngay;
            this.gio = gio;
            this.hinhThuc = hinhThuc;
            this.diaDiem = diaDiem;
            this.trangThai = trangThai;
        }

        public String getMaLich() { return maLich; }
        public String getSinhVien() { return sinhVien; }
        public String getViTri() { return viTri; }
        public String getNgay() { return ngay; }
        public String getGio() { return gio; }
        public String getHinhThuc() { return hinhThuc; }
        public String getDiaDiem() { return diaDiem; }
        public String getTrangThai() { return trangThai; }
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
