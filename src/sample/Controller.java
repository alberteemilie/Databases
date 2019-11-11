package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    //fxid's til comboboxes og knap fra scenebuilder
    TrainModel m = TrainModel.getInstance(); // make a model object when you create the controller

    @FXML
    ComboBox DepartureStation;
    @FXML
    ComboBox ArrivalStation;
    @FXML
    Button search;
    @FXML
    TextArea returnTime;

    DatabaseHelper databaseHelper = new DatabaseHelper();

    @FXML
    public void initialize() throws SQLException { //executed when GUI is ready

        databaseHelper.connect();
        ResultSet set = databaseHelper.getStations();
        databaseHelper.presentStations(set);

        //Nyt
        ResultSet setTimes = databaseHelper.getDepTimes();
        databaseHelper.presentSomething(setTimes);
        //Nyt Slut
        
        //udfylder comboboxes
        for (String s : TrainModel.getInstance().getStations()) {
            DepartureStation.getItems().add(s);
        }
        for (String z : TrainModel.getInstance().getArrivals()) {
            ArrivalStation.getItems().add(z);
        }

      /*  search.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Object selectedDeparture = DepartureStation.getSelectionModel().getSelectedItem();
                Object selectedArrival = ArrivalStation.getSelectionModel().getSelectedItem();
                returnTime.appendText("Tog linje " + selectedDeparture + " Fra " + selectedArrival);

                String formattedRoute = String.format("Fra%sTil%s", selectedDeparture, selectedArrival);
                ResultSet trainIds;

                try {
                    trainIds = databaseHelper.getTrainIdFromRoute(formattedRoute);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        */
        search.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Object selectedDeparture = DepartureStation.getSelectionModel().getSelectedItem();
                Object selectedArrival = ArrivalStation.getSelectionModel().getSelectedItem();
                returnTime.appendText("Tog linje " + selectedDeparture + " Fra " + selectedArrival);

                String formattedRoute = String.format("Fra%sTil%s", selectedDeparture, selectedArrival);
                ResultSet trainIds;

                try {
                    trainIds = databaseHelper.getDepartureTimesFromRoute(formattedRoute);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}