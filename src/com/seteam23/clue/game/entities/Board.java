
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
    private Place[][] places; //All
    private Room[][] rooms; //Room Doors
    private Tile[][] tiles; //Tiles
    
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
        // Start Platforms
        createTile(0,5);
        createTile(0,18);
        createTile(16,0);
        createTile(23,7);
        createTile(9,24);
        createTile(14,24);
        // Empty Corner
        createTile(7,0);
        createTile(23,17);
        // Ballroom Start Exit
        createTile(7,23);
        createTile(8,23);
        createTile(9,23);
        createTile(14,23);
        createTile(15,23);
        createTile(16,23);
        // Room Corners
        createTile(6,6);
        createTile(6,10);
        createTile(5,19);
        createTile(16,15);
        createTile(17,15);
        createTile(18,15);
        // Library-Billard
        createTile(1,11);
        createTile(2,11);
        createTile(3,11);
        createTile(4,11);
        createTile(5,11);
        // Study-Library
        for (int x = 1; x <= 6; x++) {
            for (int y = 4; y <= 5; y++) {
                createTile(x, y);
            }
        }
        // Billard-Conservatory
        for (int x = 1; x <= 5; x++) {
            for (int y = 17; y <= 18; y++) {
                createTile(x, y);
            }
        }
        // Conservatory-Ballroom
        for (int x = 6; x <= 7; x++) {
            for (int y = 17; y <= 22; y++) {
                createTile(x, y);
            }
        }
        // Study-Hall
        for (int x = 7; x <= 8; x++) {
            for (int y = 1; y <= 6; y++) {
                createTile(x, y);
            }
        }
        // Hall-Lounge
        for (int x = 15; x <= 16; x++) {
            for (int y = 1; y <= 5; y++) {
                createTile(x, y);
            }
        }
        createTile(15,6);
        // Kitchen-Ballroom
        for (int x = 16; x <= 17; x++) {
            for (int y = 16; y <= 22; y++) {
                createTile(x, y);
            }
        }
        // Lounge-DiningRoom
        for (int x = 16; x <= 22; x++) {
            for (int y = 6; y <= 8; y++) {
                createTile(x, y);
            }
        }
        // DiningRoom-Kitchen
        for (int x = 18; x <= 22; x++) {
            for (int y = 16; y <= 17; y++) {
                createTile(x, y);
            }
        }
        // Ballroom Corridoor
        for (int x = 8; x <= 15; x++) {
            for (int y = 15; y <= 16; y++) {
                createTile(x, y);
            }
        }
        // Above Logo
        for (int x = 9; x <= 13; x++) {
            createTile(x,7);
        }
        // Left Logo
        for (int x = 6; x <= 8; x++) {
            for (int y = 11; y <= 16; y++) {
                createTile(x, y);
            }
        }
        for (int x = 7; x <= 8; x++) {
            for (int y = 7; y <= 10; y++) {
                createTile(x, y);
            }
        }
        // Right Logo
        for (int x = 14; x <= 15; x++) {
            for (int y = 7; y <= 17; y++) {
                createTile(x, y);
            }
        }
        
    }
    
    private Tile createTile(int x, int y) {
        Tile tile = new Tile();
        places[y][x] = tile;
        tiles[y][x] = tile;
        
        return tile;
    }
    
    private Room createRoom(int x, int y) {
        Room door = new Room();
        places[y][x] = door;
        rooms[y][x] = door;
        
        return door;
    }
    
    public Tile getTile(int x, int y) {
        return this.tiles[y][x];
    }
    
    public void setBackgroundImage(String img_path) {
        controller.changeBackground(img_path);
    }
}
