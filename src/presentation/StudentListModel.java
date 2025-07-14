package presentation;

import java.util.List;
import java.util.ArrayList;
import business.StudentListViewModel;

public class StudentListModel {
    private List<StudentListViewModel> students = new ArrayList<>();
    private final List<StudentListModelObserver> observers = new ArrayList<>();

    public List<StudentListViewModel> getStudents() {
        return students;
    }

    public void setStudents(List<StudentListViewModel> students) {
        this.students = students;
        notifyObservers();
    }

    public void addObserver(StudentListModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StudentListModelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (StudentListModelObserver observer : observers) {
            observer.onStudentListChanged(this);
        }
    }
}