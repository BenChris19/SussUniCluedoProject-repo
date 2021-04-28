/*
 *      The Rooms
 *
 *      Blank room Entity
 *      Exist within the Board
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.GameController;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.Player;
import java.util.ArrayList;

public class Room extends Place{
    private int x, y;
    private int width, height;
    private Card weapon;
    private ArrayList<Door> doors;
    private ArrayList<Player> players;
    private final int[][] playerIndicatorPos;
    private String roomName;
    private Card[] weapons;
    
    /**
     * Constructor
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Room(String roomName, int[][] playerIndicatorPos, int x, int y, int width, int height) {
        // Can hold 6 people (SET TO MAX PLAYERS FOR GAME)
        super(6);
        
        this.roomName = roomName;
        this.playerIndicatorPos = playerIndicatorPos;
        this.x = x;
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
    @Override
    public boolean addOccupier(Player player) {
        GameController.showPlayerRoom(roomName, player.getName());
        return players.add(player);
    }
    
    /**
     * 
     * @param player 
     */
    @Override
    public boolean removeOccupier(Player player) {
        GameController.hidePlayerRoom(roomName, player.getName());
        return players.remove(player);
    }
    
    /**
     * Do nothing on click
     */
    @Override
    public void activate() {
    }
    
    /**
     * 
     * @param weaponToAdd 
     */
    public void addWeapon(Card weaponToAdd) {
        weapon = weaponToAdd;
    }

    public String getName() {
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

    public ArrayList<Door> getDoors() {
        return doors;
    }
    
    public int[][] getPlayerIndicatorPos() {
        return playerIndicatorPos;
    }
    
}
