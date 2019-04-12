/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Controller;

import Baccara.Entity.BaccaraCard;
import Baccara.Model.BaccaraGameModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class BaccaraGameViewController implements Initializable, Observer {

    @FXML
    private AnchorPane anchro;
    @FXML
    private ImageView firstLeftCard;
    @FXML
    private HBox playerSide;
    @FXML
    private ImageView thirdLeftCard;
    @FXML
    private ImageView secondLeftCard;
    @FXML
    private HBox dealerSide;
    @FXML
    private ImageView startGameView;
    @FXML
    private ImageView firstRightCard;
    @FXML
    private ImageView secondRightCard;
    @FXML
    private Text playerCardCountLabel;
    @FXML
    private Text dealerCardCountLabel;
    @FXML
    private ImageView chipImage;

    private Dragboard content;
    @FXML
    private ImageView bankerBet;
    @FXML
    private ImageView tieBet;
    @FXML
    private ImageView playerBet;
    @FXML
    private ImageView thirdRightImage;
    @FXML
    private Text winner;
    @FXML
    private ImageView bankerBetCoin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCardBacks();
        /*
            Code below checks for drag and drop and sets image at new position.
         */
        this.chipImage.setOnDragDetected((event) -> {
            content = chipImage.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent data = new ClipboardContent();
            Image image = chipImage.getImage();
            data.putImage(image);
            content.setContent(data);
            event.consume();
        });
        this.bankerBet.setOnMouseClicked(event -> {
            this.bankerBetCoin.setImage(this.chipImage.getImage());
            this.gameModel.setDealerBet(5);
        });
    }
    private BaccaraGameModel gameModel;

    public void setBaccaraGameModel(BaccaraGameModel gameModel) {
        this.gameModel = gameModel;
        bind();
    }

    private void setCardBacks() {
        ImageView[] imageViews = {this.firstLeftCard, this.secondLeftCard, this.firstRightCard, this.secondRightCard};
        for (ImageView imageView : imageViews) {
            imageView.setImage(new Image("/images/GameCards/cardBack.jpeg"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setCardBacks();
    }

    public void bind() {
    }

    @FXML
    private void startBaccara(MouseEvent event) throws InterruptedException {
        this.gameModel.generateCards();
        resetImageViews();
        String format = "/images/GameCards/%s";
        setCardCount();
        this.gameModel.checkForCardDraw();
        checkForNewCards(format);
        this.winner.setText(this.gameModel.determineWinner());
    }

    private void checkForNewCards(String linkFormat) {
        ArrayList<BaccaraCard> playerCards = this.gameModel.getPlayerCards();
        ArrayList<BaccaraCard> dealerCards = this.gameModel.getDealerCards();
        /*
            Check if a third card has been drawn by the algorithm following the rules of Baccara.
         */
        try {
            if (playerCards.size() == 3) {
                this.thirdLeftCard.setImage(new Image(String.format(linkFormat, playerCards.get(2).getImageLocation())));
            }
            if (dealerCards.size() == 3) {
                this.thirdRightImage.setImage(new Image(String.format(linkFormat, dealerCards.get(2).getImageLocation())));
            }
        } catch (Exception e) {

        }
        setCardCount();
    }

    private RotateTransition createRotator(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(1500), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(360);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(10);
        return rotator;
    }

    private void flipCardsArround(String format) {
        //set cards
        ImageView[] playerView = {this.firstLeftCard, this.secondLeftCard};
        ImageView[] dealerView = {this.firstRightCard, this.secondRightCard};
        ArrayList<BaccaraCard> playerCards = this.gameModel.getPlayerCards();
        ArrayList<BaccaraCard> dealerCard = this.gameModel.getDealerCards();
        for (int i = 0; i < 2; i++) {
            try {
                playerView[i].setImage(new Image(String.format(format, playerCards.get(i).getImageLocation())));
                dealerView[i].setImage(new Image(String.format(format, dealerCard.get(i).getImageLocation())));
            } catch (Exception e) {
            }
        }
    }

    private void resetImageViews() {
        this.bankerBetCoin.setImage(null);
        ImageView[] imageViews = {this.firstLeftCard, this.secondLeftCard, this.thirdLeftCard, this.firstRightCard, this.secondRightCard, this.thirdRightImage};
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView();
        }
        this.thirdRightImage.setRotate(90);
        this.thirdLeftCard.setRotate(90);
    }

    private void setCardCount() {
        //Update the card count for player and dealer by the gameModel values.
        int playerCardCount = this.gameModel.getPlayerCardCount();
        int dealerCardCount = this.gameModel.getDealerCardCount();
        this.playerCardCountLabel.setText("Spieler Kartenwert: " + String.valueOf(playerCardCount));
        this.dealerCardCountLabel.setText("Dealer Kartenwert: " + String.valueOf(dealerCardCount));
    }
    private static int betAmont = 0;

    @FXML
    private void setBankerBet(DragEvent event) {
        ImageView chipContainer = new ImageView(this.content.getImage());
        chipContainer.localToParent(this.bankerBet.getX(), this.bankerBet.getY());
        chipContainer.setVisible(true);
    }

    @FXML
    private void setTieBet(DragEvent event) {

    }

    @FXML
    private void setPlayerBet(DragEvent event) {
    }

    @FXML
    private void dropCoin(MouseDragEvent event) {
        System.out.println(this.bankerBet.getX());
        System.out.println(this.bankerBet.getY());
    }

}
