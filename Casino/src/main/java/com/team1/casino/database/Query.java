package com.team1.casino.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Query {

    private final java.sql.Connection DATABASE_CONNECTION;

    private final boolean WRAP_IN_TRANSACTION;

    public java.sql.Connection getDATABASE_CONNECTION() {
        return this.DATABASE_CONNECTION;
    }

    protected Query(final Connection DATABASE_CONNECTION, boolean wrap_in_transaction) {
        this.DATABASE_CONNECTION = DATABASE_CONNECTION;
        this.WRAP_IN_TRANSACTION = wrap_in_transaction;
    }

    public abstract int runQueryGetAddedID(String query,String parameters);

    public abstract ArrayList<String> runQueryWithReturn(String querySequence, String arguments) throws SQLException;

    public abstract void runInsertionMethod();

    public abstract void runQueryWithoutReturn(String querySequence, String arguments);
}
