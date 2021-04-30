/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team 23
 */
public class PlayerTest {
    Player instance1;
    Player instance2;
    public PlayerTest() {
    }
    @Before
    public void setUp(){
        instance1 = new Player("Miss Scarlett", 0, "Miss_Scarlett.jpg", 0, 0, false, false);
        instance2 = new Player("Miss Scarlett", 0, "Miss_Scarlett.jpg", 0, 0, false, false);
    }   

    /**
     * Test of initialiseChecklist method, of class Player.
     */
    @Test
    public void testInitialiseChecklist() {
        System.out.println("finishTurn");
        ArrayList<Card> gameCards = new ArrayList<>();
        gameCards.add(new Card("Hammer", "h.jpg", "weapon"));
        gameCards.add(new Card("Wrench", "w.jpg", "weapon"));
        instance1.initialiseChecklist(gameCards);
        List<ChecklistEntry> entries = instance1.getChecklistEntries();
        assertEquals(entries.get(0).getName(), "Hammer");
        assertEquals(entries.get(0).getChecked(), false);
        assertEquals(entries.get(0).getName(), "Wrench");
        assertEquals(entries.get(0).getChecked(), false);
        instance1.markCard(entries.get(0));
        assertEquals(entries.get(0).getChecked(), true);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewCards method, of class Player.
     */
    @Test
    public void testViewCards() {
        System.out.println("viewCards");
        ArrayList<Card> expResult = new ArrayList<>();
        expResult.add(new Card("Hammer", "h.jpg", "weapon"));
        expResult.add(new Card("Wrench", "w.jpg", "weapon"));
        instance1.addCard(new Card("Hammer", "h.jpg", "weapon"));
        instance1.addCard(new Card("Wrench", "w.jpg", "weapon"));
        ArrayList<Card> result = instance1.viewCards();
        assertArrayEquals(expResult.toArray(), result.toArray());
        // TODO review the generated test code and remove the default call to fail.
    }    
}
