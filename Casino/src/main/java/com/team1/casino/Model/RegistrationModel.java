/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.Entity.MailUtil;
import com.team1.casino.MainApp;
import com.team1.casino.User.UserCentral;
import com.team1.casino.User.UserUtil;
import com.team1.casino.User.Spieler;
import java.util.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class RegistrationModel extends Observable {

    private String errorMessage;

    private MainApp mainApplicatin;

    public RegistrationModel(MainApp mainApplicatin) {
        this.mainApplicatin = mainApplicatin;
    }

    public void backToLogin() {
        this.mainApplicatin.displayLoginView();
    }

    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getPassword() {
        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public void registerNewUser() {
        UserCentral center = UserCentral.getInstance();
        center.setUser(new Spieler(username.getValue(), password.getValue()));
        boolean isUniqueUsername = new UserUtil().isUniqueUsername(username.getValue());
        if (isUniqueUsername == false) {
            this.errorMessage = "Nutzername ist bereits vergeben.";
            setChanged();
            notifyObservers();
        } else {
            new MailUtil("nick.flueckiger@outlook.de", UserCentral.getInstance().getUser().getValidationCode(), "").sendMail();
            this.mainApplicatin.displayAuthenticationWindow();
        }
    }
}
