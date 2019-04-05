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
public class BlackJackDealerModel {
    private ArrayList<String> kartenDealer = new ArrayList<>();
    boolean gewonnen = false;
    
    public void stand(){
        /*
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
    
    public void hit(){
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
        */
        
        stand();
    }
    
    public void austeilen(){
        
    }

    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }

    public boolean hasGewonnen() {
        return gewonnen;
    }
    
    public ArrayList<String> getKarten(){
        return kartenDealer;
    }
}
