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
            deck.forEach((card) -> {
                cards.add(card);
            });
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
        //generate cards that are not numeric
        final String[] specialCards = {"King", "Queen", "Jack", "Ace"};
        for (String cardName : specialCards) {
            for (BaccaraCardType type : cardTypes) {
                int cardValue = 0;
                if ("Ace".equals(cardName)) {
                    cardValue = 1;
                }
                cards.add(new BaccaraCard(type, cardName, cardValue));
            }
        }
        return cards;
    }
}
