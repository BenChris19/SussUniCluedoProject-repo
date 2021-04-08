package com.seteam23.clue.main;

import com.seteam23.clue.singleplayer.SingleplayerMenuController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
 * @author team23
 */
public class MainController implements Initializable {
    
    @FXML
    private Button button_start;
    
    @FXML
    private Button button_quit;
    
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
        // TODO
    }
    
    
    /**
     * Changes to the Character selection Scene.
     * 
     * @param event executes an event, in this case, the event is to
     * go to the next scene, which is the character selection menu.
     */
    @FXML
    private void startGame(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(SingleplayerMenuController.class.getResource("singleplayerMenu.fxml"));
        
        Stage window = (Stage)button_start.getScene().getWindow();

        window.setScene(new Scene(root));
        window.setFullScreen(true);
        Main.makeFullscreen(root,871.9,545);
    }
        /**
     * Quits game.
     * 
     * @param event executes an event, in this case, it exits the game 
     * and code is terminated.
     */
    @FXML
    private void quitGame(ActionEvent event) throws Exception {
        System.exit(0);
    }
}
