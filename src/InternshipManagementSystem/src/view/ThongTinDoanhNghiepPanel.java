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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.TaiKhoan;
import util.MessageUtil;
import util.ValidationUtil;

public class ThongTinDoanhNghiepPanel extends JPanel {

    private JTextField txtMaDoanhNghiep;
    private JTextField txtTenDoanhNghiep;
    private JTextField txtLinhVuc;
    private JTextField txtDiaChi;
    private JTextField txtEmail;
    private JTextField txtSoDienThoai;
    private JTextField txtNguoiPhuTrach;
    private JTextField txtWebsite;
    private JTextField txtTrangThaiXacThuc;
    private JTextArea txtMoTa;

    private JLabel lblHoSo;
    private JLabel lblTrangThai;
    private JLabel lblTinDangMo;

    private JButton btnCapNhat;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;

    private String originalMaDoanhNghiep;
    private String originalTenDoanhNghiep;
    private String originalLinhVuc;
    private String originalDiaChi;
    private String originalEmail;
    private String originalSoDienThoai;
    private String originalNguoiPhuTrach;
    private String originalWebsite;
    private String originalTrangThaiXacThuc;
    private String originalMoTa;

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_GRAY_BUTTON = Color.decode("#F1F5F9");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_INPUT_BORDER = Color.decode("#CBD5E1");
    private final Color COLOR_READONLY_BG = Color.decode("#F1F5F9");

    public ThongTinDoanhNghiepPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initMockData();
        initUI();
        initEvents();
        loadDataToForm();
    }

    private void initMockData() {
        originalMaDoanhNghiep = currentUser != null ? currentUser.getMaNguoiDung() : "DN001";

        if (currentUser != null && currentUser.isHR()) {
            originalTenDoanhNghiep = "Công ty ABC";
            originalNguoiPhuTrach = currentUser.getTenHienThi();
        } else {
            originalTenDoanhNghiep = currentUser != null ? currentUser.getTenHienThi() : "Công ty ABC";
            originalNguoiPhuTrach = "Nguyễn Minh HR";
        }

        originalLinhVuc = "Công nghệ thông tin";
        originalDiaChi = "Quận 1, TP. Hồ Chí Minh";
        originalEmail = "hr@abc.com";
        originalSoDienThoai = "0901234567";
        originalWebsite = "https://abc.com";
        originalTrangThaiXacThuc = "Đã xác thực";
        originalMoTa = "Công ty ABC là doanh nghiệp hoạt động trong lĩnh vực phát triển phần mềm, "
                + "cung cấp giải pháp công nghệ và thường xuyên tiếp nhận sinh viên thực tập ở các vị trí lập trình, kiểm thử và phân tích nghiệp vụ.";
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("THÔNG TIN DOANH NGHIỆP");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Doanh nghiệp/HR cập nhật hồ sơ đơn vị, thông tin liên hệ và mô tả phục vụ kết nối thực tập");
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

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout(24, 0));
        contentPanel.setOpaque(false);

        contentPanel.add(createProfileCard(), BorderLayout.WEST);
        contentPanel.add(createOverviewCard(), BorderLayout.CENTER);

        return contentPanel;
    }

    private RoundedPanel createProfileCard() {
        RoundedPanel profileCard = new RoundedPanel(24, COLOR_CARD);
        profileCard.setLayout(new BorderLayout());
        profileCard.setPreferredSize(new Dimension(430, 590));
        profileCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblFormTitle = new JLabel("Hồ sơ doanh nghiệp");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFormTitle.setForeground(COLOR_TEXT);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        txtMaDoanhNghiep = createTextField();
        txtTenDoanhNghiep = createTextField();
        txtLinhVuc = createTextField();
        txtDiaChi = createTextField();
        txtEmail = createTextField();
        txtSoDienThoai = createTextField();
        txtNguoiPhuTrach = createTextField();
        txtWebsite = createTextField();
        txtTrangThaiXacThuc = createTextField();

        txtMaDoanhNghiep.setEditable(false);
        txtMaDoanhNghiep.setBackground(COLOR_READONLY_BG);

        txtTrangThaiXacThuc.setEditable(false);
        txtTrangThaiXacThuc.setBackground(COLOR_READONLY_BG);

        addFormField(fieldsPanel, "Mã doanh nghiệp", txtMaDoanhNghiep, 0);
        addFormField(fieldsPanel, "Tên doanh nghiệp", txtTenDoanhNghiep, 1);
        addFormField(fieldsPanel, "Lĩnh vực hoạt động", txtLinhVuc, 2);
        addFormField(fieldsPanel, "Địa chỉ", txtDiaChi, 3);
        addFormField(fieldsPanel, "Email liên hệ", txtEmail, 4);
        addFormField(fieldsPanel, "Số điện thoại", txtSoDienThoai, 5);
        addFormField(fieldsPanel, "Người phụ trách / HR", txtNguoiPhuTrach, 6);
        addFormField(fieldsPanel, "Website", txtWebsite, 7);
        addFormField(fieldsPanel, "Trạng thái xác thực", txtTrangThaiXacThuc, 8);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);

        btnCapNhat = createActionButton("Cập nhật", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnLamMoi);

        profileCard.add(lblFormTitle, BorderLayout.NORTH);
        profileCard.add(fieldsPanel, BorderLayout.CENTER);
        profileCard.add(buttonPanel, BorderLayout.SOUTH);

        return profileCard;
    }

    private RoundedPanel createOverviewCard() {
        RoundedPanel overviewCard = new RoundedPanel(24, COLOR_CARD);
        overviewCard.setLayout(new BorderLayout());
        overviewCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblTitle = new JLabel("Tổng quan hồ sơ doanh nghiệp");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel badgePanel = new JPanel(new GridLayout(1, 3, 16, 0));
        badgePanel.setOpaque(false);
        badgePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 22, 0));
        badgePanel.setPreferredSize(new Dimension(0, 100));

        lblHoSo = new JLabel("Đầy đủ");
        lblTrangThai = new JLabel(originalTrangThaiXacThuc);
        lblTinDangMo = new JLabel("3");

        badgePanel.add(createBadge("Hồ sơ", lblHoSo, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Xác thực", lblTrangThai, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Tin đang mở", lblTinDangMo, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        RoundedPanel descriptionCard = new RoundedPanel(22, Color.decode("#F8FAFC"));
        descriptionCard.setLayout(new BorderLayout());
        descriptionCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblMoTa = new JLabel("Mô tả doanh nghiệp");
        lblMoTa.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMoTa.setForeground(COLOR_TEXT);

        txtMoTa = new JTextArea();
        txtMoTa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMoTa.setForeground(COLOR_TEXT);
        txtMoTa.setCaretColor(COLOR_TEAL);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        txtMoTa.setRows(7);
        txtMoTa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        descriptionCard.add(lblMoTa, BorderLayout.NORTH);
        descriptionCard.add(txtMoTa, BorderLayout.CENTER);

        RoundedPanel noteCard = new RoundedPanel(22, Color.decode("#F8FAFC"));
        noteCard.setLayout(new BorderLayout());
        noteCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblNoteTitle = new JLabel("Gợi ý hoàn thiện hồ sơ");
        lblNoteTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNoteTitle.setForeground(COLOR_TEXT);

        JLabel lblNoteContent = new JLabel(
                "<html>"
                + "<div style='line-height:175%; color:#475569;'>"
                + "<b style='color:#1E3A8A;'>1.</b> Cập nhật đầy đủ email, số điện thoại và người phụ trách để sinh viên dễ liên hệ.<br>"
                + "<b style='color:#1E3A8A;'>2.</b> Mô tả doanh nghiệp rõ ràng giúp tăng độ tin cậy khi sinh viên xem tin tuyển dụng.<br>"
                + "<b style='color:#1E3A8A;'>3.</b> Mã doanh nghiệp và trạng thái xác thực do hệ thống hoặc quản trị viên quản lý.<br>"
                + "<b style='color:#1E3A8A;'>4.</b> Thông tin doanh nghiệp được dùng kèm khi đăng tin tuyển dụng và xét duyệt hồ sơ ứng viên."
                + "</div>"
                + "</html>"
        );
        lblNoteContent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNoteContent.setForeground(Color.decode("#475569"));
        lblNoteContent.setVerticalAlignment(SwingConstants.TOP);

        noteCard.add(lblNoteTitle, BorderLayout.NORTH);
        noteCard.add(lblNoteContent, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 18));
        centerPanel.setOpaque(false);
        centerPanel.add(badgePanel, BorderLayout.NORTH);
        centerPanel.add(descriptionCard, BorderLayout.CENTER);
        centerPanel.add(noteCard, BorderLayout.SOUTH);

        overviewCard.add(lblTitle, BorderLayout.NORTH);
        overviewCard.add(centerPanel, BorderLayout.CENTER);

        return overviewCard;
    }

    private void initEvents() {
        btnCapNhat.addActionListener(e -> handleCapNhat());
        btnLamMoi.addActionListener(e -> loadDataToForm());
    }

    private void loadDataToForm() {
        txtMaDoanhNghiep.setText(originalMaDoanhNghiep);
        txtTenDoanhNghiep.setText(originalTenDoanhNghiep);
        txtLinhVuc.setText(originalLinhVuc);
        txtDiaChi.setText(originalDiaChi);
        txtEmail.setText(originalEmail);
        txtSoDienThoai.setText(originalSoDienThoai);
        txtNguoiPhuTrach.setText(originalNguoiPhuTrach);
        txtWebsite.setText(originalWebsite);
        txtTrangThaiXacThuc.setText(originalTrangThaiXacThuc);
        txtMoTa.setText(originalMoTa);

        updateBadges();
    }

    private void handleCapNhat() {
        if (!validateForm()) {
            return;
        }

        originalTenDoanhNghiep = txtTenDoanhNghiep.getText().trim();
        originalLinhVuc = txtLinhVuc.getText().trim();
        originalDiaChi = txtDiaChi.getText().trim();
        originalEmail = txtEmail.getText().trim();
        originalSoDienThoai = txtSoDienThoai.getText().trim();
        originalNguoiPhuTrach = txtNguoiPhuTrach.getText().trim();
        originalWebsite = txtWebsite.getText().trim();
        originalMoTa = txtMoTa.getText().trim();

        updateBadges();

        MessageUtil.showInfo(this, "Cập nhật thông tin doanh nghiệp thành công!");
    }

    private boolean validateForm() {
        String tenDoanhNghiep = txtTenDoanhNghiep.getText().trim();
        String linhVuc = txtLinhVuc.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String nguoiPhuTrach = txtNguoiPhuTrach.getText().trim();
        String website = txtWebsite.getText().trim();
        String moTa = txtMoTa.getText().trim();

        if (ValidationUtil.isEmpty(tenDoanhNghiep)) {
            MessageUtil.showError(this, "Vui lòng nhập tên doanh nghiệp!");
            txtTenDoanhNghiep.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(linhVuc)) {
            MessageUtil.showError(this, "Vui lòng nhập lĩnh vực hoạt động!");
            txtLinhVuc.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(diaChi)) {
            MessageUtil.showError(this, "Vui lòng nhập địa chỉ!");
            txtDiaChi.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(email)) {
            MessageUtil.showError(this, "Vui lòng nhập email liên hệ!");
            txtEmail.requestFocus();
            return false;
        }

        if (!ValidationUtil.isValidEmail(email)) {
            MessageUtil.showError(this, "Email không đúng định dạng!");
            txtEmail.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(soDienThoai)) {
            MessageUtil.showError(this, "Vui lòng nhập số điện thoại!");
            txtSoDienThoai.requestFocus();
            return false;
        }

        if (!ValidationUtil.isValidPhone(soDienThoai)) {
            MessageUtil.showError(this, "Số điện thoại không đúng định dạng!");
            txtSoDienThoai.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(nguoiPhuTrach)) {
            MessageUtil.showError(this, "Vui lòng nhập người phụ trách!");
            txtNguoiPhuTrach.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(website)) {
            MessageUtil.showError(this, "Vui lòng nhập website!");
            txtWebsite.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(moTa)) {
            MessageUtil.showError(this, "Vui lòng nhập mô tả doanh nghiệp!");
            txtMoTa.requestFocus();
            return false;
        }

        return true;
    }

    private void updateBadges() {
        lblHoSo.setText(isProfileComplete() ? "Đầy đủ" : "Thiếu");
        lblTrangThai.setText(originalTrangThaiXacThuc);
        lblTinDangMo.setText("3");
    }

    private boolean isProfileComplete() {
        return !originalTenDoanhNghiep.isEmpty()
                && !originalLinhVuc.isEmpty()
                && !originalDiaChi.isEmpty()
                && !originalEmail.isEmpty()
                && !originalSoDienThoai.isEmpty()
                && !originalNguoiPhuTrach.isEmpty()
                && !originalWebsite.isEmpty()
                && !originalMoTa.isEmpty();
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
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 8, 0);
        panel.add(field, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setForeground(COLOR_TEXT);
        textField.setCaretColor(COLOR_TEAL);
        textField.setPreferredSize(new Dimension(360, 34));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)
        ));
        return textField;
    }

    private RoundedPanel createBadge(String title, JLabel valueLabel, Color backgroundColor, Color textColor) {
        RoundedPanel badge = new RoundedPanel(18, backgroundColor);
        badge.setLayout(new GridBagLayout());
        badge.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        valueLabel.setForeground(textColor);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
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