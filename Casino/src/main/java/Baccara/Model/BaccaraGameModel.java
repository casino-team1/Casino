/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Model;

import Baccara.BaccaraHandler;
import Baccara.Entity.BaccaraCard;
import Baccara.Entity.BaccaraDeckGenerator;
import java.util.ArrayList;
import java.util.Collections;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraGameModel extends BaccaraModel {

    private ArrayList<BaccaraCard> cardDecks = new ArrayList<>();

    public BaccaraGameModel(BaccaraHandler baccaraGame) {
        super(baccaraGame);
        this.cardDecks = new BaccaraDeckGenerator().getDecks();
        Collections.shuffle(this.cardDecks);
    }

    public BaccaraCard getNextCardInStack() {
        if (this.cardDecks.size() > 0) {
            BaccaraCard card = cardDecks.get(this.cardDecks.size() - 1);
            this.cardDecks.remove(this.cardDecks.size() - 1);
            return card;
        }
        return null;
    }

    public void reshuffleCards() {
        this.cardDecks = new BaccaraDeckGenerator().getDecks();
        Collections.shuffle(this.cardDecks);
    }

    public boolean hasCardInDecks() {
        if (this.cardDecks.isEmpty() == true) {
            return false;
        }
        return true;
    }

    private SimpleBooleanProperty playerBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty dealerBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty playerDoubleBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty dealerDoubleBetSet = new SimpleBooleanProperty();

    private int playerBet = 0;
    private int dealerBet = 0;
    private int playerDoubleBet = 0;
    private int dealerDoubleBet = 0;
    private int tieBet = 0;

    public void setPlayerBet(int betValue) {
        this.playerBet = betValue;
    }

    public void setDealerBet(int betValue) {
        this.dealerBet = betValue;
    }

    public void setPlayerDoubleBet(int betValue) {
        this.playerDoubleBet = betValue;
    }

    public void setDealerDoubleBet(int betValue) {
        this.dealerDoubleBet = betValue;
    }

    public void setTieBet(int betValue) {
        this.tieBet = betValue;
    }

    public SimpleBooleanProperty getPlayerBetSet() {
        return playerBetSet;
    }

    public SimpleBooleanProperty getDealerBetSet() {
        return dealerBetSet;
    }

    public SimpleBooleanProperty getPlayerDoubleBetSet() {
        return playerDoubleBetSet;
    }

    public SimpleBooleanProperty getDealerDoubleBetSet() {
        return dealerDoubleBetSet;
    }

    public int getPlayerBet() {
        return playerBet;
    }

    public int getDealerBet() {
        return dealerBet;
    }

    public int getPlayerDoubleBet() {
        return playerDoubleBet;
    }

    public int getDealerDoubleBet() {
        return dealerDoubleBet;
    }

    public int getTieBet() {
        return tieBet;
    }

    public void resetGame() {
        this.dealerBet = 0;
        this.playerBet = 0;
        this.dealerDoubleBet = 0;
        this.playerDoubleBet = 0;
        this.tieBet = 0;
    }

    public void interuptGame() {
        try {
            super.getBaccaraGame().displayMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
