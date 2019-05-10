/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

import java.sql.Date;

/**
 *
 * @author Nick Flückiger
 */
public class Statistic {

    public String getGameName() {
        return gameName;
    }

    public void setResult(String newResult) {
        this.gameResult = newResult;
    }

    private String username;
    private String gameResult;
    private final double bet;
    private final double accountChange;
    private final String gameName;
    private String date;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Statistic(String result, double bet, double amount, String gameName) {
        this.gameResult = result;
        this.bet = bet;
        this.accountChange = amount;
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Stat{" + "username=" + username + ", result=" + gameResult + ", bet=" + bet + ", Endamount=" + accountChange + ", gameName=" + gameName + '}';
    }

    public String getResult() {
        return gameResult;
    }

    public double getBet() {
        return bet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUserAccountChange() {
        return accountChange;
    }

}
