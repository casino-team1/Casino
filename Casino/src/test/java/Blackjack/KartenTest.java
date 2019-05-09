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
    public void testGetAnzahlKartenImKartenDeck() {
        //Arrange
        int erwarteteAnzahl = 52;

        //Act
        k.kartenErstellen();

        //Assert
        assertEquals(erwarteteAnzahl, k.getKarten().size());
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

}
