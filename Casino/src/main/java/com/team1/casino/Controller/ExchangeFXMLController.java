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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Schule
 */
public class ExchangeFXMLController implements Initializable {

    private MainApp mainApplication;
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
    @FXML
    private TextField jetonsField;
    @FXML
    private TextField moneyField;
    
    
    private boolean locked = false;
    private boolean moneyfieldlocked = false;
    private boolean jetonsfieldlocked = false;
    private double jetoncalc = 0;
    private double moneycalc = 0;
            

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //balanceLabel.setText("Konto: " + UserCentral.getInstance().getUser().getCurrentBalance());
        
        moneyField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (moneyfieldlocked == false) {
                    if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                        moneyField.setText(oldValue);
                    }
                
                    else {
                        jetonsField.setText(Integer.toString(Integer.parseInt(moneyField.getText()) * 100));
                        acceptButton.setDisable(false);
                    }
                }
            }
        });
        
        
        jetonsField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (jetonsfieldlocked == false) {
                    if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                        jetonsField.setText(oldValue);
                    }
                
                    else {     
                        moneyField.setText(Double.toString(Double.parseDouble(jetonsField.getText()) / 100));
                        acceptButton.setDisable(false);
                    }
                }
            }
        });
        balanceLabel.setText("Konto: " + UserCentral.getInstance().getUser().getCurrentChipBalance());
    }

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void pressBackButton(ActionEvent event) {
        this.mainApplication.displayMainMenu();
    }    

    @FXML
    private void typeJetonsField(KeyEvent event) {
        
        //moneyField.setText(Integer.toString(Integer.parseInt(jetonsField.getText()) / 100));
    }

    @FXML
    private void typeMoneyField(KeyEvent event) {
        
        //jetonsField.setText(Integer.toString(Integer.parseInt(moneyField.getText()) * 100));
    }
    

    @FXML
    private void exitGJLabel(MouseEvent event) {
        if (locked == false) {
            moneyToJetonLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
        }
    }

    @FXML
    private void enterGJLabel(MouseEvent event) {
        if (locked == false) {
            moneyToJetonLabel.setStyle("-fx-border-width: 4; -fx-background-color: white; -fx-border-color: black;");
        }
    }

    @FXML
    private void pressGJLabel(MouseEvent event) {
        locked = true;
        moneyField.setDisable(false);
        jetonsField.setDisable(true);
        acceptButton.setDisable(true);
        jetonsfieldlocked = true;
        moneyfieldlocked = false;
        moneyField.setText("");
        jetonsField.setText("");
        moneyToJetonLabel.setStyle("-fx-border-width: 4; -fx-background-color: lightgrey; -fx-border-color: black;");
        jetonToMoneyLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
    }

    @FXML
    private void exitJGLabel(MouseEvent event) {
        if (locked == false) {
            jetonToMoneyLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
        }
    }

    @FXML
    private void enterJGLabel(MouseEvent event) {
        if (locked == false) {
            jetonToMoneyLabel.setStyle("-fx-border-width: 4; -fx-background-color: white; -fx-border-color: black;");
        }
    }

    @FXML
    private void pressJGLabel(MouseEvent event) {
        locked = true;
        moneyField.setDisable(true);
        jetonsField.setDisable(false);
        acceptButton.setDisable(true);
        jetonsfieldlocked = false;
        moneyfieldlocked = true;
        moneyField.setText("");
        jetonsField.setText("");
        moneyToJetonLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
        jetonToMoneyLabel.setStyle("-fx-border-width: 4; -fx-background-color: lightgrey; -fx-border-color: black;");
    }

    @FXML
    private void pressAcceptButton(KeyEvent event) {
        if (jetonsField.isDisabled() == false) {
            //jetoncalc = UserCentral.getInstance().getUser().getCurrentBalance() + Integer.parseInt(jetonsField.getText());
            //moneycalc = UserCentral.getInstance().getUser().getCurrentMoney() - Integer.parseInt(moneyField.getText());
        }
        
        
        
        else if (moneyField.isDisabled() == false) {
            //jetoncalc = UserCentral.getInstance().getUser().getCurrentBalance() - Integer.parseInt(jetonsField.getText());
            //moneycalc = UserCentral.getInstance().getUser().getCurrentMoney() + (int)(Math.round(Double.parseDouble(moneyField.getText())));
        }
        
        //UserCentral.getInstance().getUser().setCurrentBalance(jetoncalc);
        //UserCentral.getInstance().getUser().setCurrentMoney(moneycalc);
        
        
        locked = false;
        moneyField.setDisable(true);
        jetonsField.setDisable(true);
        acceptButton.setDisable(true);
        moneyField.setText("");
        jetonsField.setText("");
        moneyToJetonLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
        jetonToMoneyLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
    }

    @FXML
    private void exitAcceptButton(MouseEvent event) {
        acceptButton.setStyle("-fx-border-width: 3; -fx-background-color: #01DF01");
    }

    @FXML
    private void enterAcceptButton(MouseEvent event) {
        acceptButton.setStyle("-fx-border-width: 4; -fx-background-color: green");

    } 
}