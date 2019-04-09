/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author albio
 */
public class BlackJackFXMLController implements Initializable {

    BlackJackGameModel game;

    private int einsatz;

    @FXML
    private Button buttonStand;
    @FXML
    private Button buttonHit;
    @FXML
    private Label labelKartenSpieler;
    @FXML
    private Label labelKartenDealer;
    @FXML
    private Button buttonStart;
    @FXML
    private Label labelLösung;
    @FXML
    private TextField textfeldEinsatz;
    @FXML
    private Label labelEinsatzFehler;
    @FXML
    private Button buttonPrüfung;
    @FXML
    private Button buttonVerlassen;
    @FXML
    private Button buttonHelp;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void stand(ActionEvent event) {
        game.dealerRound(labelLösung, labelKartenDealer, buttonHit, buttonStand);
    }

    @FXML
    private void hit(ActionEvent event) {
        game.spielerHit(labelLösung, buttonHit, buttonStand, labelKartenDealer);
    }

    @FXML
    private void startGame(ActionEvent event) {
        //Vorbereitung
        labelKartenSpieler.setText("");
        labelKartenDealer.setText("");
        labelLösung.setText("");
        labelEinsatzFehler.setText("");
        
        game.play(labelKartenSpieler, labelKartenDealer);
        buttonHit.setDisable(false);
        buttonStand.setDisable(false);
    }

    @FXML
    private void prüfungEinsatz(ActionEvent event) {
        try {
            einsatz = Integer.parseInt(textfeldEinsatz.getText());
        } catch (Exception e) {
            labelEinsatzFehler.setText("Sie haben nichts eingesetzt!");
        }

        if (einsatz < 50) {
            labelEinsatzFehler.setText("Ihr Einsatz ist zu klein -> mind. 50!");
        } else {
            labelEinsatzFehler.setText("Viel Spass beim Spielen!");
            buttonStart.setDisable(false);
        }
    }

    @FXML
    private void zurueck(ActionEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        rootPane.getChildren().setAll(pane);*/
    }

    @FXML
    private void help(ActionEvent event) {
    }
}
