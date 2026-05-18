
package view;

import service.BaoCaoService;
import java.awt.*;
import javax.swing.*;

public class BaoCaoFrame extends JFrame {

    private BaoCaoService baoCaoService = new BaoCaoService();

    public BaoCaoFrame() {
        setTitle("Báo cáo Thống kê");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // ===== TIÊU ĐỀ =====
        JLabel lblTitle = new JLabel("BÁO CÁO THỐNG KÊ HỆ THỐNG", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // ===== NỘI DUNG THỐNG KÊ =====
        JPanel contentPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Tạo các label thống kê
        JLabel lblTongSV = createStatLabel("Tổng sinh viên:", baoCaoService.getTongSinhVien());
        JLabel lblTongTin = createStatLabel("Tổng tin tuyển dụng:", baoCaoService.getTongTinTuyenDung());
        JLabel lblTongUngTuyen = createStatLabel("Tổng ứng tuyển:", baoCaoService.getTongUngTuyen());
        JLabel lblChoDuyet = createStatLabel("Đang chờ duyệt:", baoCaoService.getUngTuyenChoDuyet());
        JLabel lblDaDuyet = createStatLabel("Đã duyệt:", baoCaoService.getUngTuyenDaDuyet());

        contentPanel.add(lblTongSV);
        contentPanel.add(lblTongTin);
        contentPanel.add(lblTongUngTuyen);
        contentPanel.add(lblChoDuyet);
        contentPanel.add(lblDaDuyet);

        add(contentPanel, BorderLayout.CENTER);

        // ===== NÚT LÀM MỚI =====
        JButton btnLamMoi = new JButton("Làm mới thống kê");
        btnLamMoi.setFont(new Font("Arial", Font.BOLD, 16));
        btnLamMoi.addActionListener(e -> {
            lblTongSV.setText("Tổng sinh viên: " + baoCaoService.getTongSinhVien());
            lblTongTin.setText("Tổng tin tuyển dụng: " + baoCaoService.getTongTinTuyenDung());
            lblTongUngTuyen.setText("Tổng ứng tuyển: " + baoCaoService.getTongUngTuyen());
            lblChoDuyet.setText("Đang chờ duyệt: " + baoCaoService.getUngTuyenChoDuyet());
            lblDaDuyet.setText("Đã duyệt: " + baoCaoService.getUngTuyenDaDuyet());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLamMoi);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createStatLabel(String label, int value) {
        JLabel lbl = new JLabel(label + " " + value);
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        return lbl;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BaoCaoFrame().setVisible(true));
    }
}