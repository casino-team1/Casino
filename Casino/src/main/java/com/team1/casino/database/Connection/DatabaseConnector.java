/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.database.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nick Flückiger
 */
public class DatabaseConnector {

    private final String USERNAME;
    private final String PASSWORD;
    private final String DatabaseConnection_SEQUENCE;
    private final boolean DEBUG_MODE;
    private boolean ERROR_FLAG = false;

    public DatabaseConnector(final String HOST, final String PORT, final String DATABASENAME, final String USERNAME, final String PASSWORD, final boolean DEBUG_MODE) {
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.DEBUG_MODE = DEBUG_MODE;
        this.DatabaseConnection_SEQUENCE = constructDatabaseConnectionString(HOST, PORT, DATABASENAME);
    }

    private String constructDatabaseConnectionString(final String HOST, final String PORT, final String DATABASENAME) {
        return String.format("jdbc:mysql://%s:%s/%s", HOST, PORT, DATABASENAME);
    }

    public boolean getErrorFlagState() {
        return this.ERROR_FLAG;
    }

    public DatabaseConnection connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ERROR_FLAG = true;
        }
        try {
            java.sql.Connection databaseDatabaseConnection = DriverManager.getConnection(
                    this.DatabaseConnection_SEQUENCE + "?serverTimezone=UTC", this.USERNAME, this.PASSWORD);
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            databaseConnection.setDatabaseConnection(databaseDatabaseConnection);
            return databaseConnection;
        } catch (SQLException e) {
            ERROR_FLAG = true;
            if (this.DEBUG_MODE == true) {
                System.out.println("Was not able to connect to the database.. DatabaseConnection_sequence: " + this.DatabaseConnection_SEQUENCE);
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }
}
