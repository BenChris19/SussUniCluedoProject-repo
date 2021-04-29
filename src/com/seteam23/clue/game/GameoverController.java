/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;


import static com.seteam23.clue.game.GameRevised.gameWon;
import com.seteam23.clue.main.Main;
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
 * FXML Controller class
 *
 * @author benat
 */
public class GameoverController implements Initializable {

    @FXML private Button playAgainButton;
    @FXML private Label winloseMess;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                if(!gameWon){
            winloseMess.setText("YOU LOSE!");
            winloseMess.setVisible(true);
        }
        else{
            winloseMess.setText("YOU WIN!");
            winloseMess.setVisible(true);
        }
    }    
        @FXML
    private void playAgain(ActionEvent event) throws Exception {

        Parent again = FXMLLoader.load(SingleplayerMenuController.class.getResource("singleplayerMenu.fxml"));
        
        Stage window = (Stage)playAgainButton.getScene().getWindow();

        window.setScene(new Scene(again));
        window.setFullScreen(true);
        Main.makeFullscreen(again,871.9,545);
    }
    @FXML
    private void quitGame(ActionEvent event) throws Exception {
        System.exit(0);
        
    }
    
}
