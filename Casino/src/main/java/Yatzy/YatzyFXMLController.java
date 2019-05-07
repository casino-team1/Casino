/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.PlayerCentral;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Erik Hess
 */
public class YatzyFXMLController implements Initializable {

    private MainApp mainApplication;
    @FXML
    private Label lblwin;
    @FXML
    private Label sclblenemy;
    @FXML
    private Button btnbet;
    @FXML
    private Label lbltotalnum;
    @FXML
    private Rectangle geback;
    @FXML
    private Label lblbetnum;
    @FXML
    private Label lblwinnum;
    @FXML
    private TextField bettxt;
    @FXML
    private Label lblerror;
    @FXML
    private Label lblup7;
    @FXML
    private Label lblup8;
    @FXML
    private Label lbllo8;
    @FXML
    private Label lbllo9;
    @FXML
    private Label lblenemy;
    @FXML
    private ImageView imgwurf3;
    @FXML
    private ImageView imgwurf2;
    @FXML
    private ImageView imgwurf1;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button btnback;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    /**
     * Initializes the controller class.
     */
    private boolean firstthrow = true;
    private boolean secondthrow = false;
    private boolean lockdices = false;
    private boolean locklabels = true;
    private boolean finish = false;
    private boolean newgame = false;
    private int balance;
    private int enemyscore = 100;
    private int betnum = 0;
    private ArrayList<Dice> keep = new ArrayList<>();
    private ArrayList<Dice> gearray = new ArrayList<>();
    private ArrayList<Dice> bearray = new ArrayList<>();
    private ArrayList<Dice> finalarray = new ArrayList<>();

    Cup cup = new Cup();
    Images img = new Images();
    Rules rules = new Rules();

    private PlayYatzy yatzy;
    @FXML
    private Button btnthrowdices;
    @FXML
    private ImageView ge1;
    @FXML
    private ImageView ge2;
    @FXML
    private ImageView ge3;
    @FXML
    private ImageView ge4;
    @FXML
    private ImageView ge5;
    @FXML
    private ImageView be1;
    @FXML
    private ImageView be2;
    @FXML
    private ImageView be3;
    @FXML
    private ImageView be4;
    @FXML
    private ImageView be5;
    @FXML
    private Button btnhelp;
    @FXML
    private Label sclblup1;
    @FXML
    private Label sclblup2;
    @FXML
    private Label sclblup3;
    @FXML
    private Label sclblup4;
    @FXML
    private Label sclblup5;
    @FXML
    private Label sclblup6;
    @FXML
    private Label sclblup7;
    @FXML
    private Label sclblup8;
    @FXML
    private Label lblup1;
    @FXML
    private Label lblup2;
    @FXML
    private Label lblup3;
    @FXML
    private Label lblup4;
    @FXML
    private Label lblup5;
    @FXML
    private Label lblup6;
    @FXML
    private Label sclbllo1;
    @FXML
    private Label sclbllo2;
    @FXML
    private Label sclbllo3;
    @FXML
    private Label sclbllo4;
    @FXML
    private Label sclbllo5;
    @FXML
    private Label sclbllo6;
    @FXML
    private Label sclbllo7;
    @FXML
    private Label sclbllo8;
    @FXML
    private Label sclbllo9;
    @FXML
    private Label lbllo1;
    @FXML
    private Label lbllo2;
    @FXML
    private Label lbllo3;
    @FXML
    private Label lbllo4;
    @FXML
    private Label lbllo5;
    @FXML
    private Label lbllo6;
    @FXML
    private Label lbllo7;

    private ArrayList<ImageView> imgarray = new ArrayList<>();
    private ArrayList<Label> scuplabels = new ArrayList<>();
    private ArrayList<Label> sclolabels = new ArrayList<>();

    public void setYatzy(PlayYatzy yatzy) {
        this.yatzy = yatzy;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        balance = (int) PlayerCentral.getInstance().getUser().getCurrentChipBalance();
        balanceLabel.setText("Konto: " + balance);

        btnthrowdices.setDisable(true);
        lbltotalnum.setText(Integer.toString(balance));
        sclblenemy.setText(Integer.toString(enemyscore));
        bettxt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                    bettxt.setText(oldValue);
                }
            }
        });
        ImageView[] views = {ge1, ge2, ge3, ge4, ge5, be1, be2, be3, be4, be5};
        for (ImageView view : views) {
            imgarray.add(view);
        }
    }

    @FXML
    private void pressthrowdices(ActionEvent event) {

        //the Buttons Font gets updated, so that it stays the same
        updateButton(btnthrowdices);

        //if a new game was startet, after the previous one has been finished
        if (newgame == true) {

            //makes the next throw the first throw
            newgame = false;
            btnthrowdices.setText("Würfel werfen");

            //makes the dices unusable
            lockdices = true;

            //makes the figure lables unusable
            locklabels = true;

            //the total balance gets updated
            lbltotalnum.setText(Integer.toString(balance));

            //the Throw Button gets shrinked back and disabled
            btnthrowdices.setDisable(true);
            btnthrowdices.setLayoutX(166);
            btnthrowdices.setPrefWidth(258);

            //the Textbox and Bet Button get enabled
            btnbet.setDisable(false);
            bettxt.setDisable(false);

            //reseting everything for a new game
            keep.clear();
            gearray.clear();
            gearray.clear();
            finalarray.clear();
            firstthrow = true;
            secondthrow = false;
            finish = false;
            betnum = 0;
            btnbet.setVisible(true);
            bettxt.setVisible(true);
            rules.setLowerbool(false);
            rules.setUpperbool(false);
            rules.setLowercounter(0);
            rules.setUppercounter(0);
            rules.setLowertotal(0);
            rules.setUppertotal(0);

            for (Label lbl : scuplabels) {
                lbl.setText("");
            }

            for (Label lbl : sclolabels) {
                lbl.setText("");
            }

            lblbetnum.setText("");
            lblwinnum.setText("");
            lblwin.setText("");

            //the displayed images get updated
            assignImages();
        } //the first dicethrow
        else if (firstthrow == true) {

            //makes the next throw the second throw
            firstthrow = false;
            secondthrow = true;
            imgwurf1.setVisible(false);

            //makes the dices usable
            lockdices = false;

            //generates an array of five dices
            cup.throwDices();

            //attribute for the toprow gets the generated dices
            gearray.addAll(cup.getDicearray());

            //the displayed images get updated (this function appears multiple times in the code and always does the same
            assignImages();
        } //the second dicethrow (skip the third throw if all dices are kept)
        else if (secondthrow == true && bearray.size() != 5) {

            //makes the next throw the third throw
            secondthrow = false;
            imgwurf2.setVisible(false);

            keep.clear();
            keep.addAll(bearray);
            gearray.clear();
            cup.setKeep(bearray);
            cup.throwDices();
            gearray.addAll(cup.getDicearray());
            assignImages();
        } //the third dicethrow
        else {
            //turns the second throw picture invisible in case the third throw is skiped 
            imgwurf2.setVisible(false);
            imgwurf3.setVisible(false);
            btnthrowdices.setText("Figur Wählen");
            btnthrowdices.setDisable(true);
            //makes the dices unusable
            lockdices = true;
            //makes the figure lables usable
            locklabels = false;

            keep.clear();
            keep.addAll(bearray);
            gearray.clear();
            cup.setKeep(bearray);
            cup.throwDices();
            gearray.addAll(cup.getDicearray());
            gearray.addAll(bearray);
            bearray.clear();
            finalarray.clear();
            finalarray.addAll(gearray);

            //the final dices get sorted from lowest to highest
            Collections.sort(gearray, new Comparator<Dice>() {
                @Override
                public int compare(Dice dice2, Dice dice1) {
                    return dice2.getValue() - dice1.getValue();
                }
            });
            geback.setStyle("-fx-fill: #EDEDED;");
            assignImages();
        }
    }

    @FXML
    private void pressbtnbet(ActionEvent event) throws IOException {
        updateButton(btnbet);
        try {
            betnum = Integer.parseInt(bettxt.getText());
            if (betnum % 10 == 0 && balance - betnum >= 0) {
                btnthrowdices.setLayoutX(17);
                btnthrowdices.setPrefWidth(407);
                updateButton(btnthrowdices);
                betnum = Integer.parseInt(bettxt.getText());
                balance -= betnum;
                PlayerCentral.getInstance().getUser().setNewChipBalance(balance);
                lbltotalnum.setText(Integer.toString(balance));
                balanceLabel.setText("Konto: " + balance);
                lblbetnum.setText(Integer.toString(betnum));
                lblerror.setText("");
                bettxt.setText("");
                btnthrowdices.setDisable(false);
                btnbet.setDisable(true);
                bettxt.setDisable(true);
                btnbet.setVisible(false);
                bettxt.setVisible(false);
                imgwurf1.setVisible(true);
                imgwurf2.setVisible(true);
                imgwurf3.setVisible(true);

            } else if (betnum % 10 != 0) {
                lblerror.setText("Der Betrag muss ein Vielfaches von Zehn sein");
            } else {
                lblerror.setText("Sie haben nicht genügend Jetons für diesen Betrag");
            }

        } catch (NumberFormatException e) {
            lblerror.setText("Der Betrag muss eine Zahl sein");
        }
    }

    @FXML
    private void presshelp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/YatzyhelpFXML.fxml"));
        Stage stageHelp = new Stage();
        stageHelp.setTitle("Hilfe");
        stageHelp.setScene(new Scene(root, 640, 448));
        stageHelp.setResizable(false);
        stageHelp.centerOnScreen();
        stageHelp.show();
    }

    @FXML
    private void pressbtnback(ActionEvent event) {
        this.mainApplication.displayMainMenu();
    }

    @FXML
    private void pressGe1(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(0));
            gearray.remove(0);
            assignImages();
        }
    }

    @FXML
    private void pressGe2(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(1));
            gearray.remove(1);
            assignImages();
        }
    }

    @FXML
    private void pressGe3(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(2));
            gearray.remove(2);
            assignImages();
        }
    }

    @FXML
    private void pressGe4(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(3));
            gearray.remove(3);
            assignImages();
        }
    }

    @FXML
    private void pressGe5(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(4));
            gearray.remove(4);
            assignImages();
        }
    }

    @FXML
    private void pressBe1(MouseEvent event) {
        gearray.add(bearray.get(0));
        bearray.remove(0);
        assignImages();
    }

    @FXML
    private void pressBe2(MouseEvent event) {
        gearray.add(bearray.get(1));
        bearray.remove(1);
        assignImages();
    }

    @FXML
    private void pressBe3(MouseEvent event) {
        gearray.add(bearray.get(2));
        bearray.remove(2);
        assignImages();
    }

    @FXML
    private void pressBe4(MouseEvent event) {
        gearray.add(bearray.get(3));
        bearray.remove(3);
        assignImages();
    }

    @FXML
    private void pressBe5(MouseEvent event) {
        gearray.add(bearray.get(4));
        bearray.remove(4);
        assignImages();
    }

    @FXML
    private void presslblup1(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclblup1.getText().equals("") || sclblup1.getText() == null) {
            sclblup1.setText(Integer.toString(rules.createSubresult("EINER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup1);
        }
    }

    @FXML
    private void presslblup2(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclblup2.getText().equals("") || sclblup2.getText() == null) {
            sclblup2.setText(Integer.toString(rules.createSubresult("ZWEIER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup2);
        }
    }

    @FXML
    private void presslblup3(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclblup3.getText().equals("") || sclblup3.getText() == null) {
            sclblup3.setText(Integer.toString(rules.createSubresult("DREIER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup3);
        }
    }

    @FXML
    private void presslblup4(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclblup4.getText().equals("") || sclblup4.getText() == null) {
            sclblup4.setText(Integer.toString(rules.createSubresult("VIERER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup4);
        }
    }

    @FXML
    private void presslblup5(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclblup5.getText().equals("") || sclblup5.getText() == null) {
            sclblup5.setText(Integer.toString(rules.createSubresult("FÜNFER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup5);
        }
    }

    @FXML
    private void presslblup6(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclblup6.getText().equals("") || sclblup6.getText() == null) {
            sclblup6.setText(Integer.toString(rules.createSubresult("SECHSER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup6);
        }
    }

    @FXML
    private void presslbllo1(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo1.getText().equals("") || sclbllo1.getText() == null) {
            sclbllo1.setText(Integer.toString(rules.createSubresult("DREI GLEICHE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo1);
        }
    }

    @FXML
    private void presslbllo2(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo2.getText().equals("") || sclbllo2.getText() == null) {
            sclbllo2.setText(Integer.toString(rules.createSubresult("VIER GLEICHE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo2);
        }
    }

    @FXML
    private void presslbllo3(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo3.getText().equals("") || sclbllo3.getText() == null) {
            sclbllo3.setText(Integer.toString(rules.createSubresult("FULL HOUSE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo3);
        }
    }

    @FXML
    private void presslbllo4(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo4.getText().equals("") || sclbllo4.getText() == null) {
            sclbllo4.setText(Integer.toString(rules.createSubresult("KLEINE STRASSE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo4);
        }
    }

    @FXML
    private void presslbllo5(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo5.getText().equals("") || sclbllo5.getText() == null) {
            sclbllo5.setText(Integer.toString(rules.createSubresult("GROSSE STRASSE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo5);
        }
    }

    @FXML
    private void presslbllo6(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo6.getText().equals("") || sclbllo6.getText() == null) {
            sclbllo6.setText(Integer.toString(rules.createSubresult("YATZY", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo6);
        }
    }

    @FXML
    private void presslbllo7(MouseEvent event) {
        if (locklabels == true) {
        } else if (sclbllo7.getText().equals("") || sclbllo7.getText() == null) {
            sclbllo7.setText(Integer.toString(rules.createSubresult("CHANCE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo7);
        }
    }

    @FXML
    private void exitlblup1(MouseEvent event) {
        exithover(lblup1);
    }

    @FXML
    private void enterlblup1(MouseEvent event) {
        enterhover(lblup1);
    }

    @FXML
    private void exitlblup2(MouseEvent event) {
        exithover(lblup2);
    }

    @FXML
    private void enterlblup2(MouseEvent event) {
        enterhover(lblup2);
    }

    @FXML
    private void exitlblup3(MouseEvent event) {
        exithover(lblup3);
    }

    @FXML
    private void enterlblup3(MouseEvent event) {
        enterhover(lblup3);
    }

    @FXML
    private void exitlblup4(MouseEvent event) {
        exithover(lblup4);
    }

    @FXML
    private void enterlblup4(MouseEvent event) {
        enterhover(lblup4);
    }

    @FXML
    private void exitlblup5(MouseEvent event) {
        exithover(lblup5);
    }

    @FXML
    private void enterlblup5(MouseEvent event) {
        enterhover(lblup5);
    }

    @FXML
    private void exitlblup6(MouseEvent event) {
        exithover(lblup6);
    }

    @FXML
    private void enterlblup6(MouseEvent event) {
        enterhover(lblup6);
    }

    @FXML
    private void exitlbllo1(MouseEvent event) {
        exithover(lbllo1);
    }

    @FXML
    private void enterlbllo1(MouseEvent event) {
        enterhover(lbllo1);
    }

    @FXML
    private void exitlbllo2(MouseEvent event) {
        exithover(lbllo2);
    }

    @FXML
    private void enterlbllo2(MouseEvent event) {
        enterhover(lbllo2);
    }

    @FXML
    private void exitlbllo3(MouseEvent event) {
        exithover(lbllo3);
    }

    @FXML
    private void enterlbllo3(MouseEvent event) {
        enterhover(lbllo3);
    }

    @FXML
    private void exitlbllo4(MouseEvent event) {
        exithover(lbllo4);
    }

    @FXML
    private void enterlbllo4(MouseEvent event) {
        enterhover(lbllo4);
    }

    @FXML
    private void exitlbllo5(MouseEvent event) {
        exithover(lbllo5);
    }

    @FXML
    private void enterlbllo5(MouseEvent event) {
        enterhover(lbllo5);
    }

    @FXML
    private void exitlbllo6(MouseEvent event) {
        exithover(lbllo6);
    }

    @FXML
    private void enterlbllo6(MouseEvent event) {
        enterhover(lbllo6);
    }

    @FXML
    private void exitlbllo7(MouseEvent event) {
        exithover(lbllo7);
    }

    @FXML
    private void enterlbllo7(MouseEvent event) {
        enterhover(lbllo7);
    }

    @FXML
    private void enterbtnbet(MouseEvent event) {
        updateButton(btnbet);
    }

    @FXML
    private void enterbtnthrowdices(MouseEvent event) {
        updateButton(btnthrowdices);
    }

    public void enterhover(Label lbl) {
        lbl.setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-color: white;");
    }

    public void exithover(Label lbl) {
        lbl.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
    }

    public void notPressable(Label lbl) {
        lbl.setStyle("-fx-border-color: black;-fx-border-width: 3; -fx-background-color: red;");
    }

    public void assignImages() {
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (gearray.size() < j + 1) {
                    imgarray.get(j).setVisible(false);
                } else {
                    imgarray.get(j).setImage(new Image(img.getImage(gearray.get(j).getValue())));
                    imgarray.get(j).setVisible(true);
                }
            }

            for (int j = 0; j < 5; j++) {
                if (bearray.size() < j + 1) {
                    imgarray.get(j + 5).setVisible(false);
                } else {
                    imgarray.get(j + 5).setImage(new Image(img.getImage(bearray.get(j).getValue())));
                    imgarray.get(j + 5).setVisible(true);
                }
            }
        }
    }

    public void newTurn() {
        rules.totalChecker();
        if (rules.isUpperbool() == true) {
            sclblup7.setText(Integer.toString(rules.getUppertotal()));
            sclblup8.setText(Integer.toString(rules.getBonusUppertotal()));
        }
        if (rules.isLowerbool() == true) {
            sclbllo8.setText(Integer.toString(rules.getLowertotal()));
        }
        if (rules.isUpperbool() == true && rules.isLowerbool() == true) {
            sclbllo9.setText(Integer.toString(rules.getTotal()));
            finish = true;
        }
        if (finish == false) {
            btnthrowdices.setDisable(false);
            btnthrowdices.setText("Würfel werfen");
            locklabels = true;
            imgwurf1.setVisible(true);
            imgwurf2.setVisible(true);
            imgwurf3.setVisible(true);
            keep.clear();
            gearray.clear();
            bearray.clear();
            firstthrow = true;
            secondthrow = true;

            //the background of the top dicerow gets set back to white
            geback.setStyle("-fx-fill: white;");

            assignImages();
        } else {
            btnthrowdices.setDisable(false);
            newgame = true;

            rules.setBetnum(betnum);
            rules.setBalance(balance);

            //checks if player won
            rules.checkwin(Integer.parseInt(sclblenemy.getText()));

            //depending on the result the numbers will get displayed
            switch (rules.getWin()) {
                case 1:
                    lblwin.setText("Gewonnen!");
                    lblwinnum.setText(Integer.toString(rules.getWinAmount()));
                    balance = rules.getNewAmount();
                    PlayerCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(balance, "Yatzy", betnum, "WON", (PlayerCentral.getInstance().getUser().getCurrentChipBalance()) - balance);
                    break;
                case 2:
                    lblwin.setText("Verloren!");
                    lblwinnum.setText(Integer.toString(rules.getWinAmount()));
                    balance = rules.getNewAmount();
                    PlayerCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(PlayerCentral.getInstance().getUser().getCurrentChipBalance(), "Yatzy", betnum, "LOST", 0);
                    break;
                case 3:
                    lblwin.setText("Unentschieden!");
                    lblwinnum.setText(Integer.toString(rules.getWinAmount()));
                    balance = rules.getNewAmount();
                    PlayerCentral.getInstance().getUser().setCurrentBalanceAndAddStatistic(balance, "Yatzy", betnum, "Tie", betnum);
                    break;
            }

            //the total balance gets updated
            lbltotalnum.setText(Integer.toString(balance));
            balanceLabel.setText("Konto: " + balance);

            btnthrowdices.setText("Neues Spiel");
        }
    }

    public void updateButton(Button btn) {
        switch (btn.getId()) {
            case "btnthrowdices":
                btnthrowdices.setStyle("-fx-font-size: 30; -fx-font-weight: BOLD");
                break;
            case "btnbet":
                btnbet.setStyle("-fx-font-size: 18; -fx-font-weight: BOLD");
                break;
        }
    }

    @FXML
    private void exitbtnback(MouseEvent event) {
        btnback.setStyle("-fx-background-color: rgba(1, 1, 1, 0); -fx-border-color: black; -fx-border-width: 3;");
    }

    @FXML
    private void enterbtnback(MouseEvent event) {
        btnback.setStyle("-fx-background-color: rgba(1, 1, 1, .1); -fx-border-color: black; -fx-border-width: 3;");
    }
}
