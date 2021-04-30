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
 * 
 * Adds functionality for a player to mark off cards they have observed in game.
 */
public class Checklist {
    private HashMap<Card, Boolean> marked;

    /**
     *
     */
    public Checklist(){
        marked = new HashMap<>();
    }
    
    /**
     *
     * @param c 
     * Adds a card to the hashmap. Initially sets it's value to false
     */
    public void add(Card c){
        marked.put(c, false);
    }

    /**
     *
     * @param c
     * Performs binary flip on the value of a card in the hashmap. Used to mark or 
     * unmark a card.
     */
    public void mark(Card c){
        if (marked.get(c) == false){
            //System.out.println(getValue(c));
            marked.put(c, true);
            getEntries();
        }
        else{
            marked.put(c, false);
        }
    }

    /**
     *
     * @param c
     * @return
     */
    public Boolean getValue(Card c){
        return marked.get(c);
    }

    /**
     * 
     * @return
     */
    public Collection<Boolean> values(){
        return marked.values();
    }

    /**
     *
     * @return
     */
    public Collection<Card> keys(){
        return marked.keySet();
    }

    /**
     * Returns an observable list of hashmap values placed in to ChecklistEntry 
     * objects to be displayed in the checklist table in the GUI.
     * @return
     */
    public ObservableList<ChecklistEntry> getEntries(){
        ObservableList<ChecklistEntry> data = FXCollections.observableArrayList();
        marked.entrySet().forEach((entry) -> {
            Card key = entry.getKey();
            Boolean value = entry.getValue();
            data.add(new ChecklistEntry(this, key));
        });
        return data;
    }
}
