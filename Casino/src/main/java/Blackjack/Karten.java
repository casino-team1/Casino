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
    private ArrayList<String> kartenWerte = new ArrayList<String>();
    private int wert;
    private String symbol;

    public Karten() {
        //Kartendeck (für jede Farbe eine Karte)
        for (int i = 2; i <= 10; i++) {
            String j = String.valueOf(i);
            karten.put(j, i);
            karten.put(j, i);
            karten.put(j, i);
            karten.put(j, i);
            //Kartenwerte
            kartenWerte.add(j);
            kartenWerte.add(j);
            kartenWerte.add(j);
            kartenWerte.add(j);
        }
        //Erstellen Karten und Kartenwert
        String[] special = {"J", "Q", "K"};
        for (int i = 0; i < 4; i++) {
            for (String spec : special) {
                karten.put(spec, 10);
                kartenWerte.add(spec);
            }
        }
        //A || Kartenwert und Karten
        for (int i = 0; i < 4; i++) {
            karten.put("A", 11);
            kartenWerte.add("A");
        }
    }

    public HashMap<String, Integer> getKarten() {
        return karten;
    }

    public ArrayList<String> getKartenWerte() {
        return kartenWerte;
    }

    public int getWert() {
        return wert;
    }

    public String getSymbol() {
        return symbol;
    }

}
