package com.seteam23.clue.game.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author team23
 */
public class NPC extends Player{
    private String difficulty;

    public NPC(String name, int noOfPlayers, boolean turn,String difficulty,ImageView imgPath) {
        super(name, noOfPlayers, turn, imgPath);
        
        this.difficulty = difficulty;
    }
    public void addToCheckList(){
        if (this.difficulty.equals("EASY")){
            
            
        }
        else if (this.difficulty.equals("MEDIUM")){
            
        }
        else{
            
        }
        
        
    }

    
}
