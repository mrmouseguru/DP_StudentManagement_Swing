package presentation;

import business.*;
import business.command.CommandInvoker;
import persistence.*;
import javax.swing.*;
import java.util.List;

public class StudentListViewController {
    private StudentListViewScreen view;
    private StudentListModel model;
    private StudentListViewService service;
    private CommandInvoker commandInvoker;

    public StudentListViewController(StudentListViewScreen view, StudentListModel model, StudentListViewService service) {
        this.view = view;
        this.model = model;
        this.service = service;
        this.commandInvoker = new CommandInvoker();
    }

    public void loadStudentList() {
        try {
            // Sử dụng Command pattern: service implement ICommand và được thực thi qua CommandInvoker
            List<StudentListViewModel> students = commandInvoker.executeCommand(service);
            model.setStudents(students);
        } catch (Exception e) {
            view.showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    public StudentListViewScreen getView() {
        return view;
    }
}
