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
public class ImagesTest {
    
    public ImagesTest() {
    }

    /**
     * Test of getImage method, of class Images.
     */
    @Test
    public void testGetImage() {
        Images images = new Images();
        String EXPECTED_RESULT = "images/Yatzy/D2.png";
        assertEquals(EXPECTED_RESULT, images.getImage(2));
        EXPECTED_RESULT = "images/Yatzy/D3.png";
        assertEquals(EXPECTED_RESULT, images.getImage(3));
    }
    
}
