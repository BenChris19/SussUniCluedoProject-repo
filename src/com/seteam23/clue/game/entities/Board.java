
/*
 *      The Board / Map

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
    // Reference to the Current used Board Controller
    private BoardController controller;
    
    // Objects
    private Place[] places; //All
    private Room[] rooms; //Room Doors
    private Tile[] tiles; //Tiles
    
    private Player[] players;
    private static Player currentPlayer;
    
    public Board(BoardController controller) {
        this.controller = controller;
    }
    
    public Board(BoardController controller, String img_path) {
        this.controller = controller;
        setBackgroundImage(img_path);
    }
    
    public void createGrid() {
        
    }
    
    public Tile getTile(int x, int y) {
        return new Tile();
    }
    
    public void setBackgroundImage(String img_path) {
        controller.changeBackground(img_path);
    }
}
