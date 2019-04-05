/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Entity;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraGame {

    private ArrayList<BaccaraCard> baccaraCardDecks = new ArrayList<>();

    public BaccaraGame() {
        this.baccaraCardDecks = new BaccaraDeckGenerator().getDecks();
        Collections.shuffle(this.baccaraCardDecks);
    }

    private BaccaraCard getNextCardInStack() {
        if (this.baccaraCardDecks.isEmpty() == true) {
            reshuffleCards();
        }
        BaccaraCard card = baccaraCardDecks.get(this.baccaraCardDecks.size() - 1);
        this.baccaraCardDecks.remove(this.baccaraCardDecks.size() - 1);
        return card;
    }

    public void reshuffleCards() {
        this.baccaraCardDecks = new BaccaraDeckGenerator().getDecks();
        Collections.shuffle(this.baccaraCardDecks);
    }

    private int playerBet = 0;
    private int dealerBet = 0;
    private int tieBet = 0;

    private ArrayList<BaccaraCard> dealerCards = new ArrayList<>();
    private ArrayList<BaccaraCard> playerCard = new ArrayList<>();

    public int getPlayerBet() {
        return playerBet;
    }

    public int getDealerBet() {
        return dealerBet;
    }

    public int getTieBet() {
        return tieBet;
    }

    public void setPlayerBet(int playerBet) {
        this.playerBet = playerBet;
    }

    public void setDealerBet(int dealerBet) {
        this.dealerBet = dealerBet;
    }

    public void setTieBet(int tieBet) {
        this.tieBet = tieBet;
    }

    public ArrayList<BaccaraCard> getDealerCards() {
        return this.dealerCards;
    }

    public ArrayList<BaccaraCard> getPlayerCards() {
        return this.playerCard;
    }

}
