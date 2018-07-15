package calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane stackPane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CokeCalculator");
        Scene scene1 = new Scene(stackPane);
        primaryStage.setScene(scene1);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
