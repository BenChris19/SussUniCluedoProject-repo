package com.seteam23.clue.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.transform.Scale;

/**
 *Main class, executes the Clue! board game.
 * 
 * 
 * @author team23
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialise as JavaFX project
        launch(args);
    }

    public static void makeFullscreen(Parent root,double w,double h){
        Scale scale = new Scale(w, h, 0, 0);
        root.getTransforms().add(scale);
    }

    /**
     * Overrides the JFX Start function
     * 
     * Start is run after being initialised
     **/
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clue!");

        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        makeFullscreen(root,2,2);
        Scene scene = new Scene(root);

        // Set and show the scene
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
    }
}
