/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import javafx.scene.control.Button;

/**
 *
 * @author Joseph
 */
public class ChecklistEntry {
    private String name;
    private String cardType;
    private Card card;
    private Button mark;
    private Checklist checklist;
    public ChecklistEntry(Checklist checklist, Card c){
        this.card = c;
        this.checklist = checklist;
        this.mark = new Button();
        this.mark.setStyle("-fx-background-color: #ff0000");
        this.mark.setOnAction((event) -> {
            this.setChecked();
            if (this.getChecked() == true){
                this.mark.setStyle("-fx-background-color: #00ff00");
            }
            else{
                this.mark.setStyle("-fx-background-color: #ff0000");
            }
        });
    }

    public String getName() {
        return card.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return card.getCardType();
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Boolean getChecked() {
        return checklist.getValue(card);
    }

    public void setChecked() {
        this.checklist.mark(this.card);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Button getMark() {
        return mark;
    }

    public void setMark(Button mark) {
        this.mark = mark;
    }
    
}
