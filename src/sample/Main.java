package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        //launch triggers a function to start
        launch(args);
    }
  //creating a stage - a window on the screen
    @Override
    public void start(Stage primaryStage) throws Exception{
        //the components from sample.fxml are generated when the FXML file is loaded
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Train Schedules");
        primaryStage.setScene(new Scene(root, 700, 500));
        //nothing is visible until you call 'show' as beneath
        primaryStage.show();
    }
}
