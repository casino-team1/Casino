/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.control.Label;

/**
 *
 * @author albio
 */
public class BlackJackSpielerModel {
    
    private boolean gewonnen = false;

    private Random r = new Random();
    private int zufallszahl = 0;
    private String zufallskarte = "";

    public void hit(int anzahlKartenImKartendeck, int kartenWertSpieler, ArrayList<String> kartenSpieler, ArrayList<String> kartenSymbole, HashMap<String, Integer> karten, Label labelKartenSpieler) {
        
        /*//Hat es genügend Karten?
        if (anzahlKartenImKartendeck < 1) {
        //dealer.austeilen();
        }*/

        //Spieler zieht Karten
        zufallszahl = r.nextInt(51);
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.equals("J") || zufallskarte.equals("Q") || zufallskarte.equals("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.equals("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(zufallskarte);
        }
        kartenSpieler.add(zufallskarte);
        karten.remove(zufallskarte);
        anzahlKartenImKartendeck--;
        labelKartenSpieler.setText(labelKartenSpieler.getText() + "," + zufallskarte);
    }

    public void setGewonnen(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }
}
