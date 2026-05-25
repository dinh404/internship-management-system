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

public class PdtDoanhNghiepLienKetPanel extends JPanel {

    private JTextField txtTuKhoa;

    private JTable tblDoanhNghiep;
    private JTable tblDanhGia;
    private DefaultTableModel doanhNghiepTableModel;
    private DefaultTableModel danhGiaTableModel;

    private JLabel lblTongDoanhNghiep;
    private JLabel lblDangTuyen;
    private JLabel lblDanhGiaTot;
    private JLabel lblCanXemXet;

    private JLabel lblMaDoanhNghiep;
    private JLabel lblTenDoanhNghiep;
    private JLabel lblLinhVuc;
    private JLabel lblDiaChi;
    private JLabel lblEmail;
    private JLabel lblSoDienThoai;
    private JLabel lblTinDangMo;
    private JLabel lblSinhVienThucTap;
    private JLabel lblDiemDanhGia;
    private JLabel lblTrangThaiHopTac;
    private JTextArea txtNhanXetTongHop;

    private JButton btnTimKiem;
    private JButton btnLamMoi;

    private final TaiKhoan currentUser;
    private final List<DoanhNghiepLienKet> danhSachDoanhNghiep = new ArrayList<>();
    private final List<DanhGiaDoanhNghiep> danhSachDanhGia = new ArrayList<>();

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

    public PdtDoanhNghiepLienKetPanel(TaiKhoan currentUser) {
        this.currentUser = currentUser;
        initData();
        initUI();
        initEvents();
        loadDoanhNghiepToTable();
        loadDanhGiaToTable();
        clearDetail();
    }

    private void initData() {
        danhSachDoanhNghiep.add(new DoanhNghiepLienKet(
                "DN001",
                "Công ty ABC",
                "Phát triển phần mềm",
                "TP. Hồ Chí Minh",
                "hr@abc.com",
                "0281234567",
                3,
                8,
                4.6,
                "Đang hợp tác",
                "Doanh nghiệp có mentor hỗ trợ tốt, quy trình tiếp nhận thực tập rõ ràng, phù hợp tiếp tục hợp tác."
        ));

        danhSachDoanhNghiep.add(new DoanhNghiepLienKet(
                "DN002",
                "Công ty XYZ",
                "Thiết kế giao diện và Web",
                "Hà Nội",
                "contact@xyz.vn",
                "0249876543",
                2,
                5,
                4.2,
                "Đang hợp tác",
                "Môi trường trẻ, phản hồi hồ sơ nhanh, phù hợp nhóm sinh viên frontend và UI/UX."
        ));

        danhSachDoanhNghiep.add(new DoanhNghiepLienKet(
                "DN003",
                "Công ty MNO",
                "Phân tích dữ liệu",
                "Đà Nẵng",
                "talent@mno.vn",
                "0236123456",
                1,
                3,
                3.4,
                "Cần xem xét",
                "Một số phản hồi cho thấy mentor còn ít thời gian hỗ trợ, cần trao đổi lại trước đợt hợp tác tiếp theo."
        ));

        danhSachDoanhNghiep.add(new DoanhNghiepLienKet(
                "DN004",
                "Công ty KTech",
                "Hạ tầng và hỗ trợ IT",
                "Cần Thơ",
                "intern@ktech.vn",
                "0292123456",
                1,
                4,
                4.0,
                "Đang hợp tác",
                "Phù hợp sinh viên mảng mạng máy tính, hỗ trợ dấu mộc và lịch làm việc linh hoạt."
        ));

        danhSachDanhGia.add(new DanhGiaDoanhNghiep("SV001", "Nguyễn Văn A", "Công ty ABC", "Mentor nhiệt tình, công việc sát chuyên ngành.", "4.8"));
        danhSachDanhGia.add(new DanhGiaDoanhNghiep("SV002", "Trần Thị B", "Công ty XYZ", "Quy trình phỏng vấn rõ, môi trường thân thiện.", "4.3"));
        danhSachDanhGia.add(new DanhGiaDoanhNghiep("SV003", "Lê Văn C", "Công ty MNO", "Công việc ổn nhưng phản hồi từ mentor còn chậm.", "3.2"));
        danhSachDanhGia.add(new DanhGiaDoanhNghiep("SV005", "Hoàng Minh E", "Công ty KTech", "Được hướng dẫn tốt về quy trình hỗ trợ kỹ thuật.", "4.0"));
    }

    private void initUI() {
        setLayout(new BorderLayout(24, 20));
        setBackground(COLOR_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createDetailCard(), BorderLayout.WEST);
        add(createContentCard(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("DOANH NGHIỆP LIÊN KẾT");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Phòng Đào tạo theo dõi doanh nghiệp hợp tác, tin tuyển dụng đang mở và đánh giá sau thực tập");
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
        detailCard.setPreferredSize(new Dimension(370, 590));
        detailCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Chi tiết doanh nghiệp");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblNote = new JLabel("<html><span style='color:#64748B;'>Dữ liệu phục vụ theo dõi hợp tác</span></html>");
        lblNote.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblNote.setForeground(COLOR_MUTED);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.anchor = GridBagConstraints.WEST;
        titleGbc.fill = GridBagConstraints.HORIZONTAL;
        titleGbc.weightx = 1.0;

        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(0, 0, 4, 0);
        titlePanel.add(lblTitle, titleGbc);

        titleGbc.gridy = 1;
        titleGbc.insets = new Insets(0, 0, 0, 0);
        titlePanel.add(lblNote, titleGbc);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(14, 0, 10, 0));

        lblMaDoanhNghiep = createValueLabel();
        lblTenDoanhNghiep = createValueLabel();
        lblLinhVuc = createValueLabel();
        lblDiaChi = createValueLabel();
        lblEmail = createValueLabel();
        lblSoDienThoai = createValueLabel();
        lblTinDangMo = createValueLabel();
        lblSinhVienThucTap = createValueLabel();
        lblDiemDanhGia = createValueLabel();
        lblTrangThaiHopTac = createValueLabel();

        addInfoRow(infoPanel, "Mã doanh nghiệp", lblMaDoanhNghiep, 0);
        addInfoRow(infoPanel, "Tên doanh nghiệp", lblTenDoanhNghiep, 1);
        addInfoRow(infoPanel, "Lĩnh vực", lblLinhVuc, 2);
        addInfoRow(infoPanel, "Địa chỉ", lblDiaChi, 3);
        addInfoRow(infoPanel, "Email", lblEmail, 4);
        addInfoRow(infoPanel, "Số điện thoại", lblSoDienThoai, 5);
        addInfoRow(infoPanel, "Tin đang mở", lblTinDangMo, 6);
        addInfoRow(infoPanel, "Sinh viên thực tập", lblSinhVienThucTap, 7);
        addInfoRow(infoPanel, "Điểm đánh giá", lblDiemDanhGia, 8);
        addInfoRow(infoPanel, "Trạng thái hợp tác", lblTrangThaiHopTac, 9);

        txtNhanXetTongHop = new JTextArea();
        txtNhanXetTongHop.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNhanXetTongHop.setForeground(COLOR_MUTED);
        txtNhanXetTongHop.setLineWrap(true);
        txtNhanXetTongHop.setWrapStyleWord(true);
        txtNhanXetTongHop.setEditable(false);
        txtNhanXetTongHop.setOpaque(true);
        txtNhanXetTongHop.setBackground(Color.decode("#F8FAFC"));
        txtNhanXetTongHop.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        txtNhanXetTongHop.setPreferredSize(new Dimension(310, 88));

        JPanel bottomPanel = new JPanel(new BorderLayout(0, 8));
        bottomPanel.setOpaque(false);

        JLabel lblNhanXet = new JLabel("Nhận xét tổng hợp");
        lblNhanXet.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNhanXet.setForeground(COLOR_TEXT);

        bottomPanel.add(lblNhanXet, BorderLayout.NORTH);
        bottomPanel.add(txtNhanXetTongHop, BorderLayout.CENTER);

        detailCard.add(titlePanel, BorderLayout.NORTH);
        detailCard.add(infoPanel, BorderLayout.CENTER);
        detailCard.add(bottomPanel, BorderLayout.SOUTH);

        return detailCard;
    }

    private RoundedPanel createContentCard() {
        RoundedPanel contentCard = new RoundedPanel(24, COLOR_CARD);
        contentCard.setLayout(new BorderLayout(0, 18));
        contentCard.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);

        JPanel badgePanel = new JPanel(new GridLayout(1, 4, 10, 0));
        badgePanel.setOpaque(false);
        badgePanel.setPreferredSize(new Dimension(560, 64));

        lblTongDoanhNghiep = new JLabel("0");
        lblDangTuyen = new JLabel("0");
        lblDanhGiaTot = new JLabel("0");
        lblCanXemXet = new JLabel("0");

        badgePanel.add(createBadge("Tổng DN", lblTongDoanhNghiep, Color.decode("#EFF6FF"), COLOR_NAVY));
        badgePanel.add(createBadge("Đang tuyển", lblDangTuyen, Color.decode("#ECFDF5"), Color.decode("#059669")));
        badgePanel.add(createBadge("Đánh giá tốt", lblDanhGiaTot, Color.decode("#E0F2FE"), Color.decode("#0369A1")));
        badgePanel.add(createBadge("Cần xem xét", lblCanXemXet, Color.decode("#FFEDD5"), Color.decode("#C2410C")));

        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(390, 42));

        txtTuKhoa = new PlaceholderTextField("Tìm tên, lĩnh vực, địa điểm, trạng thái...");
        txtTuKhoa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTuKhoa.setForeground(COLOR_TEXT);
        txtTuKhoa.setCaretColor(COLOR_TEAL);
        txtTuKhoa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_INPUT_BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        btnTimKiem = createActionButton("Tìm kiếm", COLOR_NAVY, Color.WHITE);
        btnTimKiem.setPreferredSize(new Dimension(110, 42));

        btnLamMoi = createActionButton("Làm mới", COLOR_GRAY_BUTTON, COLOR_TEXT);
        btnLamMoi.setPreferredSize(new Dimension(98, 42));

        JPanel searchButtonPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        searchButtonPanel.setOpaque(false);
        searchButtonPanel.add(btnTimKiem);
        searchButtonPanel.add(btnLamMoi);

        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);
        searchPanel.add(searchButtonPanel, BorderLayout.EAST);

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

        JPanel tableArea = new JPanel(new GridLayout(2, 1, 0, 18));
        tableArea.setOpaque(false);

        tableArea.add(createDoanhNghiepTablePanel());
        tableArea.add(createDanhGiaTablePanel());

        contentCard.add(topPanel, BorderLayout.NORTH);
        contentCard.add(tableArea, BorderLayout.CENTER);

        return contentCard;
    }

    private JPanel createDoanhNghiepTablePanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 8));
        wrapper.setOpaque(false);

        JLabel title = new JLabel("Danh sách doanh nghiệp liên kết");
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setForeground(COLOR_TEXT);

        String[] columns = {
            "Mã DN",
            "Tên doanh nghiệp",
            "Lĩnh vực",
            "Địa điểm",
            "Tin mở",
            "SV thực tập",
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
        tblDoanhNghiep.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblDoanhNghiep.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDoanhNghiep.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDoanhNghiep.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblDoanhNghiep.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblDoanhNghiep.getColumnModel().getColumn(5).setPreferredWidth(90);
        tblDoanhNghiep.getColumnModel().getColumn(6).setPreferredWidth(70);
        tblDoanhNghiep.getColumnModel().getColumn(7).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(tblDoanhNghiep);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
    }

    private JPanel createDanhGiaTablePanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 8));
        wrapper.setOpaque(false);

        JLabel title = new JLabel("Đánh giá doanh nghiệp từ sinh viên sau thực tập");
        title.setFont(new Font("Segoe UI", Font.BOLD, 15));
        title.setForeground(COLOR_TEXT);

        String[] columns = {
            "Mã SV",
            "Sinh viên",
            "Doanh nghiệp",
            "Nhận xét",
            "Điểm"
        };

        danhGiaTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDanhGia = createStyledTable(danhGiaTableModel);
        tblDanhGia.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblDanhGia.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblDanhGia.getColumnModel().getColumn(2).setPreferredWidth(130);
        tblDanhGia.getColumnModel().getColumn(3).setPreferredWidth(360);
        tblDanhGia.getColumnModel().getColumn(4).setPreferredWidth(60);

        JScrollPane scrollPane = new JScrollPane(tblDanhGia);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        wrapper.add(title, BorderLayout.NORTH);
        wrapper.add(scrollPane, BorderLayout.CENTER);

        return wrapper;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(34);
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
            clearDetail();
            loadDoanhNghiepToTable();
            loadDanhGiaToTable();
        });

        tblDoanhNghiep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillDetailFromSelectedRow();
            }
        });
    }

    private void loadDoanhNghiepToTable() {
        loadDoanhNghiepToTable(danhSachDoanhNghiep);
    }

    private void loadDoanhNghiepToTable(List<DoanhNghiepLienKet> data) {
        doanhNghiepTableModel.setRowCount(0);

        for (DoanhNghiepLienKet item : data) {
            doanhNghiepTableModel.addRow(new Object[]{
                item.getMaDoanhNghiep(),
                item.getTenDoanhNghiep(),
                item.getLinhVuc(),
                item.getDiaChi(),
                item.getTinDangMo(),
                item.getSinhVienThucTap(),
                String.format("%.1f", item.getDiemDanhGia()),
                item.getTrangThaiHopTac()
            });
        }

        updateBadges();
    }

    private void loadDanhGiaToTable() {
        danhGiaTableModel.setRowCount(0);

        for (DanhGiaDoanhNghiep item : danhSachDanhGia) {
            danhGiaTableModel.addRow(new Object[]{
                item.getMaSinhVien(),
                item.getHoTen(),
                item.getDoanhNghiep(),
                item.getNhanXet(),
                item.getDiem()
            });
        }
    }

    private void handleTimKiem() {
        String keyword = txtTuKhoa.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            loadDoanhNghiepToTable();
            return;
        }

        List<DoanhNghiepLienKet> result = new ArrayList<>();

        for (DoanhNghiepLienKet item : danhSachDoanhNghiep) {
            String searchText = (
                    item.getMaDoanhNghiep() + " "
                    + item.getTenDoanhNghiep() + " "
                    + item.getLinhVuc() + " "
                    + item.getDiaChi() + " "
                    + item.getEmail() + " "
                    + item.getSoDienThoai() + " "
                    + item.getTrangThaiHopTac()
            ).toLowerCase();

            if (searchText.contains(keyword)) {
                result.add(item);
            }
        }

        loadDoanhNghiepToTable(result);
    }

    private void updateBadges() {
        int tong = danhSachDoanhNghiep.size();
        int dangTuyen = 0;
        int danhGiaTot = 0;
        int canXemXet = 0;

        for (DoanhNghiepLienKet item : danhSachDoanhNghiep) {
            if (item.getTinDangMo() > 0) {
                dangTuyen++;
            }

            if (item.getDiemDanhGia() >= 4.0) {
                danhGiaTot++;
            }

            if ("Cần xem xét".equalsIgnoreCase(item.getTrangThaiHopTac())) {
                canXemXet++;
            }
        }

        lblTongDoanhNghiep.setText(String.valueOf(tong));
        lblDangTuyen.setText(String.valueOf(dangTuyen));
        lblDanhGiaTot.setText(String.valueOf(danhGiaTot));
        lblCanXemXet.setText(String.valueOf(canXemXet));
    }

    private void fillDetailFromSelectedRow() {
        int selectedRow = tblDoanhNghiep.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String maDoanhNghiep = doanhNghiepTableModel.getValueAt(selectedRow, 0).toString();
        DoanhNghiepLienKet item = findByMaDoanhNghiep(maDoanhNghiep);

        if (item == null) {
            return;
        }

        lblMaDoanhNghiep.setText(item.getMaDoanhNghiep());
        lblTenDoanhNghiep.setText(item.getTenDoanhNghiep());
        lblLinhVuc.setText(item.getLinhVuc());
        lblDiaChi.setText(item.getDiaChi());
        lblEmail.setText(item.getEmail());
        lblSoDienThoai.setText(item.getSoDienThoai());
        lblTinDangMo.setText(String.valueOf(item.getTinDangMo()));
        lblSinhVienThucTap.setText(String.valueOf(item.getSinhVienThucTap()));
        lblDiemDanhGia.setText(String.format("%.1f / 5.0", item.getDiemDanhGia()));
        lblTrangThaiHopTac.setText(item.getTrangThaiHopTac());
        txtNhanXetTongHop.setText(item.getNhanXetTongHop());
    }

    private void clearDetail() {
        lblMaDoanhNghiep.setText("Chưa chọn");
        lblTenDoanhNghiep.setText("Chưa chọn");
        lblLinhVuc.setText("Chưa chọn");
        lblDiaChi.setText("Chưa chọn");
        lblEmail.setText("Chưa chọn");
        lblSoDienThoai.setText("Chưa chọn");
        lblTinDangMo.setText("Chưa chọn");
        lblSinhVienThucTap.setText("Chưa chọn");
        lblDiemDanhGia.setText("Chưa chọn");
        lblTrangThaiHopTac.setText("Chưa chọn");
        txtNhanXetTongHop.setText("Chọn một doanh nghiệp trong bảng để xem nhận xét tổng hợp.");
    }

    private DoanhNghiepLienKet findByMaDoanhNghiep(String maDoanhNghiep) {
        for (DoanhNghiepLienKet item : danhSachDoanhNghiep) {
            if (item.getMaDoanhNghiep().equalsIgnoreCase(maDoanhNghiep)) {
                return item;
            }
        }

        return null;
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

    private RoundedPanel createBadge(String title, JLabel valueLabel, Color backgroundColor, Color textColor) {
        RoundedPanel badge = new RoundedPanel(18, backgroundColor);
        badge.setLayout(new GridBagLayout());
        badge.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        badge.setPreferredSize(new Dimension(128, 60));

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

    private static class DoanhNghiepLienKet {

        private String maDoanhNghiep;
        private String tenDoanhNghiep;
        private String linhVuc;
        private String diaChi;
        private String email;
        private String soDienThoai;
        private int tinDangMo;
        private int sinhVienThucTap;
        private double diemDanhGia;
        private String trangThaiHopTac;
        private String nhanXetTongHop;

        public DoanhNghiepLienKet(String maDoanhNghiep, String tenDoanhNghiep,
                String linhVuc, String diaChi, String email, String soDienThoai,
                int tinDangMo, int sinhVienThucTap, double diemDanhGia,
                String trangThaiHopTac, String nhanXetTongHop) {
            this.maDoanhNghiep = maDoanhNghiep;
            this.tenDoanhNghiep = tenDoanhNghiep;
            this.linhVuc = linhVuc;
            this.diaChi = diaChi;
            this.email = email;
            this.soDienThoai = soDienThoai;
            this.tinDangMo = tinDangMo;
            this.sinhVienThucTap = sinhVienThucTap;
            this.diemDanhGia = diemDanhGia;
            this.trangThaiHopTac = trangThaiHopTac;
            this.nhanXetTongHop = nhanXetTongHop;
        }

        public String getMaDoanhNghiep() {
            return maDoanhNghiep;
        }

        public String getTenDoanhNghiep() {
            return tenDoanhNghiep;
        }

        public String getLinhVuc() {
            return linhVuc;
        }

        public String getDiaChi() {
            return diaChi;
        }

        public String getEmail() {
            return email;
        }

        public String getSoDienThoai() {
            return soDienThoai;
        }

        public int getTinDangMo() {
            return tinDangMo;
        }

        public int getSinhVienThucTap() {
            return sinhVienThucTap;
        }

        public double getDiemDanhGia() {
            return diemDanhGia;
        }

        public String getTrangThaiHopTac() {
            return trangThaiHopTac;
        }

        public String getNhanXetTongHop() {
            return nhanXetTongHop;
        }
    }

    private static class DanhGiaDoanhNghiep {

        private String maSinhVien;
        private String hoTen;
        private String doanhNghiep;
        private String nhanXet;
        private String diem;

        public DanhGiaDoanhNghiep(String maSinhVien, String hoTen,
                String doanhNghiep, String nhanXet, String diem) {
            this.maSinhVien = maSinhVien;
            this.hoTen = hoTen;
            this.doanhNghiep = doanhNghiep;
            this.nhanXet = nhanXet;
            this.diem = diem;
        }

        public String getMaSinhVien() {
            return maSinhVien;
        }

        public String getHoTen() {
            return hoTen;
        }

        public String getDoanhNghiep() {
            return doanhNghiep;
        }

        public String getNhanXet() {
            return nhanXet;
        }

        public String getDiem() {
            return diem;
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
