/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roulette;

import com.team1.casino.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lukas Gilgen Schule
 */
public class MainMenuRouletteFXMLController implements Initializable {

    private Roulette play;

    private MainApp mainApp;

    public void setMainApplication(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private ImageView rouletteLogo;

    public void setPlayRoulette(Roulette playing) {
        this.play = playing;
    }

    @FXML
    private Button rouletteStartButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rouletteLogo.setOpacity(0);
        // TODO
        PauseTransition transition = new PauseTransition(Duration.millis(500));
        transition.setOnFinished(x -> showLogo());
        transition.play();
    }

    public void showLogo() {

        FadeTransition ft = new FadeTransition(Duration.millis(2500), rouletteLogo);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void rouletteStart(ActionEvent event) {
        play.displayGame();
    }

    @FXML
    private void endGame(ActionEvent event) {
        this.mainApp.displayMainMenu();
    }

}
