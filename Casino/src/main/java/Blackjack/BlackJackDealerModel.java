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

/**
 *
 * @author albio
 */
public class BlackJackDealerModel {

    private int kartenWertDealer = 0;
    private int karteZweiWert = 0;

    Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();
    private ArrayList<String> kartenDealer = new ArrayList<>();
    private String zufallskarte = "";

    private boolean gewonnen = false;

    public void firstHit(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, Label labelKartenDealer) {
        //Parameter einfangen
        this.karten = karten;
        this.kartenSymbole = kartenSymbole; 
        
        //Hat es genügend Karten?
        if(k.getAnzahlKartenImKartenDeck() < 1){
            k.kartenErstellen();
            this.karten = k.getKarten();
        }
        
        //zufällige Werte
        int zufallszahl = 0;
        Random r = new Random();
        
        //Erste Karte an Dealer verteilen
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertDealer += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertDealer += 11;
        } else {
            kartenWertDealer += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallskarte);
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();
        labelKartenDealer.setText(zufallskarte + " + ?");

        //Zweite unbekannte Karte an Dealer verteilen
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            karteZweiWert += 10;
        } else if (zufallskarte.contains("A")) {
            karteZweiWert += 11;
        } else {
            karteZweiWert += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallskarte);
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();
    }
    
    public void secondHit(){
        //Zweiter Wert von Karte mitberechnen
        kartenWertDealer += karteZweiWert;
        
        //Hat es genügend Karten?
        if(k.getAnzahlKartenImKartenDeck() < 1){
            k.kartenErstellen();
            this.karten = k.getKarten();
        }
        
        //zufällige Werte
        int zufallszahl = 0;
        Random r = new Random();
        
        //Wenn Dealer unter 17 hat, muss er ziehen
        if (kartenWertDealer < 17) {
            while (kartenWertDealer < 17) {
                zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
                zufallskarte = kartenSymbole.get(zufallszahl);
                if (karten.containsKey(zufallskarte)) {
                    if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
                        kartenWertDealer += 10;
                    } else if (zufallskarte.contains("A")) {
                        kartenWertDealer += 11;
                    } else {
                        kartenWertDealer += karten.get(zufallskarte);
                    }
                    kartenDealer.add(zufallskarte);
                    karten.remove(zufallskarte);
                    kartenSymbole.remove(zufallskarte);
                    k.subAnzahlKartenImKartenDeck();
                    k.subAnzahlKartenInKartenSymbole();
                } else {
                    kartenWertDealer -= karteZweiWert;
                    secondHit();
                }
            }
        }
    }

    public void setGewonnen(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }

    public int getKartenWertDealer() {
        return kartenWertDealer;
    }

    public void setKartenWertDealer(int kartenWertDealer) {
        this.kartenWertDealer = kartenWertDealer;
    }
    
    public void kartenWertDealerPlusKarteZwei(){
        kartenWertDealer += karteZweiWert;
    }

    public String getZufallskarte() {
        return zufallskarte;
    }

    public void setZufallskarte(String zufallskarte) {
        this.zufallskarte = zufallskarte;
    }

    public int getKarteZweiWert() {
        return karteZweiWert;
    }

    public void setKarteZweiWert(int karteZweiDealer) {
        this.karteZweiWert = karteZweiDealer;
    }

    public ArrayList<String> getKartenDealer() {
        return kartenDealer;
    }

    public void clearKartenDealer() {
        kartenDealer.clear();
    }

}
