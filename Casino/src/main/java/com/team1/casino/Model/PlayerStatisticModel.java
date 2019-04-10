/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.Entity.Stat;
import com.team1.casino.User.UserCentral;
import com.team1.casino.User.UserUtil;
import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class PlayerStatisticModel extends Observable {

    public ArrayList<String> getUsernameListing() {
        return usernameListing;
    }

    private SimpleStringProperty selectedPlayer = new SimpleStringProperty();

    public SimpleStringProperty getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectePlayer(String value) {
        this.selectedPlayer.setValue(value);
    }

    private ArrayList<Double> accountValues = new ArrayList<>();
    private ArrayList<Stat> stats = new ArrayList<>();
    private ArrayList<String> usernameListing;
    private ArrayList<String> statistics;

    public void loadUsernames() throws SQLException {
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        this.usernameListing = query.runQueryWithReturn("SELECT username FROM user WHERE role = ?", "Player");
    }

    public void displayStatsForSelectedPlayer() throws SQLException {
        this.accountValues.clear();
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        String playerID = String.valueOf(new UserUtil().getIDFromUserByUsername(this.selectedPlayer.getValue()));
        this.statistics = query.runQueryWithReturn("SELECT stat.bet,stat.amount,ga.gameName,stat.result FROM statistic stat, statistictoplayer sp, game ga WHERE sp.user_id = ? AND sp.statistic_id = stat.id AND sp.game_id = ga.id", playerID);
        calculateAccountValues(this.statistics);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Double> getAccountValues() {
        return accountValues;
    }

    public ArrayList<String> getStatistics() {
        return statistics;
    }

    private void calculateAccountValues(ArrayList<String> statisticInformation) {
        ArrayList<Stat> stats = new ArrayList<>();
        for (String el : statisticInformation) {
            System.out.println(el);
        }
        int counter = 0;
        String result = "";
        double bet = 0;
        double endAmount = 0;
        String gameName = "";
        for (String statistic : statisticInformation) {
            switch (counter) {
                case 2:
                    result = statistic;
                    stats.add(new Stat(result, bet, endAmount, gameName));
                    counter = -2;
                    result = "";
                    bet = 0;
                    gameName = "";
                    endAmount = 0;
                    break;
                case 1:
                    gameName = statistic;
                    break;
                case -1:
                    bet = Double.valueOf(statistic);
                    break;
                case 0:
                    endAmount = Double.valueOf(statistic);
                    break;
            }
            counter++;
        }
        this.stats = stats;
        ArrayList<Double> accountBalance = new ArrayList<>();
        accountBalance.add(5000.0);
        double value = 5000.0;
        for (Stat stat : stats) {
            //System.out.println(value);
            if (stat.getResult().equals("Won")) {
                value += (stat.getEndamount() - stat.getBet());
                accountBalance.add(value);
            } else {
                value -= (stat.getBet());
            }
        }
        this.accountValues = accountBalance;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public ArrayList<String> getUserStatistics() {
        return this.statistics;
    }
}
