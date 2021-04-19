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
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.function.Consumer;
import javafx.scene.image.ImageView;

public class Player {
    private CheckTile[][] CheckBoard;
    private static int noOfPlayers;
    private int row = 21, column;
    private int diceTotal = 0;
    private int order;
    private String name;
    private int currentPosY;
    private int currentPosX;
    private String imgPath;
    private boolean human;
    private List<Card> cards;
    private Card player; //Card assigned to each player in Game initialisation, when each player chooses their character.
    private Place[][] place;
    private HashMap<Card, Boolean> checklist;
    private boolean endTurn;
    private ArrayList<Tile> searchSpace;
    private final boolean isHuman;

    /**
     * 
     * @param name
     * @param noOfPlayers
     * @param turn 
     */
    public Player(String name,int order,String playerImgPath,int currentPosY,int currentPosX,boolean endTurn) {
        this.order = order;
        this.isHuman = true;
        this.endTurn = endTurn;
        this.searchSpace = new ArrayList<>();
        this.currentPosY = currentPosY;
        this.currentPosX = currentPosX;
        this.imgPath = playerImgPath;
        this.noOfPlayers = noOfPlayers;
        this.CheckBoard = new CheckTile[row][noOfPlayers];
        this.name = name;  
        this.cards = new ArrayList<>();
        
    }

    public void setSearchSpace(ArrayList<Tile> searchSpace) {
        searchSpace.forEach((Tile t) -> {
            Player.this.searchSpace.add(t);
        });
    }

    public ArrayList<Tile> getSearchSpace() {
        return searchSpace;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }

    
    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    
    public void setCurrentPosYX(int currentPosY, int currentPosX) {
        this.currentPosY = currentPosY;
        this.currentPosX = currentPosX;
    }

    public int getCurrentPosY() {
        return currentPosY;
    }

    public int getCurrentPosX() {
        return currentPosX;
    }



    public String getImgPath() {
        return imgPath;
    }

    public int getOrder() {
        return order;
    }

    
    
    public void setTurn(int turn) {
        this.order = turn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }
    

    public List<Card> getCards() {
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
     * @return 
     */
    public List<Card> viewCards(){

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
