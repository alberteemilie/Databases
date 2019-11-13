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

        databaseHelper.connect();

        //udfylder comboboxes med Strings fra TrainModel.java
        for (String s : TrainModel.getInstance().getRoutes()) {
            RouteName.getItems().add(s);
        }
        for (String z : TrainModel.getInstance().getDepartureStation()) {
            DepartureStation.getItems().add(z);
        }

        search.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Object selectedRoute = RouteName.getSelectionModel().getSelectedItem();
                    Object selectedDeparture = DepartureStation.getSelectionModel().getSelectedItem();
                    returnTime.clear(); //for ikke at skrive videre på samme linje, hvis man vælger ny rute
                    returnTime.appendText("Tog linje " + selectedRoute + " Fra " + selectedDeparture + "station \npå følgende tidspunkter: \n");


                    ResultSet res = databaseHelper.chosenRoute(DepartureStation.getValue().toString(), RouteName.getValue().toString());
                    while (res != null & res.next()) {
                        String name1 = res.getString("Routes");
                        String name2 = res.getString("Name");
                        String name3 = res.getString("DepartureTime");

                        returnTime.appendText("\n " + name1);
                        returnTime.appendText(name2);
                        returnTime.appendText(name3);

                    }

                } catch (SQLException sqlE) {
                    System.out.println("SqlException Error");
                    returnTime.appendText("SqlException Error");
                }
            }
        });
    }

}