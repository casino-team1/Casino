/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.User.Util.PlayerCentral;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author albio
 */
public class BlackJackGameModel {

    private BlackJackSpielerModel spieler = new BlackJackSpielerModel();
    private BlackJackDealerModel dealer = new BlackJackDealerModel();

    private boolean unentschieden = false;

    private int einsatz;
    private int gewinn;

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
    private Label labelLoesung;
    private TextField textfeldEinsatz;
    private Button buttonPruefung;
    private Button buttonVerlassen;
    private Button buttonHelp;
    private Button buttonVerdoppeln;
    private Button buttonVersichern;
    private TextField textfeldVersicherung;
    private Label labelVerdoppeln;
    private Label labelVersicherung;
    private Pane spielerKartenPane;
    private Pane dealerKartenPane;
    private Label balanceLabel;

    public BlackJackGameModel() {
    }

    public BlackJackGameModel(Button buttonHelp, Button buttonHit, Button buttonPruefung, Button buttonStand, Button buttonStart, Button buttonVerdoppeln, Button buttonVerlassen, Button buttonVersichern, Pane spielerKartenPane, Pane dealerKartenPane, Label labelKartenWertSpieler, Label labelKartenWertDealer, Label labelLoesung, Label labelVerdoppeln, Label labelVersicherung, Label balanceLabel, TextField textfeldEinsatz, TextField textfeldVersicherung) {
        this.buttonHelp = buttonHelp;
        this.buttonHit = buttonHit;
        this.buttonPruefung = buttonPruefung;
        this.buttonStand = buttonStand;
        this.buttonStart = buttonStart;
        this.buttonVerdoppeln = buttonVerdoppeln;
        this.buttonVerlassen = buttonVerlassen;
        this.buttonVersichern = buttonVersichern;

        this.spielerKartenPane = spielerKartenPane;
        this.dealerKartenPane = dealerKartenPane;

        this.labelKartenWertSpieler = labelKartenWertSpieler;
        this.labelKartenWertDealer = labelKartenWertDealer;
        this.labelLoesung = labelLoesung;
        this.labelVerdoppeln = labelVerdoppeln;
        this.labelVersicherung = labelVersicherung;
        this.balanceLabel = balanceLabel;

        this.textfeldEinsatz = textfeldEinsatz;
        this.textfeldVersicherung = textfeldVersicherung;
    }

    public void play() {
        //Einsatz entgegen nehmen
        einsatz = Integer.parseInt(textfeldEinsatz.getText());
        double restlichesGeld = PlayerCentral.getInstance().getUser().getCurrentChipBalance() - einsatz;
        PlayerCentral.getInstance().getUser().setNewChipBalance(restlichesGeld);
        balanceLabel.setText("Konto: " + restlichesGeld + "$");
        //Vorbereitung
        k.kartenErstellen();

        spieler.setGewonnen(false);
        dealer.setGewonnen(false);
        unentschieden = false;
        k.setAnzahlKartenImKartenDeck(52);
        k.setAnzahlKartenInKartenSymbole(51);
        spieler.setKartenWertSpieler(0);
        spieler.setxKoordinate(0);
        dealer.setKartenWertDealer(0);
        dealer.setKarteZweiWert(0);
        dealer.setxKoordinate(0);
        spieler.clearKartenSpieler();
        dealer.clearKartenDealer();
        karten.clear();
        kartenSymbole.clear();
        karten.putAll(k.getKarten());
        kartenSymbole.addAll(k.getKartenSymbole());
        labelLoesung.setText("");

        //Karten mischen
        Collections.shuffle(kartenSymbole);

        //Spieler zieht Karten
        spieler.firstHit(karten, kartenSymbole, spielerKartenPane, labelKartenWertSpieler);

        //Dealer zieht Karten
        dealer.firstHit(karten, kartenSymbole, dealerKartenPane, labelKartenWertDealer);

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
        spieler.hit(karten, kartenSymbole, spielerKartenPane, labelKartenWertSpieler);

        //Überprüfung, ob 21 überschritten wurde
        if (spieler.getKartenWertSpieler() > 21) {
            if (spieler.getKartenSpieler().contains("AC") || spieler.getKartenSpieler().contains("AD") || spieler.getKartenSpieler().contains("AH") || spieler.getKartenSpieler().contains("AS")) {
                spieler.setKartenWertSpielerMinusTen();
                String j = String.valueOf(spieler.getKartenWertSpieler());
                labelKartenWertSpieler.setText("(" + j + ")");
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
        if (dealer.getKartenWertDealer() < 17) {
            do {
                dealer.secondHit();
            } while (dealer.getKartenWertDealer() < 17);
        }

        //Anzeige leeren
        dealerKartenPane.getChildren().clear();
        dealer.setxKoordinate(0);

        //Alle Karten vom Dealer anzeigen
        //erste Karte
        ImageView neueDealerKarte = new ImageView();
        dealerKartenPane.getChildren().add(neueDealerKarte);
        neueDealerKarte.setLayoutX(dealer.getxKoordinate());
        neueDealerKarte.setLayoutY(dealer.getyKoordinate());
        neueDealerKarte.setFitWidth(dealer.getKarteWidth());
        neueDealerKarte.setFitHeight(dealer.getKarteHeight());
        neueDealerKarte.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(0) + ".png"));

        //Restliche Karten
        for (int i = 1; i < dealer.getKartenDealer().size(); i++) {
            ImageView neusteDealerKarte = new ImageView();
            dealerKartenPane.getChildren().add(neusteDealerKarte);
            neusteDealerKarte.setLayoutX(dealer.getxKoordinate() + 34);
            dealer.setxKoordinate(dealer.getxKoordinate() + 34);
            neusteDealerKarte.setLayoutY(dealer.getyKoordinate());
            neusteDealerKarte.setFitWidth(dealer.getKarteWidth());
            neusteDealerKarte.setFitHeight(dealer.getKarteHeight());
            neusteDealerKarte.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
        }
        labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");

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

    public void versichern() {
        //Zweiter Wert von Karte mitberechnen
        dealer.kartenWertDealerPlusKarteZwei();

        //Anzeige zurücksetzen
        dealerKartenPane.getChildren().clear();
        dealer.setxKoordinate(0);

        //Alle Karten vom Dealer anzeigen
        //erste Karte
        ImageView neueDealerKarte = new ImageView();
        dealerKartenPane.getChildren().add(neueDealerKarte);
        neueDealerKarte.setLayoutX(dealer.getxKoordinate());
        neueDealerKarte.setLayoutY(dealer.getyKoordinate());
        neueDealerKarte.setFitWidth(dealer.getKarteWidth());
        neueDealerKarte.setFitHeight(dealer.getKarteHeight());
        neueDealerKarte.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(0) + ".png"));
        labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");

        //restliche Karten
        for (int i = 1; i < dealer.getKartenDealer().size(); i++) {
            ImageView neusteDealerKarte = new ImageView();
            dealerKartenPane.getChildren().add(neusteDealerKarte);
            neusteDealerKarte.setLayoutX(dealer.getxKoordinate() + 34);
            dealer.setxKoordinate((int) neusteDealerKarte.getX());
            neusteDealerKarte.setLayoutY(dealer.getyKoordinate());
            neusteDealerKarte.setFitWidth(dealer.getKarteWidth());
            neusteDealerKarte.setFitHeight(dealer.getKarteHeight());
            neusteDealerKarte.setImage(new Image("/images/GameCards/" + dealer.getKartenDealer().get(i) + ".png"));
            labelKartenWertDealer.setText("(" + dealer.getKartenWertDealer() + ")");
        }

        //Hat dealer BlackJack?
        if (dealer.getKartenWertDealer() == 21) {
            labelLoesung.setText("SPIELER HAT GEWONNEN!!");
        }
        if (dealer.getKartenWertDealer() < 21 || dealer.getKartenWertDealer() > 21) {
            labelLoesung.setText("DEALER HAT GEWONNEN!!");
        }

        //Eingaben blockieren
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldVersicherung.setDisable(true);
        buttonPruefung.setDisable(false);
        textfeldEinsatz.setDisable(false);
    }

    public void end() {
        //Eingaben blockieren
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
        buttonVersichern.setDisable(true);
        buttonVerdoppeln.setDisable(true);
        textfeldVersicherung.setDisable(true);
        buttonPruefung.setDisable(false);
        textfeldEinsatz.setDisable(false);

        //Hat jemand gewonnen?
        if (spieler.hasGewonnen()) {
            if (spieler.getKartenWertSpieler() == 21) {
                gewinnBerechnungBlackJack();
            } else {
                gewinnBerechnung();
            }
        }
        if (dealer.hasGewonnen()) {
            verloren();
        }
        if (unentschieden) {
            unentschieden();
        }
    }

    public void gewonnenDurchBlackJack() {
        //Gewinnberechnung       
        int gewinn = einsatz + ((einsatz * 3) / 2);
        labelLoesung.setText("SIE HABEN " + gewinn + "$ GEWONNEN!");
        PlayerCentral.getInstance().getUser().setNewChipBalance(PlayerCentral.getInstance().getUser().getCurrentChips() + gewinn);
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChips() + "$");
    }

    public void gewonnen() {
        //Gewinnberechnung
        int gewinn = (Integer.parseInt(textfeldEinsatz.getText()) * 2);
        labelLoesung.setText("SIE HABEN " + gewinn + "$ GEWONNEN!");
        PlayerCentral.getInstance().getUser().setNewChipBalance(PlayerCentral.getInstance().getUser().getCurrentChips() + gewinn);
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChips() + "$");
    }

    public void unentschieden() {
        labelLoesung.setText("UNENTSCHIEDEN!");
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChips() + "$");
    }

    public void verloren() {
        labelLoesung.setText("SIE HABEN VERLOREN!");
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChips() + "$");
    }

    public int gewinnBerechnungBlackJack() {
        gewinn = einsatz + ((einsatz * 3) / 2);
        return gewinn;
    }

    public int gewinnBerechnung() {
        gewinn = einsatz * 2;
        return gewinn;
    }

    public int getEinsatz() {
        return einsatz;
    }

    public void setEinsatz(int einsatz) {
        this.einsatz = einsatz;
    }

    public int getGewinn() {
        return gewinn;
    }

    public void setGewinn(int gewinn) {
        this.gewinn = gewinn;
    }
}
