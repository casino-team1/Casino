/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.Player.Player;
import com.team1.casino.Player.PlayerUtil;
import com.team1.casino.Player.Spieler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class CasinoLoginModel {

    private Player currentPlayer;

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
        PlayerUtil playerUtil = new PlayerUtil();
        if (playerUtil.isUniqueUsername(currentPlayer.getUsername()) == false) {
            return "Gegebener Nutzernamen ist nicht vergeben";
        }
        if (playerUtil.isValidPlayer(currentPlayer) == false) {
            return "Gegebens Passwort ist ungültig.";
        }
        return "Valid user";
    }

}
