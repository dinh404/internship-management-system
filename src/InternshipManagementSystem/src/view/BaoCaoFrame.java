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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BaoCaoFrame extends JFrame {

    private JTable tblBaoCao;
    private DefaultTableModel tableModel;

    private final Color COLOR_BACKGROUND = Color.decode("#F8FAFC");
    private final Color COLOR_CARD = Color.decode("#FFFFFF");
    private final Color COLOR_NAVY = Color.decode("#1E3A8A");
    private final Color COLOR_TEAL = Color.decode("#0D9488");
    private final Color COLOR_ORANGE = Color.decode("#C2410C");
    private final Color COLOR_GREEN = Color.decode("#059669");
    private final Color COLOR_TEXT = Color.decode("#1E293B");
    private final Color COLOR_MUTED = Color.decode("#64748B");
    private final Color COLOR_BORDER = Color.decode("#E2E8F0");
    private final Color COLOR_GRID = Color.decode("#CBD5E1");
    private final Color COLOR_BLUE_LIGHT = Color.decode("#EFF6FF");
    private final Color COLOR_CYAN_LIGHT = Color.decode("#E0F2FE");
    private final Color COLOR_ORANGE_LIGHT = Color.decode("#FFEDD5");
    private final Color COLOR_GREEN_LIGHT = Color.decode("#ECFDF5");

    public BaoCaoFrame() {
        initUI();
        loadDataToTable();
    }

    private void initUI() {
        setTitle("Báo cáo thống kê");
        setSize(1150, 740);
        setMinimumSize(new Dimension(1150, 740));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel rootPanel = new JPanel(new BorderLayout(0, 22));
        rootPanel.setBackground(COLOR_BACKGROUND);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(24, 28, 28, 28));

        rootPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        rootPanel.add(createContentPanel(), BorderLayout.CENTER);

        setContentPane(rootPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("BÁO CÁO THỐNG KÊ");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_NAVY);

        JLabel lblSubtitle = new JLabel("Hệ thống tổng hợp chỉ số vận hành, tiến độ ứng tuyển và hiệu suất kết nối doanh nghiệp");
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

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        JPanel cardPanel = createStatisticCardsPanel();
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(cardPanel, gbc);

        JPanel middlePanel = createMiddlePanel();
        gbc.gridy = 1;
        gbc.weighty = 0.42;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(middlePanel, gbc);

        RoundedPanel tablePanel = createTablePanel();
        gbc.gridy = 2;
        gbc.weighty = 0.58;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(tablePanel, gbc);

        return contentPanel;
    }

    private JPanel createStatisticCardsPanel() {
        JPanel cardPanel = new JPanel(new GridLayout(1, 4, 18, 0));
        cardPanel.setOpaque(false);
        cardPanel.setPreferredSize(new Dimension(0, 118));

        cardPanel.add(createStatisticCard(
                "Tổng số sinh viên",
                "128",
                "Hồ sơ đang quản lý",
                COLOR_BLUE_LIGHT,
                COLOR_NAVY
        ));

        cardPanel.add(createStatisticCard(
                "Tổng tin tuyển dụng",
                "18",
                "Vị trí thực tập đang ghi nhận",
                COLOR_CYAN_LIGHT,
                COLOR_TEAL
        ));

        cardPanel.add(createStatisticCard(
                "Tổng đơn ứng tuyển",
                "36",
                "Hồ sơ gửi đến doanh nghiệp",
                COLOR_ORANGE_LIGHT,
                COLOR_ORANGE
        ));

        cardPanel.add(createStatisticCard(
                "Tỷ lệ hoàn thành",
                "72%",
                "Sinh viên có tiến độ tích cực",
                COLOR_GREEN_LIGHT,
                COLOR_GREEN
        ));

        return cardPanel;
    }

    private RoundedPanel createStatisticCard(String title, String value, String description, Color backgroundColor, Color accentColor) {
        RoundedPanel card = new RoundedPanel(22, backgroundColor);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitle.setForeground(accentColor);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblValue.setForeground(accentColor);

        JLabel lblDescription = new JLabel(description);
        lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescription.setForeground(COLOR_MUTED);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        card.add(lblDescription, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createMiddlePanel() {
        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        RoundedPanel chartCard = new RoundedPanel(24, COLOR_CARD);
        chartCard.setLayout(new BorderLayout());
        chartCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblChartTitle = new JLabel("Biểu đồ trạng thái ứng tuyển");
        lblChartTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblChartTitle.setForeground(COLOR_TEXT);

        BarChartPanel chartPanel = new BarChartPanel(
                new String[]{"Chờ duyệt", "Đã duyệt", "Từ chối"},
                new int[]{12, 18, 6},
                COLOR_NAVY,
                COLOR_TEAL
        );

        chartCard.add(lblChartTitle, BorderLayout.NORTH);
        chartCard.add(chartPanel, BorderLayout.CENTER);

        RoundedPanel insightCard = createInsightCard();

        gbc.gridx = 0;
        gbc.weightx = 0.60;
        gbc.insets = new Insets(0, 0, 0, 18);
        middlePanel.add(chartCard, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.40;
        gbc.insets = new Insets(0, 0, 0, 0);
        middlePanel.add(insightCard, gbc);

        return middlePanel;
    }

    private RoundedPanel createInsightCard() {
        RoundedPanel insightCard = new RoundedPanel(24, COLOR_CARD);
        insightCard.setLayout(new BorderLayout());
        insightCard.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel lblTitle = new JLabel("Nhận xét nhanh");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT);

        JLabel lblContent = new JLabel(
                "<html>"
                + "<div style='line-height:170%; color:#64748B;'>"
                + "<b style='color:#1E3A8A;'>• Tổng quan:</b> Số lượng hồ sơ ứng tuyển đang duy trì ổn định.<br><br>"
                + "<b style='color:#0D9488;'>• Kết nối doanh nghiệp:</b> Các vị trí thực tập có mức độ quan tâm tốt từ sinh viên.<br><br>"
                + "<b style='color:#C2410C;'>• Cần xử lý:</b> Nhóm hồ sơ chờ duyệt cần được kiểm tra để rút ngắn thời gian phản hồi.<br><br>"
                + "<b style='color:#059669;'>• Đề xuất:</b> Ưu tiên theo dõi các ngành có tỷ lệ ứng tuyển cao."
                + "</div>"
                + "</html>"
        );
        lblContent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblContent.setForeground(COLOR_MUTED);
        lblContent.setVerticalAlignment(SwingConstants.TOP);

        insightCard.add(lblTitle, BorderLayout.NORTH);
        insightCard.add(lblContent, BorderLayout.CENTER);

        return insightCard;
    }

    private RoundedPanel createTablePanel() {
        RoundedPanel tableCard = new RoundedPanel(24, COLOR_CARD);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));

        JLabel lblTableTitle = new JLabel("Bảng tổng hợp theo nhóm ngành học");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTableTitle.setForeground(COLOR_TEXT);
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

        String[] columns = {
            "Nhóm ngành",
            "Sinh viên",
            "Tin tuyển dụng",
            "Đơn ứng tuyển",
            "Đã duyệt",
            "Từ chối",
            "Tỷ lệ hoàn thành"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblBaoCao = new JTable(tableModel);
        tblBaoCao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblBaoCao.setRowHeight(36);
        tblBaoCao.setForeground(COLOR_TEXT);
        tblBaoCao.setGridColor(COLOR_GRID);
        tblBaoCao.setShowVerticalLines(true);
        tblBaoCao.setShowHorizontalLines(true);
        tblBaoCao.setIntercellSpacing(new Dimension(1, 1));
        tblBaoCao.setSelectionBackground(Color.decode("#DBEAFE"));
        tblBaoCao.setSelectionForeground(COLOR_TEXT);

        tblBaoCao.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblBaoCao.getTableHeader().setForeground(Color.WHITE);
        tblBaoCao.getTableHeader().setBackground(COLOR_NAVY);
        tblBaoCao.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tblBaoCao.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tblBaoCao.getColumnCount(); i++) {
            tblBaoCao.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblBaoCao.getColumnModel().getColumn(0).setPreferredWidth(170);
        tblBaoCao.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblBaoCao.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblBaoCao.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblBaoCao.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblBaoCao.getColumnModel().getColumn(5).setPreferredWidth(90);
        tblBaoCao.getColumnModel().getColumn(6).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tblBaoCao);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        tableCard.add(lblTableTitle, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);

        tableModel.addRow(new Object[]{
            "Công nghệ thông tin",
            "52",
            "8",
            "18",
            "11",
            "3",
            "78%"
        });

        tableModel.addRow(new Object[]{
            "Hệ thống thông tin",
            "31",
            "4",
            "9",
            "5",
            "2",
            "67%"
        });

        tableModel.addRow(new Object[]{
            "Quản trị kinh doanh",
            "24",
            "3",
            "6",
            "4",
            "1",
            "75%"
        });

        tableModel.addRow(new Object[]{
            "Kế toán - Tài chính",
            "21",
            "3",
            "3",
            "2",
            "0",
            "82%"
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BaoCaoFrame().setVisible(true));
    }

    private static class BarChartPanel extends JPanel {

        private final String[] labels;
        private final int[] values;
        private final Color startColor;
        private final Color endColor;
        private final Color textColor = Color.decode("#1E293B");
        private final Color mutedColor = Color.decode("#64748B");
        private final Color gridColor = Color.decode("#E2E8F0");

        public BarChartPanel(String[] labels, int[] values, Color startColor, Color endColor) {
            this.labels = labels;
            this.values = values;
            this.startColor = startColor;
            this.endColor = endColor;
            setOpaque(false);
            setPreferredSize(new Dimension(500, 240));
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            int width = getWidth();
            int height = getHeight();

            int leftPadding = 46;
            int rightPadding = 24;
            int topPadding = 28;
            int bottomPadding = 38;

            int chartWidth = width - leftPadding - rightPadding;
            int chartHeight = height - topPadding - bottomPadding;

            int maxValue = getMaxValue();
            int roundedMax = Math.max(20, ((maxValue + 4) / 5) * 5);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.setColor(mutedColor);

            for (int i = 0; i <= 4; i++) {
                int value = roundedMax * i / 4;
                int y = topPadding + chartHeight - (chartHeight * i / 4);

                g2.setColor(gridColor);
                g2.setStroke(new BasicStroke(1f));
                g2.drawLine(leftPadding, y, width - rightPadding, y);

                g2.setColor(mutedColor);
                String label = String.valueOf(value);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(label, leftPadding - fm.stringWidth(label) - 8, y + 4);
            }

            int barGroupWidth = chartWidth / values.length;
            int barWidth = Math.min(76, barGroupWidth / 2);

            for (int i = 0; i < values.length; i++) {
                int barHeight = (int) ((double) values[i] / roundedMax * chartHeight);
                int x = leftPadding + i * barGroupWidth + (barGroupWidth - barWidth) / 2;
                int y = topPadding + chartHeight - barHeight;

                GradientPaint gradient = new GradientPaint(
                        x, y, startColor,
                        x + barWidth, y + barHeight, endColor
                );

                g2.setPaint(gradient);
                g2.fillRoundRect(x, y, barWidth, barHeight, 18, 18);

                g2.setColor(new Color(15, 23, 42, 24));
                g2.fillRoundRect(x + 4, y + barHeight - 2, barWidth - 8, 4, 8, 8);

                g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
                g2.setColor(textColor);
                String valueText = String.valueOf(values[i]);
                FontMetrics valueFm = g2.getFontMetrics();
                g2.drawString(valueText, x + (barWidth - valueFm.stringWidth(valueText)) / 2, y - 8);

                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g2.setColor(mutedColor);
                String label = labels[i];
                FontMetrics labelFm = g2.getFontMetrics();
                int labelX = x + (barWidth - labelFm.stringWidth(label)) / 2;
                g2.drawString(label, labelX, topPadding + chartHeight + 28);
            }

            g2.setColor(gridColor);
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawLine(leftPadding, topPadding + chartHeight, width - rightPadding, topPadding + chartHeight);

            g2.dispose();
        }

        private int getMaxValue() {
            int max = 0;

            for (int value : values) {
                if (value > max) {
                    max = value;
                }
            }

            return max;
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
}