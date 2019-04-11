package Roulette;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lukas Gilgen
 */
public class RouletteFXMLController implements Initializable {

    private int playerBalance = 1000;

    private int betIntFromPlayer;

    private boolean isOverZero = true;
    private boolean isNumber = true;
    private boolean betIsPlaced = false;
    private int betInt;
    private int ArrayIdentify;
    private StringProperty stringBet = new SimpleStringProperty();
    RouletteWheel wheels = new RouletteWheel();
    RouletteTable tables = new RouletteTable();
    ArrayList<Integer> betArray = new ArrayList<>();

    ArrayList<Integer> redArray = new ArrayList<>();
    ArrayList<Integer> blackArray = new ArrayList<>();

    ArrayList<Integer> row1Array = new ArrayList<>();
    ArrayList<Integer> row2Array = new ArrayList<>();
    ArrayList<Integer> row3Array = new ArrayList<>();

    ArrayList<Integer> d1Array = new ArrayList<>();
    ArrayList<Integer> d2Array = new ArrayList<>();
    ArrayList<Integer> d3Array = new ArrayList<>();

    @FXML
    private TextField numberField;
    @FXML
    private Label betString;
    @FXML
    private Label randomNumberLabel;
    @FXML
    private Label gewonnenVerloren;
    @FXML
    private Label errorMessage;
    @FXML
    private Label intBetBindLabel;

    public boolean getIsNumber() {
        return isNumber;
    }

    @FXML
    private ImageView placeYourBet;
    @FXML
    private ImageView rouletteWheel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String playerBalanceString = String.valueOf(playerBalance);
        intBetBindLabel.setText(playerBalanceString);
        
        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                    numberField.setText(oldValue);
                }
            }
        });

        betString.textProperty().bind(stringBet);
        stringBet.set("0");

        //Creating an arrayList which contains all red numbers
        redArray.add(1);
        redArray.add(3);
        redArray.add(5);
        redArray.add(7);
        redArray.add(9);
        redArray.add(12);
        redArray.add(14);
        redArray.add(16);
        redArray.add(18);
        redArray.add(19);
        redArray.add(21);
        redArray.add(23);
        redArray.add(25);
        redArray.add(27);
        redArray.add(30);
        redArray.add(32);
        redArray.add(34);
        redArray.add(36);
        //creating an arraylist which contains all black numbers
        //  !!This is dependant on the red array and it being correct!!
        for (int i = 1; i < 37; i++) {
            if (redArray.contains(i)) {
            } else {
                blackArray.add(i);
            }
        }
        //Creating an arrayList that contains all Numbers in row 1
        for (int i = 1; i < 37; i++) {
            row1Array.add(i);
            i++;
            i++;
        }
        //Creating an arrayList that contains all Numbers in row 2
        for (int i = 1; i < 37; i++) {
            i++;
            row2Array.add(i);
            i++;
        }
        //Creating an arrayList that contains all Numbers in row 3
        for (int i = 1; i < 37; i++) {
            i++;
            i++;
            row3Array.add(i);
        }
    }

    public void placeBet() {
        System.out.println(betInt);
        int difference = wheels.decideResult(betInt, betIntFromPlayer);
        playerBalance = playerBalance + difference;
        boolean isWon = wheels.getResult();
        if (isWon == true) {
            gewonnenVerloren.setText("Gewonnen");
        } else {
            gewonnenVerloren.setText("Verloren");
        }
        String playerBalanceString = String.valueOf(playerBalance);
        intBetBindLabel.setText(playerBalanceString);
    }

    public void placeBetArray(int AI) {
        int difference = wheels.decideResult(betArray, AI, betIntFromPlayer);
        playerBalance = playerBalance + difference;
        boolean isWon = wheels.getResult();
        if (isWon == true) {
            gewonnenVerloren.setText("Gewonnen");
        } else {
            gewonnenVerloren.setText("Verloren");
        }
        String playerBalanceString = String.valueOf(playerBalance);
        intBetBindLabel.setText(playerBalanceString);
    }

    public void gameEnd() {

    }

    public void gameStart() {

    }

    //Methoden für jedes Feld auf dem Roulettetisch
    //-------------------------------------------------------------------
    @FXML
    private void clickPane36(MouseEvent event) {
        betInt = 36;
        isNumber = true;
        stringBet.set("36");
    }

    @FXML
    private void clickPane21(MouseEvent event) {
        betInt = 21;
        isNumber = true;
        stringBet.set("21");
    }

    @FXML
    private void clickPane33(MouseEvent event) {
        betInt = 33;
        isNumber = true;
        stringBet.set("33");
    }

    @FXML
    private void clickPane30(MouseEvent event) {
        betInt = 30;
        isNumber = true;
        stringBet.set("30");
    }

    @FXML
    private void clickPane27(MouseEvent event) {
        betInt = 27;
        isNumber = true;
        stringBet.set("27");
    }

    @FXML
    private void clickPane24(MouseEvent event) {
        betInt = 24;
        isNumber = true;
        stringBet.set("24");
    }

    @FXML
    private void clickPane18(MouseEvent event) {
        betInt = 18;
        isNumber = true;
        stringBet.set("18");
    }

    @FXML
    private void clickPane15(MouseEvent event) {
        betInt = 15;
        isNumber = true;
        stringBet.set("15");
    }

    @FXML
    private void clickPane12(MouseEvent event) {
        betInt = 12;
        isNumber = true;
        stringBet.set("12");
    }

    @FXML
    private void clickPane9(MouseEvent event) {
        betInt = 9;
        isNumber = true;
        stringBet.set("9");
    }

    @FXML
    private void clickPane6(MouseEvent event) {
        betInt = 6;
        isNumber = true;
        stringBet.set("6");
    }

    @FXML
    private void clickPane3(MouseEvent event) {
        betInt = 3;
        isNumber = true;
        stringBet.set("3");
    }

    @FXML
    private void clickPane35(MouseEvent event) {
        betInt = 35;
        isNumber = true;
        stringBet.set("35");
    }

    @FXML
    private void clickPane32(MouseEvent event) {
        betInt = 32;
        isNumber = true;
        stringBet.set("32");
    }

    @FXML
    private void clickPane29(MouseEvent event) {
        betInt = 29;
        isNumber = true;
        stringBet.set("29");
    }

    @FXML
    private void clickPane26(MouseEvent event) {
        betInt = 26;
        isNumber = true;
        stringBet.set("26");
    }

    @FXML
    private void clickPane23(MouseEvent event) {
        betInt = 23;
        isNumber = true;
        stringBet.set("23");
    }

    @FXML
    private void clickPane20(MouseEvent event) {
        betInt = 20;
        isNumber = true;
        stringBet.set("20");
    }

    @FXML
    private void clickPane17(MouseEvent event) {
        betInt = 17;
        isNumber = true;
        stringBet.set("17");
    }

    @FXML
    private void clickPane14(MouseEvent event) {
        betInt = 14;
        isNumber = true;
        stringBet.set("14");
    }

    @FXML
    private void clickPane11(MouseEvent event) {
        betInt = 11;
        isNumber = true;
        stringBet.set("11");
    }

    @FXML
    private void clickPane8(MouseEvent event) {
        betInt = 8;
        isNumber = true;
        stringBet.set("8");
    }

    @FXML
    private void clickPane5(MouseEvent event) {
        betInt = 5;
        isNumber = true;
        stringBet.set("5");
    }

    @FXML
    private void clickPane2(MouseEvent event) {
        betInt = 2;
        isNumber = true;
        stringBet.set("2");
    }

    @FXML
    private void clickPane34(MouseEvent event) {
        betInt = 34;
        isNumber = true;
        stringBet.set("34");
    }

    @FXML
    private void clickPane31(MouseEvent event) {
        betInt = 31;
        isNumber = true;
        stringBet.set("31");
    }

    @FXML
    private void clickPane28(MouseEvent event) {
        betInt = 28;
        isNumber = true;
        stringBet.set("28");
    }

    @FXML
    private void clickPane25(MouseEvent event) {
        betInt = 25;
        isNumber = true;
        stringBet.set("25");
    }

    @FXML
    private void clickPane22(MouseEvent event) {
        betInt = 22;
        isNumber = true;
        stringBet.set("22");
    }

    @FXML
    private void clickPane19(MouseEvent event) {
        betInt = 19;
        isNumber = true;
        stringBet.set("19");
    }

    @FXML
    private void clickPane16(MouseEvent event) {
        betInt = 16;
        isNumber = true;
        stringBet.set("16");
    }

    @FXML
    private void clickPane13(MouseEvent event) {
        betInt = 13;
        isNumber = true;
        stringBet.set("13");
    }

    @FXML
    private void clickPane10(MouseEvent event) {
        betInt = 10;
        isNumber = true;
        stringBet.set("10");
    }

    @FXML
    private void clickPane7(MouseEvent event) {
        betInt = 7;
        isNumber = true;
        stringBet.set("7");
    }

    @FXML
    private void clickPane4(MouseEvent event) {
        betInt = 4;
        isNumber = true;
        stringBet.set("4");
    }

    @FXML
    private void clickPane1(MouseEvent event) {
        betInt = 1;
        isNumber = true;
        stringBet.set("1");
    }

    @FXML
    private void clickPane0(MouseEvent event) {
        betInt = 0;
        isNumber = true;
        stringBet.set("0");
    }

    @FXML
    private void clickPane00(MouseEvent event) {
        betInt = 37;
        isNumber = true;
        stringBet.set("00");
    }

    //-------------------------------------------------------------------
    //Methoden für jede Gruppe von Zahlen auf dem Tisch
    //-------------------------------------------------------------------
    @FXML
    private void clickPaneManque(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 1; i < 19; i++) {
            intArray.add(i);
        }
        ArrayIdentify = 1;
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPanePasse(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 19; i < 37; i++) {
            intArray.add(i);
        }
        ArrayIdentify = 1;
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPaneImpair(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            i++;
            intArray.add(i);
        }
        ArrayIdentify = 1;
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPanePair(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 1; i < 37; i++) {
            i++;
            intArray.add(i);
        }
        ArrayIdentify = 1;
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPaneRed(MouseEvent event) {
        ArrayIdentify = 1;
        betArray = redArray;
        isNumber = false;
    }

    @FXML
    private void clickPaneBlack(MouseEvent event) {
        ArrayIdentify = 1;
        betArray = blackArray;
        isNumber = false;
    }

    @FXML
    private void clickRow3(MouseEvent event) {
        ArrayIdentify = 2;
        betArray = row3Array;
        isNumber = false;
    }

    @FXML
    private void clickRow2(MouseEvent event) {
        ArrayIdentify = 2;
        betArray = row2Array;
        isNumber = false;
    }

    @FXML
    private void clickRow1(MouseEvent event) {
        ArrayIdentify = 2;
        isNumber = false;
        betArray = row1Array;
    }

    @FXML
    private void clickPaneD1(MouseEvent event) {
        for (int i = 1; i < 13; i++) {
            d1Array.add(i);
        }
        ArrayIdentify = 2;
        isNumber = false;
        betArray = d1Array;
    }

    @FXML
    private void clickPaneD2(MouseEvent event) {
        for (int i = 13; i < 25; i++) {
            d2Array.add(i);
        }
        ArrayIdentify = 2;
        isNumber = false;
        betArray = d2Array;
    }

    @FXML
    private void clickPaneD3(MouseEvent event) {
        for (int i = 25; i < 37; i++) {
            d3Array.add(i);
        }
        ArrayIdentify = 2;
        isNumber = false;
        betArray = d3Array;
    }

    //-------------------------------------------------------------------
    //Confirm your bet and pass off the numbers
    //-------------------------------------------------------------------
    @FXML
    private void clickPlaceBet(MouseEvent event) {
        String betIntFromPlayerString = numberField.getText();
        betIntFromPlayer = Integer.parseInt(betIntFromPlayerString);
        boolean availableMoneySuffice = tables.checkForAvailableMoney(playerBalance, betIntFromPlayer);
        if (availableMoneySuffice == false) {
            errorMessage.setText("Sie haben zu wenig geld verfügbar");
        } else {
            if (numberField.getText().isEmpty()) {
                numberField.setText("1");
            }
            if (betString.getText().trim().isEmpty()) {
                stringBet.set("0");
            }
            checkForZero(betIntFromPlayer);
            if (isOverZero == true) {
                wheels.generateRandom();
                rouletteWheel.setImage(new Image("/images/Roulette/rouletteWheelFast.gif"));
                PauseTransition transition = new PauseTransition(Duration.seconds(3));
                transition.setOnFinished(x -> rouletteWheel.setImage(new Image("/images/Roulette/rouletteWheel.png")));
                transition.play();
                int random = wheels.getRandomNumber();
                String randomString = String.valueOf(random);
                randomNumberLabel.setText(randomString);
                if (isNumber == true) {
                    placeBet();
                } else {
                    placeBetArray(ArrayIdentify);
                }
            }
        }
    }
    //-------------------------------------------------------------------
    private PlayRoulette playRoulette;

    public void setPlayRoulette(PlayRoulette playRoulette) {
        this.playRoulette = playRoulette;
    }

    @FXML
    private void rouletteVerlassen(ActionEvent event) throws IOException {
    }

    @FXML
    private void getHelp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RouletteHelpFXML.fxml"));
        Stage stageHelp = new Stage();
        stageHelp.setTitle("Help");
        stageHelp.setScene(new Scene(root, 1433, 665));
        stageHelp.setResizable(false);
        stageHelp.show();
    }

    public StringProperty stringBetProperty() {
        return stringBet;
    }
    private void checkForZero(int betIntFromPlayer) {
        if (betIntFromPlayer < 1) {
            isOverZero = false;
        } else {
            isOverZero = true;
        }
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    @FXML
    private void returnPlayerBalanceNew(ActionEvent event) {
        System.out.println(playerBalance);
    }
}
