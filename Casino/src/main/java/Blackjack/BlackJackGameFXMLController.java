/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.UserCentral;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author albio
 */
public class BlackJackGameFXMLController implements Initializable {

    private BlackJackGameModel game;
    private int einsatz;

    private MainApp main;

    private Stage stage;

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
    private Label labelKontostand;
    @FXML
    private Label labelKontostand1;
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
    private Label labelMöglichkeiten;
    @FXML
    private Label labelVerdoppeln;
    @FXML
    private Label labelLösung;
    @FXML
    private Label labelVersicherung;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label labelKartenWertSpieler;
    @FXML
    private Label labelKartenWertDealer;
    @FXML
    private Pane spielerKartenPane;
    @FXML
    private ImageView spielerKarte;
    @FXML
    private Pane dealerKartenPane;
    @FXML
    private ImageView dealerKarte;

    public void setMain(MainApp main) {
        this.main = main;
    }

    @FXML
    private void prüfungEinsatz(ActionEvent event) {
        Karten karten = new Karten();
        karten.kartenErstellen();
        try {
            einsatz = Integer.parseInt(textfeldEinsatz.getText());
            if (einsatz < 50) {

            } else {
                buttonStart.setDisable(false);
                buttonPrüfung.setDisable(true);
                textfeldEinsatz.setDisable(true);

            }
        } catch (NumberFormatException e) {

        }
    }

    @FXML
    private void startGame(ActionEvent event) {
        
        //Vorbereitung
        spielerKartenPane.getChildren().clear();
        dealerKartenPane.getChildren().clear();

        buttonStart.setDisable(true);
        textfeldEinsatz.setDisable(true);
        buttonPrüfung.setDisable(true);
        buttonHit.setDisable(false);
        buttonStand.setDisable(false);
        buttonVerdoppeln.setDisable(true);
        buttonVersichern.setDisable(true);
        labelKartenWertDealer.setText("");
        labelKartenWertSpieler.setText("");
        labelLösung.setText("");
        labelVerdoppeln.setText("");
        labelVersicherung.setText("");
    
        game.play();
    }

    @FXML
    private void zurueck(ActionEvent event) {
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
        game.dealerRound(labelKartenWertDealer);
    }

    @FXML
    private void stand(ActionEvent event) {
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        game.dealerRound(labelKartenWertDealer);
    }

    @FXML
    private void hit(ActionEvent event) {
        game.spielerHit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new BlackJackGameModel(buttonHelp, buttonHit, buttonPrüfung, buttonStand, buttonStart, buttonVerdoppeln, buttonVerlassen, buttonVersichern, spielerKartenPane, dealerKartenPane,
                labelKartenWertSpieler, labelKartenWertDealer, labelLösung, labelVerdoppeln, labelVersicherung, textfeldEinsatz, textfeldVersicherung);
        
        balanceLabel.setText("Konto: " + UserCentral.getInstance().getUser().getCurrentChipBalance() + "$");
    }

}
