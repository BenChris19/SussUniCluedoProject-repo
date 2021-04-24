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
    public ChecklistEntry(String name, String cardType, Boolean checked){
        this.name = name;
        this.cardType = cardType;
        this.checked = checked;
    }
}
