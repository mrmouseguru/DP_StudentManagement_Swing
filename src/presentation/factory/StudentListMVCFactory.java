package presentation.factory;

import presentation.*;
import business.*;
import persistence.*;

public class StudentListMVCFactory implements IMVCFactory {
    @Override
    public StudentListModel createModel() {
        return new StudentListModel();
    }

    @Override
    public StudentListViewScreen createView() {
        return new StudentListViewScreen();
    }

    @Override
    public StudentListViewService createService() {
        try {
            IStudentListViewRepository repo = new StudentListViewRepositoryImpl("students.db");
            return new StudentListViewService(repo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentListViewController createController() {
        StudentListModel model = createModel();
        StudentListViewScreen view = createView();
        StudentListViewService service = createService();
        
        // Đăng ký observer
        model.addObserver(view);
        
        return new StudentListViewController(view, model, service);
    }
}