/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.menus;


import com.seteam23.clue.game.GameControllerRevised;
import com.seteam23.clue.game.GameRevised;
import com.seteam23.clue.game.entities.PlayerRevised;
import static com.seteam23.clue.main.Main.makeFullscreen;
import com.seteam23.clue.main.MainController;
import static com.seteam23.clue.menus.SingleplayerMenuController.MENU;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static com.seteam23.clue.main.Main.makeFullscreen;
import com.seteam23.clue.main.MainController;
import java.io.IOException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benat
 */
public class MultiplayerMenuController implements Initializable {
    @FXML
    private Button buttonScarlett;
    @FXML 
    private Button board_game;
    
    @FXML
    private Button main_menu;
    
    @FXML 
    private ComboBox numPlayers;
    
    @FXML
    private Button confirm;

    private int choosingRange;
    private ArrayList<String> playing_players = new ArrayList<>();
    
    private final Menu spMenu;
    private String unchooseable;
    
    private Button prevCharacter;

    private final TabPane TABPANE;
    private final String[] TABNAMES;
    
    public MultiplayerMenuController() throws IOException{
        this.TABNAMES = new String[]{"Board", "Cards", "Checklist"};
        spMenu = new Menu();
        TABPANE = new TabPane();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choosingRange = 0; // Or 1 
        board_game.setDisable(true);
        ObservableList<Integer> listOpo = FXCollections.observableArrayList(2,3,4,5,6);
        numPlayers.setItems(listOpo);
        numPlayers.getSelectionModel().select(2);    // Index position in observableArray 
    }    
    
     /**
     * Changes to the Main Menu's Scene.
     * Loads the main menu FXML file if the user desires to go to the previous window
     */
    @FXML
    private void mainMenu() throws Exception{
        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        
        Stage window_menu = (Stage)main_menu.getScene().getWindow();
        window_menu.setScene(new Scene(root));
        window_menu.setFullScreen(true);
        makeFullscreen(root,970,545);
    }
    
    @FXML
    private void onMouseClicked(ActionEvent event) throws Exception{
        Button b = (Button)event.getSource();
        
        switch (b.getText()) {
            case "Miss Scarlett":
                b.setStyle("-fx-background-color: yellow");
                break;
            case "Prof Plum":
                b.setStyle("-fx-background-color: yellow");
                break;
            case "Col Mustard":
                b.setStyle("-fx-background-color: yellow");
                break;
            case "Mrs White":
                b.setStyle("-fx-background-color: yellow");
                break;
            case "Rev Green":
                b.setStyle("-fx-background-color: yellow");
                break;
            default:
                b.setStyle("-fx-background-color: yellow");
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
        if(choosingRange <= (Integer) numPlayers.getValue()){
            confirm.setDisable(false);
        }
    }
     /**
     * Changes to the Board Scene and the playable game.
     */
    @FXML
    private void continueBoard() throws Exception{       
        ArrayList<PlayerRevised> playerListMult = new ArrayList<PlayerRevised>(){
            {
                for(String player_name:playing_players){
                    add(MENU.newPlayer(player_name));
                    System.out.print(player_name);
                }
                
            }
        };
        FXMLLoader loader = new FXMLLoader(GameControllerRevised.class.getResource("game.fxml"));
        Parent gamesScene = loader.load();
        GameControllerRevised ctrl = loader.getController();
        
        GameRevised game = new GameRevised(ctrl, playerListMult, 0, null,
        MENU.WEAPON_CARDS, MENU.SUSPECT_CARDS, MENU.ROOM_CARDS, MENU.ALL_CARDS);
        
        
        Stage window_menu = (Stage)board_game.getScene().getWindow();
        window_menu.setScene(new Scene(gamesScene));
        window_menu.setFullScreen(true);
        makeFullscreen(gamesScene,1600,920);
    }
    @FXML
    private void confirm() throws Exception{
        if(choosingRange < (Integer) numPlayers.getValue()){
            if(prevCharacter == null){
                buttonScarlett.setDisable(true);
                playing_players.add("Miss Scarlett");

            }
            else{
                System.out.print(prevCharacter);
                prevCharacter.setDisable(true);
                playing_players.add(prevCharacter.getText());
            }
            choosingRange+=1;
            
            
            if(choosingRange == (Integer) numPlayers.getValue()){
                board_game.setDisable(false);
                confirm.setDisable(true);
            }
//        }
////
//            }
//            prevCharacter.setDisable(true);
//            choosingRange+=1;
//        }
//        if(choosingRange == (Integer) numPlayers.getValue()){
//            board_game.setDisable(false);
//            confirm.setDisable(true);
//        }
        }
    }
    
}
