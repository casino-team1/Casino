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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author albio
 */
public class BlackJackDealerModel {

    private int kartenWertDealer = 0;
    private int karteZweiWert = 0;
    
    private int xKoordinate = 0;
    private int yKoordinate = 6;
    private int karteWidth = 149;
    private int karteHeight = 201;

    Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();
    private ArrayList<String> kartenDealer = new ArrayList<>();
    private String zufallskarte = "";
    private Random r = new Random();
    private boolean gewonnen = false;

    public void firstHit(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, Pane dealerKartenPane, Label labelKartenWertDealer) {
        //Parameter einfangen
        this.karten = karten;
        this.kartenSymbole = kartenSymbole;

        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            k.kartenErstellen();
            this.karten = k.getKarten();
        }

        //zufällige Werte
        int zufallszahl = 0;

        //Erste Karte an Dealer verteilen
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertDealer += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertDealer += 11;
        } else {
            kartenWertDealer += karten.get("/images/GameCards/" + zufallskarte + ".png");
        }
        kartenDealer.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();

        //Karte anzeigen
        ImageView dealerKarte = new ImageView();
        dealerKartenPane.getChildren().add(dealerKarte);
        dealerKarte.setLayoutX(xKoordinate);
        dealerKarte.setLayoutY(yKoordinate);
        dealerKarte.setFitWidth(karteWidth);
        dealerKarte.setFitHeight(karteHeight);
        dealerKarte.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertDealer.setText("(" + kartenWertDealer + ")");
        
        //Zweite unbekannte Karte an Dealer verteilen
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            karteZweiWert += 10;
        } else if (zufallskarte.contains("A")) {
            karteZweiWert += 11;
        } else {
            karteZweiWert += karten.get("/images/GameCards/" + zufallskarte + ".png");
        }
        kartenDealer.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();
    }

    public void secondHit() {

        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            k.kartenErstellen();
            this.karten = k.getKarten();
        }

        //zufällige Werte
        int zufallszahl = 0;
        zufallskarte = "";

        //Wenn Dealer unter 17 hat, muss er ziehen
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);
        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertDealer += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertDealer += 11;
        } else {
            kartenWertDealer += karten.get("/images/GameCards/" + zufallskarte + ".png");
        }
        kartenDealer.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();
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

    public void kartenWertDealerPlusKarteZwei() {
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

    public int getxKoordinate() {
        return xKoordinate;
    }

    public int getyKoordinate() {
        return yKoordinate;
    }

    public int getKarteWidth() {
        return karteWidth;
    }

    public int getKarteHeight() {
        return karteHeight;
    }

    public void setxKoordinate(int xKoordinate) {
        this.xKoordinate = xKoordinate;
    }
    
    

}
