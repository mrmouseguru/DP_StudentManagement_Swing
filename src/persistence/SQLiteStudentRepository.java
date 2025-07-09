package persistence;

import business.Student;
import business.SoftwareStudent;
import business.EconomicsStudent;
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
    public void insert(Student student) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO Students VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, student.getId());
        ps.setString(2, student.getName());
        ps.setString(3, student.getBirthDate());
        ps.setString(4, student.getMajor());

        if (student instanceof SoftwareStudent) {
            SoftwareStudent s = (SoftwareStudent) student;
            ps.setDouble(5, s.calculateGPA()==0?0:s.calculateGPA()); // placeholder for actual fields
            ps.setDouble(5, s.getJavaScore());
            ps.setDouble(6, s.getHtmlScore());
            ps.setDouble(7, s.getCssScore());
            ps.setNull(8, Types.REAL);
            ps.setNull(9, Types.REAL);
        } else {
            EconomicsStudent e = (EconomicsStudent) student;
            ps.setNull(5, Types.REAL);
            ps.setNull(6, Types.REAL);
            ps.setNull(7, Types.REAL);
            ps.setDouble(8, e.getMarketingScore());
            ps.setDouble(9, e.getSalesScore());
        
        }

        ps.setDouble(10, student.calculateGPA());
        ps.setString(11, student.classifyAcademic());
        ps.executeUpdate();
        ps.close();
    }
}

