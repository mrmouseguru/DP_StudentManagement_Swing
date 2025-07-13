package persistence;

// import business.Student;
import java.sql.SQLException;

import persistence.StudentDTO;
public interface IStudentRepository {
    boolean exists(String id) throws SQLException;
    void insert(StudentDTO student) throws SQLException;
}