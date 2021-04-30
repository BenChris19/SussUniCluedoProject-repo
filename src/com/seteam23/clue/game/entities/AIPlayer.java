/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.GameRevised;
import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author Team 23
 */
public class AIPlayer extends PlayerRevised {
    
    private String difficulty;
    private GameRevised game;
    //private ArrayList<Tile> searchSpace = new ArrayList<>();
    private Random r = new Random();
    
    
    /**
     * 
     * @param name
     * @param difficulty
     * @param imgPath
     */
    public AIPlayer(GameRevised game, String name, String imgPath, String difficulty) {
        super(name, imgPath);
        
        this.difficulty = difficulty;
        this.game = game;
    }
    
    
    /**
     * Reset remaining values
     */
    @Override
    public void newTurn() {
        rolls_remaining += 1;
        suggest_remaining += 1;
        
        Timeline timeline = new Timeline(
            // After 1s Roll Die, 2s for die roll animation
            new KeyFrame(Duration.seconds(1), e -> {
                this.game.CONTROLLER.rollDie();
            }),
                
            // After 4s Total, move
            new KeyFrame(Duration.seconds(4), e -> {
                switch (this.difficulty) {
                    case "HARD":
                        searchSpace.get(r.nextInt(searchSpace.size())).activate();
                        break;
                    // EASY OR MEDIUM
                    default:
                        searchSpace.get(r.nextInt(searchSpace.size())).activate();
                }
                
            }),
            // After 6s Total, if in room, suggest
            new KeyFrame(Duration.seconds(6), e -> {
                
                ArrayList<Card> choice;
                if (isInRoom()) {
                    // SUGGEST
                    switch (this.difficulty) {
                        // Random Player and Weapon
                        case "EASY":
                            this.game.CONTROLLER.setPerson(r.nextInt(6));
                            this.game.CONTROLLER.setWeapon(r.nextInt(6));
                            break;
                            
                        // Random Player and Weapon that haven't checked yet
                        case "HARD":
                            choice = this.game.SUSPECT_CARDS;
                            for (Card c : this.checklist.keys()) {
                                if (this.checklist.getValue(c)) choice.remove(c);
                            }
                            if(choice.size()>0)
                                this.game.CONTROLLER.setPerson(game.getOrder(choice.get(r.nextInt(choice.size())).getName()));
                            else
                                this.game.CONTROLLER.setPerson(0);
                            
                            choice = this.game.WEAPON_CARDS;
                            for (Card c : this.checklist.keys()) {
                                if (this.checklist.getValue(c)) choice.remove(c);
                            }
                            if(choice.size()>0)
                                this.game.CONTROLLER.setPerson(game.getOrder(choice.get(r.nextInt(choice.size())).getName()));
                            else
                                this.game.CONTROLLER.setPerson(0);
                            
                        // MEDIUM : Random Player and Weapon from cards dont have in hand
                        default:
                            choice = this.game.SUSPECT_CARDS;
                            for (Card c : this.cards) {
                                choice.remove(c);
                            }
                            this.game.CONTROLLER.setPerson(game.getOrder(choice.get(r.nextInt(choice.size())).getName()));
                            
                            choice = this.game.WEAPON_CARDS;
                            for (Card c : this.cards) {
                                choice.remove(c);
                            }
                            this.game.CONTROLLER.setWeapon(this.game.CONTROLLER.getWeapon().getItems().indexOf(choice.get(r.nextInt(choice.size()))));
                        }
                    
                    this.game.CONTROLLER.makeSuggestion();
                    
                    
                    // ACCUSE
                    int suspectUnchecked = 0;
                    for (Card c : this.game.SUSPECT_CARDS) {
                        if (!this.checklist.getValue(c)) suspectUnchecked++;
                    }
                    int weaponUnchecked = 0;
                    for (Card c : this.game.WEAPON_CARDS) {
                        if (!this.checklist.getValue(c)) weaponUnchecked++;
                    }
                    int roomUnchecked = 0;
                    for (Card c : this.game.ROOM_CARDS) {
                        if (!this.checklist.getValue(c)) roomUnchecked++;
                    }
                    if (suspectUnchecked == 1 && weaponUnchecked == 1 && roomUnchecked == 1) {
                        try {
                            this.game.CONTROLLER.makeAccusation();
                        } catch (IOException ex) {
                            Logger.getLogger(AIPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            }),
                
            new KeyFrame(Duration.seconds(8), e -> {
                this.game.CONTROLLER.endTurn();
            })
        );
        timeline.play();
    }
    
    
    /**
     * Get Furthest Tiles and Doors which can be moved to depending if in room or tile
     * @param dieRoll
     * @return List of Tiles reachable from current location
     */
    @Override
    public ArrayList<Tile> setSearchSpace(int dieRoll) {
        switch (this.difficulty) {
            case "EASY":
                this.searchSpace = easySearchSpace(dieRoll);
                return this.searchSpace;
            case "HARD":
                this.searchSpace = hardSearchSpace(dieRoll);
                return this.searchSpace;
            default:
                this.searchSpace = mediumSearchSpace(dieRoll);
                return this.searchSpace;
        }
    }
    
    public ArrayList<Tile> easySearchSpace(int dieRoll) {
        if (isInRoom()) {
            return this.game.BOARD.reachableFrom((Room)location, dieRoll);
        }
        else {
            return this.game.BOARD.reachableFrom((Tile)location, dieRoll);
        }
    }
    
    public ArrayList<Tile> mediumSearchSpace(int dieRoll) {
        if (isInRoom()) {
            return this.game.BOARD.furthestReachableFrom((Room)location, dieRoll);
        }
        else {
            return this.game.BOARD.furthestReachableFrom((Tile)location, dieRoll);
        }
    }
    
    public ArrayList<Tile> hardSearchSpace(int dieRoll) {
        if (isInRoom()) {
            return this.game.BOARD.furthestReachableFrom((Room)location, dieRoll);
        }
        else {
            return this.game.BOARD.furthestReachableFrom((Tile)location, dieRoll);
        }
    }
}
