/*
 *      The Tile
 *
 *      A place where the player can move to
 *      Can be a start or tunnel?
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameRevised.getCurrentPlayer;
import com.seteam23.clue.game.entities.PlayerRevised;
import java.util.HashMap;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Tile extends Place {

    private HashMap<String, Tile> adjacent;
    private Button button;

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
        
        this.button = createButton();
        
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
    
    /**
     * 
     * @param hm
     * @param value
     * @return 
     */
    public String getKeyFromValue(HashMap hm, Door value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o)!=null && hm.get(o).equals(value)) {
                return (String)o;
            }
        }
        return null;
    }
    
    /**
     * Adds the Player to the Place if there is space for them
     * @param p Player to add to Place
     * @return True if added to ArrayList
     */
    @Override
    public boolean addOccupier(PlayerRevised p) {
        if (this.occupiers.size() < this.max_players) {
            this.getButton().getStyleClass().add("toggle-"+p.NAME.split(" ",-1)[1]);
            this.occupied = true;
            return this.occupiers.add(p);
        }
        return false;
    }
    
    /**
     * Removes Player from the Place
     * @param p Player to remove
     * @return True if removed from ArrayList
     */
    @Override
    public boolean removeOccupier(PlayerRevised p) {
        // remove player icon from tile
        this.getButton().getStyleClass().remove("toggle-"+p.NAME.split(" ",-1)[1]);
        this.occupied = false;
        return this.occupiers.remove(p);
    }
    
    
    /**
     * Creates the Tile's JavaFX Button
     * @return button
     */
    protected Button createButton() {
        Button button = new Button();
        
        button.setOnAction((ActionEvent e) -> {
            if (getCurrentPlayer().getSearchSpace().contains(this)) {
                this.activate();
            }
        });
        
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }
    
    /**
     * Gets the associated button
     */
    public Button getButton() {
        return this.button;
    }
    
    /**
     * Activated effect when button clicked
     */
    public void activate() {
        getCurrentPlayer().getLocation().removeOccupier(getCurrentPlayer());
        getCurrentPlayer().moveTo(this);
        this.addOccupier(getCurrentPlayer());
    }
}
