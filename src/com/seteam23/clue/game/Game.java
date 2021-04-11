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
import java.util.List;

public class Game{
    Board board;
    ArrayList<Card> weapon_cards;
    ArrayList<Card> suspect_cards;
    ArrayList<Card> room_cards;
    GameController controller;
    public Game() throws IOException{
        weapon_cards = new ArrayList<>();
        room_cards = new ArrayList<>();
        suspect_cards = new ArrayList<>();
        initialise();
    }
    public Game(GameController controller){
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
        try (BufferedReader br = new BufferedReader(new FileReader("init.txt"))) {
            String line;
            //Reads through each line in the document
            while ((line = br.readLine()) != null) {
                //Filters out comments in the file starting with character #
                if (!(line.charAt(0) == '#')){
                    //Splits a line in to strings separated by commas
                    String[] info = line.split(",");
                    switch (info[0]){
                        case "CARD":
                            //Creates a card object with specified data
                            String tempName = info[2];
                            String tempPath = info[3];
                            String tempType = info[1];
                            Card temp = new Card(tempName, tempPath, tempType);
                            //Adds card to game
                            if (temp.getCardType().equals("WEAPON")) {
                                weapon_cards.add(temp);
                            }
                            else if (temp.getCardType().equals("SUSPECT")) {
                                suspect_cards.add(temp);
                            }
                            else{
                                room_cards.add(temp);
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

