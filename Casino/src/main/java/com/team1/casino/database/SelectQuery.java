package com.team1.casino.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectQuery extends Query {

    public SelectQuery(final Connection DATABASE_CONNECTION, final boolean wrap_in_transaction) {
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
                return null;
            }
        }
        return resultSet;
    }

    @Override
    public void runQueryWithoutReturn(String querySequence) {
    }
}
