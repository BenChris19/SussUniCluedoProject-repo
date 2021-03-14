/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.entities.Board;
import com.seteam23.clue.game.entities.BoardController;
import com.seteam23.clue.main.MainController;
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
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class GameController implements Initializable {

    @FXML private Button main_menu;
    @FXML private Button board;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Changes to the Main Menu's Scene
     */
    @FXML
    private void mainMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        
        Stage window = (Stage)main_menu.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    
    /**
     * Changes to the Main Menu's Scene
     */
    @FXML
    private void playBoard(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(BoardController.class.getResource("board.fxml"));
        Parent root = loader.load();
        BoardController board_controller = loader.getController();
        
        Stage window = (Stage)board.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
