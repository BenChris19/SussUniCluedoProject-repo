package com.seteam23.clue.menus;

import com.seteam23.clue.game.GameControllerRevised;
import com.seteam23.clue.game.GameRevised;
import com.seteam23.clue.game.entities.PlayerRevised;
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
 * FXML GameController class
 * The controller class, allows the FXML file to be initialised, and
 * allows the user to interact with the GUI
 *
 * @author Team 23
 */
public class SingleplayerMenuController implements Initializable {
    

    public static final Menu MENU = new Menu();

    
    @FXML private Button buttonScarlett;
    @FXML private Button main_menu;
    @FXML private Button board_game;

    @FXML private ComboBox<String> difLevel;
    @FXML private ComboBox<Integer> numPlayers;

    private Button prevCharacter;
    private String charName = "Miss Scarlett";


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
        ObservableList<Integer> listOpo = FXCollections.observableArrayList(1,2,3,4,5);
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
    private void continueBoard(ActionEvent event) throws Exception {
        ArrayList<PlayerRevised> playerList = new ArrayList<PlayerRevised>(){
            {
                add(MENU.newPlayer(charName));
            }
        };
        FXMLLoader loader = new FXMLLoader(GameControllerRevised.class.getResource("game.fxml"));
        Parent gameScene = loader.load();
        GameControllerRevised ctrl = loader.getController();
        
        GameRevised game = new GameRevised(ctrl, playerList, numPlayers.getSelectionModel().getSelectedItem(), difLevel.getSelectionModel().getSelectedItem(),
                                           MENU.WEAPON_CARDS, MENU.SUSPECT_CARDS, MENU.ROOM_CARDS, MENU.ALL_CARDS);
        
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
