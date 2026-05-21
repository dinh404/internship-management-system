package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.TaiKhoan;
import util.MessageUtil;

public class MainFrame extends JFrame {

    private static final String CARD_DASHBOARD = "DASHBOARD";

    private static final String CARD_ADMIN_SINH_VIEN = "ADMIN_SINH_VIEN";
    private static final String CARD_ADMIN_TIN_TUYEN_DUNG = "ADMIN_TIN_TUYEN_DUNG";
    private static final String CARD_ADMIN_UNG_TUYEN = "ADMIN_UNG_TUYEN";
    private static final String CARD_ADMIN_BAO_CAO = "ADMIN_BAO_CAO";

    private static final String CARD_SV_XEM_TIN = "SV_XEM_TIN";
    private static final String CARD_SV_CV = "SV_CV";
    private static final String CARD_SV_UNG_TUYEN = "SV_UNG_TUYEN";
    private static final String CARD_SV_THONG_TIN = "SV_THONG_TIN";

    private static final String CARD_DN_TIN_CUA_TOI = "DN_TIN_CUA_TOI";
    private static final String CARD_DN_HO_SO_UNG_VIEN = "DN_HO_SO_UNG_VIEN";
    private static final String CARD_DN_THONG_TIN = "DN_THONG_TIN";

    private SidebarButton btnTongQuan;

    private SidebarButton btnSinhVien;
    private SidebarButton btnTinTuyenDung;
    private SidebarButton btnUngTuyen;
    private SidebarButton btnBaoCao;

    private SidebarButton btnXemTinTuyenDung;
    private SidebarButton btnQuanLyCV;
    private SidebarButton btnUngTuyenCuaToi;
    private SidebarButton btnThongTinCaNhan;

    private SidebarButton btnTinCuaDoanhNghiep;
    private SidebarButton btnHoSoUngVien;
    private SidebarButton btnThongTinDoanhNghiep;

    private JButton btnDangXuat;

    private TaiKhoan currentUser;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private SidebarButton activeButton;

    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_DANGER_LIGHT = Color.decode("#FCA5A5");

    public MainFrame() {
        this(new TaiKhoan(
                "admin",
                "123456",
                "ADMIN",
                "ADMIN001",
                "Quản trị viên"
        ));
    }

    public MainFrame(TaiKhoan currentUser) {
        if (currentUser == null) {
            this.currentUser = new TaiKhoan(
                    "admin",
                    "123456",
                    "ADMIN",
                    "ADMIN001",
                    "Quản trị viên"
            );
        } else {
            this.currentUser = currentUser;
        }

        initUI();
        initEvents();
        showCard(CARD_DASHBOARD, btnTongQuan);
    }

    private void initUI() {
        setTitle("IMEC - Quản lý thực tập và kết nối doanh nghiệp");
        setSize(1100, 700);
        setMinimumSize(new Dimension(1100, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(COLOR_BACKGROUND);

        JPanel sidebarPanel = createSidebarPanel();

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(COLOR_BACKGROUND);

        buildContentCardsByRole();

        rootPanel.add(sidebarPanel, BorderLayout.WEST);
        rootPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setPreferredSize(new Dimension(260, 700));
        sidebarPanel.setBackground(COLOR_NAVY);
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(28, 18, 24, 18));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel lblLogo = new JLabel("IMEC");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblLogo.setForeground(Color.WHITE);

        JLabel lblAppName = new JLabel(
                "<html>"
                + "<div style='line-height:145%;'>"
                + "QUẢN LÝ THỰC TẬP<br>"
                + "KẾT NỐI DOANH NGHIỆP"
                + "</div>"
                + "</html>"
        );
        lblAppName.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblAppName.setForeground(Color.WHITE);

        JLabel lblTagline = new JLabel("Kết nối sinh viên và doanh nghiệp");
        lblTagline.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTagline.setForeground(new Color(191, 219, 254));

        JLabel lblUser = new JLabel(getRoleDisplayName() + " • " + currentUser.getTenHienThi());
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUser.setForeground(new Color(219, 234, 254));

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.anchor = GridBagConstraints.WEST;
        titleGbc.fill = GridBagConstraints.HORIZONTAL;
        titleGbc.weightx = 1.0;

        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(0, 0, 10, 0);
        titlePanel.add(lblLogo, titleGbc);

        titleGbc.gridy = 1;
        titleGbc.insets = new Insets(0, 0, 8, 0);
        titlePanel.add(lblAppName, titleGbc);

        titleGbc.gridy = 2;
        titleGbc.insets = new Insets(0, 0, 8, 0);
        titlePanel.add(lblTagline, titleGbc);

        titleGbc.gridy = 3;
        titleGbc.insets = new Insets(0, 0, 0, 0);
        titlePanel.add(lblUser, titleGbc);

        topPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setOpaque(false);

        buildMenuByRole(menuPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(54, 0, 0, 0));
        centerPanel.add(menuPanel, BorderLayout.NORTH);

        btnDangXuat = createLogoutButton("Đăng xuất");

        sidebarPanel.add(topPanel, BorderLayout.NORTH);
        sidebarPanel.add(centerPanel, BorderLayout.CENTER);
        sidebarPanel.add(btnDangXuat, BorderLayout.SOUTH);

        return sidebarPanel;
    }

    private void buildMenuByRole(JPanel menuPanel) {
        btnTongQuan = createMenuButton("Tổng quan");
        addMenuButton(menuPanel, btnTongQuan, 0);

        if (currentUser.isAdmin()) {
            btnSinhVien = createMenuButton("Quản lý sinh viên");
            btnTinTuyenDung = createMenuButton("Quản lý tin tuyển dụng");
            btnUngTuyen = createMenuButton("Quản lý ứng tuyển");
            btnBaoCao = createMenuButton("Báo cáo thống kê");

            addMenuButton(menuPanel, btnSinhVien, 1);
            addMenuButton(menuPanel, btnTinTuyenDung, 2);
            addMenuButton(menuPanel, btnUngTuyen, 3);
            addMenuButton(menuPanel, btnBaoCao, 4);
        } else if (currentUser.isSinhVien()) {
            btnXemTinTuyenDung = createMenuButton("Xem tin tuyển dụng");
            btnQuanLyCV = createMenuButton("Quản lý CV");
            btnUngTuyenCuaToi = createMenuButton("Ứng tuyển của tôi");
            btnThongTinCaNhan = createMenuButton("Thông tin cá nhân");

            addMenuButton(menuPanel, btnXemTinTuyenDung, 1);
            addMenuButton(menuPanel, btnQuanLyCV, 2);
            addMenuButton(menuPanel, btnUngTuyenCuaToi, 3);
            addMenuButton(menuPanel, btnThongTinCaNhan, 4);
        } else if (currentUser.isDoanhNghiep() || currentUser.isHR()) {
            btnTinCuaDoanhNghiep = createMenuButton("Tin tuyển dụng của tôi");
            btnHoSoUngVien = createMenuButton("Hồ sơ ứng viên");
            btnThongTinDoanhNghiep = createMenuButton("Thông tin doanh nghiệp");

            addMenuButton(menuPanel, btnTinCuaDoanhNghiep, 1);
            addMenuButton(menuPanel, btnHoSoUngVien, 2);
            addMenuButton(menuPanel, btnThongTinDoanhNghiep, 3);
        }
    }

    private void buildContentCardsByRole() {
        contentPanel.add(createDashboardPanelByRole(), CARD_DASHBOARD);

        if (currentUser.isAdmin()) {
            contentPanel.add(wrapFrameContent(new SinhVienFrame()), CARD_ADMIN_SINH_VIEN);
            contentPanel.add(wrapFrameContent(new TinTuyenDungFrame()), CARD_ADMIN_TIN_TUYEN_DUNG);
            contentPanel.add(wrapFrameContent(new UngTuyenFrame()), CARD_ADMIN_UNG_TUYEN);
            contentPanel.add(wrapFrameContent(new BaoCaoFrame()), CARD_ADMIN_BAO_CAO);
        } else if (currentUser.isSinhVien()) {
            contentPanel.add(new SinhVienXemTinPanel(currentUser), CARD_SV_XEM_TIN);

            contentPanel.add(new QuanLyCVPanel(currentUser), CARD_SV_CV);

            contentPanel.add(new UngTuyenCuaToiPanel(currentUser), CARD_SV_UNG_TUYEN);

            contentPanel.add(new ThongTinCaNhanPanel(currentUser), CARD_SV_THONG_TIN);

        } else if (currentUser.isDoanhNghiep() || currentUser.isHR()) {
            contentPanel.add(new TinTuyenDungCuaToiPanel(currentUser), CARD_DN_TIN_CUA_TOI);

            contentPanel.add(new HoSoUngVienPanel(currentUser), CARD_DN_HO_SO_UNG_VIEN);

            contentPanel.add(createFeaturePlaceholderPanel(
                    "THÔNG TIN DOANH NGHIỆP",
                    "Doanh nghiệp/HR xem và cập nhật thông tin đơn vị để tăng độ tin cậy khi kết nối với sinh viên.",
                    new String[]{
                        "Xem mã doanh nghiệp, tên doanh nghiệp, lĩnh vực hoạt động và thông tin liên hệ.",
                        "Cập nhật mô tả doanh nghiệp, địa chỉ và người phụ trách tuyển dụng.",
                        "Thông tin doanh nghiệp được hiển thị kèm các tin tuyển dụng.",
                        "Dữ liệu hiện tại là dữ liệu mẫu phục vụ demo giao diện."
                    }
            ), CARD_DN_THONG_TIN);
        }
    }

    private JPanel wrapFrameContent(JFrame frame) {
        Container oldContent = frame.getContentPane();
        frame.setContentPane(new JPanel());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(COLOR_BACKGROUND);
        wrapper.add(oldContent, BorderLayout.CENTER);

        return wrapper;
    }

    private void showCard(String cardName, SidebarButton selectedButton) {
        if (cardLayout != null && contentPanel != null) {
            cardLayout.show(contentPanel, cardName);
        }

        setActiveButton(selectedButton);
    }

    private void setActiveButton(SidebarButton selectedButton) {
        if (activeButton != null) {
            activeButton.setActive(false);
        }

        activeButton = selectedButton;

        if (activeButton != null) {
            activeButton.setActive(true);
        }
    }

    private JPanel createDashboardPanelByRole() {
        if (currentUser.isSinhVien()) {
            return createStudentDashboardPanel();
        }

        if (currentUser.isDoanhNghiep() || currentUser.isHR()) {
            return createEnterpriseDashboardPanel();
        }

        return createAdminDashboardPanel();
    }

    private JPanel createAdminDashboardPanel() {
        String[][] cards = {
            {"Sinh viên", "128", "Hồ sơ đang quản lý"},
            {"Doanh nghiệp", "24", "Đối tác kết nối"},
            {"Ứng tuyển", "36", "Hồ sơ mới"},
            {"Tin tuyển dụng", "18", "Vị trí đang mở"}
        };

        String[] guideLines = {
            "Quản lý danh sách sinh viên thực tập.",
            "Theo dõi tin tuyển dụng và hồ sơ ứng tuyển.",
            "Xem báo cáo thống kê mẫu phục vụ demo giao diện.",
            "Dữ liệu hiện tại sử dụng ArrayList, chưa kết nối Oracle."
        };

        return createDashboardPanel(
                "Chào mừng đến với hệ thống quản lý thực tập",
                "Hệ thống ghi nhận trạng thái vận hành ổn định",
                "Chọn một chức năng ở thanh điều hướng để bắt đầu quản lý nghiệp vụ.",
                cards,
                "Luồng thao tác nhanh",
                guideLines
        );
    }

    private JPanel createStudentDashboardPanel() {
        String[][] cards = {
            {"Tin đang mở", "18", "Cơ hội thực tập có thể xem"},
            {"CV cá nhân", "2", "Hồ sơ CV đang lưu"},
            {"Đơn đã nộp", "3", "Hồ sơ ứng tuyển của bạn"},
            {"Đã duyệt", "1", "Hồ sơ có phản hồi tích cực"}
        };

        String[] guideLines = {
            "Xem danh sách tin tuyển dụng thực tập đang mở.",
            "Chuẩn bị hoặc cập nhật CV cá nhân trước khi ứng tuyển.",
            "Chọn vị trí phù hợp và gửi hồ sơ ứng tuyển.",
            "Theo dõi trạng thái xử lý của từng đơn ứng tuyển."
        };

        return createDashboardPanel(
                "Chào mừng " + currentUser.getTenHienThi(),
                "Không gian dành cho sinh viên theo dõi cơ hội thực tập, CV và tiến độ ứng tuyển",
                "Hãy bắt đầu bằng việc xem các tin tuyển dụng đang mở từ doanh nghiệp.",
                cards,
                "Luồng thao tác dành cho sinh viên",
                guideLines
        );
    }

    private JPanel createEnterpriseDashboardPanel() {
        String[][] cards = {
            {"Tin của tôi", "5", "Tin tuyển dụng đã đăng"},
            {"Đang mở", "3", "Vị trí còn nhận hồ sơ"},
            {"Hồ sơ nhận được", "12", "Sinh viên đã ứng tuyển"},
            {"Chờ xử lý", "4", "Hồ sơ cần xét duyệt"}
        };

        String[] guideLines = {
            "Quản lý các tin tuyển dụng thực tập của doanh nghiệp.",
            "Theo dõi danh sách sinh viên ứng tuyển vào từng vị trí.",
            "Cập nhật trạng thái xét duyệt hồ sơ ứng viên.",
            "Cập nhật thông tin doanh nghiệp để tăng độ tin cậy."
        };

        return createDashboardPanel(
                "Chào mừng " + currentUser.getTenHienThi(),
                "Không gian dành cho doanh nghiệp và HR quản lý tuyển dụng thực tập",
                "Hãy kiểm tra hồ sơ ứng viên mới và cập nhật trạng thái xử lý kịp thời.",
                cards,
                "Luồng thao tác dành cho doanh nghiệp/HR",
                guideLines
        );
    }

    private JPanel createDashboardPanel(String welcomeText, String subtitle, String hint,
            String[][] cards, String guideTitle, String[] guideLines) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(34, 34, 34, 34));

        RoundedPanel welcomeCard = new RoundedPanel(28, COLOR_CARD);
        welcomeCard.setLayout(new BorderLayout());
        welcomeCard.setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));
        welcomeCard.setPreferredSize(new Dimension(760, 160));

        JLabel lblWelcome = new JLabel(welcomeText);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblWelcome.setForeground(COLOR_TEXT);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitle.setForeground(COLOR_MUTED);

        JLabel lblHint = new JLabel(hint);
        lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHint.setForeground(COLOR_MUTED);

        JPanel welcomeTextPanel = new JPanel(new GridBagLayout());
        welcomeTextPanel.setOpaque(false);

        GridBagConstraints welcomeGbc = new GridBagConstraints();
        welcomeGbc.gridx = 0;
        welcomeGbc.gridy = 0;
        welcomeGbc.anchor = GridBagConstraints.WEST;
        welcomeGbc.fill = GridBagConstraints.HORIZONTAL;
        welcomeGbc.weightx = 1.0;
        welcomeGbc.insets = new Insets(0, 0, 8, 0);
        welcomeTextPanel.add(lblWelcome, welcomeGbc);

        welcomeGbc.gridy++;
        welcomeGbc.insets = new Insets(0, 0, 18, 0);
        welcomeTextPanel.add(lblSubtitle, welcomeGbc);

        welcomeGbc.gridy++;
        welcomeGbc.insets = new Insets(0, 0, 0, 0);
        welcomeTextPanel.add(lblHint, welcomeGbc);

        welcomeCard.add(welcomeTextPanel, BorderLayout.CENTER);

        JPanel featurePanel = new JPanel(new GridLayout(1, 4, 18, 0));
        featurePanel.setOpaque(false);
        featurePanel.setBorder(BorderFactory.createEmptyBorder(26, 0, 0, 0));

        for (String[] card : cards) {
            featurePanel.add(createFeatureCard(card[0], card[1], card[2]));
        }

        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setOpaque(false);
        lowerPanel.add(featurePanel, BorderLayout.NORTH);
        lowerPanel.add(createGuideCard(guideTitle, guideLines), BorderLayout.CENTER);

        panel.add(welcomeCard, BorderLayout.NORTH);
        panel.add(lowerPanel, BorderLayout.CENTER);

        return panel;
    }

    private RoundedPanel createFeatureCard(String title, String value, String description) {
        RoundedPanel card = new RoundedPanel(24, COLOR_CARD);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(22, 20, 22, 20));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(COLOR_MUTED);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblValue.setForeground(COLOR_TEAL);

        JLabel lblDescription = new JLabel(description);
        lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDescription.setForeground(COLOR_MUTED);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        card.add(lblDescription, BorderLayout.SOUTH);

        return card;
    }

    private RoundedPanel createGuideCard(String title, String[] lines) {
        RoundedPanel guideCard = new RoundedPanel(26, COLOR_CARD);
        guideCard.setLayout(new BorderLayout());
        guideCard.setBorder(BorderFactory.createEmptyBorder(28, 30, 28, 30));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(COLOR_TEXT);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        StringBuilder html = new StringBuilder();
        html.append("<html><div style='line-height:175%; color:#475569;'>");

        for (int i = 0; i < lines.length; i++) {
            html.append("<b style='color:#1E3A8A;'>")
                    .append(i + 1)
                    .append(".</b> ")
                    .append(lines[i]);

            if (i < lines.length - 1) {
                html.append("<br>");
            }
        }

        html.append("</div></html>");

        JLabel lblContent = new JLabel(html.toString());
        lblContent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblContent.setForeground(Color.decode("#475569"));
        lblContent.setVerticalAlignment(SwingConstants.TOP);

        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setOpaque(false);
        contentWrapper.add(lblContent, BorderLayout.NORTH);

        guideCard.add(lblTitle, BorderLayout.NORTH);
        guideCard.add(contentWrapper, BorderLayout.CENTER);

        return guideCard;
    }

    private JPanel createFeaturePlaceholderPanel(String title, String subtitle, String[] guideLines) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(34, 34, 34, 34));

        RoundedPanel mainCard = new RoundedPanel(28, COLOR_CARD);
        mainCard.setLayout(new BorderLayout());
        mainCard.setBorder(BorderFactory.createEmptyBorder(32, 34, 32, 34));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("<html>" + subtitle + "</html>");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(COLOR_MUTED);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.anchor = GridBagConstraints.WEST;
        titleGbc.fill = GridBagConstraints.HORIZONTAL;
        titleGbc.weightx = 1.0;

        titleGbc.gridy = 0;
        titleGbc.insets = new Insets(0, 0, 8, 0);
        titlePanel.add(lblTitle, titleGbc);

        titleGbc.gridy = 1;
        titleGbc.insets = new Insets(0, 0, 0, 0);
        titlePanel.add(lblSubtitle, titleGbc);

        mainCard.add(titlePanel, BorderLayout.NORTH);
        mainCard.add(createGuideCard("Nội dung nghiệp vụ dự kiến", guideLines), BorderLayout.CENTER);

        panel.add(mainCard, BorderLayout.CENTER);
        return panel;
    }

    private SidebarButton createMenuButton(String text) {
        SidebarButton button = new SidebarButton(text, COLOR_NAVY, COLOR_TEAL);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(224, 46));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createLogoutButton(String text) {
        LogoutButton button = new LogoutButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(COLOR_DANGER_LIGHT);
        button.setPreferredSize(new Dimension(224, 46));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addMenuButton(JPanel menuPanel, JButton button, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = new Insets(0, 0, 12, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        menuPanel.add(button, gbc);
    }

    private String getRoleDisplayName() {
        if (currentUser.isAdmin()) {
            return "Quản trị viên";
        }

        if (currentUser.isSinhVien()) {
            return "Sinh viên";
        }

        if (currentUser.isHR()) {
            return "HR";
        }

        if (currentUser.isDoanhNghiep()) {
            return "Doanh nghiệp";
        }

        return "Người dùng";
    }

    private void initEvents() {
        if (btnTongQuan != null) {
            btnTongQuan.addActionListener(e -> showCard(CARD_DASHBOARD, btnTongQuan));
        }

        if (btnSinhVien != null) {
            btnSinhVien.addActionListener(e -> showCard(CARD_ADMIN_SINH_VIEN, btnSinhVien));
        }

        if (btnTinTuyenDung != null) {
            btnTinTuyenDung.addActionListener(e -> showCard(CARD_ADMIN_TIN_TUYEN_DUNG, btnTinTuyenDung));
        }

        if (btnUngTuyen != null) {
            btnUngTuyen.addActionListener(e -> showCard(CARD_ADMIN_UNG_TUYEN, btnUngTuyen));
        }

        if (btnBaoCao != null) {
            btnBaoCao.addActionListener(e -> showCard(CARD_ADMIN_BAO_CAO, btnBaoCao));
        }

        if (btnXemTinTuyenDung != null) {
            btnXemTinTuyenDung.addActionListener(e -> showCard(CARD_SV_XEM_TIN, btnXemTinTuyenDung));
        }

        if (btnQuanLyCV != null) {
            btnQuanLyCV.addActionListener(e -> showCard(CARD_SV_CV, btnQuanLyCV));
        }

        if (btnUngTuyenCuaToi != null) {
            btnUngTuyenCuaToi.addActionListener(e -> showCard(CARD_SV_UNG_TUYEN, btnUngTuyenCuaToi));
        }

        if (btnThongTinCaNhan != null) {
            btnThongTinCaNhan.addActionListener(e -> showCard(CARD_SV_THONG_TIN, btnThongTinCaNhan));
        }

        if (btnTinCuaDoanhNghiep != null) {
            btnTinCuaDoanhNghiep.addActionListener(e -> showCard(CARD_DN_TIN_CUA_TOI, btnTinCuaDoanhNghiep));
        }

        if (btnHoSoUngVien != null) {
            btnHoSoUngVien.addActionListener(e -> showCard(CARD_DN_HO_SO_UNG_VIEN, btnHoSoUngVien));
        }

        if (btnThongTinDoanhNghiep != null) {
            btnThongTinDoanhNghiep.addActionListener(e -> showCard(CARD_DN_THONG_TIN, btnThongTinDoanhNghiep));
        }

        btnDangXuat.addActionListener(e -> {
            boolean confirm = MessageUtil.showConfirm(this, "Bạn có chắc chắn muốn đăng xuất?");

            if (confirm) {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
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

            if (radius > 0) {
                g2.setColor(new Color(15, 23, 42, 18));
                g2.fillRoundRect(4, 6, getWidth() - 8, getHeight() - 8, radius, radius);

                g2.setColor(backgroundColor);
                g2.fillRoundRect(0, 0, getWidth() - 6, getHeight() - 6, radius, radius);

                g2.setColor(new Color(226, 232, 240, 130));
                g2.setStroke(new BasicStroke(1.1f));
                g2.drawRoundRect(0, 0, getWidth() - 7, getHeight() - 7, radius, radius);
            } else {
                g2.setColor(backgroundColor);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class SidebarButton extends JButton {

        private final Color normalColor;
        private final Color hoverColor;
        private boolean hovered = false;
        private boolean active = false;

        public SidebarButton(String text, Color normalColor, Color hoverColor) {
            super(text);
            this.normalColor = normalColor;
            this.hoverColor = hoverColor;

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setHorizontalAlignment(SwingConstants.LEFT);
            setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 18));

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

        public void setActive(boolean active) {
            this.active = active;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = 18;

            if (active || hovered) {
                g2.setColor(hoverColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                g2.setColor(new Color(255, 255, 255, 95));
                g2.setStroke(new BasicStroke(1.4f));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc, arc);
            } else {
                g2.setColor(new Color(255, 255, 255, 18));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                g2.setColor(new Color(255, 255, 255, 42));
                g2.setStroke(new BasicStroke(1.1f));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc, arc);
            }

            if (active) {
                int centerY = getHeight() / 2;
                int[] xPoints = {9, 17, 9};
                int[] yPoints = {centerY - 7, centerY, centerY + 7};

                g2.setColor(Color.WHITE);
                g2.fillPolygon(xPoints, yPoints, 3);
            }

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private class LogoutButton extends JButton {

        private boolean hovered = false;

        public LogoutButton(String text) {
            super(text);

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setHorizontalAlignment(SwingConstants.CENTER);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    setForeground(Color.WHITE);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    setForeground(COLOR_DANGER_LIGHT);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (hovered) {
                g2.setColor(COLOR_DANGER);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            } else {
                g2.setColor(new Color(255, 255, 255, 22));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            }

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static void drawCenteredText(Graphics2D g2, String text, int width, int height) {
        FontMetrics fm = g2.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2.drawString(text, x, y);
    }
}