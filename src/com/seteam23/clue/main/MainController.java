package com.seteam23.clue.main;

import com.seteam23.clue.singleplayer.MultiplayerMenuController;
import com.seteam23.clue.singleplayer.SingleplayerMenuController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML MainController class
 * The controller class, allows the FXML file to be initialised, and
 * allows the user to interact with the GUI
 * 
 * @author Team 23
 */
public class MainController implements Initializable {
    
    @FXML
    private Button button_single;
    
    @FXML
    private Button button_multiplayer;
    
    /**
     * Initialises the controller class.
     * Sets up the main menu scene
     * @param url The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     * @param rb The resources used to localise the root object, or <tt>null</tt> if
     * the root object was not localised.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    /**
     * Changes to the Character selection Scene.
     * Allows the user to transition to the character selection menu
     */
    @FXML
    private void singleGame() throws Exception {
        Parent root = FXMLLoader.load(SingleplayerMenuController.class.getResource("singleplayerMenu.fxml"));
        
        Stage window = (Stage)button_single.getScene().getWindow();

        window.setScene(new Scene(root));
        window.setFullScreen(true);
        Main.makeFullscreen(root,871.9,545);
    }
        /**
     * Changes to the Character selection Scene.
     * Allows the user to transition to the character selection menu
     */
    @FXML
    private void multiGame() throws Exception {
        Parent root = FXMLLoader.load(MultiplayerMenuController.class.getResource("multiplayerMenu.fxml"));
        
        Stage window = (Stage)button_multiplayer.getScene().getWindow();

        window.setScene(new Scene(root));
        window.setFullScreen(true);
        Main.makeFullscreen(root,871.9,545);
    }
    
    /**
     * Quits game.
     * Exits the program
     */
    @FXML
    private void quitGame() throws Exception {
        System.exit(0);
    }
}
