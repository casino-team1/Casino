/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author albio
 */
public class Karten {

    private HashMap<Image, Integer> karten = new HashMap<>();
    private ArrayList<String> kartenSymbole = new ArrayList<String>();

    private String[] farben = new String[]{"♥", "♦", "♣", "♠"};

    private int anzahlKartenImKartenDeck = 52;
    private int anzahlKartenInKartenSymbole = 51;
    
    public void kartenErstellen() {        
        /*karten.clear();
        kartenSymbole.clear();
        
        //Kartendeck (für jede Farbe eine Karte)
        for (int i = 2; i <= 10; i++) {
        for (String f : farben) {
        String j = String.valueOf(i);
        karten.put(j + f, i);
        //kartenSymbole
        kartenSymbole.add(j + f);
        }
        
        }
        //Erstellen Karten und Kartenwert
        String[] special = {"J", "Q", "K"};
        for (int i = 0; i < 4; i++) {
        for (String spec : special) {
        for (String f : farben) {
        karten.put(spec + f, 10);
        kartenSymbole.add(spec + f);
        }
        }
        }
        //A || Kartenwert und Karten
        for (int i = 0; i < 4; i++) {
        for (String f : farben) {
        karten.put("A" + f, 11);
        kartenSymbole.add("A" + f);
        }
        }*/
        
        karten.clear();
        kartenSymbole.clear();
        
        //Kartendeck (für jede Farbe eine Karte)
        Image twoC = new Image("/images/GameCards/2C");
        Image twoD = new Image("/images/GameCards/2D");
        Image twoH = new Image("/images/GameCards/2H");
        Image twoS = new Image("/images/GameCards/2S");
        
        Image threeC = new Image("/images/GameCards/2C");
        Image threeD = new Image("/images/GameCards/2D");
        Image threeH = new Image("/images/GameCards/2H");
        Image threeS = new Image("/images/GameCards/2S");
        
        Image fourC = new Image("/images/GameCards/2C");
        Image fourD = new Image("/images/GameCards/2D");
        Image fourH = new Image("/images/GameCards/2H");
        Image fourS = new Image("/images/GameCards/2S");
        
        Image fiveC = new Image("/images/GameCards/2C");
        Image fiveD = new Image("/images/GameCards/2D");
        Image fiveH = new Image("/images/GameCards/2H");
        Image fiveS = new Image("/images/GameCards/2S");
        
        Image sixC = new Image("/images/GameCards/2C");
        Image sixD = new Image("/images/GameCards/2D");
        Image sixH = new Image("/images/GameCards/2H");
        Image sixS = new Image("/images/GameCards/2S");
        
        Image sevenC = new Image("/images/GameCards/2C");
        Image sevenD = new Image("/images/GameCards/2D");
        Image sevenH = new Image("/images/GameCards/2H");
        Image sevenS = new Image("/images/GameCards/2S");
        
        Image eightC = new Image("/images/GameCards/2C");
        Image eightD = new Image("/images/GameCards/2D");
        Image eightH = new Image("/images/GameCards/2H");
        Image eightS = new Image("/images/GameCards/2S");
        
        Image nineC = new Image("/images/GameCards/2C");
        Image nineD = new Image("/images/GameCards/2D");
        Image nineH = new Image("/images/GameCards/2H");
        Image nineS = new Image("/images/GameCards/2S");
        
        Image tenC = new Image("/images/GameCards/2C");
        Image tenD = new Image("/images/GameCards/2D");
        Image tenH = new Image("/images/GameCards/2H");
        Image tenS = new Image("/images/GameCards/2S");
        
        Image jackC = new Image("/images/GameCards/2C");
        Image jackD = new Image("/images/GameCards/2D");
        Image jackH = new Image("/images/GameCards/2H");
        Image jackS = new Image("/images/GameCards/2S");
        
        Image queenC = new Image("/images/GameCards/2C");
        Image queenD = new Image("/images/GameCards/2D");
        Image queenH = new Image("/images/GameCards/2H");
        Image queenS = new Image("/images/GameCards/2S");
        
        Image kingC = new Image("/images/GameCards/2C");
        Image kingD = new Image("/images/GameCards/2D");
        Image kingH = new Image("/images/GameCards/2H");
        Image kingS = new Image("/images/GameCards/2S");
        
        Image aceC = new Image("/images/GameCards/2C");
        Image aceD = new Image("/images/GameCards/2D");
        Image aceH = new Image("/images/GameCards/2H");
        Image aceS = new Image("/images/GameCards/2S");
        
        karten.put(twoC, 2);
        karten.put(twoD, 2);
        karten.put(twoH, 2);
        karten.put(twoS, 2);
        
        karten.put(threeC, 3);
        karten.put(threeD, 3);
        karten.put(threeH, 3);
        karten.put(threeS, 3);
        
        karten.put(fourC, 4);
        karten.put(fourD, 4);
        karten.put(fourH, 4);
        karten.put(fourS, 4);
        
        karten.put(fiveC, 5);
        karten.put(fiveD, 5);
        karten.put(fiveH, 5);
        karten.put(fiveS, 5);
        
        karten.put(sixC, 6);
        karten.put(sixD, 6);
        karten.put(sixH, 6);
        karten.put(sixS, 6);
        
        karten.put(sevenC, 7);
        karten.put(sevenD, 7);
        karten.put(sevenH, 7);
        karten.put(sevenS, 7);
        
        karten.put(eightC, 8);
        karten.put(eightD, 8);
        karten.put(eightH, 8);
        karten.put(eightS, 8);
        
        karten.put(nineC, 9);
        karten.put(nineD, 9);
        karten.put(nineH, 9);
        karten.put(nineS, 9);
        
        karten.put(tenC, 10);
        karten.put(tenD, 10);
        karten.put(tenH, 10);
        karten.put(tenS, 10);
        
        karten.put(jackC, 10);
        karten.put(jackD, 10);
        karten.put(jackH, 10);
        karten.put(jackS, 10);
        
        karten.put(queenC, 10);
        karten.put(queenD, 10);
        karten.put(queenH, 10);
        karten.put(queenS, 10);
        
        karten.put(kingC, 10);
        karten.put(kingD, 10);
        karten.put(kingH, 10);
        karten.put(kingS, 10);
        
        karten.put(aceC, 11);
        karten.put(aceD, 11);
        karten.put(aceH, 11);
        karten.put(aceS, 11);
        
    }

    public HashMap<Image, Integer> getKarten() {
        return karten;
    }

    public ArrayList<String> getKartenSymbole() {
        return kartenSymbole;
    }

    public int getAnzahlKartenImKartenDeck() {
        return anzahlKartenImKartenDeck;
    }

    public void subAnzahlKartenImKartenDeck() {
        this.anzahlKartenImKartenDeck--;
    }

    public void setAnzahlKartenImKartenDeck(int anzahlKartenImKartenDeck) {
        this.anzahlKartenImKartenDeck = anzahlKartenImKartenDeck;
    }

    public void subAnzahlKartenInKartenSymbole() {
        this.anzahlKartenInKartenSymbole--;
    }

    public void setAnzahlKartenInKartenSymbole(int zahl) {
        this.anzahlKartenInKartenSymbole = zahl;
    }

    public int getAnzahlKartenInKartenSymbole() {
        return anzahlKartenInKartenSymbole;
    }
    
}
