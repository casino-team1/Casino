/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Controller;

import Baccara.Model.BaccaraMenuModel;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import com.team1.casino.User.Spieler;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class BaccaraMenuViewController implements Initializable, Observer {

    @FXML
    private Button baccaraButton;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Text baccaraText;
    @FXML
    private Label welcomeMessage;
    @FXML
    private Label currentBalance;
    @FXML
    private ImageView cardView12;
    @FXML
    private ImageView cardView121;
    @FXML
    private ImageView cardView122;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private BaccaraMenuModel menuModel;

    public void setMenuModel(BaccaraMenuModel menuModel) {
        this.menuModel = menuModel;
        Spieler player = this.menuModel.getPlayer();
        if (player != null) {
            this.welcomeMessage.setText(String.format("Wilkommen %s", player.getUsername()));
            this.currentBalance.setText(String.format("Sie haben %s CHF", String.valueOf(player.getCurrentChipBalance())));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void startBaccara(ActionEvent event) {
        this.menuModel.startBaccara();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        this.menuModel.backToMainMenu();
    }

    @FXML
    private void exitBackToMenuButton(MouseEvent event) {
        backToMenuButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void enterBackToMenuButton(MouseEvent event) {
        backToMenuButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }

}
