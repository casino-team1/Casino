/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Entity;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraCard {

    private int cardValue;
    private String imageLocation;

    @Override
    public String toString() {
        return "BaccaraCard{" + "cardValue=" + cardValue + ", imageLocation=" + imageLocation + ", cardName=" + cardName + ", cardColor=" + cardColor + ", cardType=" + cardType + '}';
    }
    private String cardName;
    private final BaccaraCardColor cardColor;
    private final BaccaraCardType cardType;

    public BaccaraCard(BaccaraCardType type, String cardName, int cardValue) {
        this.cardType = type;
        if (type == BaccaraCardType.DIAMON || type == BaccaraCardType.HEART) {
            this.cardColor = BaccaraCardColor.RED;
        } else {
            this.cardColor = BaccaraCardColor.BLACK;
        }
        this.cardName = cardName + " " + this.cardType.toString();
        this.cardValue = cardValue;
    }

    public int getCardValue() {
        return cardValue;
    }

    private void evaluateImageLink() {

    }

    public String getImageLocation() {
        return this.imageLocation;
    }

    public String getCardName() {
        return this.cardName;
    }
}
