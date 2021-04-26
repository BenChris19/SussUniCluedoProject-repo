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
    
    public final int x;
    public final int y;
   

    /**
     * Constructor
     */
    public Tile(int x, int y) {
        // Can hold one Player at a time
        super(1);
        
        this.x = x;
        this.y = y;
        
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
    
    /**
     * 
     * @return 
     */
    public int[] getCoords() {
        return new int[]{this.x, this.y};
    }
    
    /**
     * 
     */
    public void startFlashing() {
        if(!flashing){
            flashing = true;
            this.getButton().pseudoClassStateChanged(FLASH_HIGHLIGHT, true);
            //flasher.play();
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean isFlashing(){
        return this.flashing;
    }
    
    /**
     * 
     */
    public void stopFlashing() {
        if (flashing) {
            flashing = false;
            this.getButton().pseudoClassStateChanged(FLASH_HIGHLIGHT, false);
            //flasher.stop();
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
    public String getKeyFromValue(HashMap hm, Door value) {
            for (Object o : hm.keySet()) {
              if (hm.get(o)!=null && hm.get(o).equals(value)) {
                return (String)o;
              }
            }
            return null;
          }
}
