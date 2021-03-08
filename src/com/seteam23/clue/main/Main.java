/*
 *      Main
 *
 *      Initiates the game
 *      Any user settings etc
 *
 *      Controls Menu Flow
 *      Finite State Machine between Menus?
 */
package com.seteam23.clue.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialise as JavaFX project
        launch(args);
    }

    // Overrides the JFX Start function
    // Start is run after being initialised
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clue!");

        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));

        // Temporary Scene Created
        // Will be managed by Game?
        // Depends if managing menu flow in main or in game, I think here makes more sense
        //StackPane layout = new StackPane();
        //Scene scene = new Scene(layout, 600, 500);
        Scene scene = new Scene(root);

        // Set and show the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
