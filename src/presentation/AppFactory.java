package presentation;

import business.*;
import persistence.*;

public class AppFactory {
    public static StudentListViewController createStudentListMVC() throws Exception {
        IStudentListViewRepository repo = new StudentListViewRepositoryImpl("students.db");
        StudentListViewService service = new StudentListViewService(repo);
        StudentListModel model = new StudentListModel();
        StudentListViewScreen view = new StudentListViewScreen();
        model.addObserver(view);
        return new StudentListViewController(view, model, service);
    }
}