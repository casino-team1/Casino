/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author albio
 */
public class BlackJackSpielerModelTest {
    BlackJackSpielerModel s = new BlackJackSpielerModel();
    
    public BlackJackSpielerModelTest() {
    }

    @Test
    public void testSetWon() {
        //Arrange
        s.setWon(true);
                
        //Assert
        assertEquals(true, s.hasWon());
    }

    @Test
    public void testSetPlayerCardValues() {
        //Arrange
        s.setPlayerCardValues(10);
                
        //Assert
        assertEquals(10, s.getPlayerCardValues());
    }

    @Test
    public void testSubPlayerCardValuesByTen() {
        //Arrange
        s.setPlayerCardValues(50);
        
        //Act
        s.subPlayerCardValuesByTen();
                
        //Assert
        assertEquals(40, s.getPlayerCardValues());
    }

    @Test
    public void testClearPlayerCards() {
        //Arrange
        s.clearPlayerCards();
                
        //Assert
        assertEquals(0, s.getPlayerCards().size());
    }    
}
