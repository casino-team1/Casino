package com.team1.casino.Controller;

import com.team1.casino.Model.CasinoModel;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CasinoController implements Initializable, Observer {

    private Label label;

    private CasinoModel casinoModel;
    @FXML
    private Button logOutButton;

    public void setCasinoModel(CasinoModel model) {
        this.casinoModel = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void mouseClickBlackJack(MouseEvent event) {
        this.casinoModel.startBlackJack();
    }

    @FXML
    private void mouseClickBaccara(MouseEvent event) {
        this.casinoModel.startBaccara();
    }

    @FXML
    private void clickMouseRoulette(MouseEvent event) {
        this.casinoModel.startRoulette();
    }

    @FXML
    private void clickMouseYatzy(MouseEvent event) {
        this.casinoModel.startYatzy();
    }

    @FXML
    private void logUserOut(ActionEvent event) {
        this.casinoModel.logOutUser();
    }
}
