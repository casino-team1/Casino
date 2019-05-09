/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team1.casino.Controller;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.PlayerCentral;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

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
    private Label balanceLabel;
    @FXML
    private Label moneyLabel;
    @FXML
    private TextField jetonsField;
    @FXML
    private TextField moneyField;

    private boolean locked = false;
    private boolean moneyfieldlocked = false;
    private boolean jetonsfieldlocked = false;
    private boolean nothundred = false;
    private boolean insufficient = false;
    private int jetoncalc = 0;
    private int moneycalc = 0;
    @FXML
    private Label errorJetonsLabel;
    @FXML
    private Label errorMoneyLabel;
    @FXML
    private Button btnback;

    /**
     * Initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        balanceLabel.setText(Integer.toString((int) PlayerCentral.getInstance().getUser().getCurrentChipBalance()));
        moneyLabel.setText(Integer.toString((int) PlayerCentral.getInstance().getUser().getCurrentMoney()));

        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/SqueakyChalkSound.ttf"), 14);
        balanceLabel.setStyle("-fx-font-family: 'squeaky chalk sound';");
        moneyLabel.setStyle("-fx-font-family: 'squeaky chalk sound';");

        moneyField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (moneyfieldlocked == false) {
                if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                    moneyField.setText(oldValue);
                } else {
                    if (moneyField.getText().equals("")) {
                    } else {
                        jetonsField.setText(Integer.toString(Integer.parseInt(moneyField.getText()) * 100));
                    }
                }
            }
        });

        jetonsField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (jetonsfieldlocked == false) {
                if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                    jetonsField.setText(oldValue);
                } else {
                    if (jetonsField.getText().equals("")) {
                    } else {
                        moneyField.setText(Integer.toString((int) Math.round(Double.parseDouble(jetonsField.getText()) / 100)));
                    }
                }
            }
        });
        balanceLabel.setText(Integer.toString((int) PlayerCentral.getInstance().getUser().getCurrentChipBalance()));
    }

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    private void pressBackButton(ActionEvent event) {
        PlayerCentral.getInstance().getUser().setNewChipBalance(5000);
        this.mainApplication.displayMainMenu();
    }

    @FXML
    private void typeJetonsField(KeyEvent event) {
        if (jetonsField.getText().equals("") == false) {
            moneyField.setText(Integer.toString(Integer.parseInt(jetonsField.getText()) / 100));
        }
    }

    @FXML
    private void typeMoneyField(KeyEvent event) {
        if (!"".equals(moneyField.getText())) {
            jetonsField.setText(Integer.toString(Integer.parseInt(moneyField.getText()) * 100));
        }
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
        acceptButton.setDisable(false);
        jetonsfieldlocked = true;
        moneyfieldlocked = false;
        moneyField.setText("");
        jetonsField.setText("");
        errorJetonsLabel.setText("");
        errorMoneyLabel.setText("");
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
        acceptButton.setDisable(false);
        jetonsfieldlocked = false;
        moneyfieldlocked = true;
        moneyField.setText("");
        jetonsField.setText("");
        errorJetonsLabel.setText("");
        errorMoneyLabel.setText("");
        moneyToJetonLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
        jetonToMoneyLabel.setStyle("-fx-border-width: 4; -fx-background-color: lightgrey; -fx-border-color: black;");
    }

    private void processAcception() {
        nothundred = false;
        if (jetonsField.isDisabled() == true) {
            if ("".equals(moneyField.getText())) {
                errorJetonsLabel.setText("Bitte geben Sie eine Zahl ein");
            } else {
                jetoncalc = (int) PlayerCentral.getInstance().getUser().getCurrentChipBalance() + Integer.parseInt(jetonsField.getText());
                moneycalc = (int) PlayerCentral.getInstance().getUser().getCurrentMoney() - Integer.parseInt(moneyField.getText());
            }
        } else {
            if ("".equals(jetonsField.getText())) {
                errorJetonsLabel.setText("Bitte geben Sie eine Zahl ein");
            } else if (Integer.parseInt(jetonsField.getText()) < 100) {
                errorJetonsLabel.setText("Die Zahl muss höher als 100 sein");
                nothundred = true;
            } else {
                jetoncalc = (int) PlayerCentral.getInstance().getUser().getCurrentChipBalance() - Integer.parseInt(jetonsField.getText());
                moneycalc = (int) PlayerCentral.getInstance().getUser().getCurrentMoney() + (int) (Math.floor(Double.parseDouble(moneyField.getText())));
            }
        }
        if (nothundred == false) {
            errorJetonsLabel.setText("");
            if (moneycalc < 0) {
                errorMoneyLabel.setText("Zu wenig Geld");
                insufficient = true;
            } else if (jetoncalc < 0) {
                errorJetonsLabel.setText("Zu wenig Jetons");
                insufficient = true;
            } else {
                errorJetonsLabel.setText("");
                errorMoneyLabel.setText("");
                insufficient = false;
            }
            if (insufficient == false) {
                //Update player if entries are valid and player is not insufficient
                PlayerCentral.getInstance().getUser().setNewChipBalance(jetoncalc);
                PlayerCentral.getInstance().getUser().setNewMoney(moneycalc);
                balanceLabel.setText(Integer.toString((int) PlayerCentral.getInstance().getUser().getCurrentChipBalance()));
                moneyLabel.setText(Integer.toString((int) PlayerCentral.getInstance().getUser().getCurrentMoney()));
                locked = false;
                moneyField.setDisable(true);
                jetonsField.setDisable(true);
                acceptButton.setDisable(true);
                moneyField.setText("");
                jetonsField.setText("");
                moneyToJetonLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
                jetonToMoneyLabel.setStyle("-fx-border-width: 3; -fx-background-color: white; -fx-border-color: black;");
            }   
        }
    }

    @FXML
    private void exitAcceptButton(MouseEvent event) {
        acceptButton.setStyle("-fx-border-width: 3; -fx-background-color: #01DF01");
    }

    @FXML
    private void enterAcceptButton(MouseEvent event) {
        acceptButton.setStyle("-fx-border-width: 5; -fx-background-color: green");

    }

    @FXML
    private void pressAcceptButton(KeyEvent event) {
        processAcception();
    }

    @FXML
    private void clickedAcceptButton(MouseEvent event) {
        processAcception();
    }

    @FXML
    private void exitbtnback(MouseEvent event) {
        btnback.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void enterbtnback(MouseEvent event) {
        btnback.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void pressbtnback(ActionEvent event) {
        this.mainApplication.displayMainMenu();
    }

}
