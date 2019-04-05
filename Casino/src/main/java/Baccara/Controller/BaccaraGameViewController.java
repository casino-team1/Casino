/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Controller;

import Baccara.Entity.BaccaraCard;
import Baccara.Model.BaccaraGameModel;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCardBacks();
    }
    private BaccaraGameModel gameModel;

    public void setBaccaraGameModel(BaccaraGameModel gameModel) {
        this.gameModel = gameModel;
        bind();

    }

    private void setCardBacks() {
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void bind() {

    }

    @FXML
    private void startBaccara(MouseEvent event) {
        /*
        if (this.gameModel.betsAreSet() == false) {
            JDialog dialog = new JDialog();
            dialog.setTitle("No bets placed");
            dialog.add(new JLabel("Please place a bet, we can't start without you"));
            dialog.setLocationRelativeTo(null);
            dialog.setSize(new Dimension(300, 300));
            dialog.setVisible(true);
        }
         */
        this.gameModel.generateCards();
        String format = "/images/GameCards/%s";
        ImageView[] playerView = {this.firstLeftCard, this.secondLeftCard};
        ImageView[] dealerView = {this.firstRightCard, this.secondRightCard};
        ArrayList<BaccaraCard> playerCards = this.gameModel.getPlayerCards();
        ArrayList<BaccaraCard> dealerCard = this.gameModel.getDealerCards();
        for (int i = 0; i < 2; i++) {
            try {
                playerView[i].setImage(new Image(String.format(format, playerCards.get(i).getImageLocation())));
                dealerView[i].setImage(new Image(String.format(format, dealerCard.get(i).getImageLocation())));
            } catch (Exception e) {
                System.out.println(playerCards.get(i).getImageLocation());
                System.out.println(dealerCard.get(i).getImageLocation());
            }
        }
    }
}
