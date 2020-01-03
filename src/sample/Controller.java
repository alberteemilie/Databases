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
    TrainModel m = TrainModel.getInstance(); // make a model object when you create the controller

    //fxid's til comboboxes og knap fra scenebuilder
    @FXML
    ComboBox RouteName;
    @FXML
    ComboBox DepartureStation;
    @FXML
    Button search;
    @FXML
    TextArea returnTime;

    DatabaseHelper databaseHelper = new DatabaseHelper();




    @FXML
    public void initialize() throws SQLException { //executed when GUI is ready


        //udfylder comboboxes med Strings fra TrainModel.java
        for (String s : TrainModel.getInstance().getRoutes()) {
            RouteName.getItems().add(s);
        }
        for (String z : TrainModel.getInstance().getDepartureStation()) {
            DepartureStation.getItems().add(z);
        }

        //Eventhandler til søg-knap
        search.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    returnTime.clear(); //sletter tidligere data fra textarea
                    databaseHelper.connect(); // opretter forbindelse til database når der trykkes på knappen

                    //Her oprettes to nye objekter der indeholder den valgte rute og afgangsstation i GUI'en
                    Object selectedRoute = RouteName.getSelectionModel().getSelectedItem();
                    Object selectedDeparture = DepartureStation.getSelectionModel().getSelectedItem();

                    //returntime er textarea, og her tilføjes både text, formattering og valgte objekter
                    returnTime.appendText("Tog linje: " + selectedRoute + "\nkører på følgende tidspunkter fra: "
                            + selectedDeparture + " station\n");

                    //sætter 'res' til chosenRoute fra DatabaseHelper for at bruge variablerne derfra herefter
                    ResultSet res = databaseHelper.chosenRoute(DepartureStation.getValue().toString(), RouteName.getValue().toString());

                    while (res != null & res.next()) {
                        String routeString = res.getString("Routes");
                        String stationNameString = res.getString("Name");
                        String departureTimeString = res.getString("DepartureTime");

                        returnTime.appendText("\n " + routeString + ", fra ");
                        returnTime.appendText(stationNameString + " station, kl: ");
                        returnTime.appendText(departureTimeString);

                    }
                    //closing the database connection
                    databaseHelper.conn.close();
                    System.out.println("Database connection is closed");


                } catch (SQLException sqlE) {
                    System.out.println("SqlException Error");
                    returnTime.appendText("Could not find data");

                }
            }
        });
    }

}