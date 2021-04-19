/*
 *      Game
 *
 *      Main loops and game logic
 *      Most of code will be here (probably)
 *      User input detected here
 *      Constructs Players and Board
 */
package com.seteam23.clue.singleplayer;

import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.NPC;
import com.seteam23.clue.game.entities.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SingleplayerMenu{
    private static Player player1;
    private static ArrayList<NPC> opponentPlayers;
    private static ArrayList<Card> murderCards;

    
    /**
     * 
     * @param opponents
     * @throws IOException 
     */
    public SingleplayerMenu() throws IOException{
        player1 = new Player("Miss Scarlett",1,"/resources/cards/players/Miss Scarlett.jpg",16,0,true);
        opponentPlayers = new ArrayList<>();
        murderCards = new ArrayList<>();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        SingleplayerMenu.player1 = player1;
    }

    public static ArrayList<NPC> getOpponentPlayers() {
        return opponentPlayers;
    }
    public static void addMurderCards(Card murderCard){
        murderCards.add(murderCard);
    }



    public void setOpponents(int opponents) {
                NPC[] candidates = new NPC[]{new NPC("Miss Scarlett",1,"/resources/cards/players/Miss Scarlett.jpg",16,0,true),
                new NPC("Col Mustard",2,"/resources/cards/players/Col Mustard.jpg",23,7,true),
                new NPC("Mrs White",3,"/resources/cards/players/Mrs White.jpg",14,24,true),
                new NPC("Rev Green",4,"/resources/cards/players/Rev Green.jpg",9,24,true),
                new NPC("Mrs Peacock",5,"/resources/cards/players/Mrs Peacock.jpg",0,18,true),
                new NPC("Prof Plum",6,"/resources/cards/players/Prof Plum.jpg",0,5,true)};
        
        for(int i=0;i<opponents;i++){
            int idx = new Random().nextInt(candidates.length);
            if(candidates[idx].getName().equals(getPlayer1().getName())){
                i-=1;
            }
            else if(opponentPlayers.contains(candidates[idx])){
                i-=1;
            }
            else{
                opponentPlayers.add(candidates[idx]);
            }
        }
    }
}
    


    
