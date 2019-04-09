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

    public Karten() {
        //Kartendeck (für jede Farbe eine Karte)
        for (int i = 2; i <= 10; i++) {
            String j = String.valueOf(i);
            karten.put(j, i);
            karten.put(j, i);
            karten.put(j, i);
            karten.put(j, i);
            //kartenSymbole
            kartenSymbole.add(j);
            kartenSymbole.add(j);
            kartenSymbole.add(j);
            kartenSymbole.add(j);
        }
        //Erstellen Karten und Kartenwert
        String[] special = {"J", "Q", "K"};
        for (int i = 0; i < 4; i++) {
            for (String spec : special) {
                karten.put(spec, 10);
                kartenSymbole.add(spec);
            }
        }
        //A || Kartenwert und Karten
        for (int i = 0; i < 4; i++) {
            karten.put("A", 11);
            kartenSymbole.add("A");
        }
    }

    public HashMap<String, Integer> getKarten() {
        return karten;
    }

    public ArrayList<String> getKartenSymbole() {
        return kartenSymbole;
    }

}
