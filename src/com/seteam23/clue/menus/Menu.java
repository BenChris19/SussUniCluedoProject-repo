package com.seteam23.clue.menus;

import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.PlayerRevised;
import java.util.ArrayList;
import java.util.Arrays;

/**MenuController
 * 
 * Initialises the playable game, by handing out cards to each player, choosing the murder cards and 
 * setting up the order of the playing characters.
 * 
 * @author Team 23
 */
public class Menu {
    private final ArrayList<String> CHARACTERS = new ArrayList<>(Arrays.asList("Miss Scarlett","Col Mustard","Mrs White","Rev Green","Mrs Peacock","Prof Plum"));
   
    private final ArrayList<String> CHARACTER_PATHS = new ArrayList<>(Arrays.asList("/resources/cards/players/Miss Scarlett.jpg","/resources/cards/players/Col Mustard.jpg","/resources/cards/players/Mrs White.jpg",
                                                                                    "/resources/cards/players/Rev Green.jpg","/resources/cards/players/Mrs Peacock.jpg","/resources/cards/players/Prof Plum.jpg"));    
    
    private final ArrayList<String> ROOM_PATHS = new ArrayList<>(Arrays.asList("/resources/cards/rooms/Ballroom.png","/resources/cards/rooms/Billard Room.png","/resources/cards/rooms/Conservatory.png",
                                                                               "/resources/cards/rooms/Dining Room.png","/resources/cards/rooms/Hall.png","/resources/cards/rooms/Kitchen.png",
                                                                               "/resources/cards/rooms/Library.png","/resources/cards/rooms/Lounge.png","/resources/cards/rooms/Study.png"));
    
    private final ArrayList<String> WEAPON_PATHS = new ArrayList<>(Arrays.asList("/resources/cards/weapons/Candlestick.JPG","/resources/cards/weapons/Knife.JPG","/resources/cards/weapons/Lead Pipe.JPG",
                                                                                 "/resources/cards/weapons/Revolver.JPG","/resources/cards/weapons/Rope.JPG","/resources/cards/weapons/Wrench.JPG"));
    
    public final ArrayList<Card> ALL_CARDS = new ArrayList<>();
    public final ArrayList<Card> WEAPON_CARDS = new ArrayList<>();
    public final ArrayList<Card> SUSPECT_CARDS = new ArrayList<>();
    public final ArrayList<Card> ROOM_CARDS = new ArrayList<>();
    
    /**
     * Creates cards and organises them into weapons,characters and rooms
     */
    public Menu() {
        WEAPON_PATHS.forEach((path) -> {
            WEAPON_CARDS.add(new Card(path.split("/")[4], path, path.split("/")[3]));   //Creates a card object, using regex it takes the name of the card and card type from image path
        });
        CHARACTER_PATHS.forEach((path) -> {
            SUSPECT_CARDS.add(new Card(path.split("/")[4], path, path.split("/")[3]));
        });
        ROOM_PATHS.forEach((path) -> {
            ROOM_CARDS.add(new Card(path.split("/")[4], path, path.split("/")[3]));
        });
        ALL_CARDS.addAll(WEAPON_CARDS);
        ALL_CARDS.addAll(SUSPECT_CARDS);
        ALL_CARDS.addAll(ROOM_CARDS);
    }
    
    public PlayerRevised newPlayer(String name) {
        return new PlayerRevised(CHARACTERS.get(getOrder(name)), "/resources/cards/players/"+name+".jpg");
    }      
    
    /**
     * Get index between 0 to 5 (inclusive) based on which character name is given
     * @param name
     * @return an integer from 0 to 5, showing the order of each character
     */
    public int getOrder(String name) {
        switch (name) {
            case "Miss Scarlett":
                return 0;
            case "Col Mustard":
                return 1;
            case "Mrs White":
                return 2;
            case "Rev Green":
                return 3;
            case "Mrs Peacock":
                return 4;
            default: //Prof Plum
                return 5;
        }
    }

    
}
    


    
