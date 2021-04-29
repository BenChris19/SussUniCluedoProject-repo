/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.GameRevised;
import static com.seteam23.clue.game.GameRevised.BOARD;
import com.seteam23.clue.game.board.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Team 23
 */
public class PlayerRevised {
    
    public final String NAME;
    public final String IMG_PATH;
    
    protected Place location;
    protected ArrayList<Card> cards = new ArrayList<>();
    protected Checklist checklist = new Checklist();
    protected ArrayList<Tile> searchSpace = new ArrayList<>();
    
    private int round_extra_roll = 0;
    private int round_extra_suggest = 0;
    
    protected int rolls_remaining = 1;
    protected int suggest_remaining = 1;
    private boolean out = false;
    
    /**
     * 
     * @param name
     * @param imgPath
     */
    public PlayerRevised(String name, String imgPath) {
        this.NAME = name;
        this.IMG_PATH = imgPath;
        
        this.location = null;
    }
    
    /**
     * Reset remaining values
     */
    public void newTurn() {
        rolls_remaining = 1;
        suggest_remaining = 1;
    }

    
    /**
     * Get current player location as superclass Place object
     * @return Room or Tile
     */
    public Place getLocation() {
        return location;
    }

    /**
     * Move this player to a given Tile
     * @param tile
     */
    public void moveTo(Tile tile) {
        this.location = tile;
    }
    
    /**
     * Move this player to a given room     * 
     * @param room
     */
    public void enterRoom(Room room) {
        this.location = room;
    }
    
    /**
     * Check if user is in a Room
     * @return true if in room
     */
    public boolean isInRoom() {
        return location.getClass().equals(Room.class);
    }
    
    /**
     * Add card to hand
     * @param card 
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }
    
    /**
     * Return all cards in hand
     * @return 
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    
    
    /**
     * Decrease counter for roll
     */
    public boolean roll() {
        if (rolls_remaining > 0) {
            rolls_remaining--;
            return true;
        }
        return false;
    }
    
    /**
     * Get Tiles which can be moved to depending if in room or tile
     * @param dieRoll
     * @return List of Tiles reachable from current location
     */
    public ArrayList<Tile> setSearchSpace(int dieRoll) {
        searchSpace.clear();
        if (isInRoom()) {
            this.searchSpace = GameRevised.BOARD.reachableFrom((Room)location, dieRoll);
        }
        else {
            this.searchSpace = GameRevised.BOARD.reachableFrom((Tile)location, dieRoll);
        }
        return this.searchSpace;
    }
    
    public void clearSearchSpace() {
        BOARD.unlightTiles(searchSpace);
        this.searchSpace.clear();
    }
    
    public ArrayList<Tile> getSearchSpace() {
        return this.searchSpace;
    }
    
    
    /**
     * If accused, player is out and cannot play any further
     */
    public void accuse() {
        out = true;
    }
    
    /**
     * Get Extra roll if not successive turns
     * Can only benefit once per round
     * @param round
     * @return true if get extra roll
     */
    public boolean extraRoll(int round) {
        // If not same or subsequent round
        if (round_extra_roll+1 < round) {
            round_extra_roll = round;
            rolls_remaining++;
            return true;
        }
        return false;
    }
    
    /**
     * Get Extra suggestion if not successive turns
     * Can only benefit once per round
     * @param round
     * @return true if get extra suggest
     */
    public boolean extraSuggest(int round) {
        // If not same or subsequent round
        if (round_extra_suggest+1 < round) {
            round_extra_suggest = round;
            suggest_remaining++;
            return true;
        }
        return false;
    }
    
    
    /**
     * If haven't falsely accused, then are playing
     * @return true if have accusation left
     */
    public boolean isPlaying() {
        return !out;
    }
    
    public void initialiseChecklist(ArrayList<Card> gameCards){
        getChecklistEntries();
        checklist = new Checklist();
        gameCards.forEach((c) -> {
            checklist.add(c);
        });
        getChecklistEntries();
    }
    
    public ObservableList<ChecklistEntry> getChecklistEntries(){
        return checklist.getEntries();
    }
}
