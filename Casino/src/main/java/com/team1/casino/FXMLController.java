package com.team1.casino;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLController implements Initializable, Observer {

    @FXML
    private Label label;
    @FXML
    private Button blackJack;
    @FXML
    private Button roulette;
    @FXML
    private Button baccara;
    @FXML
    private Button yatzy;

    private CasinoModel casinoModel;

    public void setCasinoModel(CasinoModel model) {
        this.casinoModel = model;
    }

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void startBlackJack(ActionEvent event) {
        this.casinoModel.startBlackJack();
    }

    @FXML
    private void startRoulette(ActionEvent event) {
        this.casinoModel.startRoulette();
    }

    @FXML
    private void startBaccara(ActionEvent event) {
        this.casinoModel.startBaccara();
    }

    @FXML
    private void startYatzy(ActionEvent event) {
        this.casinoModel.startYatzy();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
