/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import com.seteam23.clue.game.entities.Player;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joseph
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
    @Test
    public void testGetDoors() {
        System.out.println("getDoors");
        int y = 6;
        int x = 3;
        //Board instance = new Board();
        Door result = board1.getDoors(y, x);
        assertEquals(result.getRoom().getRoomName(), "Study");
    }

    /**
     * Test of getRooms method, of class Board.
     */
    @Test
    public void testGetRooms() {
        System.out.println("getRooms");
        ArrayList<Room> result = board1.getRooms();
        assertEquals(result.get(0).getRoomName(), "Study");
        assertEquals(result.get(1).getRoomName(), "Library");
        assertEquals(result.get(2).getRoomName(), "Billard Room");
        assertEquals(result.get(3).getRoomName(), "Conservatory");
        assertEquals(result.get(4).getRoomName(), "Hall");
        assertEquals(result.get(5).getRoomName(), "Ballroom");
        assertEquals(result.get(6).getRoomName(), "Lounge");
        assertEquals(result.get(7).getRoomName(), "Dining Room");
        assertEquals(result.get(8).getRoomName(), "Kitchen");
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

    /**
     * Test of getPlayers method, of class Board.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        ArrayList<Player> test = new ArrayList<>();
        Player player1 = new Player("Joe",0,"Joe.jpg",1,4,true,false);
        test.add(player1);
        board1.setPlayers(test);
        ArrayList<Player> result = board1.getPlayers();
        assertEquals(result.get(0), player1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
