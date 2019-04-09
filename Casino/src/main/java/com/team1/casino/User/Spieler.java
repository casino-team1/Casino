/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

/**
 *
 * @author Nick Flückiger
 */
public class Spieler extends User {

    public Spieler(String username, String password) {
        super(username, password);
    }

    @Override
    public void writeUserToDatabase() {
    }

}
