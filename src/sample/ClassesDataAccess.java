package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDataAccess {

    private Connection conn;
    private static final String classTable = "classRooms";

    public ClassesDataAccess()
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to the CLASSES - Database...");
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

    public List<ClassRooms> getAllClass()  throws SQLException {
        String sql2 = "SELECT classRoomId, className FROM " + classTable + " INNER JOIN teacher ON fk_teacherId = teacherId";
        PreparedStatement pstmnt = conn.prepareStatement(sql2);
        ResultSet rs2 = pstmnt.executeQuery();
        List<ClassRooms> listClass = new ArrayList<>();

        while (rs2.next()) {
            int i = rs2.getInt("classRoomId");
            String className = rs2.getString("className");
            listClass.add(new ClassRooms(i,className));
        }

        pstmnt.close();
        return listClass;
    }
}