package persistence;


import business.Student;
import java.sql.SQLException;
import java.util.List;

public interface StudentListViewRepository {
    List<Student> loadAll() throws SQLException;
}
