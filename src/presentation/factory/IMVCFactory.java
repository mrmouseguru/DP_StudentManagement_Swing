package presentation.factory;

import presentation.*;
import business.*;

public interface IMVCFactory {
    StudentListModel createModel();
    StudentListViewScreen createView();
    StudentListViewController createController();
    StudentListViewCommand createService();
}