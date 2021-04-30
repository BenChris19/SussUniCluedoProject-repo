package com.seteam23.clue.game;


import com.seteam23.clue.main.Main;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML GameOverController class
 * Initialises and FXML file with a message to show if the player has either won or lost the game
 *
 * @author Team23
 */
public class GameoverController implements Initializable {

    @FXML private Button playAgainButton;
    @FXML private Label winloseMess;
    @FXML private ImageView winLoseImg;
    
    /**
     * Initialises the controller class.
     * Changes the FXML file to be shown if the player has won or lost the game
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Game.gameLost){   //Player has lost
            winloseMess.setText("YOU LOSE!");
            winloseMess.setVisible(true);
            winloseMess.setVisible(true);
            winLoseImg.setImage(new Image("/resources/game/Game-over.jpg", 1036, 603, false, false));
            
            String winsound = "GameOver.mp3";
            Media sound = new Media(new File(winsound).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
        else{   //Player has won
            winloseMess.setText("YOU WIN!");
            winloseMess.setVisible(true);
            winloseMess.setVisible(true);
            winLoseImg.setImage(new Image("/resources/game/Win-game.jpg", 1036, 603, false, false));
            
            String winsound = "party_horn-Mike_Koenig-76599891.mp3";
            Media sound = new Media(new File(winsound).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            
        }
    }    
    
    /**
     * Gives the player the ability to play again
     */
    @FXML
    private void playAgain() throws Exception {

        Parent again = FXMLLoader.load(Main.class.getResource("main.fxml"));
        
        Stage window = (Stage)playAgainButton.getScene().getWindow();

        window.setScene(new Scene(again));
        window.setFullScreen(true);
        Main.makeFullscreen(again,871.9,545);
    }
    /**
     * Allows the user to quit the game and exit the system
     */
    @FXML
    private void quitGame() throws Exception {
        System.exit(0);
        
    }
    
}
