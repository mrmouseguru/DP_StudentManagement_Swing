package business;

import java.util.List;

import persistence.StudentListViewRepository;
import persistence.StudentDTO;

public class StudentListViewService {
    private StudentListViewRepository repository;

    public StudentListViewService(StudentListViewRepository repository) {
        this.repository = repository;
    }

    public List<Student> fetchAllStudents() throws Exception {
        List<StudentDTO> dtos = repository.loadAll();
        List<Student> students = new java.util.ArrayList<>();
        for (StudentDTO dto : dtos) {
            if ("Kỹ thuật phần mềm".equals(dto.major)) {
                students.add(new SoftwareStudent(dto.id, dto.name, dto.birth, dto.java, dto.html, dto.css));
            } else {
                students.add(new EconomicsStudent(dto.id, dto.name, dto.birth, dto.marketing, dto.sales));
            }
        }
        return students;
    }
}