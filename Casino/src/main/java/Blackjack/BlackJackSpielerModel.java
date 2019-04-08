/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.control.Label;

/**
 *
 * @author albio
 */
public class BlackJackSpielerModel {
    
    Karten k = new Karten();
    private int kartenWertSpieler = 0;
    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private boolean gewonnen = false;

    Random r = new Random();
    int zufallszahl;
    String zufallskarte = "";

    public void hit(int anzahlKartenImKartendeck, ArrayList<String> kartenWerte, HashMap<String, Integer> karten, Label labelKartenSpieler, Label labelKartenDealer) {
        //Hat es genügend Karten?
        if (anzahlKartenImKartendeck < 1) {
            //dealer.austeilen();
        }

        //Spieler zieht Karten
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.equals("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(zufallskarte);
        }
        kartenSpieler.add(zufallskarte);
        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);
    }

    public void setGewonnen(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }

    public ArrayList<String> getKartenSpieler() {
        return kartenSpieler;
    }

    public void setKartenWertSpieler(int i) {
        this.kartenWertSpieler += i;
    }

    public int getKartenWertSpieler() {
        return kartenWertSpieler;
    }

    public void addKarte(String zufallskarte) {
        kartenSpieler.add(zufallskarte);
    }
}
