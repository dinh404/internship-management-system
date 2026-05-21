package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import service.AuthService;
import util.MessageUtil;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private EyeToggleButton btnShowPassword;
    private JCheckBox chkShowPassword;
    private GradientButton btnLogin;
    private ExitButton btnExit;
    private JTextField lblMessage;

    private UnderlineInputPanel usernameInputPanel;
    private UnderlineInputPanel passwordInputPanel;
    private RoundedPanel badgePanel;

    private final AuthService authService = new AuthService();

    private final Color COLOR_INDIGO = Color.decode("#4F46E5");
    private final Color COLOR_CYAN = Color.decode("#06B6D4");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_SLATE_DARK = Color.decode("#1E293B");
    private final Color COLOR_SLATE = Color.decode("#64748B");
    private final Color COLOR_SLATE_LIGHT = Color.decode("#94A3B8");
    private final Color COLOR_INPUT_LINE = Color.decode("#E2E8F0");
    private final Color COLOR_DANGER = Color.decode("#DC2626");
    private final Color COLOR_DANGER_HOVER = Color.decode("#EF4444");
    private final Color COLOR_BADGE_BG = Color.decode("#DCFCE7");
    private final Color COLOR_BADGE_TEXT = Color.decode("#16A34A");
    private final Color COLOR_GREEN_START = Color.decode("#059669");
    private final Color COLOR_GREEN_END = Color.decode("#10B981");

    private final int FORM_WIDTH = 320;
    private final int INPUT_HEIGHT = 52;

    public LoginFrame() {
        initUI();
        initEvents();
    }

    private void initUI() {
        setTitle("Đăng nhập hệ thống");
        setSize(450, 600);
        setMinimumSize(new Dimension(450, 600));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientBackgroundPanel rootPanel = new GradientBackgroundPanel(COLOR_INDIGO, COLOR_CYAN);
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(28, 28, 28, 28));

        RoundedPanel cardPanel = new RoundedPanel(32, COLOR_CARD);
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(32, 30, 26, 30));
        cardPanel.setPreferredSize(new Dimension(370, 520));

        JLabel lblTitle = new JLabel("IMEC");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 44));
        lblTitle.setForeground(COLOR_SLATE_DARK);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblSubtitle = new JLabel("Quản lý thực tập và Kết nối doanh nghiệp");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(COLOR_SLATE);
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);

        lblMessage = new JTextField("Tài khoản chạy thử nghiệm: admin / 123456");
        lblMessage.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMessage.setForeground(COLOR_BADGE_TEXT);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setEditable(false);
        lblMessage.setFocusable(true);
        lblMessage.setOpaque(false);
        lblMessage.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lblMessage.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        badgePanel = new RoundedPanel(18, COLOR_BADGE_BG);
        badgePanel.setLayout(new BorderLayout());
        badgePanel.setBorder(BorderFactory.createEmptyBorder(7, 12, 7, 12));
        badgePanel.setPreferredSize(new Dimension(FORM_WIDTH, 36));
        badgePanel.add(lblMessage, BorderLayout.CENTER);

        txtUsername = createTextField("Nhập tên đăng nhập");
        usernameInputPanel = new UnderlineInputPanel(COLOR_INPUT_LINE, COLOR_CYAN);
        usernameInputPanel.setLayout(new BorderLayout());
        usernameInputPanel.setPreferredSize(new Dimension(FORM_WIDTH, INPUT_HEIGHT));
        usernameInputPanel.add(txtUsername, BorderLayout.CENTER);

        txtPassword = createPasswordField("Nhập mật khẩu");
        btnShowPassword = new EyeToggleButton(COLOR_SLATE, COLOR_CYAN);

        passwordInputPanel = new UnderlineInputPanel(COLOR_INPUT_LINE, COLOR_CYAN);
        passwordInputPanel.setLayout(new BorderLayout());
        passwordInputPanel.setPreferredSize(new Dimension(FORM_WIDTH, INPUT_HEIGHT));
        passwordInputPanel.add(txtPassword, BorderLayout.CENTER);
        passwordInputPanel.add(btnShowPassword, BorderLayout.EAST);

        chkShowPassword = new JCheckBox("Hiện mật khẩu");
        chkShowPassword.setVisible(false);

        btnLogin = new GradientButton("Đăng nhập", COLOR_GREEN_START, COLOR_GREEN_END);
        btnLogin.setPreferredSize(new Dimension(FORM_WIDTH, 56));

        btnExit = new ExitButton("Thoát", COLOR_SLATE_LIGHT, COLOR_DANGER_HOVER);
        btnExit.setPreferredSize(new Dimension(100, 36));

        JPanel exitPanel = new JPanel(new BorderLayout());
        exitPanel.setOpaque(false);
        exitPanel.setPreferredSize(new Dimension(FORM_WIDTH, 38));
        exitPanel.add(btnExit, BorderLayout.EAST);

        addToCard(cardPanel, lblTitle, 0, new Insets(0, 0, 10, 0), GridBagConstraints.CENTER);
        addToCard(cardPanel, lblSubtitle, 1, new Insets(0, 0, 34, 0), GridBagConstraints.CENTER);

        addToCard(cardPanel, createLabel("Tên đăng nhập"), 2, new Insets(0, 0, 6, 0), GridBagConstraints.WEST);
        addToCard(cardPanel, usernameInputPanel, 3, new Insets(0, 0, 18, 0), GridBagConstraints.CENTER);

        addToCard(cardPanel, createLabel("Mật khẩu"), 4, new Insets(0, 0, 6, 0), GridBagConstraints.WEST);
        addToCard(cardPanel, passwordInputPanel, 5, new Insets(0, 0, 16, 0), GridBagConstraints.CENTER);

        addToCard(cardPanel, badgePanel, 6, new Insets(0, 0, 26, 0), GridBagConstraints.CENTER);
        addToCard(cardPanel, btnLogin, 7, new Insets(0, 0, 18, 0), GridBagConstraints.CENTER);
        addToCard(cardPanel, exitPanel, 8, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER);

        rootPanel.add(cardPanel);
        setContentPane(rootPanel);

        getRootPane().setDefaultButton(btnLogin);
    }

    private void addToCard(JPanel panel, java.awt.Component component, int row, Insets insets, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.insets = insets;
        gbc.anchor = anchor;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(component, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(COLOR_SLATE_DARK);
        label.setPreferredSize(new Dimension(FORM_WIDTH, 20));
        return label;
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new PlaceholderTextField(placeholder);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textField.setForeground(COLOR_SLATE_DARK);
        textField.setCaretColor(COLOR_CYAN);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        textField.setOpaque(false);
        return textField;
    }

    private JPasswordField createPasswordField(String placeholder) {
        JPasswordField passwordField = new PlaceholderPasswordField(placeholder);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setForeground(COLOR_SLATE_DARK);
        passwordField.setCaretColor(COLOR_CYAN);
        passwordField.setEchoChar('•');
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        passwordField.setOpaque(false);
        return passwordField;
    }

    private void initEvents() {
        btnLogin.addActionListener(this::handleLogin);
        btnExit.addActionListener(this::handleExit);

        txtUsername.addActionListener(e -> btnLogin.doClick());
        txtPassword.addActionListener(e -> btnLogin.doClick());

        txtUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                usernameInputPanel.setFocused(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                usernameInputPanel.setFocused(false);
            }
        });

        txtPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordInputPanel.setFocused(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordInputPanel.setFocused(false);
            }
        });

        btnShowPassword.addActionListener(e -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        boolean showing = btnShowPassword.isSelected();

        if (showing) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('•');
        }

        btnShowPassword.setShowing(showing);
    }

    private void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty()) {
            showInlineError("Vui lòng nhập tên đăng nhập!");
            txtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showInlineError("Vui lòng nhập mật khẩu!");
            txtPassword.requestFocus();
            return;
        }

        if (authService.login(username, password)) {
            showInlineSuccess("Đăng nhập thành công!");
            MessageUtil.showInfo(this, "Đăng nhập thành công!");

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            dispose();
        } else {
            showInlineError("Tên đăng nhập hoặc mật khẩu không đúng!");
            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }

    private void handleExit(ActionEvent event) {
        boolean confirm = MessageUtil.showConfirm(this, "Bạn có chắc chắn muốn thoát chương trình?");

        if (confirm) {
            System.exit(0);
        }
    }

    private void showInlineSuccess(String message) {
        lblMessage.setForeground(COLOR_BADGE_TEXT);
        lblMessage.setText(message);
    }

    private void showInlineError(String message) {
        lblMessage.setForeground(COLOR_DANGER);
        lblMessage.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    private static class GradientBackgroundPanel extends JPanel {

        private final Color startColor;
        private final Color endColor;

        public GradientBackgroundPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            GradientPaint gradient = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), endColor
            );

            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
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

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            g2.setColor(new Color(255, 255, 255, 160));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class UnderlineInputPanel extends JPanel {

        private final Color normalColor;
        private final Color focusColor;
        private boolean focused = false;

        public UnderlineInputPanel(Color normalColor, Color focusColor) {
            this.normalColor = normalColor;
            this.focusColor = focusColor;
            setOpaque(false);
        }

        public void setFocused(boolean focused) {
            this.focused = focused;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int y = getHeight() - 2;
            g2.setColor(focused ? focusColor : normalColor);
            g2.setStroke(new BasicStroke(focused ? 2.4f : 1.5f));
            g2.drawLine(0, y, getWidth(), y);

            g2.dispose();
        }
    }

    private static class GradientButton extends JButton {

        private final Color startColor;
        private final Color endColor;
        private boolean hovered = false;

        public GradientButton(String text, Color startColor, Color endColor) {
            super(text);
            this.startColor = startColor;
            this.endColor = endColor;

            setFont(new Font("Segoe UI", Font.BOLD, 15));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

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

            int arc = getHeight();

            if (hovered) {
                g2.setColor(new Color(16, 185, 129, 80));
                g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 2, arc, arc);
            }

            GradientPaint gradient = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), endColor
            );

            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight() - 2, arc, arc);

            FontMetrics fm = g2.getFontMetrics(getFont());
            String text = getText();

            int textWidth = fm.stringWidth(text);
            int textX = (getWidth() - textWidth) / 2;
            int textY = ((getHeight() - 2 - fm.getHeight()) / 2) + fm.getAscent();

            g2.setFont(getFont());
            g2.setColor(getForeground());
            g2.drawString(text, textX, textY);

            g2.dispose();
        }
    }

    private static class ExitButton extends JButton {

        private final Color normalColor;
        private final Color hoverColor;
        private boolean hovered = false;

        public ExitButton(String text, Color normalColor, Color hoverColor) {
            super(text);
            this.normalColor = normalColor;
            this.hoverColor = hoverColor;

            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setForeground(normalColor);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    setForeground(hoverColor);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    setForeground(normalColor);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = getHeight();
            Color bg = hovered ? new Color(254, 226, 226) : new Color(248, 250, 252);
            Color border = hovered ? new Color(248, 113, 113, 150) : new Color(226, 232, 240);

            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            g2.setColor(border);
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, arc, arc);

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class EyeToggleButton extends JToggleButton {

        private final Color normalColor;
        private final Color activeColor;
        private boolean showing = false;
        private boolean hovered = false;

        public EyeToggleButton(Color normalColor, Color activeColor) {
            this.normalColor = normalColor;
            this.activeColor = activeColor;

            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(34, 44));

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

        public void setShowing(boolean showing) {
            this.showing = showing;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color iconColor = showing || hovered ? activeColor : normalColor;

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;
            int eyeWidth = 18;
            int eyeHeight = 10;

            g2.setColor(iconColor);
            g2.setStroke(new BasicStroke(1.5f));

            g2.drawArc(cx - eyeWidth / 2, cy - eyeHeight / 2, eyeWidth, eyeHeight, 0, 180);
            g2.drawArc(cx - eyeWidth / 2, cy - eyeHeight / 2, eyeWidth, eyeHeight, 180, 180);

            if (showing) {
                g2.fillOval(cx - 3, cy - 3, 6, 6);
            } else {
                g2.drawOval(cx - 3, cy - 3, 6, 6);
                g2.drawLine(cx - 9, cy + 8, cx + 9, cy - 8);
            }

            g2.dispose();
        }
    }

    private static class PlaceholderTextField extends JTextField {

        private final String placeholder;

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            if (getText().isEmpty() && !isFocusOwner()) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#94A3B8"));
                g2.setFont(getFont().deriveFont(Font.PLAIN));
                g2.drawString(placeholder, 0, getHeight() / 2 + 5);
                g2.dispose();
            }
        }
    }

    private static class PlaceholderPasswordField extends JPasswordField {

        private final String placeholder;

        public PlaceholderPasswordField(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            if (getPassword().length == 0 && !isFocusOwner()) {
                Graphics2D g2 = (Graphics2D) graphics.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#94A3B8"));
                g2.setFont(getFont().deriveFont(Font.PLAIN));
                g2.drawString(placeholder, 0, getHeight() / 2 + 5);
                g2.dispose();
            }
        }
    }
}