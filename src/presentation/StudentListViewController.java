package presentation;

import business.*;
import persistence.*;
import javax.swing.*;

public class StudentListViewController {
    private StudentListViewScreen view;
    private StudentListModel model;
    private StudentListViewService service;

    public StudentListViewController(StudentListViewScreen view, StudentListModel model, StudentListViewService service) {
        this.view = view;
        this.model = model;
        this.service = service;
    }

    public void loadStudentList() {
        try {
            model.setStudents(service.fetchAllStudents());
            view.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(), "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }
}