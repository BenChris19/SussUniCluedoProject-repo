package com.seteam23.clue.game.entities;

import com.seteam23.clue.game.Game;
import static com.seteam23.clue.game.GameController.getBoard;
import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.board.Door;
import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.scene.image.ImageView;

/**
 *
 * @author team23
 */
public class NPC extends Player{
    private String difficulty;


    /**
     * 
     * @param name
     * @param noOfPlayers
     * @param turn
     * @param difficulty
     * @param imgPath 
     */
    
    
    public NPC(String name, int turn, String playerImgPath, int currentPosY, int currentPosX,boolean endTurn,boolean isInRoom) {
        super(name, turn, playerImgPath, currentPosY, currentPosX,endTurn,isInRoom);

    }








    
    
        
    public Tile AIMoves(ArrayList<Tile> searchSpace){
        for(Tile t:searchSpace){
            if(t instanceof Door){
                return t;
            }
        }
        int index = new Random().nextInt(searchSpace.size());
        Tile closest = searchSpace.get(index);

        return closest;
    }

    
    /**
     * 
     */
    public void addToCheckList(){
        if (this.difficulty.equals("EASY")){
            
            
        }
        else if (this.difficulty.equals("MEDIUM")){
            
        }
        else{
            
        }
        
        
    }

    
}
