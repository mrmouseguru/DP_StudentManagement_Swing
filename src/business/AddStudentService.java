package business;

import persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddStudentService {
    private IStudentRepository repository;

    public AddStudentService(IStudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String id,
                           String name,
                           String birth,
                           String major,
                           String javaStr,
                           String htmlStr,
                           String cssStr,
                           String marketingStr,
                           String salesStr) throws Exception {
        // Validation
        validateId(id);
        validateName(name);
        validateBirth(birth);
        validateMajor(major);

        StudentDTO dto = new StudentDTO();
        dto.id = id;
        dto.name = name;
        dto.birth = birth;
        dto.major = major;
        if (major.equals("Kỹ thuật phần mềm")) {
            dto.java = parseScore(javaStr, "Java");
            dto.html = parseScore(htmlStr, "HTML");
            dto.css = parseScore(cssStr, "CSS");
        } else {
            dto.marketing = parseScore(marketingStr, "Marketing");
            dto.sales = parseScore(salesStr, "Sales");
        }
        repository.insert(dto);
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

    private double parseScore(String input, String label) throws Exception {
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

