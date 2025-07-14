package presentation;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import business.StudentListViewModel;

public class StudentListViewScreen implements StudentListModelObserver {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentListViewScreen() {
        frame = new JFrame("Danh sách sinh viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 450);
        frame.setLayout(new BorderLayout());

        // ==== TOP PANEL ====
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JTextField txtSearch = new JTextField("Search");
        JButton btnSearch = new JButton("🔍");
        JButton btnAdd = new JButton("Thêm");

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

        // ==== TABLE ====
        String[] columns = { "STT", "Mã SV", "Tên SV", "Ngày sinh", "Ngành học", "Điểm TB", "Học lực" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(new Color(173, 216, 0));
        header.setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setGridColor(new Color(184, 220, 90));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(220, 240, 200));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onStudentListChanged(StudentListModel model) {
        setModel(model);
    }

    public void setModel(StudentListModel model) {
        tableModel.setRowCount(0);
        int i = 1;
        for (business.StudentListViewModel s : model.getStudents()) {
            tableModel.addRow(new Object[] {
                i++, s.id, s.name, s.birth, s.major,
                s.gpa != null ? String.format("%.2f", s.gpa) : "",
                s.academicRank != null ? s.academicRank : ""
            });
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }
}