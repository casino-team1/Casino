/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller;

import com.team1.casino.Model.AuthenticationModel;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
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
public class AuthenticationController implements Initializable, Observer {

    private AuthenticationModel model;
    @FXML
    private Button submitButton;
    @FXML
    private TextField accessCodeInput;
    @FXML
    private Button goBackButton;

    public void setAuthenticationModel(AuthenticationModel model) {
        this.model = model;
        this.model.getUserInputCode().bindBidirectional(this.accessCodeInput.textProperty());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void checkAccessCode(ActionEvent event) {
        this.model.checkAccessCode();
    }

    @Override
    public void update(Observable o, Object arg) {
        AuthenticationModel model = (AuthenticationModel) o;
        String errorMessage = model.getErrorMessage();
    }

    @FXML
    private void goToLoginView(MouseEvent event) {
        this.model.RegistrationView();
    }

}
