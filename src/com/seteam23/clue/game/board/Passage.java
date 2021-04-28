/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.getBoard;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getPlayer1;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Team 23
 */
public class Passage extends Tile {
    
    private final Room location;
    private final Room destination;
    
    /**
     * Secret Passage ONE WAY
     * @param source Starting Room
     * @param destination Where it goes to
     * @param x
     * @param y 
     */
    public Passage(Room source, Room destination, int x, int y) {
        super(x,y);
        
        this.location = source;
        this.destination = destination;
    }
    
    /**
     * When clicked, if in room, move player to new room
     */
    @Override
    public void activate() {
        location.removeOccupier(getPlayer1());
        destination.addOccupier(getPlayer1());
        Door d = destination.getDoors().get(0);
        getPlayer1().setCurrentPosYX(d.y, d.x);

    }
    
    /**
     * Creates the Tile's JavaFX Button
     * @return button
     */
    @Override
    protected Button createButton() {
        Button button = new Button();

        button.setOnAction((ActionEvent e) -> {
            if (getPlayer1().isEndTurn() == true && getPlayer1().isInRoom()) {
                this.activate();
            }
        });
        
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }

    public Room getLocation() {
        return location;
    }

    public Room getDestination() {
        return destination;
    }
}
