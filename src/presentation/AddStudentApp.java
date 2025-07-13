package presentation;
import javax.swing.JFrame;

import business.AddStudentService;
import business.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddStudentApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Thêm sinh viên");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2));

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtBirth = new JTextField();
        JComboBox<String> cboMajor = new JComboBox<>(new String[]{"Kỹ thuật phần mềm", "Kinh tế"});
        JTextField txtJava = new JTextField();
        JTextField txtHTML = new JTextField();
        JTextField txtCSS = new JTextField();
        JTextField txtMarketing = new JTextField();
        JTextField txtSales = new JTextField();
        JButton btnAdd = new JButton("Thêm");

        frame.add(new JLabel("Mã SV:")); frame.add(txtId);
        frame.add(new JLabel("Họ tên:")); frame.add(txtName);
        frame.add(new JLabel("Ngày sinh (yyyy-mm-dd):")); frame.add(txtBirth);
        frame.add(new JLabel("Ngành học:")); frame.add(cboMajor);
        frame.add(new JLabel("Java:")); frame.add(txtJava);
        frame.add(new JLabel("HTML:")); frame.add(txtHTML);
        frame.add(new JLabel("CSS:")); frame.add(txtCSS);
        frame.add(new JLabel("Marketing:")); frame.add(txtMarketing);
        frame.add(new JLabel("Sales:")); frame.add(txtSales);
        frame.add(new JLabel("")); frame.add(btnAdd);

        cboMajor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isSoftware = cboMajor.getSelectedItem().equals("Kỹ thuật phần mềm");
                txtJava.setEnabled(isSoftware);
                txtHTML.setEnabled(isSoftware);
                txtCSS.setEnabled(isSoftware);
                txtMarketing.setEnabled(!isSoftware);
                txtSales.setEnabled(!isSoftware);
            }
        });
        cboMajor.setSelectedIndex(0);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	 IStudentRepository repo = new SQLiteStudentRepository("students.db");
                	AddStudentService service = new AddStudentService(
                			repo);
                    service.addStudent(
                        txtId.getText(),
                        txtName.getText(),
                        txtBirth.getText(),
                        cboMajor.getSelectedItem().toString(),
                        txtJava.getText(),
                        txtHTML.getText(),
                        txtCSS.getText(),
                        txtMarketing.getText(),
                        txtSales.getText()
                    );
                    JOptionPane.showMessageDialog(frame, "Thêm sinh viên thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Lỗi: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}
