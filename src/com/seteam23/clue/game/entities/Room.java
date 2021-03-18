/*
 *      The Rooms
 *
 *      Blank room Entity
 *      Exist within the Board
 */
package com.seteam23.clue.game.entities;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Room extends Place{
    private int x, y;
    private int width, height;
    
    private ArrayList<Tile> doors;
    
    public Room(int x, int y, int width, int height) {
        super(6);
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        doors = new ArrayList<>();
    }
    
    protected Door addDoor(String entry_from) {
        Door door = new Door(this, entry_from);
        doors.add(door);
        return door;
    }
}
