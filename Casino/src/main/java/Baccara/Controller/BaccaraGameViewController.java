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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private ImageView bankerBetCoin;
    @FXML
    private ImageView winnerCard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCardBacks();
        /*
            Code below checks for drag and drop and sets image at new position.
         */
        this.winnerCard.setVisible(false);
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
            imageView.setImage(new Image("/images/GameCards/cardBack.png"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setCardBacks();
    }

    private void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;
            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();
            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    public void bind() {
    }

    @FXML
    private void startBaccara(MouseEvent event) throws InterruptedException {
        this.gameModel.generateCards();
        resetImageViews();
        String format = "/images/GameCards/%s";
        ImageView[] playerView = {this.firstLeftCard, this.secondLeftCard};
        ImageView[] dealerView = {this.firstRightCard, this.secondRightCard};
        setCardCount();
        rotateCards(playerView, dealerView, format);
        this.gameModel.checkForCardDraw();
        //checkForNewCards(format);

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

    private void rotateCards(ImageView[] playerView, ImageView[] dealerView, String format) {
        ImageView[][] views = {playerView, dealerView};
        RotateTransition[] rotators = new RotateTransition[4];
        int index = 0;
        for (ImageView view : playerView) {
            rotators[index] = createRotator(view, 0, -90, 750, 1);
            index++;
        }
        for (ImageView view : dealerView) {
            rotators[index] = createRotator(view, 0, -90, 750, 1);
            index++;
        }
        for (int i = 0; i < rotators.length; i++) {
            rotators[i].play();
            if (i == 0) {
                rotators[i].setOnFinished((ActionEvent event) -> {
                    flipCardsArround(format, playerView, dealerView);
                    for (ImageView[] viewList : views) {
                        for (ImageView view : viewList) {
                            RotateTransition rot = createRotator(view, -87, 0, 750, 1);
                            rot.play();
                            rot.setOnFinished(handler -> {
                                String winner = this.gameModel.determineWinner();
                                switch (winner) {
                                    case "Player":
                                    case "Dealer":
                                    case "Tie":
                                        this.winnerCard.setImage(new Image(String.format(format, "playerWon.png")));
                                        centerImage(winnerCard);
                                        winnerCard.setVisible(true);
                                        RotateTransition rotate = createRotator(winnerCard, 0, 360, 4000, 3);
                                        rotate.play();
                                        rotate.setOnFinished(change -> {
                                            winnerCard.setVisible(false);
                                            resetImageViews();
                                            setCardBacks();
                                        });
                                        break;
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    private RotateTransition createRotator(Node card, int startAngle, int endAngle, int duration, int cicle) {
        RotateTransition rotator = new RotateTransition(Duration.millis(duration), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(startAngle);
        rotator.setToAngle(endAngle);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    private void flipCardsArround(String format, ImageView[] playerView, ImageView[] dealerView) {
        //set cards
        ArrayList<BaccaraCard> playerCards = this.gameModel.getPlayerCards();
        ArrayList<BaccaraCard> dealerCard = this.gameModel.getDealerCards();
        for (int i = 0; i < 2; i++) {
            try {
                Image playerImage = new Image(String.format(format, playerCards.get(i).getImageLocation()));
                Image dealerImage = new Image(String.format(format, dealerCard.get(i).getImageLocation()));
                playerView[i].setImage(playerImage);
                dealerView[i].setImage(dealerImage);
            } catch (Exception e) {
                System.out.println(String.format(format, playerCards.get(i).getImageLocation()));
                System.out.println(String.format(format, dealerCard.get(i).getImageLocation()));
                e.printStackTrace();
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
