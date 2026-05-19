package view;

import service.BaoCaoService;
import java.awt.*;
import javax.swing.*;

public class BaoCaoFrame extends JFrame {

    private BaoCaoService baoCaoService = new BaoCaoService();

    private JLabel lblTongTin, lblTongUngTuyen, lblChoDuyet, lblDaDuyet, lblPhongVan, lblTrungTuyen;

    public BaoCaoFrame() {
        setTitle("Báo cáo Thống kê");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Tiêu đề
        JLabel lblTitle = new JLabel("BÁO CÁO THỐNG KÊ HỆ THỐNG", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        add(lblTitle, BorderLayout.NORTH);

        // Nội dung thống kê
        JPanel contentPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        lblTongTin = createStatCard("Tổng tin tuyển dụng", baoCaoService.getTongTinTuyenDung());
        lblTongUngTuyen = createStatCard("Tổng hồ sơ ứng tuyển", baoCaoService.getTongUngTuyen());
        lblChoDuyet = createStatCard("Chờ duyệt", baoCaoService.getSoHoSoTheoTrangThai("Chờ duyệt"));
        lblDaDuyet = createStatCard("Đã duyệt", baoCaoService.getSoHoSoTheoTrangThai("Đã duyệt"));
        lblPhongVan = createStatCard("Phỏng vấn", baoCaoService.getSoHoSoTheoTrangThai("Phỏng vấn"));
        lblTrungTuyen = createStatCard("Trúng tuyển", baoCaoService.getSoHoSoTheoTrangThai("Trúng tuyển"));

        contentPanel.add(lblTongTin);
        contentPanel.add(lblTongUngTuyen);
        contentPanel.add(lblChoDuyet);
        contentPanel.add(lblDaDuyet);
        contentPanel.add(lblPhongVan);
        contentPanel.add(lblTrungTuyen);

        add(contentPanel, BorderLayout.CENTER);

        // Nút Làm mới
        JButton btnLamMoi = new JButton("Làm mới thống kê");
        btnLamMoi.setFont(new Font("Arial", Font.BOLD, 18));
        btnLamMoi.setPreferredSize(new Dimension(200, 45));
        btnLamMoi.addActionListener(e -> capNhatThongKe());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLamMoi);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createStatCard(String title, int value) {
        JLabel lbl = new JLabel("<html><center>" + title + "<br><b style='font-size:28px'>" + value + "</b></center></html>");
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        lbl.setOpaque(true);
        lbl.setBackground(new Color(240, 248, 255));
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        return lbl;
    }

    private void capNhatThongKe() {
        lblTongTin.setText("<html><center>Tổng tin tuyển dụng<br><b style='font-size:28px'>" + baoCaoService.getTongTinTuyenDung() + "</b></center></html>");
        lblTongUngTuyen.setText("<html><center>Tổng hồ sơ ứng tuyển<br><b style='font-size:28px'>" + baoCaoService.getTongUngTuyen() + "</b></center></html>");
        lblChoDuyet.setText("<html><center>Chờ duyệt<br><b style='font-size:28px'>" + baoCaoService.getSoHoSoTheoTrangThai("Chờ duyệt") + "</b></center></html>");
        lblDaDuyet.setText("<html><center>Đã duyệt<br><b style='font-size:28px'>" + baoCaoService.getSoHoSoTheoTrangThai("Đã duyệt") + "</b></center></html>");
        lblPhongVan.setText("<html><center>Phỏng vấn<br><b style='font-size:28px'>" + baoCaoService.getSoHoSoTheoTrangThai("Phỏng vấn") + "</b></center></html>");
        lblTrungTuyen.setText("<html><center>Trúng tuyển<br><b style='font-size:28px'>" + baoCaoService.getSoHoSoTheoTrangThai("Trúng tuyển") + "</b></center></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BaoCaoFrame().setVisible(true));
    }
}