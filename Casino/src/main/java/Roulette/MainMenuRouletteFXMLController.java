/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roulette;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.PlayerCentral;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label balanceLabel;
    @FXML
    private ImageView mountainviewLogo;
    @FXML
    private Button rouletteEndButton;
    @FXML
    private ImageView rouletteButtons;

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
        mountainviewLogo.setOpacity(0);
        rouletteButtons.setOpacity(0);
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChipBalance());
        // TODO
        PauseTransition transition = new PauseTransition(Duration.millis(200));
        PauseTransition transition2 = new PauseTransition(Duration.millis(500));
        transition.setOnFinished(x -> showLogo());
        transition.play();
        transition2.setOnFinished(x -> showButtons());
        transition2.play();
    }

    public void showLogo() {
        FadeTransition ft = new FadeTransition(Duration.millis(1500), rouletteLogo);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        FadeTransition ft2 = new FadeTransition(Duration.millis(1500), mountainviewLogo);
        ft2.setFromValue(0);
        ft2.setToValue(1);
        ft2.play();

    }

    public void showButtons() {
        FadeTransition ft3 = new FadeTransition(Duration.millis(1500), rouletteButtons);
        ft3.setFromValue(0);
        ft3.setToValue(1);
        ft3.play();
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
