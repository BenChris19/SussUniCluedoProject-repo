package com.seteam23.clue.singleplayer;

import com.seteam23.clue.game.GameController;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.ChecklistEntry;
import com.seteam23.clue.main.MainController;
import java.io.IOException;
import static com.seteam23.clue.main.Main.makeFullscreen;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getMurderCards;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

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
        
        Media sound = new Media(new File("Wii Music - No Copyright.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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
        SingleplayerMenu.setDif(difLevel.getValue().toString());
        
        FXMLLoader loader = new FXMLLoader(GameController.class.getResource("game.fxml"));
        Parent game = loader.load();
        GameController ctrl = loader.getController();
        
        ctrl.setNumberOfPlayers((Integer)this.numPlayers.getValue());
        
        for(Card c:getMurderCards()){
            System.out.print(c.getName());
        }
        
        Stage window_menu = (Stage)board_game.getScene().getWindow();
        window_menu.setScene(new Scene(game));
        window_menu.setFullScreen(true);
        makeFullscreen(game,1600,920);
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
}
