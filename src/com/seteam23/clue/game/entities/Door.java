/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

/**
 *
 * @author InfernoKay
 */
public class Door extends Tile {
    
    private Room room;
    private String entry;
    
    public Door(Room room, String entry_from) {
        super();
        
        this.room = room;
        this.entry = entry_from;
    }
    
    @Override    
    protected void activate() {
        
    }
    
    public Room getRoom() {
        return this.room;
    }
    
}
