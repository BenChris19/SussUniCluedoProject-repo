/*
 *      The Board / Map
 *
 *      Constructs Rooms and Tiles
 *      Players exist within the Board
 */
package com.seteam23.clue.game.entities;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Board{
    private boolean[][] is_in_room;
    private Tile[][] tile_location;
    
    private Place[] places;
    private Room[] rooms;
    private Tile[] tiles;
    private String img_path;
    
    
    public Board() {
        this.img_path = "..\\..\\..\\..\\resources\\game\\clue-board.jpg";
    }
    
    public Board(String img_path) {
        this.img_path = img_path;
    }
    
    public void createGrid() {
        
    }
    
    public Tile getTile(int x, int y) {
        return new Tile();
    }
    
    protected String imgPath() {
        return "";
    }
}
