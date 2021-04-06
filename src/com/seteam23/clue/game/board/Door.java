/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

/**
 *
 * @author InfernoKay
 */
public class Door extends Tile {
    
    private Room room;
    private String entry;
    
    /**
     * Constructor
     * @param room
     * @param entry_from 
     */
    public Door(Room room, String entry_from) {
        super();
        
        this.room = room;
        this.entry = entry_from;
    }
    
    /**
     * Overrides the button activate function
     */
    @Override    
    protected void activate() {
        System.out.println("DING DONG");
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
