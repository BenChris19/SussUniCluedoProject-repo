/*
 *      Game
 *
 *      Main loops and game logic
 *      Most of code will be here (probably)
 *      User input detected here
 *      Constructs Players and Board
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.entities.Board;
import com.seteam23.clue.game.entities.Card;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game{
    Board board;
    List<Card> cards;
    public Game() throws IOException{
        cards = new ArrayList<>();
        initialise();
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
                            cards.add(temp);
                            break;
                    }
                }
            }
        //Thrown if file not found
        } catch (IOException e) {
            System.out.println("init.txt missing from directory");
        }
    }
}