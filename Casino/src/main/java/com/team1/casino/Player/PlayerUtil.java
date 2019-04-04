/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Player;

import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.SelectQuery;
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
            SelectQuery query = new SelectQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
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

    public double loadCurrentBalanceFromGivenUsername(String username) {
        try {
            SelectQuery query = new SelectQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
            ArrayList<String> retreavedHashes = query.runQueryWithReturn("SELECT b.balance FROM Balance b, Player p WHERE p.balanceID = b.balanceID AND p.username = ?", username);
            if (retreavedHashes.isEmpty() == true) {
                return 0.0;
            } else {
                return Double.valueOf(retreavedHashes.get(0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
}
