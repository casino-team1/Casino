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
    public ArrayList<String> runQueryWithReturn(String querySequence, String arguments) throws SQLException {
        PreparedStatement statement = super.getDATABASE_CONNECTION().prepareStatement(querySequence);
        ArrayList<String> resultSet = new ArrayList<>();
        statement.setString(1, arguments);
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
    public void runQueryWithoutReturn(String querySequence, String parameter) {
        try {
            PreparedStatement statement = super.getDATABASE_CONNECTION().prepareStatement(querySequence);
            ArrayList<String> resultSet = new ArrayList<>();
            int counter = 1;
            String[] elements = parameter.split(";-");
            for (String argument : elements) {
                statement.setString(counter, argument);
                counter++;
            }
            System.out.println(statement.toString());
            int retn = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int runQueryGetAddedID(String query, String parameter) {
        PreparedStatement statement = null;
        try {
            statement = super.getDATABASE_CONNECTION().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, parameter);
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
