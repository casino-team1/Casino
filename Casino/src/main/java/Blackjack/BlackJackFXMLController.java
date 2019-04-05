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

    private String zufallskarte = "";
    private int zufallszahl = 0;

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
    private Button buttonPrüfung;

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
        //Karte ziehen
        if (anzahlKartenImKartendeck < 1) {
            this.karten = k.getKarten();
        }
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);

        kartenWertSpieler += karten.get(zufallskarte);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
            kartenWertSpieler += 10;
        }
        if (zufallskarte.equals("A")) {
            kartenWertSpieler += 10;
        }

        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);

        //Überprüfung, ob 21 überschritten wurde
        if (kartenWertSpieler > 21) {
            dealerGewonnen = true;
        }

        if (spielerGewonnen) {              
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealerGewonnen) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }
    }

    @FXML
    private void startGame(ActionEvent event) {
        //Vorbereitung
        buttonStart.setDisable(true);

        kartenWertSpieler = 0;
        kartenWertDealer = 0;
        karteZweiWert = 0;

        spielerGewonnen = false;
        dealerGewonnen = false;

        labelKartenDealer.setText("");
        labelKartenSpieler.setText("");
        labelLösung.setText("");

        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        this.karten = k.getKarten();
        this.kartenWerte = k.getKartenWerte();

        //Karten mischen
        Collections.shuffle(kartenWerte);

        //Zufallskarten verteilen an Spieler
        for (int i = 0; i < 2; i++) {
            zufallszahl = r.nextInt(51);
            zufallskarte = kartenWerte.get(zufallszahl);

            kartenWertSpieler += karten.get(zufallskarte);

            if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
                kartenWertSpieler += 10;
            }
            if (zufallskarte.equals("A")) {
                kartenWertSpieler += 10;
            }

            karten.remove(zufallszahl);
            anzahlKartenImKartendeck--;
            labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);
        }

        //Erste Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);

        kartenWertDealer += karten.get(zufallskarte);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
            kartenWertDealer += 10;
        }
        if (zufallskarte.equals("A")) {
            kartenWertDealer += 10;
        }

        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenDealer.setText(zufallskarte + " + ?");

        //Zweite unbekannte Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);

        kartenWertDealer += karten.get(zufallskarte);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
            kartenWertDealer += 10;
        }
        if (zufallskarte.equals("A")) {
            kartenWertDealer += 10;
        }

        karten.remove(zufallszahl);
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
