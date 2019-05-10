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
public class KartenTest {

    Karten k = new Karten();

    public KartenTest() {
    }
    
    @Test
    public void testKartenErstellen() {
        //Arrange
        k.kartenErstellen();
                
        //Assert
        assertEquals(52, k.getCardsInDeck());
    }

    @Test
    public void testGetAnzahlKartenInKartenSymbole() {
        //Arrange
        int erwarteteAnzahl = 52;
        
        //Act
        k.kartenErstellen();
        
        //Assert
        assertEquals(erwarteteAnzahl, k.getCardsSymbole().size());
    }

    @Test
    public void testGetCardsInDeck() {
        //Arrange
        int erwarteteAnzahl = 52;

        //Act
        k.kartenErstellen();

        //Assert
        assertEquals(erwarteteAnzahl, k.getKarten().size());
    }

    @Test
    public void testSubAmountOfCardsInDeck() {
        //Arrange
        k.setAmountOfCardsInDeck(52);
        
        //Act
        k.subAmountOfCardsInDeck();
        
        //Assert
        assertEquals(51, k.getCardsInDeck());
    }

    @Test
    public void testSetAmountOfCardsInDeck() {
        //Arrange
        k.setAmountOfCardsInDeck(10);
        
        //Assert
        assertEquals(10, k.getCardsInDeck());
    }

    @Test
    public void testSubAmountOfCardSymbols() {
        //Arrange
        k.setAmountOfCardsInCardsSymbole(52);
        
        //Act
        k.subAmountOfCardSymbols();
        
        //Assert
        assertEquals(51, k.getAmountOfCardsInCardsSymbole());
    }

    @Test
    public void testSetAmountOfCardsInCardsSymbole() {
        //Arrange
        k.setAmountOfCardsInCardsSymbole(10);
        
        //Assert
        assertEquals(10, k.getAmountOfCardsInCardsSymbole());
    }
}
