/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Button;

/**
 *
 * @author InfernoKay
 */
public class Place {
    private int max_players;
    private ArrayList<Player> occupiers;
    
    public Place(int max_players) {        
        this.max_players = max_players;
        this.occupiers = new ArrayList<>();
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
     * Creates the Tile's JavaFX Button
     * @return button
     */
    private Button createButton() {
        Button button = new Button();
        
        button.setOnAction(e -> {
                              this.movePlayer();
                              this.activate();
                            });
        
        return button;
    }
    
    /**
     * 
     */
    protected void movePlayer() {
        // Move Board.currentPlayer to this tile location
        // If can addPlayer
    }
    
    /**
     * 
     */
    protected void activate() {
        
    }
    
}
