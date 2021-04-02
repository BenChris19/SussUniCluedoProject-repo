/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class BoardController implements Initializable {
    
    Board board;
    
    @FXML private AnchorPane anchor_pane;
    @FXML private ImageView background_img;
    @FXML private GridPane grid;
    @FXML private ImageView player_img;
    
    @FXML private Button dices;
    @FXML private Button suggAcc;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = new Board(this);
        createButtons();
    }
    @FXML
    private void change(ActionEvent event) throws Exception{ 
        Button b = (Button)event.getSource();
        if (b.getText().equals("Make Suggestion")){
                    b.setStyle("-fx-background-color: red");
                    b.setText("Make Accusation"); 
        }
        else{
                    b.setStyle("-fx-background-color: green");
                    b.setText("Make Suggestion"); 
        }

    }
    
    /**
     * Change the background image to the designated new image
     * @param image_path 
     */
    public void changeBackground(String image_path){
        background_img.setImage(new Image(getClass().getResource(image_path).toExternalForm()));
    }
    public void changeChar(String image_path){
        player_img.setImage(new Image(getClass().getResource(image_path).toExternalForm()));
    }
    @FXML
    private void rollDices(ActionEvent event) throws Exception{
        Random r = new Random();
        int die1 = r.nextInt(6)+1;
        int die2 = r.nextInt(6)+1;
        
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

        
    }

    
    
    /**
     * Get the button linked to each tile and add it to the GridPane
     */
    public void createButtons() {
        for (int y = 0; y<25; y++) {
            for (int x = 0; x<24; x++) {
                Tile tile = board.getTile(x, y);
                if (tile != null) 
                    grid.add(tile.getButton(), x, y);
            }
        }
    }
}
