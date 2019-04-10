/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Flückiger
 */
public class UserUtilTest {

    public UserUtilTest() {
    }

    /**
     * Test of getHashedPassword method, of class UserUtil.
     */
    @Test
    public void testGetHashedPassword() {
    }

    /**
     * Test of isValidUser method, of class UserUtil.
     */
    @Test
    public void testIsValidUser() {
    }

    /**
     * Test of retreaveStoredUserPassword method, of class UserUtil.
     */
    @Test
    public void testRetreaveStoredUserPassword() {
    }

    /**
     * Test of isUniqueUsername method, of class UserUtil.
     */
    @Test
    public void testIsUniqueUsername() {
    }

    /**
     * Test of getUserRoleByUsername method, of class UserUtil.
     */
    @Test
    public void testGetUserRoleByUsername() {
    }

    /**
     * Test of getIDFromUserByUsername method, of class UserUtil.
     */
    @Test
    public void testGetIDFromUserByUsername() {
    }

    /**
     * Test of loadCurrentBalanceFromGivenUsername method, of class UserUtil.
     */
    @Test
    public void testLoadCurrentBalanceFromGivenUsername() {
    }

    /**
     * Test of updatePlayerBalance method, of class UserUtil.
     */
    @Test
    public void testUpdatePlayerBalance() {
    }

    /**
     * Test of validPassword method, of class UserUtil.
     */
    @Test
    public void testValidPassword() {
        String password = "1234";
        String hashedPassword = "$2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK";
        boolean EXPECTED_RESULT = true;
        assertEquals(EXPECTED_RESULT, new UserUtil().validPassword(password, hashedPassword));
    }

}
