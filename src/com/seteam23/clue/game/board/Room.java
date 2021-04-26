/*
 *      The Rooms
 *
 *      Blank room Entity
 *      Exist within the Board
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.Game;
import com.seteam23.clue.game.GameController;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.Player;
import java.util.ArrayList;

public class Room extends Place{
    private int x, y;
    private int width, height;
    private Card weapon;
    private ArrayList<Tile> doors;
    private ArrayList<Player> players;
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
        players = new ArrayList<>();
    }
    
    /**
     * 
     * @param player 
     */
    public void addPlayer(Player player) {
        players.add(player);
        int i;
        switch (player.getName()) {
            case "Col Mustard":
                i = 1;
                break;
            case "Mrs White":
                i = 2;
                break;
            case "Rev Green":
                i = 3;
                break;
            case "Mrs Peacock":
                i = 4;
                break;
            case "Prof Plum":
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
    }
    
    /**
     * 
     * @param player 
     */
    public boolean removePlayer(Player player) {
        int i;
        switch (player.getName()) {
            case "Col Mustard":
                i = 1;
                break;
            case "Mrs White":
                i = 2;
                break;
            case "Rev Green":
                i = 3;
                break;
            case "Mrs Peacock":
                i = 4;
                break;
            case "Prof Plum":
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        return players.remove(player);
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
    protected Door addDoor(String entry_from, int x, int y) {
        Door door = new Door(this, entry_from, x, y);
        doors.add(door);
        return door;
    }

    public ArrayList<Tile> getDoors() {
        return doors;
    }
    
}
