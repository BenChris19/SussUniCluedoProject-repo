package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.GameRevised;
import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**AI player class subclass of Player
 *
 * @author Team 23
 */
public class AIPlayer extends PlayerRevised {
    
    private final String DIFFICULTY;
    private final GameRevised game;
    private final Random R = new Random();
    
    
    /**Creates the AI player class, which behaves similarly to a player
     * 
     * @param game
     * @param name
     * @param imgPath
     * @param difficulty
     */
    public AIPlayer(GameRevised game, String name, String imgPath, String difficulty) {
        super(name, imgPath);
        
        this.DIFFICULTY = difficulty;
        this.game = game;
    }
    
    
    /**
     * Reset remaining values
     */
    @Override
    public void newTurn() {
        rolls_remaining += 1;
        suggest_remaining += 1;
        
        Timeline timeline = new Timeline(
            // After 1s Roll Die, 2s for die roll animation
            new KeyFrame(Duration.seconds(1), e -> {
                this.game.CONTROLLER.rollDie();
            }),
                
            // After 4s Total, move
            new KeyFrame(Duration.seconds(4), e -> {
                switch (this.DIFFICULTY) {
                    case "HARD":
                        searchSpace.get(R.nextInt(searchSpace.size())).activate();
                        break;
                    // EASY OR MEDIUM
                    default:
                        searchSpace.get(R.nextInt(searchSpace.size())).activate();
                }
                
            }),
            // After 6s Total, if in room, suggest
            new KeyFrame(Duration.seconds(6), (ActionEvent e) -> {
                ArrayList<Card> choice;
                if (isInRoom()) {
                    // SUGGEST
                    switch (AIPlayer.this.DIFFICULTY) {
                        // Random Player and Weapon
                        case "EASY":
                            AIPlayer.this.game.CONTROLLER.setPerson(R.nextInt(6));
                            AIPlayer.this.game.CONTROLLER.setWeapon(R.nextInt(6));
                            break;
                            // Random Player and Weapon that haven't checked yet
                        case "HARD":
                            choice = AIPlayer.this.game.SUSPECT_CARDS;
                            for (Card c : AIPlayer.this.checklist.keys()) {
                                if (AIPlayer.this.checklist.getValue(c)) {
                                    choice.remove(c);
                                }
                            }
                            if (choice.size()>0) {
                                AIPlayer.this.game.CONTROLLER.setPerson(game.getOrder(choice.get(R.nextInt(choice.size())).getName()));
                            } else {
                                AIPlayer.this.game.CONTROLLER.setPerson(0);
                            }
                            choice = AIPlayer.this.game.WEAPON_CARDS;
                            for (Card c : AIPlayer.this.checklist.keys()) {
                                if (AIPlayer.this.checklist.getValue(c)) {
                                    choice.remove(c);
                                }
                            }
                            if (choice.size()>0) {
                                AIPlayer.this.game.CONTROLLER.setPerson(game.getOrder(choice.get(R.nextInt(choice.size())).getName()));
                            } else {
                                AIPlayer.this.game.CONTROLLER.setPerson(0);
                            }
                            // MEDIUM : Random Player and Weapon from cards dont have in hand
                        default:
                            choice = AIPlayer.this.game.SUSPECT_CARDS;
                            for (Card c : AIPlayer.this.cards) {
                                choice.remove(c);
                            }
                            AIPlayer.this.game.CONTROLLER.setPerson(game.getOrder(choice.get(R.nextInt(choice.size())).getName()));
                            choice = AIPlayer.this.game.WEAPON_CARDS;
                            for (Card c : AIPlayer.this.cards) {
                                choice.remove(c);
                            }
                            AIPlayer.this.game.CONTROLLER.setWeapon(AIPlayer.this.game.CONTROLLER.getWeapon().getItems().indexOf(choice.get(R.nextInt(choice.size()))));
                    }
                    AIPlayer.this.game.CONTROLLER.makeSuggestion();
                    // ACCUSE
                    int suspectUnchecked = 0;
                    suspectUnchecked = AIPlayer.this.game.SUSPECT_CARDS.stream().filter((c) -> (!AIPlayer.this.checklist.getValue(c))).map((_item) -> 1).reduce(suspectUnchecked, Integer::sum);
                    int weaponUnchecked = 0;
                    weaponUnchecked = AIPlayer.this.game.WEAPON_CARDS.stream().filter((c) -> (!AIPlayer.this.checklist.getValue(c))).map((_item) -> 1).reduce(weaponUnchecked, Integer::sum);
                    int roomUnchecked = 0;
                    roomUnchecked = AIPlayer.this.game.ROOM_CARDS.stream().filter((c) -> (!AIPlayer.this.checklist.getValue(c))).map((_item) -> 1).reduce(roomUnchecked, Integer::sum);
                    if (suspectUnchecked == 1 && weaponUnchecked == 1 && roomUnchecked == 1) {
                        try {
                            AIPlayer.this.game.CONTROLLER.makeAccusation();
                        }catch (IOException ex) {
                            Logger.getLogger(AIPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        }),
                
            new KeyFrame(Duration.seconds(8), e -> {
                this.game.CONTROLLER.endTurn();
            })
        );
        timeline.play();
    }
    
    
    /**
     * Get Furthest Tiles and Doors which can be moved to depending if in room or tile
     * @param dieRoll
     * @return List of Tiles reachable from current location
     */
    @Override
    public ArrayList<Tile> setSearchSpace(int dieRoll) {
        switch (this.DIFFICULTY) {
            case "EASY":
                this.searchSpace = easySearchSpace(dieRoll);
                return this.searchSpace;
            case "HARD":
                this.searchSpace = hardSearchSpace(dieRoll);
                return this.searchSpace;
            default:
                this.searchSpace = mediumSearchSpace(dieRoll);
                return this.searchSpace;
        }
    }
    
    /**AI moves more advanced when selected EASY difficulty
     *
     * @param dieRoll
     * @return the places where the AI can move to
     */
    public ArrayList<Tile> easySearchSpace(int dieRoll) {
        if (isInRoom()) {
            return GameRevised.BOARD.reachableFrom((Room)location, dieRoll);
        }
        else {
            return GameRevised.BOARD.reachableFrom((Tile)location, dieRoll);
        }
    }
    
    /**AI moves more advanced when selected MEDIUM difficulty
     *
     * @param dieRoll
     * @return the places where the AI can move to
     */
    public ArrayList<Tile> mediumSearchSpace(int dieRoll) {
        if (isInRoom()) {
            return GameRevised.BOARD.furthestReachableFrom((Room)location, dieRoll);
        }
        else {
            return GameRevised.BOARD.furthestReachableFrom((Tile)location, dieRoll);
        }
    }
    
    /**AI moves more advanced when selected HARD difficulty
     *
     * @param dieRoll
     * @return the places where the AI can move to
     */
    public ArrayList<Tile> hardSearchSpace(int dieRoll) {
        if (isInRoom()) {
            return GameRevised.BOARD.furthestReachableFrom((Room)location, dieRoll);
        }
        else {
            return GameRevised.BOARD.furthestReachableFrom((Tile)location, dieRoll);
        }
    }
}
