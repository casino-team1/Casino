/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Model;

import com.team1.casino.MainApp;
import java.sql.SQLException;
import java.util.Observable;

/**
 *
 * @author Nick Flückiger
 */
public class StatisticModel extends Observable {

    private MainApp mainApplication;

    public StatisticModel(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    public void displayPlayerStatistic() throws SQLException {
        this.mainApplication.displayPlayerStatistic();
    }

    public void displayGameStatistic() {
        this.mainApplication.displayGameStatistic();
    }

}
