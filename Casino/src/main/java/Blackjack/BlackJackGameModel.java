/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author albio
 */
public class BlackJackGameModel {

    private BlackJackSpielerModel spieler = new BlackJackSpielerModel();
    private BlackJackDealerModel dealer = new BlackJackDealerModel();

    private Karten k = new Karten();

    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private int kartenWertSpieler = 0;

    private int einsatz = 0;

    private int anzahlKartenImKartendeck = 52;

    private ArrayList<String> kartenDealer = new ArrayList<>();
    private int kartenWertDealer = 0;
    private int karteZweiWert = 0;

    private boolean unentschieden = false;

    private String zufallskarte = "";
    private int zufallszahl = 0;

    private HashMap<String, Integer> karten = k.getKarten();
    private ArrayList<String> kartenSymbole = k.getKartenSymbole();

    private Random r = new Random();

    public void play(Label labelKartenSpieler, Label labelKartenDealer) {
        //Vorbereitung
        spieler.setGewonnen(false);
        dealer.setGewonnen(false);
        kartenSpieler.clear();
        kartenDealer.clear();

        dealer.austeilen(karten, kartenSymbole, anzahlKartenImKartendeck, kartenWertSpieler, kartenWertDealer, karteZweiWert, kartenSpieler, kartenDealer, labelKartenSpieler, labelKartenDealer);
    }

    public void spielerHit(Label labelLösung, Button buttonHit, Button buttonStand, Label labelKartenDealer, Label labelKartenSpieler) {
        spieler.hit(anzahlKartenImKartendeck, kartenWertSpieler, kartenSpieler, kartenSymbole, karten, labelKartenSpieler);

        //Überprüfung, ob 21 überschritten wurde
        if (kartenWertSpieler > 21) {
            dealer.setGewonnen(true);
        }

        //Hat jemand gewonnen?
        if (spieler.hasGewonnen()) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
            buttonHit.setDisable(true);
            buttonStand.setDisable(true);
        }
        if (dealer.hasGewonnen()) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
            buttonHit.setDisable(true);
            buttonStand.setDisable(true);
        }

    }

    public void dealerRound(Label labelLösung, Label labelKartenDealer, Button buttonHit, Button buttonStand) {
        //Zweiter Wert von Karte mitberechnen
        kartenWertDealer += karteZweiWert;

        //Hat es genügend Karten?
        if (anzahlKartenImKartendeck < 1) {
            this.karten = k.getKarten();
        }

        //Karte/n ziehen
        dealer.hit(anzahlKartenImKartendeck, kartenWertDealer, kartenDealer, kartenSymbole, karten);

        //Anzeige leeren
        labelKartenDealer.setText("");

        //Alle Karten vom Dealer anzeigen
        for (String s : kartenDealer) {
            labelKartenDealer.setText(labelKartenDealer.getText() + "," + s);
        }

        //Anzeigen der Karten
        if (kartenWertDealer > 21) {
            spieler.setGewonnen(true);
        }
        if (kartenWertDealer > kartenWertSpieler) {
            dealer.setGewonnen(true);
        }
        if (kartenWertDealer == kartenWertSpieler) {
            unentschieden = true;
        }
        if (kartenWertDealer < kartenWertSpieler) {
            spieler.setGewonnen(true);
        }

        //Hat jemand gewonnen?
        if (spieler.hasGewonnen()) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
            buttonHit.setDisable(true);
            buttonStand.setDisable(true);
        }
        if (dealer.hasGewonnen()) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
            buttonHit.setDisable(true);
            buttonStand.setDisable(true);
        }
        if (unentschieden) {
            labelLösung.setText("UNENTSCHIEDEN!!");
            buttonHit.setDisable(true);
            buttonStand.setDisable(true);
        }
    }

}
