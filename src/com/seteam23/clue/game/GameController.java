/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.board.Tile;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.ChecklistEntry;
import com.seteam23.clue.game.entities.NPC;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.main.Main;
import com.seteam23.clue.singleplayer.SingleplayerMenu;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getMurderCards;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getOpponentPlayers;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getPlayer1;
import java.io.File;
import java.io.FileNotFoundException;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class GameController implements Initializable {
    
    @FXML private Tab boardTab;
    @FXML private Tab cardsTab;
    @FXML private Tab checklistTab;
    
    @FXML private ComboBox person;
    @FXML private ComboBox weapon;
    @FXML private ComboBox room;
    @FXML private ImageView player_img;
    @FXML private ImageView diceThrow;
    @FXML private ImageView revealCard;
    @FXML private Label whoCard;

    @FXML private Label moves_label;
    @FXML private GridPane grid;
    
    @FXML private Button suggest;
    @FXML private Button accuse;
    
    private int[] aiPrevX;
    private int[] aiPrevY;
    private static final Game game = new Game();
    private static final Board board = new Board();
    private ArrayList<Tile> searchSpace;
    private Player startingPlayer;
    public int dieRolls;
    public int turn = 0;
    private static boolean win = false;
    
    private final String[] playerImg = new String[]{"/resources/game/Miss-Scarlett-game-piece.png","/resources/game/Col-Mustard-game-piece.png","/resources/game/Mrs-White-game-piece.png","/resources/game/Rev-Green-game-piece.png","/resources/game/Mrs-Peacock-game-piece.png","/resources/game/Prof-Plum-game-piece.png"};
    private static final HashMap<String, ImageView[]> playerMarkers = new HashMap<>();
    private int numberOfPlayers = 6;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TabPane tabPane;
    @FXML
    private ImageView background_img;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cardsTab.setContent(createCardPane());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        checklistTab.setContent(createChecklistPane());
        
        
        playerMarkers.clear();
        
        createButtons();
        
        aiPrevX = new int[6];
        aiPrevY = new int[6];
        
        board.getRooms().forEach((room) -> {
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
            playerMarkers.put(room.getRoomName(), playerRoomIcon);
        });

        ArrayList<String> allPlayers = new ArrayList<>(Arrays.asList("Miss Scarlett","Col Mustard","Mrs White","Rev Green","Mrs Peacock","Prof Plum"));
        int startingPlayerPos = 0;
        boolean startFound = false;
        Image userImage = null;
        while(!startFound){
        for(int i=0;i<getOpponentPlayers().size();i++){
            if(getOpponentPlayers().get(i).getName().equals(allPlayers.get(startingPlayerPos))){
                userImage = new Image(getClass().getResourceAsStream(getOpponentPlayers().get(i).getImgPath()));
                this.startingPlayer = getOpponentPlayers().get(i);
                startFound = true;
                break;
            }
            else if (getPlayer1().getName().equals(allPlayers.get(startingPlayerPos))){
                userImage = new Image(getClass().getResourceAsStream(getPlayer1().getImgPath()));
                this.startingPlayer = getPlayer1();
                this.startingPlayer.setHuman(true);
                startFound = true;
                break;
            }
            }
            startingPlayerPos+=1;
        }

        player_img.setImage(userImage);
        ObservableList<String> listPer = FXCollections.observableArrayList("Miss Scarlett","Rev Green","Col Mustard","Mrs Peacock","Mrs White","Prof Plum");
        this.person.setItems(listPer);
        ObservableList<String> listWea = FXCollections.observableArrayList("Candlestick","Knife","Lead Pipe","Revolver","Rope","Wrench");
        this.weapon.setItems(listWea);
        ObservableList<String> listRoo = FXCollections.observableArrayList("Ballroom","Billard room","Conservatory","Dining room","Hall","Kitchen","Library","Lounge","Study");
        this.room.setItems(listRoo);
        
        if(this.startingPlayer instanceof NPC){
            try {
                rollDie();
            } catch (Exception ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                     Platform.runLater(() -> {
                         try {
                             endTurn();
                         } catch (Exception ex) {
                             Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     });
                    timer.cancel();
                }
        },3200);
        
        }
    }

    public static void setWin(boolean lose) {
        GameController.win = lose;
    }

    public static boolean isWin() {
        return win;
    }
    
    
    public static Board getBoard() {
        return board;
    }

    public void setStartingPlayer(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
    
    public void createButtons() {
        for (int y = 0; y<25; y++) {
            for (int x = 0; x<24; x++) {
                Tile tile = board.getTile(x, y);
                if (tile != null) 
                    grid.add(tile.getButton(), x, y);
            }
        }
    }

    public Player getStartingPlayer() {
        return startingPlayer;
    }
    
    public static void showPlayerRoom(String roomName, String playerName) {
        int i;
        switch (playerName) {
            case "Col Mustard":
                i = 1;
                break;
            case "Mrs White":
                i = 2;
                break;
            case "Rev Green":
                i = 3;
                break;
            case "Mrs Peacock":
                i = 4;
                break;
            case "Prof Plum":
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        playerMarkers.get(roomName)[i].setVisible(true);
    }
    
    public static void hidePlayerRoom(String roomName, String playerName) {
        int i;
        switch (playerName) {
            case "Col Mustard":
                i = 1;
                break;
            case "Mrs White":
                i = 2;
                break;
            case "Rev Green":
                i = 3;
                break;
            case "Mrs Peacock":
                i = 4;
                break;
            case "Prof Plum":
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        playerMarkers.get(roomName)[i].setVisible(false);
    }
    
    public void rollDieAnimation(){
        Media sound = new Media(new File("Dice-Roll-Sound.m4a").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        Timer timer = new Timer();
        this.dieRolls = this.game.rollDice();
        moves_label.setText("    "+dieRolls);
        moves_label.setVisible(false);
        diceThrow.setVisible(true);
        if (getStartingPlayer().isHuman()) {
            getStartingPlayer().setSearchSpace(board.reachableFrom(board.getTile(getStartingPlayer().getCurrentPosY(), getStartingPlayer().getCurrentPosX()),dieRolls));
        }
        else {
            getStartingPlayer().setSearchSpace(board.furthestReachableFrom(board.getTile(getStartingPlayer().getCurrentPosY(), getStartingPlayer().getCurrentPosX()),dieRolls));
        }
        
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    diceThrow.setVisible(false);
                    moves_label.setVisible(true);
                    board.showAvailableMoves(board.getTile(getStartingPlayer().getCurrentPosY(), getStartingPlayer().getCurrentPosX()),dieRolls);
                    if(!getStartingPlayer().isHuman()){
                        if((aiPrevY[getStartingPlayer().getOrder()-1] != 0) && (aiPrevY[getStartingPlayer().getOrder()-1]!=0)){
                            getBoard().getTile(aiPrevY[getStartingPlayer().getOrder()-1], aiPrevX[getStartingPlayer().getOrder()-1]).getButton().getStyleClass().remove("toggle-"+getStartingPlayer().getName().split(" ",-1)[1]);
                        }
                        NPC npc = new NPC(getStartingPlayer().getName(),getStartingPlayer().getOrder(),getStartingPlayer().getImgPath(),getStartingPlayer().getCurrentPosY(),getStartingPlayer().getCurrentPosX(),false,false);
                        Tile aiMoved = npc.AIMoves(getStartingPlayer().getSearchSpace());
                        getStartingPlayer().setCurrentPosYX(GridPane.getColumnIndex(aiMoved.getButton()), GridPane.getRowIndex(aiMoved.getButton()));
                        aiPrevX[getStartingPlayer().getOrder()-1] = GridPane.getRowIndex(aiMoved.getButton());
                        aiPrevY[getStartingPlayer().getOrder()-1] = GridPane.getColumnIndex(aiMoved.getButton());
                    }
                    timer.cancel();
                }
            }, 2500);
        board.unlightAllTiles(); 
        
    }
    
    

    @FXML
    private void rollDie() throws Exception{
        this.startingPlayer.setEndTurn(false);
        rollDieAnimation();

    }
    @FXML private void WinLose(ActionEvent event) throws Exception{
        if(getMurderCards().contains(new Card(person.getValue()+".jpg","/resources/cards/players/"+person.getValue()+".jpg","players")) || getMurderCards().contains(new Card(weapon.getValue()+".JPG","/resources/cards/weapons/"+weapon.getValue()+".JPG","weapons")) || getMurderCards().contains(new Card(room.getValue()+".png","/resources/cards/rooms/"+room.getValue()+".png","rooms"))){
            setWin(true);
        }
            Parent root = FXMLLoader.load(GameController.class.getResource("gameover.fxml"));
            Stage window_over = (Stage)accuse.getScene().getWindow();
            window_over.setScene(new Scene(root));
            window_over.setFullScreen(true);
            Main.makeFullscreen(root,871.9,545);
        
    }
    @FXML
    private void makeSuggestion(ActionEvent event) throws Exception{
        if(this.startingPlayer.isInRoom() && (this.person.getValue()!= null || this.weapon.getValue() != null)){
            Media sound = new Media(new File("SuggSound.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            this.room.getSelectionModel().select(getBoard().getDoors(this.startingPlayer.getCurrentPosX(), this.startingPlayer.getCurrentPosY()).getRoom().getRoomName());
            boolean shown = false;
            int askNext = this.startingPlayer.getOrder()+1;
            while(!shown){
                if(askNext%7 == this.startingPlayer.getOrder()){
                    System.out.print("Done");
                    shown = true;
                }
                else{
                for(NPC eachPlayer:getOpponentPlayers()){                    
                    if(eachPlayer.getOrder() == (askNext%7)){
                        for(Card aiEachCard:eachPlayer.getCards()){
                            if(aiEachCard.getName().equals(person.getValue()+".jpg") || aiEachCard.getName().equals(weapon.getValue()+".JPG") || aiEachCard.getName().equals(room.getValue()+".png")){
                                System.out.print(aiEachCard.getName()+"   ");
                                System.out.print(eachPlayer.getName()+"  ");
                                this.whoCard.setText(eachPlayer.getName()+" has this card");
                                System.out.print(aiEachCard.getImgPath());
                                Image cardImage = new Image(getClass().getResourceAsStream(aiEachCard.getImgPath()));
                                this.revealCard.setImage(cardImage);
                                this.revealCard.setVisible(true);
                                this.whoCard.setVisible(true);
                                
                                
                                shown=true;
                            }
                        }   
                    }
                }
                askNext+=1;
                }
            }
        }
    }
    
    @FXML
    private void endTurn() throws Exception{
        this.revealCard.setVisible(false);
        this.whoCard.setVisible(false);
        board.unlightAllTiles();
        getStartingPlayer().setEndTurn(true);
        int next = getStartingPlayer().getOrder()+1;
        boolean nextPlayerFound = false;
        
        while(!nextPlayerFound){
            for(int i = 0;i<getOpponentPlayers().size();i++){
                if(getOpponentPlayers().get(i).getOrder() == next%7){
                    setStartingPlayer(getOpponentPlayers().get(i));
                    nextPlayerFound = true;
                    break;
                }
                else if(getPlayer1().getOrder() == next%7){
                    setStartingPlayer(getPlayer1());
                    this.getStartingPlayer().setHuman(true);
                    nextPlayerFound = true;      
                    break;
                }
            }
            next+=1;
        }
        Image userImage = new Image(getClass().getResourceAsStream(getStartingPlayer().getImgPath()));
        player_img.setImage(userImage);
        
        if(!getStartingPlayer().isHuman()){
            rollDieAnimation(); 
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                     Platform.runLater(new Runnable(){
                         @Override
                         public void run() {
                            try {
                                endTurn();
                            } catch (Exception ex) {
                                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         }
                     });
                    timer.cancel();
                }
            },3200);
        }   
    }
    
    
    public void setNumberOfPlayers(int num) {
        this.numberOfPlayers = num;
    }
    
    /**
    * Creates a Pane of ImageViews displaying the player's cards.
    * 
    * @return CardPane
    */
    private Pane createCardPane() throws FileNotFoundException{
        ArrayList<String> cardsImagePaths = new ArrayList<>(Arrays.asList("/resources/cards/rooms/Ballroom.png","/resources/cards/rooms/Billard Room.png","/resources/cards/rooms/Conservatory.png",
        "/resources/cards/rooms/Dining Room.png","/resources/cards/rooms/Hall.png","/resources/cards/rooms/Kitchen.png","/resources/cards/rooms/Library.png","/resources/cards/rooms/Lounge.png",
            "/resources/cards/rooms/Study.png",
        
        "/resources/cards/weapons/Candlestick.JPG","/resources/cards/weapons/Knife.JPG","/resources/cards/weapons/Lead Pipe.JPG",
            "/resources/cards/weapons/Revolver.JPG","/resources/cards/weapons/Rope.JPG","/resources/cards/weapons/Wrench.JPG",
        
        "/resources/cards/players/Miss Scarlett.jpg","/resources/cards/players/Col Mustard.jpg","/resources/cards/players/Rev Green.jpg",
            "/resources/cards/players/Prof Plum.jpg","/resources/cards/players/Mrs White.jpg","/resources/cards/players/Mrs Peacock.jpg"));
        Collections.shuffle(cardsImagePaths);
        
        TilePane cardPane = new TilePane();
        
        ArrayList<String> player1Cards = new ArrayList<>();
        
        ArrayList<Card> gameCards = new ArrayList<>();
        cardsImagePaths.forEach((path) -> {
            gameCards.add(new Card(path.split("/")[4],path,path.split("/")[3]));
        });
        
        SingleplayerMenu.getPlayer1().initialiseChecklist(gameCards);
        for (Player p : getOpponentPlayers()){
            p.initialiseChecklist(gameCards);
        }
        
        boolean[] filledMurder = new boolean[]{false,false,false};
        int j = 0;
        while(filledMurder[0] == false || filledMurder[1] == false || filledMurder[2] == false){
            if(gameCards.get(j).getCardType().equals("rooms") && !filledMurder[0]){
                filledMurder[0] = true;
                SingleplayerMenu.addMurderCards(gameCards.remove(j));
            }
            else if(gameCards.get(j).getCardType().equals("weapons") && !filledMurder[1]){
                filledMurder[1] = true;
                SingleplayerMenu.addMurderCards(gameCards.remove(j));
            }
            else if(gameCards.get(j).getCardType().equals("players") && !filledMurder[2]){
                filledMurder[2] = true;
                SingleplayerMenu.addMurderCards(gameCards.remove(j));
            }
            j+=1;
        }
        
        int distribution = gameCards.size()/this.numberOfPlayers;
        
        for(int i = 0;i<distribution;i++){
            Card temp = gameCards.remove(0);
            player1Cards.add(temp.getImgPath());
            SingleplayerMenu.getPlayer1().addCards(temp);
        }
        int numCards = gameCards.size();
        for(int i=0;i < numCards;i++){
            getOpponentPlayers().get(i%getOpponentPlayers().size()).addCards(gameCards.remove(0));  
        }
        player1Cards.stream().map((card) -> new Image(getClass().getResourceAsStream(card))).map((image) -> new ImageView(image)).map((temp) -> {
            temp.setFitHeight(200);
            return temp;
        }).map((temp) -> {
            temp.setFitWidth(125);
            return temp;
        }).forEachOrdered((temp) -> {
            cardPane.getChildren().add(temp);
        });
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
        //System.out.println(SingleplayerMenu.getPlayer1().getChecklistEntries());
        ObservableList<ChecklistEntry> checklistElements = getPlayer1().getChecklistEntries();
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
