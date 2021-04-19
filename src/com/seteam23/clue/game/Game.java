/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.Player;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author InfernoKay
 */
public final class Game{
    Board board;
    ArrayList<Card> weapon_cards;
    ArrayList<Card> suspect_cards;
    ArrayList<Card> room_cards;
    GameController controller;
    Card[] killCards;
    ArrayList<Player> players; //Indiscriminant of human or AI
    Player currentPlayer;
    
    public Game(GameController controller) throws IOException{
        this.controller = controller;
        this.board = new Board();

    }

    //Returns separate dice rolls also for a potential visualisation of the dice in the GUI
    /**
     * 
     * @return 
     */
    public static int[] rollDice(){
        Random r = new Random();
        int die1 = r.nextInt(6)+1;
        int die2 = r.nextInt(6)+1;
        int[] rolls = new int[]{die1, die2, die1 + die2};
        return rolls;
    }
    
    public void endTurn() {
        
    }
    
    
    
    /**
     * 
     * @return 
     */
    public ArrayList<Card> getWeaponCards() {
        return weapon_cards;
    }

    /**
     * 
     * @param weapon_cards 
     */
    public void setWeapon_cards(ArrayList<Card> weapon_cards) {
        this.weapon_cards = weapon_cards;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Card> getSuspectCards() {
        return suspect_cards;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getSuspectNames(){
        ArrayList<String> a = new ArrayList<>();
        suspect_cards.forEach((c) -> {
            a.add(c.getName());
        });
        return a;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getWeaponNames(){
        ArrayList<String> a = new ArrayList<>();
        weapon_cards.forEach((c) -> {
            a.add(c.getName());
        });
        return a;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getRoomNames(){
        ArrayList<String> a = new ArrayList<>();
        room_cards.forEach((c) -> {
            a.add(c.getName());
        });
        return a;
    }
    
    /**
     * 
     * @param suspect_cards 
     */
    public void setSuspect_cards(ArrayList<Card> suspect_cards) {
        this.suspect_cards = suspect_cards;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Card> getRoomCards() {
        return room_cards;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * 
     * @param players 
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * 
     * @param room_cards 
     */
    public void setRoom_cards(ArrayList<Card> room_cards) {
        this.room_cards = room_cards;
    }

    /**
     * 
     * @return 
     */
    public GameController getController() {
        return controller;
    }

    /**
     * 
     * @param controller 
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }


    
}

/**
 *
 * @author InfernoKay
 */
//public class Game {
    
//}

