/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.GameRevised;
import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import java.util.ArrayList;
import java.util.Random;
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
        rolls_remaining = 1;
        suggest_remaining = 1;
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> {
                this.game.CONTROLLER.rollDie();
            }),
            new KeyFrame(Duration.seconds(4), e -> {
                //searchSpace = this.getSearchSpace();
                
                searchSpace.get(r.nextInt(searchSpace.size())).activate();
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
                this.searchSpace = hardSearchSpace(dieRoll);
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
            return this.game.BOARD.reachableFrom((Room)location, dieRoll);
        }
        else {
            return this.game.BOARD.reachableFrom((Tile)location, dieRoll);
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
