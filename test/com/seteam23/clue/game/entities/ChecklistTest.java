/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team 23
 */
public class ChecklistTest {
    private Checklist checklist1;
    private Card c;
    public ChecklistTest() {
    }
    
    @Before
    public void setUp() {
        c = new Card("Joe", "Joe.jpg", "Character");
        checklist1 = new Checklist();
    }

    /**
     * Test of add method, of class Checklist.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        checklist1.add(c);
        assertEquals(checklist1.getValue(c), false);
    }

    /**
     * Test of mark method, of class Checklist.
     */
    @Test
    public void testMark() {
        System.out.println("mark");
        checklist1.mark(c);
        assertEquals(checklist1.getValue(c), true);
    }
    
}
