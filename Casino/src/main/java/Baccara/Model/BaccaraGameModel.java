/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Model;

import Baccara.BaccaraHandler;
import Baccara.Entity.BaccaraCard;
import Baccara.Entity.BaccaraGame;
import com.team1.casino.User.Util.UserCentral;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Nick Flückiger
 */
public class BaccaraGameModel extends BaccaraModel {

    private BaccaraGame baccaraGame;

    public void startNewRound() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        resetGame();
        setChanged();
        notifyAll();

    }

    public void checkForCardDraw() {
        this.baccaraGame.checkForAdditionalDraw();
    }

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

    public void generateCards() {
        this.baccaraGame.generateCards();
    }

    public ArrayList<BaccaraCard> getPlayerCards() {
        return this.baccaraGame.getPlayerCards();
    }

    public ArrayList<BaccaraCard> getDealerCards() {
        return this.baccaraGame.getDealerCards();
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

    public String determineWinner() {
        if (this.baccaraGame.getPlayerCardCount() > this.baccaraGame.getDealerCardCount()) {
            if (this.baccaraGame.getPlayerBet() != 0) {
                UserCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(UserCentral.getInstance().getUser().getCurrentBalance() + (this.baccaraGame.getPlayerBet() * 2), "Baccara", this.baccaraGame.getPlayerBet(), "Won", this.baccaraGame.getPlayerBet());
            }
            return "Player";
        } else if (this.baccaraGame.getPlayerCardCount() == this.baccaraGame.getDealerCardCount()) {
            UserCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(UserCentral.getInstance().getUser().getCurrentBalance() + this.baccaraGame.getTieBet(), "Baccara", this.baccaraGame.getPlayerBet(), "Tie", 0);
            return "Tie";
        }
        UserCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(UserCentral.getInstance().getUser().getCurrentBalance(), "Baccara", this.baccaraGame.getPlayerBet(), "Lost", this.baccaraGame.getPlayerBet() - (this.baccaraGame.getPlayerBet() * 2));
        return "Dealer";
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
