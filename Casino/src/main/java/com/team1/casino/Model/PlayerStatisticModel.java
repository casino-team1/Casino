/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.Entity.Statistic;
import com.team1.casino.MainApp;
import com.team1.casino.User.Util.UserUtil;
import com.team1.casino.database.Connection.DatabaseConnection;
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

    private MainApp mainApplication;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    private SimpleStringProperty selectedPlayer = new SimpleStringProperty();

    public SimpleStringProperty getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectePlayer(String value) {
        this.selectedPlayer.setValue(value);
    }

    private ArrayList<Double> accountValues = new ArrayList<>();
    private ArrayList<Statistic> stats = new ArrayList<>();
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
        ArrayList<Statistic> stats = new ArrayList<>();
        int counter = 0;
        String result = "";
        double bet = 0;
        double endAmount = 0;
        String gameName = "";
        for (String statistic : statisticInformation) {
            switch (counter) {
                case 0:
                    bet = Double.valueOf(statistic);
                    counter++;
                    break;
                case 1:
                    endAmount = Double.valueOf(statistic);
                    counter++;
                    break;
                case 2:
                    gameName = statistic;
                    counter++;
                    break;
                case 3:
                    result = statistic;
                    stats.add(new Statistic(result, bet, endAmount, gameName));
                    gameName = "";
                    result = "";
                    bet = 0.0;
                    endAmount = 0.0;
                    counter = 0;
                    break;
            }
        }
        this.stats = stats;
        ArrayList<Double> accountBalance = new ArrayList<>();
        double value = 0;
        for (Statistic stat : stats) {
            value += stat.getUserAccountChange();
            accountBalance.add(value);
        }
        this.accountValues = accountBalance;
    }

    public ArrayList<Statistic> getStats() {
        return stats;
    }

    public ArrayList<String> getUserStatistics() {
        return this.statistics;
    }

    public void backToMenu() {
        this.mainApplication.displayStatisticView();
    }
}
