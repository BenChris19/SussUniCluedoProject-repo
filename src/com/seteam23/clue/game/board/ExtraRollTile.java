package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.getDieRoll;
import static com.seteam23.clue.game.Game.BOARD;
import static com.seteam23.clue.game.Game.getCurrentPlayer;
import static com.seteam23.clue.game.Game.getRound;
import java.util.HashMap;

/**Allows the user to do an extra roll for the next turn
 * 
 * @author Team 23
 */
public class ExtraRollTile extends Tile {
    
    /**Creates the extra roll tile
     *
     * @param x
     * @param y
     */
    public ExtraRollTile(int x, int y) {
        super(x, y);
        
        getButton().setText("⚅");
    }
    
    @Override
    /*
    *Activates the button which allows the user to roll again when it gets back to their turn
    */
    public void activate()  {
        Tile start;
        int[] t = this.getCoords();
        HashMap<Integer, Door> distToDoor = new HashMap<>();
        int shortDist = Integer.MAX_VALUE;
        
        if (getCurrentPlayer().getLocation() instanceof Room) {
            // Get Closest Door
            Room room = (Room)getCurrentPlayer().getLocation();
            for (Door door : room.getDoors()) {
                int[] d = door.getCoords();
                
                int dx = Math.abs(d[0]-t[0]);
                int dy = Math.abs(d[1]-t[1]);
                
                distToDoor.put(dx+dy, door);
            }
            // Find shortest
            for (int dist : distToDoor.keySet()) {
                shortDist = dist < shortDist ? dist : shortDist;
            }
            // Start tile is closest door
            start = distToDoor.get(shortDist);
            
        }
        // If tile
        else {
            start = (Tile)getCurrentPlayer().getLocation();
        }
        int[] s = start.getCoords();
        
        // Move Player and Extra Roll
        getCurrentPlayer().getLocation().removeOccupier(getCurrentPlayer());
        getCurrentPlayer().moveTo(this);
        this.addOccupier(getCurrentPlayer());
        getCurrentPlayer().extraRoll(getRound());
        
        // Dist from
        int dx = Math.abs(s[0]-t[0]);
        int dy = Math.abs(s[1]-t[1]);
        
        // Reset 
        getCurrentPlayer().clearSearchSpace();
        BOARD.highlightTiles(getCurrentPlayer().setSearchSpace(getDieRoll()-dx-dy));
    }
}
