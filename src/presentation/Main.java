package presentation;

public class Main {
    public static void main(String[] args) {
        try {
            StudentListViewController controller = AppFactory.createStudentListMVC();
            controller.loadStudentList();
            controller.getView().show(); // Bạn có thể thêm phương thức getView() trong controller nếu muốn
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}