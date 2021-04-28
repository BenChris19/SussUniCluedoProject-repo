/*
 *      The Cards
 *
 *      A Card can be a player, room, or weapon
 *
 */
package com.seteam23.clue.game.entities;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card{
    private String name;
    private String imgPath;
    private Image img;
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
        this.img = new Image(imgPath);
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
    
    public Image getImg() {
        return this.img;
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
