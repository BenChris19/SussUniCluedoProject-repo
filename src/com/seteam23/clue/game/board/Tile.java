/*
 *      The Tile
 *
 *      A place where the player can move to
 *      Can be a start or tunnel?
 */
package com.seteam23.clue.game.board;

import java.util.HashMap;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.css.PseudoClass;

public class Tile extends Place {

    private HashMap<String, Tile> adjacent;

    private Timeline flasher;
    private PseudoClass flashHighlight;
    private boolean flashing;

    /**
     * Constructor
     */
    public Tile() {
        // Can hold one Player at a time
        super(1);

        adjacent = new HashMap<>();
        adjacent.put("N", null);
        adjacent.put("S", null);
        adjacent.put("E", null);
        adjacent.put("W", null);

        flashHighlight = PseudoClass.getPseudoClass("flash-highlight");
        flasher = new Timeline(
            new KeyFrame(javafx.util.Duration.seconds(1.0), e -> {
                this.getButton().pseudoClassStateChanged(flashHighlight, true);
            }),
            new KeyFrame(javafx.util.Duration.seconds(1.0), e -> {
                this.getButton().pseudoClassStateChanged(flashHighlight, false);
            })
        );
        flasher.setCycleCount(Animation.INDEFINITE);
    }
    
    public void startFlashing() {
        if(!flashing){
            flashing = true;
            flasher.play();
        }
    }
    
    public void stopFlashing() {
        if (flashing) {
            flashing = false;
            flasher.stop();
            this.getButton().pseudoClassStateChanged(flashHighlight, false);
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
