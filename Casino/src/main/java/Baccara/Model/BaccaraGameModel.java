/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Model;

import Baccara.BaccaraHandler;
import Baccara.Entity.BaccaraGame;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Nick Flückiger
 */


public class BaccaraGameModel extends BaccaraModel {

    private BaccaraGame baccaraGame;

    public BaccaraGameModel(BaccaraHandler baccaraGame) {
        super(baccaraGame);
        this.baccaraGame = new BaccaraGame();
    }

    public boolean betsAreSet() {
        if (playerBetSet.getValue() == true || dealerBetSet.getValue() == true || tieBetSet.getValue() == true) {
            return true;
        }
        return false;
    }

    public int getPlayerCardCount() {
        return this.baccaraGame.getPlayerCardCount();
    }

    public int getDealerCardCount() {
        return this.baccaraGame.getDealerCardCount();
    }

    private SimpleBooleanProperty playerBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty dealerBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty playerDoubleBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty dealerDoubleBetSet = new SimpleBooleanProperty();
    private SimpleBooleanProperty tieBetSet = new SimpleBooleanProperty();

    private int playerBet = 0;
    private int dealerBet = 0;
    private int playerDoubleBet = 0;
    private int dealerDoubleBet = 0;
    private int tieBet = 0;

    public void setPlayerBet(int betValue) {
        this.baccaraGame.setPlayerBet(betValue);
    }

    public void setDealerBet(int betValue) {
        this.baccaraGame.setDealerBet(betValue);
    }

    public void setPlayerDoubleBet(int betValue) {
        this.playerDoubleBet = betValue;
    }

    public void setDealerDoubleBet(int betValue) {
        this.dealerDoubleBet = betValue;
    }

    public void setTieBet(int betValue) {
        this.baccaraGame.setTieBet(betValue);
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
        return this.baccaraGame.getPlayerBet();
    }

    public int getDealerBet() {
        return this.baccaraGame.getDealerBet();
    }

    public int getPlayerDoubleBet() {
        return playerDoubleBet;
    }

    public int getDealerDoubleBet() {
        return dealerDoubleBet;
    }

    public int getTieBet() {
        return this.baccaraGame.getTieBet();
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
