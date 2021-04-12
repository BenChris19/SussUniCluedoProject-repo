/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.entities.Player;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Button;

/**
 *
 * @author InfernoKay
 */
public abstract class Place {
    private int max_players;
    private ArrayList<Player> occupiers;
    private Button button;
    
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
        
        button.setOnAction(e -> {
                              this.activate();
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
        System.out.println("CLICK");
        if(getPlayer().getName().equals("Miss Scarlett")){
            button.getStyleClass().add("toggle-Scarlett");
        }
        else if (getPlayer().getName().equals("Prof Plum")){
            button.getStyleClass().add("toggle-Plum"); 
        }
        else if (getPlayer().getName().equals("Col Mustard")){
            button.getStyleClass().add("toggle-Mustard"); 
        }
        else if (getPlayer().getName().equals("Mrs White")){
            button.getStyleClass().add("toggle-White"); 
        }
        else if (getPlayer().getName().equals("Rev Green")){
            button.getStyleClass().add("toggle-Green"); 
        }
        else{
            button.getStyleClass().add("toggle-Peacock");
        }
    }
    
}
