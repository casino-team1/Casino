/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.UserCentral;
import java.util.Observable;

/**
 *
 * @author Nick Flückiger
 */
public class CasinoModel extends Observable {

    private MainApp mainApplication;

    public CasinoModel(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void startBaccara() {
        this.mainApplication.startBaccara();
    }

    public void startRoulette() {
        this.mainApplication.startRoulette();
    }

    public void startYatzy() {
        this.mainApplication.startYatzy();
    }

    public void startBlackJack() {
        this.mainApplication.startBlackJack();
    }

    public void logOutUser() {
        UserCentral.getInstance().setUser(null);
        this.mainApplication.displayLoginView();
    }
}
