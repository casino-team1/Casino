package com.team1.casino.database;

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
    
    private void addStatToDatabase(){
        
    }

}
