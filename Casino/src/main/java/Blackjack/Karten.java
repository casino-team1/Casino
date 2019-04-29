/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author albio
 */
public class Karten {

    private HashMap<Image, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<String>();

    private String[] farben = new String[]{"♥", "♦", "♣", "♠"};

    private int anzahlKartenImKartenDeck = 52;
    private int anzahlKartenInKartenSymbole = 51;

    public void kartenErstellen() {
        /*karten.clear();
        kartenSymbole.clear();
        
        //Kartendeck (für jede Farbe eine Karte)
        for (int i = 2; i <= 10; i++) {
        for (String f : farben) {
        String j = String.valueOf(i);
        karten.put(j + f, i);
        //kartenSymbole
        kartenSymbole.add(j + f);
        }
        
        }
        //Erstellen Karten und Kartenwert
        String[] special = {"J", "Q", "K"};
        for (int i = 0; i < 4; i++) {
        for (String spec : special) {
        for (String f : farben) {
        karten.put(spec + f, 10);
        kartenSymbole.add(spec + f);
        }
        }
        }
        //A || Kartenwert und Karten
        for (int i = 0; i < 4; i++) {
        for (String f : farben) {
        karten.put("A" + f, 11);
        kartenSymbole.add("A" + f);
        }
        }*/

        karten.clear();
        kartenSymbole.clear();

        String[] special = {"J", "K", "Q"};
        String[] symbols = {"C", "H", "S", "D"};
        String format = "/images/GameCards/%s%s.png";
        for (int i = 1; i < 10; i++) {
            for (String symbol : symbols) {
                karten.put(new Image(String.format(format, String.valueOf(i + 1), symbol)), i + 1);
            }
        }
        for (String sepci : special) {
            for (String symbol : symbols) {
                karten.put(new Image(String.format(format, sepci, symbol)), 10);
            }
        }
        for (String symb : symbols) {
            karten.put(new Image(String.format(format, "A", symb)), 11);
        }
        System.out.println(karten);
    }

    public HashMap<Image, Integer> getKarten() {
        return karten;
    }

    public ArrayList<String> getKartenSymbole() {
        return kartenSymbole;
    }

    public int getAnzahlKartenImKartenDeck() {
        return anzahlKartenImKartenDeck;
    }

    public void subAnzahlKartenImKartenDeck() {
        this.anzahlKartenImKartenDeck--;
    }

    public void setAnzahlKartenImKartenDeck(int anzahlKartenImKartenDeck) {
        this.anzahlKartenImKartenDeck = anzahlKartenImKartenDeck;
    }

    public void subAnzahlKartenInKartenSymbole() {
        this.anzahlKartenInKartenSymbole--;
    }

    public void setAnzahlKartenInKartenSymbole(int zahl) {
        this.anzahlKartenInKartenSymbole = zahl;
    }

    public int getAnzahlKartenInKartenSymbole() {
        return anzahlKartenInKartenSymbole;
    }

}
