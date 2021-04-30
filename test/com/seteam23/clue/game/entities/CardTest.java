package com.seteam23.clue.game.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Team 23
 */
public class CardTest {
    private Card c;

    @Before
    public void setUp() {
        c = new Card("patrick", "patrick.jpg", "character");
    }

    /**
     * Tests the equals method in the Card class.
     */
    public void equalsTest() {
        assertTrue(c.equals(c));
        assertFalse(c.equals(null));
        assertFalse(c.equals(new Checklist()));
        assertFalse(c.equals(new Card ("j", "j.jpg", "character")));
        Card x = new Card("Patrick", "Patrick.jpg", "Character"); 
        Card y = new Card("Patrick", "Patrick.jpg", "Character");
        assertTrue(x.equals(y));
    }
    
    /**
     * Tests the hash code method in the Card class.
     */
    @Test
    public void hashCodeTest() {
        Card x = new Card("Patrick", "Patrick.jpg", "Character"); 
        Card y = new Card("Patrick", "Patrick.jpg", "Character");
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }

}
