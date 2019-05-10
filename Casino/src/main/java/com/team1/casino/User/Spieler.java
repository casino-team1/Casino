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
import com.team1.casino.User.Util.PlayerCentral;
import com.team1.casino.database.Connection.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import com.team1.casino.database.Updater;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick Flückiger
 */
public class Spieler {

    public Spieler(String username, String password) {
        this.username = username;
        this.password = password;
    }
    private int ID;
    private String role;
    private String username;
    private String password;
    private double currentChips = 999999;
    private boolean validated = false;
    private String validationCode = "";
    private String emailAdress;
    private double currentMoney;

    public void writeUserToDatabase() {
        this.currentChips = 0;
        this.currentMoney = 5000;
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        int balanceIndex = query.runQueryGetAddedID("INSERT INTO balance(chips,money,lastUpdated) VALUES(?,?,NOW())", "0.0", "5000.0");
        query.runQueryWithoutReturn("INSERT INTO user(username,password,role,balance_id,email) VALUES(?,?,?,?,?)", username, password, "Player", String.valueOf(balanceIndex),
                emailAdress
        );
    }

    public void setNewChipBalance(double currentBalance) {
        this.currentChips = currentBalance;
        if (MainApp.EXECUTION_MODE != ExecutionMode.DEVELOPMENT) {
            try {
                Updater updated = new Updater();
                updated.performUpdateWithArgument("UPDATE balance b, user u SET b.chips = ?,b.lastUpdated = NOW() WHERE b.id = u.balance_id AND u.id = ?", String.valueOf(this.currentChips), String.valueOf(this.ID));
            } catch (SQLException ex) {
                Logger.getLogger(Spieler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addStat(String gameName, double bet, String result, double amount) {
        Updater updated = new Updater();
        updated.writeStatisticToDatabase(gameName, bet, result, amount);
    }

    public void setCurrentBalanceAndAddStatistic(double newBalance, String gameName, double bet, String result, double amount) {
        this.setNewChipBalance(newBalance);
        this.addStat(gameName, bet, result, amount);
    }

    public String getValidationCode() {
        if (this.validationCode.equals("")) {
            String secureCode = "";
            while (secureCode.length() < 10) {
                secureCode += String.valueOf(((char) (97 + new Random().nextInt(26))));
            }
            this.validationCode = secureCode;
        }
        return this.validationCode;
    }

    public void loadUserInformation() {
        UserUtil util = new UserUtil();
        this.currentChips = (int) util.loadCurrentBalanceFromGivenUsername(username);
        this.currentMoney = (int) util.loadCurrentMoneyFromUsername(username);
        this.ID = util.getIDFromUserByUsername(username);
    }

    public boolean isValidPlayer() {
        boolean isValid = new UserUtil().isValidUser(this);
        this.validated = isValid;
        if (isValid == true) {
            UserUtil util = new UserUtil();
            this.role = util.getUserRoleByUsername(this.username);
            this.ID = util.getIDFromUserByUsername(username);
            PlayerCentral.getInstance().setUser(this);
        }
        return isValid;
    }

    public void setNewPassword(String newPasswordHash, String newPasswordPlain) {
        this.password = newPasswordPlain;
        String statement = "Update user SET password = ? WHERE username = ?";
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        query.runQueryWithoutReturn(statement, newPasswordHash + ";-" + this.username);
    }

    public void setNewMoney(double newMoney) {
        this.currentMoney = newMoney;
        if (MainApp.EXECUTION_MODE != ExecutionMode.DEVELOPMENT) {
            try {
                Updater updated = new Updater();
                updated.performUpdateWithArgument("UPDATE balance b, user u SET b.money = ?, b.lastUpdated = NOW() WHERE b.id = u.balance_id AND u.id = ?", String.valueOf(this.currentMoney), String.valueOf(this.ID));
            } catch (SQLException ex) {
                Logger.getLogger(Spieler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public double getCurrentMoney() {
        return this.currentMoney;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getEmailAdress() {
        return this.emailAdress;
    }

    public int getID() {
        return this.ID;
    }

    public String getRole() {
        return this.role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public double getCurrentChipBalance() {
        return this.currentChips;
    }

    public boolean isValidValidationCode(String code) {
        return this.validationCode.equals(code);
    }

    public void setRole(String userRole) {
        this.role = userRole;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCurrentChips() {
        return this.currentChips;
    }

    @Override
    public String toString() {
        return "Spieler{" + "ID=" + ID + ", role=" + role + ", username=" + username + ", password=" + password + ", currentChips=" + currentChips + ", validated=" + validated + ", validationCode=" + validationCode + ", emailAdress=" + emailAdress + ", currentMoney=" + currentMoney + '}';
    }

}
