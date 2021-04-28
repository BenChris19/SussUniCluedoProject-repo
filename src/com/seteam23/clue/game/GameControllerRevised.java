/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.NPC;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.game.entities.PlayerRevised;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author Team 23
 */
public final class GameControllerRevised implements Initializable {
    
    /*
     *  FXML Setup
     */
    @FXML private Tab boardTab;
    @FXML private Tab cardsTab;
    @FXML private Tab checklistTab;
    
    @FXML private GridPane grid;
    @FXML private Label moves_label;
    
    @FXML private ImageView player_img;
    @FXML private ImageView diceThrow;
    
    @FXML private ComboBox person;
    @FXML private ComboBox weapon;
    @FXML private ComboBox room;
    @FXML private Button suggest;
    @FXML private Button accuse;
    
    @FXML private ImageView revealCard;
    @FXML private Label whoCard;
    
    
    /*
     *  Declarations
     */
    private GameRevised game;
    private PlayerRevised player;
    private ArrayList<Tile> searchSpace;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.player = game.getCurrentPlayer();
        this.searchSpace = new ArrayList<>();
        
        createButtons();
    }
    
    /**
     * Done After Creating Controller
     * @param game
     */
    public void setGame(GameRevised game) {
        this.game = game;
    }
    
    /**
     * 
     */
    private void createButtons() {
        for (Tile[] tileArr : game.BOARD.getTiles()) {
            for (Tile tile : tileArr) {
                if (tile != null) grid.add(tile.getButton(), tile.x ,tile.y);
            }
        }
    }
    
    /**
     * 
     */
    private void rollDieAnimation() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                moves_label.setVisible(false);
                diceThrow.setVisible(true);
            }),
            new KeyFrame(Duration.seconds(2), e -> {
                moves_label.setVisible(true);
                diceThrow.setVisible(false);
            })
        );
        timeline.play();
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Tile> getSearchSpace() {
        return searchSpace;
    }
    
    
    /*
     *  FXML Methods
     */
    
    @FXML
    public void rollDie() {
        int roll = game.rollDice();
        moves_label.setText(""+roll);
        rollDieAnimation();
        
        searchSpace = this.player.getSearchSpace(roll);
        game.BOARD.highlightTiles(searchSpace);
    }
    
    @FXML
    public void makeSuggestion() {
        Card found = null;
        PlayerRevised nextPlayer = null;
        int i = 1;
        
        // If  player can suggest and value in person and weapon boxes
        if (this.player.suggest() && person.getValue() != null && weapon.getValue() != null) {
            // Check for found or ran out of plauers
            while (found != null && i < game.NUM_PLAYERS) {
                nextPlayer = game.PLAYERS.get((game.getTurn()+i) % game.NUM_PLAYERS);
                
                Room current_room = (Room)player.getLocation();
                nextPlayer.enterRoom(current_room);
                
                // Look through cards of next player and see if have any of suggested
                for (Card c : nextPlayer.getCards()) {
                    if (c.getName().equals(person+".jpg") || c.getName().equals(weapon+".JPG") || c.getName().equals(current_room.getName()+".png")) {
                        found = c;
                        break;
                    }
                }
            }
            // Found Something
            if (found != null) {
                this.revealCard.setImage(found.getImg());
                this.whoCard.setText(nextPlayer.NAME+" has this card");
            }
            // Didn't
            else {
                this.whoCard.setText("No-one else had these cards");
            }
        }
    }
    
    @FXML
    public void endTurn() {
        this.game.nextTurn();
        
        // Player
        this.player = game.getCurrentPlayer();
        player_img.setImage(new Image(this.player.IMG_PATH));
        
        // Reset GUI
        game.BOARD.unlightAllTiles();
        revealCard.setVisible(false);
        whoCard.setVisible(false);
        
        // Unlight Tiles
        game.BOARD.unlightTiles(searchSpace);
        searchSpace.clear();
        
        // If NPC cannot look at cards or checklist tabs
        if (this.player.getClass().equals(NPC.class)) {
            cardsTab.setDisable(true);
            checklistTab.setDisable(true);
        }
        // If Player can
        else {
            cardsTab.setDisable(false);
            checklistTab.setDisable(false);
        }
    }
    
    
}
