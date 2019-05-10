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
public class BlackJackDealerModelTest {

    BlackJackDealerModel d = new BlackJackDealerModel();

    public BlackJackDealerModelTest() {
    }

    @Test
    public void testSetWon() {
        //Arrange
        d.setWon(true);
                
        //Assert
        assertEquals(true, d.hasWon());
    }

    @Test
    public void testSetDealerCardValue() {
        //Arrange
        d.setDealerCardValue(10);
                
        //Assert
        assertEquals(10, d.getDealerCardValue());
    }

    @Test
    public void testAddDealerCardValues() {
        //Arrange
        d.setSecondCardValue(10);
        d.setDealerCardValue(10);
        
        //Act
        d.addDealerCardValues();
        
        //Assert
        assertEquals(20, d.getDealerCardValue());
    }

    @Test
    public void testSetSecondCardValue() {
        //Arrange
        d.setSecondCardValue(10);
                
        //Assert
        assertEquals(10, d.getSecondCardValue());
    }

    @Test
    public void testClearDealerCards() {
        //Arrange
        d.clearDealerCards();
                
        //Assert
        assertEquals(0, d.getDealerCards().size());
    }

}
