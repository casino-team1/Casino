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
        //generaten new card pile and shuffle if by inbuilt methods.
        this.baccaraCardDecks = new BaccaraDeckGenerator().getDecks();
        Collections.shuffle(this.baccaraCardDecks);
    }

    private int playerCardCount;
    private int dealerCardCount;

    public int getPlayerCardCount() {
        return this.playerCardCount;
    }

    public int getDealerCardCount() {
        return this.dealerCardCount;
    }

    private int playerBet = 0;
    private int dealerBet = 0;
    private int tieBet = 0;

    private ArrayList<BaccaraCard> dealerCards = new ArrayList<>();
    private ArrayList<BaccaraCard> playerCards = new ArrayList<>();

    public int getPlayerBet() {
        //100 is just set for debugging.
        return 100;
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

    public void resetGame() {
        this.dealerBet = 0;
        this.playerBet = 0;
        this.tieBet = 0;
    }

    public void generateCards() {
        resetGame();
        this.playerCards.clear();
        this.dealerCards.clear();
        for (int i = 0; i < 2; i++) {
            this.playerCards.add(getNextCardInStack());
            this.dealerCards.add(getNextCardInStack());
        }
        this.playerCardCount = calculateCardCount(playerCards);
        this.dealerCardCount = calculateCardCount(dealerCards);
    }

    private void dealerThirdDraw() {
        this.dealerCards.add(getNextCardInStack());
        this.dealerCardCount = calculateCardCount(dealerCards);
    }

    private void playerThirdDraw() {
        this.playerCards.add(getNextCardInStack());
        this.playerCardCount = calculateCardCount(playerCards);
    }

    private int calculateCardCount(ArrayList<BaccaraCard> cards) {
        int total = 0;
        total = cards.stream().map((card) -> card.getCardValue()).reduce(total, Integer::sum);
        //in baccara a card value of 10 is looked at as 0. So the real card value of the player is always modul 10.
        total %= 10;
        return total;
    }

    public void checkForAdditionalDraw() {
        /**
         * These are implementations of the baccara roules according to the
         * wikipedia entry. For informaton or further knowledge of the used
         * rules, consult wikipedia: Baccar.
         */

        if (this.playerCardCount == 8 || this.playerCardCount == 9 || this.playerCardCount == 6 || this.playerCardCount == 7) {
            if (this.dealerCardCount >= 0 && this.dealerCardCount <= 5) {
                this.dealerThirdDraw();
            }
        } else {
            this.playerThirdDraw();
            int playerCardDrawn = this.playerCards.get(this.playerCards.size() - 1).getCardValue();
            switch (this.dealerCardCount) {
                case 0:
                case 1:
                case 2:
                    if (playerCardDrawn >= 1 && playerCardDrawn <= 10) {
                        this.dealerThirdDraw();
                    }
                    break;
                case 3:
                    if (playerCardDrawn >= 1 && playerCardDrawn <= 10 && playerCardDrawn != 8) {
                        this.dealerThirdDraw();
                    }
                    break;
                case 4:
                    if (playerCardDrawn >= 2 && playerCardDrawn <= 7) {
                        this.dealerThirdDraw();
                    }
                    break;
                case 5:
                    if (playerCardDrawn >= 4 && playerCardDrawn <= 7) {
                        this.dealerThirdDraw();
                    }
                    break;
                case 6:
                    if (playerCardDrawn == 6 || playerCardDrawn == 7) {
                        this.dealerThirdDraw();
                    }
                    break;
                case 7:
                    break;
                case 8:
                case 9:
                    break;
                default:
                    break;
            }
        }
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
        return this.playerCards;
    }

    public double getTotalBet() {
        return this.playerBet + this.dealerBet + this.tieBet;
    }

}
