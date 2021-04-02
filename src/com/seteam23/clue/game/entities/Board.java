
/*
 *      The Board / Map

/*
 *      The Board / Map
 *
 *      Constructs Rooms and Tiles
*      Players exist within the Board
 */
package com.seteam23.clue.game.entities;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Board{  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Joseph
 */
public final class Board{  
    // Reference to the Current used Board Controller
    private BoardController controller;

@@ -258,6 +260,37 @@ private void addTile(Tile tile, int x, int y) {
        places[y][x] = tile;
        tiles[y][x] = tile;
    }
    //Performs a breadth first search to find all places on the board that can be reached
    //from a particular Place for a given number of steps
    private ArrayList reachableTiles(Tile start, int diceRoll){
        ArrayList<LinkedList<Tile>> tileQueueArray = new ArrayList<LinkedList<Tile>>();
        HashMap<Tile, Boolean> visited = new HashMap();
        int i = 0;
        tileQueueArray.add(i, new LinkedList<Tile>());
        LinkedList<Tile> list = tileQueueArray.get(i);
        list.add(start);
        visited.put(start, true);
        while(!list.isEmpty() && i < diceRoll){
            tileQueueArray.add(i+1, new LinkedList<Tile>());
            for(Tile t : list){
                for (Tile u : t.getAdjacent().values()){
                    if(!visited.get(u)){
                        tileQueueArray.get(i+1).add(u);
                        visited.put(u, true);
                    }
                }
            }
            i++;
            list = tileQueueArray.get(i);
        }
        ArrayList tiles = new ArrayList();
        for(LinkedList<Tile> l : tileQueueArray){
            for (Place p : l){
                tiles.add(p);
            }
        }
        return tiles;
    }

    /**
     * Create Room and add to arrays
@@ -292,4 +325,36 @@ public Tile getTile(int x, int y) {
    public void setBackgroundImage(String img_path) {
        controller.changeBackground(img_path);
    }

    /**
     *
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     *
     * @param players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     *
     * @return
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *
     * @param currentPlayer
     */
    public static void setCurrentPlayer(Player currentPlayer) {
        Board.currentPlayer = currentPlayer;
    }
}
