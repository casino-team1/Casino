/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author albio
 */
public class Karten {

    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<String>();

    private String[] farben = new String[]{"♥", "♦", "♣", "♠"};

    private int anzahlKartenImKartenDeck = 52;

    public Karten() {

        //Kartendeck (für jede Farbe eine Karte)
        for (int i = 2; i <= 10; i++) {
            for (String f : farben) {
                String j = String.valueOf(i);
                karten.put(j+f, i);
                //kartenSymbole
                kartenSymbole.add(j+f);
            }

        }
        //Erstellen Karten und Kartenwert
        String[] special = {"J", "Q", "K"};
        for (int i = 0; i < 4; i++) {
            for (String spec : special) {
                for(String f : farben){
                    karten.put(spec+f, 10);
                    kartenSymbole.add(spec+f);
                }
            }
        }
        //A || Kartenwert und Karten
        for (int i = 0; i < 4; i++) {
            for(String f : farben){
                karten.put("A"+f, 11);
                kartenSymbole.add("A"+f);
            }
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

    public void setAnzahlKartenImKartenDeck(int anzahlKartenImKartenDeck) {
        this.anzahlKartenImKartenDeck -= anzahlKartenImKartenDeck;
    }

}
