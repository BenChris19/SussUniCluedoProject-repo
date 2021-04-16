/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.Game;
import com.seteam23.clue.game.GameController;
import static com.seteam23.clue.game.GameController.board;
import static com.seteam23.clue.game.GameController.dieRolls;
import static com.seteam23.clue.game.GameController.getBoard;
import static com.seteam23.clue.game.GameController.getDieRolls;

import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.singleplayer.SingleplayerMenuController;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author InfernoKay
 */
public abstract class Place {
    private int max_players;
    private ArrayList<Player> occupiers;
    private Button button;
    public static int turn = 0;

    
    /**
     * 
     * @param max_players 
     */
    public Place(int max_players) {        
        this.max_players = max_players;
        this.occupiers = new ArrayList<>();
        
        this.button = createButton();
        
    }

    
    
    /**
     * Adds the Player to the Place if there is space for them
     * @param p Player to add to Place
     * @return True if added to ArrayList
     */
    public boolean addOccupier(Player p) {
        if (this.occupiers.size() < this.max_players) {
            return this.occupiers.add(p);
        }
        return false;
    }
    
    /**
     * Removes Player from the Place
     * @param p Player to remove
     * @return True if removed from ArrayList
     */
    public boolean removeOccupier(Player p) {
        return this.occupiers.remove(p);
    }
    
    
    /**
     * 
     * @return List of Players Occupying the place
     */
    public ArrayList<Player> occupiedBy() {
        return this.occupiers;
    }
    
    /**
     * 
     * @return true if fully occupied
     */
    public boolean isFull() {
        return this.occupiers.size() == this.max_players;
    }
    
    /**
     * Creates the Tile's JavaFX Button
     * @return button
     */
    private Button createButton() {
        Button button = new Button();
        
        button.setOnAction((ActionEvent e) -> {
            if (!getBoard().reachableFrom(board.getTile(Board.startCols[turn],Board.startRows[turn]),dieRolls[2]).isEmpty() && getBoard().getReachableButtons(getBoard().reachableFrom(board.getTile(Board.startCols[turn],Board.startRows[turn]),dieRolls[2])).contains((Button)e.getSource())) {
                Place.this.activate();
            }
            
        }); 
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }
    
    /**
     * Gets the associated button
     */
    public Button getButton() {
        return this.button;
    }
    
    /**
     * Activated effect when button clicked
     */
    public void activate() {
        List<String> order = Arrays.asList("Miss Scarlett", "Col Mustard","Mrs White", "Rev Green","Mrs Peacock","Prof Plum");
        System.out.println("CLICK");
        switch (getPlayer().getName()) {
            case "Miss Scarlett":
                button.getStyleClass().add("toggle-Scarlett");
                break;
            case "Prof Plum":
                button.getStyleClass().add("toggle-Plum");
                break;
            case "Col Mustard":
                button.getStyleClass().add("toggle-Mustard");
                break;
            case "Mrs White":
                button.getStyleClass().add("toggle-White");
                break;
            case "Rev Green":
                button.getStyleClass().add("toggle-Green");
                break;
            default:
                button.getStyleClass().add("toggle-Peacock");
                break;
        }
        getBoard().unlightAllTiles();

        Place.turn+=1;
        
                
        int prevY = Board.startCols[turn-1];
        int prevX = Board.startRows[turn-1];
        Board.startCols[turn-1] = GridPane.getColumnIndex(button);
        Board.startRows[turn-1]  = GridPane.getRowIndex(button);
        

        GameController.dieRolls[2] = 0;
        
                        if(Place.turn != 0 && Place.turn%6==0){
            Place.turn = 0;
        }
                                Player nextPlayer  = new Player(order.get(turn));
        getBoard().getTile(prevY,prevX).getButton().getStyleClass().remove("toggle-"+getPlayer().getName().split(" ",-1)[1]);
        getBoard().getStartPos(nextPlayer).startFlashing();
                SingleplayerMenuController.setPlayer(nextPlayer);
    }
 
}