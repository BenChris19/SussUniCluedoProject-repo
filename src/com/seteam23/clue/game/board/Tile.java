package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.Game.getCurrentPlayer;
import com.seteam23.clue.game.entities.Player;
import java.util.HashMap;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**Creates the subclass tile from place, which is located around the entire board, except for doors and rooms
 *
 * @author Team 23
 */
public class Tile extends Place {

    private final HashMap<String, Tile> ADJACENT;
    private final Button BUTTON;

    private static PseudoClass FLASH_HIGHLIGHT;
    private boolean flashing;
    
    public final int x;
    public final int y;
   

    /**
     * Constructs a tile inside the game board
     * @param x
     * @param y
     */
    public Tile(int x, int y) {
        // Can hold one Player at a time
        super(1);
        
        this.x = x;
        this.y = y;
        
        this.BUTTON = createButton();

        ADJACENT = new HashMap<>();
        ADJACENT.put("N", null);
        ADJACENT.put("S", null);
        ADJACENT.put("E", null);
        ADJACENT.put("W", null);

        FLASH_HIGHLIGHT = PseudoClass.getPseudoClass("flash-highlight");

        
    }
    
    /**Getter method to get location of tile
     * 
     * @return the coordinates of the tile
     */
    public int[] getCoords() {
        return new int[]{this.x, this.y};
    }
    
    /**
     * Lights up tile
     */
    public void startFlashing() {
        if(!flashing){
            flashing = true;
            this.getButton().pseudoClassStateChanged(FLASH_HIGHLIGHT, true);
        }
    }
    
    /**Returns true of false depending on tile is flashing
     * 
     * @return true if Tile is flashing, false otherwise
     */
    public boolean isFlashing(){
        return this.flashing;
    }
    
    /**
     * Un lights the tile
     */
    public void stopFlashing() {
        if (flashing) {
            flashing = false;
            this.getButton().pseudoClassStateChanged(FLASH_HIGHLIGHT, false);
        }
    }

    /**
     * Map of which items are in each compass direction
     *
     * @return Map of which Places are directly ADJACENT
     */
    public HashMap<String, Tile> getAdjacent() {
        return ADJACENT;
    }

    /**
     * Specify which tile instance is in which compass direction
     *
     * @param direction
     * @param tile
     */
    public void setAdjacent(String direction, Tile tile) {
        ADJACENT.put(direction, tile);
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
    public boolean addOccupier(Player p) {
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
    public boolean removeOccupier(Player p) {
        // remove player icon from tile
        this.getButton().getStyleClass().remove("toggle-"+p.NAME.split(" ",-1)[1]);
        this.occupied = false;
        return this.occupiers.remove(p);
    }
    
    
    /**
     * Creates the Tile's JavaFX Button
     * @return BUTTON
     */
    protected Button createButton() {
        Button button = new Button();
        
        button.setOnAction((ActionEvent e) -> {
            if (getCurrentPlayer().getSearchSpace().contains(this) && getCurrentPlayer().getClass().equals(Player.class)) {
                this.activate();
            }
        });
        
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }
    
    /**
     * Gets the associated button
     * @return 
     */
    public Button getButton() {
        return this.BUTTON;
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
