/*
 *      The Cards
 *
 *      A Card can be a player, room, or weapon
 *
 */
package com.seteam23.clue.game.entities;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Card{
    private String name;
    private String imgPath;
    //private ImageView img;
    private CardType cardType;
    
    public enum CardType{
        SUSPECT,
        WEAPON,
        DESTINATION
    }        
    
    /**
     *
     * @param name
     * @param imgPath
     * @param cardType
     */
    public void Card(String name, String imgPath, CardType cardType){
        this.name = name;
        this.imgPath = imgPath;
        this.cardType = cardType;
    }
}