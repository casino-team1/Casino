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
public class BlackJackGameModelTest {
    BlackJackGameModel g = new BlackJackGameModel();
    BlackJackSpielerModel s = new BlackJackSpielerModel();
    BlackJackDealerModel d = new BlackJackDealerModel();
    
    public BlackJackGameModelTest() {
    }
    
    @Test
    public void testGewinnBerechnungBlackJack() {
        //Arrange
        g.setEinsatz(50);
        
        //Act
        g.gewinnBerechnungBlackJack();
        
        //Assert
        assertEquals(125, g.gewinnBerechnungBlackJack());
    }

    @Test
    public void testGewinnBerechnung() {
        //Arrange
        g.setEinsatz(50);
        
        //Act
        g.gewinnBerechnung();
        
        //Assert
        assertEquals(100, g.gewinnBerechnung());
    }
    
}
