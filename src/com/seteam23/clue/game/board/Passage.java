package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.Game.getCurrentPlayer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**Subclass of tile. Allows user to go to a different room from a room
 *
 * @author Team 23
 */
public class Passage extends Tile {
    
    private final Room LOCATION;
    private final Room DESTINATION;
    
    /**
     * Secret Passage ONE WAY
     * @param source Starting Room
     * @param destination Where it goes to
     * @param x 
     * @param y 
     */
    public Passage(Room source, Room destination, int x, int y) {
        super(x,y);
        
        this.LOCATION = source;
        this.DESTINATION = destination;
    }
    
    /**
     * When clicked, if in room, move player to new room
     */
    @Override
    public void activate() {
        getCurrentPlayer().clearSearchSpace();
        LOCATION.removeOccupier(getCurrentPlayer());
        getCurrentPlayer().enterRoom(DESTINATION);
        DESTINATION.addOccupier(getCurrentPlayer());
    }
    
    /**
     * Creates the Tile's JavaFX Button
     * @return button
     */
    @Override
    protected Button createButton() {
        Button button = new Button();

        button.setOnAction((ActionEvent e) -> {
            if (getCurrentPlayer().isInRoom()) {
                this.activate();
            }
        });
        
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }

    /**Get the location of the room with the secret passage
     *
     * @return the room with the secret passage
     */
    public Room getLocation() {
        return LOCATION;
    }

    /**Getter method for the room destination
     *
     * @return the room the player travel to using the secret passage
     */
    public Room getDestination() {
        return DESTINATION;
    }
}
