/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.GameRevised;
import com.seteam23.clue.game.board.Place;
import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author Team23
 */
public class AIPlayer extends PlayerRevised {
    
    private String difficulty;
    
    private ArrayList<Tile> searchSpace = new ArrayList<>();
    private Random r = new Random();
    
    
    /**
     * 
     * @param name
     * @param difficulty
     * @param imgPath
     */
    public AIPlayer(String name, String difficulty, String imgPath) {
        super(name, imgPath);
        
        this.difficulty = difficulty;
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                GameRevised.CONTROLLER.rollDie();
            }),
            new KeyFrame(Duration.seconds(4), e -> {
                GameRevised.CONTROLLER.getSearchSpace();
            })
        );
        timeline.play();
    }
    
    
    /**
     * Reset remaining values
     */
    @Override
    public void newTurn() {
        rolls_remaining = 1;
        suggest_remaining = 1;
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                GameRevised.CONTROLLER.rollDie();
            }),
            new KeyFrame(Duration.seconds(4), e -> {
                searchSpace = GameRevised.CONTROLLER.getSearchSpace();
                
                searchSpace.get(r.nextInt(searchSpace.size())).activate();
            }),
            new KeyFrame(Duration.seconds(4), e -> {
                // other
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
    public ArrayList<Tile> getSearchSpace(int dieRoll) {
        switch (this.difficulty) {
            case "EASY":
                return easySearchSpace(dieRoll);
            case "MEDIUM":
                return mediumSearchSpace(dieRoll);
            case "HARD":
                return hardSearchSpace(dieRoll);
            default:
                return mediumSearchSpace(dieRoll);
        }
    }
    
    public ArrayList<Tile> easySearchSpace(int dieRoll) {
        if (isInRoom()) {
            return GameRevised.BOARD.reachableFrom((Room)location, dieRoll);
        }
        else {
            return GameRevised.BOARD.reachableFrom((Tile)location, dieRoll);
        }
    }
    
    public ArrayList<Tile> mediumSearchSpace(int dieRoll) {
        if (isInRoom()) {
            return GameRevised.BOARD.furthestReachableFrom((Room)location, dieRoll);
        }
        else {
            return GameRevised.BOARD.furthestReachableFrom((Tile)location, dieRoll);
        }
    }
    
    public ArrayList<Tile> hardSearchSpace(int dieRoll) {
        if (isInRoom()) {
            return GameRevised.BOARD.furthestReachableFrom((Room)location, dieRoll);
        }
        else {
            return GameRevised.BOARD.furthestReachableFrom((Tile)location, dieRoll);
        }
    }
}
