/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller;

import com.team1.casino.Model.RegistrationModel;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class RegistrationViewController implements Initializable, Observer {

    @FXML
    private Button registerButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    private RegistrationModel model;
    @FXML
    private Text errorMessage;
    @FXML
    private Button backButton;
    @FXML
    private TextField emailAdress;

    public void setModel(RegistrationModel model) {
        this.model = model;
        bind();
    }

    private void bind() {
        this.model.getPassword().bind(this.password.textProperty());
        this.model.getUsername().bind(this.username.textProperty());
        this.model.getEmailAdress().bind(this.emailAdress.textProperty());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.errorMessage.setVisible(false);
    }

    @FXML
    private void registerUser(ActionEvent event) {
        this.errorMessage.setVisible(false);
        this.model.registerNewUser();
    }

    @Override
    public void update(Observable o, Object arg) {
        RegistrationModel model = (RegistrationModel) o;
        this.errorMessage.setText(model.getErrorMessage());
        this.errorMessage.setVisible(true);
        this.password.setText("");
        this.username.setText("");
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        this.model.backToLogin();
    }
}
