package com.seteam23.clue.game.entities;

public class CheckTile {
    private int state;

    public CheckTile(){
        state = 1;
    }
    public void changeState(int state){
        if(state == 5) {
            state = 1;
        }
        else {
            state++;
        }
    }



}
