/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.util.Collection;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Team 23
 */
public class Checklist {
    private final HashMap<Card, Boolean> MARKED;
    public Checklist(){
        MARKED = new HashMap<>();
    }
    
    public void add(Card c){
        MARKED.put(c, false);
    }
    public void mark(Card c){

        if (MARKED.get(c) == false){
            //System.out.println(getValue(c));
            MARKED.put(c, true);
            getEntries();
        }
        else{
            MARKED.put(c, false);
        }
    }
    public Boolean getValue(Card c){
        return MARKED.get(c);
    }
    public Collection<Boolean> values(){
        return MARKED.values();
    }
    public Collection<Card> keys(){
        return MARKED.keySet();
    }
    public ObservableList<ChecklistEntry> getEntries(){
        ObservableList<ChecklistEntry> data = FXCollections.observableArrayList();
        MARKED.entrySet().forEach((entry) -> {
            Card key = entry.getKey();
            Boolean value = entry.getValue();
            data.add(new ChecklistEntry(this, key));
        });
        return data;
    }
}
