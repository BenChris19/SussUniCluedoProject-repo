/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author InfernoKay
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of finishTurn method, of class Player.
     */
    @Test
    public void testFinishTurn() {
        System.out.println("finishTurn");
        Player instance = new Player();
        instance.finishTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startTurn method, of class Player.
     */
    @Test
    public void testStartTurn() {
        System.out.println("startTurn");
        Player instance = new Player();
        instance.startTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewCards method, of class Player.
     */
    @Test
    public void testViewCards() {
        System.out.println("viewCards");
        Player instance = new Player();
        Card[] expResult = null;
        Card[] result = instance.viewCards();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollDice method, of class Player.
     */
    @Test
    public void testRollDice() {
        System.out.println("rollDice");
        Player instance = new Player();
        int[] expResult = null;
        int[] result = instance.rollDice();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeAccusation method, of class Player.
     */
    @Test
    public void testMakeAccusation() {
        System.out.println("makeAccusation");
        Card person = null;
        Card weapon = null;
        Card room = null;
        Player instance = new Player();
        String expResult = "";
        String result = instance.makeAccusation(person, weapon, room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeSuggestion method, of class Player.
     */
    @Test
    public void testMakeSuggestion() {
        System.out.println("makeSuggestion");
        Card person = null;
        Card weapon = null;
        Player instance = new Player();
        String expResult = "";
        String result = instance.makeSuggestion(person, weapon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of movePlayer method, of class Player.
     */
    @Test
    public void testMovePlayer() {
        System.out.println("movePlayer");
        int x = 0;
        int y = 0;
        int diceTotal = 0;
        Player instance = new Player();
        instance.movePlayer(x, y, diceTotal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
