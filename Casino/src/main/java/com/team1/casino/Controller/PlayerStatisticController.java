/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller;

import com.team1.casino.Model.PlayerStatisticModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class PlayerStatisticController implements Initializable, Observer {

    private PlayerStatisticModel model;

    @FXML
    private ComboBox<String> nutzernamen;
    @FXML
    private LineChart<String, Double> kontostandChart;

    public void setPlayerStatisticModel(PlayerStatisticModel model) {
        this.model = model;
        for (String username : model.getUsernameListing()) {
            this.nutzernamen.getItems().add(username);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object arg) {
        PlayerStatisticModel model = (PlayerStatisticModel) o;
        ArrayList<Double> accountbalances = model.getAccountValues();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        int counter = 0;
        this.kontostandChart.getData().clear();
        for (double value : accountbalances) {
            series.getData().add(new XYChart.Data<>(String.valueOf(counter + 20), value));
            counter++;
        }
        this.kontostandChart.getData().add(series);
    }

    @FXML
    private void selectionChanged(ActionEvent event) throws SQLException {
        this.model.setSelectePlayer(this.nutzernamen.getValue());
        this.model.displayStatsForSelectedPlayer();
    }

}
