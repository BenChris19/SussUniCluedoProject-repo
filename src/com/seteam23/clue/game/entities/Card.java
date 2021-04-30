/*
 *      The Cards
 *
 *      A Card can be a player, room, or weapon
 *
 */
package com.seteam23.clue.game.entities;

import java.util.Objects;

/**
 * 
 * @author Team 23
 */
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

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * 
     * @param imgPath 
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * 
     * @return 
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * 
     * @param cardType 
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
