package com.seteam23.clue.menus;

import com.seteam23.clue.game.GameController;
import com.seteam23.clue.game.Game;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.main.MainController;
import static com.seteam23.clue.main.Main.makeFullscreen;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.stage.Stage;

/**
 * FXML SinglePlayerMenuController class
 * The controller class, allows the FXML file to be initialised, and
 * allows the user to interact with the single player character selection menu GUI
 *
 * @author Team 23
 */
public class SingleplayerMenuController implements Initializable {
    

    public final Menu MENU = new Menu();

    
    @FXML private Button buttonScarlett;
    @FXML private Button main_menu;
    @FXML private Button board_game;

    @FXML private ComboBox<String> difLevel;
    @FXML private ComboBox<Integer> numPlayers;

    private Button prevCharacter;
    private String charName = "Miss Scarlett";  //Set Miss Scarlett as default


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
        difLevel.getSelectionModel().select("MEDIUM");  //Select "MEDIUM" as default
        ObservableList<Integer> listOpo = FXCollections.observableArrayList(1,2,3,4,5);
        numPlayers.setItems(listOpo);
        numPlayers.getSelectionModel().select(2);    // Index position in observableArray    
    }
    
    
    /**
     * Changes to the Main Menu's Scene.
     */
    @FXML
    private void mainMenu() throws Exception{
        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        
        Stage window_menu = (Stage)main_menu.getScene().getWindow();
        window_menu.setScene(new Scene(root));
        window_menu.setFullScreen(true);
        makeFullscreen(root,970,545);
    }
    
    /**
     * Changes to the Board's Scene.
     */
    @FXML
    private void continueBoard() throws Exception {
        ArrayList<Player> playerList = new ArrayList<Player>(){
            {
                add(MENU.newPlayer(charName));
            }
        };
        FXMLLoader loader = new FXMLLoader(GameController.class.getResource("game.fxml"));
        Parent gameScene = loader.load();
        GameController ctrl = loader.getController();
        
        Game game = new Game(ctrl, playerList, numPlayers.getSelectionModel().getSelectedItem(), difLevel.getSelectionModel().getSelectedItem(),
                                           MENU.WEAPON_CARDS, MENU.SUSPECT_CARDS, MENU.ROOM_CARDS, MENU.ALL_CARDS); //Initialise game
        
        Stage window_menu = (Stage)board_game.getScene().getWindow();
        window_menu.setScene(new Scene(gameScene));
        window_menu.setFullScreen(true);
        makeFullscreen(gameScene,1600,920);
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
        
        charName = b.getText();
        
        if(!b.getText().equals("Miss Scarlett")){
            this.buttonScarlett.setStyle("-fx-background-color: transparent");  //As Miss Scarlett is default, the way of unselecting the character is different
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
