/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.board.BoardController;

import static com.seteam23.clue.game.board.BoardController.getBoard;
import com.seteam23.clue.game.board.Tile;

import com.seteam23.clue.game.entities.Card;

import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getImageview;
import static com.seteam23.clue.singleplayer.SingleplayerMenuController.getPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class GameController implements Initializable {
    
    @FXML private BorderPane viewport;
    @FXML private Button dices;
    @FXML private Button suggAcc;
    @FXML private ComboBox person;
    @FXML private ComboBox weapon;
    @FXML private ComboBox room;
    @FXML private ImageView player_img;
    @FXML private AnchorPane anchorPane;

    private Game game;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(BoardController.class.getResource("board.fxml"));
            viewport.setCenter(loader.load());
            game = new Game(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        player_img = getImageview();
        anchorPane.getChildren().addAll(player_img);
        ObservableList<String> listPer = FXCollections.observableArrayList(game.getSuspectNames());
        this.person.setItems(listPer);
        ObservableList<String> listWea = FXCollections.observableArrayList(game.getWeaponNames());
        this.weapon.setItems(listWea);
        ObservableList<String> listRoo = FXCollections.observableArrayList(game.getRoomNames());
        this.room.setItems(listRoo);
        
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
    
    /**
     * 
     * @param event
     * @throws Exception 
     */
    @FXML
    private void rollDices(ActionEvent event) throws Exception{
        
        //This needs to be incorporated to rollDice() in Game
        //I didn't want to remove and fuck up the FXML
        Random r = new Random();
        int die1 = r.nextInt(6)+1;
        int die2 = r.nextInt(6)+1;
        int total = die1 + die2;

        Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setWidth(250);
            window.setHeight(150);
            
            BorderPane paneRoll = new BorderPane();
            Label labelRoll = new Label("You rolled a...");
            Label diceSum = new Label(Integer.toString(die1 + die2));
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
            
            getBoard().startTile(getBoard().getStartPos(getPlayer()));
            ArrayList<Tile> reach = getBoard().showAvailableMoves(getBoard().getStartPos(getPlayer()), total);
            System.out.print(reach);
            getBoard().highlightTiles(reach);
    }
    
}
