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
import java.text.ParseException;
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
public class GameStatisticController implements Initializable, Observer {

    private GameStatisticModel model;
    @FXML
    private TableView<Statistic> gameStatTable;
    @FXML
    private LineChart<Number, Double> gameProfit;
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
    private ComboBox<String> statCounter;
    @FXML
    private Button back;
    @FXML
    private TableColumn<Statistic, String> dateColiumn;

    public void setGameStatistics(GameStatisticModel model) throws SQLException {
        this.model = model;
        this.gameNames.getItems().addAll(model.getGameNames());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setHandlingOfStatInformationForTable();
        addPossibleStatRanges();
    }

    private void addPossibleStatRanges() {
        this.statCounter.getItems().add(String.valueOf(10));
        this.statCounter.getItems().add(String.valueOf(20));
        this.statCounter.getItems().add(String.valueOf(100));
        this.statCounter.getItems().add(String.valueOf(200));
        this.statCounter.getItems().add(String.valueOf(300));
        this.statCounter.getItems().add(String.valueOf(400));
        this.statCounter.getItems().add(String.valueOf(500));
        this.statCounter.getItems().add("All");
    }

    private void setHandlingOfStatInformationForTable() {
        userCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getUsername());
            } else {
                return new SimpleStringProperty("No Game");
            }
        });
        userCol.setSortable(false);
        resultCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(p.getValue().getResult());
            } else {
                return new SimpleStringProperty("No Result");
            }
        });
        resultCol.setSortable(false);
        betCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getBet()));
            } else {
                return new SimpleStringProperty("No Bet");
            }
        });
        betCol.setSortable(false);
        changeCol.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getUserAccountChange()));
            } else {
                return new SimpleStringProperty("No Change");
            }
        });
        changeCol.setSortable(false);
        dateColiumn.setCellValueFactory((TableColumn.CellDataFeatures<Statistic, String> p) -> {
            if (p.getValue() != null) {
                return new SimpleStringProperty(String.valueOf(p.getValue().getDate()));
            } else {
                return new SimpleStringProperty("No Change");
            }
        });
        dateColiumn.setSortable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameStatisticModel gameModel = (GameStatisticModel) o;
        this.gameStatTable.getItems().clear();
        int maxNumberOfEntries = evaluateMaxNumberOfEntries();
        addStatistics(maxNumberOfEntries, gameModel);
        XYChart.Series<Number, Double> series = getPopulatedProfitChart(maxNumberOfEntries);
        this.gameProfit.getData().add(series);
        this.gameProfit.setCreateSymbols(false);
    }

    private int evaluateMaxNumberOfEntries() {
        int maxNumberOfEntries = -1;
        if (this.statCounter.getSelectionModel().getSelectedItem() != null) {
            if (this.statCounter.getSelectionModel().getSelectedItem().equals("All") == false) {
                maxNumberOfEntries = Integer.parseInt(this.statCounter.getSelectionModel().getSelectedItem());
            }
        }
        return maxNumberOfEntries;
    }

    private void addStatistics(int maxNumberOfEntries, GameStatisticModel gameModel) {
        for (Statistic stat : gameModel.getGameStats()) {
            if (maxNumberOfEntries != -1 && this.gameStatTable.getItems().size() >= maxNumberOfEntries) {
                break;
            }
            this.gameStatTable.getItems().add(stat);
        }
    }

    private XYChart.Series<Number, Double> getPopulatedProfitChart(int maxNumberOfEntries) {
        XYChart.Series<Number, Double> series = new XYChart.Series<>();
        int counter = 0;
        ArrayList<Double> loadedGameProfits = model.getGameProfits();
        this.gameProfit.getData().clear();
        for (double value : loadedGameProfits) {
            if (series.getData().size() >= maxNumberOfEntries && maxNumberOfEntries != -1) {
                break;
            }
            series.getData().add(new XYChart.Data<>(counter + 1, value));
            counter++;
        }
        series.setName(String.format("Statistik zu %s", this.model.getSelectedGame()));
        this.gameStatTable.setStyle("-fx-tick-labyel-fill:#fff");
        return series;
    }

    @FXML
    private void selectionChanged(ActionEvent event) throws SQLException, ParseException {
        this.model.setSelectedGame(this.gameNames.getSelectionModel().getSelectedItem());
        this.model.loadGameStats();
    }

    @FXML
    private void selectedStatCount(ActionEvent event) throws SQLException, ParseException {
        this.model.setSelectedGame(this.gameNames.getSelectionModel().getSelectedItem());
        this.model.loadGameStats();
    }

    @FXML
    private void goBackToMenu(ActionEvent event) {
        this.model.goBackToMenu();
    }

}
