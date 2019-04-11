/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Entity;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.cardValue;
        hash = 97 * hash + Objects.hashCode(this.imageLocation);
        hash = 97 * hash + Objects.hashCode(this.cardName);
        hash = 97 * hash + Objects.hashCode(this.cardColor);
        hash = 97 * hash + Objects.hashCode(this.cardType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaccaraCard other = (BaccaraCard) obj;
        if (this.cardValue != other.cardValue) {
            return false;
        }
        if (!Objects.equals(this.imageLocation, other.imageLocation)) {
            return false;
        }
        if (!Objects.equals(this.cardName, other.cardName)) {
            return false;
        }
        if (this.cardColor != other.cardColor) {
            return false;
        }
        if (this.cardType != other.cardType) {
            return false;
        }
        return true;
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
        //Certain symbols can only be one color, so color is not set but evaluated by the code.
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
