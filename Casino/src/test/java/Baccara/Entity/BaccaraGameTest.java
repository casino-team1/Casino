/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Entity;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraGameTest {

    public BaccaraGameTest() {
    }

    /**
     * Test of reshuffleCards method, of class BaccaraGame.
     */
    @Test
    public void testReshuffleCards() {
        BaccaraGame game = new BaccaraGame();
        ArrayList<BaccaraCard> beforeShuffle = game.getRemainingCardDecks();
        game.reshuffleCards();
        ArrayList<BaccaraCard> afterShuffle = game.getRemainingCardDecks();
        boolean EXPECTED_RESULT = false;
        assertEquals(EXPECTED_RESULT, beforeShuffle == afterShuffle);
    }

    /**
     * Test of getPlayerCardCount method, of class BaccaraGame.
     */
    @Test
    public void testGetPlayerCardCount() {

    }

    /**
     * Test of getDealerCardCount method, of class BaccaraGame.
     */
    @Test
    public void testGetDealerCardCount() {
    }

    /**
     * Test of getPlayerBet method, of class BaccaraGame.
     */
    @Test
    public void testGetPlayerBet() {
        BaccaraGame game = new BaccaraGame();
        game.setPlayerBet(50);
        double EXPECTED_RESULT = 50.0;
        assertEquals(EXPECTED_RESULT, game.getPlayerBet(), 0.00);
    }

    /**
     * Test of getDealerBet method, of class BaccaraGame.
     */
    @Test
    public void testGetDealerBet() {
        BaccaraGame game = new BaccaraGame();
        game.setDealerBet(50);
        double EXPECTED_RESULT = 50.0;
        assertEquals(EXPECTED_RESULT, game.getDealerBet(), 0.00);
    }

    /**
     * Test of getTieBet method, of class BaccaraGame.
     */
    @Test
    public void testGetTieBet() {
        BaccaraGame game = new BaccaraGame();
        game.setTieBet(50);
        double EXPECTED_RESULT = 50.0;
        assertEquals(EXPECTED_RESULT, game.getTieBet(), 0.00);
    }

    /**
     * Test of setPlayerBet method, of class BaccaraGame.
     */
    @Test
    public void testSetPlayerBet() {
        BaccaraGame game = new BaccaraGame();
        game.setPlayerBet(50);
        double EXPECTED_RESULT = 50.0;
        assertEquals(EXPECTED_RESULT, game.getPlayerBet(), 0.00);
    }

    /**
     * Test of generateCards method, of class BaccaraGame.
     */
    @Test
    public void testGenerateCards() {
    }

    /**
     * Test of dealerThirdDraw method, of class BaccaraGame.
     */
    @Test
    public void testDealerThirdDraw() {
    }

    /**
     * Test of playerThirdDraw method, of class BaccaraGame.
     */
    @Test
    public void testPlayerThirdDraw() {
    }

    /**
     * Test of setDealerBet method, of class BaccaraGame.
     */
    @Test
    public void testSetDealerBet() {
    }

    /**
     * Test of setTieBet method, of class BaccaraGame.
     */
    @Test
    public void testSetTieBet() {
        BaccaraGame game = new BaccaraGame();
        game.setTieBet(50);
        double EXPECTED_RESULT = 50.0;
        assertEquals(EXPECTED_RESULT, game.getTieBet(), 0.00);
    }

    /**
     * Test of getDealerCards method, of class BaccaraGame.
     */
    @Test
    public void testGetDealerCards() {
    }

    /**
     * Test of getPlayerCards method, of class BaccaraGame.
     */
    @Test
    public void testGetPlayerCards() {
    }

    /**
     * Test of getRemainingCardDecks method, of class BaccaraGame.
     */
    @Test
    public void testGetRemainingCardDecks() {
    }

    /**
     * Test of resetGame method, of class BaccaraGame.
     */
    @Test
    public void testResetGame() {
        BaccaraGame game = new BaccaraGame();
        game.setTieBet(50);
        double EXPECTED_RESULT = 00.0;
        game.resetGame();
        assertEquals(EXPECTED_RESULT, game.getTieBet(), 0.00);

    }

    /**
     * Test of checkForAdditionalDraw method, of class BaccaraGame.
     */
    @Test
    public void testCheckForAdditionalDraw() {
    }

    /**
     * Test of getSetBets method, of class BaccaraGame.
     */
    @Test
    public void testGetSetBets() {
    }

    /**
     * Test of getTotalBet method, of class BaccaraGame.
     */
    @Test
    public void testGetTotalBet() {
        BaccaraGame game = new BaccaraGame();
        game.setTieBet(50);
        game.setPlayerBet(50);
        game.setDealerBet(50);
        double EXPECTED_RESULT = 150.0;
        assertEquals(EXPECTED_RESULT, game.getTotalBet(), 0.0);
    }

}
