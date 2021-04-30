package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameRevised.getCurrentPlayer;
import static com.seteam23.clue.game.GameRevised.getRound;

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
        getCurrentPlayer().getLocation().removeOccupier(getCurrentPlayer());
        getCurrentPlayer().moveTo(this);
        this.addOccupier(getCurrentPlayer());
        getCurrentPlayer().extraSuggest(getRound());
    }
}

