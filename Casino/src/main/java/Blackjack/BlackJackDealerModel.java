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
import javafx.scene.layout.Pane;

/**
 *
 * @author albio
 */
public class BlackJackDealerModel {

    private int cardValueDealer = 0;
    private int secondCardValue = 0;

    private int xCord = 0;
    private int yCord = 6;
    private int cardWidth = 167;
    private int cardHeight = 237;

    Karten k = new Karten();
    private HashMap<String, Integer> cards = new HashMap<>();
    private ArrayList<String> cardSymbols = new ArrayList<>();
    private ArrayList<String> dealerCards = new ArrayList<>();
    private String randomCard = "";
    private Random r = new Random();
    private boolean gewonnen = false;

    public void firstHit(HashMap<String, Integer> cards, ArrayList<String> cardSymbol, Pane dealerCardPane, Label labelCardValue) {
        //Parameter einfangen
        this.cards = cards;
        this.cardSymbols = cardSymbol;
        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            k.kartenErstellen();
            this.cards = k.getKarten();
        }
        //zufällige Werte
        int randomNumber = 0;
        //Erste Karte an Dealer verteilen
        randomNumber = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        randomCard = cardSymbol.get(randomNumber);
        if (randomCard.contains("10") || randomCard.contains("J") || randomCard.contains("Q") || randomCard.contains("K")) {
            cardValueDealer += 10;
        } else if (randomCard.contains("A")) {
            cardValueDealer += 11;
        } else {
            cardValueDealer += cards.get("/images/GameCards/" + randomCard + ".png");
        }
        dealerCards.add(randomCard);
        cards.remove("/images/GameCards/" + randomCard + ".png");
        cardSymbol.remove(randomCard);
        k.subAmountOfCardsInDeck();
        k.subAmountOfCardSymbols();
        //Karte anzeigen
        ImageView dealerCard = new ImageView();
        dealerCardPane.getChildren().add(dealerCard);
        dealerCard.setLayoutX(xCord);
        dealerCard.setLayoutY(yCord);
        dealerCard.setFitWidth(cardWidth);
        dealerCard.setFitHeight(cardHeight);
        dealerCard.setImage(new Image("/images/GameCards/" + randomCard + ".png"));
        labelCardValue.setText("(" + cardValueDealer + ")");
        //Zweite unbekannte Karte an Dealer verteilen
        randomNumber = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        randomCard = cardSymbol.get(randomNumber);
        if (randomCard.contains("10") || randomCard.contains("J") || randomCard.contains("Q") || randomCard.contains("K")) {
            secondCardValue += 10;
        } else if (randomCard.contains("A")) {
            secondCardValue += 11;
        } else {
            secondCardValue += cards.get("/images/GameCards/" + randomCard + ".png");
        }
        dealerCards.add(randomCard);
        cards.remove("/images/GameCards/" + randomCard + ".png");
        cardSymbol.remove(randomCard);
        k.subAmountOfCardsInDeck();
        k.subAmountOfCardSymbols();
    }

    public void secondHit() {
        //Hat es genügend Karten?
        if (k.getAnzahlKartenImKartenDeck() < 1) {
            k.kartenErstellen();
            this.cards = k.getKarten();
        }
        //zufällige Werte
        int randomNumber = 0;
        randomCard = "";
        //Wenn Dealer unter 17 hat, muss er ziehen
        randomNumber = r.nextInt(k.getAnzahlKartenInKartenSymbole());
        randomCard = cardSymbols.get(randomNumber);
        if (randomCard.contains("10") || randomCard.contains("J") || randomCard.contains("Q") || randomCard.contains("K")) {
            cardValueDealer += 10;
        } else if (randomCard.contains("A")) {
            cardValueDealer += 11;
        } else {
            cardValueDealer += cards.get("/images/GameCards/" + randomCard + ".png");
        }
        dealerCards.add(randomCard);
        cards.remove("/images/GameCards/" + randomCard + ".png");
        cardSymbols.remove(randomCard);
        k.subAmountOfCardsInDeck();
        k.subAmountOfCardSymbols();
    }

    public void setWon(boolean g) {
        this.gewonnen = g;
    }

    public boolean hasWon() {
        return gewonnen;
    }

    public int getDealerCardValue() {
        return cardValueDealer;
    }

    public void setDealerCardValue(int i) {
        this.cardValueDealer = i;
    }

    public void addDealerCardValues() {
        cardValueDealer += secondCardValue;
    }

    public String getRandomCard() {
        return randomCard;
    }

    public void setRandomCard(String s) {
        this.randomCard = s;
    }

    public int getSecondCardValue() {
        return secondCardValue;
    }

    public void setSecondCardValue(int i) {
        this.secondCardValue = i;
    }

    public ArrayList<String> getDealerCards() {
        return dealerCards;
    }

    public void clearDealerCards() {
        dealerCards.clear();
    }

    public int getxKoordinate() {
        return xCord;
    }

    public void setxKoordinate(int i) {
        this.xCord = i;
    }

    public int getyKoordinate() {
        return yCord;
    }

    public int getKarteWidth() {
        return cardWidth;
    }

    public int getKarteHeight() {
        return cardHeight;
    }
}
