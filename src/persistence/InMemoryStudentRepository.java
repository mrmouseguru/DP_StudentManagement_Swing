package persistence;

import persistence.StudentDTO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryStudentRepository implements IStudentRepository {
    private Map<String, StudentDTO> studentMap = new HashMap<>();

    @Override
    public boolean exists(String id) throws SQLException {
        return studentMap.containsKey(id);
    }

    @Override
    public void insert(StudentDTO student) throws SQLException {
        studentMap.put(student.id, student);
    }

    // Tuỳ chọn: dùng để test hoặc in danh sách
    public Map<String, StudentDTO> getAllStudents() {
        return studentMap;
    }
}
