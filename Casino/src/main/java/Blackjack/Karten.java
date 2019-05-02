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

    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<String>();

    private int anzahlKartenImKartenDeck = 52;
    private int anzahlKartenInKartenSymbole = 51;

    public void kartenErstellen() {
        karten.clear();
        kartenSymbole.clear();

        String[] special = {"J", "K", "Q"};
        String[] symbols = {"C", "H", "S", "D"};
        String format = "/images/GameCards/%s%s.png";

        //kartendeck
        for (int i = 1; i < 10; i++) {
            for (String symbol : symbols) {
                karten.put((String.format(format, String.valueOf(i + 1), symbol)), i + 1);
            }
        }
        for (String speci : special) {
            for (String symbol : symbols) {
                karten.put((String.format(format, speci, symbol)), 10);
            }
        }
        for (String symb : symbols) {
            karten.put((String.format(format, "A", symb)), 11);
        }

        //kartenSymbole
        for (int i = 2; i <= 10; i++) {
            for (String j : symbols) {
                kartenSymbole.add(i + j);
            }
        }

        for (String s : special) {
            for (String sy : symbols) {
                kartenSymbole.add(s + sy);
            }
        }

        for (String sy : symbols) {
            kartenSymbole.add("A" + sy);
        }
    }

    public HashMap<String, Integer> getKarten() {
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
