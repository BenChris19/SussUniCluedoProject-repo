/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import static com.seteam23.clue.game.GameRevised.BOARD;
import com.seteam23.clue.game.board.*;
import com.seteam23.clue.game.entities.*;
import com.seteam23.clue.main.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Team 23
 */
public final class GameControllerRevised implements Initializable {
    
    /*
     *  FXML Setup
     */
    @FXML private Tab boardTab;
    @FXML private Tab cardsTab;
    @FXML private Tab checklistTab;
    
    @FXML private GridPane grid;
    @FXML private Label moves_label;
    
    @FXML private ImageView player_img;
    @FXML private ImageView diceThrow;
    
    @FXML private ComboBox person;
    @FXML private ComboBox weapon;
    @FXML private ComboBox room;
    @FXML private Button suggest;
    @FXML private Button accuse;
    @FXML private Button diceRoll;
    @FXML private Button finish;
    
    @FXML private ImageView revealCard;
    @FXML private Label whoCard;
    
    
    /*
     *  Declarations
     */
    private GameRevised game;
    private PlayerRevised player;
    private final ArrayList<PlayerRevised> OUTOFGAME = new ArrayList<>();
    
    private final String[] playerImg = new String[]{"/resources/game/Miss-Scarlett-game-piece.png","/resources/game/Col-Mustard-game-piece.png","/resources/game/Mrs-White-game-piece.png","/resources/game/Rev-Green-game-piece.png","/resources/game/Mrs-Peacock-game-piece.png","/resources/game/Prof-Plum-game-piece.png"};
    public static final HashMap<String, ImageView[]> PLAYER_MARKERS = new HashMap<>();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listPer = FXCollections.observableArrayList("Miss Scarlett","Rev Green","Col Mustard","Mrs Peacock","Mrs White","Prof Plum");
        this.person.setItems(listPer);
        ObservableList<String> listWea = FXCollections.observableArrayList("Candlestick","Knife","Lead Pipe","Revolver","Rope","Wrench");
        this.weapon.setItems(listWea);
        ObservableList<String> listRoo = FXCollections.observableArrayList("Ballroom","Billard room","Conservatory","Dining room","Hall","Kitchen","Library","Lounge","Study");
        this.room.setItems(listRoo);
        
        createButtons();
       
        BOARD.getRooms().forEach((room) -> {
            ImageView[] playerRoomIcon = new ImageView[9];
            int[][] pos = room.getPlayerIndicatorPos();
            for (int i = 0; i < 6; i++) {
                int[] coord = pos[i];
                ImageView img = new ImageView(new Image(playerImg[i], 35, 35, false, false));
                img.setFitHeight(35);
                img.setFitWidth(35); 
                img.setVisible(false);
                playerRoomIcon[i] = img;
                grid.add(playerRoomIcon[i], coord[0], coord[1]);
            }
            PLAYER_MARKERS.put(room.getName(), playerRoomIcon);
        });
        
    }
    
    /**
     * Done After Creating Controller
     * @param game
     */
    public void setGame(GameRevised game) {
        this.game = game;
        this.player = game.getCurrentPlayer();
        player_img.setImage(new Image(this.player.IMG_PATH));
        setPanes();
        
        this.player.newTurn();
    }
    
    public void setPanes() {
        try {
            cardsTab.setContent(createCardPane());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameControllerRevised.class.getName()).log(Level.SEVERE, null, ex);
        }
        checklistTab.setContent(createChecklistPane());
    }
    
    /**
     * 
     */
    private void createButtons() {
        for (Tile[] tileArr : game.BOARD.getTiles()) {
            for (Tile tile : tileArr) {
                if (tile != null) grid.add(tile.getButton(), tile.x ,tile.y);
            }
        }
    }
    
    /**
     * 
     */
    private void rollDieAnimation() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                String winsound = "Dice-Roll-Sound.m4a";
                Media sound = new Media(new File(winsound).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                moves_label.setVisible(false);
                diceThrow.setVisible(true);
            }),
            new KeyFrame(Duration.seconds(2), e -> {
                moves_label.setVisible(true);
                diceThrow.setVisible(false);
            })
        );
        timeline.play();
    }
    
    
    /*
     *  FXML Methods
     */
    
    @FXML
    public void rollDie() {
        Random r = new Random();
        int roll = game.rollDice();
        
        moves_label.setText(""+roll);
        rollDieAnimation();
        this.player.clearSearchSpace();
        this.player.setSearchSpace(roll);
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> {
                BOARD.highlightTiles(this.player.getSearchSpace());
            })
        );
        timeline.play();

        if (!this.player.canRoll()) {
            diceRoll.setDisable(true);
        }
        
        if (!(this.player instanceof AIPlayer)) {
            if (this.player.isInRoom() || this.player.getSearchSpace().stream().anyMatch(Door.class::isInstance)) {
                suggest.setDisable(false);
                room.setDisable(false);
                weapon.setDisable(false);
                person.setDisable(false);
            }
            finish.setDisable(false);
        }
    }
    
    @FXML
    public void makeSuggestion() {
        if(this.player.isInRoom()){
            this.player.clearSearchSpace();
            
            this.room.getSelectionModel().select(((Room)this.player.getLocation()).getName());
            Card found = null;
            PlayerRevised nextPlayer = null;
            int i = 1;

            // If  player can suggest and value in person and weapon boxes
            if (person.getValue() != null && weapon.getValue() != null) {
                // If can suggest
                if (this.player.suggest()) {
                    // Current Room
                    Room current_room = (Room)player.getLocation();

                    //Move suggested Player
                    for (PlayerRevised p : game.PLAYERS) {
                        if (p.NAME.equals(person.getValue())) {
                            p.getLocation().removeOccupier(p);
                            p.enterRoom(current_room);
                            current_room.addOccupier(p);
                            break;
                        }
                    }
                    // Check for found or ran out of players
                    while (i < game.getNumberPlayers() && found == null) {
                        nextPlayer = game.PLAYERS.get((GameRevised.getTurn()+i) % game.getNumberPlayers());

                        // Look through cards of next player and see if have any of suggested
                        for (Card c : nextPlayer.getCards()) {
                            if (c.getName().equals(person.getValue()+".jpg") || c.getName().equals(weapon.getValue()+".JPG") || c.getName().equals(current_room.getName()+".png")) {
                                found = c;
                                this.player.markCard(found);
                                
                                Image cardImage = new Image(getClass().getResourceAsStream(c.getImgPath()));
                                this.revealCard.setImage(cardImage);
                                this.whoCard.setText(nextPlayer.NAME+" has this card");
                                if (!this.player.getClass().equals(AIPlayer.class)){  
                                    this.revealCard.setVisible(true);
                                    this.whoCard.setVisible(true);
                                    
                                    String winsound = "SuggSound.mp3";
                                    Media sound = new Media(new File(winsound).toURI().toString());
                                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                                    mediaPlayer.play();
                                }
                                
                                break;
                            }
                        }
                        i+=1;
                    }
                    // Found Something
                    if (found != null) {
                        this.revealCard.setImage(found.getImg());
                        this.whoCard.setText(nextPlayer.NAME+" has this card");
                    }
                    // Didn't
                    else {
                        this.whoCard.setText("No-one else had these cards");
                    }
                }

                // If Player no suggestions left disable accuse button
                if (!this.player.canSuggest()) {
                    suggest.setDisable(true); 
                }
                if (this.player.getClass().equals(AIPlayer.class)) {
                    accuse.setDisable(true);
                }
                else{
                    accuse.setDisable(false);
                }
            }
        }
    }
    private boolean checkHuman(){
        boolean isHumanLeft = false;
        for(PlayerRevised p: game.PLAYERS){
            if(!p.getClass().equals(AIPlayer.class) && p.isPlaying()){
                isHumanLeft = true;
            }
        }
        return isHumanLeft;
    }
    
    @FXML
    public void makeAccusation() throws IOException {
        player.accuse();
            if(game.getKillCards()[0].getName().equals(person.getValue()+".jpg") && game.getKillCards()[1].getName().equals(weapon.getValue()+".JPG") && game.getKillCards()[2].getName().equals(room.getValue()+".png")){
                        Parent root = FXMLLoader.load(GameControllerRevised.class.getResource("gameover.fxml"));
                        Stage window_over = (Stage)accuse.getScene().getWindow();
                        window_over.setScene(new Scene(root));
                        window_over.setFullScreen(true);
                        Main.makeFullscreen(root,871.9,545);
            }
            else{
                GameRevised.gameLost = true;
                    OUTOFGAME.add(this.player);
                    if(OUTOFGAME.size()>=game.getNumberPlayers() || !checkHuman()){
                        Parent root = FXMLLoader.load(GameControllerRevised.class.getResource("gameover.fxml"));
                        Stage window_over = (Stage)accuse.getScene().getWindow();
                        window_over.setScene(new Scene(root));
                        window_over.setFullScreen(true);
                        Main.makeFullscreen(root,871.9,545);
                    }
                    else{
                        endTurn();
                    }
            }
        
    }
    
    @FXML
    public void endTurn() {
        // Reset GUI
        this.player.clearSearchSpace();
        revealCard.setVisible(false);
        whoCard.setVisible(false);
        
        // Get Next Turn
        this.game.nextTurn();
        
        // Player
        this.player = GameRevised.getCurrentPlayer();
        if(!this.player.isPlaying()){
            endTurn();
        }
        player_img.setImage(new Image(this.player.IMG_PATH));     //It work for multiplayer, not for AI, however it makes sense that you can only see the image characte of what the user chose for single player
        setPanes();
        
        
        // If NPCm cannot look at cards or checklist tabs
        if (this.player.getClass().equals(AIPlayer.class)) {    //Players should be able to access and see the cards they have at all times
            cardsTab.setDisable(true);
            checklistTab.setDisable(true);
            diceRoll.setDisable(true);
        }
        // If Player, Look at Card Panes, Click Buttons
        else {
            cardsTab.setDisable(false);
            checklistTab.setDisable(false);
            diceRoll.setDisable(false);
        }
        
        moves_label.setVisible(false);
        finish.setDisable(true);
        suggest.setDisable(true);
        room.setDisable(true);
        weapon.setDisable(true);
        person.setDisable(true);
        accuse.setDisable(true);
    }
    
     /**
    * Creates a Pane of ImageViews displaying the player's cards.
    * 
    * @return CardPane
    */
    private Pane createCardPane() throws FileNotFoundException{
        TilePane cardPane = new TilePane();
        for(Card c:player.getCards()){
            
            Image temp = new Image(getClass().getResourceAsStream(c.getImgPath()));
            ImageView showCard = new ImageView();
            showCard.setImage(temp);
            showCard.setFitHeight(200);
            showCard.setFitWidth(125);

            cardPane.getChildren().add(showCard);
            
        }
        return cardPane;
    }
    
    
    /**
     * 
     * @return 
     */
    private Node createChecklistPane() {
        GridPane tablePane = new GridPane();
        TableView table = new TableView();
        TableColumn<ChecklistEntry, String> nameCol = new TableColumn<>("Name");
        TableColumn<ChecklistEntry, String> cardTypeCol = new TableColumn<>("Card Type");
        TableColumn<ChecklistEntry, Button> markedCol = new TableColumn<>("Marked");
        ObservableList<ChecklistEntry> checklistElements = player.getChecklistEntries();
        table.setItems(checklistElements);
        nameCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, String>("name"));
        cardTypeCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, String>("cardType"));
        markedCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, Button>("markButton"));
        table.getColumns().setAll(nameCol, cardTypeCol, markedCol);
        table.setRowFactory(tv -> new TableRow<ChecklistEntry>() {
        @Override
        protected void updateItem(ChecklistEntry item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || item.getCardType() == null){
                setStyle("");
            }
            else if (item.getCardType().equals("weapons")){
                setStyle("-fx-text-background-color: #ff0000;");
            }
            else if (item.getCardType().equals("rooms")){
                setStyle("-fx-text-background-color: #0000ff;");
            }
            else if (item.getCardType().equals("players")){
                setStyle("-fx-text-background-color: #00ff00;");
            }
            else{
                setStyle("-fx-background-color: #444444;");
            }
        }
        });
        tablePane.getChildren().add(table);
        tablePane.getRowConstraints().add(new RowConstraints(checklistElements.size()*32.5));
        return tablePane;
    }
    
    public ComboBox getPerson() {
        return person;
    }

    public ComboBox getWeapon() {
        return weapon;
    }
    
    
    
    public void setPerson(int i) {
        person.getSelectionModel().select(i);
    }

    public void setWeapon(int i) {
        weapon.getSelectionModel().select(i);
    }
}
    
    

