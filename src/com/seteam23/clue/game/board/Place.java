/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.entities.PlayerRevised;

import java.util.ArrayList;

/**
 *
 * @author InfernoKay
 */
public abstract class Place {
    protected int max_players;
    protected ArrayList<PlayerRevised> occupiers;
    protected boolean occupied;

    
    /**
     * 
     * @param max_players 
     */
    public Place(int max_players) {        
        this.max_players = max_players;
        this.occupiers = new ArrayList<>();
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
    public abstract boolean addOccupier(PlayerRevised p);
    
    /**
     * Removes Player from the Place
     * @param p Player to remove
     * @return True if removed from ArrayList
     */
    public abstract boolean removeOccupier(PlayerRevised p);
    
    
    /**
     * 
     * @return List of Players Occupying the place
     */
    public ArrayList<PlayerRevised> occupiedBy() {
        return this.occupiers;
    }
    
    /**
     * 
     * @return true if fully occupied
     */
    public boolean isFull() {
        return this.occupiers.size() == this.max_players;
    }
}