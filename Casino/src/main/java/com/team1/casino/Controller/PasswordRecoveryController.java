/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller;

import com.team1.casino.Entity.PasswordRecovery;
import com.team1.casino.MainApp;
import com.team1.casino.Model.AuthenticationModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class PasswordRecoveryController implements Initializable {

    private AuthenticationModel model;
    @FXML
    private Button passwordRecoveryButton;
    @FXML
    private TextField username;

    private MainApp mainApplication;
    @FXML
    private Button goBackButton;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sendUserANewPassword(ActionEvent event) {
        PasswordRecovery recovery = new PasswordRecovery(username.getText());
        recovery.handlePasswordRecovery();
        this.mainApplication.displayLoginView();
    }

    @FXML
    private void goToLoginView(MouseEvent event) {
        this.mainApplication.displayMainMenu();
    }

    @FXML
    private void exitPasswordRecoveryButton(MouseEvent event) {
        passwordRecoveryButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void enterPasswordRecoveryButton(MouseEvent event) {
        passwordRecoveryButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void exitGoBackButton(MouseEvent event) {
        goBackButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void enterGoBackButton(MouseEvent event) {
        goBackButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }

}
