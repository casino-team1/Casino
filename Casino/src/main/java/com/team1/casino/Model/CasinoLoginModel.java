/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.UserUtil;
import com.team1.casino.User.Spieler;
import com.team1.casino.User.Util.PlayerCentral;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class CasinoLoginModel {

    private Spieler currentPlayer;
    private MainApp mainApplication;

    public CasinoLoginModel() {
    }
    

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public StringProperty getUsernameProperty() {
        return username;
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    public String loginUser() {
        this.currentPlayer = new Spieler(username.getValue(), password.getValue());
        UserUtil playerUtil = new UserUtil();
        if (playerUtil.isUniqueUsername(currentPlayer.getUsername()) == true) {
            return "Gegebener Nutzername ist nicht vergeben";
        }
        if (currentPlayer.isValidPlayer() == false) {
            return "Gegebenes Passwort ist ungültig.";
        }
        currentPlayer.loadUserInformation();
        PlayerCentral.getInstance().setUser(currentPlayer);
        return "Valid user";
    }

    public void displayRegistrationView() {
        this.mainApplication.displayRegistrationView();
    }

    public void displayMainMenu() {
        if (PlayerCentral.getInstance().getUser() != null) {
            if (PlayerCentral.getInstance().getUser().getRole().equals("Admin")) {
                this.mainApplication.displayStatisticView();
            } else {
                this.mainApplication.displayMainMenu();
            }
        } else {
            this.mainApplication.displayLoginView();
        }
    }

    public void displayPasswordRecovery() {
        this.mainApplication.displayPasswordRecovery();
    }
    
    

}
