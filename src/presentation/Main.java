package presentation;

import java.sql.SQLException;

import business.*;
import persistence.*;

public class Main {
    public static void main(String[] args) {
        IStudentListViewRepository repo = null;
        try {
            repo = new StudentListViewRepositoryImpl("students.db");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StudentListViewService service = new StudentListViewService(repo);
        StudentListModel model = new StudentListModel();
        StudentListViewScreen view = new StudentListViewScreen();
        StudentListViewController controller = new StudentListViewController(view, model, service);

        controller.loadStudentList();
        view.show();
    }
}