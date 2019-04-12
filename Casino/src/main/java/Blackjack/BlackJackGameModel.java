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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author albio
 */
public class BlackJackGameModel {

    private BlackJackSpielerModel spieler = new BlackJackSpielerModel();
    private BlackJackDealerModel dealer = new BlackJackDealerModel();

    private boolean unentschieden = false;

    private Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();

    //FXML
    private Button buttonStand;
    private Button buttonHit;
    private Label labelKartenSpieler;
    private Label labelKartenDealer;
    private Label labelEinsatzFehler;
    private Button buttonStart;
    private Label labelLösung;
    private TextField textfeldEinsatz;
    private Button buttonPrüfung;
    private Button buttonVerlassen;
    private Button buttonHelp;
    private Button buttonVerdoppeln;
    private Button buttonVersichern;
    private TextField textfeldVersicherung;
    private Label labelVerdoppeln;
    private Label labelVersicherung;

    public BlackJackGameModel(Button buttonHelp, Button buttonHit, Button buttonPrüfung, Button buttonStand, Button buttonStart, Button buttonVerdoppeln, Button buttonVerlassen, Button buttonVersichern, Label labelKartenSpieler, Label labelKartenDealer, Label labelLösung, Label labelVerdoppeln, Label labelVersicherung, Label labelEinsatzFehler, TextField textfeldEinsatz, TextField textfeldVersicherung) {
        this.buttonHelp = buttonHelp;
        this.buttonHit = buttonHit;
        this.buttonPrüfung = buttonPrüfung;
        this.buttonStand = buttonStand;
        this.buttonStart = buttonStart;
        this.buttonVerdoppeln = buttonVerdoppeln;
        this.buttonVerlassen = buttonVerlassen;
        this.buttonVersichern = buttonVersichern;

        this.labelEinsatzFehler = labelEinsatzFehler;
        this.labelKartenDealer = labelKartenDealer;
        this.labelKartenSpieler = labelKartenSpieler;
        this.labelLösung = labelLösung;
        this.labelVerdoppeln = labelVerdoppeln;
        this.labelVersicherung = labelVersicherung;

        this.textfeldEinsatz = textfeldEinsatz;
        this.textfeldVersicherung = textfeldVersicherung;
    }

    public void play() {
        //Vorbereitung
        k.kartenErstellen();

        spieler.setGewonnen(false);
        dealer.setGewonnen(false);
        unentschieden = false;
        k.setAnzahlKartenImKartenDeck(52);
        k.setAnzahlKartenInKartenSymbole(51);
        spieler.setKartenWertSpieler(0);
        dealer.setKartenWertDealer(0);
        dealer.setKarteZweiWert(0);
        spieler.clearKartenSpieler();
        dealer.clearKartenDealer();
        karten.clear();
        kartenSymbole.clear();
        karten.putAll(k.getKarten());
        kartenSymbole.addAll(k.getKartenSymbole());

        //Karten mischen
        Collections.shuffle(kartenSymbole);

        //2 Zufallskarten verteilen an Spieler
        spieler.hit(karten, kartenSymbole, labelKartenSpieler);
        spieler.hit(karten, kartenSymbole, labelKartenSpieler);

        //Dealer zieht Karten
        dealer.firstHit(karten, kartenSymbole, labelKartenDealer);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            spieler.setKartenWertSpielerMinusTen();
        }

        if (spieler.getKartenWertSpieler() == 21) {
            spieler.setGewonnen(true);
            end();
        }

        if (spieler.getKartenWertSpieler() == 9 || spieler.getKartenWertSpieler() == 10 || spieler.getKartenWertSpieler() == 11) {
            buttonVerdoppeln.setDisable(false);
        }

        if (dealer.getKartenWertDealer() == 11) {
            buttonVersichern.setDisable(false);
            textfeldVersicherung.setDisable(false);
        }
    }

    public void spielerHit() {
        //Spieler zieht Karte
        spieler.hit(karten, kartenSymbole, labelKartenSpieler);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            if (spieler.getZufallskarte().contains("A")) {
                spieler.setKartenWertSpielerMinusTen();
                if (spieler.getKartenWertSpieler() > 21) {
                    dealer.setGewonnen(true);
                    end();
                } else {
                    //nichts
                }
            } else {
                dealer.setGewonnen(true);
                end();
            }
        }

        //Überprüfung, ob 21 erreicht wurde wurde
        if (spieler.getKartenWertSpieler() == 21) {
            spieler.setGewonnen(true);
            end();
        }
    }

    public void dealerRound() {
        //dealer muss karten ziehen
        dealer.secondHit();

        //Anzeige leeren
        labelKartenDealer.setText("");

        //Alle Karten vom Dealer anzeigen
        for (String s : dealer.getKartenDealer()) {
            labelKartenDealer.setText(labelKartenDealer.getText() + " , " + s);
        }

        //Werte überprüfen
        if (dealer.getKartenWertDealer() > 21) {
            spieler.setGewonnen(true);
        } else if (dealer.getKartenWertDealer() > spieler.getKartenWertSpieler()) {
            dealer.setGewonnen(true);
        } else if (dealer.getKartenWertDealer() == spieler.getKartenWertSpieler()) {
            unentschieden = true;
        } else if (dealer.getKartenWertDealer() < spieler.getKartenWertSpieler()) {
            spieler.setGewonnen(true);
        } else {
        }

        end();
    }

    public void end() {
        //Eingaben blockieren
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldVersicherung.setDisable(true);
        buttonPrüfung.setDisable(false);
        textfeldEinsatz.setDisable(false);

        //Hat jemand gewonnen?
        if (spieler.hasGewonnen()) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealer.hasGewonnen()) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }
        if (unentschieden) {
            labelLösung.setText("UNENTSCHIEDEN!!");
        }
    }

    public void versichern() {
        //Zweiter Wert von Karte mitberechnen
        dealer.kartenWertDealerPlusKarteZwei();

        //Anzeige leeren
        labelKartenDealer.setText("");

        //Alle Karten vom Dealer anzeigen
        for (String s : dealer.getKartenDealer()) {
            labelKartenDealer.setText(labelKartenDealer.getText() + " , " + s);
        }

        //Hat dealer BlackJack?
        if (dealer.getKartenWertDealer() == 21) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealer.getKartenWertDealer() < 21 || dealer.getKartenWertDealer() > 21) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }

        //Eingaben blockieren
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldVersicherung.setDisable(true);
        buttonPrüfung.setDisable(false);
        textfeldEinsatz.setDisable(false);
    }
}
