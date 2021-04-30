package com.seteam23.clue.game.entities;

import static com.seteam23.clue.game.GameController.getMurderCards;
import com.seteam23.clue.game.board.Door;
import com.seteam23.clue.game.board.Tile;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getDif;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author team23
 */
public class NPC extends Player{
    private final String difficulty;
    private final String[] allCardsString;
    private final Card[] allCards;


    /**
     * 
     * @param name
     * @param playerImgPath
     * @param currentPosY
     * @param currentPosX
     * @param endTurn
     * @param isInRoom
     * @param turn 
     */
    
    
    public NPC(String name, int turn, String playerImgPath, int currentPosY, int currentPosX,boolean endTurn,boolean isInRoom) {
        super(name, turn, playerImgPath, currentPosY, currentPosX,endTurn,isInRoom);
        this.difficulty = getDif();
        
        allCardsString =new String[]{"/resources/cards/rooms/Ballroom.png","/resources/cards/rooms/Billard Room.png","/resources/cards/rooms/Conservatory.png",
        "/resources/cards/rooms/Dining Room.png","/resources/cards/rooms/Hall.png","/resources/cards/rooms/Kitchen.png","/resources/cards/rooms/Library.png","/resources/cards/rooms/Lounge.png",
            "/resources/cards/rooms/Study.png",
        
        "/resources/cards/weapons/Candlestick.JPG","/resources/cards/weapons/Knife.JPG","/resources/cards/weapons/Lead Pipe.JPG",
            "/resources/cards/weapons/Revolver.JPG","/resources/cards/weapons/Rope.JPG","/resources/cards/weapons/wrench.JPG",
        
        "/resources/cards/players/Miss Scarlett.jpg","/resources/cards/players/Col Mustard.jpg","/resources/cards/players/Rev Green.jpg",
            "/resources/cards/players/Prof Plum.jpg","/resources/cards/players/Mrs White.jpg","/resources/cards/players/Mrs Peacock.jpg"} ;
        
        allCards = new Card[allCardsString.length];
            
        for(int i = 0;i<allCards.length;i++){
            allCards[i] = new Card(allCardsString[i].split("/")[4],allCardsString[i],allCardsString[i].split("/")[3]);
        }
        
                       
    }
    public Tile AIMoves(ArrayList<Tile> searchSpace){
        for(Tile t:searchSpace){
            if(t instanceof Door){
                setIsInRoom(true);
                return t;
            }
        }
        int index = new Random().nextInt(searchSpace.size());
        Tile closest = searchSpace.get(index);

        return closest;
    }
    public Card[] AiMakesSugg(){
        Card[] otherPlayersCards = new Card[3];
        boolean[] found = new boolean[]{false,false,false};
        for (Card allCard : allCards) {
            if (!getCards().contains(allCard)) {
                if (!found[0] && allCard.getCardType().equals("rooms")) {
                    found[0] = true;
                    otherPlayersCards[0] = allCard;
                } else if (!found[1] && allCard.getCardType().equals("weapons")) {
                    found[1] = true;
                    otherPlayersCards[1] = allCard;
                } else if (!found[2] && allCard.getCardType().equals("players")) {
                    found[2] = true;
                    otherPlayersCards[2] = allCard;
                }
            }
            if(found[0]==true && found[1]==true && found[2]==true){
                break;
            }
        }
        return otherPlayersCards;
    }

    
    /**
     * 
     * @param obtained
     */
    public void addToCheckList(Card[] obtained){
        Random rand = new Random();
        OUTER:
        for (int i = 0; i<obtained.length; i++) {
            if (!getMurderCards().contains(obtained[i])) {
                switch (this.difficulty) {
                    case "EASY":
                        addCards(obtained[i]);
                        break OUTER;
                    case "MEDIUM":
                        addCards(obtained[i]);
                        if(rand.nextDouble()>0.75){
                            if (!getMurderCards().contains(obtained[i+1])){
                                addCards(obtained[i+1]);
                            } 
                        }
                        break OUTER;
                    default:
                        addCards(obtained[i]);
                        if(rand.nextDouble()>0.45){
                            if (!getMurderCards().contains(obtained[i+1])){
                                addCards(obtained[i+1]);
                            }
                        }
                        break OUTER;
                }
            }
        }
    }
}
