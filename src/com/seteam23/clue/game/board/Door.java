/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameRevised.getCurrentPlayer;
import static com.seteam23.clue.game.GameRevised.getRound;

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
        getCurrentPlayer().getLocation().removeOccupier(getCurrentPlayer());
        getCurrentPlayer().enterRoom(room);
        getCurrentPlayer().clearSearchSpace();
        room.addOccupier(getCurrentPlayer());
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