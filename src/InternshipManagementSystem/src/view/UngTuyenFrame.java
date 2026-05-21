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
import java.lang.reflect.Method;
import java.util.ArrayList;
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

import model.UngTuyen;
import service.UngTuyenService;
import util.MessageUtil;

public class UngTuyenFrame extends JFrame {

    private JTextField txtMaUngTuyen;
    private JTextField txtMaSinhVien;
    private JTextField txtMaTinTuyenDung;
    private JTextField txtHoTen;
    private JTextField txtViTri;
    private JTextField txtNgayUngTuyen;
    private JTextField txtTuKhoa;

    private JComboBox<String> cboTrangThai;

    private JButton btnCapNhat;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private JTable tblUngTuyen;
    private DefaultTableModel tableModel;

    private JLabel lblTongUngTuyen;
    private JLabel lblChoDuyet;

    private final UngTuyenService ungTuyenService = new UngTuyenService();

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_ORANGE = Color.decode("#C2410C");
    private final Color COLOR_GRAY_BUTTON = Color.decode("#F1F5F9");
    private final Color COLOR_READONLY_BG = Color.decode("#F1F5F9");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_INPUT_BORDER = Color.decode("#CBD5E1");
    private final Color COLOR_ROW_SELECTED = Color.decode("#DBEAFE");
    private final Color COLOR_TABLE_HEADER = Color.decode("#1E3A8A");

    public UngTuyenFrame() {
        initUI();
        initEvents();
        loadDataToTable();
    }

    private void initUI() {
        setTitle("Quản lý ứng tuyển");
        setSize(1150, 680);
        setMinimumSize(new Dimension(1150, 680));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout(24, 20));
        rootPanel.setBackground(COLOR_BACKGROUND);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        rootPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        rootPanel.add(createDetailCard(), BorderLayout.WEST);
        rootPanel.add(createTableCard(), BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("QUẢN LÝ ỨNG TUYỂN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Hệ thống kiểm duyệt, cập nhật trạng thái và theo dõi tiến độ đơn ứng tuyển thực tập của sinh viên");
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
        detailCard.setLayout(new BorderLayout());
        detailCard.setPreferredSize(new Dimension(340, 560));
        detailCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblFormTitle = new JLabel("Chi tiết hồ sơ ứng tuyển");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFormTitle.setForeground(COLOR_TEXT);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        txtMaUngTuyen = createReadonlyTextField();
        txtMaSinhVien = createReadonlyTextField();
        txtHoTen = createReadonlyTextField();
        txtMaTinTuyenDung = createReadonlyTextField();
        txtViTri = createReadonlyTextField();
        txtNgayUngTuyen = createReadonlyTextField();

        cboTrangThai = new JComboBox<>(new String[]{
            "Chờ duyệt",
            "Đã duyệt",
            "Từ chối"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBackground(Color.WHITE);
        cboTrangThai.setForeground(COLOR_TEXT);
        cboTrangThai.setPreferredSize(new Dimension(292, 36));

        addFormField(fieldsPanel, "Mã ứng tuyển", txtMaUngTuyen, 0);
        addFormField(fieldsPanel, "Mã sinh viên", txtMaSinhVien, 1);
        addFormField(fieldsPanel, "Họ tên", txtHoTen, 2);
        addFormField(fieldsPanel, "Mã tin tuyển dụng", txtMaTinTuyenDung, 3);
        addFormField(fieldsPanel, "Vị trí ứng tuyển", txtViTri, 4);
        addFormField(fieldsPanel, "Ngày ứng tuyển", txtNgayUngTuyen, 5);
        addFormField(fieldsPanel, "Trạng thái xử lý", cboTrangThai, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);

        btnCapNhat = createActionButton("Cập nhật", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnCapNhat);
        buttonPanel.add(btnLamMoi);

        detailCard.add(lblFormTitle, BorderLayout.NORTH);
        detailCard.add(fieldsPanel, BorderLayout.CENTER);
        detailCard.add(buttonPanel, BorderLayout.SOUTH);

        return detailCard;
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

        lblTongUngTuyen = new JLabel("0");
        lblChoDuyet = new JLabel("0");

        badgePanel.add(createBadge("Tổng số đơn", lblTongUngTuyen, new Color(239, 246, 255), COLOR_NAVY));
        badgePanel.add(createBadge("Chờ xử lý", lblChoDuyet, new Color(255, 237, 213), COLOR_ORANGE));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(430, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập mã SV, họ tên, mã tin, vị trí, trạng thái...");
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
            "Mã ứng tuyển",
            "Mã SV",
            "Họ tên",
            "Mã tin",
            "Vị trí",
            "Ngày ứng tuyển",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblUngTuyen = new JTable(tableModel);
        tblUngTuyen.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblUngTuyen.setRowHeight(36);
        tblUngTuyen.setForeground(COLOR_TEXT);
        tblUngTuyen.setGridColor(new Color(203, 213, 225));
        tblUngTuyen.setShowVerticalLines(true);
        tblUngTuyen.setShowHorizontalLines(true);
        tblUngTuyen.setIntercellSpacing(new Dimension(1, 1));
        tblUngTuyen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblUngTuyen.setSelectionBackground(COLOR_ROW_SELECTED);
        tblUngTuyen.setSelectionForeground(COLOR_TEXT);

        tblUngTuyen.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblUngTuyen.getTableHeader().setForeground(Color.WHITE);
        tblUngTuyen.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblUngTuyen.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblUngTuyen.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblUngTuyen.getColumnModel().getColumn(0).setPreferredWidth(105);
        tblUngTuyen.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblUngTuyen.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblUngTuyen.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblUngTuyen.getColumnModel().getColumn(4).setPreferredWidth(170);
        tblUngTuyen.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblUngTuyen.getColumnModel().getColumn(6).setPreferredWidth(120);

        for (int i = 0; i < tblUngTuyen.getColumnCount(); i++) {
            tblUngTuyen.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblUngTuyen);
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

    private JTextField createReadonlyTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setForeground(COLOR_TEXT);
        textField.setBackground(COLOR_READONLY_BG);
        textField.setEditable(false);
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
        btnCapNhat.addActionListener(e -> handleCapNhat());
        btnLamMoi.addActionListener(e -> handleLamMoi());
        btnTimKiem.addActionListener(e -> handleTimKiem());

        txtTuKhoa.addActionListener(e -> handleTimKiem());

        tblUngTuyen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(getAllUngTuyen());
    }

    private void loadDataToTable(List<UngTuyen> danhSachUngTuyen) {
        tableModel.setRowCount(0);

        for (UngTuyen ungTuyen : danhSachUngTuyen) {
            String maUngTuyen = getValue(ungTuyen, "getMaUngTuyen", "getMaDonUngTuyen", "getMa");
            String maSinhVien = getValue(ungTuyen, "getMaSinhVien", "getMaSV");
            String maTinTuyenDung = getValue(ungTuyen, "getMaTinTuyenDung", "getMaTin", "getMaTD");
            String ngayUngTuyen = getValue(ungTuyen, "getNgayUngTuyen", "getNgayNop", "getNgay");
            String trangThai = getValue(ungTuyen, "getTrangThai", "getTinhTrang");

            tableModel.addRow(new Object[]{
                maUngTuyen,
                maSinhVien,
                getHoTenByMaSinhVien(maSinhVien),
                maTinTuyenDung,
                getViTriByMaTinTuyenDung(maTinTuyenDung),
                ngayUngTuyen,
                trangThai
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        List<UngTuyen> danhSach = getAllUngTuyen();

        int tongUngTuyen = danhSach.size();
        int choDuyet = 0;

        for (UngTuyen ungTuyen : danhSach) {
            String trangThai = getValue(ungTuyen, "getTrangThai", "getTinhTrang");

            if ("Chờ duyệt".equalsIgnoreCase(trangThai)
                    || "Chờ xử lý".equalsIgnoreCase(trangThai)
                    || "Đang chờ".equalsIgnoreCase(trangThai)) {
                choDuyet++;
            }
        }

        lblTongUngTuyen.setText(String.valueOf(tongUngTuyen));
        lblChoDuyet.setText(String.valueOf(choDuyet));
    }

    private void handleCapNhat() {
        String maUngTuyen = txtMaUngTuyen.getText().trim();

        if (maUngTuyen.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn hồ sơ ứng tuyển cần cập nhật!");
            return;
        }

        String trangThaiMoi = cboTrangThai.getSelectedItem().toString();

        boolean success = updateTrangThaiUngTuyen(maUngTuyen, trangThaiMoi);

        if (success) {
            MessageUtil.showInfo(this, "Cập nhật trạng thái ứng tuyển thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không thể cập nhật trạng thái ứng tuyển!");
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();
        List<UngTuyen> result = new ArrayList<>();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        for (UngTuyen ungTuyen : getAllUngTuyen()) {
            String maUngTuyen = getValue(ungTuyen, "getMaUngTuyen", "getMaDonUngTuyen", "getMa");
            String maSinhVien = getValue(ungTuyen, "getMaSinhVien", "getMaSV");
            String maTinTuyenDung = getValue(ungTuyen, "getMaTinTuyenDung", "getMaTin", "getMaTD");
            String ngayUngTuyen = getValue(ungTuyen, "getNgayUngTuyen", "getNgayNop", "getNgay");
            String trangThai = getValue(ungTuyen, "getTrangThai", "getTinhTrang");
            String hoTen = getHoTenByMaSinhVien(maSinhVien);
            String viTri = getViTriByMaTinTuyenDung(maTinTuyenDung);

            String searchText = (maUngTuyen + " " + maSinhVien + " " + hoTen + " "
                    + maTinTuyenDung + " " + viTri + " " + ngayUngTuyen + " " + trangThai).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(ungTuyen);
            }
        }

        loadDataToTable(result);
    }

    private void handleLamMoi() {
        txtTuKhoa.setText("");
        clearForm();
        loadDataToTable();
    }

    private void fillFormFromSelectedRow() {
        int selectedRow = tblUngTuyen.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        txtMaUngTuyen.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtMaSinhVien.setText(tableModel.getValueAt(selectedRow, 1).toString());
        txtHoTen.setText(tableModel.getValueAt(selectedRow, 2).toString());
        txtMaTinTuyenDung.setText(tableModel.getValueAt(selectedRow, 3).toString());
        txtViTri.setText(tableModel.getValueAt(selectedRow, 4).toString());
        txtNgayUngTuyen.setText(tableModel.getValueAt(selectedRow, 5).toString());
        cboTrangThai.setSelectedItem(tableModel.getValueAt(selectedRow, 6).toString());
    }

    private void clearForm() {
        txtMaUngTuyen.setText("");
        txtMaSinhVien.setText("");
        txtHoTen.setText("");
        txtMaTinTuyenDung.setText("");
        txtViTri.setText("");
        txtNgayUngTuyen.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblUngTuyen.clearSelection();
    }

    @SuppressWarnings("unchecked")
    private List<UngTuyen> getAllUngTuyen() {
        String[] methodNames = {
            "getAll",
            "getAllUngTuyen",
            "getDanhSachUngTuyen",
            "layDanhSachUngTuyen"
        };

        for (String methodName : methodNames) {
            try {
                Method method = ungTuyenService.getClass().getMethod(methodName);
                Object result = method.invoke(ungTuyenService);

                if (result instanceof List<?>) {
                    return (List<UngTuyen>) result;
                }
            } catch (Exception ignored) {
            }
        }

        return new ArrayList<>();
    }

    private boolean updateTrangThaiUngTuyen(String maUngTuyen, String trangThaiMoi) {
        String[] serviceMethodNames = {
            "updateTrangThai",
            "capNhatTrangThai",
            "updateStatus",
            "capNhatTrangThaiUngTuyen"
        };

        for (String methodName : serviceMethodNames) {
            try {
                Method method = ungTuyenService.getClass().getMethod(methodName, String.class, String.class);
                Object result = method.invoke(ungTuyenService, maUngTuyen, trangThaiMoi);

                if (result instanceof Boolean) {
                    return (Boolean) result;
                }

                return true;
            } catch (Exception ignored) {
            }
        }

        for (UngTuyen ungTuyen : getAllUngTuyen()) {
            String currentMaUngTuyen = getValue(ungTuyen, "getMaUngTuyen", "getMaDonUngTuyen", "getMa");

            if (maUngTuyen.equalsIgnoreCase(currentMaUngTuyen)) {
                return setValue(ungTuyen, trangThaiMoi, "setTrangThai", "setTinhTrang");
            }
        }

        return false;
    }

    private String getValue(UngTuyen ungTuyen, String... getterNames) {
        for (String getterName : getterNames) {
            try {
                Method method = ungTuyen.getClass().getMethod(getterName);
                Object value = method.invoke(ungTuyen);

                if (value != null) {
                    return value.toString();
                }
            } catch (Exception ignored) {
            }
        }

        return "";
    }

    private boolean setValue(UngTuyen ungTuyen, String value, String... setterNames) {
        for (String setterName : setterNames) {
            try {
                Method method = ungTuyen.getClass().getMethod(setterName, String.class);
                method.invoke(ungTuyen, value);
                return true;
            } catch (Exception ignored) {
            }
        }

        return false;
    }

    private String getHoTenByMaSinhVien(String maSinhVien) {
        if ("SV001".equalsIgnoreCase(maSinhVien)) {
            return "Nguyễn Văn A";
        }

        if ("SV002".equalsIgnoreCase(maSinhVien)) {
            return "Trần Thị B";
        }

        if ("SV003".equalsIgnoreCase(maSinhVien)) {
            return "Lê Văn C";
        }

        if ("SV004".equalsIgnoreCase(maSinhVien)) {
            return "Phạm Thị D";
        }

        return "Sinh viên " + maSinhVien;
    }

    private String getViTriByMaTinTuyenDung(String maTinTuyenDung) {
        if ("TD001".equalsIgnoreCase(maTinTuyenDung)) {
            return "Thực tập Java Developer";
        }

        if ("TD002".equalsIgnoreCase(maTinTuyenDung)) {
            return "Thực tập Frontend Developer";
        }

        if ("TD003".equalsIgnoreCase(maTinTuyenDung)) {
            return "Thực tập Tester";
        }

        if ("TD004".equalsIgnoreCase(maTinTuyenDung)) {
            return "Thực tập Business Analyst";
        }

        return "Vị trí " + maTinTuyenDung;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UngTuyenFrame().setVisible(true));
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