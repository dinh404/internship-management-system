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

import model.TinTuyenDung;
import service.TinTuyenDungService;
import util.MessageUtil;

public class TinTuyenDungFrame extends JFrame {

    private JTextField txtMaTinTuyenDung;
    private JTextField txtTenViTri;
    private JTextField txtTenDoanhNghiep;
    private JTextField txtDiaDiem;
    private JTextField txtSoLuong;
    private JTextField txtHanNop;
    private JTextField txtTuKhoa;

    private JComboBox<String> cboTrangThai;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private JTable tblTinTuyenDung;
    private DefaultTableModel tableModel;

    private JLabel lblTongTin;
    private JLabel lblDangMo;

    private final TinTuyenDungService tinTuyenDungService = new TinTuyenDungService();

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
    private final Color COLOR_TABLE_HEADER = Color.decode("#1E3A8A");

    public TinTuyenDungFrame() {
        initUI();
        initEvents();
        loadDataToTable();
    }

    private void initUI() {
        setTitle("Quản lý tin tuyển dụng");
        setSize(1150, 720);
        setMinimumSize(new Dimension(1150, 720));
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

        JLabel lblTitle = new JLabel("QUẢN LÝ TIN TUYỂN DỤNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Quản lý danh sách các vị trí thực tập và nhu cầu tuyển dụng từ đối tác doanh nghiệp");
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
        formCard.setPreferredSize(new Dimension(340, 590));
        formCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblFormTitle = new JLabel("Thông tin tuyển dụng");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFormTitle.setForeground(COLOR_TEXT);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        txtMaTinTuyenDung = createTextField();
        txtTenViTri = createTextField();
        txtTenDoanhNghiep = createTextField();
        txtDiaDiem = createTextField();
        txtSoLuong = createTextField();
        txtHanNop = createTextField();

        cboTrangThai = new JComboBox<>(new String[]{
            "Đang mở",
            "Đã đóng"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBackground(Color.WHITE);
        cboTrangThai.setForeground(COLOR_TEXT);
        cboTrangThai.setPreferredSize(new Dimension(292, 36));

        addFormField(fieldsPanel, "Mã tin tuyển dụng", txtMaTinTuyenDung, 0);
        addFormField(fieldsPanel, "Tên vị trí", txtTenViTri, 1);
        addFormField(fieldsPanel, "Tên doanh nghiệp", txtTenDoanhNghiep, 2);
        addFormField(fieldsPanel, "Địa điểm", txtDiaDiem, 3);
        addFormField(fieldsPanel, "Số lượng", txtSoLuong, 4);
        addFormField(fieldsPanel, "Hạn nộp", txtHanNop, 5);
        addFormField(fieldsPanel, "Trạng thái", cboTrangThai, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        buttonPanel.setOpaque(false);

        btnThem = createActionButton("Thêm", COLOR_TEAL, Color.WHITE);
        btnSua = createActionButton("Sửa", COLOR_NAVY, Color.WHITE);
        btnXoa = createActionButton("Xóa", COLOR_GRAY_BUTTON, COLOR_DANGER);
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

        lblTongTin = new JLabel("0");
        lblDangMo = new JLabel("0");

        badgePanel.add(createBadge("Tổng số tin", lblTongTin, new Color(239, 246, 255), COLOR_NAVY));
        badgePanel.add(createBadge("Tin đang mở", lblDangMo, new Color(236, 253, 245), COLOR_TEAL));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(430, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập mã, vị trí, doanh nghiệp, địa điểm...");
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
            "Mã tin",
            "Vị trí",
            "Doanh nghiệp",
            "Địa điểm",
            "Số lượng",
            "Hạn nộp",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblTinTuyenDung = new JTable(tableModel);
        tblTinTuyenDung.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblTinTuyenDung.setRowHeight(36);
        tblTinTuyenDung.setForeground(COLOR_TEXT);
        tblTinTuyenDung.setGridColor(new Color(203, 213, 225));
        tblTinTuyenDung.setShowVerticalLines(true);
        tblTinTuyenDung.setShowHorizontalLines(true);
        tblTinTuyenDung.setIntercellSpacing(new Dimension(1, 1));
        tblTinTuyenDung.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblTinTuyenDung.setSelectionBackground(COLOR_ROW_SELECTED);
        tblTinTuyenDung.setSelectionForeground(COLOR_TEXT);

        tblTinTuyenDung.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblTinTuyenDung.getTableHeader().setForeground(Color.WHITE);
        tblTinTuyenDung.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblTinTuyenDung.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblTinTuyenDung.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblTinTuyenDung.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblTinTuyenDung.getColumnModel().getColumn(1).setPreferredWidth(170);
        tblTinTuyenDung.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblTinTuyenDung.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblTinTuyenDung.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblTinTuyenDung.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblTinTuyenDung.getColumnModel().getColumn(6).setPreferredWidth(110);

        for (int i = 0; i < tblTinTuyenDung.getColumnCount(); i++) {
            tblTinTuyenDung.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblTinTuyenDung);
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
        textField.setPreferredSize(new Dimension(292, 36));
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

        tblTinTuyenDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(tinTuyenDungService.getAll());
    }

    private void loadDataToTable(List<TinTuyenDung> danhSachTin) {
        tableModel.setRowCount(0);

        for (TinTuyenDung tin : danhSachTin) {
            tableModel.addRow(new Object[]{
                tin.getMaTinTuyenDung(),
                tin.getTenViTri(),
                tin.getTenDoanhNghiep(),
                tin.getDiaDiem(),
                tin.getSoLuong(),
                tin.getHanNop(),
                tin.getTrangThai()
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        List<TinTuyenDung> danhSach = tinTuyenDungService.getAll();

        int tongTin = danhSach.size();
        int dangMo = 0;

        for (TinTuyenDung tin : danhSach) {
            if ("Đang mở".equalsIgnoreCase(tin.getTrangThai())) {
                dangMo++;
            }
        }

        lblTongTin.setText(String.valueOf(tongTin));
        lblDangMo.setText(String.valueOf(dangMo));
    }

    private void handleThem() {
        if (!validateForm()) {
            return;
        }

        TinTuyenDung tin = getDataFromForm();
        boolean success = tinTuyenDungService.add(tin);

        if (success) {
            MessageUtil.showInfo(this, "Thêm tin tuyển dụng thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Mã tin tuyển dụng đã tồn tại!");
            txtMaTinTuyenDung.requestFocus();
        }
    }

    private void handleSua() {
        if (txtMaTinTuyenDung.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần sửa!");
            return;
        }

        if (!validateForm()) {
            return;
        }

        TinTuyenDung tin = getDataFromForm();
        boolean success = tinTuyenDungService.update(tin);

        if (success) {
            MessageUtil.showInfo(this, "Cập nhật tin tuyển dụng thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không tìm thấy tin tuyển dụng cần cập nhật!");
        }
    }

    private void handleXoa() {
        int selectedRow = tblTinTuyenDung.getSelectedRow();

        if (selectedRow < 0) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần xóa!");
            return;
        }

        String maTin = tableModel.getValueAt(selectedRow, 0).toString();
        boolean confirm = MessageUtil.showConfirm(this, "Bạn có chắc chắn muốn xóa tin tuyển dụng " + maTin + "?");

        if (!confirm) {
            return;
        }

        boolean success = tinTuyenDungService.delete(maTin);

        if (success) {
            MessageUtil.showInfo(this, "Xóa tin tuyển dụng thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không thể xóa tin tuyển dụng!");
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim();
        List<TinTuyenDung> result = tinTuyenDungService.search(keyword);
        loadDataToTable(result);
    }

    private void handleLamMoi() {
        txtTuKhoa.setText("");
        clearForm();
        loadDataToTable();
    }

    private TinTuyenDung getDataFromForm() {
        String maTinTuyenDung = txtMaTinTuyenDung.getText().trim();
        String tenViTri = txtTenViTri.getText().trim();
        String tenDoanhNghiep = txtTenDoanhNghiep.getText().trim();
        String diaDiem = txtDiaDiem.getText().trim();
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        String hanNop = txtHanNop.getText().trim();
        String trangThai = cboTrangThai.getSelectedItem().toString();

        return new TinTuyenDung(maTinTuyenDung, tenViTri, tenDoanhNghiep, diaDiem, soLuong, hanNop, trangThai);
    }

    private void fillFormFromSelectedRow() {
        int selectedRow = tblTinTuyenDung.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        txtMaTinTuyenDung.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtTenViTri.setText(tableModel.getValueAt(selectedRow, 1).toString());
        txtTenDoanhNghiep.setText(tableModel.getValueAt(selectedRow, 2).toString());
        txtDiaDiem.setText(tableModel.getValueAt(selectedRow, 3).toString());
        txtSoLuong.setText(tableModel.getValueAt(selectedRow, 4).toString());
        txtHanNop.setText(tableModel.getValueAt(selectedRow, 5).toString());
        cboTrangThai.setSelectedItem(tableModel.getValueAt(selectedRow, 6).toString());

        txtMaTinTuyenDung.setEditable(false);
    }

    private void clearForm() {
        txtMaTinTuyenDung.setText("");
        txtTenViTri.setText("");
        txtTenDoanhNghiep.setText("");
        txtDiaDiem.setText("");
        txtSoLuong.setText("");
        txtHanNop.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblTinTuyenDung.clearSelection();
        txtMaTinTuyenDung.setEditable(true);
        txtMaTinTuyenDung.requestFocus();
    }

    private boolean validateForm() {
        String maTinTuyenDung = txtMaTinTuyenDung.getText().trim();
        String tenViTri = txtTenViTri.getText().trim();
        String tenDoanhNghiep = txtTenDoanhNghiep.getText().trim();
        String diaDiem = txtDiaDiem.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();
        String hanNop = txtHanNop.getText().trim();

        if (maTinTuyenDung.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập mã tin tuyển dụng!");
            txtMaTinTuyenDung.requestFocus();
            return false;
        }

        if (tenViTri.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập tên vị trí!");
            txtTenViTri.requestFocus();
            return false;
        }

        if (tenDoanhNghiep.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập tên doanh nghiệp!");
            txtTenDoanhNghiep.requestFocus();
            return false;
        }

        if (diaDiem.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập địa điểm!");
            txtDiaDiem.requestFocus();
            return false;
        }

        if (soLuongText.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập số lượng!");
            txtSoLuong.requestFocus();
            return false;
        }

        try {
            int soLuong = Integer.parseInt(soLuongText);

            if (soLuong <= 0) {
                MessageUtil.showError(this, "Số lượng phải lớn hơn 0!");
                txtSoLuong.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            MessageUtil.showError(this, "Số lượng phải là số nguyên!");
            txtSoLuong.requestFocus();
            return false;
        }

        if (hanNop.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập hạn nộp!");
            txtHanNop.requestFocus();
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TinTuyenDungFrame().setVisible(true));
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