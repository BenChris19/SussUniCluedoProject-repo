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
    public void activate() {
        System.out.println("DING DONG");
        getBoard().getTile(getPlayer1().getCurrentPosY(),getPlayer1().getCurrentPosX()).getButton().getStyleClass().remove("toggle-"+getPlayer1().getName().split(" ",-1)[1]);
        getBoard().getTile(GridPane.getColumnIndex(getButton()), GridPane.getRowIndex(getButton())).getButton().getStyleClass().add("toggle-Scarlett");
        getPlayer1().setIsInRoom(true);
        getPlayer1().setCurrentPosYX(GridPane.getColumnIndex(getButton()), GridPane.getRowIndex(getButton()));
        getBoard().unlightAllTiles();
        addOccupier(getPlayer1());

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
