/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;

/**
 *
 * @author albio
 */
public class BlackJackSpielerModel {
    Karten k = new Karten();
    
    private ArrayList<String> kartenSpieler = new ArrayList<>();
    boolean gewonnen = false;
    
    public void stand(){
        
    }
    
    public void hit(){
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
        
        stand();
    }
    
    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }
    
    public ArrayList<String> getKarten(){
        return kartenSpieler;
    }
}

