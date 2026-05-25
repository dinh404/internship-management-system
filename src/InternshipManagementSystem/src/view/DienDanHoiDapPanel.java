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

public class DienDanHoiDapPanel extends JPanel {

    private JComboBox<String> cboChuDe;
    private JTextField txtTieuDe;
    private JTextArea txtNoiDung;
    private JTextField txtTuKhoa;

    private JLabel lblTongBaiViet;
    private JLabel lblKinhNghiem;
    private JLabel lblPhongVan;
    private JLabel lblDaGiaiDap;

    private JTable tblBaiViet;
    private DefaultTableModel tableModel;

    private JButton btnDangBai;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private final TaiKhoan currentUser;
    private final List<BaiViet> danhSachBaiViet = new ArrayList<>();

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

    public DienDanHoiDapPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
        updateBadges();
    }

    private void initData() {
        danhSachBaiViet.add(new BaiViet(
                "Review công ty",
                "Kinh nghiệm phỏng vấn Java Intern tại Công ty ABC",
                "Cần nắm OOP, SQL cơ bản và giải thích được project cá nhân. Mentor khá thân thiện.",
                "Nguyễn Văn A",
                "12",
                "Đã giải đáp"
        ));

        danhSachBaiViet.add(new BaiViet(
                "Hỏi đáp CV",
                "CV thực tập nên viết project như thế nào?",
                "Nên mô tả rõ chức năng, công nghệ sử dụng và vai trò cá nhân trong project.",
                "Trần Thị B",
                "8",
                "Đã giải đáp"
        ));

        danhSachBaiViet.add(new BaiViet(
                "Kinh nghiệm thực tập",
                "Đi thực tập nên chuẩn bị gì trong tuần đầu?",
                "Nên chuẩn bị laptop, tài khoản email, tinh thần ghi chú và chủ động hỏi mentor.",
                "Lê Văn C",
                "15",
                "Đang thảo luận"
        ));

        danhSachBaiViet.add(new BaiViet(
                "Phỏng vấn",
                "Frontend Intern thường hỏi những gì?",
                "HTML, CSS, JavaScript cơ bản, React props/state và cách xử lý form.",
                "Phạm Thị D",
                "6",
                "Đang thảo luận"
        ));
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createPostFormCard(), BorderLayout.WEST);
        add(createForumCard(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("DIỄN ĐÀN / HỎI ĐÁP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Sinh viên chia sẻ kinh nghiệm phỏng vấn, review doanh nghiệp và giải đáp thắc mắc cho khóa sau");
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

    private RoundedPanel createPostFormCard() {
        RoundedPanel formCard = new RoundedPanel(24, COLOR_CARD);
        formCard.setLayout(new BorderLayout(0, 14));
        formCard.setPreferredSize(new Dimension(390, 600));
        formCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Tạo bài viết / câu hỏi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblNote = new JLabel("<html><span style='color:#64748B;'>Nội dung chia sẻ giúp các khóa sau chuẩn bị tốt hơn</span></html>");
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

        cboChuDe = createComboBox(new String[]{
            "Review công ty",
            "Phỏng vấn",
            "Hỏi đáp CV",
            "Kinh nghiệm thực tập",
            "Khác"
        });

        txtTieuDe = createTextField("Nhập tiêu đề bài viết...");
        txtNoiDung = new JTextArea();
        txtNoiDung.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNoiDung.setForeground(COLOR_TEXT);
        txtNoiDung.setLineWrap(true);
        txtNoiDung.setWrapStyleWord(true);
        txtNoiDung.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(9, 10, 9, 10)
        ));

        addFormRow(formPanel, "Chủ đề", cboChuDe, 0);
        addFormRow(formPanel, "Tiêu đề", txtTieuDe, 1);
        addFormRow(formPanel, "Nội dung", new JScrollPane(txtNoiDung), 2);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(330, 42));

        btnDangBai = createActionButton("Đăng bài", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnDangBai);
        buttonPanel.add(btnLamMoi);

        formCard.add(titlePanel, BorderLayout.NORTH);
        formCard.add(formPanel, BorderLayout.CENTER);
        formCard.add(buttonPanel, BorderLayout.SOUTH);

        return formCard;
    }

    private RoundedPanel createForumCard() {
        RoundedPanel forumCard = new RoundedPanel(24, COLOR_CARD);
        forumCard.setLayout(new BorderLayout(0, 18));
        forumCard.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel topPanel = new JPanel(new BorderLayout(0, 18));
        topPanel.setOpaque(false);

        JPanel badgePanel = new JPanel(new GridLayout(1, 4, 12, 0));
        badgePanel.setOpaque(false);
        badgePanel.setPreferredSize(new Dimension(720, 70));

        lblTongBaiViet = new JLabel("0");
        lblKinhNghiem = new JLabel("0");
        lblPhongVan = new JLabel("0");
        lblDaGiaiDap = new JLabel("0");

        badgePanel.add(createBadge("Tổng bài viết", lblTongBaiViet, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Kinh nghiệm", lblKinhNghiem, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Phỏng vấn", lblPhongVan, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Đã giải đáp", lblDaGiaiDap, Color.decode("#E0F2FE"), Color.decode("#0369A1")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(720, 42));

        txtTuKhoa = createTextField("Tìm chủ đề, tiêu đề, tác giả, nội dung...");
        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        String[] columns = {
            "Chủ đề",
            "Tiêu đề",
            "Tác giả",
            "Phản hồi",
            "Trạng thái",
            "Tóm tắt nội dung"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblBaiViet = new JTable(tableModel);
        tblBaiViet.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblBaiViet.setRowHeight(38);
        tblBaiViet.setForeground(COLOR_TEXT);
        tblBaiViet.setGridColor(Color.decode("#CBD5E1"));
        tblBaiViet.setShowVerticalLines(true);
        tblBaiViet.setShowHorizontalLines(true);
        tblBaiViet.setIntercellSpacing(new Dimension(1, 1));
        tblBaiViet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBaiViet.setSelectionBackground(COLOR_ROW_SELECTED);
        tblBaiViet.setSelectionForeground(COLOR_TEXT);

        tblBaiViet.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblBaiViet.getTableHeader().setForeground(Color.WHITE);
        tblBaiViet.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblBaiViet.getTableHeader().setPreferredSize(new Dimension(0, 42));
        tblBaiViet.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblBaiViet.getColumnCount(); i++) {
            tblBaiViet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblBaiViet.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblBaiViet.getColumnModel().getColumn(1).setPreferredWidth(230);
        tblBaiViet.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblBaiViet.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblBaiViet.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblBaiViet.getColumnModel().getColumn(5).setPreferredWidth(300);

        JScrollPane scrollPane = new JScrollPane(tblBaiViet);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        forumCard.add(topPanel, BorderLayout.NORTH);
        forumCard.add(scrollPane, BorderLayout.CENTER);

        return forumCard;
    }

    private void initEvents() {
        btnDangBai.addActionListener(e -> handleDangBai());

        btnLamMoi.addActionListener(e -> clearForm());

        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());
    }

    private void handleDangBai() {
        String chuDe = cboChuDe.getSelectedItem().toString();
        String tieuDe = txtTieuDe.getText().trim();
        String noiDung = txtNoiDung.getText().trim();

        if (tieuDe.isEmpty() || noiDung.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập đầy đủ tiêu đề và nội dung bài viết!");
            return;
        }

        danhSachBaiViet.add(new BaiViet(
                chuDe,
                tieuDe,
                noiDung,
                currentUser.getTenHienThi(),
                "0",
                "Đang thảo luận"
        ));

        loadDataToTable();
        updateBadges();
        clearForm();

        MessageUtil.showInfo(this, "Đã đăng bài viết lên diễn đàn hỏi đáp.");
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        tableModel.setRowCount(0);

        for (BaiViet item : danhSachBaiViet) {
            String searchText = (
                    item.getChuDe() + " "
                    + item.getTieuDe() + " "
                    + item.getNoiDung() + " "
                    + item.getTacGia() + " "
                    + item.getTrangThai()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                addRow(item);
            }
        }
    }

    private void clearForm() {
        cboChuDe.setSelectedIndex(0);
        txtTieuDe.setText("");
        txtNoiDung.setText("");
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        for (BaiViet item : danhSachBaiViet) {
            addRow(item);
        }
    }

    private void addRow(BaiViet item) {
        tableModel.addRow(new Object[]{
            item.getChuDe(),
            item.getTieuDe(),
            item.getTacGia(),
            item.getSoPhanHoi(),
            item.getTrangThai(),
            item.getNoiDung()
        });
    }

    private void updateBadges() {
        int tong = danhSachBaiViet.size();
        int kinhNghiem = 0;
        int phongVan = 0;
        int daGiaiDap = 0;

        for (BaiViet item : danhSachBaiViet) {
            if ("Kinh nghiệm thực tập".equalsIgnoreCase(item.getChuDe())
                    || "Review công ty".equalsIgnoreCase(item.getChuDe())) {
                kinhNghiem++;
            }

            if ("Phỏng vấn".equalsIgnoreCase(item.getChuDe())) {
                phongVan++;
            }

            if ("Đã giải đáp".equalsIgnoreCase(item.getTrangThai())) {
                daGiaiDap++;
            }
        }

        lblTongBaiViet.setText(String.valueOf(tong));
        lblKinhNghiem.setText(String.valueOf(kinhNghiem));
        lblPhongVan.setText(String.valueOf(phongVan));
        lblDaGiaiDap.setText(String.valueOf(daGiaiDap));
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

    private static class BaiViet {

        private String chuDe;
        private String tieuDe;
        private String noiDung;
        private String tacGia;
        private String soPhanHoi;
        private String trangThai;

        public BaiViet(String chuDe, String tieuDe, String noiDung,
                String tacGia, String soPhanHoi, String trangThai) {
            this.chuDe = chuDe;
            this.tieuDe = tieuDe;
            this.noiDung = noiDung;
            this.tacGia = tacGia;
            this.soPhanHoi = soPhanHoi;
            this.trangThai = trangThai;
        }

        public String getChuDe() {
            return chuDe;
        }

        public String getTieuDe() {
            return tieuDe;
        }

        public String getNoiDung() {
            return noiDung;
        }

        public String getTacGia() {
            return tacGia;
        }

        public String getSoPhanHoi() {
            return soPhanHoi;
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
