/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Entity;

import java.util.ArrayList;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraDeckGenerator {

    private static final int DECKS = 8;

    public ArrayList<BaccaraCard> getDecks() {
        ArrayList<BaccaraCard> cards = new ArrayList<>();
        for (int i = 0; i < DECKS; i++) {
            ArrayList<BaccaraCard> deck = generateDeck();
            for (BaccaraCard card : deck) {
                cards.add(card);
            }
        }
        return cards;
    }

    private ArrayList<BaccaraCard> generateDeck() {
        ArrayList<BaccaraCard> cards = new ArrayList<>();
        BaccaraCardType[] cardTypes = {BaccaraCardType.CLUB, BaccaraCardType.DIAMON, BaccaraCardType.HEART, BaccaraCardType.SPADE};
        for (int i = 2; i <= 10; i++) {
            for (BaccaraCardType type : cardTypes) {
                cards.add(new BaccaraCard(type, String.valueOf(i), i));
            }
        }
        final String[] specialCards = {"King", "Queen", "Jack"};
        for (String cardName : specialCards) {
            for (BaccaraCardType type : cardTypes) {
                cards.add(new BaccaraCard(type, cardName, 0));
            }
        }
        for (BaccaraCardType type : cardTypes) {
            cards.add(new BaccaraCard(type, "Ace", 1));
        }
        return cards;
    }
}
