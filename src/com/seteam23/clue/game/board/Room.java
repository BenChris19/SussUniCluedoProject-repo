package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.PLAYER_MARKERS;
import com.seteam23.clue.game.entities.Player;
import java.util.ArrayList;

/**Subclass of place, occupies the rooms on the board image
 *
 * @author Team 23
 */
public class Room extends Place{
    private final int x, y;
    private final int width, height;
    private final ArrayList<Door> doors;
    private final ArrayList<Player> players;
    private final int[][] playerIndicatorPos;
    private final String name;

    
    /**
     * Constructs a room
     * @param roomName  Name of the room i.e Study, hall, ballroom...
     * @param playerIndicatorPos    Gets the player icons
     * @param x     //Gets the x coordinate of the board
     * @param y     //Gets the y coordinate of the board
     * @param width //Gets the width of the room, how much it occupies on the board
     * @param height //Gets the height of the room, how much it occupies on the board
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
    
    /**Add a player to the room, to make suggestions/accusations
     * 
     * @param player 
     * @return  adds an occupier into the room
     */
    @Override
    public boolean addOccupier(Player player) {
        PLAYER_MARKERS.get(name)[getOrder(player.NAME)].setVisible(true);
        return players.add(player);
    }
    
    /**Gets player to come out of the room
     * 
     * @param player 
     * @return  removes occupied from room
     */
    @Override
    public boolean removeOccupier(Player player) {
        PLAYER_MARKERS.get(name)[getOrder(player.NAME)].setVisible(false);
        return players.remove(player);
    }
    
    /**Get the name of the room
     *
     * @return
     */
    public String getName() {
        return name;
    }
 
    /**
     * Create a new Door for the room that can entered only from a given direction
     * @param entry_from
     * @param x from which tile can you enter room x-coordinate
     * @param y from which tile can you enter room y-coordinate
     * 
     * @return the door to that room
     */
    protected Door addDoor(String entry_from, int x, int y) {
        Door door = new Door(this, entry_from, x, y);
        doors.add(door);
        return door;
    }

    /**Gets the doors to that room
     *
     * @return the doors in that room
     */
    public ArrayList<Door> getDoors() {
        return doors;
    }
    
    /**Gets the location of the player within the room
     *
     * @return the location of the player
     */
    public int[][] getPlayerIndicatorPos() {
        return playerIndicatorPos;
    }
    
    /**
     * Get index between 0 to 5 (inclusive) based on which character name is given
     * @param name
     * @return the order of the character given in the parameter
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
