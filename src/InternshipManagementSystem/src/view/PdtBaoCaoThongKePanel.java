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

public class PdtBaoCaoThongKePanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblNganhHoc;
    private JTable tblDoanhNghiep;
    private DefaultTableModel nganhHocTableModel;
    private DefaultTableModel doanhNghiepTableModel;

    private JLabel lblTongSinhVien;
    private JLabel lblDaCoNoiThucTap;
    private JLabel lblChuaCoNoiThucTap;
    private JLabel lblTiLeHoanThanh;

    private JButton btnTimKiem;
    private JButton btnLamMoi;

    private BarChartPanel chartPanel;
    private JTextArea txtNhanXet;

    private final TaiKhoan currentUser;
    private final List<ThongKeNganhHoc> danhSachNganhHoc = new ArrayList<>();
    private final List<ThongKeDoanhNghiep> danhSachDoanhNghiep = new ArrayList<>();

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

    public PdtBaoCaoThongKePanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadNganhHocToTable();
        loadDoanhNghiepToTable();
        updateBadges();
        updateNhanXet();
    }

    private void initData() {
        danhSachNganhHoc.add(new ThongKeNganhHoc(
                "Công nghệ thông tin",
                52,
                38,
                14,
                18,
                11,
                3,
                "73%"
        ));

        danhSachNganhHoc.add(new ThongKeNganhHoc(
                "Kỹ thuật phần mềm",
                34,
                26,
                8,
                12,
                8,
                2,
                "76%"
        ));

        danhSachNganhHoc.add(new ThongKeNganhHoc(
                "Hệ thống thông tin",
                31,
                20,
                11,
                9,
                5,
                2,
                "65%"
        ));

        danhSachNganhHoc.add(new ThongKeNganhHoc(
                "Mạng máy tính",
                11,
                2,
                9,
                4,
                1,
                1,
                "18%"
        ));

        danhSachDoanhNghiep.add(new ThongKeDoanhNghiep("Công ty ABC", "Phát triển phần mềm", 8, 3, 4.6, "Đang hợp tác"));
        danhSachDoanhNghiep.add(new ThongKeDoanhNghiep("Công ty XYZ", "Web/UI", 5, 2, 4.2, "Đang hợp tác"));
        danhSachDoanhNghiep.add(new ThongKeDoanhNghiep("Công ty MNO", "Data", 3, 1, 3.4, "Cần xem xét"));
        danhSachDoanhNghiep.add(new ThongKeDoanhNghiep("Công ty KTech", "Hạ tầng IT", 4, 1, 4.0, "Đang hợp tác"));
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("THỐNG KÊ & BÁO CÁO");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Phòng Đào tạo theo dõi tỷ lệ sinh viên có nơi thực tập, doanh nghiệp tiếp nhận và hiệu quả kết nối");
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

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setOpaque(false);

        mainPanel.add(createTopStatisticPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createTopStatisticPanel() {
        JPanel panel = new JPanel(new BorderLayout(18, 0));
        panel.setOpaque(false);

        JPanel badgePanel = new JPanel(new GridLayout(1, 4, 16, 0));
        badgePanel.setOpaque(false);

        lblTongSinhVien = new JLabel("0");
        lblDaCoNoiThucTap = new JLabel("0");
        lblChuaCoNoiThucTap = new JLabel("0");
        lblTiLeHoanThanh = new JLabel("0%");

        badgePanel.add(createStatisticCard("Tổng sinh viên", lblTongSinhVien, "Sinh viên thuộc đợt thực tập", Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createStatisticCard("Đã có nơi TT", lblDaCoNoiThucTap, "Sinh viên đã được nhận", Color.decode("#ECFDF5"), Color.decode("#059669")));
        badgePanel.add(createStatisticCard("Chưa có nơi TT", lblChuaCoNoiThucTap, "Cần hỗ trợ kết nối", Color.decode("#FEF2F2"), Color.decode("#DC2626")));
        badgePanel.add(createStatisticCard("Tỷ lệ hoàn thành", lblTiLeHoanThanh, "Tỷ lệ đã có nơi thực tập", Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        panel.add(badgePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0));
        centerPanel.setOpaque(false);

        JPanel leftPanel = new JPanel(new BorderLayout(0, 20));
        leftPanel.setOpaque(false);

        leftPanel.add(createChartAndNotePanel(), BorderLayout.NORTH);
        leftPanel.add(createNganhHocTableCard(), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout(0, 20));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(430, 520));

        rightPanel.add(createSearchCard(), BorderLayout.NORTH);
        rightPanel.add(createDoanhNghiepTableCard(), BorderLayout.CENTER);

        centerPanel.add(leftPanel, BorderLayout.CENTER);
        centerPanel.add(rightPanel, BorderLayout.EAST);

        return centerPanel;
    }

    private RoundedPanel createChartAndNotePanel() {
        RoundedPanel panel = new RoundedPanel(24, COLOR_CARD);
        panel.setLayout(new BorderLayout(18, 0));
        panel.setPreferredSize(new Dimension(740, 260));
        panel.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        chartPanel = new BarChartPanel();
        chartPanel.setPreferredSize(new Dimension(430, 210));

        JPanel notePanel = new JPanel(new BorderLayout(0, 10));
        notePanel.setOpaque(false);
        notePanel.setPreferredSize(new Dimension(300, 210));

        JLabel lblTitle = new JLabel("Nhận xét nhanh");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        txtNhanXet = new JTextArea();
        txtNhanXet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNhanXet.setForeground(COLOR_MUTED);
        txtNhanXet.setLineWrap(true);
        txtNhanXet.setWrapStyleWord(true);
        txtNhanXet.setEditable(false);
        txtNhanXet.setOpaque(false);

        notePanel.add(lblTitle, BorderLayout.NORTH);
        notePanel.add(txtNhanXet, BorderLayout.CENTER);

        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(notePanel, BorderLayout.EAST);

        return panel;
    }

    private RoundedPanel createSearchCard() {
        RoundedPanel card = new RoundedPanel(24, COLOR_CARD);
        card.setLayout(new BorderLayout(0, 14));
        card.setPreferredSize(new Dimension(430, 126));
        card.setBorder(BorderFactory.createEmptyBorder(20, 22, 20, 22));

        JLabel title = new JLabel("Tra cứu doanh nghiệp");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(COLOR_TEXT);

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);

        txtTuKhoa = new PlaceholderTextField("Nhập tên, lĩnh vực, trạng thái...");
        txtTuKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTuKhoa.setForeground(COLOR_TEXT);
        txtTuKhoa.setCaretColor(COLOR_TEAL);
        txtTuKhoa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(180, 40));

        btnTimKiem = createActionButton("Tìm", COLOR_NAVY, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnLamMoi);

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(buttonPanel, BorderLayout.EAST);

        card.add(title, BorderLayout.NORTH);
        card.add(searchPanel, BorderLayout.CENTER);

        return card;
    }

    private RoundedPanel createNganhHocTableCard() {
        RoundedPanel card = new RoundedPanel(24, COLOR_CARD);
        card.setLayout(new BorderLayout(0, 14));
        card.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel title = new JLabel("Bảng tổng hợp theo ngành học");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(COLOR_TEXT);

        String[] columns = {
            "Ngành học",
            "Tổng SV",
            "Đã có nơi TT",
            "Chưa có nơi TT",
            "Đơn ứng tuyển",
            "Đã nhận",
            "Từ chối",
            "Tỷ lệ"
        };

        nganhHocTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblNganhHoc = createStyledTable(nganhHocTableModel);
        tblNganhHoc.getColumnModel().getColumn(0).setPreferredWidth(170);
        tblNganhHoc.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblNganhHoc.getColumnModel().getColumn(2).setPreferredWidth(95);
        tblNganhHoc.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblNganhHoc.getColumnModel().getColumn(4).setPreferredWidth(95);
        tblNganhHoc.getColumnModel().getColumn(5).setPreferredWidth(75);
        tblNganhHoc.getColumnModel().getColumn(6).setPreferredWidth(75);
        tblNganhHoc.getColumnModel().getColumn(7).setPreferredWidth(70);

        JScrollPane scrollPane = new JScrollPane(tblNganhHoc);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        card.add(title, BorderLayout.NORTH);
        card.add(scrollPane, BorderLayout.CENTER);

        return card;
    }

    private RoundedPanel createDoanhNghiepTableCard() {
        RoundedPanel card = new RoundedPanel(24, COLOR_CARD);
        card.setLayout(new BorderLayout(0, 14));
        card.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JLabel title = new JLabel("Sinh viên tại từng doanh nghiệp");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(COLOR_TEXT);

        String[] columns = {
            "Doanh nghiệp",
            "Lĩnh vực",
            "SV",
            "Tin",
            "Điểm",
            "Trạng thái"
        };

        doanhNghiepTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDoanhNghiep = createStyledTable(doanhNghiepTableModel);
        tblDoanhNghiep.getColumnModel().getColumn(0).setPreferredWidth(110);
        tblDoanhNghiep.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblDoanhNghiep.getColumnModel().getColumn(2).setPreferredWidth(45);
        tblDoanhNghiep.getColumnModel().getColumn(3).setPreferredWidth(45);
        tblDoanhNghiep.getColumnModel().getColumn(4).setPreferredWidth(55);
        tblDoanhNghiep.getColumnModel().getColumn(5).setPreferredWidth(105);

        JScrollPane scrollPane = new JScrollPane(tblDoanhNghiep);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        card.add(title, BorderLayout.NORTH);
        card.add(scrollPane, BorderLayout.CENTER);

        return card;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(34);
        table.setForeground(COLOR_TEXT);
        table.setGridColor(Color.decode("#CBD5E1"));
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(COLOR_ROW_SELECTED);
        table.setSelectionForeground(COLOR_TEXT);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        table.getTableHeader().setPreferredSize(new Dimension(0, 38));
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }

    private void initEvents() {
        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblDoanhNghiep.clearSelection();
            loadDoanhNghiepToTable();
        });
    }

    private void loadNganhHocToTable() {
        nganhHocTableModel.setRowCount(0);

        for (ThongKeNganhHoc item : danhSachNganhHoc) {
            nganhHocTableModel.addRow(new Object[]{
                item.getNganhHoc(),
                item.getTongSinhVien(),
                item.getDaCoNoiThucTap(),
                item.getChuaCoNoiThucTap(),
                item.getTongUngTuyen(),
                item.getDaNhan(),
                item.getTuChoi(),
                item.getTiLeHoanThanh()
            });
        }
    }

    private void loadDoanhNghiepToTable() {
        loadDoanhNghiepToTable(danhSachDoanhNghiep);
    }

    private void loadDoanhNghiepToTable(List<ThongKeDoanhNghiep> data) {
        doanhNghiepTableModel.setRowCount(0);

        for (ThongKeDoanhNghiep item : data) {
            doanhNghiepTableModel.addRow(new Object[]{
                item.getTenDoanhNghiep(),
                item.getLinhVuc(),
                item.getSoSinhVien(),
                item.getTinDangMo(),
                String.format("%.1f", item.getDiemDanhGia()),
                item.getTrangThai()
            });
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDoanhNghiepToTable();
            return;
        }

        List<ThongKeDoanhNghiep> result = new ArrayList<>();

        for (ThongKeDoanhNghiep item : danhSachDoanhNghiep) {
            String searchText = (
                    item.getTenDoanhNghiep() + " "
                    + item.getLinhVuc() + " "
                    + item.getTrangThai()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(item);
            }
        }

        loadDoanhNghiepToTable(result);
    }

    private void updateBadges() {
        int tongSinhVien = 0;
        int daCoNoi = 0;
        int chuaCoNoi = 0;

        for (ThongKeNganhHoc item : danhSachNganhHoc) {
            tongSinhVien += item.getTongSinhVien();
            daCoNoi += item.getDaCoNoiThucTap();
            chuaCoNoi += item.getChuaCoNoiThucTap();
        }

        int tiLe = 0;
        if (tongSinhVien > 0) {
            tiLe = Math.round((daCoNoi * 100f) / tongSinhVien);
        }

        lblTongSinhVien.setText(String.valueOf(tongSinhVien));
        lblDaCoNoiThucTap.setText(String.valueOf(daCoNoi));
        lblChuaCoNoiThucTap.setText(String.valueOf(chuaCoNoi));
        lblTiLeHoanThanh.setText(tiLe + "%");

        chartPanel.setValues(daCoNoi, chuaCoNoi);
    }

    private void updateNhanXet() {
        txtNhanXet.setText(
                "• Tổng quan: Tỷ lệ sinh viên đã có nơi thực tập đang ở mức khá, nhưng vẫn còn nhóm cần hỗ trợ kết nối.\n\n"
                + "• Ngành cần chú ý: Mạng máy tính có tỷ lệ hoàn thành thấp, cần ưu tiên thêm doanh nghiệp phù hợp.\n\n"
                + "• Doanh nghiệp: Công ty ABC và XYZ có số lượng tiếp nhận tốt, có thể tiếp tục duy trì hợp tác.\n\n"
                + "• Cần xem xét: Các doanh nghiệp có điểm đánh giá dưới 3.5 cần được trao đổi lại trước kỳ thực tập tiếp theo."
        );
    }

    private RoundedPanel createStatisticCard(String title, JLabel valueLabel,
            String description, Color backgroundColor, Color textColor) {
        RoundedPanel card = new RoundedPanel(22, backgroundColor);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        card.setPreferredSize(new Dimension(210, 104));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitle.setForeground(textColor);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        valueLabel.setForeground(textColor);

        JLabel lblDescription = new JLabel(description);
        lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescription.setForeground(COLOR_MUTED);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(lblDescription, BorderLayout.SOUTH);

        return card;
    }

    private JButton createActionButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new HoverButton(text, backgroundColor, foregroundColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(foregroundColor);
        button.setPreferredSize(new Dimension(90, 38));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private class BarChartPanel extends JPanel {

        private int daCoNoi = 0;
        private int chuaCoNoi = 0;

        public BarChartPanel() {
            setOpaque(false);
        }

        public void setValues(int daCoNoi, int chuaCoNoi) {
            this.daCoNoi = daCoNoi;
            this.chuaCoNoi = chuaCoNoi;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2.setColor(COLOR_TEXT);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g2.drawString("Biểu đồ tình trạng thực tập", 0, 22);

            int chartTop = 52;
            int chartLeft = 46;
            int chartHeight = height - 92;
            int chartWidth = width - 80;

            int maxValue = Math.max(daCoNoi, chuaCoNoi);
            if (maxValue < 1) {
                maxValue = 1;
            }

            g2.setColor(Color.decode("#E2E8F0"));
            g2.setStroke(new BasicStroke(1.1f));

            for (int i = 0; i <= 4; i++) {
                int y = chartTop + chartHeight - (chartHeight * i / 4);
                g2.drawLine(chartLeft, y, chartLeft + chartWidth, y);
            }

            drawBar(g2, chartLeft + 70, chartTop, chartHeight, "Đã có nơi TT", daCoNoi, maxValue, COLOR_TEAL);
            drawBar(g2, chartLeft + 250, chartTop, chartHeight, "Chưa có nơi TT", chuaCoNoi, maxValue, Color.decode("#DC2626"));

            g2.dispose();
        }

        private void drawBar(Graphics2D g2, int x, int chartTop, int chartHeight,
                String label, int value, int maxValue, Color color) {
            int barWidth = 80;
            int barHeight = Math.round((value * 1f / maxValue) * (chartHeight - 22));
            int y = chartTop + chartHeight - barHeight;

            g2.setColor(color);
            g2.fillRoundRect(x, y, barWidth, barHeight, 18, 18);

            g2.setColor(COLOR_TEXT);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            String valueText = String.valueOf(value);
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(valueText, x + (barWidth - fm.stringWidth(valueText)) / 2, y - 8);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            fm = g2.getFontMetrics();
            g2.drawString(label, x + (barWidth - fm.stringWidth(label)) / 2, chartTop + chartHeight + 26);
        }
    }

    private static class ThongKeNganhHoc {

        private String nganhHoc;
        private int tongSinhVien;
        private int daCoNoiThucTap;
        private int chuaCoNoiThucTap;
        private int tongUngTuyen;
        private int daNhan;
        private int tuChoi;
        private String tiLeHoanThanh;

        public ThongKeNganhHoc(String nganhHoc, int tongSinhVien,
                int daCoNoiThucTap, int chuaCoNoiThucTap, int tongUngTuyen,
                int daNhan, int tuChoi, String tiLeHoanThanh) {
            this.nganhHoc = nganhHoc;
            this.tongSinhVien = tongSinhVien;
            this.daCoNoiThucTap = daCoNoiThucTap;
            this.chuaCoNoiThucTap = chuaCoNoiThucTap;
            this.tongUngTuyen = tongUngTuyen;
            this.daNhan = daNhan;
            this.tuChoi = tuChoi;
            this.tiLeHoanThanh = tiLeHoanThanh;
        }

        public String getNganhHoc() {
            return nganhHoc;
        }

        public int getTongSinhVien() {
            return tongSinhVien;
        }

        public int getDaCoNoiThucTap() {
            return daCoNoiThucTap;
        }

        public int getChuaCoNoiThucTap() {
            return chuaCoNoiThucTap;
        }

        public int getTongUngTuyen() {
            return tongUngTuyen;
        }

        public int getDaNhan() {
            return daNhan;
        }

        public int getTuChoi() {
            return tuChoi;
        }

        public String getTiLeHoanThanh() {
            return tiLeHoanThanh;
        }
    }

    private static class ThongKeDoanhNghiep {

        private String tenDoanhNghiep;
        private String linhVuc;
        private int soSinhVien;
        private int tinDangMo;
        private double diemDanhGia;
        private String trangThai;

        public ThongKeDoanhNghiep(String tenDoanhNghiep, String linhVuc,
                int soSinhVien, int tinDangMo, double diemDanhGia, String trangThai) {
            this.tenDoanhNghiep = tenDoanhNghiep;
            this.linhVuc = linhVuc;
            this.soSinhVien = soSinhVien;
            this.tinDangMo = tinDangMo;
            this.diemDanhGia = diemDanhGia;
            this.trangThai = trangThai;
        }

        public String getTenDoanhNghiep() {
            return tenDoanhNghiep;
        }

        public String getLinhVuc() {
            return linhVuc;
        }

        public int getSoSinhVien() {
            return soSinhVien;
        }

        public int getTinDangMo() {
            return tinDangMo;
        }

        public double getDiemDanhGia() {
            return diemDanhGia;
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
