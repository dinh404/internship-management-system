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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.SinhVien;
import service.SinhVienService;
import util.MessageUtil;
import util.ValidationUtil;

public class SinhVienFrame extends JFrame {

    private JTextField txtMaSinhVien;
    private JTextField txtHoTen;
    private JTextField txtEmail;
    private JTextField txtSoDienThoai;
    private JTextField txtNganhHoc;
    private JTextField txtGpa;
    private JTextField txtTuKhoa;

    private JComboBox<String> cboTrangThai;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private JTable tblSinhVien;
    private DefaultTableModel tableModel;

    private JLabel lblTongSinhVien;
    private JLabel lblDaCoNoiThucTap;

    private final SinhVienService sinhVienService = new SinhVienService();

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_GRAY_BUTTON = Color.decode("#E2E8F0");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_INPUT_BORDER = Color.decode("#CBD5E1");
    private final Color COLOR_ROW_SELECTED = Color.decode("#DBEAFE");
    private final Color COLOR_TABLE_HEADER = Color.decode("#EFF6FF");

    public SinhVienFrame() {
        initUI();
        initEvents();
        loadDataToTable();
    }

    private void initUI() {
        setTitle("Quản lý sinh viên");
        setSize(1100, 680);
        setMinimumSize(new Dimension(1100, 680));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout(24, 20));
        rootPanel.setBackground(COLOR_BACKGROUND);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        rootPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        rootPanel.add(createFormCard(), BorderLayout.WEST);
        rootPanel.add(createTableCard(), BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("QUẢN LÝ SINH VIÊN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Hệ thống quản lý thông tin hồ sơ và tiến độ thực tập của sinh viên");
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
        formCard.setLayout(new BorderLayout());
        formCard.setPreferredSize(new Dimension(360, 560));
        formCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblFormTitle = new JLabel("Thông tin sinh viên");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFormTitle.setForeground(COLOR_TEXT);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        txtMaSinhVien = createTextField();
        txtHoTen = createTextField();
        txtEmail = createTextField();
        txtSoDienThoai = createTextField();
        txtNganhHoc = createTextField();
        txtGpa = createTextField();

        cboTrangThai = new JComboBox<>(new String[]{
            "Chưa thực tập",
            "Đang ứng tuyển",
            "Đang thực tập",
            "Hoàn thành thực tập"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBackground(Color.WHITE);
        cboTrangThai.setForeground(COLOR_TEXT);
        cboTrangThai.setPreferredSize(new Dimension(300, 38));

        addFormField(fieldsPanel, "Mã sinh viên", txtMaSinhVien, 0);
        addFormField(fieldsPanel, "Họ tên", txtHoTen, 1);
        addFormField(fieldsPanel, "Email", txtEmail, 2);
        addFormField(fieldsPanel, "Số điện thoại", txtSoDienThoai, 3);
        addFormField(fieldsPanel, "Ngành học", txtNganhHoc, 4);
        addFormField(fieldsPanel, "GPA", txtGpa, 5);
        addFormField(fieldsPanel, "Trạng thái", cboTrangThai, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        buttonPanel.setOpaque(false);

        btnThem = createActionButton("Thêm", COLOR_TEAL, Color.WHITE);
        btnSua = createActionButton("Sửa", COLOR_NAVY, Color.WHITE);
        btnXoa = createActionButton("Xóa", COLOR_DANGER, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);

        formCard.add(lblFormTitle, BorderLayout.NORTH);
        formCard.add(fieldsPanel, BorderLayout.CENTER);
        formCard.add(buttonPanel, BorderLayout.SOUTH);

        return formCard;
    }

    private RoundedPanel createTableCard() {
        RoundedPanel tableCard = new RoundedPanel(24, COLOR_CARD);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel topPanel = new JPanel(new BorderLayout(16, 0));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        JPanel badgePanel = new JPanel(new GridLayout(1, 2, 12, 0));
        badgePanel.setOpaque(false);

        lblTongSinhVien = new JLabel("0");
        lblDaCoNoiThucTap = new JLabel("0");

        badgePanel.add(createBadge("Tổng số SV", lblTongSinhVien, new Color(219, 234, 254), COLOR_NAVY));
        badgePanel.add(createBadge("Đã có nơi thực tập", lblDaCoNoiThucTap, new Color(220, 252, 231), COLOR_TEAL));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(430, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập mã, tên, email, ngành học...");
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

        topPanel.add(badgePanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        String[] columns = {
            "Mã SV",
            "Họ tên",
            "Email",
            "Số điện thoại",
            "Ngành học",
            "GPA",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblSinhVien = new JTable(tableModel);
        tblSinhVien.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblSinhVien.setRowHeight(36);
        tblSinhVien.setForeground(COLOR_TEXT);
        tblSinhVien.setGridColor(new Color(203, 213, 225));
        tblSinhVien.setShowVerticalLines(true);
        tblSinhVien.setShowHorizontalLines(true);
        tblSinhVien.setIntercellSpacing(new Dimension(1, 1));
        tblSinhVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSinhVien.setSelectionBackground(COLOR_ROW_SELECTED);
        tblSinhVien.setSelectionForeground(COLOR_TEXT);

        tblSinhVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblSinhVien.getTableHeader().setForeground(COLOR_NAVY);
        tblSinhVien.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblSinhVien.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblSinhVien.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblSinhVien.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblSinhVien.getColumnModel().getColumn(1).setPreferredWidth(140);
        tblSinhVien.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblSinhVien.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblSinhVien.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblSinhVien.getColumnModel().getColumn(5).setPreferredWidth(60);
        tblSinhVien.getColumnModel().getColumn(6).setPreferredWidth(140);

        for (int i = 0; i < tblSinhVien.getColumnCount(); i++) {
            tblSinhVien.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblSinhVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCard.add(topPanel, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private RoundedPanel createBadge(String title, JLabel valueLabel, Color backgroundColor, Color textColor) {
        RoundedPanel badge = new RoundedPanel(18, backgroundColor);
        badge.setLayout(new BorderLayout());
        badge.setBorder(BorderFactory.createEmptyBorder(9, 14, 9, 14));
        badge.setPreferredSize(new Dimension(178, 54));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitle.setForeground(textColor);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueLabel.setForeground(textColor);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        badge.add(lblTitle, BorderLayout.WEST);
        badge.add(valueLabel, BorderLayout.EAST);

        return badge;
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
        gbc.insets = new Insets(0, 0, 6, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 12, 0);
        panel.add(field, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setForeground(COLOR_TEXT);
        textField.setCaretColor(COLOR_TEAL);
        textField.setPreferredSize(new Dimension(300, 38));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return textField;
    }

    private JButton createActionButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new HoverButton(text, backgroundColor, foregroundColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(foregroundColor);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void initEvents() {
        btnThem.addActionListener(e -> handleThem());
        btnSua.addActionListener(e -> handleSua());
        btnXoa.addActionListener(e -> handleXoa());
        btnLamMoi.addActionListener(e -> handleLamMoi());
        btnTimKiem.addActionListener(e -> handleTimKiem());

        txtTuKhoa.addActionListener(e -> handleTimKiem());

        tblSinhVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(sinhVienService.getAll());
    }

    private void loadDataToTable(List<SinhVien> danhSachSinhVien) {
        tableModel.setRowCount(0);

        for (SinhVien sinhVien : danhSachSinhVien) {
            tableModel.addRow(new Object[]{
                sinhVien.getMaSinhVien(),
                sinhVien.getHoTen(),
                sinhVien.getEmail(),
                sinhVien.getSoDienThoai(),
                sinhVien.getNganhHoc(),
                sinhVien.getGpa(),
                sinhVien.getTrangThai()
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        List<SinhVien> danhSach = sinhVienService.getAll();

        int tongSinhVien = danhSach.size();
        int daCoNoiThucTap = 0;

        for (SinhVien sinhVien : danhSach) {
            String trangThai = sinhVien.getTrangThai();

            if ("Đang thực tập".equalsIgnoreCase(trangThai)
                    || "Hoàn thành thực tập".equalsIgnoreCase(trangThai)) {
                daCoNoiThucTap++;
            }
        }

        lblTongSinhVien.setText(String.valueOf(tongSinhVien));
        lblDaCoNoiThucTap.setText(String.valueOf(daCoNoiThucTap));
    }

    private void handleThem() {
        if (!validateForm()) {
            return;
        }

        SinhVien sinhVien = getDataFromForm();
        boolean success = sinhVienService.add(sinhVien);

        if (success) {
            MessageUtil.showInfo(this, "Thêm sinh viên thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Mã sinh viên đã tồn tại!");
            txtMaSinhVien.requestFocus();
        }
    }

    private void handleSua() {
        if (txtMaSinhVien.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn sinh viên cần sửa!");
            return;
        }

        if (!validateForm()) {
            return;
        }

        SinhVien sinhVien = getDataFromForm();
        boolean success = sinhVienService.update(sinhVien);

        if (success) {
            MessageUtil.showInfo(this, "Cập nhật sinh viên thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không tìm thấy sinh viên cần cập nhật!");
        }
    }

    private void handleXoa() {
        int selectedRow = tblSinhVien.getSelectedRow();

        if (selectedRow < 0) {
            MessageUtil.showError(this, "Vui lòng chọn sinh viên cần xóa!");
            return;
        }

        String maSinhVien = tableModel.getValueAt(selectedRow, 0).toString();
        boolean confirm = MessageUtil.showConfirm(this, "Bạn có chắc chắn muốn xóa sinh viên " + maSinhVien + "?");

        if (!confirm) {
            return;
        }

        boolean success = sinhVienService.delete(maSinhVien);

        if (success) {
            MessageUtil.showInfo(this, "Xóa sinh viên thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không thể xóa sinh viên!");
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim();
        List<SinhVien> result = sinhVienService.search(keyword);
        loadDataToTable(result);
    }

    private void handleLamMoi() {
        txtTuKhoa.setText("");
        clearForm();
        loadDataToTable();
    }

    private SinhVien getDataFromForm() {
        String maSinhVien = txtMaSinhVien.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String nganhHoc = txtNganhHoc.getText().trim();
        double gpa = Double.parseDouble(txtGpa.getText().trim());
        String trangThai = cboTrangThai.getSelectedItem().toString();

        return new SinhVien(maSinhVien, hoTen, email, soDienThoai, nganhHoc, gpa, trangThai);
    }

    private void fillFormFromSelectedRow() {
        int selectedRow = tblSinhVien.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        txtMaSinhVien.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtHoTen.setText(tableModel.getValueAt(selectedRow, 1).toString());
        txtEmail.setText(tableModel.getValueAt(selectedRow, 2).toString());
        txtSoDienThoai.setText(tableModel.getValueAt(selectedRow, 3).toString());
        txtNganhHoc.setText(tableModel.getValueAt(selectedRow, 4).toString());
        txtGpa.setText(tableModel.getValueAt(selectedRow, 5).toString());
        cboTrangThai.setSelectedItem(tableModel.getValueAt(selectedRow, 6).toString());

        txtMaSinhVien.setEditable(false);
    }

    private void clearForm() {
        txtMaSinhVien.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtNganhHoc.setText("");
        txtGpa.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblSinhVien.clearSelection();
        txtMaSinhVien.setEditable(true);
        txtMaSinhVien.requestFocus();
    }

    private boolean validateForm() {
        String maSinhVien = txtMaSinhVien.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String nganhHoc = txtNganhHoc.getText().trim();
        String gpaText = txtGpa.getText().trim();

        if (ValidationUtil.isEmpty(maSinhVien)) {
            MessageUtil.showError(this, "Vui lòng nhập mã sinh viên!");
            txtMaSinhVien.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(hoTen)) {
            MessageUtil.showError(this, "Vui lòng nhập họ tên!");
            txtHoTen.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(email)) {
            MessageUtil.showError(this, "Vui lòng nhập email!");
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

        if (ValidationUtil.isEmpty(nganhHoc)) {
            MessageUtil.showError(this, "Vui lòng nhập ngành học!");
            txtNganhHoc.requestFocus();
            return false;
        }

        if (ValidationUtil.isEmpty(gpaText)) {
            MessageUtil.showError(this, "Vui lòng nhập GPA!");
            txtGpa.requestFocus();
            return false;
        }

        try {
            double gpa = Double.parseDouble(gpaText);

            if (gpa < 0 || gpa > 4) {
                MessageUtil.showError(this, "GPA phải nằm trong khoảng từ 0 đến 4!");
                txtGpa.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            MessageUtil.showError(this, "GPA phải là số!");
            txtGpa.requestFocus();
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SinhVienFrame().setVisible(true));
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