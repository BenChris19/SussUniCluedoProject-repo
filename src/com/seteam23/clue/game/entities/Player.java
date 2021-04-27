/*
 *      The Players
 *
 *      Generic Blank Player Class
 *      Needs to interact with any possible AI
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.board.Place;
import com.seteam23.clue.game.board.Tile;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import javafx.collections.ObservableList;

public class Player {

    private int order;
    private String name;
    private int currentPosY;
    private int currentPosX;
    private String imgPath;
    private boolean human;
    private ArrayList<Card> cards;
    private Place[][] place;
    private boolean endTurn;
    private ArrayList<Tile> searchSpace;
    private final boolean isHuman;
    private boolean isInRoom;
    private Checklist checklist;

    /**
     * 
     * @param name
     * @param order
     * @param playerImgPath
     * @param currentPosY
     * @param currentPosX
     * @param endTurn
     * @param isInRoom 
     */
    public Player(String name,int order,String playerImgPath,int currentPosY,int currentPosX,boolean endTurn,boolean isInRoom) {
        this.order = order;
        this.isHuman = true;
        this.endTurn = endTurn;
        this.searchSpace = new ArrayList<>();
        this.currentPosY = currentPosY;
        this.currentPosX = currentPosX;
        this.imgPath = playerImgPath;
        this.name = name;  
        this.cards = new ArrayList<>();
        this.isInRoom = isInRoom;
        
    }

    public void setSearchSpace(ArrayList<Tile> searchSpace) {
        this.searchSpace.clear();
        searchSpace.forEach((Tile t) -> {
            Player.this.searchSpace.add(t);
        });
    }

    public void setIsInRoom(boolean isInRoom) {
        this.isInRoom = isInRoom;
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

    public void initialiseChecklist(ArrayList<Card> gameCards){
        checklist = new Checklist();
        for (Card c : gameCards){
            checklist.add(c);
        }
    }
    
    public ObservableList<ChecklistEntry> getChecklistEntries(){
        return checklist.getEntries();
    }
    public void markCard(ChecklistEntry c){
        checklist.mark(c.getCard());
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
        return dice;
    }

    public boolean isInRoom() {
        return isInRoom;
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
    public String makeSuggestion(String person, String weapon){  //No Room as it has to be in the same room as the player is in
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
