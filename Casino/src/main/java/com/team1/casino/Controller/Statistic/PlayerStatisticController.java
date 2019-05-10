/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller.Statistic;

import com.team1.casino.Entity.Statistic;
import com.team1.casino.Model.PlayerStatisticModel;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class PlayerStatisticController implements Initializable, Observer {

    private PlayerStatisticModel model;

    @FXML
    private ComboBox<String> userName;
    @FXML
    private LineChart<Number, Double> bettChangeChart;
    @FXML
    private TableView<Statistic> statisticTable;
    @FXML
    private TableColumn<Statistic, String> gameCol;
    @FXML
    private TableColumn<Statistic, String> betCol;
    @FXML
    private TableColumn<Statistic, String> ResCol;
    @FXML
    private TableColumn<Statistic, String> ChangeCol;
    @FXML
    private Button goBackButton;
    @FXML
    private TableColumn<Statistic, String> dateColumn;

    public void setPlayerStatisticModel(PlayerStatisticModel model) {
        this.model = model;
        for (String username : model.getUsernameListing()) {
            this.userName.getItems().add(username);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setHandlingOfStatsForTable();

    }

    private void setHandlingOfStatsForTable() {
        gameCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getGameName());
            } else {
                return new SimpleStringProperty("No Game");
            }
        });
        gameCol.setSortable(false);
        ResCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getResult());
            } else {
                return new SimpleStringProperty("No Result");
            }
        });
        ResCol.setSortable(false);
        betCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getBet()));
            } else {
                return new SimpleStringProperty("No Bet");
            }
        });
        betCol.setSortable(false);
        ChangeCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getUserAccountChange()));
            } else {
                return new SimpleStringProperty("No Change");
            }
        });
        ChangeCol.setSortable(false);
        dateColumn
                .setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
                    if (p.getValue() != null) {
                        return new SimpleStringProperty(String.valueOf(p.getValue().getDate()));
                    } else {
                        return new SimpleStringProperty("No Change");
                    }
                });
        dateColumn.setSortable(false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object arg) {
        PlayerStatisticModel playerModel = (PlayerStatisticModel) o;
        XYChart.Series<Number, Double> series = getPopulatedPlayerBalanceSeries();
        this.bettChangeChart.getData().add(series);
        this.bettChangeChart.setCreateSymbols(false);
        this.statisticTable.getItems().clear();
        playerModel.getStats().forEach((stat) -> {
            this.statisticTable.getItems().add(stat);
        });
    }

    private XYChart.Series<Number, Double> getPopulatedPlayerBalanceSeries() {
        ArrayList<Double> accountbalances = model.getAccountValues();
        XYChart.Series<Number, Double> series = new XYChart.Series<>();
        int counter = 0;
        this.bettChangeChart.getData().clear();
        series.getData().add(new XYChart.Data<>(counter, 0.0));
        counter++;
        for (double value : accountbalances) {
            series.getData().add(new XYChart.Data<>(counter, value));
            counter++;
        }
        series.setName(String.format("Statistik für %s", this.model.getSelectedPlayer().getValue()));
        return series;
    }

    @FXML
    private void selectionChanged(ActionEvent event) throws SQLException {
        this.model.setSelectePlayer(this.userName.getValue());
        this.model.displayStatsForSelectedPlayer();
    }

    @FXML
    private void goBackToMenu(ActionEvent event) {
        this.model.backToMenu();
    }

}
