package business;

import java.util.List;
import java.util.ArrayList;
import business.command.ICommand;
import persistence.IStudentListViewRepository;
import persistence.StudentDTO;

public class StudentListViewCommand implements ICommand<List<StudentListViewModel>> {
    private IStudentListViewRepository repository;

    public StudentListViewCommand(IStudentListViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentListViewModel> execute() throws Exception {
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