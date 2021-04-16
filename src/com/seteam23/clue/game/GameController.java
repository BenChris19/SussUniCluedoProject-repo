/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.board.BoardController;
import com.seteam23.clue.game.board.Place;
import com.seteam23.clue.game.board.Tile;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.singleplayer.SingleplayerMenuController;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.generatePlayers;

import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getImageview;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayer;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayers;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getImageview;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayer;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class GameController implements Initializable {
    
    @FXML private BorderPane viewport;
    @FXML private ComboBox person;
    @FXML private ComboBox weapon;
    @FXML private ComboBox room;
    @FXML private ImageView player_img;
    @FXML private AnchorPane anchorPane;
    @FXML private Label moves_label;
    @FXML private GridPane grid;

    private Game game;
    public static Board board;
    public static int[] dieRolls = new int[3];
    public int turn = 0;


    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            game = new Game(this);
            board = new Board();
            createButtons();
            //board.getStartPos(getPlayer()).startFlashing();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        player_img = getImageview();
        anchorPane.getChildren().remove(player_img);
        anchorPane.getChildren().addAll(player_img);
        ObservableList<String> listPer = FXCollections.observableArrayList(game.getSuspectNames());
        this.person.setItems(listPer);
        ObservableList<String> listWea = FXCollections.observableArrayList(game.getWeaponNames());
        this.weapon.setItems(listWea);
        ObservableList<String> listRoo = FXCollections.observableArrayList(game.getRoomNames());
        this.room.setItems(listRoo);
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

    
    /**
     * 
     * @param event
     * @throws Exception 
     */
    @FXML
    private void change(ActionEvent event) throws Exception{ 
        Button b = (Button)event.getSource();
        if (b.getText().equals("Make Suggestion")) {
                    b.setStyle("-fx-background-color: red");
                    b.setText("Make Accusation"); 
        }
        else {
                    b.setStyle("-fx-background-color: green");
                    b.setText("Make Suggestion"); 
        }
    }
    
    /**
     * 
     * @param image_path 
     */
    public void changeChar(String image_path){
        player_img.setImage(new Image(getClass().getResource(image_path).toExternalForm()));
    }

    public static Board getBoard() {
        return board;
    }

    public static int[] getDieRolls() {
        return dieRolls;
    }

    public ImageView getPlayer_img() {
        return player_img;
    }

    public void setPlayer_img(ImageView player_img) {
        this.player_img = player_img;
    }
    
    
    
    /**
     * 
     * @param event
     * @throws Exception 
     */
    @FXML
    private void rollDices(ActionEvent event) throws Exception{
        
        //This needs to be incorporated to rollDice() in Game
        //I didn't want to remove and fuck up the FXML
        dieRolls = game.rollDice();
        
        moves_label.setText("YOU ROLLED A\n"+dieRolls[2]);
        
        board.unlightAllTiles();

            board.showAvailableMoves(board.getTile(Board.startCols[Place.turn], Board.startRows[Place.turn]),dieRolls[2]);





        


        
        /*
        Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setWidth(250);
            window.setHeight(150);
            
            BorderPane paneRoll = new BorderPane();
            Label labelRoll = new Label("You rolled a...");
            Label diceSum = new Label(Integer.toString(dieRolls[2]));
            BorderPane.setAlignment(labelRoll, Pos.TOP_CENTER);
            BorderPane.setAlignment(diceSum,Pos.CENTER);
            paneRoll.setTop(labelRoll);
            paneRoll.setCenter(diceSum);
            
            Button buttonOK = new Button("Ok");  //closes current window and opens a new one, reseting the game
            buttonOK.setOnAction(e->{
                window.close();
            });
            BorderPane.setAlignment(buttonOK,Pos.CENTER);
            paneRoll.setBottom(buttonOK);
        
            Scene scene = new Scene(paneRoll);
            window.setScene(scene);
            window.showAndWait();  
        */
    }

    
}