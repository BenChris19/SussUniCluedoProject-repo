/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import static com.seteam23.clue.game.GameRevised.BOARD;
import com.seteam23.clue.game.board.Room;
import com.seteam23.clue.game.board.Tile;
import com.seteam23.clue.game.entities.AIPlayer;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.ChecklistEntry;
import com.seteam23.clue.game.entities.PlayerRevised;
import com.seteam23.clue.main.Main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private ArrayList<Tile> searchSpace;
    
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
        
        this.searchSpace = new ArrayList<>();
        
        createButtons();
    }
    
    /**
     * Done After Creating Controller
     * @param game
     */
    public void setGame(GameRevised game) {
        this.game = game;
        this.player = game.getCurrentPlayer();
        player_img.setImage(new Image(this.player.IMG_PATH));
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
    
    /**
     * 
     * @return 
     */
    public ArrayList<Tile> getSearchSpace() {
        return searchSpace;
    }
    
    
    /*
     *  FXML Methods
     */
    
    @FXML
    public void rollDie() {
        Random r = new Random();
        int roll = 0;
        
        if (this.player.roll()) {
            int die1 = r.nextInt(6)+1;
            int die2 = r.nextInt(6)+1;
            roll = die1 + die2;
            //return rolls;
        }
        
        moves_label.setText(""+roll);
        rollDieAnimation();
        
        searchSpace = this.player.setSearchSpace(roll);
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> {
                BOARD.highlightTiles(searchSpace);
            })
        );
        timeline.play();
        
        diceRoll.setDisable(true);
        finish.setDisable(false);
    }
    
    @FXML
    public void makeSuggestion() {
        if(this.player.isInRoom()){
            suggest.setDisable(false);
        }
        Card found = null;
        PlayerRevised nextPlayer = null;
        int i = 1;
        
        // If  player can suggest and value in person and weapon boxes
        if (this.player.suggest() && person.getValue() != null && weapon.getValue() != null) {
            // Check for found or ran out of plauers
            while (found != null && i < game.getNumPlayers()) {
                nextPlayer = game.PLAYERS.get((game.getTurn()+i) % game.getNumPlayers());
                
                Room current_room = (Room)player.getLocation();
                nextPlayer.enterRoom(current_room);
                
                // Look through cards of next player and see if have any of suggested
                for (Card c : nextPlayer.getCards()) {
                    if (c.getName().equals(person+".jpg") || c.getName().equals(weapon+".JPG") || c.getName().equals(current_room.getName()+".png")) {
                        found = c;
                        break;
                    }
                }
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
    }
    
    @FXML
    public void makeAccusation() throws IOException {
            Parent root = FXMLLoader.load(GameControllerRevised.class.getResource("gameover.fxml"));
            Stage window_over = (Stage)accuse.getScene().getWindow();
            window_over.setScene(new Scene(root));
            window_over.setFullScreen(true);
            Main.makeFullscreen(root,871.9,545);
    }
    
    @FXML
    public void endTurn() {

        diceRoll.setDisable(false);
        finish.setDisable(true);
        
        this.game.nextTurn();
        
        
        // Player
        this.player = GameRevised.getCurrentPlayer();
        //player_img.setImage(new Image(this.player.IMG_PATH));     //It work for multiplayer, not for AI, however it makes sense that you can only see the image characte of what the user chose for single player
        
        // Reset GUI
        BOARD.unlightAllTiles();
        revealCard.setVisible(false);
        whoCard.setVisible(false);
        
        //Change Card and checklist pane if player
        if (!this.player.getClass().equals(AIPlayer.class)) {
            setGame(this.game);
            
        }
        
        // Unlight Tiles
        BOARD.unlightTiles(searchSpace);
        searchSpace.clear();
        
        // If NPC cannot look at cards or checklist tabs
        if (this.player.getClass().equals(AIPlayer.class)) {    //Players should be able to access and see the cards they have at all times
            cardsTab.setDisable(true);
            checklistTab.setDisable(true);
            while(!this.player.getClass().equals(AIPlayer.class)){
                rollDie();
                endTurn();
            }
        }
        // If Player can
        else {
            cardsTab.setDisable(false);
            checklistTab.setDisable(false);
        }

    }
    
     /**
    * Creates a Pane of ImageViews displaying the player's cards.
    * 
    * @return CardPane
    */
    private Pane createCardPane() throws FileNotFoundException{
        TilePane cardPane = new TilePane();
        player.initialiseChecklist(game.getAllCards());
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
        //System.out.println(player.getChecklistEntries());
        ObservableList<ChecklistEntry> checklistElements = player.getChecklistEntries();
        table.setItems(checklistElements);
        nameCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, String>("name"));
        cardTypeCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, String>("cardType"));
        //markedCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, Boolean>("checked"));
        markedCol.setCellValueFactory(new PropertyValueFactory<ChecklistEntry, Button>("mark"));
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
}
    
    

