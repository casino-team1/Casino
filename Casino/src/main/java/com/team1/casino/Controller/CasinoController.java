package com.team1.casino.Controller;

import com.team1.casino.Entity.PasswordChanger;
import com.team1.casino.Model.CasinoModel;
import com.team1.casino.User.Util.UserCentral;
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

    private Label label;

    private CasinoModel casinoModel;
    @FXML
    private Button logOutButton;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button changePasswordButton;

    public void setCasinoModel(CasinoModel model) {
        this.casinoModel = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        balanceLabel.setText("Konto: " + UserCentral.getInstance().getUser().getCurrentBalance());
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
    private void changePassword(ActionEvent event) {
        PasswordChanger changer = new PasswordChanger();
        boolean isValidChange = changer.displayDialog();
        if (isValidChange == true) {
            String newPassword = changer.getNewPassword();
            if (newPassword.equals(UserCentral.getInstance().getUser().getPassword()) == false) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        UserCentral.getInstance().getUser().setNewPassword(UserUtil.getHashedPassword(newPassword), newPassword);
                    }
                };
                changer.displayMessage("You'r new password", String.format("You have changed your password to: %s", newPassword));
            } else {
                changer.displayMessage("That looks a lot a like", "You can't change the password to the password itself, please use a different one");
            }
        }
    }
}
