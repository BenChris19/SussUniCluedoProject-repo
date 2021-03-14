/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.entities;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author InfernoKay
 */
public class BoardController implements Initializable {
    
    @FXML private AnchorPane anchor_pane;
    @FXML private ImageView background_img;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Board(this);
    }
    
    public void changeBackground(String image_path){
        background_img.setImage(new Image(getClass().getResource(image_path).toExternalForm()));
    }
    
}
