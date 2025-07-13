package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistence.StudentDTO;

public class StudentListViewRepositoryImpl implements StudentListViewRepository {
    private Connection conn;

    public StudentListViewRepositoryImpl(String dbPath) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    @Override
    public List<StudentDTO> loadAll() throws SQLException {
        List<StudentDTO> list = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Students");

        while (rs.next()) {
            StudentDTO dto = new StudentDTO();
            dto.id = rs.getString("id");
            dto.name = rs.getString("name");
            dto.birth = rs.getString("birth");
            dto.major = rs.getString("major");
            dto.java = rs.getObject("java") != null ? rs.getDouble("java") : null;
            dto.html = rs.getObject("html") != null ? rs.getDouble("html") : null;
            dto.css = rs.getObject("css") != null ? rs.getDouble("css") : null;
            dto.marketing = rs.getObject("marketing") != null ? rs.getDouble("marketing") : null;
            dto.sales = rs.getObject("sales") != null ? rs.getDouble("sales") : null;
            list.add(dto);
        }

        rs.close();
        st.close();
        return list;
    }
}
