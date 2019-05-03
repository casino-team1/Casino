/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.Entity.MailUtil;
import com.team1.casino.MainApp;
import com.team1.casino.User.Util.PlayerCentral;
import com.team1.casino.User.Util.UserUtil;
import com.team1.casino.User.Spieler;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public MainApp getMainApplicatin() {
        return mainApplicatin;
    }

    public SimpleStringProperty getEmailAdress() {
        return emailAdress;
    }

    private SimpleStringProperty emailAdress = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public void registerNewUser() {
        PlayerCentral center = PlayerCentral.getInstance();
        center.setUser(new Spieler(username.getValue(), password.getValue()));
        boolean isUniqueUsername = new UserUtil().isUniqueUsername(username.getValue());
        if (isUniqueUsername == false) {
            this.errorMessage = "Nutzername ist bereits vergeben.";
            setChanged();
            notifyObservers();
        } else {
            if (isValidEmail(this.emailAdress.getValue()) == true) {
                new MailUtil(this.emailAdress.getValue(), PlayerCentral.getInstance().getUser().getValidationCode(), "").sendRegistrationMail();
                PlayerCentral.getInstance().getUser().setEmailAdress(this.emailAdress.getValue());
                this.mainApplicatin.displayAuthenticationWindow();
            } else {
                this.errorMessage = "Ungültige Email-Adresse";
                setChanged();
                notifyObservers();
            }
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
