package com.team1.casino.Controller;

import com.team1.casino.Entity.PasswordChanger;
import com.team1.casino.Model.CasinoModel;
import com.team1.casino.User.Util.PlayerCentral;
import com.team1.casino.User.Util.UserUtil;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CasinoController implements Initializable, Observer {
    
    private CasinoModel casinoModel;
    @FXML
    private Button logOutButton;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button kasseButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Label usernameLabel;
    
    public void setCasinoModel(CasinoModel model) {
        this.casinoModel = model;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChipBalance());
        this.usernameLabel.setText(String.format("Wilkommen %s", PlayerCentral.getInstance().getUser().getUsername()));
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void mouseClickBlackJack(MouseEvent event) {
        this.casinoModel.startBlackJack();
    }
    
    @FXML
    private void mouseClickBaccara(MouseEvent event) {
        this.casinoModel.startBaccara();
    }
    
    @FXML
    private void clickMouseRoulette(MouseEvent event) {
        this.casinoModel.startRoulette();
    }
    
    @FXML
    private void clickMouseYatzy(MouseEvent event) {
        this.casinoModel.startYatzy();
    }
    
    @FXML
    private void logUserOut(ActionEvent event) {
        this.casinoModel.logOutUser();
    }
    
    @FXML
    private void pressKasseButton(ActionEvent event) {
        this.casinoModel.displayExchange();
    }
    
    @FXML
    private void changePassword(ActionEvent event) {
        PasswordChanger changer = new PasswordChanger();
        boolean isValidChange = changer.displayDialog();
        if (isValidChange == true) {
            String newPassword = changer.getNewPassword();
            if (newPassword.equals(PlayerCentral.getInstance().getUser().getPassword()) == false) {
                //Run a new thread that changes the password, even if the programm is terminated.
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        PlayerCentral.getInstance().getUser().setNewPassword(UserUtil.getHashedPassword(newPassword), newPassword);
                    }
                };
                thread.start();
                changer.displayMessage("Ihr neues Passwort", String.format("Sie haben Ihr Passwort zu: %s geändert", newPassword));
            } else {
                changer.displayMessage("Das scheint mir aber sehr verwandt", "Sie können nicht das gleiche Passwort setzen, welches Sie vorher bereits hatten");
            }
        }
    }
    
    @FXML
    private void exitLogOutButton(MouseEvent event) {
        logOutButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }
    
    @FXML
    private void enterLogOutButton(MouseEvent event) {
        logOutButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }
    
    @FXML
    private void exitKasseButton(MouseEvent event) {
        kasseButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }
    
    @FXML
    private void enterKasseButton(MouseEvent event) {
        kasseButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }
    
    @FXML
    private void exitChangePasswordButton(MouseEvent event) {
        changePasswordButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }
    
    @FXML
    private void enterChangePasswordButton(MouseEvent event) {
        changePasswordButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }
}
