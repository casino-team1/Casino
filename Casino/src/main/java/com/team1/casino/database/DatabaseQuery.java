package com.team1.casino.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseQuery extends Query {

    public DatabaseQuery(final Connection DATABASE_CONNECTION, final boolean wrap_in_transaction) {
        super(DATABASE_CONNECTION, wrap_in_transaction);
    }

    @Override
    public ArrayList<String> runQueryWithReturn(String querySequence, String... arguments) throws SQLException {
        PreparedStatement statement = super.getDATABASE_CONNECTION().prepareStatement(querySequence);
        ArrayList<String> resultSet = new ArrayList<>();
        for (int i = 0; i < arguments.length; i++) {
            statement.setString(i + 1, arguments[i]);
        }
        ResultSet queryResult = statement.executeQuery();
        try {
            while (queryResult.next()) {
                int numColumns = queryResult.getMetaData().getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    resultSet.add(queryResult.getString(i));
                }
            }
        } finally {
            try {
                queryResult.close();
            } catch (SQLException ignore) {
                ignore.printStackTrace();
                return null;
            }
        }
        return resultSet;
    }

    @Override
    public void runQueryWithoutReturn(String querySequence, String... parameter) {
        try {
            PreparedStatement statement = super.getDATABASE_CONNECTION().prepareStatement(querySequence);
            ArrayList<String> resultSet = new ArrayList<>();
            int counter = 1;
            for (String argument : parameter) {
                statement.setString(counter, argument);
                counter++;
            }
            int retn = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int runQueryGetAddedID(String query, String... parameters) {
        PreparedStatement statement;
        try {
            statement = super.getDATABASE_CONNECTION().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(parameters[i]);
                statement.setString(i + 1, parameters[i]);
            }
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                return -1;
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void runInsertionMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
