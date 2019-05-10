/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.database;

import com.team1.casino.database.Connection.DatabaseConnection;
import com.team1.casino.User.Util.PlayerCentral;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick Flückiger
 */
public class Updater {

    private final java.sql.Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();

    public Updater() {
    }

    public void performUpdateWithoutArgument(String query) throws SQLException {
        Thread thread;
        thread = new Thread(new Runnable() {
            private boolean result;

            public boolean getResult() {
                return this.result;
            }

            @Override
            public void run() {
                Statement statement = null;
                try {
                    statement = connection.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    int queryResult = statement.executeUpdate(query);
                } catch (SQLException ex) {
                    Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
                }
                int updates;
                try {
                    updates = statement.getUpdateCount();
                    if (updates == -1) {
                        this.result = false;
                    } else {
                        this.result = true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    public void performUpdateWithArgument(String query, String... arguments) throws SQLException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    for (int i = 0; i < arguments.length; i++) {
                        statement.setString(i + 1, arguments[i]);
                    }
                    int queryResult = statement.executeUpdate();
                    int updates = statement.getUpdateCount();
                } catch (SQLException ex) {
                    Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }

    public void writeStatisticToDatabase(String gameName, double bet, String result, double amount) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
                    int userID = PlayerCentral.getInstance().getUser().getID();
                    int gameID = Integer.parseInt(query.runQueryWithReturn("SELECT id FROM game WHERE gameName = ?", gameName).get(0));
                    String parameters = String.valueOf(amount);
                    int statID = query.runQueryGetAddedID("INSERT INTO statistic(bet,result,amount,playDate) VALUES(?,?,?,NOW())", String.valueOf(bet), result, parameters);
                    query.runQueryWithoutReturn("INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(?,?,?)", String.valueOf(userID), String.valueOf(statID),
                            String.valueOf(gameID));
                } catch (NumberFormatException | SQLException e) {
                }
            }
        });
        thread.start();
    }

}
