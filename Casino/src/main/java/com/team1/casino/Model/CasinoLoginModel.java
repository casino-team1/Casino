/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.MainApp;
import com.team1.casino.User.User;
import com.team1.casino.User.UserUtil;
import com.team1.casino.User.Spieler;
import com.team1.casino.User.UserCentral;
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

        return "Valid user";
    }

    public void registerWindow() {
        this.mainApplication.displayRegistrationView();
    }

    public void displayMainMenu() {
        if (UserCentral.getInstance().getUser() != null) {
            System.out.println(UserCentral.getInstance());
            if (UserCentral.getInstance().getUser().getRole().equals("Admin")) {
                this.mainApplication.displayStatisticView();
            } else {
                this.mainApplication.displayMainMenu();
            }
        } else {
            this.mainApplication.displayMainMenu();
        }
    }

}
