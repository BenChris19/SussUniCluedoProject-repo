package com.seteam23.clue.singleplayer;

import com.seteam23.clue.game.entities.NPC;
import com.seteam23.clue.game.entities.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Team 23
 */
public class SingleplayerMenu{
    private static Player player1;
    private static ArrayList<NPC> opponentPlayers;
    private static String dif;

    
    /**Initialises the Single Player Menu
     *
     * Depending on what the user chooses in the single player menu controller, that information
     * will be transfered to the Game class.
     * @throws IOException 
     */
    public SingleplayerMenu() throws IOException{
        player1 = new Player("Miss Scarlett",1,"/resources/cards/players/Miss Scarlett.jpg",16,0,true,false);//Miss Scarlett is chosen as default character
        opponentPlayers = new ArrayList<>();
        
    }

    /**Getter method to get the user's player
     *
     * @return player1 the player the user has chosen.
     */
    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        SingleplayerMenu.player1 = player1;
    }

    /**Getter method to get Difficulty of AI opponents 
     *
     * @return dif a String which is either "EASY","MEDIUM" and "HARD".
     */
    public static String getDif() {
        return dif;
    } 

    /**Sets the difficulty
     *
     * @param dif
     */
    public static void setDif(String dif) {
        SingleplayerMenu.dif = dif;
    }
    

    /**Gets the number of opponents the user will play against
     *
     * @return opponentPlayers ArrayList of NPC (AI players) the user will play against.
     */
    public static ArrayList<NPC> getOpponentPlayers() {
        return opponentPlayers;
    }
    
    /**Sets the opponents of the user
     * The code make sure that all opponents are different from each other and from the user.
     *
     * @param opponents
     */
    public void setOpponents(int opponents) {
                NPC[] candidates = new NPC[]{new NPC("Miss Scarlett",1,"/resources/cards/players/Miss Scarlett.jpg",16,0,true,false),
                new NPC("Col Mustard",2,"/resources/cards/players/Col Mustard.jpg",23,7,true,false),
                new NPC("Mrs White",3,"/resources/cards/players/Mrs White.jpg",14,24,true,false),
                new NPC("Rev Green",4,"/resources/cards/players/Rev Green.jpg",9,24,true,false),
                new NPC("Mrs Peacock",5,"/resources/cards/players/Mrs Peacock.jpg",0,18,true,false),
                new NPC("Prof Plum",6,"/resources/cards/players/Prof Plum.jpg",0,5,true,false)};
        
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
    


    
