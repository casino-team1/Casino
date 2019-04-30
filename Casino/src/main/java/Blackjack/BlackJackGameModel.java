/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 *
 * @author albio
 */
public class BlackJackGameModel {

    private BlackJackSpielerModel spieler = new BlackJackSpielerModel();
    private BlackJackDealerModel dealer = new BlackJackDealerModel();

    private boolean unentschieden = false;

    private Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();

    //FXML
    private Button buttonStand;
    private Button buttonHit;
    private Label labelEinsatzFehler;
    private Button buttonStart;
    private Label labelKartenWertSpieler;
    private Label labelKartenWertDealer;
    private Label labelLösung;
    private TextField textfeldEinsatz;
    private Button buttonPrüfung;
    private Button buttonVerlassen;
    private Button buttonHelp;
    private Button buttonVerdoppeln;
    private Button buttonVersichern;
    private TextField textfeldVersicherung;
    private Label labelVerdoppeln;
    private Label labelVersicherung;
    private ImageView spielerKarte1;
    private ImageView spielerKarte2;
    private ImageView spielerKarte3;
    private ImageView spielerKarte4;
    private ImageView spielerKarte5;
    private ImageView dealerKarte1;
    private ImageView dealerKarte2;
    private ImageView dealerKarte3;
    private ImageView dealerKarte4;
    private ImageView dealerKarte5;

    public BlackJackGameModel(Button buttonHelp, Button buttonHit, Button buttonPrüfung, Button buttonStand, Button buttonStart, Button buttonVerdoppeln, Button buttonVerlassen, Button buttonVersichern, ImageView spielerKarte1, ImageView spielerKarte2, ImageView spielerKarte3, ImageView spielerKarte4, ImageView spielerKarte5, ImageView dealerKarte1, ImageView dealerKarte2, ImageView dealerKarte3, ImageView dealerKarte4, ImageView dealerKarte5, Label labelKartenWertSpieler, Label labelKartenWertDealer, Label labelLösung, Label labelVerdoppeln, Label labelVersicherung, TextField textfeldEinsatz, TextField textfeldVersicherung) {
        this.buttonHelp = buttonHelp;
        this.buttonHit = buttonHit;
        this.buttonPrüfung = buttonPrüfung;
        this.buttonStand = buttonStand;
        this.buttonStart = buttonStart;
        this.buttonVerdoppeln = buttonVerdoppeln;
        this.buttonVerlassen = buttonVerlassen;
        this.buttonVersichern = buttonVersichern;

        this.spielerKarte1 = spielerKarte1;
        this.spielerKarte2 = spielerKarte2;
        this.spielerKarte3 = spielerKarte3;
        this.spielerKarte4 = spielerKarte4;
        this.spielerKarte5 = spielerKarte5;
        this.dealerKarte1 = dealerKarte1;
        this.dealerKarte2 = dealerKarte2;
        this.dealerKarte3 = dealerKarte3;
        this.dealerKarte4 = dealerKarte4;
        this.dealerKarte5 = dealerKarte5;

        this.labelKartenWertSpieler = labelKartenWertSpieler;
        this.labelKartenWertDealer = labelKartenWertDealer;
        this.labelLösung = labelLösung;
        this.labelVerdoppeln = labelVerdoppeln;
        this.labelVersicherung = labelVersicherung;

        this.textfeldEinsatz = textfeldEinsatz;
        this.textfeldVersicherung = textfeldVersicherung;
    }

    public void play() {
        //Vorbereitung
        k.kartenErstellen();

        spieler.setGewonnen(false);
        dealer.setGewonnen(false);
        unentschieden = false;
        k.setAnzahlKartenImKartenDeck(52);
        k.setAnzahlKartenInKartenSymbole(51);
        spieler.setKartenWertSpieler(0);
        dealer.setKartenWertDealer(0);
        dealer.setKarteZweiWert(0);
        spieler.clearKartenSpieler();
        dealer.clearKartenDealer();
        karten.clear();
        kartenSymbole.clear();
        karten.putAll(k.getKarten());
        kartenSymbole.addAll(k.getKartenSymbole());
        labelLösung.setText("");

        //Karten mischen
        Collections.shuffle(kartenSymbole);

        //2 Zufallskarten verteilen an Spieler
        spieler.hit(karten, kartenSymbole, spielerKarte1, spielerKarte2, spielerKarte3, spielerKarte4, spielerKarte5, labelKartenWertSpieler);
        spieler.hit(karten, kartenSymbole, spielerKarte1, spielerKarte2, spielerKarte3, spielerKarte4, spielerKarte5, labelKartenWertSpieler);

        //Dealer zieht Karten
        dealer.firstHit(karten, kartenSymbole, dealerKarte1, dealerKarte2, dealerKarte3, dealerKarte4, dealerKarte5, labelKartenWertDealer);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            spieler.setKartenWertSpielerMinusTen();
        }

        if (spieler.getKartenWertSpieler() == 21) {
            spieler.setGewonnen(true);
            end();
            return;
        }

        if (spieler.getKartenWertSpieler() == 9 || spieler.getKartenWertSpieler() == 10 || spieler.getKartenWertSpieler() == 11) {
            buttonVerdoppeln.setDisable(false);
        }

        if (dealer.getKartenWertDealer() == 11) {
            buttonVersichern.setDisable(false);
            textfeldVersicherung.setDisable(false);
        }
    }

    public void spielerHit() {
        //Spieler zieht Karte
        spieler.hit(karten, kartenSymbole, spielerKarte1, spielerKarte2, spielerKarte3, spielerKarte4, spielerKarte5, labelKartenWertSpieler);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            if (spieler.getZufallskarte().contains("A")) {
                spieler.setKartenWertSpielerMinusTen();
                String j = String.valueOf(spieler.getKartenWertSpieler());
                labelKartenWertSpieler.setText(j);
                if (spieler.getKartenWertSpieler() > 21) {
                    dealer.setGewonnen(true);
                    end();
                }
            } else {
                dealer.setGewonnen(true);
                end();
            }
        }

        //Überprüfung, ob 21 erreicht wurde wurde
        if (spieler.getKartenWertSpieler() == 21) {
            spieler.setGewonnen(true);
            end();
        }
    }

    public void dealerRound(Label labelKartenWertDealer) {
        //Zweite Karte mitberechnen
        dealer.kartenWertDealerPlusKarteZwei();
        
        //dealer muss karten ziehen
        if(dealer.getKartenWertDealer() < 17){
            do{
                dealer.secondHit();
            }while(dealer.getKartenWertDealer() < 17);
        }
        

        //Anzeige leeren
        dealerKarte1.setImage(null);
        dealerKarte2.setImage(null);
        dealerKarte3.setImage(null);
        dealerKarte4.setImage(null);
        dealerKarte5.setImage(null);

        //Alle Karten vom Dealer anzeigen
        /*for (String s : dealer.getKartenDealer()) {
        labelKartenDealer.setText(labelKartenDealer.getText() + " , " + s);
        }*/
        for (int i = 0; i < dealer.getKartenDealer().size(); i++) {
            if (dealerKarte1.getImage() == null) {
                dealerKarte1.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte2.getImage() == null) {
                dealerKarte2.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte3.getImage() == null) {
                dealerKarte3.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte4.getImage() == null) {
                dealerKarte4.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte5.getImage() == null) {
                dealerKarte5.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            }
        }

        //Werte überprüfen
        if (dealer.getKartenWertDealer() > 21) {
            spieler.setGewonnen(true);
        } else if (dealer.getKartenWertDealer() > spieler.getKartenWertSpieler()) {
            dealer.setGewonnen(true);
        } else if (dealer.getKartenWertDealer() == spieler.getKartenWertSpieler()) {
            unentschieden = true;
        } else if (dealer.getKartenWertDealer() < spieler.getKartenWertSpieler()) {
            spieler.setGewonnen(true);
        } else {
        }

        end();
    }

    public void end() {
        //Eingaben blockieren
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldVersicherung.setDisable(true);
        buttonPrüfung.setDisable(false);
        textfeldEinsatz.setDisable(false);

        //Hat jemand gewonnen?
        if (spieler.hasGewonnen()) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealer.hasGewonnen()) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }
        if (unentschieden) {
            labelLösung.setText("UNENTSCHIEDEN!!");
        }
    }

    public void versichern() {
        //Zweiter Wert von Karte mitberechnen
        dealer.kartenWertDealerPlusKarteZwei();

        //Anzeige leeren
        dealerKarte1.setImage(null);
        dealerKarte2.setImage(null);
        dealerKarte3.setImage(null);
        dealerKarte4.setImage(null);
        dealerKarte5.setImage(null);

        //Alle Karten vom Dealer anzeigen
        /*for (String s : dealer.getKartenDealer()) {
        labelKartenDealer.setText(labelKartenDealer.getText() + " , " + s);
        }*/
        for (int i = 0; i < dealer.getKartenDealer().size(); i++) {
            if (dealerKarte1.getImage() == null) {
                dealerKarte1.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte2.getImage() == null) {
                dealerKarte2.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte3.getImage() == null) {
                dealerKarte3.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte4.getImage() == null) {
                dealerKarte4.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            } else if (dealerKarte5.getImage() == null) {
                dealerKarte5.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
                labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
            }
        }

        //Hat dealer BlackJack?
        if (dealer.getKartenWertDealer() == 21) {
            labelLösung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealer.getKartenWertDealer() < 21 || dealer.getKartenWertDealer() > 21) {
            labelLösung.setText("DEALER HAT GEWONNEN!!");
        }

        //Eingaben blockieren
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldVersicherung.setDisable(true);
        buttonPrüfung.setDisable(false);
        textfeldEinsatz.setDisable(false);
    }
}
