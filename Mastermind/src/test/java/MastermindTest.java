/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import domain.MastermindGame;

/**
 *
 * @author mcsirkka
 */
public class MastermindTest {
    
    MastermindGame game;

    @Before
    public void setUp() {
        this.game = new MastermindGame();
    }
    
    @Test
    public void constructorSetsGameOverAsFalse() {
        assertEquals(false, game.codeIsSolved());
    }
    
    @Test
    public void constructorSetsNumberOfGuessesAsZero() {
        assertEquals(0, game.getNumberOfGuesses());
    }
       
    @Test
    public void restartSetsNumberOfGuessesAsZero() {
        this.game.setNumberOfGuesses(5);
        this.game.restart();
        assertEquals(0, game.getNumberOfGuesses());
    }   
}
