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
    private final HashMap<Card, Boolean> marked;
    public Checklist(){
        marked = new HashMap<>();
    }
    public void add(Card c){
        marked.put(c, false);
    }
    public void mark(Card c){
        if (marked.get(c) == false){
            marked.put(c, true);
        }
        else{
            marked.put(c, false);
        }
    }
    public Boolean getValue(Card c){
        return marked.get(c);
    }
    public Collection<Boolean> values(){
        return marked.values();
    }
    public Collection<Card> keys(){
        return marked.keySet();
    }
    public ObservableList<ChecklistEntry> getEntries(){
        ObservableList<ChecklistEntry> data = FXCollections.observableArrayList();
        marked.entrySet().forEach((entry) -> {
            Card key = entry.getKey();
            data.add(new ChecklistEntry(this, key));
        });
        return data;
    }
}
