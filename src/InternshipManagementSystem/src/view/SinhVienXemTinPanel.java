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
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class SinhVienXemTinPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblTinTuyenDung;
    private DefaultTableModel tableModel;

    private JLabel lblTinDangMo;
    private JLabel lblTinPhuHop;
    private JLabel lblDaUngTuyen;
    private JLabel lblDaLuu;

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

    private JButton btnTimKiem;
    private JButton btnXemChiTiet;
    private JButton btnLuuTin;
    private JButton btnUngTuyen;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final TinTuyenDungService tinTuyenDungService = new TinTuyenDungService();
    private final Set<String> danhSachMaTinDaUngTuyen = new HashSet<>();
    private final Set<String> danhSachMaTinDaLuu = new HashSet<>();

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
    private final Color COLOR_ORANGE = Color.decode("#C2410C");

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

        JLabel lblSubtitle = new JLabel("Sinh viên tra cứu vị trí thực tập, xem chi tiết đầy đủ, lưu tin và nộp CV ứng tuyển");
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
        detailCard.setPreferredSize(new Dimension(420, 600));
        detailCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Chi tiết tin tuyển dụng");
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

        txtMoTaCongViec = createReadOnlyTextArea(76);
        txtYeuCauKyNang = createReadOnlyTextArea(76);
        txtQuyenLoi = createReadOnlyTextArea(70);

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
        detailScrollPane.setOpaque(false);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setOpaque(false);

        btnXemChiTiet = createActionButton("Xem chi tiết", COLOR_NAVY, Color.WHITE);
        btnLuuTin = createActionButton("Lưu tin", Color.decode("#F97316"), Color.WHITE);
        btnUngTuyen = createActionButton("Ứng tuyển", COLOR_TEAL, Color.WHITE);
        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);

        buttonPanel.add(btnXemChiTiet);
        buttonPanel.add(btnLuuTin);
        buttonPanel.add(btnUngTuyen);
        buttonPanel.add(btnLamMoi);

        detailCard.add(lblTitle, BorderLayout.NORTH);
        detailCard.add(detailScrollPane, BorderLayout.CENTER);
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

        JPanel badgePanel = new JPanel(new GridLayout(1, 4, 10, 0));
        badgePanel.setOpaque(false);
        badgePanel.setPreferredSize(new Dimension(560, 60));

        lblTinDangMo = new JLabel("0");
        lblTinPhuHop = new JLabel("0");
        lblDaUngTuyen = new JLabel("0");
        lblDaLuu = new JLabel("0");

        badgePanel.add(createBadge("Tin đang mở", lblTinDangMo, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Tin phù hợp", lblTinPhuHop, Color.decode("#ECFDF5"), COLOR_TEAL));
        badgePanel.add(createBadge("Đã ứng tuyển", lblDaUngTuyen, Color.decode("#FFEDD5"), COLOR_ORANGE));
        badgePanel.add(createBadge("Đã lưu", lblDaLuu, Color.decode("#F5F3FF"), Color.decode("#6D28D9")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(420, 42));

        txtTuKhoa = new PlaceholderTextField("Nhập vị trí, doanh nghiệp, địa điểm, kỹ năng...");
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

        tblTinTuyenDung = new JTable(tableModel);
        tblTinTuyenDung.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblTinTuyenDung.setRowHeight(38);
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
        tblTinTuyenDung.getTableHeader().setPreferredSize(new Dimension(0, 42));
        tblTinTuyenDung.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblTinTuyenDung.getColumnCount(); i++) {
            tblTinTuyenDung.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblTinTuyenDung.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblTinTuyenDung.getColumnModel().getColumn(1).setPreferredWidth(140);
        tblTinTuyenDung.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblTinTuyenDung.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblTinTuyenDung.getColumnModel().getColumn(4).setPreferredWidth(95);
        tblTinTuyenDung.getColumnModel().getColumn(5).setPreferredWidth(160);
        tblTinTuyenDung.getColumnModel().getColumn(6).setPreferredWidth(90);

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

        btnXemChiTiet.addActionListener(e -> showChiTietDialog());
        btnLuuTin.addActionListener(e -> handleLuuTin());
        btnUngTuyen.addActionListener(e -> handleUngTuyen());

        tblTinTuyenDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });
    }

    private void loadDataToTable() {
        loadDataToTable(tinTuyenDungService.getTinSinhVienCoTheXem());
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

        loadDataToTable(tinTuyenDungService.searchTinSinhVienCoTheXem(keyword));
    }

    private void handleLuuTin() {
        TinTuyenDung tin = getSelectedTin();

        if (tin == null) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần lưu!");
            return;
        }

        String maTin = tin.getMaTinTuyenDung();

        if (danhSachMaTinDaLuu.contains(maTin)) {
            MessageUtil.showInfo(this, "Tin tuyển dụng này đã có trong danh sách yêu thích!");
            return;
        }

        danhSachMaTinDaLuu.add(maTin);
        updateBadges();
        MessageUtil.showInfo(this, "Đã lưu tin tuyển dụng để xem lại sau!");
    }

    private void handleUngTuyen() {
        TinTuyenDung tin = getSelectedTin();

        if (tin == null) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần ứng tuyển!");
            return;
        }

        if (!isTinDangMo(tin)) {
            MessageUtil.showError(this, "Tin tuyển dụng này hiện không còn nhận hồ sơ!");
            return;
        }

        String maTin = tin.getMaTinTuyenDung();

        if (danhSachMaTinDaUngTuyen.contains(maTin)) {
            MessageUtil.showError(this, "Bạn đã ứng tuyển tin tuyển dụng này rồi!");
            return;
        }

        showUngTuyenDialog(tin);
    }

    private void showUngTuyenDialog(TinTuyenDung tin) {
        JComboBox<String> cboCV = new JComboBox<>(new String[]{
            "CV Java Backend Intern",
            "CV Frontend Intern",
            "CV Data Analyst Intern",
            "Tải lên CV mới..."
        });

        JTextArea txtGhiChu = new JTextArea(4, 26);
        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addDialogRow(panel, "Vị trí", new JLabel(tin.getTenViTri()), 0);
        addDialogRow(panel, "Doanh nghiệp", new JLabel(tin.getTenDoanhNghiep()), 1);
        addDialogRow(panel, "Chọn CV", cboCV, 2);
        addDialogRow(panel, "Ghi chú", new JScrollPane(txtGhiChu), 3);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Nộp hồ sơ ứng tuyển",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String selectedCV = cboCV.getSelectedItem().toString();

        if ("Tải lên CV mới...".equals(selectedCV)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn file CV");

            int chooseResult = fileChooser.showOpenDialog(this);

            if (chooseResult != JFileChooser.APPROVE_OPTION) {
                MessageUtil.showInfo(this, "Bạn chưa chọn file CV để tải lên.");
                return;
            }

            File selectedFile = fileChooser.getSelectedFile();
            selectedCV = selectedFile.getName();
        }

        danhSachMaTinDaUngTuyen.add(tin.getMaTinTuyenDung());
        updateBadges();

        MessageUtil.showInfo(
                this,
                "Ứng tuyển thành công với CV: " + selectedCV
                + ". Hệ thống đã ghi nhận hồ sơ và gửi thông báo mô phỏng đến doanh nghiệp."
        );
    }

    private void addDialogRow(JPanel panel, String labelText, java.awt.Component component, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(COLOR_TEXT);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 12, 12);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 12, 0);
        panel.add(component, gbc);
    }

    private void showChiTietDialog() {
        TinTuyenDung tin = getSelectedTin();

        if (tin == null) {
            MessageUtil.showError(this, "Vui lòng chọn tin tuyển dụng cần xem chi tiết!");
            return;
        }

        JTextArea txtDetail = new JTextArea();
        txtDetail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDetail.setEditable(false);
        txtDetail.setLineWrap(true);
        txtDetail.setWrapStyleWord(true);
        txtDetail.setText(
                "Mã tin: " + tin.getMaTinTuyenDung() + "\n"
                + "Vị trí: " + tin.getTenViTri() + "\n"
                + "Doanh nghiệp: " + tin.getTenDoanhNghiep() + "\n"
                + "Địa điểm: " + tin.getDiaDiem() + "\n"
                + "Số lượng: " + tin.getSoLuong() + "\n"
                + "Hạn nộp: " + tin.getHanNop() + "\n"
                + "Trạng thái: " + tin.getTrangThai() + "\n"
                + "Mức lương/Phụ cấp: " + tin.getMucLuong() + "\n"
                + "Hình thức làm việc: " + tin.getHinhThucLamViec() + "\n"
                + "Ngành phù hợp: " + tin.getNganhPhuHop() + "\n\n"
                + "Mô tả công việc:\n" + tin.getMoTaCongViec() + "\n\n"
                + "Yêu cầu kỹ năng:\n" + tin.getYeuCauKyNang() + "\n\n"
                + "Quyền lợi:\n" + tin.getQuyenLoi()
        );

        JScrollPane scrollPane = new JScrollPane(txtDetail);
        scrollPane.setPreferredSize(new Dimension(560, 430));

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Chi tiết tin tuyển dụng",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    private boolean isTinDangMo(TinTuyenDung tin) {
        return "Đang mở".equalsIgnoreCase(tin.getTrangThai())
                || "Đã duyệt".equalsIgnoreCase(tin.getTrangThai());
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

        if (tin == null) {
            return;
        }

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
    }

    private void updateBadges() {
        List<TinTuyenDung> all = tinTuyenDungService.getTinSinhVienCoTheXem();
        int tinDangMo = 0;

        for (TinTuyenDung tin : all) {
            if (isTinDangMo(tin)) {
                tinDangMo++;
            }
        }

        lblTinDangMo.setText(String.valueOf(tinDangMo));
        lblTinPhuHop.setText(String.valueOf(Math.min(3, all.size())));
        lblDaUngTuyen.setText(String.valueOf(danhSachMaTinDaUngTuyen.size()));
        lblDaLuu.setText(String.valueOf(danhSachMaTinDaLuu.size()));
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
        textArea.setPreferredSize(new Dimension(330, height));
        return textArea;
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
        badge.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        badge.setPreferredSize(new Dimension(128, 60));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitle.setForeground(textColor);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
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
