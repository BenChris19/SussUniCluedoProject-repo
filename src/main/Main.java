/*
 *      Main
 *
 *      Initiates the game
 *      Any user settings etc
 *
 *      Controls Menu Flow
 *      Finite State Machine between Menus?
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

    public static void main(String[] args) {
        // Initialise as JavaFX project
        launch(args);
    }

    // Overrides the JFX Start function
    // Start is run after being initialised
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clue!");

        // Temporary Scene Created
        // Will be managed by Game?
        // Depends if managing menu flow in main or in game, I think here makes more sense
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 600, 500);

        // Set and show the scene
        primaryStage.setScene(scene);
        primaryStage.show()
    }
}
