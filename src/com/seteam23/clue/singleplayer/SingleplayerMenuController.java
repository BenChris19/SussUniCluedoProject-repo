package com.seteam23.clue.singleplayer;

import com.seteam23.clue.game.GameController;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.ChecklistEntry;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.main.MainController;
import java.io.IOException;
import static com.seteam23.clue.main.Main.makeFullscreen;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getOpponentPlayers;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML GameController class
 * The controller class, allows the FXML file to be initialised, and
 * allows the user to interact with the GUI
 *
 * @author team23
 */
public class SingleplayerMenuController implements Initializable {
    @FXML
    private Button buttonScarlett;
    @FXML
    private Button main_menu;
    @FXML
    private Button board_game;
    
    
    @FXML
    private ComboBox difLevel;
    @FXML 
    private ComboBox numPlayers;


    private final SingleplayerMenu spMenu;
    private Button prevCharacter;

    private final TabPane tabPane;
    private final String[] tabNames = {"Board", "Cards", "Checklist"};
    private TableView table = new TableView();
    private ObservableList<ChecklistEntry> checklistElements;

    /**
     * 
     * @throws IOException 
     */
    public SingleplayerMenuController() throws IOException{
        spMenu = new SingleplayerMenu();
        tabPane = new TabPane();
    }


    /**
     * Initialises the controller class.
     * Sets up the character selection window, and adds items to the AI
     * difficulty combo box.
     * 
     * @param url The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     * @param rb The resources used to localise the root object, or <tt>null</tt> if
     * the root object was not localised.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listDif = FXCollections.observableArrayList("EASY","MEDIUM","HARD");
        difLevel.setItems(listDif);
        difLevel.getSelectionModel().select("MEDIUM");
        ObservableList<Integer> listOpo = FXCollections.observableArrayList(2,3,4,5,6);
        numPlayers.setItems(listOpo);
        numPlayers.getSelectionModel().select(2);    // Index position in observableArray    
    }
    
    
    /**
     * Changes to the Main Menu's Scene.
     * @param event executes and event, in this case, it goes to the previous window i.e 
     * the main menu
     */
    @FXML
    private void mainMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        
        
        Stage window_menu = (Stage)main_menu.getScene().getWindow();
        window_menu.setScene(new Scene(root));
        window_menu.setFullScreen(true);
        makeFullscreen(root,970,545);
    }
    
    /**
     * Changes to the Board's Scene.
     * @param event executes and event, in this case, it goes to the next window i.e 
     * the board game.
     */
    @FXML
    private void continueBoard(ActionEvent event) throws Exception{
        spMenu.setOpponents(((Integer)this.numPlayers.getValue())-1);
        for (String s : tabNames) {
            Tab t = new Tab(s);
            t.setClosable(false);
            tabPane.getTabs().add(t);
            switch (s) {
                case "Board":
                    t.setContent(FXMLLoader.load(GameController.class.getResource("game.fxml")));
                    break;
                case "Cards":
                    t.setContent(createCardPane());
                    break;
                case "Checklist":
                    t.setOnSelectionChanged(new EventHandler<Event>() {
                        @Override
                        public void handle(Event event) {
                            if (t.isSelected()) {
                                table.refresh();
                            }
                        }
                    });
                    t.setContent(createChecklistPane());
            }
        }
        Parent board = tabPane;
            Stage window_game = (Stage)board_game.getScene().getWindow();
            window_game.setScene(new Scene(board));
            window_game.setFullScreen(true);
            makeFullscreen(board,1600,940);
    
    }
    

    
    
    

        /**
     * Allows the user to choose a character.
     * @param event executes and event, in this case, the border of the button
     * the player icon is on, changes to yellow to indicate that the user has chosen
     * that character.
     */
    @FXML
    private void onMouseClicked(ActionEvent event) throws Exception{
        Button b = (Button)event.getSource();
        
        SingleplayerMenu.getPlayer1().setName(b.getText());
        SingleplayerMenu.getPlayer1().setImgPath("/resources/cards/players/"+b.getText()+".jpg");
        
        switch (b.getText()) {
            case "Miss Scarlett":
                SingleplayerMenu.getPlayer1().setTurn(1);
                SingleplayerMenu.getPlayer1().setCurrentPosYX(16, 0);
                break;
            case "Prof Plum":
                SingleplayerMenu.getPlayer1().setTurn(6);
                SingleplayerMenu.getPlayer1().setCurrentPosYX(0,5);
                break;
            case "Col Mustard":
                SingleplayerMenu.getPlayer1().setTurn(2);
                SingleplayerMenu.getPlayer1().setCurrentPosYX(23, 7);
                break;
            case "Mrs White":
                SingleplayerMenu.getPlayer1().setTurn(3);
                SingleplayerMenu.getPlayer1().setCurrentPosYX(14, 24);
                break;
            case "Rev Green":
                SingleplayerMenu.getPlayer1().setTurn(4);
                SingleplayerMenu.getPlayer1().setCurrentPosYX(9, 24);
                break;
            default:
                SingleplayerMenu.getPlayer1().setTurn(5);
                SingleplayerMenu.getPlayer1().setCurrentPosYX(0, 18);
                break;
        }
        
        if(!b.getText().equals("Miss Scarlett")){
            this.buttonScarlett.setStyle("-fx-background-color: transparent");
            b.setStyle("-fx-background-color: yellow");
        }
        else{
            this.buttonScarlett.setStyle("-fx-background-color: yellow");
        }
        
        if(prevCharacter != b && prevCharacter != null){
            this.prevCharacter.setStyle("-fx-background-color: transparent");
        }
        prevCharacter = b;
        
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
            "/resources/cards/weapons/Revolver.JPG","/resources/cards/weapons/Rope.JPG","/resources/cards/weapons/wrench.JPG",
        
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
        
        int distribution = gameCards.size()/(Integer)this.numPlayers.getValue();
        
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

    private Node createChecklistPane() {
        GridPane tablePane = new GridPane();
        table = new TableView();
        TableColumn<ChecklistEntry, String> nameCol = new TableColumn<>("Name");
        TableColumn<ChecklistEntry, String> cardTypeCol = new TableColumn<>("Card Type");
        TableColumn<ChecklistEntry, Button> markedCol = new TableColumn<>("Marked");
        System.out.println(SingleplayerMenu.getPlayer1().getChecklistEntries());
        checklistElements = SingleplayerMenu.getPlayer1().getChecklistEntries();
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
