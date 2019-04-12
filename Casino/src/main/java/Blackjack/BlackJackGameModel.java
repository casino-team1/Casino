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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author albio
 */
public class BlackJackGameModel {

    private BlackJackSpielerModel spieler = new BlackJackSpielerModel();
    private BlackJackDealerModel dealer = new BlackJackDealerModel();

    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private int kartenWertSpieler = 0;

    private ArrayList<String> kartenDealer = new ArrayList<>();
    private int kartenWertDealer = 0;
    private int karteZweiWert = 0;
    private int anzahlKartenInKartenSymbole = 51;

    private boolean unentschieden = false;

    private String zufallskarte = "";
    private int zufallszahl = 0;

    private Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();

    private Random r = new Random();

    public void play(Label labelKartenSpieler, Label labelKartenDealer, Button buttonHit, Button buttonStand, Button buttonPrüfung, Button buttonVerdoppeln, Button buttonVersichern, Label labelLösung, TextField textfeldEinsatz, TextField textfeldVersicherung) {
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
            if(spieler.getKartenSpieler().contains("A♥") || spieler.getKartenSpieler().contains("A♦") || spieler.getKartenSpieler().contains("A♣") || spieler.getKartenSpieler().contains("A♠")){
                spieler.setKartenWertSpielerMinusTen();
            }else{
                dealer.setGewonnen(true);
                end(buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelLösung, textfeldEinsatz, textfeldVersicherung);
            } 
        }

        if (spieler.getKartenWertSpieler() == 21) {
            spieler.setGewonnen(true);
            end(buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelLösung, textfeldEinsatz, textfeldVersicherung);
        }

        if (spieler.getKartenWertSpieler() == 9 || spieler.getKartenWertSpieler() == 10 || spieler.getKartenWertSpieler() == 11) {
            buttonVerdoppeln.setDisable(false);
        }

        if (dealer.getKartenWertDealer() == 11) {
            buttonVersichern.setDisable(false);
            textfeldVersicherung.setDisable(false);          
        }
    }

    public void spielerHit(Label labelLösung, Button buttonHit, Button buttonStand, Button buttonPrüfung, Button buttonVerdoppeln, Button buttonVersichern, Label labelKartenDealer, Label labelKartenSpieler, TextField textfeldEinsatz, TextField textfeldVersicherung) {
        //Spieler zieht Karte
        spieler.hit(karten, kartenSymbole, labelKartenSpieler);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            if(zufallskarte.contains("A")){
                kartenWertSpieler -= 10;
            }
            dealer.setGewonnen(true);
            end(buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelLösung, textfeldEinsatz, textfeldVersicherung);
        }
        
        //Überprüfung, ob 21 erreicht wurde wurde
        if (kartenWertSpieler == 21) {
            spieler.setGewonnen(true);
            end(buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelLösung, textfeldEinsatz, textfeldVersicherung);
        }
    }

    public void dealerRound(Label labelLösung, Label labelKartenDealer, Button buttonHit, Button buttonStand, Button buttonPrüfung, Button buttonVerdoppeln, Button buttonVersichern, TextField textfeldEinsatz, TextField textfeldVersicherung) {
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

        end(buttonHit, buttonStand, buttonPrüfung, buttonVerdoppeln, buttonVersichern, labelLösung, textfeldEinsatz, textfeldVersicherung);
    }

    public void end(Button buttonHit, Button buttonStand, Button buttonPrüfung, Button buttonVerdoppeln, Button buttonVersichern, Label labelLösung, TextField textfeldEinsatz, TextField textfeldVersicherung) {
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

    public void versichern(Label labelLösung, Label labelKartenDealer, Button buttonHit, Button buttonStand, Button buttonPrüfung, Button buttonVerdoppeln, Button buttonVersichern, TextField textfeldEinsatz, TextField textfeldVersicherung) {
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
