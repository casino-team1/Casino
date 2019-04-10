/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Yatzy;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Flückiger
 */
public class CupTest {

    public CupTest() {
    }

    /**
     * Test of getDicearray method, of class Cup.
     */
    @Test
    public void testGetDicearray() {

    }

    /**
     * Test of setKeep method, of class Cup.
     */
    @Test
    public void testSetKeep() {

    }

    /**
     * Test of getKeep method, of class Cup.
     */
    @Test
    public void testGetKeep() {
    }

    /**
     * Test of throwDices method, of class Cup.
     */
    @Test
    public void testThrowDices() {
        Cup cub = new Cup();
        cub.throwDices();
        int EXPECTED_SIZE = 5;
        assertEquals(EXPECTED_SIZE, cub.getDicearray().size());
    }

}
