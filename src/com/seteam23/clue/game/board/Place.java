/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.getBoard;
import com.seteam23.clue.game.entities.Player;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getPlayer1;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author InfernoKay
 */
public abstract class Place {
    private int max_players;
    private ArrayList<Player> occupiers;
    private Button button;
    private boolean occupied;

    
    /**
     * 
     * @param max_players 
     */
    public Place(int max_players) {        
        this.max_players = max_players;
        this.occupiers = new ArrayList<>();
        this.button = createButton();
        this.occupied = false;
        
    }

    public boolean isOccupied() {
        return occupied;
    }

    
    /**
     * Adds the Player to the Place if there is space for them
     * @param p Player to add to Place
     * @return True if added to ArrayList
     */
    public boolean addOccupier(Player p) {
        if (this.occupiers.size() < this.max_players) {
            this.getButton().getStyleClass().add("toggle-"+p.getName().split(" ",-1)[1]);
            this.occupied = true;
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
        // remove player icon from tile
        getBoard().getTile(p.getCurrentPosY(),p.getCurrentPosX()).getButton().getStyleClass().remove("toggle-"+p.getName().split(" ",-1)[1]);
        this.occupied = false;
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
            try {
                if (getPlayer1().isEndTurn() == false && getPlayer1().getSearchSpace().contains(getBoard().getTile(GridPane.getColumnIndex(button), GridPane.getRowIndex(button)))) {
                    this.activate();
                }
            }catch (InterruptedException ex) {
                Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
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
     * @throws java.lang.InterruptedException
     */
    public void activate() throws InterruptedException {
        getBoard().getTile(getPlayer1().getCurrentPosY(),getPlayer1().getCurrentPosX()).removeOccupier(getPlayer1());
        getPlayer1().setCurrentPosYX(GridPane.getColumnIndex(button), GridPane.getRowIndex(button));
        addOccupier(getPlayer1()); 
    }
}