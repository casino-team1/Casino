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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author albio
 */
public class BlackJackSpielerModel {

    private int kartenWertSpieler = 0;
    
    private Karten k = new Karten();
    private HashMap<String, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<>();
    private ArrayList<String> kartenSpieler = new ArrayList<>();
    private String zufallskarte = "";

    private boolean gewonnen = false;

    public void hit(HashMap<String, Integer> karten, ArrayList<String> kartenSymbole, ImageView spielerKarte1, ImageView spielerKarte2, ImageView spielerKarte3, ImageView spielerKarte4, ImageView spielerKarte5) {
        //Parameter einfangen
        this.karten = karten;
        this.kartenSymbole = kartenSymbole;
        
        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            k.kartenErstellen();
            this.karten = k.getKarten();
        }
        
        //Zufällige Werte
        int zufallszahl = 0;
        Random r = new Random();

        //Spieler zieht Karten
        zufallszahl = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        zufallskarte = kartenSymbole.get(zufallszahl);

        if (zufallskarte.contains("10") || zufallskarte.contains("J") || zufallskarte.contains("Q") || zufallskarte.contains("K")) {
            kartenWertSpieler += 10;
        } else if (zufallskarte.contains("A")) {
            kartenWertSpieler += 11;
        } else {
            kartenWertSpieler += karten.get(("/images/GameCards/"+zufallskarte+".png"));
        }
        kartenSpieler.add(zufallskarte);
        karten.remove("/images/GameCards/" + zufallskarte + ".png");
        kartenSymbole.remove(zufallskarte);
        k.subAnzahlKartenImKartenDeck();
        k.subAnzahlKartenInKartenSymbole();
        //labelKartenSpieler.setText(labelKartenSpieler.getText() + " , " + zufallskarte);
        //Karten anzeigen
        if(spielerKarte1.getImage() == null){
            spielerKarte1.setImage(new Image("/images/GameCards/"+zufallskarte+".png"));
            return;
        }
        if(spielerKarte2.getImage() == null){
            spielerKarte2.setImage(new Image("/images/GameCards/"+zufallskarte+".png"));
            return;
        }
        if(spielerKarte3.getImage() == null){
            spielerKarte3.setImage(new Image("/images/GameCards/"+zufallskarte+".png"));
            return;
        }
        if(spielerKarte4.getImage() == null){
            spielerKarte4.setImage(new Image("/images/GameCards/"+zufallskarte+".png"));
            return;
        }
        if(spielerKarte5.getImage() == null){
            spielerKarte5.setImage(new Image("/images/GameCards/"+zufallskarte+".png"));
            return;
        }
        
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

    public void setKartenWertSpieler(int kartenWertSpieler) {
        this.kartenWertSpieler = kartenWertSpieler;
    }
    
    public void setKartenWertSpielerMinusTen() {
        kartenWertSpieler -= 10;
    }
    
    public String getZufallskarte() {
        return zufallskarte;
    }

    public ArrayList<String> getKartenSpieler() {
        return kartenSpieler;
    }
    
    public void clearKartenSpieler() {
        kartenSpieler.clear();
    }
   
}
