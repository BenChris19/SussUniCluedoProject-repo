package com.seteam23.clue.Testing;

import com.seteam23.clue.game.entities.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void test1(){
        Player p = new Player();
        int[] n = p.rollDice();
    }
}
