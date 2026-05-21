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

public class QuanLyCVPanel extends JPanel {

    private JTextField txtMaCV;
    private JTextField txtTieuDe;
    private JTextField txtKyNang;
    private JTextField txtKinhNghiem;
    private JTextField txtNgayCapNhat;
    private JTextField txtTuKhoa;
    private JTextArea txtMucTieu;
    private JComboBox<String> cboTrangThai;

    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTimKiem;

    private JTable tblCV;
    private DefaultTableModel tableModel;

    private JLabel lblTongCV;
    private JLabel lblDangSuDung;
    private JLabel lblNhap;

    private final TaiKhoan currentUser;
    private final List<CVSinhVien> danhSachCV = new ArrayList<>();

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_GRAY_BUTTON = Color.decode("#F1F5F9");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_INPUT_BORDER = Color.decode("#CBD5E1");
    private final Color COLOR_ROW_SELECTED = Color.decode("#DBEAFE");
    private final Color COLOR_TABLE_HEADER = Color.decode("#1E3A8A");

    public QuanLyCVPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
    }

    private void initData() {
        String maSinhVien = currentUser != null ? currentUser.getMaNguoiDung() : "SV001";

        danhSachCV.add(new CVSinhVien(
                "CV001",
                maSinhVien,
                "CV Java Backend Intern",
                "Java, Swing, SQL, Git",
                "Dự án quản lý thực tập bằng Java Swing",
                "Mong muốn thực tập lập trình Java Backend, học hỏi quy trình phát triển phần mềm doanh nghiệp.",
                "21/05/2026",
                "Đang sử dụng"
        ));

        danhSachCV.add(new CVSinhVien(
                "CV002",
                maSinhVien,
                "CV Frontend Intern",
                "HTML, CSS, JavaScript, UI Design",
                "Thiết kế giao diện quản trị và dashboard demo",
                "Mong muốn tham gia phát triển giao diện người dùng cho hệ thống web/app doanh nghiệp.",
                "20/05/2026",
                "Nháp"
        ));

        danhSachCV.add(new CVSinhVien(
                "CV003",
                maSinhVien,
                "CV Business Analyst Intern",
                "Phân tích yêu cầu, Use Case, UML, giao tiếp",
                "Tham gia phân tích đồ án hệ thống quản lý thực tập",
                "Mong muốn rèn luyện kỹ năng phân tích nghiệp vụ và kết nối giữa người dùng với nhóm kỹ thuật.",
                "18/05/2026",
                "Nháp"
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

        JLabel lblTitle = new JLabel("QUẢN LÝ CV");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Sinh viên thêm, sửa, xóa và tra cứu CV cá nhân phục vụ quá trình ứng tuyển thực tập");
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
        formCard.setPreferredSize(new Dimension(360, 590));
        formCard.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel lblFormTitle = new JLabel("Thông tin CV");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFormTitle.setForeground(COLOR_TEXT);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        txtMaCV = createTextField();
        txtTieuDe = createTextField();
        txtKyNang = createTextField();
        txtKinhNghiem = createTextField();
        txtNgayCapNhat = createTextField();

        txtMucTieu = new JTextArea();
        txtMucTieu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtMucTieu.setForeground(COLOR_TEXT);
        txtMucTieu.setCaretColor(COLOR_TEAL);
        txtMucTieu.setLineWrap(true);
        txtMucTieu.setWrapStyleWord(true);
        txtMucTieu.setRows(3);
        txtMucTieu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        cboTrangThai = new JComboBox<>(new String[]{
            "Đang sử dụng",
            "Nháp"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBackground(Color.WHITE);
        cboTrangThai.setForeground(COLOR_TEXT);
        cboTrangThai.setPreferredSize(new Dimension(300, 38));

        addFormField(fieldsPanel, "Mã CV", txtMaCV, 0);
        addFormField(fieldsPanel, "Tiêu đề CV", txtTieuDe, 1);
        addFormField(fieldsPanel, "Kỹ năng", txtKyNang, 2);
        addFormField(fieldsPanel, "Kinh nghiệm", txtKinhNghiem, 3);
        addFormField(fieldsPanel, "Mục tiêu nghề nghiệp", txtMucTieu, 4);
        addFormField(fieldsPanel, "Ngày cập nhật", txtNgayCapNhat, 5);
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

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        JPanel badgePanel = new JPanel(new GridLayout(1, 3, 10, 0));
        badgePanel.setOpaque(false);
        badgePanel.setPreferredSize(new Dimension(430, 64));

        lblTongCV = new JLabel("0");
        lblDangSuDung = new JLabel("0");
        lblNhap = new JLabel("0");

        badgePanel.add(createBadge("Tổng CV", lblTongCV, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Đang sử dụng", lblDangSuDung, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("CV nháp", lblNhap, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(430, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập tiêu đề, kỹ năng, kinh nghiệm...");
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

        GridBagConstraints topGbc = new GridBagConstraints();

        topGbc.gridx = 0;
        topGbc.gridy = 0;
        topGbc.weightx = 0;
        topGbc.fill = GridBagConstraints.NONE;
        topGbc.anchor = GridBagConstraints.WEST;
        topGbc.insets = new Insets(0, 0, 0, 16);
        topPanel.add(badgePanel, topGbc);

        topGbc.gridx = 1;
        topGbc.weightx = 1.0;
        topGbc.fill = GridBagConstraints.HORIZONTAL;
        topGbc.anchor = GridBagConstraints.EAST;
        topGbc.insets = new Insets(0, 0, 0, 0);
        topPanel.add(searchPanel, topGbc);

        String[] columns = {
            "Mã CV",
            "Tiêu đề",
            "Kỹ năng",
            "Kinh nghiệm",
            "Ngày cập nhật",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblCV = new JTable(tableModel);
        tblCV.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblCV.setRowHeight(36);
        tblCV.setForeground(COLOR_TEXT);
        tblCV.setGridColor(Color.decode("#CBD5E1"));
        tblCV.setShowVerticalLines(true);
        tblCV.setShowHorizontalLines(true);
        tblCV.setIntercellSpacing(new Dimension(1, 1));
        tblCV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCV.setSelectionBackground(COLOR_ROW_SELECTED);
        tblCV.setSelectionForeground(COLOR_TEXT);

        tblCV.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblCV.getTableHeader().setForeground(Color.WHITE);
        tblCV.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        tblCV.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblCV.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblCV.getColumnCount(); i++) {
            tblCV.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblCV.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblCV.getColumnModel().getColumn(1).setPreferredWidth(170);
        tblCV.getColumnModel().getColumn(2).setPreferredWidth(180);
        tblCV.getColumnModel().getColumn(3).setPreferredWidth(180);
        tblCV.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblCV.getColumnModel().getColumn(5).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblCV);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCard.add(topPanel, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private void initEvents() {
        btnThem.addActionListener(e -> handleThem());
        btnSua.addActionListener(e -> handleSua());
        btnXoa.addActionListener(e -> handleXoa());
        btnLamMoi.addActionListener(e -> handleLamMoi());
        btnTimKiem.addActionListener(e -> handleTimKiem());

        txtTuKhoa.addActionListener(e -> handleTimKiem());

        tblCV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(danhSachCV);
    }

    private void loadDataToTable(List<CVSinhVien> danhSach) {
        tableModel.setRowCount(0);

        for (CVSinhVien cv : danhSach) {
            tableModel.addRow(new Object[]{
                cv.getMaCV(),
                cv.getTieuDe(),
                cv.getKyNang(),
                cv.getKinhNghiem(),
                cv.getNgayCapNhat(),
                cv.getTrangThai()
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        int tong = danhSachCV.size();
        int dangSuDung = 0;
        int nhap = 0;

        for (CVSinhVien cv : danhSachCV) {
            if ("Đang sử dụng".equalsIgnoreCase(cv.getTrangThai())) {
                dangSuDung++;
            } else if ("Nháp".equalsIgnoreCase(cv.getTrangThai())) {
                nhap++;
            }
        }

        lblTongCV.setText(String.valueOf(tong));
        lblDangSuDung.setText(String.valueOf(dangSuDung));
        lblNhap.setText(String.valueOf(nhap));
    }

    private void handleThem() {
        if (!validateForm()) {
            return;
        }

        String maCV = txtMaCV.getText().trim();

        if (findByMaCV(maCV) != null) {
            MessageUtil.showError(this, "Mã CV đã tồn tại!");
            txtMaCV.requestFocus();
            return;
        }

        CVSinhVien cv = getDataFromForm();
        danhSachCV.add(cv);

        MessageUtil.showInfo(this, "Thêm CV thành công!");
        loadDataToTable();
        clearForm();
    }

    private void handleSua() {
        String maCV = txtMaCV.getText().trim();

        if (maCV.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn CV cần sửa!");
            return;
        }

        if (!validateForm()) {
            return;
        }

        CVSinhVien cv = findByMaCV(maCV);

        if (cv == null) {
            MessageUtil.showError(this, "Không tìm thấy CV cần cập nhật!");
            return;
        }

        cv.setTieuDe(txtTieuDe.getText().trim());
        cv.setKyNang(txtKyNang.getText().trim());
        cv.setKinhNghiem(txtKinhNghiem.getText().trim());
        cv.setMucTieu(txtMucTieu.getText().trim());
        cv.setNgayCapNhat(txtNgayCapNhat.getText().trim());
        cv.setTrangThai(cboTrangThai.getSelectedItem().toString());

        MessageUtil.showInfo(this, "Cập nhật CV thành công!");
        loadDataToTable();
        clearForm();
    }

    private void handleXoa() {
        int selectedRow = tblCV.getSelectedRow();

        if (selectedRow < 0) {
            MessageUtil.showError(this, "Vui lòng chọn CV cần xóa!");
            return;
        }

        String maCV = tableModel.getValueAt(selectedRow, 0).toString();

        boolean confirm = MessageUtil.showConfirm(this, "Bạn có chắc chắn muốn xóa CV " + maCV + "?");

        if (!confirm) {
            return;
        }

        CVSinhVien cv = findByMaCV(maCV);

        if (cv != null) {
            danhSachCV.remove(cv);
            MessageUtil.showInfo(this, "Xóa CV thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không thể xóa CV!");
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        List<CVSinhVien> result = new ArrayList<>();

        for (CVSinhVien cv : danhSachCV) {
            String searchText = (
                    cv.getMaCV() + " "
                    + cv.getTieuDe() + " "
                    + cv.getKyNang() + " "
                    + cv.getKinhNghiem() + " "
                    + cv.getMucTieu() + " "
                    + cv.getNgayCapNhat() + " "
                    + cv.getTrangThai()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(cv);
            }
        }

        loadDataToTable(result);
    }

    private void handleLamMoi() {
        txtTuKhoa.setText("");
        clearForm();
        loadDataToTable();
    }

    private CVSinhVien getDataFromForm() {
        String maSinhVien = currentUser != null ? currentUser.getMaNguoiDung() : "SV001";

        return new CVSinhVien(
                txtMaCV.getText().trim(),
                maSinhVien,
                txtTieuDe.getText().trim(),
                txtKyNang.getText().trim(),
                txtKinhNghiem.getText().trim(),
                txtMucTieu.getText().trim(),
                txtNgayCapNhat.getText().trim(),
                cboTrangThai.getSelectedItem().toString()
        );
    }

    private void fillFormFromSelectedRow() {
        int selectedRow = tblCV.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String maCV = tableModel.getValueAt(selectedRow, 0).toString();
        CVSinhVien cv = findByMaCV(maCV);

        if (cv == null) {
            return;
        }

        txtMaCV.setText(cv.getMaCV());
        txtTieuDe.setText(cv.getTieuDe());
        txtKyNang.setText(cv.getKyNang());
        txtKinhNghiem.setText(cv.getKinhNghiem());
        txtMucTieu.setText(cv.getMucTieu());
        txtNgayCapNhat.setText(cv.getNgayCapNhat());
        cboTrangThai.setSelectedItem(cv.getTrangThai());

        txtMaCV.setEditable(false);
    }

    private void clearForm() {
        txtMaCV.setText("");
        txtTieuDe.setText("");
        txtKyNang.setText("");
        txtKinhNghiem.setText("");
        txtMucTieu.setText("");
        txtNgayCapNhat.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblCV.clearSelection();
        txtMaCV.setEditable(true);
        txtMaCV.requestFocus();
    }

    private boolean validateForm() {
        if (txtMaCV.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập mã CV!");
            txtMaCV.requestFocus();
            return false;
        }

        if (txtTieuDe.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập tiêu đề CV!");
            txtTieuDe.requestFocus();
            return false;
        }

        if (txtKyNang.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập kỹ năng!");
            txtKyNang.requestFocus();
            return false;
        }

        if (txtKinhNghiem.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập kinh nghiệm!");
            txtKinhNghiem.requestFocus();
            return false;
        }

        if (txtMucTieu.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập mục tiêu nghề nghiệp!");
            txtMucTieu.requestFocus();
            return false;
        }

        if (txtNgayCapNhat.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập ngày cập nhật!");
            txtNgayCapNhat.requestFocus();
            return false;
        }

        return true;
    }

    private CVSinhVien findByMaCV(String maCV) {
        for (CVSinhVien cv : danhSachCV) {
            if (cv.getMaCV().equalsIgnoreCase(maCV)) {
                return cv;
            }
        }

        return null;
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

    private RoundedPanel createBadge(String title, JLabel valueLabel, Color backgroundColor, Color textColor) {
        RoundedPanel badge = new RoundedPanel(18, backgroundColor);
        badge.setLayout(new GridBagLayout());
        badge.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        badge.setPreferredSize(new Dimension(132, 60));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
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

    private static class CVSinhVien {

        private String maCV;
        private String maSinhVien;
        private String tieuDe;
        private String kyNang;
        private String kinhNghiem;
        private String mucTieu;
        private String ngayCapNhat;
        private String trangThai;

        public CVSinhVien(String maCV, String maSinhVien, String tieuDe, String kyNang,
                String kinhNghiem, String mucTieu, String ngayCapNhat, String trangThai) {
            this.maCV = maCV;
            this.maSinhVien = maSinhVien;
            this.tieuDe = tieuDe;
            this.kyNang = kyNang;
            this.kinhNghiem = kinhNghiem;
            this.mucTieu = mucTieu;
            this.ngayCapNhat = ngayCapNhat;
            this.trangThai = trangThai;
        }

        public String getMaCV() {
            return maCV;
        }

        public String getMaSinhVien() {
            return maSinhVien;
        }

        public String getTieuDe() {
            return tieuDe;
        }

        public void setTieuDe(String tieuDe) {
            this.tieuDe = tieuDe;
        }

        public String getKyNang() {
            return kyNang;
        }

        public void setKyNang(String kyNang) {
            this.kyNang = kyNang;
        }

        public String getKinhNghiem() {
            return kinhNghiem;
        }

        public void setKinhNghiem(String kinhNghiem) {
            this.kinhNghiem = kinhNghiem;
        }

        public String getMucTieu() {
            return mucTieu;
        }

        public void setMucTieu(String mucTieu) {
            this.mucTieu = mucTieu;
        }

        public String getNgayCapNhat() {
            return ngayCapNhat;
        }

        public void setNgayCapNhat(String ngayCapNhat) {
            this.ngayCapNhat = ngayCapNhat;
        }

        public String getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(String trangThai) {
            this.trangThai = trangThai;
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