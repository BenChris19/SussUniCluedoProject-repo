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
import java.util.HashSet;
import java.util.Random;

import java.util.Set;
import javafx.scene.control.Button;

public final class Board {

    private Place[][] places; //All
    private ArrayList<Room> rooms; //Rooms
    private Door[][] doors;
    private Tile[][] tiles; //Tiles
    private ArrayList<Passage> passages;
    
    private ArrayList<Player> players = new ArrayList<>();


    public Board(){
        createGrid();  
    }

    public Door getDoors(int y,int x) {
        return doors[y][x];
    }


    /**
     * 
     */
    public void createGrid() {
        places = new Place[25][24];
        rooms = new ArrayList<>();
        tiles = new Tile[25][24];
        doors = new Door[25][24];
        passages = new ArrayList<>();


        // Start Platforms
        createTile(0, 5);
        createTile(0, 18);
        createTile(16, 0);
        createTile(23, 7);
        createTile(9, 24);
        createTile(14, 24);
        // Empty Corner
        createTile(7, 0);
        createTile(23, 17);
        // Ballroom Start Exit
        createTile(7, 23);
        createTile(8, 23);
        createTile(9, 23);
        createTile(14, 23);
        createTile(15, 23);
        createTile(16, 23);
        // Room Corners
        createTile(6, 6);
        createTile(6, 10);
        createTile(5, 19);
        createTile(16, 15);
        createTile(17, 15);
        createTile(18, 15);
        // Library-Billard
        createTile(1, 11);
        createTile(2, 11);
        createTile(3, 11);
        createTile(4, 11);
        createTile(5, 11);
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
        createTile(15, 6);
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
            createTile(x, 7);
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
        
        
        // Rooms
        int[][] studyPlayers = new int[][]{ {2,1}, {3,1}, {4,1}, {2,2}, {3,2}, {4,2}};
        int[][] libraryPlayers = new int[][]{ {2,7}, {3,7}, {4,7}, {2,9}, {3,9}, {3,9}};
        int[][] billiardPlayers = new int[][]{ {2,13}, {3,13}, {2,14}, {3,14}, {2,15}, {3,15}};
        int[][] conservatoryPlayers = new int[][]{{1,21}, {2,21}, {3,21}, {4,21}, {2,22}, {3,22}};
        int[][] hallPlayers = new int[][]{{10,1}, {13,1}, {10,3}, {13,3}, {10,5}, {13,5}};
        int[][] ballroomPlayers = new int[][]{{10,19}, {11,19}, {12,19}, {13,19}, {11,21}, {12,21}};
        int[][] loungePlayers = new int[][]{{19,1}, {20,1}, {21,1}, {19,3}, {20,3}, {21,3}};
        int[][] diningPlayers = new int[][]{{18,11}, {19,11}, {20,11}, {21,11}, {21,12}, {21,3}};
        int[][] kitchenPlayers = new int[][]{{19,20}, {20,20}, {21,20}, {20,21}, {21,21}, {22,21}};
        
        Room study = createRoom("Study", studyPlayers, 0, 0, 7, 4);
        Room library = createRoom("Library", libraryPlayers, 1, 6, 5, 5);
        Room billiard_room = createRoom("Billard Room", billiardPlayers,0, 12, 6, 5);
        Room conservatory = createRoom("Conservatory", conservatoryPlayers, 0, 20, 6, 4);
        Room hall = createRoom("Hall", hallPlayers, 9, 1, 6, 5);
        Room ballroom = createRoom("Ballroom", ballroomPlayers, 9, 18, 6, 4);
        Room lounge = createRoom("Lounge", loungePlayers, 1, 18, 5, 4);
        Room dining_room = createRoom("Dining Room", diningPlayers, 17, 10, 6, 4);
        Room kitchen = createRoom("Kitchen", kitchenPlayers, 19, 19, 4, 4);

        // Doors
        addDoor(study, "N", 6, 3);
        addDoor(library, "W", 6, 8);
        addDoor(library, "N", 3, 10);
        addDoor(billiard_room, "S", 1, 12);
        addDoor(billiard_room, "W", 5, 15);
        addDoor(conservatory, "W", 4, 19);
        addDoor(hall, "E", 9, 4);
        addDoor(hall, "N", 11, 6);
        addDoor(hall, "N", 12, 6);
        addDoor(ballroom, "E", 8, 19);
        addDoor(ballroom, "S", 9, 17);
        addDoor(ballroom, "S", 14, 17);
        addDoor(ballroom, "W", 15, 19);
        addDoor(lounge, "N", 17, 5);
        addDoor(dining_room, "S", 17, 9);
        addDoor(dining_room, "E", 16, 12);
        addDoor(kitchen, "S", 19, 18);
        
        // Passages
        addPassage(study, kitchen, 0, 3);
        addPassage(kitchen, study, 18, 23);
        addPassage(lounge, conservatory, 23, 5);
        addPassage(conservatory, lounge, 1, 19);
        
        // Extra Tiles
        Random r = new Random();
        int et = 0;
        int etx, ety;
        Tile t;
        // 3 Extra Tiles
        while (et < 3) {
            etx = r.nextInt(24);
            ety = r.nextInt(25);
            t = tiles[ety][etx];
            // Not null and regular Tile
            if (t != null && t.getClass() == Tile.class) {
                int type = r.nextInt(2);
                // if type==0: extra roll, if type==1: extra suggest
                tiles[ety][etx] = type == 0 ? new ExtraRollTile(etx, ety) : new ExtraSuggestTile(etx, ety);
                et++;
            }
        }

        // Adjacency
        for (int y = 0; y < 25; y++) {
            for (int x = 0; x < 24; x++) {
                Tile tile = tiles[y][x];

                if (tile != null) {
                    // Possible Refactor?
                    // Door only adjacent
                    if (tile.getClass() == Door.class) {
                        Door door = (Door) tile;
                        
                        if (y - 1 >= 0 && door.entryFrom().equals("N")) {
                            door.setAdjacent("N", tiles[y - 1][x]);
                        }
                        if (y + 1 < 25 && door.entryFrom().equals("S")) {
                            door.setAdjacent("S", tiles[y + 1][x]);
                        }
                        if (x - 1 >= 0 && door.entryFrom().equals("W")) {
                            door.setAdjacent("W", tiles[y][x - 1]);
                        }
                        if (x + 1 < 24 && door.entryFrom().equals("E")) {
                            door.setAdjacent("E", tiles[y][x + 1]);
                        }
                    }
                    else if (tile.getClass() == Passage.class) {
                    }
                    else {
                        if (y - 1 >= 0) {
                            tile.setAdjacent("N", tiles[y - 1][x]);
                        }
                        if (y + 1 < 25) {
                            tile.setAdjacent("S", tiles[y + 1][x]);
                        }
                        if (x - 1 >= 0) {
                            tile.setAdjacent("W", tiles[y][x - 1]);
                        }
                        if (x + 1 < 24) {
                            tile.setAdjacent("E", tiles[y][x + 1]);
                        }
                    }
                }
            }
        }

    }

    /**
     * Create a new Tile Entity and add to arrays
     *
     * @param x
     * @param y
     * @return tile
     */
    private void createTile(int x, int y) {
        Tile tile = new Tile(x, y);
        places[y][x] = tile;
        tiles[y][x] = tile;
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
    private void addDoor(Room room, String entry, int x, int y) {
        Door door = room.addDoor(entry, x, y);
        places[y][x] = door;
        tiles[y][x] = door;
        doors[y][x] = door;
    }
    
    private void addPassage(Room start, Room end, int x, int y) {
        Passage passage = new Passage(start, end, x, y);
        System.out.println(passage);
        places[y][x] = passage;
        tiles[y][x] = passage;
        passages.add(passage);
        System.out.println(passages);
    }

    
    

    /**
     * Create Room and add to arrays
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return room
     */
    private Room createRoom(String roomName, int[][] playerIndicators, int x, int y, int width, int height) {
        Room room = new Room(roomName, playerIndicators, x, y, width, height);
        places[y][x] = room;
        rooms.add(room);

        return room;
    }
    
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    

    /**
     * Recursive method to find all tiles reachable from the start using Tile adjacency matrix
     * @param start Initial tile to check from
     * @param moves_remaining How many moves left, decreases by 1 when call to 0
     * @return Set of tiles reachable from the current tile
     */
    private Set<Tile> reachableRecursive(Tile start, int moves_remaining) {
        Set<Tile> reach = new HashSet<>();

        if (moves_remaining > 0) {
            start.getAdjacent().values().stream().filter((a) -> (a != null && !a.isFull())).forEachOrdered((a) -> {
                if(a instanceof Door){
                    Door checkDoor = (Door)a;
                    if(start.getKeyFromValue(start.getAdjacent(), checkDoor).equals(checkDoor.entryFrom())){
                        Set<Tile> r = reachableRecursive(a, moves_remaining - 1);
                        reach.addAll(r);
                        reach.add(a);
                    }
                }
                else{
                    Set<Tile> r = reachableRecursive(a, moves_remaining - 1);
                    reach.addAll(r);
                    reach.add(a);
                }
            });
        }

        return reach;
    }

    /**
     * Gets the tiles reachable from a start TILE with a known dice roll total
     * @param start Initial Tile
     * @param die_roll Total number of moves available
     * @return ArrayList of Tiles reachable from a Tile
     */
    public ArrayList<Tile> reachableFrom(Tile start, int die_roll) {
        Set<Tile> reach = reachableRecursive(start, die_roll);
        reach.remove(start);
        return new ArrayList<>(reach);
    }
    
    /**
     * Gets the tiles reachable from a ROOM through the doors with a known dice roll total
     * @param room Initial Room, uses doors to get places movable
     * @param die_roll Total number of moves available
     * @return ArrayList of Tiles reachable from a Room's doors
     */
    public ArrayList<Tile> reachableFrom(Room room, int die_roll) {
        Set<Tile> reach = new HashSet<>();
        // Reach from each door
        for (Door door : room.getDoors()) {
            reach.addAll(reachableRecursive(door, die_roll));
            reach.remove(door);
        }
        // Add passage in room if any
        for (Passage pass : passages) {
            if (pass.getLocation().equals(room)) {
                reach.add(pass);
            }
        }
        return new ArrayList<>(reach);
    }
    
    /**
     * 
     * @param convertTile
     * @return 
     */
    public ArrayList<Button> getReachableButtons(ArrayList<Tile> convertTile){
        ArrayList<Button> buttons = new ArrayList<>();
        for(Tile t:convertTile){
            buttons.add(t.getButton());
        }
        return buttons;
    }
    
    /**
     * 
     * @param start
     * @param die_roll
     * @return 
     */
    public ArrayList<Tile> furthestReachableFrom(Tile start, int die_roll) {
        Set<Tile> all_reach = reachableRecursive(start, die_roll);
        ArrayList<Tile> reach = new ArrayList<>();
        
        int[] s = start.getCoords();
        
        for (Tile tile : all_reach) {
            if (tile instanceof Door) {
                reach.add(tile);
            }
            else {
                int[] t = tile.getCoords();
                int dx = Math.abs(s[0]-t[0]);
                int dy = Math.abs(s[1]-t[1]);
                if (dx + dy == die_roll) {
                    reach.add(tile);
                }
            }
        }
        
        reach.remove(start);
        return reach;
    }
    
    /**
     * 
     * @param room
     * @param die_roll
     * @return 
     */
    public ArrayList<Tile> furthestReachableFrom(Room room, int die_roll) {
        ArrayList<Tile> reach = new ArrayList<>();
        Set<Tile> all_reach = new HashSet<>();
        
        for (Door door : room.getDoors()) {
            all_reach = reachableRecursive(door, die_roll);

            int[] s = door.getCoords();
            
            // All Tiles Reachable from Room
            for (Tile tile : all_reach) {
                // Add all doors
                if (tile instanceof Door) {
                    reach.add(tile);
                }
                // Add furthest tile
                else {
                    int[] t = tile.getCoords();
                    int dx = Math.abs(s[0]-t[0]);
                    int dy = Math.abs(s[1]-t[1]);
                    if (dx + dy == die_roll) {
                        reach.add(tile);
                    }
                }
            }
            
            // Passage in room
            for (Passage pass : passages) {
                if (pass.getLocation().equals(room)) {
                    reach.add(pass);
                }
            }
        }
        
        // Remove all doors in the room
        for (Door door : room.getDoors()) reach.remove(door);
        
        return reach;
    }

    /**
     * 
     * @param ts 
     */
    public void highlightTiles(ArrayList<Tile> ts) {
        for (Tile t : ts) {
            t.startFlashing();
        }
    }

    /**
     * 
     * @param ts 
     */
    public void unlightTiles(ArrayList<Tile> ts) {
        for (Tile t : ts) {
            t.stopFlashing();
        }
    }

    /**
     * 
     */
    public void unlightAllTiles() {
        for (Tile[] tr : tiles) {
            for (Tile t : tr) {
                if (t != null) {
                    t.stopFlashing();
                }
            }
        }
    }

    
    public void highlightAllTiles() {
        for (Tile[] tr : tiles) {
            for (Tile t : tr) {
                if (t != null) {
                    t.startFlashing();
                }
            }
        }
    }

    
    /**
     * 
     * @param start
     * @param dice_roll
     * @return List of tiles lit
     */
    public ArrayList<Tile> showAvailableMoves(Tile start, int dice_roll) {
        ArrayList<Tile> r = reachableFrom(start, dice_roll);
        highlightTiles(r);
        return r;
    }

    /**
     * Get the tile at the specific coordinates
     *
     * @param x
     * @param y
     * @return
     */
    public Tile getTile(int x, int y) {
        return this.tiles[y][x];
    }
    

    /**
     * 
     * @param tile 
     */
    public void startTile(Tile tile){
        if(tile.isFlashing() == true){
            tile.stopFlashing();
        }
        else{
            tile.startFlashing();
        }
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @param players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    /**
     * 
     * @param player
     * @return 
     */
    public Tile getStartPos(Player player){
        switch (player.getName()) {
            case "Prof Plum":
                return getTile(0,5);
            case "Col Mustard":
                return getTile(23,7);
            case "Rev Green":
                return getTile(9,24);
            case "Mrs Peacock":
                return getTile(0,18);
            case "Mrs White":
                return getTile(14,24);
            default:
                return getTile(16,0);
        }
        
    }
    public Tile setStartPos(int y,int x){
        return getTile(y,x);
    }

    public Place[][] getPlaces() {
        return places;
    }

    public Door[][] getDoors() {
        return doors;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Passage> getPassages() {
        return passages;
    }
    
    
        
}