/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
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
 * @author albio
 */
public class BlackJackFXMLController implements Initializable {

    private String[] kartenSpieler;
    private int kartenWertSpieler;
    private boolean spielerGewonnen = false;

    private int einsatz;

    private int anzahlKartenImKartendeck = 52;

    private String[] kartenDealer;
    private int kartenWertDealer;
    private int karteZweiWert;
    private boolean dealerGewonnen = false;

    private Karten k = new Karten();

    private HashMap<String, Integer> karten = new HashMap<>();

    private ArrayList<String> kartenWerte = new ArrayList<>();

    Random r = new Random();

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
    private Button buttonStart1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void stand(ActionEvent event) {
        if (spielerGewonnen) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealerGewonnen) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }
    }

    @FXML
    private void hit(ActionEvent event) {
        if (spielerGewonnen) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealerGewonnen) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }
    }

    @FXML
    private void startGame(ActionEvent event) {

        spielerGewonnen = false;
        dealerGewonnen = false;

        labelKartenDealer.setText("");
        labelKartenSpieler.setText("");
        labelLösung.setText("");

        buttonHit.setDisable(true);
        this.karten = k.getKarten();
        this.kartenWerte = k.getKartenWerte();
        String zufallskarte = "";
        int zufallszahl;

        //Karten mischen
        Collections.shuffle(kartenWerte);

        //Zufallskarten verteilen an Spieler
        for (int i = 0; i < 2; i++) {
            zufallszahl = r.nextInt(51);
            zufallskarte = kartenWerte.get(zufallszahl);
            try {
                kartenWertSpieler += Integer.parseInt(zufallskarte);
            } catch (Exception e) {
                if (zufallskarte == "J" || zufallskarte == "Q" || zufallskarte == "Q") {
                    kartenWertSpieler += 10;
                }
                if (zufallskarte == "A") {
                    kartenWertSpieler += 10;
                }
            }
            kartenWerte.remove(zufallszahl);
            anzahlKartenImKartendeck--;
            labelKartenSpieler.setText(zufallskarte);
        }

        //Zufallskarten verteilen an Dealer
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        try {
            kartenWertDealer += Integer.parseInt(zufallskarte);
        } catch (Exception e) {
            if (zufallskarte == "J" || zufallskarte == "Q" || zufallskarte == "Q") {
                kartenWertDealer += 10;
            }
            if (zufallskarte == "A") {
                kartenWertDealer += 10;
            }
        }
        kartenWerte.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenDealer.setText(zufallskarte+" + ?"); 
        
        //Zweite unbekannte Karte von Dealer
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        try {
            kartenWertDealer += Integer.parseInt(zufallskarte);
        } catch (Exception e) {
            if (zufallskarte == "J" || zufallskarte == "Q" || zufallskarte == "Q") {
                kartenWertDealer += 10;
            }
            if (zufallskarte == "A") {
                kartenWertDealer += 10;
            }
        }
        kartenWerte.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        

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
}
