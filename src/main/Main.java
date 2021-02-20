/*
 *      Main
 *
 *      Initiates the game
 *      Any user settings etc
*/

package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static voic main(String[] args) {
        // Initialise as JavaFX project
        launch(args);
    }

    // Overrides the JFX Start function
    // Start is run after being initialised
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clue!");
    }
}
