/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

import com.team1.casino.Entity.Statistic;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Flückiger
 */
public class StatisticTest {

    public StatisticTest() {
    }

    /**
     * Test of getGamePlayed method, of class Statistic.
     */
    @Test
    public void testGetGamePlayed() {
    }

    /**
     * Test of getResult method, of class Statistic.
     */
    @Test
    public void testGetResult() {
    }

    /**
     * Test of getAmount method, of class Statistic.
     */
    @Test
    public void testGetAmount() {
        Statistic stat = new Statistic("Won", 0.0, 0.0, "Baccara");
        String EXPECTED_RESULT = "Won";
        assertEquals(EXPECTED_RESULT, stat.getResult());
    }

}
