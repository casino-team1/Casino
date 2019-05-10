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

        //Assert
        assertEquals(125, g.gewinnBerechnungBlackJack());
    }

    @Test
    public void testGewinnBerechnung() {
        //Arrange
        g.setEinsatz(50);

        //Assert
        assertEquals(100, g.gewinnBerechnung());
    }

    @Test
    public void testVersicherungGewonnen() {
        //Arrange
        g.setVersicherung(50);

        //Assert
        assertEquals(100, g.versicherungGewonnen());
    }

    @Test
    public void testSetVersicherung() {
        //Arrange
        g.setVersicherung(50);
                
        //Assert
        assertEquals(50, g.getVersicherung());
    }

    @Test
    public void testSetEinsatz() {
        //Arrange
        g.setEinsatz(50);
                
        //Assert
        assertEquals(50, g.getEinsatz());
    }
    
    @Test
    public void testSetGewinn() {
        //Arrange
        g.setGewinn(50);
                
        //Assert
        assertEquals(50, g.getGewinn());
    }

}
