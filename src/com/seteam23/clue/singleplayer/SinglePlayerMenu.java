/*
 *      Game
 *
 *      Main loops and game logic
 *      Most of code will be here (probably)
 *      User input detected here
 *      Constructs Players and Board
 */
package com.seteam23.clue.singleplayer;

import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.entities.Card;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleplayerMenu{
    Board board;
    List<Card> cards;
    
    public SingleplayerMenu() throws IOException{
        cards = new ArrayList<>();
        initialise();
    }

    /**
     * Loads data from the init.txt file in the directory in to the game object
@@ -32,7 +34,7 @@ public Game() throws IOException{
     */
    public void initialise() throws IOException{
        //Creates file reader
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/main/init.csv"))) {
            String line;
            //Reads through each line in the document
            while ((line = br.readLine()) != null) {
//public void initialise() throws IOException{
        } }catch (IOException e) {
            System.out.println("init.txt missing from directory");
        }
        
        //for (Card c : cards){
            //System.out.println(c.getName());
        //}
    
        }
    }
