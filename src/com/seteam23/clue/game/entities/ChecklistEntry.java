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
 * @author Joseph
 * 
 * This class acts as an intermediate between the Checklist class and the checklist table
 * in the GUI. Each instance represents one entry of a Checklist. 
 */
public final class ChecklistEntry {
    private String name;
    private String cardType;
    private Card card;
    private Button markButton;
    private final Checklist checklist;

    /**
     *
     * @param checklist
     * @param c
     */
    public ChecklistEntry(Checklist checklist, Card c){
        this.card = c;
        this.checklist = checklist;
        this.markButton = new Button();
        //Sets colour of button to either red or green depending whether the 
        //card has been marked
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

    /**
     *
     * @return
     */
    public String getName() {
        return card.getName();
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
    public String getCardType() {
        return card.getCardType();
    }

    /**
     *
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     *
     * @return
     */
    public Boolean getChecked() {
        return checklist.getValue(card);
    }

    /**
     *
     */
    public void setChecked() {
        checklist.mark(card);
    }

    /**
     *
     * @return
     */
    public Card getCard() {
        return card;
    }

    /**
     *
     * @param card
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     *
     * @return
     */
    public Button getMarkButton() {
        return markButton;
    }

    /**
     *
     * @param markButton
     */
    public void setMarkButton(Button markButton) {
        this.markButton = markButton;
    }
}
