package com.seteam23.clue.singleplayer;

import com.seteam23.clue.game.GameController;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.main.MainController;
import com.seteam23.clue.game.entities.Player;
import java.io.IOException;
import static com.seteam23.clue.main.Main.makeFullscreen;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
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
 
    private String character = "Miss Scarlett";  //Use Scarlett as default character
    private SingleplayerMenu spMenu;
    private Button prevCharacter;
    private Image imageCharacter;
    private static ImageView imageview;
    private ArrayList<String> others = new ArrayList<>(
        Arrays.asList("Scarlett","Mustard","Plum","Green","Peacock","White"));
    private static Player user;
    private TabPane tabPane = new TabPane();
    private final String[] tabNames = {"Board", "Cards"};

    /**
     * 
     * @throws IOException 
     */
    public SingleplayerMenuController() throws IOException{
        spMenu = new SingleplayerMenu();
        SingleplayerMenuController.imageview = new ImageView(new Image(getClass().getResourceAsStream("/resources/main/Miss Scarlett.jpg")));
        SingleplayerMenuController.imageview.setFitHeight(370);
        SingleplayerMenuController.imageview.setFitWidth(245);
    }

    /**
     * 
     * @return 
     */
    public static ImageView getImageview() {
        return imageview;
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
        ObservableList<Integer> listOpo = FXCollections.observableArrayList(2,3,4,5,6);
        numPlayers.setItems(listOpo);
        
    }
    
    /**
     * 
     * @return 
     */
    public String getCharacterName(){
        return this.character;
    }
    
    /**
     * 
     * @return 
     */
    public String getDifficulty(){
        if (this.difLevel.getSelectionModel().getSelectedItem() == null){
            return null;
        }
        else{
            return (String) this.difLevel.getSelectionModel().getSelectedItem();
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getOpponents(){
        if (this.numPlayers.getSelectionModel().getSelectedItem() == null){
            return 0;
        }
        else{
            return (int) this.numPlayers.getSelectionModel().getSelectedItem();
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getOtherCharacterNames(){
        return others.get(new Random().nextInt(others.size())); 
    }
    
    /**
     * 
     * @return 
     */
    public Image getImageCharacter() {
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
            this.user = new Player(this.character,getOpponents(),true);

        for (String s : tabNames) {
            Tab t = new Tab(s);
            t.setClosable(false);
            tabPane.getTabs().add(t);
            switch (s) {
                case "Board":
                    //t.setContent(FXMLLoader.load(BoardController.class.getResource("board.fxml")));
                    t.setContent(FXMLLoader.load(GameController.class.getResource("game.fxml")));
                    
                    break;
                case "Card":
                    t.setContent(createCardPane());
                    break;
            }

        }
        Parent board = tabPane;
        

            Stage window_game = (Stage)board_game.getScene().getWindow();
            window_game.setScene(new Scene(board));
            window_game.setFullScreen(true);
            makeFullscreen(board,1600,940);
    }
    }
    
    /**
     * 
     * @return
     */
    public static Player getPlayer(){
        return SingleplayerMenuController.user;
    }
    
    
//@@ -85,6 +115,22 @@ private void continueBoard(ActionEvent event) throws Exception{
    // * the player icon is on, changes to yellow to indicate that the user has chosen
    // * that character.
    // */
    
    /**
     * 
     * @return
     * @throws FileNotFoundException 
     */
    private Pane createCardPane() throws FileNotFoundException{
        TilePane cardPane = new TilePane();
        for (Card c : user.viewCards()){
            InputStream stream = new FileInputStream(c.getImgPath());
            Image image = new Image(stream);
            ImageView imageView = new ImageView();
            //Setting image to the image view
            imageView.setImage(image);
            cardPane.getChildren().add(imageView);
        }
        return cardPane;
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
        Image image = new Image(getClass().getResourceAsStream("/resources/main/"+this.character+".jpg"));
        this.imageview = new ImageView(image);
        this.imageview.setFitHeight(370);
        this.imageview.setFitWidth(245);

        
        if(!character.equals("Miss Scarlett")){
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
