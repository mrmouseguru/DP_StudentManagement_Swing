package persistence;

import business.Student;
import java.sql.SQLException;

public interface IStudentRepository {
    boolean exists(String id) throws SQLException;
    void insert(Student student) throws SQLException;
}