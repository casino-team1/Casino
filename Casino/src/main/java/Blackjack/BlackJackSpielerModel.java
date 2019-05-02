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
public class BlackJackSpielerModel {

    private int kartenWertSpieler = 0;

    private int xKoordinate = 0;
    private int yKoordinate = 6;
    private int karteWidth = 149;
    private int karteHeight = 201;

    private Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();
    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private String zufallskarte = "";

    private boolean gewonnen = false;

    public void firstHit(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, Pane spielerKartenPane, Label labelKartenWertSpieler) {
        //Parameter einfangen
        this.karten = karten;
        this.kartenSymbole = kartenSymbole;

        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            k.kartenErstellen();
            this.karten = k.getKarten();
        }

        //Zufällige Werte
        int zufallszahl = 0;
        Random r = new Random();
        zufallskarte = "";

        //Spieler zieht Karten
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(("/images/GameCards/" + zufallskarte + ".png"));
        }
        kartenSpieler.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();

        //Karte anzeigen
        ImageView spielerKarte = new ImageView();
        spielerKartenPane.getChildren().add(spielerKarte);
        spielerKarte.setLayoutX(xKoordinate);
        spielerKarte.setLayoutY(yKoordinate);
        spielerKarte.setFitWidth(karteWidth);
        spielerKarte.setFitHeight(karteHeight);
        spielerKarte.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));

        //zweites Mal Karte ziehen
        //Zufällige Werte
        zufallszahl = 0;
        r = new Random();
        zufallskarte = "";

        //Spieler zieht Karten
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(("/images/GameCards/" + zufallskarte + ".png"));
        }
        kartenSpieler.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();

        //Karte anzeigen
        ImageView neusteSpielerKarte = new ImageView();
        spielerKartenPane.getChildren().add(neusteSpielerKarte);
        neusteSpielerKarte.setLayoutX(xKoordinate += 34);
        neusteSpielerKarte.setLayoutY(yKoordinate);
        neusteSpielerKarte.setFitWidth(karteWidth);
        neusteSpielerKarte.setFitHeight(karteHeight);
        neusteSpielerKarte.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");
    }

    public void hit(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, Pane spielerKartenPane, Label labelKartenWertSpieler) {

        int zufallszahl = 0;
        Random r = new Random();
        zufallskarte = "";

        //Spieler zieht Karten
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(("/images/GameCards/" + zufallskarte + ".png"));
        }
        kartenSpieler.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();

        //Karte anzeigen
        ImageView neusteSpielerKarte = new ImageView();
        spielerKartenPane.getChildren().add(neusteSpielerKarte);
        neusteSpielerKarte.setLayoutX(xKoordinate += 34);
        neusteSpielerKarte.setLayoutY(yKoordinate);
        neusteSpielerKarte.setFitWidth(karteWidth);
        neusteSpielerKarte.setFitHeight(karteHeight);
        neusteSpielerKarte.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");

        /*//Karten anzeigen
        if (spielerKarte1.getImage() == null) {
        spielerKarte1.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");
        return;
        }
        if (spielerKarte2.getImage() == null) {
        spielerKarte2.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");
        return;
        }
        if (spielerKarte3.getImage() == null) {
        spielerKarte3.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");
        return;
        }
        if (spielerKarte4.getImage() == null) {
        spielerKarte4.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");
        return;
        }
        if (spielerKarte5.getImage() == null) {
        spielerKarte5.setImage(new Image("/images/GameCards/" + zufallskarte + ".png"));
        labelKartenWertSpieler.setText("(" + kartenWertSpieler + ")");
        }*/
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

    public void clearKartenSpieler() {
        kartenSpieler.clear();
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
