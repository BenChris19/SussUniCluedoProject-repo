/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.main;

import com.seteam23.clue.game.GameController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author InfernoKay
 */
public class MainController implements Initializable {
    
    @FXML
    // Reference to the button with id start_game
    private Button start_game;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Changes to the Game's Scene
     */
    @FXML
    private void startGame(ActionEvent event) throws Exception{
        // Load the game fxml resource associated with the GameController
        Parent root = FXMLLoader.load(GameController.class.getResource("game.fxml"));
        
        // Get the window (window = stage) that the button is located in
        Stage window = (Stage)start_game.getScene().getWindow();
        // Set the scene to the new one
        window.setScene(new Scene(root));
    }

}
