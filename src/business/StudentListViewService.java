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
            Double gpa = null;
            String academicRank = "";
            if ("Kỹ thuật phần mềm".equals(dto.major)) {
                int count = 0;
                double sum = 0;
                if (dto.java != null) { sum += dto.java; count++; }
                if (dto.html != null) { sum += dto.html; count++; }
                if (dto.css != null) { sum += dto.css; count++; }
                gpa = count > 0 ? sum / count : null;
            } else if ("Kinh tế".equals(dto.major)) {
                int count = 0;
                double sum = 0;
                if (dto.marketing != null) { sum += dto.marketing; count++; }
                if (dto.sales != null) { sum += dto.sales; count++; }
                gpa = count > 0 ? sum / count : null;
            }
            if (gpa != null) {
                if (gpa >= 8.0) academicRank = "Giỏi";
                else if (gpa >= 6.5) academicRank = "Khá";
                else if (gpa >= 5.0) academicRank = "Trung bình";
                else academicRank = "Yếu";
            }
            result.add(new StudentListViewModel(dto.id, dto.name, dto.birth, dto.major, gpa, academicRank));
        }
        return result;
    }
}