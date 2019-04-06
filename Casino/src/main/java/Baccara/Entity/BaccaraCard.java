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
    private final String imageLocation;

    @Override
    public String toString() {
        return "BaccaraCard{" + "cardValue=" + cardValue + ", imageLocation=" + imageLocation + ", cardName=" + cardName + ", cardColor=" + cardColor + ", cardType=" + cardType + '}';
    }

    private final String cardName;
    private final BaccaraCardColor cardColor;
    private final BaccaraCardType cardType;

    public BaccaraCard(BaccaraCardType type, String cardName, int cardValue) {
        this.cardType = type;
        this.cardColor = evaluateCardColor(type);
        this.cardName = cardName + " " + this.cardType.toString();
        this.cardValue = cardValue;
        this.imageLocation = concatinateImageLocation(cardName, type);
    }

    private String concatinateImageLocation(String cardName, BaccaraCardType type) {
        return String.valueOf(cardName.toUpperCase().toCharArray()[0]) + "" + (type.toString().toUpperCase().toCharArray()[0]) + ".jpg";
    }

    private BaccaraCardColor evaluateCardColor(BaccaraCardType type) {
        BaccaraCardColor color;
        if (type == BaccaraCardType.DIAMON || type == BaccaraCardType.HEART) {
            color = BaccaraCardColor.RED;
        } else {
            color = BaccaraCardColor.BLACK;
        }
        return color;
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
