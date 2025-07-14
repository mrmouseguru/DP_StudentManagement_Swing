package presentation;

import java.util.List;
import business.StudentListViewModel;

public class StudentListModel {
    private List<StudentListViewModel> students;

    public List<StudentListViewModel> getStudents() {
        return students;
    }

    public void setStudents(List<StudentListViewModel> students) {
        this.students = students;
    }
}