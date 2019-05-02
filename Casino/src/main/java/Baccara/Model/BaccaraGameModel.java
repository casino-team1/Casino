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
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;

/**
 * @author Nick Flückiger
 */
public class BaccaraGameModel extends BaccaraModel {

    private BaccaraGame baccaraGame;

    public void startNewRound() {
        /**
         * Thread.sleep is not working, because the procedure is running in
         * different threads.
         *
         */
        //TODO: Implement way to delay the game thread for a some seconds.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        resetGame();
        setChanged();
        notifyObservers();
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
    private String resultMessage;

    public void endGame() {
        String winner = determineWinner();
        String betMatch = checkForResult(winner);
        switch (betMatch) {
            case "Player":
                accountChange = (this.playerBet * 2) - (this.baccaraGame.getTotalBet());
                changeUserBalance(UserCentral.getInstance().getUser().getCurrentChipBalance() + (this.playerBet * 2), "Baccara", this.baccaraGame.getTotalBet(), "Won", accountChange);
                break;
            case "Dealer":
                accountChange = (this.dealerBet * 2) - (this.baccaraGame.getTotalBet());
                changeUserBalance(UserCentral.getInstance().getUser().getCurrentChipBalance() + (this.dealerBet * 2), "Baccara", this.baccaraGame.getTotalBet(), "Won", accountChange);
                break;
            case "Tie":
                accountChange = (this.tieBet * 8) - (this.baccaraGame.getTotalBet());
                changeUserBalance(UserCentral.getInstance().getUser().getCurrentChipBalance() + this.tieBet * 8, "Baccara", this.baccaraGame.getTotalBet(), "Won", accountChange);
                break;
            case "Lost":
                accountChange = this.baccaraGame.getTotalBet();
                changeUserBalance(UserCentral.getInstance().getUser().getCurrentChipBalance(), "Baccara", this.baccaraGame.getTotalBet(), "Lost", 0);
                break;
        }
        setChanged();
        notifyObservers();
    }

    private double accountChange = 0;

    public String getResultMessage() {
        return resultMessage;
    }

    public double getAccountChange() {
        return accountChange;
    }

    private String checkForResult(String winner) {
        List<String> betted;
        betted = new ArrayList<>();
        if (this.baccaraGame.getDealerBet() != 0) {
            betted.add("Dealer");
        }
        if (this.baccaraGame.getPlayerBet() != 0) {
            betted.add("Player");
        }
        if (this.baccaraGame.getTieBet() != 0) {
            betted.add("Tie");
        }
        for (String bet : betted) {
            if (bet.equals(winner)) {
                return bet;
            }
        }
        return "Lost";
    }

    private void changeUserBalance(double newBalance, String gameName, double bet, String result, double changed) {
        UserCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(newBalance, gameName, bet, result, changed);
    }

    public String determineWinner() {
        if (this.baccaraGame.getPlayerCardCount() > this.baccaraGame.getDealerCardCount()) {
            return "Player";
        } else if (this.baccaraGame.getPlayerCardCount() == this.baccaraGame.getDealerCardCount()) {
            return "Tie";
        }
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

    public Scene setCursor() {
        return super.getBaccaraGame().getScene();
    }

    public void interuptGame() {
        try {
            super.getBaccaraGame().displayMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getTotalBet() {
        return this.baccaraGame.getTotalBet();
    }

}
