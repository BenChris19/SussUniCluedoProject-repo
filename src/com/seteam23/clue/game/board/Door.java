/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.getBoard;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getPlayer1;
import javafx.scene.layout.GridPane;
/**
 *
 * @author Team23
 */
public class Door extends Tile {
    
    private final Room room;
    private final String entry;
    
    /**
     * Constructor
     * @param room
     * @param entry_from 
     * @param x 
     * @param y 
     */
    public Door(Room room, String entry_from, int x, int y) {
        super(x, y);
        
        this.room = room;
        this.entry = entry_from;
    }
    
    /**
     * Overrides the button activate function
     */
    @Override    
    public void activate() {
        getBoard().getTile(getPlayer1().getCurrentPosY(),getPlayer1().getCurrentPosX()).removeOccupier(getPlayer1());
        getPlayer1().setIsInRoom(true);
        getPlayer1().setCurrentPosYX(GridPane.getColumnIndex(getButton()), GridPane.getRowIndex(getButton()));
        room.addOccupier(getPlayer1());

    }
    
    /**
     * Get the room the door is associated with
     * @return room connected to
     */
    public Room getRoom() {
        return this.room;
    }
    
    /**
     * Get which direction the door is entered from
     * @return entry compass direction
     */
    public String entryFrom() {
        return this.entry;
    }
    
}