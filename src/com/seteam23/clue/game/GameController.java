package com.seteam23.clue.game;

import com.seteam23.clue.game.entities.BoardController;
import com.seteam23.clue.game.entities.Card;
import com.seteam23.clue.game.entities.Player;
import com.seteam23.clue.main.MainController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

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
    private Game game;
    private String character = "Scarlett";  //Use Scarlett as default character
    private Button prevCharacter;
    private TabPane tabPane = new TabPane();
    private final String[] tabNames = {"Board", "Cards"};
    
    public GameController() throws IOException{
        game = new Game();
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
        ObservableList<String> list = FXCollections.observableArrayList("EASY","MEDIUM","HARD");
        difLevel.setItems(list);
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
    }
    /**
     * Changes to the Board's Scene.
     * @param event executes and event, in this case, it goes to the next window i.e 
     * the board game.
     */
    @FXML
    private void continueBoard(ActionEvent event) throws Exception{
        for (String s : tabNames) {
            Tab t = new Tab(s);
            t.setClosable(false);
            tabPane.getTabs().add(t);
            switch (s) {
                case "Board":
                    t.setContent(FXMLLoader.load(BoardController.class.getResource("board.fxml")));
                    break;
                case "Card":
                    //t.setContent(createCardPane());
                    break;
            }

        }
        Parent board = tabPane;
        
        Stage window_game = (Stage)board_game.getScene().getWindow();
        window_game.setScene(new Scene(board));
    }
        /**
     * Let's the user choose a character.
     * @param event executes and event, in this case, the border of the button
     * the player icon is on, changes to yellow to indicate that the user has chosen
     * that character.
     */
    /**
     * Currently need to find a way to fetch the current player 
    private Pane createCardPane() throws FileNotFoundException{
        TilePane cardPane = new TilePane();
        Player cur = game.getBoard().getCurrentPlayer();
        for (Card c : cur.viewCards()){
            InputStream stream = new FileInputStream(c.getImgPath());
            Image image = new Image(stream);
            ImageView imageView = new ImageView();
            //Setting image to the image view
            imageView.setImage(image);
            cardPane.getChildren().add(imageView);
        }
        return null;
    }
    */
    @FXML
    private void onMouseClicked(ActionEvent event) throws Exception{
        Button b = (Button)event.getSource();
        b.setStyle("-fx-background-color: yellow");
        character = b.getText();    
        
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
