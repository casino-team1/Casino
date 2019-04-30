/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team1.casino.Controller;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.UserCentral;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Schule
 */
public class ExchangeFXMLController implements Initializable {

    private MainApp mainApplication;
    @FXML
    private TextField jetonsLabel;
    @FXML
    private TextField moneyLabel;
    @FXML
    private Label moneyToJetonLabel;
    @FXML
    private Label jetonToMoneyLabel;
    @FXML
    private Label acceptButton;
    @FXML
    private Button backButton;
    @FXML
    private Label balanceLabel;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        balanceLabel.setText("Konto: " + UserCentral.getInstance().getUser().getCurrentBalance());
    }    
    
    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void pressBackButton(ActionEvent event) {
        this.mainApplication.displayMainMenu();
    }
    
}
