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
public class BaccaraDeckGeneratorTest {

    public BaccaraDeckGeneratorTest() {
    }

    /**
     * Test of getDecks method, of class BaccaraDeckGenerator.
     */
    @Test
    public void testGetDecks() {
        BaccaraDeckGenerator generator = new BaccaraDeckGenerator();
        int EXPECTED_DECK_SIZE = 416;
        assertEquals(EXPECTED_DECK_SIZE, generator.getDecks().size());
    }

}
