/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getImageview;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayer;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class BoardController implements Initializable {
    
    static Board board;
    
    @FXML private AnchorPane anchor_pane;
    @FXML private ImageView background_img;
    @FXML private GridPane grid;
    @FXML private Pane paneView;
    
    
    
    private Image imageCharacter;
    private ImageView imageview;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = new Board(this);
        board.addPlayers(getPlayer());
        board.startTile(board.getStartPos(getPlayer()));
        createButtons();
    }

    /**
     * 
     * @return 
     */
    public static Board getBoard() {
        return board;
    }
    
    
    
    /**
     * Change the background image to the designated new image
     * @param image_path 
     */
    public void changeBackground(String image_path){
        background_img.setImage(new Image(getClass().getResource(image_path).toExternalForm()));
    }
    
    /**
     * Get the button linked to each tile and add it to the GridPane
     */
    public void createButtons() {
        for (int y = 0; y<25; y++) {
            for (int x = 0; x<24; x++) {
                Tile tile = board.getTile(x, y);
                if (tile != null) 
                    grid.add(tile.getButton(), x, y);
            }
        }
    }
}
