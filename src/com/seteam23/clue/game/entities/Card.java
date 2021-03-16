/*
 *      The Cards
 *
 *      A Card can be a player, room, or weapon
 *
 */
package com.seteam23.clue.game.entities;

public class Card{
    private String name;
    private String imgPath;
    //private ImageView img;
    private String cardType;
    /**
     *
     * @param name
     * @param imgPath
     * @param cardType
     */
    public Card(String name, String imgPath, String cardType){
        this.name = name;
        this.imgPath = imgPath;
        this.cardType = cardType;
    }

    public String getName(){

        return name;
    }

    public String getImg(){
        return imgPath;
    }
}