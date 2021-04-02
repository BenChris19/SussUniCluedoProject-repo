/*
 *      The Players
 *
 *      Generic Blank Player Class
 *      Needs to interact with any possible AI
 */
package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.ui.CheckTile;
import com.seteam23.clue.game.Game;
import com.seteam23.clue.game.entities.Tile;
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
    private int noOfPlayers;
    private int row = 21, column;
    private int diceTotal = 0;
    private boolean turn;
    private String name;
    private ImageView imgPath;
    private Card[] cards;
    private Card player; //Card assigned to each player in Game initialisation, when each player chooses their character.
    private Place[][] place;

    public Player(String name, int noOfPlayers,boolean turn,ImageView imgPath) {
        this.turn = turn;
        this.imgPath = imgPath;
        this.noOfPlayers = noOfPlayers;
        this.CheckBoard = new CheckTile[row][noOfPlayers];
        this.name = name;  
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

    public void finishTurn(){

        turn = false;
        //Game.nextPlayerTurn()
    }

    public void startTurn(){

        turn = true;
        //rollDice();
        //

    }

    public Card[] viewCards(){

        return cards;
    }

    public int[] rollDice(){
        Random r = new Random();
        int die1 = r.nextInt(6)+1;
        int die2 = r.nextInt(6)+1;
        System.out.println(die1+","+die2);
        int[] dice = new int[die1+die2];//Kept as individual dice for graphics
        diceTotal = die1+die2;
        return dice;
    }

    public String makeAccusation(Card person,Card weapon,Card room){
        String s = "Person: "+person+" Weapon: "+weapon+" Room: "+room;
        return s;
    }

    public String makeSuggestion(Card person, Card weapon){  //No Room as it has to be in the same room as the player is in
        String s = "Person: "+person+" Weapon: "+weapon+ " Room: "+place;
        return s;

    }

    public void movePlayer(int x, int y, int diceTotal){
        

        /**
         * Can't really write this until board is created
         */
    }
}
