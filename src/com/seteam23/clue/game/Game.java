package com.seteam23.clue.game;

import com.seteam23.clue.game.board.*;
import com.seteam23.clue.game.entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *The underlying clue game
 * 
 * @author Team 23
 */
public final class Game {
    
    private Random r = new Random();

    public final GameController CONTROLLER;
    public static final Board BOARD = new Board();
    
    private final Player[] PLAYER_ARRAY;
    public final ArrayList<Player> PLAYERS;
    private int num_players;
    
    public static boolean gameLost = false;
    
    public final ArrayList<Card> ALL_CARDS;
    public final ArrayList<Card> WEAPON_CARDS;
    public final ArrayList<Card> SUSPECT_CARDS;
    public final ArrayList<Card> ROOM_CARDS;
    
    public final Card[] KILL_CARDS;
    private final ArrayList<Card> cards;
    
    private static Player player;   // Current Player
    private static int turn = 0;   // Turn inc whenever new player
    private static int round = 1;  // One Round : All Players played

    
    
    /**
     * 
     * @param controller    Initialise GUI
     * @param humanPlayers  The number of human players (Only multi player)
     * @param numAI     The number of AI players  (Only single player)
     * @param difficulty    The AI difficulty
     * @param weapons   The weapon cards
     * @param suspects  The suspect cards
     * @param rooms     The room cards
     * @param all   All cards
     */
    public Game(GameController controller, ArrayList<Player> humanPlayers, int numAI, String difficulty, 
                       ArrayList<Card> weapons, ArrayList<Card> suspects, ArrayList<Card> rooms, ArrayList<Card> all) {
        // Set Vars and Finals
        player = humanPlayers.get(0);
        
        this.PLAYER_ARRAY = new Player[6];
        
        ArrayList<String> characters = new ArrayList<>(Arrays.asList("Miss Scarlett","Col Mustard","Mrs White","Rev Green","Mrs Peacock","Prof Plum"));
        
        humanPlayers.forEach((p) -> {
            int charIndex = characters.indexOf(p.NAME);
            characters.remove(charIndex);
            PLAYER_ARRAY[getOrder(p.NAME)] = p;
        });
        
        for (int i = 0; i < numAI; i++) {
            int charIndex = r.nextInt(characters.size());
            String name = characters.remove(charIndex);
            int order = getOrder(name);
            PLAYER_ARRAY[order] = newAI(name, difficulty);
        }
        
        ArrayList<Player> temp = new ArrayList<>(Arrays.asList(PLAYER_ARRAY));
        temp.removeAll(Collections.singleton(null));
        this.PLAYERS = temp;
        this.num_players = PLAYERS.size();
        
        this.WEAPON_CARDS = weapons;
        this.SUSPECT_CARDS = suspects;
        this.ROOM_CARDS = rooms;
        this.ALL_CARDS = all;
        this.cards = all;
        
        PLAYERS.forEach((p) -> {
            p.initialiseChecklist(getAllCards());
        });
        
        // Set up Kill Cards
        this.KILL_CARDS = new Card[3];
        KILL_CARDS[0] = selectRandomCard(SUSPECT_CARDS);
        KILL_CARDS[1] = selectRandomCard(WEAPON_CARDS);
        KILL_CARDS[2] = selectRandomCard(ROOM_CARDS);
        
        // Place Players on Board
        for (Player p : PLAYERS) {
            Game.player = p;
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
        Game.player = PLAYERS.get(0);
        
        // Shuffle and Handout Cards
        Collections.shuffle(cards, r);
        for (int i = 0; i < cards.size(); i++) {
            PLAYERS.get(i % num_players).addCard(cards.get(i));
        }
        this.CONTROLLER = controller;
        this.CONTROLLER.setGame(this); // Hand to Controller
    }
    
     /**
     * Creates an AI agent
     * @param name
     * @param difficulty
     * @return 
     */
    private AIPlayer newAI(String name, String difficulty) {
        return new AIPlayer(this, name, "/resources/cards/players/"+name+".jpg", difficulty);
    }
    
    /**
     * Get index between 0 to 5 (inclusive) based on which character name is given
     * @param name
     * @return 
     */
    public int getOrder(String name) {
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
     * @return Picks one random card from an ArrayList of cards
     */
    private Card selectRandomCard(ArrayList<Card> cardList) {
        Card selected = cardList.get(r.nextInt(cardList.size()));
        this.cards.remove(selected);
        return selected;
    }
    
    
    
    /**
     * Get the Current Player
     * 
     * @return Player or NPC
     */
    public static Player getCurrentPlayer() {
        return player;
    }
    
    /**
     * Creates 2 random numbers (dices) and sums them
     * @return the sum of the dices if the player can roll the dices, else return 0
     */
    public int rollDice(){
        if (player.roll()) {
            int die1 = r.nextInt(6)+1;
            int die2 = r.nextInt(6)+1;
            int rolls = die1 + die2;
            return rolls;
        }
        return 0;
    }
    
    /**Getter method to get the turn
     *
     * @return turn; the turn of the character
     */
    public static int getTurn() {
        return turn;
    }
    
    /**Getter method to get the current round of the game (every time the game returns back to the initial player)
     *
     * @return round; which round the game is on
     */
    public static int getRound() {
        return round;
    }
    
    /**
     * Sets the next player
     */
    public void nextTurn() {
        // Sets current player to next player
        turn++;
        round = (int) Math.ceil(Game.turn / num_players);
        player = PLAYERS.get(turn % num_players);
        player.newTurn();
    }
    
    /**Gets all the cards from the game 
     *
     * @return all the cards from the game 
     */
    public ArrayList<Card> getAllCards(){
        return this.ALL_CARDS;
    }

    /**Getter method to get the murder cards
     *
     * @return the 3 murder cards
     */
    public Card[] getKillCards() {
        return KILL_CARDS;
    }
    
    /**Getter method to get the number of players in the game
     *
     * @return the number of players playing
     */
    public int getNumberPlayers() {
        return this.num_players;
    }
    
}
