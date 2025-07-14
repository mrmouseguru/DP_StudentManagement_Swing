package presentation;

import presentation.factory.*;

public class Main {
    public static void main(String[] args) {
        try {
            IMVCFactory factory = new StudentListMVCFactory();
            StudentListViewController controller = factory.createController();
            controller.loadStudentList();
            controller.getView().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}