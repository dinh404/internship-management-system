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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.TaiKhoan;
import util.MessageUtil;

public class DanhGiaDoanhNghiepPanel extends JPanel {

    private JComboBox<String> cboDoanhNghiep;
    private JComboBox<String> cboCoSoVatChat;
    private JComboBox<String> cboMentor;
    private JComboBox<String> cboPhuCap;
    private JComboBox<String> cboMoiTruong;
    private JTextArea txtNhanXet;

    private JLabel lblTongDanhGia;
    private JLabel lblDiemTrungBinh;
    private JLabel lblDanhGiaTot;
    private JLabel lblCanXemXet;

    private JTable tblDanhGia;
    private DefaultTableModel tableModel;

    private JButton btnGuiDanhGia;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<DanhGiaDoanhNghiep> danhSachDanhGia = new ArrayList<>();

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

    public DanhGiaDoanhNghiepPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        updateBadges();
    }

    private void initData() {
        danhSachDanhGia.add(new DanhGiaDoanhNghiep(
                "Công ty ABC",
                "5",
                "5",
                "4",
                "5",
                "Mentor hỗ trợ tốt, công việc sát chuyên ngành, có phụ cấp rõ ràng.",
                "24/07/2026"
        ));

        danhSachDanhGia.add(new DanhGiaDoanhNghiep(
                "Công ty XYZ",
                "4",
                "4",
                "4",
                "5",
                "Môi trường trẻ, được review code định kỳ, quy trình phỏng vấn rõ ràng.",
                "22/07/2026"
        ));

        danhSachDanhGia.add(new DanhGiaDoanhNghiep(
                "Công ty MNO",
                "3",
                "3",
                "2",
                "3",
                "Công việc phù hợp nhưng mentor phản hồi hơi chậm, cần cải thiện hỗ trợ sinh viên.",
                "20/07/2026"
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

        JLabel lblTitle = new JLabel("ĐÁNH GIÁ DOANH NGHIỆP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Sinh viên gửi khảo sát sau thực tập để nhà trường theo dõi chất lượng doanh nghiệp liên kết");
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

        JLabel lblTitle = new JLabel("Phiếu khảo sát sau thực tập");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblNote = new JLabel("<html><span style='color:#64748B;'>Dữ liệu dùng để Phòng Đào tạo đánh giá chất lượng hợp tác</span></html>");
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

        cboDoanhNghiep = createComboBox(new String[]{
            "Công ty ABC",
            "Công ty XYZ",
            "Công ty MNO",
            "Công ty KTech"
        });

        cboCoSoVatChat = createScoreComboBox();
        cboMentor = createScoreComboBox();
        cboPhuCap = createScoreComboBox();
        cboMoiTruong = createScoreComboBox();

        txtNhanXet = new JTextArea();
        txtNhanXet.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNhanXet.setForeground(COLOR_TEXT);
        txtNhanXet.setLineWrap(true);
        txtNhanXet.setWrapStyleWord(true);
        txtNhanXet.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(9, 10, 9, 10)
        ));

        addFormRow(formPanel, "Doanh nghiệp", cboDoanhNghiep, 0);
        addFormRow(formPanel, "Cơ sở vật chất", cboCoSoVatChat, 1);
        addFormRow(formPanel, "Mentor hướng dẫn", cboMentor, 2);
        addFormRow(formPanel, "Phụ cấp/Hỗ trợ", cboPhuCap, 3);
        addFormRow(formPanel, "Môi trường làm việc", cboMoiTruong, 4);
        addFormRow(formPanel, "Nhận xét", new JScrollPane(txtNhanXet), 5);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(330, 42));

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
        lblDanhGiaTot = new JLabel("0");
        lblCanXemXet = new JLabel("0");

        badgePanel.add(createBadge("Tổng đánh giá", lblTongDanhGia, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Điểm TB", lblDiemTrungBinh, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Đánh giá tốt", lblDanhGiaTot, Color.decode("#E0F2FE"), Color.decode("#0369A1")));
        badgePanel.add(createBadge("Cần xem xét", lblCanXemXet, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JLabel tableTitle = new JLabel("Lịch sử đánh giá doanh nghiệp");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(COLOR_TEXT);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(tableTitle, BorderLayout.SOUTH);

        String[] columns = {
            "Doanh nghiệp",
            "CSVC",
            "Mentor",
            "Phụ cấp",
            "Môi trường",
            "Điểm TB",
            "Ngày gửi",
            "Nhận xét"
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

        tblDanhGia.getColumnModel().getColumn(0).setPreferredWidth(130);
        tblDanhGia.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblDanhGia.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblDanhGia.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblDanhGia.getColumnModel().getColumn(4).setPreferredWidth(85);
        tblDanhGia.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblDanhGia.getColumnModel().getColumn(6).setPreferredWidth(90);
        tblDanhGia.getColumnModel().getColumn(7).setPreferredWidth(330);

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
        String doanhNghiep = cboDoanhNghiep.getSelectedItem().toString();
        String coSoVatChat = cboCoSoVatChat.getSelectedItem().toString();
        String mentor = cboMentor.getSelectedItem().toString();
        String phuCap = cboPhuCap.getSelectedItem().toString();
        String moiTruong = cboMoiTruong.getSelectedItem().toString();
        String nhanXet = txtNhanXet.getText().trim();

        if (nhanXet.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập nhận xét trước khi gửi đánh giá!");
            return;
        }

        danhSachDanhGia.add(new DanhGiaDoanhNghiep(
                doanhNghiep,
                coSoVatChat,
                mentor,
                phuCap,
                moiTruong,
                nhanXet,
                "Hôm nay"
        ));

        loadDataToTable();
        updateBadges();
        clearForm();

        MessageUtil.showInfo(this, "Đã gửi đánh giá doanh nghiệp. Phòng Đào tạo sẽ dùng dữ liệu này để theo dõi chất lượng hợp tác.");
    }

    private void clearForm() {
        cboDoanhNghiep.setSelectedIndex(0);
        cboCoSoVatChat.setSelectedIndex(4);
        cboMentor.setSelectedIndex(4);
        cboPhuCap.setSelectedIndex(3);
        cboMoiTruong.setSelectedIndex(4);
        txtNhanXet.setText("");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (DanhGiaDoanhNghiep item : danhSachDanhGia) {
            tableModel.addRow(new Object[]{
                item.getDoanhNghiep(),
                item.getCoSoVatChat(),
                item.getMentor(),
                item.getPhuCap(),
                item.getMoiTruong(),
                String.format("%.1f", item.getDiemTrungBinh()),
                item.getNgayGui(),
                item.getNhanXet()
            });
        }
    }

    private void updateBadges() {
        int tong = danhSachDanhGia.size();
        double tongDiem = 0;
        int danhGiaTot = 0;
        int canXemXet = 0;

        for (DanhGiaDoanhNghiep item : danhSachDanhGia) {
            double diem = item.getDiemTrungBinh();
            tongDiem += diem;

            if (diem >= 4.0) {
                danhGiaTot++;
            }

            if (diem < 3.5) {
                canXemXet++;
            }
        }

        double diemTrungBinh = tong == 0 ? 0 : tongDiem / tong;

        lblTongDanhGia.setText(String.valueOf(tong));
        lblDiemTrungBinh.setText(String.format("%.1f", diemTrungBinh));
        lblDanhGiaTot.setText(String.valueOf(danhGiaTot));
        lblCanXemXet.setText(String.valueOf(canXemXet));
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
        comboBox.setPreferredSize(new Dimension(330, 38));
        return comboBox;
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

    private static class DanhGiaDoanhNghiep {

        private String doanhNghiep;
        private String coSoVatChat;
        private String mentor;
        private String phuCap;
        private String moiTruong;
        private String nhanXet;
        private String ngayGui;

        public DanhGiaDoanhNghiep(String doanhNghiep, String coSoVatChat,
                String mentor, String phuCap, String moiTruong,
                String nhanXet, String ngayGui) {
            this.doanhNghiep = doanhNghiep;
            this.coSoVatChat = coSoVatChat;
            this.mentor = mentor;
            this.phuCap = phuCap;
            this.moiTruong = moiTruong;
            this.nhanXet = nhanXet;
            this.ngayGui = ngayGui;
        }

        public String getDoanhNghiep() {
            return doanhNghiep;
        }

        public String getCoSoVatChat() {
            return coSoVatChat;
        }

        public String getMentor() {
            return mentor;
        }

        public String getPhuCap() {
            return phuCap;
        }

        public String getMoiTruong() {
            return moiTruong;
        }

        public String getNhanXet() {
            return nhanXet;
        }

        public String getNgayGui() {
            return ngayGui;
        }

        public double getDiemTrungBinh() {
            return (
                    Integer.parseInt(coSoVatChat)
                    + Integer.parseInt(mentor)
                    + Integer.parseInt(phuCap)
                    + Integer.parseInt(moiTruong)
            ) / 4.0;
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
