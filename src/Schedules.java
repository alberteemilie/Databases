/*import java.sql.*;

public class Schedules {

    public Connection connect(String url)
            throws SQLException {
        return DriverManager.getConnection(url);
    }

    public void PresentStudents(ResultSet res)
            throws SQLException {
        if (res == null)
            System.out.println("No records for customer");
        while (res != null & res.next()) {
            String name = res.getString("Name");
            System.out.println(name);
        }
    }


//Select statement fra JDBC undervisningen
public ResultSet plainstatement(String StationNames, Connection conn)
        throws SQLException {
    String query = "SELECT Name from Stations";

    Statement stmt = null;
    ResultSet res = null;
    stmt = conn.createStatement();
    res = stmt.executeQuery(query);
    return res;
}

//Connecting to the database
    public static void main(String[] args) {
        Schedules CJ = new Schedules();
        Connection conn = null;

        try {
            // String url = "jdbc:sqlite:C:/Users/liner/Documents/SD2019E/SQLiteDatabases/Enrollment.db";
            String url = "jdbc:sqlite:/Users/Alberte/Desktop/SoftwareDevelopment/Databases/Test1-kopi.db";

            conn = CJ.connect(url);

            System.out.println();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}*/