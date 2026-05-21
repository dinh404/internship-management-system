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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.TaiKhoan;
import model.TinTuyenDung;
import service.TinTuyenDungService;
import util.MessageUtil;

public class SinhVienXemTinPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblTinTuyenDung;
    private DefaultTableModel tableModel;

    private JLabel lblTinDangMo;
    private JLabel lblTinPhuHop;
    private JLabel lblDaUngTuyen;

    private JLabel lblMaTin;
    private JLabel lblViTri;
    private JLabel lblDoanhNghiep;
    private JLabel lblDiaDiem;
    private JLabel lblSoLuong;
    private JLabel lblHanNop;
    private JLabel lblTrangThai;

    private JButton btnTimKiem;
    private JButton btnUngTuyen;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final TinTuyenDungService tinTuyenDungService = new TinTuyenDungService();
    private final Set<String> danhSachMaTinDaUngTuyen = new HashSet<>();

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
    private final Color COLOR_GRAY_BUTTON = Color.decode("#F1F5F9");

    public SinhVienXemTinPanel(TaiKhoan currentUser) {
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

        JLabel lblTitle = new JLabel("XEM TIN TUYỂN DỤNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Sinh viên tra cứu vị trí thực tập đang mở và gửi hồ sơ ứng tuyển phù hợp");
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

        JLabel lblTitle = new JLabel("Chi tiết tin tuyển dụng");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        lblMaTin = createValueLabel();
        lblViTri = createValueLabel();
        lblDoanhNghiep = createValueLabel();
        lblDiaDiem = createValueLabel();
        lblSoLuong = createValueLabel();
        lblHanNop = createValueLabel();
        lblTrangThai = createValueLabel();

        addInfoRow(infoPanel, "Mã tin", lblMaTin, 0);
        addInfoRow(infoPanel, "Vị trí", lblViTri, 1);
        addInfoRow(infoPanel, "Doanh nghiệp", lblDoanhNghiep, 2);
        addInfoRow(infoPanel, "Địa điểm", lblDiaDiem, 3);
        addInfoRow(infoPanel, "Số lượng", lblSoLuong, 4);
        addInfoRow(infoPanel, "Hạn nộp", lblHanNop, 5);
        addInfoRow(infoPanel, "Trạng thái", lblTrangThai, 6);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        buttonPanel.setOpaque(false);

        btnUngTuyen = createActionButton("Ứng tuyển", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnUngTuyen);
        buttonPanel.add(btnLamMoi);

        detailCard.add(lblTitle, BorderLayout.NORTH);
        detailCard.add(infoPanel, BorderLayout.CENTER);
        detailCard.add(buttonPanel, BorderLayout.SOUTH);

        return detailCard;
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
        badgePanel.setPreferredSize(new Dimension(430, 54));

        lblTinDangMo = new JLabel("0");
        lblTinPhuHop = new JLabel("0");
        lblDaUngTuyen = new JLabel("0");

        badgePanel.add(createBadge("Tin đang mở", lblTinDangMo, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Tin phù hợp", lblTinPhuHop, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Đã ứng tuyển", lblDaUngTuyen, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(420, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập vị trí, doanh nghiệp, địa điểm...");
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
        tblTinTuyenDung.getColumnModel().getColumn(1).setPreferredWidth(170);
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
        btnTimKiem.addActionListener(e -> handleTimKiem());
        txtTuKhoa.addActionListener(e -> handleTimKiem());

        btnLamMoi.addActionListener(e -> {
            txtTuKhoa.setText("");
            tblTinTuyenDung.clearSelection();
            clearDetail();
            loadDataToTable();
        });

        btnUngTuyen.addActionListener(e -> handleUngTuyen());

        tblTinTuyenDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
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
        int tinDangMo = 0;

        for (TinTuyenDung tin : tinTuyenDungService.getAll()) {
            if ("Đang mở".equalsIgnoreCase(tin.getTrangThai())) {
                tinDangMo++;
            }
        }

        lblTinDangMo.setText(String.valueOf(tinDangMo));
        lblTinPhuHop.setText(String.valueOf(tinDangMo));
        lblDaUngTuyen.setText(String.valueOf(danhSachMaTinDaUngTuyen.size()));
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim();
        List<TinTuyenDung> result = tinTuyenDungService.search(keyword);
        loadDataToTable(result);
    }

    private void handleUngTuyen() {
        int selectedRow = tblTinTuyenDung.getSelectedRow();

        if (selectedRow < 0) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần ứng tuyển!");
            return;
        }

        String maTin = tableModel.getValueAt(selectedRow, 0).toString();
        String viTri = tableModel.getValueAt(selectedRow, 1).toString();
        String doanhNghiep = tableModel.getValueAt(selectedRow, 2).toString();
        String trangThai = tableModel.getValueAt(selectedRow, 6).toString();

        if (!"Đang mở".equalsIgnoreCase(trangThai)) {
            MessageUtil.showError(this, "Tin tuyển dụng này hiện không còn mở ứng tuyển!");
            return;
        }

        if (danhSachMaTinDaUngTuyen.contains(maTin)) {
            MessageUtil.showError(this, "Bạn đã ứng tuyển tin tuyển dụng này rồi!");
            return;
        }

        boolean confirm = MessageUtil.showConfirm(
                this,
                "Xác nhận ứng tuyển vị trí " + viTri + " tại " + doanhNghiep + "?"
        );

        if (!confirm) {
            return;
        }

        danhSachMaTinDaUngTuyen.add(maTin);
        updateBadges();

        MessageUtil.showInfo(
                this,
                "Ứng tuyển thành công! Hồ sơ của " + currentUser.getTenHienThi()
                + " đã được ghi nhận ở trạng thái Chờ duyệt."
        );
    }

    private void fillDetailFromSelectedRow() {
        int selectedRow = tblTinTuyenDung.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        lblMaTin.setText(tableModel.getValueAt(selectedRow, 0).toString());
        lblViTri.setText(tableModel.getValueAt(selectedRow, 1).toString());
        lblDoanhNghiep.setText(tableModel.getValueAt(selectedRow, 2).toString());
        lblDiaDiem.setText(tableModel.getValueAt(selectedRow, 3).toString());
        lblSoLuong.setText(tableModel.getValueAt(selectedRow, 4).toString());
        lblHanNop.setText(tableModel.getValueAt(selectedRow, 5).toString());
        lblTrangThai.setText(tableModel.getValueAt(selectedRow, 6).toString());
    }

    private void clearDetail() {
        lblMaTin.setText("Chưa chọn");
        lblViTri.setText("Chưa chọn");
        lblDoanhNghiep.setText("Chưa chọn");
        lblDiaDiem.setText("Chưa chọn");
        lblSoLuong.setText("Chưa chọn");
        lblHanNop.setText("Chưa chọn");
        lblTrangThai.setText("Chưa chọn");
    }

    private JLabel createValueLabel() {
        JLabel label = new JLabel("Chưa chọn");
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(COLOR_TEXT);
        label.setOpaque(true);
        label.setBackground(Color.decode("#F1F5F9"));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return label;
    }

    private void addInfoRow(JPanel panel, String labelText, JLabel valueLabel, int row) {
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
        panel.add(valueLabel, gbc);
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