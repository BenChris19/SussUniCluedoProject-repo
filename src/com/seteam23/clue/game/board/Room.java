/*
 *      The Rooms
 *
 *      Blank room Entity
 *      Exist within the Board
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameControllerRevised.PLAYER_MARKERS;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.PlayerRevised;
import java.util.ArrayList;

public class Room extends Place{
    private final int x, y;
    private final int width, height;
    private Card weapon;
    private final ArrayList<Door> doors;
    private final ArrayList<PlayerRevised> players;
    private final int[][] playerIndicatorPos;
    private final String name;
    private Card[] weapons;
    
    /**
     * Constructor
     * @param roomName
     * @param playerIndicatorPos
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Room(String roomName, int[][] playerIndicatorPos, int x, int y, int width, int height) {
        // Can hold 6 people (SET TO MAX PLAYERS FOR GAME)
        super(6);
        
        this.name = roomName;
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
    public boolean addOccupier(PlayerRevised player) {
        PLAYER_MARKERS.get(name)[getOrder(player.NAME)].setVisible(true);
        return players.add(player);
    }
    
    /**
     * 
     * @param player 
     */
    @Override
    public boolean removeOccupier(PlayerRevised player) {
        PLAYER_MARKERS.get(name)[getOrder(player.NAME)].setVisible(false);
        return players.remove(player);
    }
    
    /**
     * 
     * @param weaponToAdd 
     */
    public void addWeapon(Card weaponToAdd) {
        weapon = weaponToAdd;
    }

    public String getName() {
        return name;
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
    
    /**
     * Get index between 0 to 5 (inclusive) based on which character name is given
     * @param name
     * @return 
     */
    private int getOrder(String name) {
        switch (name) {
            case "Miss Scarlett":
                return 0;
            case "Col Mustard":
                return 1;
            case "Mrs White":
                return 2;
            case "Rev Green":
                return 3;
            case "Mrs Peacock":
                return 4;
            default: //Prof Plum
                return 5;
        }
    }
    
}
