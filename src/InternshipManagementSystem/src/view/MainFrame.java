package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import util.MessageUtil;

public class MainFrame extends JFrame {

    private JButton btnSinhVien;
    private JButton btnTinTuyenDung;
    private JButton btnUngTuyen;
    private JButton btnBaoCao;
    private JButton btnDangXuat;

    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_NAVY_DARK = Color.decode("#172554");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_DANGER_LIGHT = Color.decode("#FCA5A5");

    public MainFrame() {
        initUI();
        initEvents();
    }

    private void initUI() {
        setTitle("Hệ thống quản lý Thực tập và Kết nối doanh nghiệp");
        setSize(1100, 700);
        setMinimumSize(new Dimension(1100, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(COLOR_BACKGROUND);

        JPanel sidebarPanel = createSidebarPanel();
        JPanel contentPanel = createContentPanel();

        rootPanel.add(sidebarPanel, BorderLayout.WEST);
        rootPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setPreferredSize(new Dimension(260, 700));
        sidebarPanel.setBackground(COLOR_NAVY);
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(28, 18, 24, 18));

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());

        JLabel lblLogo = new JLabel("IMEC");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblLogo.setForeground(Color.WHITE);

        JLabel lblAppName = new JLabel(
                "<html>"
                + "<div style='line-height:145%;'>"
                + "QUẢN LÝ THỰC TẬP<br>"
                + "KẾT NỐI DOANH NGHIỆP"
                + "</div>"
                + "</html>"
        );
        lblAppName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAppName.setForeground(Color.WHITE);

        JLabel lblTagline = new JLabel("Kết nối sinh viên và doanh nghiệp");
        lblTagline.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTagline.setForeground(new Color(191, 219, 254));

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
        titleGbc.insets = new Insets(0, 0, 0, 0);
        titlePanel.add(lblTagline, titleGbc);

        topPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new GridBagLayout());

        btnSinhVien = createMenuButton("Quản lý sinh viên");
        btnTinTuyenDung = createMenuButton("Quản lý tin tuyển dụng");
        btnUngTuyen = createMenuButton("Quản lý ứng tuyển");
        btnBaoCao = createMenuButton("Báo cáo thống kê");

        addMenuButton(menuPanel, btnSinhVien, 0);
        addMenuButton(menuPanel, btnTinTuyenDung, 1);
        addMenuButton(menuPanel, btnUngTuyen, 2);
        addMenuButton(menuPanel, btnBaoCao, 3);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(42, 0, 0, 0));
        centerPanel.add(menuPanel, BorderLayout.NORTH);

        btnDangXuat = createLogoutButton("Đăng xuất");

        sidebarPanel.add(topPanel, BorderLayout.NORTH);
        sidebarPanel.add(centerPanel, BorderLayout.CENTER);
        sidebarPanel.add(btnDangXuat, BorderLayout.SOUTH);

        return sidebarPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(COLOR_BACKGROUND);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(34, 34, 34, 34));

        RoundedPanel welcomeCard = new RoundedPanel(28, COLOR_CARD);
        welcomeCard.setLayout(new BorderLayout());
        welcomeCard.setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));
        welcomeCard.setPreferredSize(new Dimension(760, 160));

        JLabel lblWelcome = new JLabel("Chào mừng đến với hệ thống quản lý thực tập");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblWelcome.setForeground(COLOR_TEXT);

        JLabel lblSubtitle = new JLabel("Hệ thống ghi nhận trạng thái vận hành ổn định");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitle.setForeground(COLOR_MUTED);

        JLabel lblHint = new JLabel("Chọn một chức năng ở thanh điều hướng để bắt đầu quản lý nghiệp vụ.");
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

        featurePanel.add(createFeatureCard("Sinh viên", "128", "Hồ sơ đang quản lý"));
        featurePanel.add(createFeatureCard("Doanh nghiệp", "24", "Đối tác kết nối"));
        featurePanel.add(createFeatureCard("Ứng tuyển", "36", "Hồ sơ mới"));
        featurePanel.add(createFeatureCard("Tin tuyển dụng", "18", "Vị trí đang mở"));

        JPanel lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setOpaque(false);
        lowerPanel.add(featurePanel, BorderLayout.NORTH);
        lowerPanel.add(createGuideCard(), BorderLayout.CENTER);

        contentPanel.add(welcomeCard, BorderLayout.NORTH);
        contentPanel.add(lowerPanel, BorderLayout.CENTER);

        return contentPanel;
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

    private RoundedPanel createGuideCard() {
        RoundedPanel guideCard = new RoundedPanel(26, COLOR_CARD);
        guideCard.setLayout(new BorderLayout());
        guideCard.setBorder(BorderFactory.createEmptyBorder(28, 30, 28, 30));

        JLabel lblTitle = new JLabel("Luồng thao tác nhanh");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblContent = new JLabel(
                "<html>"
                + "<div style='line-height:170%; color:#64748B;'>"
                + "1. Quản lý danh sách sinh viên thực tập.<br>"
                + "2. Theo dõi tin tuyển dụng và hồ sơ ứng tuyển.<br>"
                + "3. Xem báo cáo thống kê mẫu phục vụ demo giao diện.<br>"
                + "4. Dữ liệu hiện tại sử dụng ArrayList, chưa kết nối Oracle."
                + "</div>"
                + "</html>"
        );
        lblContent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblContent.setForeground(COLOR_MUTED);

        guideCard.add(lblTitle, BorderLayout.NORTH);
        guideCard.add(lblContent, BorderLayout.CENTER);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(28, 0, 0, 0));
        wrapper.add(guideCard, BorderLayout.NORTH);

        RoundedPanel container = new RoundedPanel(0, COLOR_BACKGROUND);
        container.setLayout(new BorderLayout());
        container.setOpaque(false);
        container.add(wrapper, BorderLayout.NORTH);

        return guideCard;
    }

    private JButton createMenuButton(String text) {
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
        gbc.insets = new Insets(0, 0, 14, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        menuPanel.add(button, gbc);
    }

    private void initEvents() {
        btnSinhVien.addActionListener(e -> {
            SinhVienFrame frame = new SinhVienFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });

        btnTinTuyenDung.addActionListener(e -> {
            TinTuyenDungFrame frame = new TinTuyenDungFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });

        btnUngTuyen.addActionListener(e -> {
            UngTuyenFrame frame = new UngTuyenFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });

        btnBaoCao.addActionListener(e -> {
            BaoCaoFrame frame = new BaoCaoFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });

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

        public SidebarButton(String text, Color normalColor, Color hoverColor) {
            super(text);
            this.normalColor = normalColor;
            this.hoverColor = hoverColor;

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setHorizontalAlignment(SwingConstants.LEFT);
            setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 18));

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

            Color bg = hovered ? hoverColor : normalColor;

            if (hovered) {
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
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