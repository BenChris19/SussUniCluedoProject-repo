/*
 *      The Rooms
 *
 *      Blank room Entity
 *      Exist within the Board
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.entities.Card;
import java.util.ArrayList;

public class Room extends Place{
    private int x, y;
    private int width, height;
    private Card weapon;
    private ArrayList<Tile> doors;
    private String roomName;
    private Card[] weapons;
    
    /**
     * Constructor
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Room(String roomName,int x, int y, int width, int height) {
        // Can hold 6 people (SET TO MAX PLAYERS FOR GAME)
        super(6);
        
        this.x = x;
        this.roomName = roomName;
        this.y = y;
        this.width = width;
        this.height = height;
        
        doors = new ArrayList<>();
    }

    
    /**
     * 
     * @param weaponToAdd 
     */
    public void addWeapon(Card weaponToAdd) {
        weapon = weaponToAdd;
    }

    public String getRoomName() {
        return roomName;
    }

    
    /**
     * 
     */
    public void removeWeapon() {
        weapon = null;
    }
    
    /**
     * 
     * @return 
     */
    public Card getWeapon() {
        return weapon;
    }
        
    /**
     * Create a new Door for the room that can entered only from a given direction
     * @param entry_from
     * @return 
     */
    protected Door addDoor(String entry_from) {
        Door door = new Door(this, entry_from);
        doors.add(door);
        return door;
    }

    public ArrayList<Tile> getDoors() {
        return doors;
    }
    
}