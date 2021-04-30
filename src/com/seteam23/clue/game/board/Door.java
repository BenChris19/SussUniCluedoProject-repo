package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameRevised.getCurrentPlayer;

/**A subclass of Tile, which is located in the entrance of every ROOM
 *
 * @author Team23
 */
public class Door extends Tile {
    
    private final Room ROOM;
    private final String entry;
    
    /**
     * Constructor
     * @param room
     * @param entry_from 
     * @param x 
     * @param y 
     */
    public Door(Room room, String entry_from, int x, int y) {
        super(x, y);
        
        this.ROOM = room;
        this.entry = entry_from;
    }
    
    /**
     * Overrides the button activate function
     */
    @Override    
    public void activate() {
        getCurrentPlayer().getLocation().removeOccupier(getCurrentPlayer());
        getCurrentPlayer().enterRoom(ROOM);
        getCurrentPlayer().clearSearchSpace();
        ROOM.addOccupier(getCurrentPlayer());
    }
    
    /**
     * Get the room the door is associated with
     * @return room connected to
     */
    public Room getRoom() {
        return this.ROOM;
    }
    
    /**
     * Get which direction the door is entered from
     * @return entry compass direction
     */
    public String entryFrom() {
        return this.entry;
    }
    
}