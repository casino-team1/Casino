/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Player;

import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Nick Flückiger
 */
public class PlayerUtil {

    public static String getHashedPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }

    private boolean validPassword(String givenPassword, String hashedPassword) {
        boolean isValidPassword = BCrypt.checkpw(givenPassword, hashedPassword);
        return isValidPassword;
    }

    public boolean isValidPlayer(Player player) {
        String storedPassword = retreaveStoredPlayerPassword(player.getUsername());
        if (storedPassword.equals("None")) {
            return false;
        }
        if (validPassword(player.getPassword(), storedPassword) == true) {
            return true;
        }
        return false;
    }

    public String retreaveStoredPlayerPassword(String username) {
        try {
            DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
            ArrayList<String> retreavedHashes = query.runQueryWithReturn("SELECT password FROM Player WHERE username = ?", username);
            if (retreavedHashes.isEmpty() == true) {
                return "None";
            } else {
                return retreavedHashes.get(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "None";
    }

    public boolean isUniqueUsername(String username) {
        try {
            DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
            ArrayList<String> retreavedHashes = query.runQueryWithReturn("SELECT username FROM Player WHERE username = ?", username);
            if (retreavedHashes.isEmpty() == true) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public double loadCurrentBalanceFromGivenUsername(String username) {
        try {
            DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
            ArrayList<String> retreavedInfromation = query.runQueryWithReturn("SELECT b.balance FROM Balance b, Player p WHERE p.balanceID = b.balanceID AND p.username = ?", username);
            if (retreavedInfromation.isEmpty() == true) {
                return 0.0;
            } else {
                return Double.valueOf(retreavedInfromation.get(0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    public static void updatePlayerBalance(String username, double currentBalance) {
        try {
            DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
            ArrayList<String> elements = query.runQueryWithReturn("SELECT balanceID FROM Player WHERE username = ?", username);
            int balanceID = Integer.parseInt(elements.get(0));
            query.runQueryWithoutReturn(String.format("UPDATE Balance SET balance = %s WHERE balanceID = %s", String.valueOf(currentBalance), String.valueOf(balanceID)));
        } catch (SQLException ex) {
            Logger.getLogger(PlayerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
