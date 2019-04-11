/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller;

import com.team1.casino.Entity.Stat;
import com.team1.casino.Model.PlayerStatisticModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
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
public class PlayerStatisticController implements Initializable, Observer {

    private PlayerStatisticModel model;

    @FXML
    private ComboBox<String> nutzernamen;
    @FXML
    private LineChart<String, Double> kontostandChart;
    @FXML
    private TableView<Stat> statisticTable;
    @FXML
    private TableColumn<Stat, String> gameCol;
    @FXML
    private TableColumn<Stat, String> betCol;
    @FXML
    private TableColumn<Stat, String> ResCol;
    @FXML
    private TableColumn<Stat, String> ChangeCol;
    @FXML
    private Button back;

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
        gameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stat, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getGameName());
                } else {
                    return new SimpleStringProperty("No Game");
                }
            }
        });
        ResCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stat, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getResult());
                } else {
                    return new SimpleStringProperty("No Result");
                }
            }
        });
        betCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stat, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getBet()));
                } else {
                    return new SimpleStringProperty("No Bet");
                }
            }
        });
        ChangeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Stat, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getEndamount()));
                } else {
                    return new SimpleStringProperty("No Change");
                }
            }
        });
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
        this.statisticTable.getItems().clear();
        for (Stat stat : model.getStats()) {
            this.statisticTable.getItems().add(stat);
        }
    }

    @FXML
    private void selectionChanged(ActionEvent event) throws SQLException {
        this.model.setSelectePlayer(this.nutzernamen.getValue());
        this.model.displayStatsForSelectedPlayer();
    }

    @FXML
    private void goBackToMenu(ActionEvent event) {
        this.model.backToMenu();
    }

}
