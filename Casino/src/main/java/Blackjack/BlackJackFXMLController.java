/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.MainApp;
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

    BlackJackGameModel game = new BlackJackGameModel();

    private MainApp main;

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
    private Button buttonVerdoppeln;
    @FXML
    private Button buttonVersichern;
    @FXML
    private TextField textfeldVersicherung;
    @FXML
    private Label labelVerdoppeln;
    @FXML
    private Label labelVersicherung;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void stand(ActionEvent event) {
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        game.dealerRound(labelLösung, labelKartenDealer, buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, textfeldEinsatz, textfeldVersicherung);
    }

    @FXML
    private void hit(ActionEvent event) {
        game.spielerHit(labelLösung, buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelKartenDealer, labelKartenSpieler, textfeldEinsatz, textfeldVersicherung);
    }

    @FXML
    private void startGame(ActionEvent event) {
        //Vorbereitung
        labelKartenSpieler.setText("");
        labelKartenDealer.setText("");
        labelLösung.setText("");
        labelEinsatzFehler.setText("");
        buttonStart.setDisable(true);
        textfeldEinsatz.setDisable(true);
        buttonPrüfung.setDisable(true);
        buttonHit.setDisable(false);
        buttonStand.setDisable(false);
        buttonVerdoppeln.setDisable(true);
        buttonVersichern.setDisable(true);
        textfeldVersicherung.setDisable(false);

        game.play(labelKartenSpieler, labelKartenDealer, buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelLösung, textfeldEinsatz, textfeldVersicherung);
    }

    @FXML
    private void prüfungEinsatz(ActionEvent event) {
        try {
            einsatz = Integer.parseInt(textfeldEinsatz.getText());
            if (einsatz < 50) {
                labelEinsatzFehler.setText("Ihr Einsatz ist zu klein -> mind. 50!");
            } else {
                labelEinsatzFehler.setText("Viel Spass beim Spielen!");
                buttonStart.setDisable(false);
                buttonPrüfung.setDisable(true);
                textfeldVersicherung.setDisable(true);
            }
        } catch (NumberFormatException e) {
            labelEinsatzFehler.setText("Bitte ganze Zahlen einsetzen!!");
        }
    }

    @FXML
    private void zurueck(ActionEvent event) throws IOException {
        this.main.displayMainMenu();
    }

    public void setMain(MainApp main) {
        this.main = main;
    }

    @FXML
    private void help(ActionEvent event) {
    }

    @FXML
    private void verdoppeln(ActionEvent event) {
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldEinsatz.setDisable(true);
        textfeldVersicherung.setDisable(true);

        int i = Integer.parseInt(textfeldEinsatz.getText());
        String s = String.valueOf(i * 2);
        labelVerdoppeln.setText("Ihr Einsatz wurde erhöht auf " + s);
        game.spielerHit(labelLösung, buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelKartenDealer, labelKartenSpieler, textfeldEinsatz, textfeldVersicherung);
        game.dealerRound(labelLösung, labelKartenDealer, buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, textfeldEinsatz, textfeldVersicherung);
    }

    @FXML
    private void versichern(ActionEvent event) {

        try {
            int i;
            i = Integer.parseInt(textfeldVersicherung.getText());
            if (i > 0 || i % 2 == 1) {
                buttonHit.setDisable(true);
                buttonStand.setDisable(true);
                buttonVersichern.setDisable(true);
                buttonVerdoppeln.setDisable(true);
                textfeldEinsatz.setDisable(true);
                textfeldVersicherung.setDisable(true);

                game.versichern(labelLösung, labelKartenDealer, buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, textfeldEinsatz, textfeldVersicherung);
            } else {
                labelVersicherung.setText("Bitte ganze Zahlen über 0 eingeben!");
            }
        } catch (Exception e) {
            labelVersicherung.setText("Bitte ganze Zahlen über 0 eingeben!");
        }
    }
}
