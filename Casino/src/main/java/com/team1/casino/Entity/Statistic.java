/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Entity;

/**
 *
 * @author Nick Flückiger
 */
public class Statistic {

    public String getGameName() {
        return gameName;
    }

    private String username;
    private String result;
    private double bet;
    private double Endamount;
    private String gameName;

    public void setUseranme(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Statistic(String result, double bet, double amount, String gameName) {
        this.result = result;
        this.bet = bet;
        this.Endamount = amount;
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "Stat{" + "username=" + username + ", result=" + result + ", bet=" + bet + ", Endamount=" + Endamount + ", gameName=" + gameName + '}';
    }

    public String getResult() {
        return result;
    }

    public double getBet() {
        return bet;
    }

    public double getEndamount() {
        return Endamount;
    }

}
