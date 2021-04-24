package com.seteam23.clue.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Scene;
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

    /**
     * 
     * @param root
     * @param width
     * @param height 
     */
    public static void makeFullscreen(Parent root,double width,double height){
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double w = resolution.getWidth()/width;  // your window width
        double h = resolution.getHeight()/height;  // your window height
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
        Scene scene = new Scene(root);

        // Set and show the scene
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        makeFullscreen(root,970,545);
        primaryStage.show();
        
    }
}
