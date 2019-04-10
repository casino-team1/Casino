/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Controller;

import com.team1.casino.Model.PlayerStatisticModel;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public void setPlayerStatisticModel(PlayerStatisticModel model) {
        this.model = model;
        for (String username : model.getUsernameListing()) {
            this.nutzernamen.getItems().add(username);
        }
    }

    private void bind() {
        this.model.getSelectedPlayer().bind(this.nutzernamen.getEditor().textProperty());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void selectionChanged(ActionEvent event) {
        this.model.displayStatsForSelectedPlayer();
    }

}
