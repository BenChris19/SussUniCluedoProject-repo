/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.*;
import com.seteam23.clue.game.entities.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Team 23
 */
public final class GameRevised {
    
    private Random r = new Random();
    
    public static final GameControllerRevised CONTROLLER = new GameControllerRevised();
    public static final Board BOARD = new Board();
    
    public final ArrayList<PlayerRevised> PLAYERS;
    public final int NUM_PLAYERS;
    
    public static boolean gameWon = false;
    public static boolean gameLost = false;
    
    private final ArrayList<Card> ALL_CARDS;
    private final ArrayList<Card> WEAPON_CARDS;
    private final ArrayList<Card> SUSPECT_CARDS;
    private final ArrayList<Card> ROOM_CARDS;
    private final ArrayList<Card> cards;
    private final Card[] KILL_CARDS;
    
    private PlayerRevised player;   // Current Player
    private int turn = 1;   // Turn inc whenever new player
    private int round = 1;  // One Round : All Players played

    
    
    /**
     * 
     * @param players 
     */
    public GameRevised(ArrayList<PlayerRevised> players,
                       ArrayList<Card> weapons, ArrayList<Card> suspects, ArrayList<Card> rooms, ArrayList<Card> all) {
        // Set Vars and Finals
        this.PLAYERS = players;
        this.NUM_PLAYERS = players.size();
        this.player = players.get(0);
        
        this.CONTROLLER.setGame(this); // Hand to Controller
        
        this.WEAPON_CARDS = weapons;
        this.SUSPECT_CARDS = suspects;
        this.ROOM_CARDS = rooms;
        this.ALL_CARDS = all;
        this.cards = all;
        
        // Set up Kill Cards
        this.KILL_CARDS = new Card[3];
        KILL_CARDS[0] = selectRandomCard(SUSPECT_CARDS);
        KILL_CARDS[1] = selectRandomCard(ROOM_CARDS);
        KILL_CARDS[2] = selectRandomCard(WEAPON_CARDS);
        
        // Place Players on Board
        for (PlayerRevised p : PLAYERS) {
            switch (p.NAME) {
                case "Miss Scarlett":
                    p.moveTo(BOARD.getTile(16, 0));
                    break;
                case "Prof Plum":
                    p.moveTo(BOARD.getTile(0,5));
                    break;
                case "Col Mustard":
                    p.moveTo(BOARD.getTile(23, 7));
                    break;
                case "Mrs White":
                    p.moveTo(BOARD.getTile(14, 24));
                    break;
                case "Rev Green":
                    p.moveTo(BOARD.getTile(9, 24));
                    break;
                default:
                    p.moveTo(BOARD.getTile(0, 18));
                    break;
            }
        }
        
        // Shuffle and Handout Cards
        Collections.shuffle(cards, r);
        for (int i = 0; i < cards.size(); i++) {
            PLAYERS.get(i % NUM_PLAYERS).addCard(cards.get(i));
        }
    }
    
    /**
     * Selects random card from cardList, removes from cards to hand out 
     * @param cardList
     * @return 
     */
    private Card selectRandomCard(ArrayList<Card> cardList) {
        Card selected = cardList.get(r.nextInt(cardList.size()));
        this.cards.remove(selected);
        return selected;
    }
    
    
    
    /**
     * Get the Current Player
     * @return Player or NPC
     */
    public PlayerRevised getCurrentPlayer() {
        return player;
    }
    
    /**
     * Rolls 2d6 and returns combined result
     * @return Random int between 2 and 12 (inclusive)
     */
    public int rollDice(){
        if (this.player.roll()) {
            int die1 = r.nextInt(6)+1;
            int die2 = r.nextInt(6)+1;
            int rolls = die1 + die2;
            return rolls;
        }
        return 0;
    }
    
    
    public void suggestion(String person, String weapon, Room room) {
        PlayerRevised nextPlayer;
        Card found = null;
        int i = 1;
        
        if (this.player.suggest()) {
            while (found != null && i < NUM_PLAYERS) {
                nextPlayer = PLAYERS.get((turn+i) % NUM_PLAYERS);
                nextPlayer.enterRoom(room);
                
                for (Card c : nextPlayer.getCards()) {
                    if (c.getName().equals(person+".jpg") || c.getName().equals(weapon+".JPG") || c.getName().equals(room.getName()+".png")) {
                        found = c;
                        break;
                    }
                }
            }
        }
    }
    
    public int getTurn() {
        return this.turn;
    }
    
    public int getRound() {
        return this.getRound();
    }
    
    /**
     * Sets the next player
     */
    public void nextTurn() {
        // Sets current player to next player
        this.turn++;
        this.round = (int) Math.ceil(this.turn / NUM_PLAYERS);
        this.player = PLAYERS.get(turn % NUM_PLAYERS);

        // Reset rolls and suggestions if player is playing 
        if (this.player.isPlaying()) {
            this.player.newTurn();
        }
        // Check if any Human Players left
        else {
            boolean peoplePlaying = false;
            
            for (PlayerRevised p : PLAYERS) {
                // If Human, skip current Player because they're out
                if (p.getClass().equals(Player.class) && p.isPlaying()) {
                    nextTurn();
                    peoplePlaying = true;
                    break;
                }
            }
            
            if (!peoplePlaying) {
                gameLost = true;
            }
        }
    }
}
