/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller.Statistic;

import com.team1.casino.Entity.Statistic;
import com.team1.casino.Model.GameStatisticModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class GameStatisticController implements Initializable, Observer {

    private GameStatisticModel model;
    @FXML
    private TableView<Statistic> gameStatTable;
    @FXML
    private LineChart<String, Double> gameProfit;
    @FXML
    private ComboBox<String> gameNames;
    @FXML
    private TableColumn<Statistic, String> userCol;
    @FXML
    private TableColumn<Statistic, String> betCol;
    @FXML
    private TableColumn<Statistic, String> resultCol;
    @FXML
    private TableColumn<Statistic, String> changeCol;
    @FXML
    private ComboBox<Integer> statCounter;
    @FXML
    private Button back;

    public void setGameStatistics(GameStatisticModel model) throws SQLException {
        this.model = model;
        this.gameNames.getItems().addAll(model.getGameNames());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setHandlingOfStatInformationForTable();
        addPossibleStatRanges();
    }

    private void addPossibleStatRanges() {
        this.statCounter.getItems().add(10);
        this.statCounter.getItems().add(20);
        this.statCounter.getItems().add(50);
        this.statCounter.getItems().add(100);
    }

    private void setHandlingOfStatInformationForTable() {
        userCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getUsername());
            } else {
                return new SimpleStringProperty("No Game");
            }
        });

        resultCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getResult());
            } else {
                return new SimpleStringProperty("No Result");
            }
        });
        betCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getBet()));
            } else {
                return new SimpleStringProperty("No Bet");
            }
        });
        changeCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getEndamount()));
            } else {
                return new SimpleStringProperty("No Change");
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        GameStatisticModel gameModel = (GameStatisticModel) o;
        this.gameStatTable.getItems().clear();
        int maxNumberOfEntries = -1;
        if (this.statCounter.getSelectionModel().getSelectedItem() != null) {
            maxNumberOfEntries = this.statCounter.getSelectionModel().getSelectedItem();
        }
        for (Statistic stat : gameModel.getGameStats()) {
            if (maxNumberOfEntries != -1 && this.gameStatTable.getItems().size() >= maxNumberOfEntries) {
                break;
            }
            this.gameStatTable.getItems().add(stat);
        }
        XYChart.Series<String, Double> series = getPopulatedProfitChart(maxNumberOfEntries);
        this.gameProfit.getData().add(series);
        this.gameProfit.setCreateSymbols(false);
    }

    private XYChart.Series<String, Double> getPopulatedProfitChart(int maxNumberOfEntries) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        int counter = 0;
        ArrayList<Double> gameProfit = model.getGameProfits();
        this.gameProfit.getData().clear();
        for (double value : gameProfit) {
            if (series.getData().size() >= maxNumberOfEntries && maxNumberOfEntries != -1) {
                break;
            }
            series.getData().add(new XYChart.Data<>(String.valueOf(counter + 1), value));
            counter++;
        }
        return series;
    }

    @FXML
    private void selectionChanged(ActionEvent event) throws SQLException {
        this.model.setSelectedGame(this.gameNames.getSelectionModel().getSelectedItem());
        this.model.loadGameStats();
    }

    @FXML
    private void selectedStatCount(ActionEvent event) throws SQLException {
        this.model.setSelectedGame(this.gameNames.getSelectionModel().getSelectedItem());
        this.model.loadGameStats();
    }

    @FXML
    private void goBackToMenu(ActionEvent event) {
        this.model.goBackToMenu();
    }

}
