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

    BlackJackSpielerModel spieler = new BlackJackSpielerModel();
    BlackJackDealerModel dealer = new BlackJackDealerModel();
    Karten k;

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

    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();

    Random r = new Random();

    public void play(Label labelKartenSpieler, Label labelKartenDealer) {
        //Vorbereitung
        spieler.setGewonnen(false);
        dealer.setGewonnen(false);
        spieler.getKartenSpieler().clear();
        dealer.getKartenDealer().clear();
        
        dealer.austeilen(anzahlKartenImKartendeck, kartenWertSpieler, kartenWertDealer, karteZweiWert, kartenSpieler, labelKartenSpieler, labelKartenDealer);
    }

    public void spielerHit(Label labelLösung, Button buttonHit, Button buttonStand, Label labelKartenSpieler) {
        spieler.hit(anzahlKartenImKartendeck, kartenSymbole, karten, labelKartenSpieler);

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
        dealer.setKartenWertDealer(dealer.getKarteZweiWert());

        //Hat es genügend Karten?
        if (anzahlKartenImKartendeck < 1) {
            this.karten = k.getKarten();
        }

        //Karte/n ziehen
        dealer.hit(anzahlKartenImKartendeck, kartenSymbole, karten);

        //Anzeige leeren
        labelKartenDealer.setText("");

        //Alle Karten vom Dealer anzeigen
        for (String s : dealer.getKartenDealer()) {
            labelKartenDealer.setText(labelKartenDealer.getText() + "," + s);
        }

        //Anzeigen der Karten
        if (dealer.getKartenWertDealer() > 21) {
            spieler.setGewonnen(true);
        }
        if (dealer.getKartenWertDealer() > spieler.getKartenWertSpieler()) {
            dealer.setGewonnen(true);
        }
        if (dealer.getKartenWertDealer() == spieler.getKartenWertSpieler()) {
            unentschieden = true;
        }
        if (dealer.getKartenWertDealer() < spieler.getKartenWertSpieler()) {
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

    public void setAnzahlKartenImKartendeck(int i) {
        this.anzahlKartenImKartendeck -= i;
    }

    public int getAnzahlKartenImKartendeck() {
        return anzahlKartenImKartendeck;
    }

    public ArrayList<String> getKartenSymbole() {
        return kartenSymbole;
    }

    public HashMap<String, Integer> getKarten() {
        return karten;
    }

}
