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
    private Label label;
    @FXML
    private Button start_game;

    @FXML
    private void startGame(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(GameController.class.getResource("game.fxml"));
        
        Stage window = (Stage)start_game.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
