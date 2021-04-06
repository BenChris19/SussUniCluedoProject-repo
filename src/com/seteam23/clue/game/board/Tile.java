/*
 *      The Tile
 *
 *      A place where the player can move to
 *      Can be a start or tunnel?
 */
package com.seteam23.clue.game.board;

import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Tile extends Place{
    
    private HashMap<String, Tile> adjacent;
    
    /**
     * Constructor
     */
    public Tile() {
        // Can hold one Player at a time
        super(1);
        
        adjacent = new HashMap<>();
        adjacent.put("N", null);
        adjacent.put("S", null);
        adjacent.put("E", null);
        adjacent.put("W", null);
    }
    
    /**
     * Map of which items are in each compass direction
     * @return Map of which Places are directly adjacent
     */
    public HashMap<String, Tile> getAdjacent() {
        return adjacent;
    }
    
    /**
     * Specify which tile instance is in which compass direction
     * @param direction
     * @param tile 
     */
    public void setAdjacent(String direction, Tile tile) {
        adjacent.put(direction, tile);
    }
}