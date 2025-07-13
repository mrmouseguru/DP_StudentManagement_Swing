package business;

import java.util.List;
import java.util.ArrayList;

import persistence.IStudentListViewRepository;
import persistence.StudentDTO;

public class StudentListViewService {
    private IStudentListViewRepository repository;

    public StudentListViewService(IStudentListViewRepository repository) {
        this.repository = repository;
    }

    public List<StudentListViewModel> fetchAllStudents() throws Exception {
        List<StudentDTO> dtos = repository.loadAll();
        List<StudentListViewModel> result = new ArrayList<>();
        for (StudentDTO dto : dtos) {
            result.add(new StudentListViewModel(
                dto.id,
                dto.name,
                dto.birth,
                dto.major,
                dto.gpa,
                dto.academicRank
            ));
        }
        return result;
    }
}