package persistence;


import java.sql.*;

public class SQLiteStudentRepository implements IStudentRepository {
    private Connection conn;

    public SQLiteStudentRepository(String dbPath) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        System.out.println("Connected!");

        createTable();
    }

    private void createTable() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Students (" +
                            "id TEXT PRIMARY KEY, name TEXT, birth TEXT, major TEXT, " +
                            "java REAL, html REAL, css REAL, marketing REAL, sales REAL, gpa REAL, hocluc TEXT)");
        stmt.close();
    }

    @Override
    public boolean exists(String id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT id FROM Students WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        boolean found = rs.next();
        rs.close(); ps.close();
        return found;
    }

   

    @Override
    public void insert(StudentDTO student) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO Students VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, student.id);
        ps.setString(2, student.name);
        ps.setString(3, student.birth);
        ps.setString(4, student.major);
        if ("Kỹ thuật phần mềm".equals(student.major)) {
            ps.setObject(5, student.java, java.sql.Types.REAL);
            ps.setObject(6, student.html, java.sql.Types.REAL);
            ps.setObject(7, student.css, java.sql.Types.REAL);
            ps.setNull(8, java.sql.Types.REAL);
            ps.setNull(9, java.sql.Types.REAL);
        } else {
            ps.setNull(5, java.sql.Types.REAL);
            ps.setNull(6, java.sql.Types.REAL);
            ps.setNull(7, java.sql.Types.REAL);
            ps.setObject(8, student.marketing, java.sql.Types.REAL);
            ps.setObject(9, student.sales, java.sql.Types.REAL);
        }
        ps.setObject(10, student.gpa, java.sql.Types.REAL);
        ps.setString(11, student.academicRank);
        ps.executeUpdate();
        ps.close();
    }
}

