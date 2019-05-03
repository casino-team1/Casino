/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User.Util;

import com.team1.casino.User.Spieler;

/**
 *
 * @author Nick Flückiger
 */
public class PlayerCentral {

    private static PlayerCentral userCentral;

    public static PlayerCentral getInstance() {
        if (userCentral == null) {
            userCentral = new PlayerCentral();
        }
        return userCentral;
    }

    private PlayerCentral() {

    }

    private Spieler spieler;

    public void setUser(Spieler user) {
        this.spieler = user;
    }

    public Spieler getUser() {
        return this.spieler;
    }
}
