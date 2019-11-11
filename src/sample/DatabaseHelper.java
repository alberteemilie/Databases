package sample;

import java.sql.*;

public class DatabaseHelper {
    Connection conn = null;

    public void connect() {
        try {
            // String url = "jdbc:sqlite:C:/Users/liner/Documents/SD2019E/SQLiteDatabases/Enrollment.db";
            String url = "jdbc:sqlite:Test1-kopi.db";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("PROBLEMER MED FORBINDELSE TIL DB");
            System.out.println(e.toString());
        }
    }

    public ResultSet getStations() throws SQLException {
        String query = "SELECT * FROM Stations";
        Statement stmt = null;
        ResultSet res = null;
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);
        return res;
    }


   /* public ResultSet getTrainIdFromRoute(String route) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet res = null;
        stmt = conn.prepareStatement("SELECT Routes, TrainID FROM Lines WHERE Routes IS ?");
        stmt.setString(1, route);
        res = stmt.executeQuery();
        while (res != null & res.next()) {
            String name = res.getString("TrainID");
            //System.out.println(name);
        }
        return res;
    }*/

    public void presentStations(ResultSet res)
            throws SQLException {
        if (res == null)
            System.out.println("No records for customer");
        while (res != null & res.next()) {
            String name = res.getString("Name");
            //System.out.println(name);
        }
    }

    public ResultSet getDepTimes() throws SQLException {
        String query = "SELECT * FROM Departures";
        Statement stmt = null;
        ResultSet DepTimeRes = null;
        stmt = conn.createStatement();
        DepTimeRes = stmt.executeQuery(query);
        return DepTimeRes;
    }

    //Det er her vi ville hente data til GUI'en for at finde afgangstider
    public ResultSet getDepartureTimesFromRoute(String times) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet DepTimeRes = null;
        stmt = conn.prepareStatement("SELECT DepartureTime, Routes, Lines.TrainID, Departures.Name FROM Lines"
                + "INNER JOIN Departures ON Lines.TrainID = Departures.TrainID"
                + "WHERE Routes IS ? and  Name IS ?");
        stmt.setString(1, times);
        DepTimeRes = stmt.executeQuery();
        while (DepTimeRes != null & DepTimeRes.next()) {
            String name = DepTimeRes.getString("DepartureTime");
            // System.out.println(name);
        }
        return DepTimeRes;
    }

    public void presentSomething(ResultSet DepTimeRes)
            throws SQLException {
        if (DepTimeRes == null)
            System.out.println("No records for customer");
        while (DepTimeRes != null & DepTimeRes.next()) {
            String timString = DepTimeRes.getString("Name");
            System.out.println(timString);
        }
    }

}
