/*
 *      The Players
 *
 *      Generic Blank Player Class
 *      Needs to interact with any possible AI
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.board.Place;
import com.seteam23.clue.game.ui.CheckTile;
import com.seteam23.clue.singleplayer.SingleplayerMenu;
import com.seteam23.clue.game.board.Tile;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;
import javafx.scene.image.ImageView;

public class Player {
    private CheckTile[][] CheckBoard;
    private static int noOfPlayers;
    private int row = 21, column;
    private int diceTotal = 0;
    private boolean turn;
    private String name;
    private ImageView imgPath;
    private ArrayList<Card> cards;
    private Card player; //Card assigned to each player in Game initialisation, when each player chooses their character.
    private Place[][] place;
    private HashMap<Card, Boolean> checklist;

    /**
     * 
     * @param name
     * @param noOfPlayers
     * @param turn 
     */
    public Player(String name) {
        this.turn = turn;
        this.noOfPlayers = noOfPlayers;
        this.CheckBoard = new CheckTile[row][noOfPlayers];
        this.name = name;  
        cards = new ArrayList<>();
        switch (this.name) {
            case "Scarlett":
                this.place = new Tile[16][0];
                break;
            case "Plum":
                this.place = new Tile[0][5];
                break;
            case "Peacock":
                this.place = new Tile[0][18];
                break;
            case "Mustard":
                this.place = new Tile[23][7];
                break;
            case "Green":
                this.place = new Tile[15][23];
            case "White":
                this.place = new Tile[16][23];
            default:
                break;
        }
        
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return 
     */
    public static int getNoOfPlayers() {
        return noOfPlayers;
    }
    
    
    /**
     * 
     */
    public void finishTurn(){

        turn = false;
        //Game.nextPlayerTurn()
    }
    
    /**
     * 
     */
    public void startTurn(){

        turn = true;
        //rollDice();
        //
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Card> viewCards(){

        return cards;
    }
    
    /**
     * 
     * @param c 
     */
    public void addCard(Card c){
        cards.add(c);
    }
    
    /**
     * 
     * @return 
     */
    public int[] rollDice(){
        Random r = new Random();
        int die1 = r.nextInt(6)+1;
        int die2 = r.nextInt(6)+1;
        System.out.println(die1+","+die2);
        int[] dice = new int[die1+die2];//Kept as individual dice for graphics
        diceTotal = die1+die2;
        return dice;
    }
    
    /**
     * 
     * @param person
     * @param weapon
     * @param room
     * @return 
     */
    public String makeAccusation(Card person,Card weapon,Card room){
        String s = "Person: "+person+" Weapon: "+weapon+" Room: "+room;
        return s;
    }
    
    /**
     * 
     * @param person
     * @param weapon
     * @return 
     */
    public String makeSuggestion(Card person, Card weapon){  //No Room as it has to be in the same room as the player is in
        String s = "Person: "+person+" Weapon: "+weapon+ " Room: "+place;
        return s;

    }
    
    /**
     * 
     * @param x
     * @param y
     * @param diceTotal 
     */
    public void movePlayer(int x, int y, int diceTotal){
        

        /**
         * Can't really write this until board is created
         */
    }
}
