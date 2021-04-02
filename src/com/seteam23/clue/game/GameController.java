package com.seteam23.clue.game;

import com.seteam23.clue.game.entities.BoardController;
import com.seteam23.clue.main.MainController;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.game.entities.NPC;
import static com.seteam23.clue.main.Main.makeFullscreen;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML GameController class
 * The controller class, allows the FXML file to be initialised, and
 * allows the user to interact with the GUI
 *
 * @author team23
 */
public class GameController implements Initializable {
    @FXML
    private Button buttonScarlett;
    @FXML
    private Button main_menu;
    @FXML
    private Button board_game;
    
    @FXML
    private ComboBox difLevel;
    @FXML 
    private ComboBox numOpponents;
 
    private String character = "Scarlett";  //Use Scarlett as default character
    private Button prevCharacter;
    private ImageView imageCharacter;
    private ArrayList<String> others = new ArrayList<>(
        Arrays.asList("Scarlett","Mustard","Plum","Green","Peacock","White"));
    private Player user;

    

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
        ObservableList<Integer> listOpo = FXCollections.observableArrayList(2,3,4,5,6);
        numOpponents.setItems(listOpo);
        
    }
    public String getCharacterName(){
        return this.character;
    }
    public String getDifficulty(){
        if (this.difLevel.getSelectionModel().getSelectedItem() == null){
            return null;
        }
        else{
            return (String) this.difLevel.getSelectionModel().getSelectedItem();
        }
    }
    public int getOpponents(){
        if (this.numOpponents.getSelectionModel().getSelectedItem() == null){
            return 0;
        }
        else{
            return (int) this.numOpponents.getSelectionModel().getSelectedItem();
        }
    }
    public String getOtherCharacterNames(){
        return others.get(new Random().nextInt(others.size())); 
    }

    public ImageView getImageCharacter() {
        return imageCharacter;
    }
    

    
    /**
     * Changes to the Main Menu's Scene.
     * @param event executes and event, in this case, it goes to the previous window i.e 
     * the main menu
     */
    @FXML
    private void mainMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        makeFullscreen(root,2,2);
        
        Stage window_menu = (Stage)main_menu.getScene().getWindow();
        window_menu.setScene(new Scene(root));
        window_menu.setFullScreen(true);
    }
    /**
     * Changes to the Board's Scene.
     * @param event executes and event, in this case, it goes to the next window i.e 
     * the board game.
     */
    @FXML
    private void continueBoard(ActionEvent event) throws Exception{
        Parent board = FXMLLoader.load(BoardController.class.getResource("board.fxml"));
        
        if (getOpponents() == 0 || getDifficulty() == null){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Error");
            window.setWidth(500);
            window.setHeight(150);
            
            BorderPane paneError = new BorderPane();
            Label labelError = new Label("You must choose a difficulty level and the number of opponents");
            BorderPane.setAlignment(labelError, Pos.TOP_CENTER);
            paneError.setTop(labelError);
            
            Button buttonOK = new Button("Ok");  //closes current window and opens a new one, reseting the game
            buttonOK.setOnAction(e->{
                window.close();
            });
            BorderPane.setAlignment(buttonOK,Pos.CENTER);
            paneError.setBottom(buttonOK);
        
            Scene scene = new Scene(paneError);
            window.setScene(scene);
            window.showAndWait();
        }
        else{
            user = new Player(getCharacterName(),getOpponents(),true,getImageCharacter());
            ArrayList<NPC> players= new ArrayList<>();
            int remain = getOpponents();
            int i = 0;
            while (remain > 0){
                String check = getOtherCharacterNames();
                if(players.contains(check)){
                    this.others.remove(check);
                }
                else{
                    players.add(new NPC(getOtherCharacterNames(),getOpponents(),false,getDifficulty(),getImageCharacter()));
                    remain-=1;
                }
            }
            makeFullscreen(board,1.4,1.2);
            Stage window_game = (Stage)board_game.getScene().getWindow();
            window_game.setScene(new Scene(board));
            window_game.setFullScreen(true);
        }
        


    }
        /**
     * Let's the user choose a character.
     * @param event executes and event, in this case, the border of the button
     * the player icon is on, changes to yellow to indicate that the user has chosen
     * that character.
     */
    @FXML
    private void onMouseClicked(ActionEvent event) throws Exception{
        Button b = (Button)event.getSource();
        b.setStyle("-fx-background-color: yellow");
        character = b.getText();    
        imageCharacter = (ImageView) b.getGraphic();
        
        if(!character.equals("Scarlett")){
            this.buttonScarlett.setStyle("-fx-background-color: transparent");
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
