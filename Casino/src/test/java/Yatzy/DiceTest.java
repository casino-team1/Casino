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
public class DiceTest {

    public DiceTest() {
    }

    /**
     * Test of getValue method, of class Dice.
     */
    @Test
    public void testGetValue() {
        Dice dice = new Dice(5);
        int EXPECTED_RESULT = 5;
        assertEquals(EXPECTED_RESULT, dice.getValue());
    }

}
