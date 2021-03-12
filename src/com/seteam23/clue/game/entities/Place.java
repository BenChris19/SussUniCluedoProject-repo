/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.util.HashMap;

/**
 *
 * @author InfernoKay
 */
public class Place {
    private HashMap<String, Place> adjacent;
    
    public Place() {
        adjacent = new HashMap<>();
        adjacent.put("N", null);
        adjacent.put("S", null);
        adjacent.put("E", null);
        adjacent.put("W", null);
    }
    
    /**
     *
     */
    public HashMap<String, Place> getAdjacent() {
        return adjacent;
    }
    
}
