/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino;

/**
 *
 * @author Nick Flückiger
 */
public abstract class Spiel {

    private MainApp mainApplication;

    public Spiel(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    public abstract void startGame();
    
    public MainApp getMainApp(){
        return this.mainApplication;
    }
}
