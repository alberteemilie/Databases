package sample;

import java.sql.ResultSet;

public class TrainModel {  // is a Singleton!
    //variable for database connection
    private TrainModel() {
    }

    static sample.TrainModel inst;

    static sample.TrainModel getInstance() {
        if (inst == null) inst = new sample.TrainModel();
        return inst;
    }

    String[] getStations() {
        String[] s = {"FraKBHTilOdense", "FraOdenseTilKBH", "FraKBHTilNykobingF", "FraNykobingFTilKBH"}; //select station name from 'tabelnavn'
        return s;
    }

    String[] getArrivals() {
        String[] z = {"KBH", "HojeTaastrup", "Roskilde", "Ringsted", "Odense", "Naestved", "NykobingF"};
        return z;
    }

    String findRoute(String DepartureStation, String ArrivalStation) {
        //make query to database - make it into a string - return string
        return "route from " + DepartureStation + "\n to " + ArrivalStation;
    }

}

