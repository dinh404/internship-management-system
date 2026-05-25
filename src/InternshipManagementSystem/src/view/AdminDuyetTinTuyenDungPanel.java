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
import model.TinTuyenDung;
import service.TinTuyenDungService;
import util.MessageUtil;

public class AdminDuyetTinTuyenDungPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblTinTuyenDung;
    private DefaultTableModel tableModel;

    private JLabel lblTongTin;
    private JLabel lblChoDuyet;
    private JLabel lblDaDuyet;
    private JLabel lblViPham;

    private JLabel lblMaTin;
    private JLabel lblViTri;
    private JLabel lblDoanhNghiep;
    private JLabel lblDiaDiem;
    private JLabel lblSoLuong;
    private JLabel lblHanNop;
    private JLabel lblTrangThai;
    private JLabel lblMucLuong;
    private JLabel lblHinhThuc;
    private JLabel lblNganhPhuHop;

    private JTextArea txtMoTaCongViec;
    private JTextArea txtYeuCauKyNang;
    private JTextArea txtQuyenLoi;
    private JTextArea txtLyDoXuLy;

    private JButton btnTimKiem;
    private JButton btnDuyetTin;
    private JButton btnTuChoi;
    private JButton btnDanhDauViPham;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final TinTuyenDungService tinTuyenDungService = new TinTuyenDungService();

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
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_ORANGE = Color.decode("#F97316");

    public AdminDuyetTinTuyenDungPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initUI();
        initEvents();
        loadDataToTable();
        clearDetail();
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createDetailCard(), BorderLayout.WEST);
        add(createTableCard(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("DUYỆT TIN TUYỂN DỤNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Admin kiểm duyệt nội dung tin tuyển dụng, duyệt tin hợp lệ hoặc đánh dấu vi phạm trước khi hiển thị cho sinh viên");
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
        detailCard.setLayout(new BorderLayout(0, 14));
        detailCard.setPreferredSize(new Dimension(430, 600));
        detailCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Nội dung cần kiểm duyệt");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);

        lblMaTin = createValueLabel();
        lblViTri = createValueLabel();
        lblDoanhNghiep = createValueLabel();
        lblDiaDiem = createValueLabel();
        lblSoLuong = createValueLabel();
        lblHanNop = createValueLabel();
        lblTrangThai = createValueLabel();
        lblMucLuong = createValueLabel();
        lblHinhThuc = createValueLabel();
        lblNganhPhuHop = createValueLabel();

        txtMoTaCongViec = createReadOnlyTextArea(74);
        txtYeuCauKyNang = createReadOnlyTextArea(74);
        txtQuyenLoi = createReadOnlyTextArea(64);

        addInfoRow(infoPanel, "Mã tin", lblMaTin, 0);
        addInfoRow(infoPanel, "Vị trí", lblViTri, 1);
        addInfoRow(infoPanel, "Doanh nghiệp", lblDoanhNghiep, 2);
        addInfoRow(infoPanel, "Địa điểm", lblDiaDiem, 3);
        addInfoRow(infoPanel, "Số lượng", lblSoLuong, 4);
        addInfoRow(infoPanel, "Hạn nộp", lblHanNop, 5);
        addInfoRow(infoPanel, "Trạng thái", lblTrangThai, 6);
        addInfoRow(infoPanel, "Mức lương/Phụ cấp", lblMucLuong, 7);
        addInfoRow(infoPanel, "Hình thức", lblHinhThuc, 8);
        addInfoRow(infoPanel, "Ngành phù hợp", lblNganhPhuHop, 9);
        addTextAreaRow(infoPanel, "Mô tả công việc", txtMoTaCongViec, 10);
        addTextAreaRow(infoPanel, "Yêu cầu kỹ năng", txtYeuCauKyNang, 11);
        addTextAreaRow(infoPanel, "Quyền lợi", txtQuyenLoi, 12);

        JScrollPane detailScrollPane = new JScrollPane(infoPanel);
        detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
        detailScrollPane.getViewport().setBackground(COLOR_CARD);

        JPanel actionPanel = new JPanel(new BorderLayout(0, 10));
        actionPanel.setOpaque(false);

        JLabel lblLyDo = new JLabel("Ghi chú/Lý do xử lý");
        lblLyDo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblLyDo.setForeground(COLOR_TEXT);

        txtLyDoXuLy = new JTextArea();
        txtLyDoXuLy.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtLyDoXuLy.setForeground(COLOR_TEXT);
        txtLyDoXuLy.setLineWrap(true);
        txtLyDoXuLy.setWrapStyleWord(true);
        txtLyDoXuLy.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtLyDoXuLy.setPreferredSize(new Dimension(350, 62));

        JPanel notePanel = new JPanel(new BorderLayout(0, 4));
        notePanel.setOpaque(false);
        notePanel.add(lblLyDo, BorderLayout.NORTH);
        notePanel.add(txtLyDoXuLy, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(360, 88));

        btnDuyetTin = createActionButton("Duyệt tin", COLOR_TEAL, Color.WHITE);
        btnTuChoi = createActionButton("Từ chối", COLOR_ORANGE, Color.WHITE);
        btnDanhDauViPham = createActionButton("Vi phạm", COLOR_DANGER, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnDuyetTin);
        buttonPanel.add(btnTuChoi);
        buttonPanel.add(btnDanhDauViPham);
        buttonPanel.add(btnLamMoi);

        actionPanel.add(notePanel, BorderLayout.NORTH);
        actionPanel.add(buttonPanel, BorderLayout.CENTER);

        detailCard.add(lblTitle, BorderLayout.NORTH);
        detailCard.add(detailScrollPane, BorderLayout.CENTER);
        detailCard.add(actionPanel, BorderLayout.SOUTH);

        return detailCard;
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

        lblTongTin = new JLabel("0");
        lblChoDuyet = new JLabel("0");
        lblDaDuyet = new JLabel("0");
        lblViPham = new JLabel("0");

        badgePanel.add(createBadge("Tổng tin", lblTongTin, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Chờ duyệt", lblChoDuyet, Color.decode("#FFEDD5"), Color.decode("#C2410C")));
        badgePanel.add(createBadge("Đã duyệt", lblDaDuyet, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Vi phạm/Từ chối", lblViPham, Color.decode("#FEF2F2"), COLOR_DANGER));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(720, 42));

        txtTuKhoa = createTextField("Tìm mã tin, vị trí, doanh nghiệp, kỹ năng, trạng thái...");
        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        topPanel.add(badgePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        String[] columns = {
            "Mã tin",
            "Vị trí",
            "Doanh nghiệp",
            "Địa điểm",
            "Hạn nộp",
            "Mức lương",
            "Trạng thái"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblTinTuyenDung = createStyledTable(tableModel);
        tblTinTuyenDung.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblTinTuyenDung.getColumnModel().getColumn(1).setPreferredWidth(140);
        tblTinTuyenDung.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblTinTuyenDung.getColumnModel().getColumn(3).setPreferredWidth(95);
        tblTinTuyenDung.getColumnModel().getColumn(4).setPreferredWidth(95);
        tblTinTuyenDung.getColumnModel().getColumn(5).setPreferredWidth(155);
        tblTinTuyenDung.getColumnModel().getColumn(6).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblTinTuyenDung);
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
        tblTinTuyenDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });

        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnDuyetTin.addActionListener(e -> handleCapNhatTrangThai("Đã duyệt"));
        btnTuChoi.addActionListener(e -> handleCapNhatTrangThai("Từ chối"));
        btnDanhDauViPham.addActionListener(e -> handleCapNhatTrangThai("Vi phạm"));

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblTinTuyenDung.clearSelection();
            clearDetail();
            loadDataToTable();
        });
    }

    private void loadDataToTable() {
        loadDataToTable(tinTuyenDungService.getAll());
    }

    private void loadDataToTable(List<TinTuyenDung> data) {
        tableModel.setRowCount(0);

        for (TinTuyenDung tin : data) {
            tableModel.addRow(new Object[]{
                tin.getMaTinTuyenDung(),
                tin.getTenViTri(),
                tin.getTenDoanhNghiep(),
                tin.getDiaDiem(),
                tin.getHanNop(),
                tin.getMucLuong(),
                tin.getTrangThai()
            });
        }

        updateBadges();
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim();

        if (keyword.isEmpty()) {
            loadDataToTable();
            return;
        }

        loadDataToTable(tinTuyenDungService.search(keyword));
    }

    private void handleCapNhatTrangThai(String trangThaiMoi) {
        TinTuyenDung tin = getSelectedTin();

        if (tin == null) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần xử lý!");
            return;
        }

        if (("Từ chối".equals(trangThaiMoi) || "Vi phạm".equals(trangThaiMoi))
                && txtLyDoXuLy.getText().trim().isEmpty()) {
            MessageUtil.showError(this, "Vui lòng nhập lý do khi từ chối hoặc đánh dấu vi phạm!");
            return;
        }

        tin.setTrangThai(trangThaiMoi);
        tinTuyenDungService.update(tin);

        loadDataToTable();
        updateBadges();
        fillDetail(tin);

        String message = "Đã cập nhật trạng thái tin tuyển dụng thành: " + trangThaiMoi
                + ". Hệ thống đã gửi thông báo mô phỏng cho doanh nghiệp/HR.";
        MessageUtil.showInfo(this, message);
    }

    private TinTuyenDung getSelectedTin() {
        String maTin = lblMaTin.getText();

        if (maTin == null || maTin.equals("Chưa chọn")) {
            return null;
        }

        return tinTuyenDungService.getByMa(maTin);
    }

    private void fillDetailFromSelectedRow() {
        int selectedRow = tblTinTuyenDung.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String maTin = tableModel.getValueAt(selectedRow, 0).toString();
        TinTuyenDung tin = tinTuyenDungService.getByMa(maTin);

        if (tin != null) {
            fillDetail(tin);
        }
    }

    private void fillDetail(TinTuyenDung tin) {
        lblMaTin.setText(tin.getMaTinTuyenDung());
        lblViTri.setText(tin.getTenViTri());
        lblDoanhNghiep.setText(tin.getTenDoanhNghiep());
        lblDiaDiem.setText(tin.getDiaDiem());
        lblSoLuong.setText(String.valueOf(tin.getSoLuong()));
        lblHanNop.setText(tin.getHanNop());
        lblTrangThai.setText(tin.getTrangThai());
        lblMucLuong.setText(tin.getMucLuong());
        lblHinhThuc.setText(tin.getHinhThucLamViec());
        lblNganhPhuHop.setText(tin.getNganhPhuHop());
        txtMoTaCongViec.setText(tin.getMoTaCongViec());
        txtYeuCauKyNang.setText(tin.getYeuCauKyNang());
        txtQuyenLoi.setText(tin.getQuyenLoi());
    }

    private void clearDetail() {
        lblMaTin.setText("Chưa chọn");
        lblViTri.setText("Chưa chọn");
        lblDoanhNghiep.setText("Chưa chọn");
        lblDiaDiem.setText("Chưa chọn");
        lblSoLuong.setText("Chưa chọn");
        lblHanNop.setText("Chưa chọn");
        lblTrangThai.setText("Chưa chọn");
        lblMucLuong.setText("Chưa chọn");
        lblHinhThuc.setText("Chưa chọn");
        lblNganhPhuHop.setText("Chưa chọn");
        txtMoTaCongViec.setText("Chưa chọn");
        txtYeuCauKyNang.setText("Chưa chọn");
        txtQuyenLoi.setText("Chưa chọn");
        txtLyDoXuLy.setText("");
    }

    private void updateBadges() {
        int tong = 0;
        int choDuyet = 0;
        int daDuyet = 0;
        int viPham = 0;

        for (TinTuyenDung tin : tinTuyenDungService.getAll()) {
            tong++;

            if ("Chờ duyệt".equalsIgnoreCase(tin.getTrangThai())) {
                choDuyet++;
            }

            if ("Đã duyệt".equalsIgnoreCase(tin.getTrangThai())
                    || "Đang mở".equalsIgnoreCase(tin.getTrangThai())) {
                daDuyet++;
            }

            if ("Từ chối".equalsIgnoreCase(tin.getTrangThai())
                    || "Vi phạm".equalsIgnoreCase(tin.getTrangThai())) {
                viPham++;
            }
        }

        lblTongTin.setText(String.valueOf(tong));
        lblChoDuyet.setText(String.valueOf(choDuyet));
        lblDaDuyet.setText(String.valueOf(daDuyet));
        lblViPham.setText(String.valueOf(viPham));
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel("Chưa chọn");
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT);
        label.setOpaque(true);
        label.setBackground(Color.decode("#F1F5F9"));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return label;
    }

    private JTextArea createReadOnlyTextArea(int height) {
        JTextArea textArea = new JTextArea("Chưa chọn");
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        textArea.setForeground(COLOR_TEXT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(true);
        textArea.setBackground(Color.decode("#F8FAFC"));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        textArea.setPreferredSize(new Dimension(350, height));
        return textArea;
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

    private void addInfoRow(JPanel panel, String labelText, JLabel valueLabel, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 3, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(valueLabel, gbc);
    }

    private void addTextAreaRow(JPanel panel, String labelText, JTextArea textArea, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row * 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 3, 0);
        panel.add(label, gbc);

        gbc.gridy = row * 2 + 1;
        gbc.insets = new Insets(0, 0, 7, 0);
        panel.add(textArea, gbc);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(foregroundColor);
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
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
