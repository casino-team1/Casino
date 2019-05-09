/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.Entity.Statistic;
import com.team1.casino.MainApp;
import com.team1.casino.database.Connection.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 * @author Nick Flückiger
 */
public class GameStatisticModel extends Observable {

    public String getSelectedGame() {
        return selectedGame;
    }

    private MainApp mainApplication;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    public ArrayList<Statistic> getGameStats() {
        return gameStats;
    }

    public ArrayList<Statistic> getProfit() {
        return profit;
    }

    private ArrayList<Double> gameProfits = new ArrayList<>();

    private String selectedGame;

    public void setSelectedGame(String value) {
        this.selectedGame = value;
    }

    private ArrayList<String> gameNames = new ArrayList<>();

    private ArrayList<Statistic> gameStats = new ArrayList<>();

    private ArrayList<Statistic> profit = new ArrayList<>();

    public void loadGameNames() {
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        try {
            this.gameNames = query.runQueryWithReturn("SELECT gameName FROM game WHERE 1 = ?", "1");
        } catch (SQLException ex) {
            Logger.getLogger(GameStatisticModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getGameNames() {
        return gameNames;
    }

    public void loadGameStats() throws SQLException {
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        ArrayList<String> retreavedData = query.runQueryWithReturn("SELECT ga.gameName,us.username,stat.bet,stat.result,stat.amount FROM game ga,user us,statistic stat, statistictoplayer stp WHERE stp.user_id = us.id AND stp.game_id = ga.id AND stat.id = stp.statistic_id AND ga.gameName = ?", this.selectedGame);
        populateCurrentList(retreavedData);
        setChanged();
        notifyObservers();
    }

    private void populateCurrentList(ArrayList<String> retreavedDatabaseInfromation) {
        this.gameStats.clear();
        int counter = 0;
        String result = "";
        double bet = 0;
        double endAmount;
        String gameName = "";
        String username = "";
        for (String element : retreavedDatabaseInfromation) {
            switch (counter) {
                case 0:
                    gameName = element;
                    counter++;
                    break;
                case 1:
                    username = element;
                    counter++;
                    break;
                case 2:
                    bet = Double.valueOf(element);
                    counter++;
                    break;
                case 3:
                    result = element;
                    counter++;
                    break;
                case 4:
                    endAmount = Double.valueOf(element);
                    Statistic currentStatistic = new Statistic(result, bet, endAmount, gameName);
                    currentStatistic.setUsername(username);
                    this.gameStats.add(currentStatistic);
                    counter = 0;
            }
        }
        double value = 0;
        this.gameProfits.clear();
        this.gameProfits.add(0.0);
        for (Statistic stat : this.gameStats) {
            if (stat.getResult().equals("Won")) {
                value -= stat.getUserAccountChange();
                stat.setResult("Gewonnen");
            } else if (stat.getResult().equals("Lost")) {
                value += Math.abs(-1 * stat.getBet());
                stat.setResult("Verloren");
            } else {
                value += 0;
            }
            this.gameProfits.add(value);
        }
    }

    public ArrayList<Double> getGameProfits() {
        return gameProfits;
    }

    public void goBackToMenu() {
        this.mainApplication.displayStatisticView();
    }

}
