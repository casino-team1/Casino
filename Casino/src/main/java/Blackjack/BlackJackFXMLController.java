/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author albio
 */
public class BlackJackFXMLController implements Initializable {

    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private int kartenWertSpieler;
    private boolean spielerGewonnen = false;

    private int einsatz;

    private int anzahlKartenImKartendeck = 52;

    private ArrayList<String> kartenDealer = new ArrayList<>();
    private int kartenWertDealer;
    private int karteZweiWert;
    private boolean dealerGewonnen = false;

    private boolean unentschieden = false;

    private String zufallskarte = "";
    private int zufallszahl = 0;

    private Karten k = new Karten();

    private HashMap<String, Integer> karten = new HashMap<>();

    private ArrayList<String> kartenWerte = new ArrayList<>();

    Random r = new Random();

    @FXML
    private Button buttonStand;
    @FXML
    private Button buttonHit;
    @FXML
    private Label labelKartenSpieler;
    @FXML
    private Label labelKartenDealer;
    @FXML
    private Button buttonStart;
    @FXML
    private Label labelLösung;
    @FXML
    private TextField textfeldEinsatz;
    @FXML
    private Label labelEinsatzFehler;
    @FXML
    private Button buttonPrüfung;
    @FXML
    private Button buttonVerlassen;
    @FXML
    private Button buttonHelp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void stand(ActionEvent event) {
        /*//Zweiter Wert von Karte mitberechnen
        kartenWertDealer += karteZweiWert;
        
        //Hat es genügend Karten?
        if (anzahlKartenImKartendeck < 1) {
        this.karten = k.getKarten();
        }
        
        //Wenn Dealer unter 17 hat, muss er ziehen
        if (kartenWertDealer < 17) {
        while (kartenWertDealer < 17) {
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        
        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
        kartenWertDealer += 10;
        } else if (zufallskarte.equals("A")) {
        kartenWertDealer += 11;
        } else {
        kartenWertDealer += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        }
        }
        
        //Anzeige leeren
        labelKartenDealer.setText("");
        
        //Alle Karten anzeigen
        for (String s : kartenDealer) {
        labelKartenDealer.setText(labelKartenDealer.getText() + "," + s);
        }
        
        //Anzeigen der Karten
        if (kartenWertDealer > 21) {
        spielerGewonnen = true;
        }
        if (kartenWertDealer > kartenWertSpieler) {
        dealerGewonnen = true;
        }
        if (kartenWertDealer == kartenWertSpieler) {
        unentschieden = true;
        }
        if (kartenWertDealer < kartenWertSpieler) {
        spielerGewonnen = true;
        }
        
        //Hat jemand gewonnen?
        if (spielerGewonnen) {
        labelLösung.setText("SPIELER HAT GEWONNEN!!");
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        }
        if (dealerGewonnen) {
        labelLösung.setText("DEALER HAT GEWONNEN!!");
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        }
        if (unentschieden) {
        labelLösung.setText("UNENTSCHIEDEN");
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        }*/
    }

    @FXML
    private void hit(ActionEvent event
    ) {
        /*//Hat es genügend Karten?
        if (anzahlKartenImKartendeck < 1) {
        this.karten = k.getKarten();
        }
        
        //Spieler zieht Karten
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        
        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
        kartenWertSpieler += 10;
        } else if (zufallskarte.equals("A")) {
        kartenWertSpieler += 11;
        } else {
        kartenWertSpieler += karten.get(zufallskarte);
        }
        kartenSpieler.add(zufallskarte);
        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);
        
        //Überprüfung, ob 21 überschritten wurde
        if (kartenWertSpieler > 21) {
        dealerGewonnen = true;
        }
        
        //Hat jemand gewonnen?
        if (spielerGewonnen) {
        labelLösung.setText("SPIELER HAT GEWONNEN!!");
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        }
        if (dealerGewonnen) {
        labelLösung.setText("DEALER HAT GEWONNEN!!");
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        }*/
    }

    @FXML
    private void startGame(ActionEvent event) {
        /*//Vorbereitung
        buttonHit.setDisable(true);
        buttonStart.setDisable(true);
        
        kartenDealer.clear();
        kartenSpieler.clear();
        
        kartenWertSpieler = 0;
        kartenWertDealer = 0;
        karteZweiWert = 0;
        
        spielerGewonnen = false;
        dealerGewonnen = false;
        
        labelKartenDealer.setText("");
        labelKartenSpieler.setText("");
        labelLösung.setText("");
        
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        this.karten = k.getKarten();
        this.kartenWerte = k.getKartenWerte();
        
        //Karten mischen
        Collections.shuffle(kartenWerte);
        
        //Zufallskarten verteilen an Spieler
        for (int i = 0; i < 2; i++) {
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        
        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
        kartenWertSpieler += 10;
        } else if (zufallskarte.equals("A")) {
        kartenWertSpieler += 11;
        } else {
        kartenWertSpieler += karten.get(zufallskarte);
        }
        kartenSpieler.add(zufallskarte);
        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);
        }
        
        //Erste Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        
        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
        kartenWertDealer += 10;
        } else if (zufallskarte.equals("A")) {
        kartenWertDealer += 11;
        } else {
        kartenWertDealer += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        labelKartenDealer.setText(zufallskarte + " + ?");
        
        //Zweite unbekannte Karte an Dealer verteilen
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenWerte.get(zufallszahl);
        
        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("Q")) {
        karteZweiWert += 10;
        } else if (zufallskarte.equals("A")) {
        karteZweiWert += 11;
        } else {
        karteZweiWert += karten.get(zufallskarte);
        }
        kartenDealer.add(zufallskarte);
        karten.remove(zufallszahl);
        anzahlKartenImKartendeck--;
        
        buttonHit.setDisable(false);
        buttonStand.setDisable(false);*/
        
        new BlackJackGameModel().play();
    }

    @FXML
    private void prüfungEinsatz(ActionEvent event
    ) {
        try {
            einsatz = Integer.parseInt(textfeldEinsatz.getText());
        } catch (Exception e) {
            labelEinsatzFehler.setText("Sie haben nichts eingesetzt!");
        }

        if (einsatz < 50) {
            labelEinsatzFehler.setText("Ihr Einsatz ist zu klein -> mind. 50!");
        } else {
            labelEinsatzFehler.setText("Viel Spass beim Spielen!");
            buttonStart.setDisable(false);
        }

    }

    @FXML
    private void zurueck(ActionEvent event) {
        /*Stage stage = super.getMainApp().getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = null;
        try {
        root = (Parent) loader.load();
        } catch (IOException ex) {
        Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Casino Central");
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    private void help(ActionEvent event) {
    }
}
