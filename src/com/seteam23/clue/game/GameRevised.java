/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.*;
import com.seteam23.clue.game.entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Team 23
 */
public final class GameRevised {
    
    private Random r = new Random();

    public final GameControllerRevised CONTROLLER;
    public static final Board BOARD = new Board();
    
    private final PlayerRevised[] PLAYER_ARRAY;
    public final ArrayList<PlayerRevised> PLAYERS;
    public final int NUM_PLAYERS;
    
    public static boolean gameLost = false;
    
    private final ArrayList<Card> ALL_CARDS;
    private final ArrayList<Card> WEAPON_CARDS;
    private final ArrayList<Card> SUSPECT_CARDS;
    private final ArrayList<Card> ROOM_CARDS;
    private final ArrayList<Card> cards;
    private final Card[] KILL_CARDS;
    
    private static PlayerRevised player;   // Current Player
    private static int turn = 0;   // Turn inc whenever new player
    private static int round = 1;  // One Round : All Players played

    
    
    /**
     * 
     * @param controller
     * @param humanPlayers
     * @param numAI
     * @param difficulty
     * @param weapons
     * @param suspects
     * @param rooms
     * @param all
     */
    public GameRevised(GameControllerRevised controller, ArrayList<PlayerRevised> humanPlayers, int numAI, String difficulty, 
                       ArrayList<Card> weapons, ArrayList<Card> suspects, ArrayList<Card> rooms, ArrayList<Card> all) {
        // Set Vars and Finals
        this.player = humanPlayers.get(0);
        
        this.PLAYER_ARRAY = new PlayerRevised[6];
        
        ArrayList<String> characters = new ArrayList<>(Arrays.asList("Miss Scarlett","Col Mustard","Mrs White","Rev Green","Mrs Peacock","Prof Plum"));
        
        for (PlayerRevised p : humanPlayers) {
            int charIndex = getOrder(p.NAME);
            characters.get(charIndex);
            PLAYER_ARRAY[charIndex] = p;
        }
        
        for (int i = 0; i < numAI; i++) {
            int charIndex = r.nextInt(characters.size());
            String name = characters.get(charIndex);
            int order = getOrder(name);
            PLAYER_ARRAY[order] = newAI(name, difficulty);
        }
        
        ArrayList<PlayerRevised> temp = new ArrayList<>(Arrays.asList(PLAYER_ARRAY));
        temp.removeAll(Collections.singleton(null));
        this.PLAYERS = temp;
        this.NUM_PLAYERS = PLAYERS.size();
        
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
            GameRevised.player = p;
            Tile t;
            switch (p.NAME) {
                case "Miss Scarlett":
                    t = BOARD.getTile(16, 0);
                    p.moveTo(t);
                    t.addOccupier(p);
                    break;
                case "Prof Plum":
                    t = BOARD.getTile(0,5);
                    p.moveTo(t);
                    t.addOccupier(p);
                    break;
                case "Col Mustard":
                    t = BOARD.getTile(23, 7);
                    p.moveTo(t);
                    t.addOccupier(p);
                    break;
                case "Mrs White":
                    t = BOARD.getTile(14, 24);
                    p.moveTo(t);
                    t.addOccupier(p);
                    break;
                case "Rev Green":
                    t = BOARD.getTile(9, 24);
                    p.moveTo(t);
                    t.addOccupier(p);
                    break;
                default:
                    t = BOARD.getTile(0, 18);
                    p.moveTo(t);
                    t.addOccupier(p);
                    break;
            }
        }
        GameRevised.player = PLAYERS.get(0);
        
        // Shuffle and Handout Cards
        Collections.shuffle(cards, r);
        for (int i = 0; i < cards.size(); i++) {
            PLAYERS.get(i % NUM_PLAYERS).addCard(cards.get(i));
        }
        this.CONTROLLER = controller;
        this.CONTROLLER.setGame(this); // Hand to Controller
    }
    
    private AIPlayer newAI(String name, String difficulty) {
        return new AIPlayer(this, name, "/resources/cards/players/"+name+".jpg", difficulty);
    }
    
    /**
     * Get index between 0 to 5 (inclusive) based on which character name is given
     * @param name
     * @return 
     */
    private int getOrder(String name) {
        switch (name) {
            case "Miss Scarlett":
                return 0;
            case "Col Mustard":
                return 1;
            case "Mrs White":
                return 2;
            case "Rev Green":
                return 3;
            case "Mrs Peacock":
                return 4;
            default: //Prof Plum
                return 5;
        }
    }
    
    
    
    /**
     * Selects random card from cardList, removes from cards to hand out 
     * @param cardList ArrayList
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
    public static PlayerRevised getCurrentPlayer() {
        return player;
    }
    
    /**
     * Rolls 2d6 and returns combined result
     * @param person
     * @param weapon
     * @param room
     */
//    public int rollDice(){
//        if (this.player.roll()) {
//            int die1 = r.nextInt(6)+1;
//            int die2 = r.nextInt(6)+1;
//            int rolls = die1 + die2;
//            return rolls;
//        }
//        return 0;
//    }
    
    
    public void suggestion(String person, String weapon, Room room) {
        PlayerRevised nextPlayer;
        Card found = null;
        int i = 1;
        

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
    
    public static int getTurn() {
        return turn;
    }
    
    public static int getRound() {
        return round;
    }
    
    /**
     * Sets the next player
     */
    public void nextTurn() {
        // Sets current player to next player
        GameRevised.turn++;
        GameRevised.round = (int) Math.ceil(GameRevised.turn / NUM_PLAYERS);
        GameRevised.player = PLAYERS.get(turn % NUM_PLAYERS);

        // Reset rolls and suggestions if player is playing 
        if (GameRevised.player.isPlaying()) {
            GameRevised.player.newTurn();
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
    public ArrayList<Card> getAllCards(){
        return this.ALL_CARDS;
    }

    public Card[] getKILL_CARDS() {
        return KILL_CARDS;
    }
    
}
