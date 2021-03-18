
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
        // Rooms
        Room study = createRoom(0,0,7,4);
        Room library = createRoom(1,6,5,5);
        Room billiard_room = createRoom(0,12,6,5);
        Room conservatory = createRoom(0,20,6,4);
        Room hall = createRoom(9,1,6,5);
        Room ballroom = createRoom(9,18,6,4);
        Room lounge = createRoom(1,18,5,4);
        Room dining_room = createRoom(17,10,6,4);
        Room kitchen = createRoom(19,19,4,4);

        // Doors
        addTile(study.addDoor("N"),6,3);
        addTile(library.addDoor("W"),6,8);
        addTile(library.addDoor("N"),3,10);
        addTile(billiard_room.addDoor("S"),1,12);
        addTile(billiard_room.addDoor("W"),5,15);
        addTile(conservatory.addDoor("W"),4,19);
        addTile(hall.addDoor("E"),9,4);
        addTile(hall.addDoor("N"),11,6);
        addTile(hall.addDoor("N"),12,6);
        addTile(ballroom.addDoor("E"),8,19);
        addTile(ballroom.addDoor("S"),9,17);
        addTile(ballroom.addDoor("S"),14,17);
        addTile(ballroom.addDoor("W"),15,19);
        addTile(lounge.addDoor("N"),17,5);
        addTile(dining_room.addDoor("S"),17,9);
        addTile(dining_room.addDoor("E"),16,12);
        addTile(kitchen.addDoor("S"),19,18);
        
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
        
        // Adjacency
        for (int y = 0; y<25; y++) {
            for (int x = 0; x<24; x++) {
                Tile tile = tiles[y][x];
                
                if (y - 1 > 0) tile.setAdjacent("N", tiles[y-1][x]);
                if (y + 1 > 0) tile.setAdjacent("S", tiles[y+1][x]);
                if (x - 1 > 0) tile.setAdjacent("E", tiles[y][x-1]);
                if (x + 1 > 0) tile.setAdjacent("W", tiles[y][x+1]);
            }
        }
        
    }
    
    private Tile createTile(int x, int y) {
        Tile tile = new Tile();
        places[y][x] = tile;
        tiles[y][x] = tile;
        
        return tile;
    }
    
    private void addTile(Tile tile, int x, int y) {
        places[y][x] = tile;
        tiles[y][x] = tile;
    }
    
    private Room createRoom(int x, int y, int width, int height) {
        Room room = new Room(x,y,width,height);
        places[y][x] = room;
        rooms[y][x] = room;
        
        return room;
    }
    
    public Tile getTile(int x, int y) {
        return this.tiles[y][x];
    }
    
    public void setBackgroundImage(String img_path) {
        controller.changeBackground(img_path);
    }
}
