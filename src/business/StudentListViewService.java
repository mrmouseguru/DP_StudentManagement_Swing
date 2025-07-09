package business;

import java.util.List;

import persistence.StudentListViewRepository;

public class StudentListViewService {
    private StudentListViewRepository repository;

    public StudentListViewService(StudentListViewRepository repository) {
        this.repository = repository;
    }

    public List<Student> fetchAllStudents() throws Exception {
        return repository.loadAll();
    }
}