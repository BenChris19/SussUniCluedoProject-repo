/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.entities.PlayerRevised;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team 23
 */
public class BoardTest extends Application{
    Board board1;
    public BoardTest() {
    }
    
    @Before
    public void setUp() {
        board1 = new Board();
    }
    

    /**
     * Test of getDoors method, of class Board.
     */

    /**
     * Test of getRooms method, of class Board.
     */
    @Test
    public void testGetRooms() {
        System.out.println("getRooms");
        ArrayList<Room> result = board1.getRooms();
        assertEquals(result.get(0).getName(), "Study");
        assertEquals(result.get(1).getName(), "Library");
        assertEquals(result.get(2).getName(), "Billard Room");
        assertEquals(result.get(3).getName(), "Conservatory");
        assertEquals(result.get(4).getName(), "Hall");
        assertEquals(result.get(5).getName(), "Ballroom");
        assertEquals(result.get(6).getName(), "Lounge");
        assertEquals(result.get(7).getName(), "Dining Room");
        assertEquals(result.get(8).getName(), "Kitchen");
    }

    /**
     * Test of reachableFrom method, of class Board.
     */
    @Test
    public void testReachableFrom() {
        System.out.println("reachableFrom");
        Tile start = board1.getTile(1, 4);
        int die_roll = 1;
        ArrayList<Tile> expResult = null;
        ArrayList<Tile> result = board1.reachableFrom(start, die_roll);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), board1.getTile(2, 4));
        assertEquals(result.get(0), board1.getTile(1, 5));
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
