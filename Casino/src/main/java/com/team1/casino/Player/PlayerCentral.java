/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Player;

/**
 *
 * @author Nick Flückiger
 */
public class PlayerCentral {

    private static PlayerCentral playerCentral;

    public static PlayerCentral getInstance() {
        if (playerCentral == null) {
            playerCentral = new PlayerCentral();
        }
        return playerCentral;
    }

    private PlayerCentral() {

    }

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
