package persistence;

import business.Student;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryStudentRepository implements IStudentRepository {
    private Map<String, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(String id) throws SQLException {
        return studentMap.containsKey(id);
    }

    @Override
    public void insert(Student student) throws SQLException {
        studentMap.put(student.getId(), student);
    }

    // Tuỳ chọn: dùng để test hoặc in danh sách
    public Map<String, Student> getAllStudents() {
        return studentMap;
    }
}
