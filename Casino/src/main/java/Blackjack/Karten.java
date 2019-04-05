/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.HashMap;

/**
 *
 * @author albio
 */
public class Karten {
    private HashMap<Integer, String> karten = new HashMap<>();
    private int wert;
    private String symbol;

    public Karten(int wert, String symbol) {
    }

    public HashMap<Integer, String> getKarten() {
        return karten;
    }

    public int getWert() {
        return wert;
    }

    public String getSymbol() {
        return symbol;
    }
    
    
}
