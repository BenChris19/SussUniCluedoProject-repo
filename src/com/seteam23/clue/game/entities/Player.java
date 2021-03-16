/*
 *      The Players
 *
 *      Generic Blank Player Class
 *      Needs to interact with any possible AI
 */
package com.seteam23.clue.game.entities;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

public class Player {
    private CheckTile[][] CheckBoard;
    private int noOfPlayers = 6;
    private int row = 21, column;
    private int diceTotal;
    private boolean turn;
    private String name;
    private String imgPath;
    private Card[] cards;
    private Card player; //Card assigned to each player in Game initialisation, when each player chooses their character.
    private Place place;

    public Player() {
        column = noOfPlayers;
        CheckBoard = new CheckTile[row][column];
        //name = player.getName();  //Once cards have been assigned otherwise nullpointerexcepton
        //imgPath = player.getImg();
        //place = starting coordinates could be in  Game.java or in the Card for specific player.
    }

    public void finishTurn(){

        turn = false;
    }

    public void startTurn(){

        turn = true;
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
