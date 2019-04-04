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
public abstract class Player {

    private String username;
    private String password;
    private double currentBalance;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isValidPlayer() {
        return new PlayerUtil().isValidPlayer(this);
    }

}
