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

    private HashMap<String, Integer> cards = new HashMap<>();
    private ArrayList<String> cardsSymbols = new ArrayList<String>();

    private int cardsInDeck = 52;
    private int cardsInSymboles = 51;

    public void kartenErstellen() {
        cards.clear();
        cardsSymbols.clear();
        String[] special = {"J", "K", "Q"};
        String[] symbols = {"C", "H", "S", "D"};
        String format = "/images/GameCards/%s%s.png";
        //kartendeck
        for (int i = 1; i < 10; i++) {
            for (String symbol : symbols) {
                cards.put((String.format(format, String.valueOf(i + 1), symbol)), i + 1);
            }
        }
        for (String speci : special) {
            for (String symbol : symbols) {
                cards.put((String.format(format, speci, symbol)), 10);
            }
        }
        for (String symb : symbols) {
            cards.put((String.format(format, "A", symb)), 11);
        }

        //kartenSymbole
        for (int i = 2; i <= 10; i++) {
            for (String j : symbols) {
                cardsSymbols.add(i + j);
            }
        }

        for (String s : special) {
            for (String sy : symbols) {
                cardsSymbols.add(s + sy);
            }
        }

        for (String sy : symbols) {
            cardsSymbols.add("A" + sy);
        }
    }

    public HashMap<String, Integer> getKarten() {
        return cards;
    }

    public ArrayList<String> getCardsSymbole() {
        return cardsSymbols;
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    public void subAmountOfCardsInDeck() {
        this.cardsInDeck--;
    }

    public void setAmountOfCardsInDeck(int i) {
        this.cardsInDeck = i;
    }

    public void subAmountOfCardSymbols() {
        this.cardsInSymboles--;
    }

    public void setAmountOfCardsInCardsSymbole(int i) {
        this.cardsInSymboles = i;
    }

    public int getAmountOfCardsInCardsSymbole() {
        return cardsInSymboles;
    }

}
