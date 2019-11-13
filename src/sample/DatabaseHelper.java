package sample;

import java.sql.*;

public class DatabaseHelper {
    Connection conn = null;

    public void connect() {
        try {
            String url = "jdbc:sqlite:Test1-kopi.db";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Problemer med at oprette forbindelse til databasen");
            System.out.println(e.toString());

    }

    }

    public ResultSet chosenRoute(String Routes, String Name) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet chRoute = null;
        stmt = conn.prepareStatement("SELECT Routes, DepartureTime, Name FROM Departures\n" +
                "INNER JOIN Lines L on Departures.TrainID = L.TrainID\n" +
                "WHERE Name is ? and Routes is ?");

        stmt.setString(1, Routes);
        stmt.setString(2, Name);
        chRoute = stmt.executeQuery();



        return chRoute;
    }



}