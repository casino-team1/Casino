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
public class BlackJackSpielerModel {

    private int cardsValuePlayer = 0;

    private int xCord = 0;
    private int yCord = 6;
    private int cardWidth = 167;
    private int cardHeigth = 237;

    private Karten k = new Karten();
    private HashMap<String, Integer> cards = new HashMap<>();
    private ArrayList<String> cardSymbols = new ArrayList<>();
    private ArrayList<String> playerCards = new ArrayList<>();
    private String randomCard = "";

    private boolean won = false;

    public void firstHit(HashMap<String, Integer> cards, ArrayList<String> cardSymbols, Pane playerCardPane, Label playerCardValueLabel) {
        //Parameter einfangen
        this.cards = cards;
        this.cardSymbols = cardSymbols;
        //Hat es genügend Karten?
        if (k.getCardsInDeck() < 1 || k.getAmountOfCardsInCardsSymbole() < 1) {
            k.kartenErstellen();
            this.cards = k.getKarten();
        }
        //Zufällige Werte
        int randomNumber = 0;
        Random r = new Random();
        randomCard = "";
        //Spieler zieht Karten
        randomNumber = r.nextInt(k.getAmountOfCardsInCardsSymbole());
        randomCard = cardSymbols.get(randomNumber);
        if (randomCard.contains("10") || randomCard.contains("J") || randomCard.contains("Q") || randomCard.contains("K")) {
            cardsValuePlayer += 10;
        } else if (randomCard.contains("A")) {
            cardsValuePlayer += 11;
        } else {
            cardsValuePlayer += cards.get(("/images/GameCards/" + randomCard + ".png"));
        }
        playerCards.add(randomCard);
        cards.remove("/images/GameCards/" + randomCard + ".png");
        cardSymbols.remove(randomCard);
        k.subAmountOfCardsInDeck();
        k.subAmountOfCardSymbols();

        //Karte anzeigen
        ImageView playerCard = new ImageView();
        playerCardPane.getChildren().add(playerCard);
        playerCard.setLayoutX(xCord);
        playerCard.setLayoutY(yCord);
        playerCard.setFitWidth(cardWidth);
        playerCard.setFitHeight(cardHeigth);
        playerCard.setImage(new Image("/images/GameCards/" + randomCard + ".png"));
        //zweites Mal Karte ziehen
        //Zufällige Werte
        randomNumber = 0;
        r = new Random();
        randomCard = "";
        //Spieler zieht Karten
        randomNumber = r.nextInt(k.getAmountOfCardsInCardsSymbole());
        randomCard = cardSymbols.get(randomNumber);
        if (randomCard.contains("10") || randomCard.contains("J") || randomCard.contains("Q") || randomCard.contains("K")) {
            cardsValuePlayer += 10;
        } else if (randomCard.contains("A")) {
            cardsValuePlayer += 11;
        } else {
            cardsValuePlayer += cards.get(("/images/GameCards/" + randomCard + ".png"));
        }
        playerCards.add(randomCard);
        cards.remove("/images/GameCards/" + randomCard + ".png");
        cardSymbols.remove(randomCard);
        k.subAmountOfCardsInDeck();
        k.subAmountOfCardSymbols();

        //Karte anzeigen
        ImageView latestPlayerCard = new ImageView();
        playerCardPane.getChildren().add(latestPlayerCard);
        latestPlayerCard.setLayoutX(xCord += 34);
        latestPlayerCard.setLayoutY(yCord);
        latestPlayerCard.setFitWidth(cardWidth);
        latestPlayerCard.setFitHeight(cardHeigth);
        latestPlayerCard.setImage(new Image("/images/GameCards/" + randomCard + ".png"));
        playerCardValueLabel.setText("(" + cardsValuePlayer + ")");
    }

    public void hit(HashMap<String, Integer> cards, ArrayList<String> cardSymbols, Pane playerCardPane, Label playerCardValueLabel) {
        //Hat es genügend Karten?
        if (k.getCardsInDeck() < 1) {
            k.kartenErstellen();
            this.cards = k.getKarten();
        }
        int zufallszahl = 0;
        Random r = new Random();
        randomCard = "";
        //Spieler zieht Karten
        zufallszahl = r.nextInt(k.getAmountOfCardsInCardsSymbole());
        randomCard = cardSymbols.get(zufallszahl);
        if (randomCard.contains("10") || randomCard.contains("J") || randomCard.contains("Q") || randomCard.contains("K")) {
            cardsValuePlayer += 10;
        } else if (randomCard.contains("A")) {
            cardsValuePlayer += 11;
        } else {
            cardsValuePlayer += cards.get(("/images/GameCards/" + randomCard + ".png"));
        }
        playerCards.add(randomCard);
        cards.remove("/images/GameCards/" + randomCard + ".png");
        cardSymbols.remove(randomCard);
        k.subAmountOfCardsInDeck();
        k.subAmountOfCardSymbols();
        //Karte anzeigen
        ImageView latestPlayerCardValues = new ImageView();
        playerCardPane.getChildren().add(latestPlayerCardValues);
        latestPlayerCardValues.setLayoutX(xCord += 34);
        latestPlayerCardValues.setLayoutY(yCord);
        latestPlayerCardValues.setFitWidth(cardWidth);
        latestPlayerCardValues.setFitHeight(cardHeigth);
        latestPlayerCardValues.setImage(new Image("/images/GameCards/" + randomCard + ".png"));
        playerCardValueLabel.setText("(" + cardsValuePlayer + ")");
    }

    public void setWon(boolean g) {
        this.won = g;
    }

    public boolean hasWon() {
        return won;
    }

    public int getPlayerCardValues() {
        return cardsValuePlayer;
    }

    public void setPlayerCardValues(int i) {
        this.cardsValuePlayer = i;
    }

    public void subPlayerCardValuesByTen() {
        cardsValuePlayer -= 10;
    }

    public String getRandomNumber() {
        return randomCard;
    }

    public ArrayList<String> getPlayerCards() {
        return playerCards;
    }

    public void clearPlayerCards() {
        playerCards.clear();
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int i) {
        this.xCord = i;
    }

    public int getyCord() {
        return yCord;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public int getCardHeigth() {
        return cardHeigth;
    }

}
