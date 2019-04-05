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
        
        for(int i = 2; i <=10; i++){
            String j = String.valueOf(i);
            karten.put(j, i);
            karten.put(j, i);
            karten.put(j, i);
            karten.put(j, i);
        }
        
        //J, Q, K, A
        karten.put("J", 10);
        karten.put("J", 10);
        karten.put("J", 10);
        karten.put("J", 10);
        
        karten.put("Q", 10);
        karten.put("Q", 10);
        karten.put("Q", 10);
        karten.put("Q", 10);
        
        karten.put("K", 10);
        karten.put("K", 10);
        karten.put("K", 10);
        karten.put("K", 10);
        
        karten.put("A", 11);
        karten.put("A", 11);
        karten.put("A", 11);
        karten.put("A", 11);
        
        //kartenWerte
        for(int i = 2; i <= 10; i++){
            String j = String.valueOf(i);
            kartenWerte.add(j);
            kartenWerte.add(j);
            kartenWerte.add(j);
            kartenWerte.add(j);
        }
        
        //J, Q, K, A
        kartenWerte.add("J");
        kartenWerte.add("J");
        kartenWerte.add("J");
        kartenWerte.add("J");

        kartenWerte.add("Q");
        kartenWerte.add("Q");
        kartenWerte.add("Q");
        kartenWerte.add("Q");
        
        kartenWerte.add("K");
        kartenWerte.add("K");
        kartenWerte.add("K");
        kartenWerte.add("K");
        
        kartenWerte.add("A");
        kartenWerte.add("A");
        kartenWerte.add("A");
        kartenWerte.add("A");
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
