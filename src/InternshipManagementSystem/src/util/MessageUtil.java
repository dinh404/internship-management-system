package util;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MessageUtil {

    private static final Color COLOR_INDIGO = Color.decode("#4F46E5");
    private static final Color COLOR_CYAN = Color.decode("#06B6D4");
    private static final Color COLOR_GREEN_START = Color.decode("#059669");
    private static final Color COLOR_GREEN_END = Color.decode("#10B981");
    private static final Color COLOR_RED = Color.decode("#EF4444");
    private static final Color COLOR_CARD = Color.decode("#FFFFFF");
    private static final Color COLOR_TEXT = Color.decode("#1E293B");
    private static final Color COLOR_MUTED = Color.decode("#64748B");
    private static final Color COLOR_BORDER = Color.decode("#E2E8F0");

    private MessageUtil() {
    }

    public static void showInfo(String message) {
        showInfo(null, message);
    }

    public static void showInfo(Component parent, String message) {
        CustomDialog dialog = new CustomDialog(parent, "Thông báo", message, DialogType.INFO, false);
        dialog.setVisible(true);
    }

    public static void showError(String message) {
        showError(null, message);
    }

    public static void showError(Component parent, String message) {
        CustomDialog dialog = new CustomDialog(parent, "Lỗi", message, DialogType.ERROR, false);
        dialog.setVisible(true);
    }

    public static boolean showConfirm(String message) {
        return showConfirm(null, message);
    }

    public static boolean showConfirm(Component parent, String message) {
        CustomDialog dialog = new CustomDialog(parent, "Xác nhận", message, DialogType.CONFIRM, true);
        dialog.setVisible(true);
        return dialog.isConfirmed();
    }

    private enum DialogType {
        INFO,
        ERROR,
        CONFIRM
    }

    private static class CustomDialog extends JDialog {

        private boolean confirmed = false;
        private final DialogType type;
        private Point dragPoint;

        public CustomDialog(Component parent, String title, String message, DialogType type, boolean confirmMode) {
            super(getWindow(parent), title, ModalityType.APPLICATION_MODAL);
            this.type = type;

            setUndecorated(true);
            setSize(420, confirmMode ? 250 : 230);
            setMinimumSize(new Dimension(420, confirmMode ? 250 : 230));
            setResizable(false);
            setBackground(new Color(0, 0, 0, 0));
            setContentPane(createRootPanel(title, message, confirmMode));
            setLocationRelativeTo(parent);
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        private JPanel createRootPanel(String title, String message, boolean confirmMode) {
            ShadowPanel outerPanel = new ShadowPanel();
            outerPanel.setLayout(new BorderLayout());
            outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 14, 14));

            RoundedPanel cardPanel = new RoundedPanel(26, COLOR_CARD);
            cardPanel.setLayout(new BorderLayout());
            cardPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

            JPanel headerPanel = createHeaderPanel(title);
            JPanel contentPanel = createContentPanel(message);
            JPanel buttonPanel = createButtonPanel(confirmMode);

            cardPanel.add(headerPanel, BorderLayout.NORTH);
            cardPanel.add(contentPanel, BorderLayout.CENTER);
            cardPanel.add(buttonPanel, BorderLayout.SOUTH);

            outerPanel.add(cardPanel, BorderLayout.CENTER);
            return outerPanel;
        }

        private JPanel createHeaderPanel(String title) {
            GradientHeaderPanel headerPanel = new GradientHeaderPanel();
            headerPanel.setLayout(new BorderLayout());
            headerPanel.setPreferredSize(new Dimension(400, 58));
            headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 22, 0, 12));
            headerPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));

            JLabel lblTitle = new JLabel(title);
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblTitle.setForeground(Color.WHITE);

            JButton btnClose = createCloseButton();
            btnClose.addActionListener(e -> dispose());

            headerPanel.add(lblTitle, BorderLayout.WEST);
            headerPanel.add(btnClose, BorderLayout.EAST);

            MouseAdapter dragAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dragPoint = e.getPoint();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    Point current = e.getLocationOnScreen();
                    setLocation(current.x - dragPoint.x, current.y - dragPoint.y);
                }
            };

            headerPanel.addMouseListener(dragAdapter);
            headerPanel.addMouseMotionListener(dragAdapter);

            return headerPanel;
        }

        private JPanel createContentPanel(String message) {
            JPanel contentPanel = new JPanel(new GridBagLayout());
            contentPanel.setOpaque(false);
            contentPanel.setBorder(BorderFactory.createEmptyBorder(34, 34, 22, 34));

            JLabel lblMessage = new JLabel("<html><center>" + escapeHtml(message) + "</center></html>");
            lblMessage.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblMessage.setForeground(COLOR_TEXT);
            lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
            lblMessage.setVerticalAlignment(SwingConstants.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            contentPanel.add(lblMessage, gbc);

            return contentPanel;
        }

        private JPanel createButtonPanel(boolean confirmMode) {
            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setOpaque(false);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 28, 0, 28));

            if (confirmMode) {
                GradientButton btnYes = new GradientButton("Đồng ý", COLOR_GREEN_START, COLOR_GREEN_END);
                btnYes.setPreferredSize(new Dimension(118, 40));
                btnYes.addActionListener(e -> {
                    confirmed = true;
                    dispose();
                });

                OutlineButton btnNo = new OutlineButton("Hủy", COLOR_MUTED, COLOR_BORDER);
                btnNo.setPreferredSize(new Dimension(96, 40));
                btnNo.addActionListener(e -> {
                    confirmed = false;
                    dispose();
                });

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(0, 0, 0, 12);
                buttonPanel.add(btnYes, gbc);

                gbc.gridx = 1;
                gbc.insets = new Insets(0, 0, 0, 0);
                buttonPanel.add(btnNo, gbc);
            } else {
                GradientButton btnOk = new GradientButton("OK", COLOR_GREEN_START, COLOR_GREEN_END);
                btnOk.setPreferredSize(new Dimension(126, 42));
                btnOk.addActionListener(e -> dispose());
                buttonPanel.add(btnOk);
            }

            return buttonPanel;
        }

        private JButton createCloseButton() {
            JButton button = new CloseButton();
            button.setPreferredSize(new Dimension(38, 38));
            return button;
}
    }

    private static Window getWindow(Component parent) {
        if (parent == null) {
            return null;
        }

        return SwingUtilities.getWindowAncestor(parent);
    }

    private static String escapeHtml(String text) {
        if (text == null) {
            return "";
        }

        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private static class ShadowPanel extends JPanel {

        public ShadowPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(15, 23, 42, 35));
            g2.fillRoundRect(18, 20, getWidth() - 34, getHeight() - 34, 28, 28);

            g2.setColor(new Color(15, 23, 42, 18));
            g2.fillRoundRect(14, 16, getWidth() - 30, getHeight() - 30, 28, 28);

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static class GradientHeaderPanel extends JPanel {

        public GradientHeaderPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gradient = new GradientPaint(
                    0, 0, COLOR_INDIGO,
                    getWidth(), getHeight(), COLOR_CYAN
            );

            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight() + 28, 26, 26);

            g2.dispose();
            super.paintComponent(graphics);
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

            g2.setColor(new Color(255, 255, 255, 170));
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, radius, radius);

            g2.dispose();
            super.paintComponent(graphics);
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

            setFont(new Font("Segoe UI", Font.BOLD, 14));
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
                g2.setColor(new Color(16, 185, 129, 70));
                g2.fillRoundRect(2, 3, getWidth() - 4, getHeight() - 1, arc, arc);
            }

            GradientPaint gradient = new GradientPaint(
                    0, 0, startColor,
                    getWidth(), getHeight(), endColor
            );

            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight() - 2, arc, arc);

            g2.setFont(getFont());
            g2.setColor(getForeground());
            drawCenteredText(g2, getText(), getWidth(), getHeight() - 2);

            g2.dispose();
        }
    }

    private static class OutlineButton extends JButton {

        private final Color textColor;
        private final Color borderColor;
        private boolean hovered = false;

        public OutlineButton(String text, Color textColor, Color borderColor) {
            super(text);
            this.textColor = textColor;
            this.borderColor = borderColor;

            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setForeground(textColor);
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
            Color bg = hovered ? new Color(248, 250, 252) : Color.WHITE;

            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.3f));
            g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, arc, arc);

            g2.setFont(getFont());
            g2.setColor(textColor);
            drawCenteredText(g2, getText(), getWidth(), getHeight());

            g2.dispose();
        }
    }

    private static class CloseButton extends JButton {

    private boolean hovered = false;

    public CloseButton() {
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
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

        int size = 26;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        if (hovered) {
            g2.setColor(new Color(255, 255, 255, 45));
            g2.fillOval(x, y, size, size);
        }

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int padding = 9;
        int x1 = x + padding;
        int y1 = y + padding;
        int x2 = x + size - padding;
        int y2 = y + size - padding;

        g2.drawLine(x1, y1, x2, y2);
        g2.drawLine(x2, y1, x1, y2);

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