package business;

import persistence.*;
import business.StudentFactory;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddStudentService {
    private IStudentRepository repository;

    public AddStudentService(IStudentRepository repository) {
        this.repository = repository;
    }

public void addStudent(AddStudentRequest req) throws Exception {
        // Validation
        validateId(req.id);
        validateName(req.name);
        validateBirth(req.birth);
        validateMajor(req.major);

        Student student = StudentFactory.create(req);
        StudentDTO dto = this.toDTO(student);
        repository.insert(dto);
    }
    // Chuyển đối tượng Student sang StudentDTO
    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.id = student.getId();
        dto.name = student.getName();
        dto.birth = student.getBirthDate();
        dto.major = student.getMajor();
        dto.gpa = student.calculateGPA();
        dto.academicRank = student.classifyAcademic();
        if (student instanceof SoftwareStudent) {
            SoftwareStudent s = (SoftwareStudent) student;
            dto.java = s.getJavaScore();
            dto.html = s.getHtmlScore();
            dto.css = s.getCssScore();
        } else if (student instanceof EconomicsStudent) {
            EconomicsStudent e = (EconomicsStudent) student;
            dto.marketing = e.getMarketingScore();
            dto.sales = e.getSalesScore();
        }
        return dto;
    }

    private void validateId(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) throw new Exception("Mã sinh viên không được để trống");
        if (!id.matches("[a-zA-Z0-9]+")) throw new Exception("Mã sinh viên phải là chữ hoặc số");
        if (repository.exists(id)) throw new Exception("Mã sinh viên đã tồn tại");
    }

    private void validateName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) throw new Exception("Họ tên không được để trống");
    }

    private void validateBirth(String birth) throws Exception {
        LocalDate dob;
        try {
            dob = LocalDate.parse(birth);
        } catch (DateTimeParseException ex) {
            throw new Exception("Ngày sinh không đúng định dạng yyyy-MM-dd");
        }
        if (dob.plusYears(16).isAfter(LocalDate.now())) throw new Exception("Sinh viên phải từ 16 tuổi trở lên");
    }

    private void validateMajor(String major) throws Exception {
        if (!major.equals("Kỹ thuật phần mềm") && !major.equals("Kinh tế"))
            throw new Exception("Ngành học không hợp lệ");
    }

    public static double parseScore(String input, String label) throws Exception {
        if (input == null || input.trim().isEmpty())
            throw new Exception("Điểm " + label + " không được để trống");
        double score;
        try {
            score = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new Exception("Điểm " + label + " phải là số thực");
        }
        if (score < 0 || score > 10)
            throw new Exception("Điểm " + label + " phải từ 0 đến 10");
        return score;
    }
}

