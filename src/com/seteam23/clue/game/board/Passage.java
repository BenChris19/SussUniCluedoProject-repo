/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.getBoard;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getPlayer1;

/**
 *
 * @author Team 23
 */
public class Passage extends Tile {
    
    private Room location;
    private Room destination;
    
    public Passage(Room source, Room destination, int x, int y) {
        super(x,y);
        
        this.destination = location;
        this.destination = destination;
    }
    
    @Override
    public void activate() {
        
    }
    
    
}
