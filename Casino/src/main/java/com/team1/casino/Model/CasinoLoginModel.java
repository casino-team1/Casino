/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.MainApp;
import com.team1.casino.User.User;
import com.team1.casino.User.Util.UserUtil;
import com.team1.casino.User.Spieler;
import com.team1.casino.User.Util.UserCentral;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class CasinoLoginModel {

    private User currentPlayer;
    private MainApp mainApplication;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

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
            return "Gegebener Nutzernamen ist nicht vergeben";
        }
        if (currentPlayer.isValidUser() == false) {
            return "Gegebens Passwort ist ungültig.";
        }
        currentPlayer.loadUserInformation();
        UserCentral.getInstance().setUser(currentPlayer);
        return "Valid user";
    }

    public void displayRegistrationView() {
        this.mainApplication.displayRegistrationView();
    }

    public void displayMainMenu() {
        if (UserCentral.getInstance().getUser() != null) {
            if (UserCentral.getInstance().getUser().getRole().equals("Admin")) {
                this.mainApplication.displayStatisticView();
            } else {
                this.mainApplication.displayMainMenu();
            }
        } else {
            this.mainApplication.displayLoginView();
        }
    }

    public void forgotPassword() {

    }

    public void displayPasswordRecovery() {
        this.mainApplication.displayPasswordRecovery();
    }

}
