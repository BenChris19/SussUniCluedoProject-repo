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
 * @author Team 23
 */
public class ExtraRollTile extends Tile {
    
    public ExtraRollTile(int x, int y) {
        super(x, y);
        
        getButton().setText("⚅");
    }
    
    @Override
    public void activate() throws InterruptedException {
        getBoard().getTile(getPlayer1().getCurrentPosY(),getPlayer1().getCurrentPosX()).removeOccupier(getPlayer1());
        getPlayer1().setCurrentPosYX(GridPane.getColumnIndex(getButton()), GridPane.getRowIndex(getButton()));
        addOccupier(getPlayer1()); 
        // player.extraRoll();
    }
}
