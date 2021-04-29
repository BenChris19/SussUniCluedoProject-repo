/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.hash;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Joseph
 */
public class Checklist {
    private HashMap<Card, Boolean> marked;
    public Checklist(){
        marked = new HashMap<>();
    }
    
    public void add(Card c){
        marked.put(c, false);
    }
    public void mark(Card c){
        if (marked.get(c) == false){
            //System.out.println(getValue(c));
            System.out.println(this);
            marked.put(c, true);
            getEntries();
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
            Boolean value = entry.getValue();
            System.out.println(value);
            data.add(new ChecklistEntry(this, key));
        });
        System.out.println("----");
        return data;
    }
}
