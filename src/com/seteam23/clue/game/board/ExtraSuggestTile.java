package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameController.getDieRoll;
import static com.seteam23.clue.game.Game.BOARD;
import static com.seteam23.clue.game.Game.getCurrentPlayer;
import static com.seteam23.clue.game.Game.getRound;
import java.util.HashMap;

/**Creates a tile which gives the player an extra suggestion if they land on it
 *
 * @author Team 23
 */
public class ExtraSuggestTile extends Tile {
    
    /**Creates the extra suggestion tile
     *
     * @param x
     * @param y
     */
    public ExtraSuggestTile(int x, int y) {
        super(x, y);
        
        getButton().setText("🖎");   //Set special label to button
    }
    
    @Override
    /*
    *Activate the button with the extra suggestion, enabling the user to make one more suggestion when inside a room
    */
    public void activate() {
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
        getCurrentPlayer().extraSuggest(getRound());
        
        // Dist from
        int dx = Math.abs(s[0]-t[0]);
        int dy = Math.abs(s[1]-t[1]);
        
        // Reset 
        getCurrentPlayer().clearSearchSpace();
        BOARD.highlightTiles(getCurrentPlayer().setSearchSpace(getDieRoll()-dx-dy));
    }
}

