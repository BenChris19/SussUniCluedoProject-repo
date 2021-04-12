/*
 *      The Tile
 *
 *      A place where the player can move to
 *      Can be a start or tunnel?
 */
package com.seteam23.clue.game.board;

import java.util.HashMap;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.css.PseudoClass;
import javafx.util.Duration;

public class Tile extends Place {

    private HashMap<String, Tile> adjacent;

    private FadeTransition flasher;
    private static PseudoClass FLASH_HIGHLIGHT;
    private boolean flashing;

    /**
     * Constructor
     */
    public Tile() {
        // Can hold one Player at a time
        super(1);
        
        // 

        adjacent = new HashMap<>();
        adjacent.put("N", null);
        adjacent.put("S", null);
        adjacent.put("E", null);
        adjacent.put("W", null);

        FLASH_HIGHLIGHT = PseudoClass.getPseudoClass("flash-highlight");
        flasher = new FadeTransition(Duration.millis(1200), this.getButton());
        flasher.setFromValue(0.0);
        flasher.setToValue(0.3);
        flasher.setCycleCount(Animation.INDEFINITE);
        flasher.setAutoReverse(true);
        
    }
    
    public void startFlashing() {
        if(!flashing){
            flashing = true;
            this.getButton().pseudoClassStateChanged(FLASH_HIGHLIGHT, true);
            flasher.play();
        }
    }
    public boolean isFlashing(){
        return this.flashing;
    }
    
    public void stopFlashing() {
        if (flashing) {
            flashing = false;
            this.getButton().pseudoClassStateChanged(FLASH_HIGHLIGHT, false);
            flasher.stop();
        }
    }

    /**
     * Map of which items are in each compass direction
     *
     * @return Map of which Places are directly adjacent
     */
    public HashMap<String, Tile> getAdjacent() {
        return adjacent;
    }

    /**
     * Specify which tile instance is in which compass direction
     *
     * @param direction
     * @param tile
     */
    public void setAdjacent(String direction, Tile tile) {
        adjacent.put(direction, tile);
    }
}
