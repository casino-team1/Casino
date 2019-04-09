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
import javafx.scene.control.Label;

/**
 *
 * @author albio
 */
public class BlackJackDealerModel {

    private boolean gewonnen = false;

    private Random r = new Random();
    private int zufallszahl = 0;
    private String zufallskarte = "";

    public void hit(int anzahlKartenImKartendeck, int kartenWertDealer, ArrayList<String> kartenDealer, ArrayList<String> kartenSymbole, HashMap<String, Integer> karten) {

        //Wenn Dealer unter 17 hat, muss er ziehen
        if (kartenWertDealer < 17) {
            while (kartenWertDealer < 17) {
                zufallszahl = r.nextInt(51);
                zufallskarte = kartenSymbole.get(zufallszahl);

                if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("K")) {
                    kartenWertDealer += 10;
                } else if (zufallskarte.equals("A")) {
                    kartenWertDealer += 11;
                } else {
                    kartenWertDealer += karten.get(zufallskarte);
                }
                kartenDealer.add(zufallskarte);
                karten.remove(zufallskarte);
                anzahlKartenImKartendeck--;
            }
        }
    }

    public void austeilen(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, int anzahlKartenImKartendeck, int kartenWertSpieler, int kartenWertDealer, int karteZweiWert, ArrayList<String> kartenSpieler, ArrayList<String> kartenDealer, Label labelKartenSpieler, Label labelKartenDealer) {

        //Karten mischen
        Collections.shuffle(kartenSymbole);

        //Zufallskarten verteilen an Spieler
        for (int i = 0; i < 2; i++) { 
            zufallszahl = r.nextInt(51);
            zufallskarte = kartenSymbole.get(zufallszahl);

            if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("K")) {
                kartenWertSpieler += 10;
            } else if (zufallskarte.equals("A")) {
                kartenWertSpieler += 11;
            } else {
                kartenWertSpieler += karten.get(zufallskarte);
            }
            kartenSpieler.add(zufallskarte);
            karten.remove(zufallskarte);
            anzahlKartenImKartendeck--;
            labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);
        }

        //Erste Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("K")) {
            kartenWertDealer += 10;
        } else if (zufallskarte.equals("A")) {
            kartenWertDealer += 11;
        } else {
            kartenWertDealer += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallskarte);
        anzahlKartenImKartendeck--;
        labelKartenDealer.setText(zufallskarte + " + ?");

        //Zweite unbekannte Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("K")) {
            karteZweiWert += 10;
        } else if (zufallskarte.equals("A")) {
            karteZweiWert += 11;
        } else {
            karteZweiWert += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallskarte);
        anzahlKartenImKartendeck--;
    }

    public void setGewonnen(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }

}
