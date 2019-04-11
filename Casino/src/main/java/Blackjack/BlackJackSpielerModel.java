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

    private int kartenWertSpieler = 0;

    private boolean gewonnen = false;

    private Random r = new Random();
    private int zufallszahl = 0;
    private String zufallskarte = "";

    public void hit() {

    }

    public void setGewonnen(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }
    
    public int getKartenWertSpieler() {
        return kartenWertSpieler;
    }
}
