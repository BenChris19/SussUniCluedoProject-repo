/*
 *      The Board / Map

/*
 *      The Board / Map
 *
 *      Constructs Rooms and Tiles
*      Players exist within the Board
 */
package com.seteam23.clue.game.board;
import com.seteam23.clue.game.entities.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;



public final class Board{  
    // Reference to the Current used Board Controller
    private BoardController controller;
    
    private Place[][] places; //All
    private Room[][] rooms; //Room Doors
    private Tile[][] tiles; //Tiles
    
    private Player[] players;
    
public Board(BoardController controller) {
        this.controller = controller;

        createGrid();
        
        highlightAllTiles();
    }
public Board(BoardController controller, String img_path) {
        this.controller = controller;
        setBackgroundImage(img_path);
        
        createGrid();
    }
 public void createGrid() {
        places = new Place[25][24];
        rooms = new Room[25][24];
        tiles = new Tile[25][24];
        
        // Rooms
        Room study = createRoom(0,0,7,4);
        Room library = createRoom(1,6,5,5);
        Room billiard_room = createRoom(0,12,6,5);
        Room conservatory = createRoom(0,20,6,4);
        Room hall = createRoom(9,1,6,5);
        Room ballroom = createRoom(9,18,6,4);
        Room lounge = createRoom(1,18,5,4);
        Room dining_room = createRoom(17,10,6,4);
        Room kitchen = createRoom(19,19,4,4);

        // Doors
        addTile(study.addDoor("N"),6,3);
        addTile(library.addDoor("W"),6,8);
        addTile(library.addDoor("N"),3,10);
        addTile(billiard_room.addDoor("S"),1,12);
        addTile(billiard_room.addDoor("W"),5,15);
        addTile(conservatory.addDoor("W"),4,19);
        addTile(hall.addDoor("E"),9,4);
        addTile(hall.addDoor("N"),11,6);
        addTile(hall.addDoor("N"),12,6);
        addTile(ballroom.addDoor("E"),8,19);
        addTile(ballroom.addDoor("S"),9,17);
        addTile(ballroom.addDoor("S"),14,17);
        addTile(ballroom.addDoor("W"),15,19);
        addTile(lounge.addDoor("N"),17,5);
        addTile(dining_room.addDoor("S"),17,9);
        addTile(dining_room.addDoor("E"),16,12);
        addTile(kitchen.addDoor("S"),19,18);
        
        // Start Platforms
        createTile(0,5);
        createTile(0,18);
        createTile(16,0);
        createTile(23,7);
        createTile(9,24);
        createTile(14,24);
        // Empty Corner
        createTile(7,0);
        createTile(23,17);
        // Ballroom Start Exit
        createTile(7,23);
        createTile(8,23);
        createTile(9,23);
        createTile(14,23);
        createTile(15,23);
        createTile(16,23);
        // Room Corners
        createTile(6,6);
        createTile(6,10);
        createTile(5,19);
        createTile(16,15);
        createTile(17,15);
        createTile(18,15);
        // Library-Billard
        createTile(1,11);
        createTile(2,11);
        createTile(3,11);
        createTile(4,11);
        createTile(5,11);
        // Study-Library
        for (int x = 1; x <= 6; x++) {
            for (int y = 4; y <= 5; y++) {
                createTile(x, y);
            }
        }
        // Billard-Conservatory
        for (int x = 1; x <= 5; x++) {
            for (int y = 17; y <= 18; y++) {
                createTile(x, y);
            }
        }
        // Conservatory-Ballroom
        for (int x = 6; x <= 7; x++) {
            for (int y = 17; y <= 22; y++) {
                createTile(x, y);
            }
        }
        // Study-Hall
        for (int x = 7; x <= 8; x++) {
            for (int y = 1; y <= 6; y++) {
                createTile(x, y);
            }
        }
        // Hall-Lounge
        for (int x = 15; x <= 16; x++) {
            for (int y = 1; y <= 5; y++) {
                createTile(x, y);
            }
        }
        createTile(15,6);
        // Kitchen-Ballroom
        for (int x = 16; x <= 17; x++) {
            for (int y = 16; y <= 22; y++) {
                createTile(x, y);
            }
        }
        // Lounge-DiningRoom
        for (int x = 16; x <= 22; x++) {
            for (int y = 6; y <= 8; y++) {
                createTile(x, y);
            }
        }
        // DiningRoom-Kitchen
        for (int x = 18; x <= 22; x++) {
            for (int y = 16; y <= 17; y++) {
                createTile(x, y);
            }
        }
        // Ballroom Corridoor
        for (int x = 8; x <= 15; x++) {
            for (int y = 15; y <= 16; y++) {
                createTile(x, y);
            }
        }
        // Above Logo
        for (int x = 9; x <= 13; x++) {
            createTile(x,7);
        }
        // Left Logo
        for (int x = 6; x <= 8; x++) {
            for (int y = 11; y <= 16; y++) {
                createTile(x, y);
            }
        }
        for (int x = 7; x <= 8; x++) {
            for (int y = 7; y <= 10; y++) {
                createTile(x, y);
            }
        }
        // Right Logo
        for (int x = 14; x <= 15; x++) {
            for (int y = 7; y <= 17; y++) {
                createTile(x, y);
            }
        }
        
        // Adjacency
        for (int y = 0; y<25; y++) {
            for (int x = 0; x<24; x++) {
                Tile tile = tiles[y][x];
                
                if (tile != null) {       
                    // Possible Refactor?
                    // Door only adjacent
                    if (tile.getClass() == Door.class) {
                        Door door = (Door)tile;
                        if (y - 1 >= 0 && door.entryFrom() == "N") door.setAdjacent("N", tiles[y-1][x]);
                        if (y + 1 < 25 && door.entryFrom() == "S") door.setAdjacent("S", tiles[y+1][x]);
                        if (x - 1 >= 0 && door.entryFrom() == "W") door.setAdjacent("W", tiles[y][x-1]);
                        if (x + 1 < 24 && door.entryFrom() == "E") door.setAdjacent("E", tiles[y][x+1]);
                    }
                    // NEED TO CHECK IF ADJACENT IS DOOR THEN ONLY ADD IF IN ENTRY FROM SIDE
                    else {
                        if (y - 1 >= 0) tile.setAdjacent("N", tiles[y-1][x]);
                        if (y + 1 < 25) tile.setAdjacent("S", tiles[y+1][x]);
                        if (x - 1 >= 0) tile.setAdjacent("W", tiles[y][x-1]);
                        if (x + 1 < 24) tile.setAdjacent("E", tiles[y][x+1]);
                    }
                }
            }
        }
        
    }
 /**
     * Create a new Tile Entity and add to arrays
     * @param x
     * @param y
     * @return tile
     */
    private Tile createTile(int x, int y) {
        Tile tile = new Tile();
        places[y][x] = tile;
        tiles[y][x] = tile;
        
        return tile;
    }
    
    /**
     * Add an existing tile entity to the arrays
     * 
     * Currently only used for Doors
     * 
     * @param tile
     * @param x
     * @param y 
     */
    private void addTile(Tile tile, int x, int y) {
        places[y][x] = tile;
        tiles[y][x] = tile;
    }
    
    /**
     * Create Room and add to arrays
     * @param x
     * @param y
     * @param width
     * @param height
     * @return room
     */
    private Room createRoom(int x, int y, int width, int height) {
        Room room = new Room(x,y,width,height);
        places[y][x] = room;
        rooms[y][x] = room;
        
        return room;
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
    
    // Tried to do a recursive version of ^^^ but idk if it will perform better (havent tested)
    public ArrayList reachableFrom(Tile start, int movesRemaining) {
        ArrayList<Tile> reach = new ArrayList<>();
        
        if (movesRemaining > 0) {
            for (Tile a : start.getAdjacent().values()) {
                if (a != null) {
                    reach.addAll(reachableFrom(a, movesRemaining-1));
                }
            }
        }
        
        return reach;
    }
    
    public void highlightTiles(ArrayList<Tile> ts) {
        for (Tile t : ts) {
            t.startFlashing();
        }
    }
    
    public void unlightTiles(ArrayList<Tile> ts) {
        for (Tile t : ts) {
            t.stopFlashing();
        }
    }
    
    public void unlightAllTiles() {
        for (Tile[] tr : tiles) {
            for (Tile t : tr) 
                if (t != null) t.stopFlashing();
        }
    }
    
    public void highlightAllTiles() {
        for (Tile[] tr : tiles) {
            for (Tile t : tr) 
                if (t != null) t.startFlashing();
        }
    }
    
    /**
     * Get the tile at the specific coordinates
     * @param x
     * @param y
     * @return 
     */
    public Tile getTile(int x, int y) {
        return this.tiles[y][x];
    }
    
    /**
     * Set the board image to the new image
     * @param img_path 
     */
    public void setBackgroundImage(String img_path) {
        controller.changeBackground(img_path);
    }
    public void setCharacterImage(String img_path) {
        controller.changeChar(img_path);
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
}



