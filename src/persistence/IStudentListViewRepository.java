package persistence;


// import business.Student;
import java.sql.SQLException;
import java.util.List;

import java.util.List;
public interface IStudentListViewRepository {
    List<StudentDTO> loadAll() throws SQLException;
}
