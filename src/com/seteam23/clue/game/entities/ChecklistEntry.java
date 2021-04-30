/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Team 23
 */
public final class ChecklistEntry {
    private String name;
    private String cardType;
    private Card card;
    private Button markButton;
    private final Checklist checklist;
    public ChecklistEntry(Checklist checklist, Card c){
        this.card = c;
        this.checklist = checklist;
        this.markButton = new Button();
        if (!getChecked()){
            this.markButton.setStyle("-fx-background-color: #ff0000");
        }
        else{
            this.markButton.setStyle("-fx-background-color: #00ff00");
        }
        this.markButton.setOnAction((ActionEvent event) -> {
            ChecklistEntry.this.setChecked();
            if (ChecklistEntry.this.getChecked() == true) {
                ChecklistEntry.this.markButton.setStyle("-fx-background-color: #00ff00");
            } else {
                ChecklistEntry.this.markButton.setStyle("-fx-background-color: #ff0000");
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
        checklist.mark(card);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Button getMarkButton() {
        return markButton;
    }

    public void setMarkButton(Button markButton) {
        this.markButton = markButton;
    }
}
