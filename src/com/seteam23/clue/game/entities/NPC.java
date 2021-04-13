package com.seteam23.clue.game.entities;

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
    public NPC(String name, String difficulty,ImageView imgPath) {
        super(name);
        
        this.difficulty = difficulty;
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
