/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.entities.Card;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Game{
    Board board;
    ArrayList<Card> weapon_cards;
    ArrayList<Card> suspect_cards;
    ArrayList<Card> room_cards;
    GameController controller;
    public Game(GameController controller) throws IOException{
        weapon_cards = new ArrayList<>();
        room_cards = new ArrayList<>();
        suspect_cards = new ArrayList<>();
        initialise();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Card> getWeaponCards() {
        return weapon_cards;
    }

    public void setWeapon_cards(ArrayList<Card> weapon_cards) {
        this.weapon_cards = weapon_cards;
    }

    public ArrayList<Card> getSuspectCards() {
        return suspect_cards;
    }
    
    public ArrayList<String> getSuspectNames(){
        ArrayList<String> a = new ArrayList<>();
        suspect_cards.forEach((c) -> {
            a.add(c.getName());
        });
        return a;
    }
    
    public ArrayList<String> getWeaponNames(){
        ArrayList<String> a = new ArrayList<>();
        weapon_cards.forEach((c) -> {
            a.add(c.getName());
        });
        return a;
    }
    
    public ArrayList<String> getRoomNames(){
        ArrayList<String> a = new ArrayList<>();
        room_cards.forEach((c) -> {
            a.add(c.getName());
        });
        return a;
    }
    
    public void setSuspect_cards(ArrayList<Card> suspect_cards) {
        this.suspect_cards = suspect_cards;
    }

    public ArrayList<Card> getRoomCards() {
        return room_cards;
    }

    public void setRoom_cards(ArrayList<Card> room_cards) {
        this.room_cards = room_cards;
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
    /**
     * Loads data from the init.txt file in the directory in to the game object
     * 
     * @throws IOException
     * 
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
                                    System.out.println("Recognised Weapon");
                                    weapon_cards.add(temp);
                                    break;
                                case "SUSPECT":
                                    System.out.println("Recognised Suspect");
                                    suspect_cards.add(temp);
                                    break;
                                case "ROOM":
                                    System.out.println("Recognised Room");
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

