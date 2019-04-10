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
public class Stat {

    private String result;
    private double bet;
    private double Endamount;

    public Stat(String result, double bet, double amount) {
        this.result = result;
        this.bet = bet;
        this.Endamount = amount;
    }

    @Override
    public String toString() {
        return "Stat{" + "result=" + result + ", bet=" + bet + ", Endamount=" + Endamount + '}';
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
