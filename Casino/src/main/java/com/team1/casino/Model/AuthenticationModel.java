/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.PlayerCentral;
import java.util.Observable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class AuthenticationModel extends Observable {

    public AuthenticationModel(String accessCodeNeeded) {
        this.givenCode = accessCodeNeeded;
    }

    private MainApp mainApplication;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    private String errorMessage;

    private final String givenCode;

    private final SimpleStringProperty userInputCode = new SimpleStringProperty();

    public String getGivenCode() {
        return givenCode;
    }

    public SimpleStringProperty getUserInputCode() {
        return userInputCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void checkAccessCode() {
        String accessCode = this.userInputCode.getValue();
        if (accessCode.trim().equals(this.givenCode.trim())) {
            PlayerCentral.getInstance().getUser().writeUserToDatabase();
            PlayerCentral.getInstance().getUser().loadUserInformation();
            this.mainApplication.displayMainMenu();
        } else {
            System.out.println("invalid");
            this.errorMessage = "Ungülteriger Zugangscode, bitte versuchen sie es erneut";
            setChanged();
            notifyObservers();
        }
    }
}
