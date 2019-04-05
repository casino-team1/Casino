/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author albio
 */
public class BlackJackFXMLController implements Initializable {
    
    private int kartenWertSpieler;
    private String kartenSpieler;
    
    private String kartenDealer;
    private int kartenWertDealer;
    
    private HashMap<String, Integer> karten = new HashMap<>();
    
    private ArrayList<String> kartenWerte = new ArrayList<String>();

    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void stand(ActionEvent event) {
    }

    @FXML
    private void hit(ActionEvent event) {
    }

    @FXML
    private void startGame(ActionEvent event) {
        Karten k = new Karten();
        this.karten = k.getKarten();
        this.kartenWerte = k.getKartenWerte();
        String zufallskarten = "";
        
        //Zufallskarten verteilen an Spieler
        for(int i = 0; i <2; i++) {
            int zufallszahl = r.nextInt(12);
            zufallskarten = zufallskarten + "+" + kartenWerte.get(zufallszahl);
            kartenWertSpieler += Integer.parseInt(zufallskarten);
            kartenWerte.remove(zufallszahl);
            labelKartenSpieler.setText(zufallskarten);
        }
        
        //Zufallskarten verteilen an Dealer
        for(int i = 0; i <2; i++) {
            int zufallszahl = r.nextInt(12);
            zufallskarten = zufallskarten + "+" + kartenWerte.get(zufallszahl); 
            kartenWertDealer += Integer.parseInt(zufallskarten);
            labelKartenDealer.setText(zufallskarten);
        }
        
    }
    
}
