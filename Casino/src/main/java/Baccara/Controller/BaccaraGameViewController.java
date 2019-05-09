/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Controller;

import Baccara.BaccaraHandler;
import Baccara.Entity.BaccaraCard;
import Baccara.Model.BaccaraGameModel;
import com.sun.javafx.PlatformUtil;
import com.team1.casino.User.Util.PlayerCentral;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
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
    private Text userBalance;
    @FXML
    private Text totalBet;
    @FXML
    private Button menuButton;
    @FXML
    private ImageView tieBetCoin;
    @FXML
    private ImageView playerBetCoin;
    @FXML
    private ImageView hundretBetCoin;
    @FXML
    private ImageView fiveBetCoin;
    @FXML
    private ImageView tenBetCoin;
    @FXML
    private ImageView twentyfiveBetCoin;

    private int coinSelected;
    @FXML
    private Button helpButton;

    private JFXCustomCursor customCursor;

    /**
     *
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCardBacks();
        ImageView[] coins = {hundretBetCoin, fiveBetCoin, twentyfiveBetCoin, tenBetCoin};
        for (ImageView view : coins) {
            view.setOnMouseClicked(event -> {
                if (this.gameRunning == false) {
                    if (view.equals(hundretBetCoin)) {
                        coinSelected = 100;
                    } else if (view.equals(fiveBetCoin)) {
                        coinSelected = 5;
                    } else if (view.equals(twentyfiveBetCoin)) {
                        coinSelected = 25;
                    } else {
                        coinSelected = 10;
                    }
                    this.draggable = new ImageView();
                    this.draggable.setImage(view.getImage());
                    Image cursorImage = this.draggable.getImage();
                    int imageSize = 100;
                    if (PlatformUtil.isWindows()) {
                        BorderPane borderPane = new BorderPane();
                        borderPane.getChildren().add(new ImageView(view.getImage()));
                        customCursor = new JFXCustomCursor(this.gameModel.setCursor(), this.anchro, borderPane, 0, 0);
                    } else {
                        ImageCursor cursor;
                        cursor = new ImageCursor(cursorImage);
                        this.gameModel.setCursor().setCursor(cursor);
                    }
                }
            });
        }
        ImageView[] betAreas = {bankerBet, playerBet, tieBet, playerBetCoin, tieBetCoin, bankerBetCoin};
        for (ImageView area : betAreas) {
            area.setOnMouseClicked(event -> {
                if (this.draggable != null) {
                    area.setVisible(true);
                    int betValue = getBetToSet();
                    if (betValue > PlayerCentral.getInstance().getUser().getCurrentChipBalance()) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Zu wenig Chips in ihrem Konto");
                            alert.setHeaderText(null);
                            String userMessage = String.format("Die Wette überschreitet ihren momentanen Chipbestand. Sie können Chips an der Kasse umtauschen");
                            alert.setContentText(userMessage);
                            alert.showAndWait();
                            resetImageViews();
                            this.setCardBacks();
                            resetCardCount();
                        });
                    } else {
                        if (betValue > 0 == true) {
                            if (area.equals(bankerBet) || area.equals(bankerBetCoin)) {
                                bankerBetCoin.setImage(this.draggable.getImage());
                                bankerBetCoin.toFront();
                                this.gameModel.setDealerBet(betValue);
                            } else if (area.equals(playerBet) || area.equals(playerBetCoin)) {
                                this.gameModel.setPlayerBet(betValue);
                                playerBetCoin.setImage(this.draggable.getImage());
                                playerBetCoin.toFront();
                            } else {
                                tieBetCoin.setImage(this.draggable.getImage());
                                tieBetCoin.toFront();
                                this.gameModel.setTieBet(betValue);
                            }
                            PlayerCentral.getInstance().getUser().setNewChipBalance(PlayerCentral.getInstance().getUser().getCurrentChipBalance() - betValue);
                            updateBalanceAndBet();
                        }
                    }
                    if (PlatformUtil.isWin7OrLater()) {
                        customCursor.unRegister();
                    }
                    coinSelected = 0;
                    this.draggable = null;
                    this.gameModel.setCursor().setCursor(Cursor.DEFAULT);
                }
            });
        }

        /*
            Code below checks for drag and drop and sets image at new position.
         */
    }
    private BaccaraGameModel gameModel;

    public void setBaccaraGameModel(BaccaraGameModel gameModel) {
        this.gameModel = gameModel;
        this.gameModel.addObserver(this);
        updateBalanceAndBet();
    }

    private void setCardBacks() {
        ImageView[] imageViews = {this.firstLeftCard, this.secondLeftCard, this.firstRightCard, this.secondRightCard};
        for (ImageView imageView : imageViews) {
            imageView.setImage(new Image("/images/GameCards/cardBack.png"));
        }
    }

    private BaccaraHandler handler;

    public void setBaccaraHandler(BaccaraHandler handler) {
        this.handler = handler;
    }

    private void resetCardCount() {
        this.playerCardCountLabel.setText("Spieler Kartenwert: ");
        this.dealerCardCountLabel.setText("Banker Kartenwert: ");
    }

    @Override
    public void update(Observable o, Object arg) {
        setCardCount();
        updateBalanceAndBet();
        final int totaleWette = (int) this.gameModel.getTotalBet();
        final boolean isWon = this.gameModel.isWon();
        final String result = this.gameModel.getResultMessage();
        final int totalerGewinn = (int) this.gameModel.getAccountChange();
        Platform.runLater(() -> {
            String userMessage = "";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (isWon) {
                alert.setTitle("Gewonnen");
            } else {
                alert.setTitle("Verloren");
            }
            userMessage = String.format("%s", result);
            alert.setHeaderText(null);
            alert.setContentText(userMessage);
            alert.showAndWait();
            resetImageViews();
            this.setCardBacks();
            resetCardCount();
        });
        this.gameModel.resetGame();
    }

    public void bind() {
    }

    private void updateBalanceAndBet() {
        if (PlayerCentral.getInstance().getUser() != null) {
            this.userBalance.setText("Kontostand: " + PlayerCentral.getInstance().getUser().getCurrentChipBalance());
            this.totalBet.setText("Einsatz: " + this.gameModel.getTotalBet());
        }
    }

    @FXML
    private void startBaccara(MouseEvent event) throws InterruptedException {
        if (this.gameModel.getTotalBet() == 0) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bitte setzen sie eine Wette");
                alert.setHeaderText(null);
                String userMessage = "Sie müssen zuerst eine Betrag setzten";
                alert.setContentText(userMessage);
                alert.showAndWait();
            });
        }
        this.bankerBetCoin.setImage(null);
        this.tieBetCoin.setImage(null);
        this.playerBetCoin.setImage(null);
        this.gameModel.generateCards();
        this.gameRunning = true;
        String format = "/images/GameCards/%s";
        ImageView[] playerView = {this.firstLeftCard, this.secondLeftCard};
        ImageView[] dealerView = {this.firstRightCard, this.secondRightCard};
        setCardCount();
        rotateCards(playerView, dealerView, format);
        this.gameRunning = false;
    }

    private void checkForNewCards(String linkFormat) {
        this.gameModel.checkForCardDraw();
        ArrayList<BaccaraCard> playerCards = this.gameModel.getPlayerCards();
        ArrayList<BaccaraCard> dealerCards = this.gameModel.getDealerCards();
        /*
            Check if a third card has been drawn by the algorithm following the rules of Baccara.
         */
        try {
            if (dealerCards.size() == 2 && playerCards.size() == 2) {
                Thread.sleep(1000);
                this.gameModel.endGame();
                updateBalanceAndBet();
            }
            if (playerCards.size() == 3 && dealerCards.size() != 3) {
                thirdLeftCard.setImage(new Image(String.format(linkFormat, "cardBack.png")));
                this.thirdLeftCard.setVisible(true);
                TranslateTransition translateTransition = createTransitionForCad(this.thirdRightImage);
                translateTransition.play();
                translateTransition.setOnFinished(hanlder -> {
                    RotateTransition trans = createRotator(this.thirdLeftCard, 0, -90, 750, 1);
                    trans.play();
                    trans.setOnFinished(imageSetter -> {
                        this.thirdLeftCard.setImage(new Image(String.format(linkFormat, playerCards.get(2).getImageLocation())));
                        RotateTransition rotateBack = createRotator(this.thirdLeftCard, -85, 0, 750, 1);
                        rotateBack.play();
                        rotateBack.setOnFinished(imageBack -> {
                            try {
                                Thread.sleep(2000);
                                this.gameModel.endGame();
                                updateBalanceAndBet();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        });
                    });
                });
            }
            if (dealerCards.size() == 3 && playerCards.size() != 3) {
                this.thirdRightImage.setImage(new Image(String.format(linkFormat, "cardBack.png")));
                this.thirdRightImage.setVisible(true);
                TranslateTransition translateTransition = createTransitionForCad(this.thirdRightImage);
                translateTransition.play();
                translateTransition.setOnFinished(hanlder -> {
                    RotateTransition trans = createRotator(this.thirdRightImage, 0, -90, 750, 1);
                    trans.play();
                    trans.setOnFinished(imageSetter -> {
                        this.thirdRightImage.setImage(new Image(String.format(linkFormat, dealerCards.get(2).getImageLocation())));
                        RotateTransition rotate = createRotator(this.thirdRightImage, -85, 0, 750, 1);
                        rotate.play();
                        rotate.setOnFinished(cardBack -> {
                            try {
                                Thread.sleep(2000);
                                this.gameModel.endGame();
                                updateBalanceAndBet();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        });
                    });
                });
            }
            if (dealerCards.size() == 3 && playerCards.size() == 3) {
                ImageView[] imageViews = {this.thirdLeftCard, this.thirdRightImage};
                for (ImageView view : imageViews) {
                    try {
                        view.setImage(new Image(String.format(linkFormat, "cardBack.png")));
                        view.setVisible(true);
                    } catch (Exception e) {
                    }
                }
                TranslateTransition left = createTransitionForCad(this.thirdLeftCard);
                TranslateTransition right = createTransitionForCad(this.thirdRightImage);
                ParallelTransition parTrans = new ParallelTransition(left, right);
                parTrans.play();
                parTrans.setOnFinished(parFinished -> {
                    ParallelTransition parRotate = new ParallelTransition(createRotator(this.thirdLeftCard, 0, -89, 750, 1), createRotator(this.thirdRightImage, 0, -89, 750, 1));
                    parRotate.play();
                    parRotate.setOnFinished(rotationFinished -> {
                        this.thirdRightImage.setImage(new Image(String.format(linkFormat, dealerCards.get(2).getImageLocation())));
                        this.thirdLeftCard.setImage(new Image(String.format(linkFormat, playerCards.get(2).getImageLocation())));
                        ParallelTransition returnBack = new ParallelTransition(createRotator(this.thirdLeftCard, -85, 0, 750, 1), createRotator(this.thirdRightImage, -85, 0, 750, 1));
                        returnBack.play();
                        returnBack.setOnFinished(backRotationFiniseh -> {
                            try {
                                Thread.sleep(2000);
                                this.gameModel.endGame();
                                updateBalanceAndBet();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        });
                    });
                });
            }
        } catch (InterruptedException e) {
        }

    }

    private TranslateTransition createTransitionForCad(ImageView view) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1500));
        translateTransition.setNode(view);
        translateTransition.fromYProperty().set(view.getY() + ((241 - (241 * 2)) * 2));
        translateTransition.toYProperty().setValue(view.getY());
        return translateTransition;
    }

    /**
     * Rotate inner and outer cards in paralell and check if a third card is
     * needed for player and/or dealer.
     *
     *
     *
     * @param playerView
     * @param dealerView
     * @param format
     */
    private void rotateCards(ImageView[] playerView, ImageView[] dealerView, String format) {
        ImageView[][] views = {playerView, dealerView};
        RotateTransition[] rotators = new RotateTransition[2];
        int index = 0;
        rotators[0] = createRotator(playerView[0], 0, -90, 500, 1);
        rotators[1] = createRotator(dealerView[0], 0, -90, 500, 1);
        ParallelTransition rotateOuterFirst = new ParallelTransition(rotators);
        rotateOuterFirst.play();
        rotateOuterFirst.setOnFinished((ActionEvent event) -> {
            flipCardsArround(format, playerView[0], dealerView[0], 0);
            ParallelTransition rotateCards = new ParallelTransition(createRotator(playerView[0], -85, 0, 500, 1), createRotator(dealerView[0], -85, 0, 500, 1));
            rotateCards.play();
            rotateCards.setOnFinished(rotationFinished -> {
                ParallelTransition rotateOuterCards = new ParallelTransition(createRotator(playerView[1], 0, -90, 500, 1), createRotator(dealerView[1], 0, -90, 500, 1));
                rotateOuterCards.play();
                rotateOuterCards.setOnFinished(outerCardsRotaded -> {
                    flipCardsArround(format, playerView[1], dealerView[1], 1);
                    ParallelTransition rotateOuterSecond = new ParallelTransition(createRotator(playerView[1], -85, 0, 500, 1), createRotator(dealerView[1], -85, 0, 500, 1));
                    rotateOuterSecond.play();
                    rotateOuterSecond.setOnFinished(secondRotationEnd -> {
                        checkForNewCards(format);
                    });
                });
            });
        });
    }

    /**
     * Code copied and modified from:
     * https://stackoverflow.com/questions/19896562/flip-a-card-animation
     *
     * @param card
     * @param startAngle
     * @param endAngle
     * @param duration
     * @param cicle
     * @return
     */
    private RotateTransition createRotator(Node card, int startAngle, int endAngle, int duration, int cicle) {
        RotateTransition rotator = new RotateTransition(Duration.millis(duration), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(startAngle);
        rotator.setToAngle(endAngle);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        return rotator;
    }

    private void flipCardsArround(String format, ImageView playerView, ImageView dealerView, int pos) {
        //set cards
        ArrayList<BaccaraCard> playerCards = this.gameModel.getPlayerCards();
        ArrayList<BaccaraCard> dealerCard = this.gameModel.getDealerCards();
        try {
            Image playerImage = new Image(String.format(format, playerCards.get(pos).getImageLocation()));
            Image dealerImage = new Image(String.format(format, dealerCard.get(pos).getImageLocation()));
            playerView.setImage(playerImage);
            dealerView.setImage(dealerImage);
        } catch (Exception e) {
            System.out.println(String.format(format, playerCards.get(pos).getImageLocation()));
            System.out.println(String.format(format, dealerCard.get(pos).getImageLocation()));
        }
    }

    private void resetImageViews() {
        ImageView[] imageViews = {this.firstLeftCard, this.secondLeftCard, this.thirdLeftCard, this.firstRightCard, this.secondRightCard, this.thirdRightImage};
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView();
        }
        this.bankerBetCoin.setImage(null);
        this.gameRunning = false;
        this.thirdLeftCard.setVisible(false);
        this.thirdLeftCard.setImage(null);
        this.thirdRightImage.setVisible(false);
        this.thirdRightImage.setImage(null);
    }

    private void setCardCount() {
        int playerCardCount = this.gameModel.getPlayerCardCount();
        int dealerCardCount = this.gameModel.getDealerCardCount();
        this.playerCardCountLabel.setText("Spieler Kartenwert: " + String.valueOf(playerCardCount));
        this.dealerCardCountLabel.setText("Dealer Kartenwert: " + String.valueOf(dealerCardCount));
    }

    private boolean gameRunning = false;

    private ImageView draggable;

    private void dropCoin(MouseDragEvent event) {
        this.draggable = null;
    }

    private int getBetToSet() {
        int value = -1;
        List<Integer> choices = new ArrayList<>();
        for (int i = coinSelected; i <= 1000; i += coinSelected) {
            choices.add(i);
        }
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(coinSelected, choices);
        dialog.setTitle("Wettbetrag setzen");
        dialog.setHeaderText(null);
        dialog.setContentText("Betrag wählen");
        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            value = result.get();
        }
        return value;
    }

    @FXML
    private void backToMenu(ActionEvent event) {
        this.handler.displayMenu();
    }

    @FXML
    private void exitMenuButton(MouseEvent event) {
        menuButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void enterMenuButton(MouseEvent event) {
        menuButton.setStyle("-fx-background-color: rgba(255, 255, 255, .1); -fx-border-color: white; -fx-border-width: 3;");
    }

    @FXML
    private void helpRequested(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BaccaraRulesView.fxml"));
        Parent root;
        root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Baccara Spielanleitung");
        stage.setScene(scene);
        stage.show();
    }

}
