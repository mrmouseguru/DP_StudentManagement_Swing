// File: presentation/ViewStudentApp.java
package presentation;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ViewStudentApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Danh sách sinh viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 450);
        frame.setLayout(new BorderLayout());

        // ===== Top panel =====
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JTextField txtSearch = new JTextField("Search");
        JButton btnSearch = new JButton("\uD83D\uDD0D"); // Unicode icon kính lúp
        JButton btnAdd = new JButton("Thêm");

        // Custom style
        txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnSearch.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnAdd.setBackground(new Color(33, 106, 255));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(txtSearch, BorderLayout.CENTER);
        searchPanel.add(btnSearch, BorderLayout.EAST);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(btnAdd, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);

        // ===== Table =====
        String[] columns = {"STT", "Mã SV", "Tên SV", "Ngày sinh", "Ngành học", "Điểm TB", "Học lực"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Custom header style
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(173, 216, 0));  // màu xanh lá
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Custom cell style
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setGridColor(new Color(184, 220, 90));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(220, 240, 200));

        // Scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        // ===== Show frame =====
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
