/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author InfernoKay
 */
public class Place {
    private HashMap<String, Place> adjacent;
    private int max_players;
    private ArrayList<Player> occupiers;
    
    public Place(int max_players) {
        adjacent = new HashMap<>();
        adjacent.put("N", null);
        adjacent.put("S", null);
        adjacent.put("E", null);
        adjacent.put("W", null);
        
        this.max_players = max_players;
        this.occupiers = new ArrayList<>();
    }
    
    /**
     * Map of which items are in each compass direction
     * @return Map of which Places are directly adjacent
     */
    public HashMap<String, Place> getAdjacent() {
        return adjacent;
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
    
    public ArrayList<Player> occupiedBy() {
        return this.occupiers;
    }
    
}
