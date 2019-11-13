package sample;

public class TrainModel {  // is a Singleton!

    //variable for database connection
    private TrainModel(){
    }

    static sample.TrainModel inst;

    static sample.TrainModel getInstance(){
        if (inst == null) inst = new sample.TrainModel();
        return inst;
    }

    String[] getRoutes() {
        String[] s = {"FraKBHTilOdense", "FraOdenseTilKBH", "FraKBHTilNykobingF", "FraNykobingFTilKBH"}; //select station name from 'tabelnavn'
        return s;
    }

    String[] getDepartureStation() {
        String[] z = {"KBH", "HojeTaastrup", "Roskilde", "Ringsted", "Odense", "Naestved", "NykobingF"};
        return z;
    }
}

