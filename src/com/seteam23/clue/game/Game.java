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
    
    /**
     * 
     * @param controller
     * @throws IOException 
     */
    public Game(GameController controller, ArrayList<Player> players) throws IOException{
        this.controller = controller;
        board = controller.board;
        weapon_cards = new ArrayList<>();
        room_cards = new ArrayList<>();
        suspect_cards = new ArrayList<>();
        //Needs changing so GUI sets players
        this.players = players;
        initialise();
        startGame();
    }
    
    /**
     * Loads data from the init.txt file in the directory in to the game object
     * 
     * @throws IOException
     */
    public void initialise() throws IOException{
        //Creates file reader
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/main/init.csv"))) {
            String line;
            //Reads through each line in the document
            while ((line = br.readLine()) != null) {
                //Filters out comments in the file starting with character #
                System.out.println(line);
                if (!(line.charAt(0) == '#')){
                    //Splits a line in to strings separated by commas
                    String[] info = line.split(",");
                    switch (info[0]){
                        case "CARD":
                            System.out.println("Recognised Card");
                            //Creates a card object with specified data
                            String tempName = info[2];
                            String tempPath = info[3];
                            String tempType = info[1];
                            Card temp = new Card(tempName, tempPath, tempType);
                            //Adds card to game
                            switch (temp.getCardType()) {
                                case "WEAPON":
                                    //System.out.println("Recognised Weapon");
                                    weapon_cards.add(temp);
                                    break;
                                case "SUSPECT":
                                    //System.out.println("Recognised Suspect");
                                    suspect_cards.add(temp);
                                    break;
                                case "ROOM":
                                    //System.out.println("Recognised Room");
                                    room_cards.add(temp);
                                    break;
                            }
                        break;
                    }
                }
            }
        //Thrown if file not found
        } catch (IOException e) {
            System.out.println("init.txt missing from directory");
        }
    }
    
    //Separates Killer cards from deck and distributes remaining cards
    /**
     * 
     */
    public void startGame(){
        //Pick killer cards at random
        Random r = new Random();
        int suspectInd = r.nextInt(suspect_cards.size());
        int weaponInd = r.nextInt(weapon_cards.size());
        int roomInd = r.nextInt(room_cards.size());
        killCards = new Card[]{suspect_cards.get(suspectInd), weapon_cards.get(weaponInd), room_cards.get(roomInd)};
        //Combine all cards and distribute between players
        //Mix all cards together
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(suspect_cards);
        cards.addAll(weapon_cards);
        cards.addAll(room_cards);
        for (Card c : killCards){
            cards.remove(c);
        }
        Collections.shuffle(cards);
        int i = 0;
        while(!cards.isEmpty() && !players.isEmpty()){
            players.get(i%players.size()).addCard(cards.remove(0));
            i++;
        }
        
        //currentPlayer = players.get(0);
    }
    //Returns separate dice rolls also for a potential visualisation of the dice in the GUI
    /**
     * 
     * @return 
     */
    public int[] rollDice(){
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
    public Board getBoard() {
        return board;
    }

    /**
     * 
     * @param board 
     */
    public void setBoard(Board board) {
        this.board = board;
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

    /**
     * 
     * @param img_path 
     */
    public void setCharacterImage(String img_path) {
        controller.changeChar(img_path);
    }
    
}

/**
 *
 * @author InfernoKay
 */
//public class Game {
    
//}

