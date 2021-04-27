/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.entities.Player;
import java.util.ArrayList;
import javafx.scene.control.Button;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joseph
 */
public class BoardTest {
    Board board1;
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        board1 = new Board();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDoors method, of class Board.
     */
    @Test
    public void testGetDoors() {
        System.out.println("getDoors");
        int y = 0;
        int x = 0;
        Board instance = new Board();
        Door expResult = null;
        Door result = instance.getDoors(y, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGrid method, of class Board.
     */
    @Test
    public void testCreateGrid() {
        System.out.println("createGrid");
        Board instance = new Board();
        instance.createGrid();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRooms method, of class Board.
     */
    @Test
    public void testGetRooms() {
        System.out.println("getRooms");
        Board instance = new Board();
        ArrayList<Room> expResult = null;
        ArrayList<Room> result = instance.getRooms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reachableFrom method, of class Board.
     */
    @Test
    public void testReachableFrom() {
        System.out.println("reachableFrom");
        Tile start = null;
        int die_roll = 0;
        Board instance = new Board();
        ArrayList<Tile> expResult = null;
        ArrayList<Tile> result = instance.reachableFrom(start, die_roll);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReachableButtons method, of class Board.
     */
    @Test
    public void testGetReachableButtons() {
        System.out.println("getReachableButtons");
        ArrayList<Tile> convertTile = null;
        Board instance = new Board();
        ArrayList<Button> expResult = null;
        ArrayList<Button> result = instance.getReachableButtons(convertTile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of furthestReachableFrom method, of class Board.
     */
    @Test
    public void testFurthestReachableFrom() {
        System.out.println("furthestReachableFrom");
        Tile start = null;
        int die_roll = 0;
        Board instance = new Board();
        ArrayList<Tile> expResult = null;
        ArrayList<Tile> result = instance.furthestReachableFrom(start, die_roll);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of highlightTiles method, of class Board.
     */
    @Test
    public void testHighlightTiles() {
        System.out.println("highlightTiles");
        ArrayList<Tile> ts = null;
        Board instance = new Board();
        instance.highlightTiles(ts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unlightTiles method, of class Board.
     */
    @Test
    public void testUnlightTiles() {
        System.out.println("unlightTiles");
        ArrayList<Tile> ts = null;
        Board instance = new Board();
        instance.unlightTiles(ts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unlightAllTiles method, of class Board.
     */
    @Test
    public void testUnlightAllTiles() {
        System.out.println("unlightAllTiles");
        Board instance = new Board();
        instance.unlightAllTiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of highlightAllTiles method, of class Board.
     */
    @Test
    public void testHighlightAllTiles() {
        System.out.println("highlightAllTiles");
        Board instance = new Board();
        instance.highlightAllTiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showAvailableMoves method, of class Board.
     */
    @Test
    public void testShowAvailableMoves() {
        System.out.println("showAvailableMoves");
        Tile start = null;
        int dice_roll = 0;
        Board instance = new Board();
        ArrayList<Tile> expResult = null;
        ArrayList<Tile> result = instance.showAvailableMoves(start, dice_roll);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTile method, of class Board.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        int x = 0;
        int y = 0;
        Board instance = new Board();
        Tile expResult = null;
        Tile result = instance.getTile(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startTile method, of class Board.
     */
    @Test
    public void testStartTile() {
        System.out.println("startTile");
        Tile tile = null;
        Board instance = new Board();
        instance.startTile(tile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayers method, of class Board.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        Board instance = new Board();
        ArrayList<Player> expResult = null;
        ArrayList<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayers method, of class Board.
     */
    @Test
    public void testSetPlayers() {
        System.out.println("setPlayers");
        ArrayList<Player> players = null;
        Board instance = new Board();
        instance.setPlayers(players);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartPos method, of class Board.
     */
    @Test
    public void testGetStartPos() {
        System.out.println("getStartPos");
        Player player = null;
        Board instance = new Board();
        Tile expResult = null;
        Tile result = instance.getStartPos(player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartPos method, of class Board.
     */
    @Test
    public void testSetStartPos() {
        System.out.println("setStartPos");
        int y = 0;
        int x = 0;
        Board instance = new Board();
        Tile expResult = null;
        Tile result = instance.setStartPos(y, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
