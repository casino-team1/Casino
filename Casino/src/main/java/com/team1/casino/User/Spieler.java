/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

import com.team1.casino.User.Util.UserUtil;
import com.team1.casino.ExecutionMode;
import com.team1.casino.MainApp;
import com.team1.casino.database.Connection.DatabaseConnection;
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
        //Create new Class, that handles the creation of the user.
        //The class should also handle Statupdate and other actions related to the player.
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
                int balanceIndex = query.runQueryGetAddedID("INSERT INTO balance(balance,lastUpdated) VALUES(?,CURDATE())", "5000.0;");
                System.out.println(balanceIndex);
                query.runQueryWithoutReturn("INSERT INTO user(username,password,role,balance_id,email) VALUES(?,?,?,?,?)", username + ";-" + password + ";-" + "Player" + ";-" + String.valueOf(balanceIndex) + ";-"
                        + email
                );
                System.out.println("Inserted the information");
            }
        }).start();
    }
    
    @Override
    public void setNewChipBalance(double currentBalance) {
        super.setNewChipBalance(currentBalance);
        if (MainApp.EXECUTION_MODE != ExecutionMode.DEVELOPMENT) {
            try {
                Updater updated = new Updater();
                updated.performUpdateWithArgument("UPDATE balance b, user u SET b.chips = ? WHERE b.id = u.balance_id AND u.id = ?", String.valueOf(super.getCurrentChipBalance()) + ";" + String.valueOf(super.getID()));
            } catch (SQLException ex) {
                Logger.getLogger(Spieler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void addStat(String gameName, double bet, String result, double amount) {
        Updater updated = new Updater();
        updated.writeStatisticToDatabase(gameName, bet, result, amount);
    }
    
    @Override
    public void setCurrentBalanceAndAddStatistic(double newBalance, String gameName, double bet, String result, double amount) {
        this.setNewChipBalance(newBalance);
        this.addStat(gameName, bet, result, amount);
    }
    
    @Override
    public void setNewPassword(String newPasswordHash, String newPasswordPlain) {
        super.setPassword(newPasswordPlain);
        String statement = "Update user SET password = ? WHERE username = ?";
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        query.runQueryWithoutReturn(statement, newPasswordHash + ";-" + super.getUsername());
    }
    
    @Override
    public void setNewMoney(int newMoney) {
        super.setCurrentMoney(newMoney);
        if (MainApp.EXECUTION_MODE != ExecutionMode.DEVELOPMENT) {
            try {
                Updater updated = new Updater();
                updated.performUpdateWithArgument("UPDATE balance b, user u SET b.money = ? WHERE b.id = u.balance_id AND u.id = ?", String.valueOf(super.getCurrentMoney()) + ";" + String.valueOf(super.getID()));
            } catch (SQLException ex) {
                Logger.getLogger(Spieler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
