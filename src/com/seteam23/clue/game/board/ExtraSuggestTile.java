/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seteam23.clue.game.board;

import static com.seteam23.clue.game.GameRevised.getCurrentPlayer;
import static com.seteam23.clue.game.GameRevised.getRound;

/**
 *
 * @author InfernoKay
 */
public class ExtraSuggestTile extends Tile {
    
    public ExtraSuggestTile(int x, int y) {
        super(x, y);
        
        getButton().setText("🖎");
    }
    
    @Override
    public void activate() {
        getCurrentPlayer().moveTo(this);
        getCurrentPlayer().extraSuggest(getRound());
    }
}
