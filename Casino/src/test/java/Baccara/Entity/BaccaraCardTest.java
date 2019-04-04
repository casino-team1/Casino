/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraCardTest {

    public BaccaraCardTest() {
    }

    /**
     * Test of getImageLocation method, of class BaccaraCard.
     */
    @org.junit.Test
    public void testGetImageLocation() {
    }

    /**
     * Test of getCardName method, of class BaccaraCard.
     */
    @org.junit.Test
    public void testGetCardName() {
        BaccaraCard card = new BaccaraCard(BaccaraCardType.SPADE, "Ace", 1);
        String EXPECTED_RESULT = "Ace SPADE";
        assertEquals(EXPECTED_RESULT, card.getCardName());
    }

    /**
     * Test of getCardName method, of class BaccaraCard. Multiple tests
     */
    @org.junit.Test
    public void testGetCardNameMultipleTests() {
        for (int i = 2; i < 10; i++) {
            BaccaraCard card = new BaccaraCard(BaccaraCardType.SPADE, String.valueOf(i), i);
            String EXPECTED_RESULT = String.valueOf(i) + " " + BaccaraCardType.SPADE.toString();
            assertEquals(EXPECTED_RESULT, card.getCardName());
        }
    }

}
