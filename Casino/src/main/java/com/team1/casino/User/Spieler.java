/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.User;

import com.team1.casino.database.DatabaseConnection;
import com.team1.casino.database.DatabaseQuery;

/**
 *
 * @author Nick Flückiger
 */
public class Spieler extends User {

    public Spieler(String username, String password) {
        super(username, password);
    }

    @Override
    public void writeUserToDatabase() {
        String username = super.getUsername();
        String password = UserUtil.getHashedPassword(super.getPassword());
        String email = super.getEmailAdress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseQuery query = new DatabaseQuery(DatabaseConnection.getInstance().getDatabaseConnection(), false);
                int balanceIndex = query.runQueryGetAddedID("INSERT INTO balance(balance,lastUpdated) VALUES(?,CURDATE())", "1000.0");
                System.out.println(balanceIndex);
                query.runQueryWithoutReturn("INSERT INTO user(username,password,role,balance_id,email) VALUES(?,?,?,?,?)", username + ";-" + password + ";-" + "Player" + ";-" + String.valueOf(balanceIndex) + ";-"
                        + email
                );
                System.out.println("Inserted the information");
            }
        }).start();
    }
}
