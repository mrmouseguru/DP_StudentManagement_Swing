package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.EconomicsStudent;
import business.SoftwareStudent;
import business.Student;

public class StudentListViewRepositoryImpl implements StudentListViewRepository {
    private Connection conn;

    public StudentListViewRepositoryImpl(String dbPath) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    @Override
    public List<Student> loadAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Students");

        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String birth = rs.getString("birth");
            String major = rs.getString("major");

            Student s;
            if ("Kỹ thuật phần mềm".equals(major)) {
                s = new SoftwareStudent(id, name, birth,
                        rs.getDouble("java"), rs.getDouble("html"), rs.getDouble("css"));
            } else {
                s = new EconomicsStudent(id, name, birth,
                        rs.getDouble("marketing"), rs.getDouble("sales"));
            }

            list.add(s);
        }

        rs.close();
        st.close();
        return list;
    }
}
