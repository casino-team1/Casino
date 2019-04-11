/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

import com.team1.casino.ExecutionMode;
import com.team1.casino.MainApp;
import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import com.team1.casino.database.Updater;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick Flückiger
 */
public class Spieler extends User {

    public Spieler(String username, String password) {
        super(username, password);
    }

    @Override
    public void writeUserToDatabase() {
        String username = super.getUsername();
        String password = UserUtil.getHashedPassword(super.getPassword());
        String email = super.getEmailAdress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
                int balanceIndex = query.runQueryGetAddedID("INSERT INTO balance(balance,lastUpdated) VALUES(?,CURDATE())", "1000.0");
                System.out.println(balanceIndex);
                query.runQueryWithoutReturn("INSERT INTO user(username,password,role,balance_id,email) VALUES(?,?,?,?,?)", username + ";-" + password + ";-" + "Player" + ";-" + String.valueOf(balanceIndex) + ";-"
                        + email
                );
                System.out.println("Inserted the information");
            }
        }).start();
    }

    @Override
    public void setCurrentBalance(double currentBalance) {
        super.setCurrentBalance(currentBalance);
        if (MainApp.executionMode != ExecutionMode.DEVELOPMENT) {
            try {
                new Updater().performUpdateWithArgument("UPDATE balance b, user u SET b.balance = ? WHERE b.id = u.balance_id AND u.id = ?", String.valueOf(super.getCurrentBalance()) + ";" + String.valueOf(super.getID()));
            } catch (SQLException ex) {
                Logger.getLogger(Spieler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
