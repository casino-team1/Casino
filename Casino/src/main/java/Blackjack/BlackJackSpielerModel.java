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

    private int kartenWertSpieler = 0;
    
    private Karten k;
    private HashMap<String, Integer> karten;
    private ArrayList<String> kartenSymbole;
    private ArrayList<String> kartenSpieler;
    private String zufallskarte;

    private boolean gewonnen = false;

    public void hit(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, ArrayList<String> kartenSpieler, Label labelKartenSpieler) {
        //Parameter einfangen
        this.karten = karten;
        this.kartenSymbole = kartenSymbole;
        this.kartenSpieler = kartenSpieler;
        
        //Zufällige Werte
        int zufallszahl = 0;
        String zufallskarte = "";
        Random r = new Random();
        
        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            this.karten = k.getKarten();
        }

        //Spieler zieht Karten
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(zufallskarte);
        }
        kartenSpieler.add(zufallskarte);
        karten.remove(zufallskarte);
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();
        labelKartenSpieler.setText(labelKartenSpieler.getText() + " , " + zufallskarte);
    }

    public void setGewonnen(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }
    
    public int getKartenWertSpieler() {
        return kartenWertSpieler;
    }

    public void setKartenWertSpieler(int kartenWertSpieler) {
        this.kartenWertSpieler = kartenWertSpieler;
    }
    
    public void setKartenWertSpielerMinusTen() {
        kartenWertSpieler -= 10;
    }
    
    public String getZufallskarte() {
        return zufallskarte;
    }

    public ArrayList<String> getKartenSpieler() {
        return kartenSpieler;
    }
    
    public void setKartenSpieler(ArrayList<String> kartenSpieler) {
        this.kartenSpieler = kartenSpieler;
    }
   
}
