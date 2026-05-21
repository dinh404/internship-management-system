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

public class TinTuyenDungCuaToiPanel extends JPanel {

    private JTextField txtMaTin;
    private JTextField txtViTri;
    private JTextField txtDiaDiem;
    private JTextField txtSoLuong;
    private JTextField txtHanNop;
    private JTextField txtTuKhoa;
    private JTextArea txtYeuCau;
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
    private JLabel lblDaDong;

    private final TaiKhoan currentUser;
    private final List<TinTuyenDungDoanhNghiep> danhSachTin = new ArrayList<>();

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

    public TinTuyenDungCuaToiPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDataToTable();
    }

    private void initData() {
        String maDoanhNghiep = currentUser != null ? currentUser.getMaNguoiDung() : "DN001";
        String tenDoanhNghiep = getTenDoanhNghiep();

        danhSachTin.add(new TinTuyenDungDoanhNghiep(
                "TD001",
                maDoanhNghiep,
                tenDoanhNghiep,
                "Thực tập Java Developer",
                "TP. Hồ Chí Minh",
                5,
                "30/06/2026",
                "Java Core, SQL, Git, tư duy lập trình tốt",
                "Đang mở"
        ));

        danhSachTin.add(new TinTuyenDungDoanhNghiep(
                "TD002",
                maDoanhNghiep,
                tenDoanhNghiep,
                "Thực tập Frontend Developer",
                "Hà Nội",
                3,
                "25/06/2026",
                "HTML, CSS, JavaScript, có tư duy UI/UX cơ bản",
                "Đang mở"
        ));

        danhSachTin.add(new TinTuyenDungDoanhNghiep(
                "TD003",
                maDoanhNghiep,
                tenDoanhNghiep,
                "Thực tập Tester",
                "Đà Nẵng",
                2,
                "15/06/2026",
                "Biết viết test case, có kiến thức kiểm thử phần mềm",
                "Đã đóng"
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

        JLabel lblTitle = new JLabel("TIN TUYỂN DỤNG CỦA TÔI");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Doanh nghiệp/HR đăng, cập nhật, tra cứu và quản lý các vị trí thực tập của đơn vị mình");
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

        JLabel lblFormTitle = new JLabel("Thông tin tin tuyển dụng");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFormTitle.setForeground(COLOR_TEXT);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        txtMaTin = createTextField();
        txtViTri = createTextField();
        txtDiaDiem = createTextField();
        txtSoLuong = createTextField();
        txtHanNop = createTextField();

        txtYeuCau = new JTextArea();
        txtYeuCau.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtYeuCau.setForeground(COLOR_TEXT);
        txtYeuCau.setCaretColor(COLOR_TEAL);
        txtYeuCau.setLineWrap(true);
        txtYeuCau.setWrapStyleWord(true);
        txtYeuCau.setRows(3);
        txtYeuCau.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        cboTrangThai = new JComboBox<>(new String[]{
            "Đang mở",
            "Đã đóng"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBackground(Color.WHITE);
        cboTrangThai.setForeground(COLOR_TEXT);
        cboTrangThai.setPreferredSize(new Dimension(300, 38));

        addFormField(fieldsPanel, "Mã tin", txtMaTin, 0);
        addFormField(fieldsPanel, "Vị trí thực tập", txtViTri, 1);
        addFormField(fieldsPanel, "Địa điểm", txtDiaDiem, 2);
        addFormField(fieldsPanel, "Số lượng", txtSoLuong, 3);
        addFormField(fieldsPanel, "Hạn nộp", txtHanNop, 4);
        addFormField(fieldsPanel, "Yêu cầu ứng viên", txtYeuCau, 5);
        addFormField(fieldsPanel, "Trạng thái", cboTrangThai, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        buttonPanel.setOpaque(false);

        btnThem = createActionButton("Thêm", COLOR_TEAL, Color.WHITE);
        btnSua = createActionButton("Sửa", COLOR_NAVY, Color.WHITE);
        btnXoa = createActionButton("Xóa", COLOR_DANGER, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", Color.decode("#E2E8F0"), COLOR_TEXT);

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

        lblTongTin = new JLabel("0");
        lblDangMo = new JLabel("0");
        lblDaDong = new JLabel("0");

        badgePanel.add(createBadge("Tổng tin", lblTongTin, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Đang mở", lblDangMo, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Đã đóng", lblDaDong, Color.decode("#FEE2E2"), COLOR_DANGER));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(430, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập mã tin, vị trí, địa điểm, trạng thái...");
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
            "Mã tin",
            "Doanh nghiệp",
            "Vị trí",
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
        tblTinTuyenDung.setGridColor(Color.decode("#CBD5E1"));
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

        for (int i = 0; i < tblTinTuyenDung.getColumnCount(); i++) {
            tblTinTuyenDung.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblTinTuyenDung.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblTinTuyenDung.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblTinTuyenDung.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblTinTuyenDung.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblTinTuyenDung.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblTinTuyenDung.getColumnModel().getColumn(5).setPreferredWidth(110);
        tblTinTuyenDung.getColumnModel().getColumn(6).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblTinTuyenDung);
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

        tblTinTuyenDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillFormFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(danhSachTin);
    }

    private void loadDataToTable(List<TinTuyenDungDoanhNghiep> danhSach) {
        tableModel.setRowCount(0);

        for (TinTuyenDungDoanhNghiep tin : danhSach) {
            tableModel.addRow(new Object[]{
                tin.getMaTin(),
                tin.getTenDoanhNghiep(),
                tin.getViTri(),
                tin.getDiaDiem(),
                tin.getSoLuong(),
                tin.getHanNop(),
                tin.getTrangThai()
            });
        }

        updateBadges();
    }

    private void updateBadges() {
        int tong = danhSachTin.size();
        int dangMo = 0;
        int daDong = 0;

        for (TinTuyenDungDoanhNghiep tin : danhSachTin) {
            if ("Đang mở".equalsIgnoreCase(tin.getTrangThai())) {
                dangMo++;
            } else if ("Đã đóng".equalsIgnoreCase(tin.getTrangThai())) {
                daDong++;
            }
        }

        lblTongTin.setText(String.valueOf(tong));
        lblDangMo.setText(String.valueOf(dangMo));
        lblDaDong.setText(String.valueOf(daDong));
    }

    private void handleThem() {
        if (!validateForm()) {
            return;
        }

        String maTin = txtMaTin.getText().trim();

        if (findByMaTin(maTin) != null) {
            MessageUtil.showError(this, "Mã tin tuyển dụng đã tồn tại!");
            txtMaTin.requestFocus();
            return;
        }

        danhSachTin.add(getDataFromForm());

        MessageUtil.showInfo(this, "Thêm tin tuyển dụng thành công!");
        loadDataToTable();
        clearForm();
    }

    private void handleSua() {
        String maTin = txtMaTin.getText().trim();

        if (maTin.isEmpty()) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần sửa!");
            return;
        }

        if (!validateForm()) {
            return;
        }

        TinTuyenDungDoanhNghiep tin = findByMaTin(maTin);

        if (tin == null) {
            MessageUtil.showError(this, "Không tìm thấy tin tuyển dụng cần cập nhật!");
            return;
        }

        tin.setViTri(txtViTri.getText().trim());
        tin.setDiaDiem(txtDiaDiem.getText().trim());
        tin.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
        tin.setHanNop(txtHanNop.getText().trim());
        tin.setYeuCau(txtYeuCau.getText().trim());
        tin.setTrangThai(cboTrangThai.getSelectedItem().toString());

        MessageUtil.showInfo(this, "Cập nhật tin tuyển dụng thành công!");
        loadDataToTable();
        clearForm();
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

        TinTuyenDungDoanhNghiep tin = findByMaTin(maTin);

        if (tin != null) {
            danhSachTin.remove(tin);
            MessageUtil.showInfo(this, "Xóa tin tuyển dụng thành công!");
            loadDataToTable();
            clearForm();
        } else {
            MessageUtil.showError(this, "Không thể xóa tin tuyển dụng!");
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        List<TinTuyenDungDoanhNghiep> result = new ArrayList<>();

        for (TinTuyenDungDoanhNghiep tin : danhSachTin) {
            String searchText = (
                    tin.getMaTin() + " "
                    + tin.getTenDoanhNghiep() + " "
                    + tin.getViTri() + " "
                    + tin.getDiaDiem() + " "
                    + tin.getSoLuong() + " "
                    + tin.getHanNop() + " "
                    + tin.getYeuCau() + " "
                    + tin.getTrangThai()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(tin);
            }
        }

        loadDataToTable(result);
    }

    private void handleLamMoi() {
        txtTuKhoa.setText("");
        clearForm();
        loadDataToTable();
    }

    private TinTuyenDungDoanhNghiep getDataFromForm() {
        String maDoanhNghiep = currentUser != null ? currentUser.getMaNguoiDung() : "DN001";

        return new TinTuyenDungDoanhNghiep(
                txtMaTin.getText().trim(),
                maDoanhNghiep,
                getTenDoanhNghiep(),
                txtViTri.getText().trim(),
                txtDiaDiem.getText().trim(),
                Integer.parseInt(txtSoLuong.getText().trim()),
                txtHanNop.getText().trim(),
                txtYeuCau.getText().trim(),
                cboTrangThai.getSelectedItem().toString()
        );
    }

    private void fillFormFromSelectedRow() {
        int selectedRow = tblTinTuyenDung.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String maTin = tableModel.getValueAt(selectedRow, 0).toString();
        TinTuyenDungDoanhNghiep tin = findByMaTin(maTin);

        if (tin == null) {
            return;
        }

        txtMaTin.setText(tin.getMaTin());
        txtViTri.setText(tin.getViTri());
        txtDiaDiem.setText(tin.getDiaDiem());
        txtSoLuong.setText(String.valueOf(tin.getSoLuong()));
        txtHanNop.setText(tin.getHanNop());
        txtYeuCau.setText(tin.getYeuCau());
        cboTrangThai.setSelectedItem(tin.getTrangThai());

        txtMaTin.setEditable(false);
    }

    private void clearForm() {
        txtMaTin.setText("");
        txtViTri.setText("");
        txtDiaDiem.setText("");
        txtSoLuong.setText("");
        txtHanNop.setText("");
        txtYeuCau.setText("");
        cboTrangThai.setSelectedIndex(0);
        tblTinTuyenDung.clearSelection();
        txtMaTin.setEditable(true);
        txtMaTin.requestFocus();
    }

    private boolean validateForm() {
        if (txtMaTin.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập mã tin tuyển dụng!");
            txtMaTin.requestFocus();
            return false;
        }

        if (txtViTri.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập vị trí thực tập!");
            txtViTri.requestFocus();
            return false;
        }

        if (txtDiaDiem.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập địa điểm!");
            txtDiaDiem.requestFocus();
            return false;
        }

        if (txtSoLuong.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập số lượng!");
            txtSoLuong.requestFocus();
            return false;
        }

        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

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

        if (txtHanNop.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập hạn nộp!");
            txtHanNop.requestFocus();
            return false;
        }

        if (txtYeuCau.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập yêu cầu ứng viên!");
            txtYeuCau.requestFocus();
            return false;
        }

        return true;
    }

    private TinTuyenDungDoanhNghiep findByMaTin(String maTin) {
        for (TinTuyenDungDoanhNghiep tin : danhSachTin) {
            if (tin.getMaTin().equalsIgnoreCase(maTin)) {
                return tin;
            }
        }

        return null;
    }

    private String getTenDoanhNghiep() {
        if (currentUser == null) {
            return "Công ty ABC";
        }

        if (currentUser.isHR()) {
            return "Công ty ABC";
        }

        return currentUser.getTenHienThi();
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

    private static class TinTuyenDungDoanhNghiep {

        private String maTin;
        private String maDoanhNghiep;
        private String tenDoanhNghiep;
        private String viTri;
        private String diaDiem;
        private int soLuong;
        private String hanNop;
        private String yeuCau;
        private String trangThai;

        public TinTuyenDungDoanhNghiep(String maTin, String maDoanhNghiep, String tenDoanhNghiep,
                String viTri, String diaDiem, int soLuong, String hanNop, String yeuCau, String trangThai) {
            this.maTin = maTin;
            this.maDoanhNghiep = maDoanhNghiep;
            this.tenDoanhNghiep = tenDoanhNghiep;
            this.viTri = viTri;
            this.diaDiem = diaDiem;
            this.soLuong = soLuong;
            this.hanNop = hanNop;
            this.yeuCau = yeuCau;
            this.trangThai = trangThai;
        }

        public String getMaTin() {
            return maTin;
        }

        public String getMaDoanhNghiep() {
            return maDoanhNghiep;
        }

        public String getTenDoanhNghiep() {
            return tenDoanhNghiep;
        }

        public String getViTri() {
            return viTri;
        }

        public void setViTri(String viTri) {
            this.viTri = viTri;
        }

        public String getDiaDiem() {
            return diaDiem;
        }

        public void setDiaDiem(String diaDiem) {
            this.diaDiem = diaDiem;
        }

        public int getSoLuong() {
            return soLuong;
        }

        public void setSoLuong(int soLuong) {
            this.soLuong = soLuong;
        }

        public String getHanNop() {
            return hanNop;
        }

        public void setHanNop(String hanNop) {
            this.hanNop = hanNop;
        }

        public String getYeuCau() {
            return yeuCau;
        }

        public void setYeuCau(String yeuCau) {
            this.yeuCau = yeuCau;
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