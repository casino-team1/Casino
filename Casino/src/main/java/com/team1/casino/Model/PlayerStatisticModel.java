/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nick Flückiger
 */
public class PlayerStatisticModel {

    public ArrayList<String> getUsernameListing() {
        return usernameListing;
    }

    private SimpleStringProperty selectedPlayer = new SimpleStringProperty();

    public SimpleStringProperty getSelectedPlayer() {
        return selectedPlayer;
    }

    private ArrayList<String> usernameListing;

    public void loadusernames() throws SQLException {
        DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
        this.usernameListing = query.runQueryWithReturn("SELECT username FROM user WHERE role = ?", "Player");
    }

    public void displayStatsForSelectedPlayer() {

    }
}
