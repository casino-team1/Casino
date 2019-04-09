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

    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private int kartenWertSpieler = 0;

    private ArrayList<String> kartenDealer = new ArrayList<>();
    private int kartenWertDealer = 0;
    private int karteZweiWert = 0;

    private boolean unentschieden = false;

    private String zufallskarte = "";
    private int zufallszahl = 0;

    private Karten k = new Karten();
    private HashMap<String, Integer> karten = k.getKarten();
    private ArrayList<String> kartenSymbole = k.getKartenSymbole();

    private Random r = new Random();

    public void play(Label labelKartenSpieler, Label labelKartenDealer) {
        //Vorbereitung
        spieler.setGewonnen(false);
        dealer.setGewonnen(false);
        kartenSpieler.clear();
        kartenDealer.clear();

        //Karten mischen
        Collections.shuffle(kartenSymbole);

        //Zufallskarten verteilen an Spieler
        for (int i = 0; i < 2; i++) {
            zufallszahl = r.nextInt(51);
            zufallskarte = kartenSymbole.get(zufallszahl);

            if (zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
                kartenWertSpieler += 10;
            } else if (zufallskarte.contains("A")) {
                kartenWertSpieler += 11;
            } else {
                kartenWertSpieler += karten.get(zufallskarte);
            }
            kartenSpieler.add(zufallskarte);
            karten.remove(zufallskarte);
            k.setAnzahlKartenImKartenDeck(1);
            labelKartenSpieler.setText(labelKartenSpieler.getText() + " , " + zufallskarte);
        }

        //Erste Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertDealer += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertDealer += 11;
        } else {
            kartenWertDealer += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallskarte);
        k.setAnzahlKartenImKartenDeck(1);
        labelKartenDealer.setText(zufallskarte + " + ?");

        //Zweite unbekannte Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            karteZweiWert += 10;
        } else if (zufallskarte.contains("A")) {
            karteZweiWert += 11;
        } else {
            karteZweiWert += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallskarte);
        k.setAnzahlKartenImKartenDeck(1);
    }

    public void spielerHit(Label labelLösung, Button buttonHit, Button buttonStand, Label labelKartenDealer, Label labelKartenSpieler) {
        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            this.karten = k.getKarten();
        }

        //Spieler zieht Karten
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(zufallskarte);
        }
        kartenSpieler.add(zufallskarte);
        karten.remove(zufallskarte);
        k.setAnzahlKartenImKartenDeck(1);
        labelKartenSpieler.setText(labelKartenSpieler.getText() + " , " + zufallskarte);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            dealer.setGewonnen(true);
        }

        end(buttonHit, buttonStand, labelLösung);

    }

    public void dealerHit() {
        //Zweiter Wert von Karte mitberechnen
        kartenWertDealer += karteZweiWert;

        //Wenn Dealer unter 17 hat, muss er ziehen
        if (kartenWertDealer < 17) {
            while (kartenWertDealer < 17) {
                zufallszahl = r.nextInt(51);
                zufallskarte = kartenSymbole.get(zufallszahl);
                if (karten.containsKey(zufallskarte)) {
                    if (zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
                        kartenWertDealer += 10;
                    } else if (zufallskarte.contains("A")) {
                        kartenWertDealer += 11;
                    } else {
                        kartenWertDealer += karten.get(zufallskarte);
                    }
                    kartenDealer.add(zufallskarte);
                    karten.remove(zufallskarte);
                    k.setAnzahlKartenImKartenDeck(1);
                } else {
                    kartenWertDealer -= karteZweiWert;
                    dealerHit();
                }
            }
        }
        
    }

    public void dealerRound(Label labelLösung, Label labelKartenDealer, Button buttonHit, Button buttonStand) {
        dealerHit();

        //Anzeige leeren
        labelKartenDealer.setText("");

        //Alle Karten vom Dealer anzeigen
        for (String s : kartenDealer) {
            labelKartenDealer.setText(labelKartenDealer.getText() + " , " + s);
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

        end(buttonHit, buttonStand, labelLösung);
    }
    
    public void end(Button buttonHit, Button buttonStand, Label labelLösung){
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
