/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

/**
 *
 * @author albio
 */
public class BlackJackGameModel {
    
    public void play(){
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
    }
    
}
