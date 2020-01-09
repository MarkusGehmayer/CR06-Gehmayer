package sample;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class TeacherDataAccess {

    private Connection conn;
    private static final String teacherTable = "teacher";

    public TeacherDataAccess()
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to the TEACHER - Database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cr06_gehmayer" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }
    public void closeDb() throws SQLException {
        conn.close();
    }

    public List<Teacher> getAllRows()  throws SQLException {
        String sql = "SELECT * FROM " + teacherTable + " ORDER BY teacherName";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Teacher> list = new ArrayList<>();

        while (rs.next()) {
            int i = rs.getInt("teacherId");
            String name = rs.getString("teacherName");
            String surname = rs.getString("teacherSurname");
            String email = rs.getString("teacherEmail");
            list.add(new Teacher(i, name, surname, email));
        }

        pstmnt.close();
        return list;
    }
}