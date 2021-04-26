/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game;

import com.seteam23.clue.game.Game;
import com.seteam23.clue.game.board.Board;
import com.seteam23.clue.game.board.Door;

import com.seteam23.clue.game.board.Tile;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.NPC;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.main.Main;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getMurderCards;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getOpponentPlayers;
import static com.seteam23.clue.singleplayer.SingleplayerMenu.getPlayer1;
import com.seteam23.clue.singleplayer.SingleplayerMenuController;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class GameController implements Initializable {
    
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
    private Game game;
    private ArrayList<Tile> searchSpace;
    private Player startingPlayer;
    public static Board board;
    public int dieRolls;
    public int turn = 0;


    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = new Board();
        this.game = new Game();
        createButtons();
        aiPrevX = new int[6];
        aiPrevY = new int[6];

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
                rollDices();
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
    
    public void rollDicesAnimation(){
        Timer timer = new Timer();
        this.dieRolls = this.game.rollDice();
        moves_label.setText("    "+dieRolls);
        moves_label.setVisible(false);
        diceThrow.setVisible(true);
        getStartingPlayer().setSearchSpace(board.reachableFrom(board.getTile(getStartingPlayer().getCurrentPosY(), getStartingPlayer().getCurrentPosX()),dieRolls));
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
                        Button aiMovedButton = npc.AIMoves(getStartingPlayer().getSearchSpace()).getButton();
                        aiMovedButton.getStyleClass().add("toggle-"+getStartingPlayer().getName().split(" ",-1)[1]);
                        getStartingPlayer().setCurrentPosYX(GridPane.getColumnIndex(aiMovedButton), GridPane.getRowIndex(aiMovedButton));
                        aiPrevX[getStartingPlayer().getOrder()-1] = GridPane.getRowIndex(aiMovedButton);
                        aiPrevY[getStartingPlayer().getOrder()-1] = GridPane.getColumnIndex(aiMovedButton);
                    }
                    timer.cancel();
                }
            }, 2500);
        board.unlightAllTiles(); 
        
    }
    
    

    @FXML
    private void rollDices() throws Exception{
        this.startingPlayer.setEndTurn(false);
        rollDicesAnimation();

    }
    @FXML private void WinLose(ActionEvent event) throws Exception{
        if(!getMurderCards().contains(person.getValue()+".jpg") || !getMurderCards().contains(weapon.getValue()+".JPG") || !getMurderCards().contains(room.getValue()+".png")){
            Parent root = FXMLLoader.load(GameController.class.getResource("gameover.fxml"));
            Stage window_over = (Stage)accuse.getScene().getWindow();
            window_over.setScene(new Scene(root));
            window_over.setFullScreen(true);
            Main.makeFullscreen(root,871.9,545);
        }
        else{
            
        }
        
    }
    @FXML
    private void makeSuggestion(ActionEvent event) throws Exception{
        if(this.startingPlayer.getIsInRoom()){
            this.room.getSelectionModel().select(getBoard().getDoors(this.startingPlayer.getCurrentPosY(), this.startingPlayer.getCurrentPosX()).getRoom().getRoomName());
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
            rollDicesAnimation(); 
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
}
