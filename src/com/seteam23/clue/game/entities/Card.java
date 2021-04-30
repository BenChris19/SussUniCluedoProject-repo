package com.seteam23.clue.game.entities;

import javafx.scene.image.Image;

public class Card{
    private final String NAME;
    private final String IMGPATH;
    private final Image IMG;
    private final String CARDTYPE;
    
    /**
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

    /**
     * 
     * @return 
     */
    public String getName() {
        return NAME;
    }


    /**
     * 
     * @return 
     */
    public String getImgPath() {
        return IMGPATH;
    }


    
    public Image getImg() {
        return this.IMG;
    }

    /**
     * 
     * @return 
     */
    public String getCardType() {
        return CARDTYPE;
    }





}
