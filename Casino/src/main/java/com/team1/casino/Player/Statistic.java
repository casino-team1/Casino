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
public class Statistic {

    private String gamePlayed;
    private String result;

    public Statistic(String gamePlayed, String result, double amount) {
        this.gamePlayed = gamePlayed;
        this.result = result;
        this.amount = amount;
    }

    public String getGamePlayed() {
        return gamePlayed;
    }

    public String getResult() {
        return result;
    }

    public double getAmount() {
        return amount;
    }
    private double amount;
}
