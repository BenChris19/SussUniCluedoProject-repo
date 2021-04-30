package com.seteam23.clue.game.entities;

import javafx.scene.image.Image;

public class Card{
    private final String NAME;
    private final String IMGPATH;
    private final Image IMG;
    private final String CARDTYPE;
    
    /**Creates the Card class, which contains every weapon, character and room
     *
     * @param name
     * @param imgPath
     * @param cardType
     */
    public Card(String name, String imgPath, String cardType){
        this.NAME = name;
        this.IMGPATH = imgPath;
        this.CARDTYPE = cardType;
        this.IMG = new Image(imgPath);
    }

    /**Getter method to get the name of the card
     * 
     * @return the name of the card
     */
    public String getName() {
        return NAME;
    }


    /**Getter method to get the Image path of the card
     * 
     * @return the card image path
     */
    public String getImgPath() {
        return IMGPATH;
    }

    /**Getter method to get the card image
     *
     * @return the card image
     */
    public Image getImg() {
        return this.IMG;
    }

    /**Getter method to get the type of card
     * 
     * @return the type of card
     */
    public String getCardType() {
        return CARDTYPE;
    }
    




}
