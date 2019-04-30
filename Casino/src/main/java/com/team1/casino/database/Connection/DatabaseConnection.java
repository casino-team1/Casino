package com.team1.casino.database.Connection;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection connection;
    private java.sql.Connection DATABASE_CONNECTION;

    private DatabaseConnection() {

    }

    public static DatabaseConnection getInstance() {
        if (connection == null) {
            connection = new DatabaseConnection();
        }
        return connection;
    }

    public void setDatabaseConnection(final java.sql.Connection DATABASE_CONNECTION) {
        this.DATABASE_CONNECTION = DATABASE_CONNECTION;
    }

    public java.sql.Connection getDatabaseConnection() {
        return this.DATABASE_CONNECTION;
    }

    public boolean IsHealthy() {
        try {
            return this.DATABASE_CONNECTION.isValid(1000);
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() throws SQLException {
        this.DATABASE_CONNECTION.close();
    }

}
