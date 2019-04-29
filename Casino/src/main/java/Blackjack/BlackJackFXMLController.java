
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author albio
 */
public class BlackJackFXMLController implements Initializable {

    BlackJackGameModel game;

    private MainApp main;

    private int einsatz;

    @FXML
    private TextField textfeldEinsatz;
    @FXML
    private Button buttonPrüfung;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonVerlassen;
    @FXML
    private Button buttonHelp;
    @FXML
    private Button buttonVersichern;
    @FXML
    private Button buttonVerdoppeln;
    @FXML
    private Button buttonStand;
    @FXML
    private Button buttonHit;
    @FXML
    private TextField textfeldVersicherung;
    @FXML
    private Label labelVerdoppeln;
    @FXML
    private Label labelLösung;
    @FXML
    private Label labelVersicherung;
    @FXML
    private Label labelKartenSpieler;
    @FXML
    private Label labelKartenDealer;
    @FXML
    private Label labelEinsatzFehler;

    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*game = new BlackJackGameModel(buttonHelp, buttonHit, buttonPrüfung, buttonStand, buttonStart, buttonVerdoppeln, buttonVerlassen, buttonVersichern,
        labelKartenSpieler, labelKartenDealer, labelLösung, labelVerdoppeln, labelVersicherung, labelEinsatzFehler,
        textfeldEinsatz, textfeldVersicherung);*/
    }

    public void setMain(MainApp main) {
        this.main = main;
    }

    @FXML
    private void stand(ActionEvent event) {
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        game.dealerRound();
    }

    @FXML
    private void hit(ActionEvent event) {
        game.spielerHit();
    }

    @FXML
    private void startGame(ActionEvent event) {
        //Vorbereitung
        labelKartenSpieler.setText("");
        labelKartenDealer.setText("");
        labelLösung.setText("");
        buttonStart.setDisable(true);
        textfeldEinsatz.setDisable(true);
        buttonPrüfung.setDisable(true);
        buttonHit.setDisable(false);
        buttonStand.setDisable(false);
        buttonVerdoppeln.setDisable(true);
        buttonVersichern.setDisable(true);
        textfeldVersicherung.setDisable(true);

        game.play();
    }

    @FXML
    private void prüfungEinsatz(ActionEvent event) {
        try {
            einsatz = Integer.parseInt(textfeldEinsatz.getText());
            if (einsatz < 50) {
                labelEinsatzFehler.setText("Der Einsatz muss mindestens 50 sein!");
            } else {
                buttonStart.setDisable(false);
                buttonPrüfung.setDisable(true);
                textfeldEinsatz.setDisable(true);
                labelEinsatzFehler.setText("Viel Spass!");
            }
        } catch (NumberFormatException e) {

            labelEinsatzFehler.setText("Bitte geben Sie ganze Zahlen ein!");
        }
    }

    @FXML
    private void zurueck(ActionEvent event) throws IOException {
        this.main.displayMainMenu();
    }

    @FXML
    private void help(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackHelp1FXML.fxml"));
        Parent root;
        root = (Parent) loader.load();
        Stage stageHelp = new Stage();
        this.stage = stageHelp;
        stageHelp.setTitle("Hilfe");
        stageHelp.setScene(new Scene(root, 750, 510));
        stageHelp.setResizable(false);
        BlackJackHelp1FXMLController cont = loader.getController();
        cont.setStage(stage);
        stageHelp.show();
    }

    @FXML
    private void verdoppeln(ActionEvent event) {
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldEinsatz.setDisable(true);
        textfeldVersicherung.setDisable(true);

        int i = Integer.parseInt(textfeldEinsatz.getText());
        String s = String.valueOf(i * 2);
        labelVerdoppeln.setText("Ihr Einsatz wurde erhöht auf " + s);
        game.spielerHit();
        game.dealerRound();
    }

    @FXML
    private void versichern(ActionEvent event) {
        try {
            int i;
            i = Integer.parseInt(textfeldVersicherung.getText());
            if (i > 0 || i % 2 == 1) {
                buttonHit.setDisable(true);
                buttonStand.setDisable(true);
                buttonVersichern.setDisable(true);
                buttonVerdoppeln.setDisable(true);
                textfeldEinsatz.setDisable(true);
                textfeldVersicherung.setDisable(true);
                labelVersicherung.setVisible(false);

                game.versichern();
            } else {
                labelVersicherung.setText("Bitte ganze Zahlen über 0 eingeben!");
            }
        } catch (Exception e) {
            labelVersicherung.setText("Bitte ganze Zahlen über 0 eingeben!");
        }
    }
}
