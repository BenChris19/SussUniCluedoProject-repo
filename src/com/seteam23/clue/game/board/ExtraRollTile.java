package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameRevised.getCurrentPlayer;
import static com.seteam23.clue.game.GameRevised.getRound;

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
        getCurrentPlayer().getLocation().removeOccupier(getCurrentPlayer());
        getCurrentPlayer().moveTo(this);
        this.addOccupier(getCurrentPlayer());
        getCurrentPlayer().extraRoll(getRound());
    }
}
