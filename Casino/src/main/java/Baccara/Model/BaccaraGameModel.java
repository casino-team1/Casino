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
import com.team1.casino.User.Util.PlayerCentral;
import java.util.ArrayList;
import java.util.List;
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
    }

    public void checkForCardDraw() {
        this.baccaraGame.checkForAdditionalDraw();
    }

    public BaccaraGameModel(BaccaraHandler baccaraGame) {
        super(baccaraGame);
        this.baccaraGame = new BaccaraGame();
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

    private int playerDoubleBet = 0;
    private int dealerDoubleBet = 0;

    public void setPlayerBet(int betValue) {
        this.baccaraGame.setPlayerBet(betValue);
    }

    public void setDealerBet(int betValue) {
        this.baccaraGame.setDealerBet(betValue);
    }

    private String resultMessage;

    private boolean won = true;

    public void endGame() {
        String winner = determineWinner();
        String betMatch = checkForResult(winner);
        String totalBet = String.valueOf(getTotalBet());
        switch (betMatch) {
            case "Player":
                this.resultMessage = String.format("Der Spieler hat gewonnen, gut gewettet... Sie erhalten ihren Einsatz von %s plus weitere %s chips", String.valueOf(this.baccaraGame.getPlayerBet()), String.valueOf(this.baccaraGame.getPlayerBet()));
                accountChange = (this.baccaraGame.getPlayerBet() * 2) - (this.baccaraGame.getTotalBet());
                changeUserBalance(PlayerCentral.getInstance().getUser().getCurrentChipBalance() + (this.baccaraGame.getPlayerBet() * 2), "Baccara", this.baccaraGame.getTotalBet(), "Won", accountChange);
                break;
            case "Dealer":
                this.resultMessage = String.format("Der Dealer hat gewonnen, gut gewettet... Sie erhalten ihren Einsatz von %s plus weitere %s chips", String.valueOf(this.baccaraGame.getDealerBet()), String.valueOf(this.baccaraGame.getDealerBet()));
                accountChange = (this.baccaraGame.getDealerBet() * 2) - (this.baccaraGame.getTotalBet());
                changeUserBalance(PlayerCentral.getInstance().getUser().getCurrentChipBalance() + (this.baccaraGame.getDealerBet() * 2), "Baccara", this.baccaraGame.getTotalBet(), "Won", accountChange);
                break;
            case "Tie":
                this.resultMessage = String.format("Weder Spieler noch Dealer hat gewonnen, gut gewettet... Sie erhalten ihren Einsatz von %s plus weitere %s chips", String.valueOf((this.baccaraGame.getTieBet())), String.valueOf((this.baccaraGame.getTieBet())));
                accountChange = (this.baccaraGame.getTieBet() * 8) - (this.baccaraGame.getTotalBet());
                changeUserBalance(PlayerCentral.getInstance().getUser().getCurrentChipBalance() + this.baccaraGame.getTieBet() * 8, "Baccara", this.baccaraGame.getTotalBet(), "Won", accountChange);
                break;
            case "Lost":
                won = false;
                if (winner.equals("Tie") == true) {
                    this.resultMessage = String.format("Weder Dealer noch Spieler hat gewonnen Leider knapp daneben... Sie verlieren ihren Einsatz von %s ", totalBet);
                } else {
                    this.resultMessage = String.format("Der %s hat gewonnen.Leider knapp daneben... Sie verlieren ihren Einsatz von %s ", winner, totalBet
                    );
                }
                accountChange = this.baccaraGame.getTotalBet();
                changeUserBalance(PlayerCentral.getInstance().getUser().getCurrentChipBalance(), "Baccara", this.baccaraGame.getTotalBet(), "Lost", 0);
                break;
            default:
                System.out.println(betMatch);
                break;
        }
        setChanged();
        notifyObservers();
    }

    public boolean isWon() {
        return won;
    }

    private double accountChange = 0;

    public String getResultMessage() {
        return resultMessage;
    }

    public double getAccountChange() {
        return accountChange;
    }

    private String checkForResult(String winner) {
        List<String> bets = this.baccaraGame.getSetBets();
        if (bets.isEmpty()) {
            return "Lost";
        }
        for (String bet : bets) {
            if (bet.trim().equals(winner.trim())) {
                return bet;
            }
        }
        return "Lost";
    }

    private void changeUserBalance(double newBalance, String gameName, double bet, String result, double changed) {
        PlayerCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(newBalance, gameName, bet, result, changed);
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
        this.dealerDoubleBet = 0;
        this.playerDoubleBet = 0;
        this.baccaraGame.resetGame();
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
