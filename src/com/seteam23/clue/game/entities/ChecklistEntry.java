/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

/**
 *
 * @author Joseph
 */
public class ChecklistEntry {
    private String name;
    private String cardType;
    private Boolean checked;
    private Card card;
    public ChecklistEntry(Card c, Boolean checked){
        this.card = c;
        this.name = c.getName();
        this.cardType = c.getCardType();
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
    
}
