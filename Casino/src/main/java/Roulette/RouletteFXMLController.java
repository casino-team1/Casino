package Roulette;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.UserCentral;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lukas Gilgen
 */
public class RouletteFXMLController implements Initializable {

    private int playerBalance;

    private int betIntFromPlayer;

    private MainApp mainApplication;

    public void setMainApplication(MainApp mainApplication) {
        this.mainApplication = mainApplication;
    }

    private int neigborNumber1;
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

    ArrayList<Integer> c1Array = new ArrayList<>();
    ArrayList<Integer> c2Array = new ArrayList<>();
    ArrayList<Integer> c3Array = new ArrayList<>();
    ArrayList<Integer> c4Array = new ArrayList<>();
    ArrayList<Integer> c5Array = new ArrayList<>();
    ArrayList<Integer> c6Array = new ArrayList<>();
    ArrayList<Integer> c7Array = new ArrayList<>();
    ArrayList<Integer> c8Array = new ArrayList<>();
    ArrayList<Integer> c9Array = new ArrayList<>();
    ArrayList<Integer> c10Array = new ArrayList<>();
    ArrayList<Integer> c11Array = new ArrayList<>();
    ArrayList<Integer> c12Array = new ArrayList<>();

    ArrayList<Integer> fiveArray = new ArrayList<>();

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
    @FXML
    private RadioButton radioTable;
    @FXML
    private RadioButton radioNeighbor;
    @FXML
    private TextField neighborField1;
    @FXML
    private ComboBox<String> neighborField2;

    ToggleGroup group = new ToggleGroup();

    public boolean getIsNumber() {
        return isNumber;
    }

    @FXML
    private ImageView placeYourBet;
    @FXML
    private ImageView rouletteWheel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserCentral.getInstance().getUser() != null) {
            playerBalance = UserCentral.getInstance().getUser().getCurrentBalance();
        } else {
            playerBalance = 1000;
        }
        String playerBalanceString = String.valueOf(playerBalance);
        intBetBindLabel.setText(playerBalanceString);

        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{1,7}(\\d{0,4})?")) {
                    numberField.setText(oldValue);
                }
            }
        });
        neighborField1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String neighborString = neighborField1.getText();
                if (!newValue.matches("\\d{0,0}(\\d{0,2})?")) {
                    neighborField1.setText(oldValue);
                }
                if (newValue.matches("\\d{0,0}(\\d{0,2})?")) {
                    boolean checkForValidity = tables.checkValid(neighborString);
                    if (checkForValidity == false) {
                        neighborField1.setText(oldValue);
                    }
                }

                //Check if field is empty / not empty
                if (neighborField1.getText().isEmpty()) {
                    neighborField2.setEditable(false);
                    neighborField2.setOpacity(0.5);
                } else if (!neighborField1.getText().isEmpty()) {
                    neighborField2.setEditable(true);
                    neighborField2.setOpacity(1);
                }
                tables.clear(neighborField2);
                tables.addNeighborNumbers(neighborString, neighborField2);

            }
        });

        radioTable.setToggleGroup(group);
        radioTable.setSelected(true);
        radioNeighbor.setToggleGroup(group);

        betString.textProperty().bind(stringBet);
        stringBet.set("0");

        //Creating an arrayList which contains all red numbers
        int[] rouletteNumbers = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int value : rouletteNumbers) {
            redArray.add(value);
        }
        //creating an arraylist which contains all black numbers
        //  !!This is dependant on the red array and it being correct!!
        for (int i = 1; i < 37; i++) {
            if (redArray.contains(i) == false) {
                blackArray.add(i);
            }
        }
        //Creating an arrayList that contains all Numbers in row 1
        for (int i = 1; i < 37; i += 3) {
            row1Array.add(i);
        }
        //Creating an arrayList that contains all Numbers in row 2
        for (int i = 1; i < 37; i++) {
            i++;
            row2Array.add(i);
            i++;
        }
        //Creating an arrayList that contains all Numbers in row 3
        for (int i = 1; i < 37; i++) {
            i += 2;
            row3Array.add(i);
        }
    }

    public void placeBet() {
        int difference = wheels.decideResult(betInt, betIntFromPlayer);
        playerBalance += difference;
        boolean isWon = wheels.getResult();
        gewonnenVerloren.setText(isWon == true ? "Gewonnen" : "Verloren");
        intBetBindLabel.setText(String.valueOf(playerBalance));
    }

    public void placeBetArray(int AI) {
        int difference = wheels.decideResult(betArray, AI, betIntFromPlayer);
        playerBalance = playerBalance + difference;
        boolean isWon = wheels.getResult();
        gewonnenVerloren.setText(isWon == true ? "Gewonnen" : "Verloren");
        intBetBindLabel.setText(String.valueOf(playerBalance));
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
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane21(MouseEvent event) {
        betInt = 21;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane33(MouseEvent event) {
        betInt = 33;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane30(MouseEvent event) {
        betInt = 30;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane27(MouseEvent event) {
        betInt = 27;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane24(MouseEvent event) {
        betInt = 24;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane18(MouseEvent event) {
        betInt = 18;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane15(MouseEvent event) {
        betInt = 15;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane12(MouseEvent event) {
        betInt = 12;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane9(MouseEvent event) {
        betInt = 9;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane6(MouseEvent event) {
        betInt = 6;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane3(MouseEvent event) {
        betInt = 3;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane35(MouseEvent event) {
        betInt = 35;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane32(MouseEvent event) {
        betInt = 32;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane29(MouseEvent event) {
        betInt = 29;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane26(MouseEvent event) {
        betInt = 26;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane23(MouseEvent event) {
        betInt = 23;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane20(MouseEvent event) {
        betInt = 20;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane17(MouseEvent event) {
        betInt = 17;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane14(MouseEvent event) {
        betInt = 14;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane11(MouseEvent event) {
        betInt = 11;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane8(MouseEvent event) {
        betInt = 8;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane5(MouseEvent event) {
        betInt = 5;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane2(MouseEvent event) {
        betInt = 2;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane34(MouseEvent event) {
        betInt = 34;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane31(MouseEvent event) {
        betInt = 31;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane28(MouseEvent event) {
        betInt = 28;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane25(MouseEvent event) {
        betInt = 25;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane22(MouseEvent event) {
        betInt = 22;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));
    }

    @FXML
    private void clickPane19(MouseEvent event) {
        betInt = 19;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane16(MouseEvent event) {
        betInt = 16;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane13(MouseEvent event) {
        betInt = 13;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane10(MouseEvent event) {
        betInt = 10;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane7(MouseEvent event) {
        betInt = 7;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane4(MouseEvent event) {
        betInt = 4;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane1(MouseEvent event) {
        betInt = 1;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

    }

    @FXML
    private void clickPane0(MouseEvent event) {
        betInt = 0;
        isNumber = true;
        stringBet.set(String.valueOf(betInt));

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

    @FXML
    private void clickColumn1(MouseEvent event) {
        for (int i = 1; i < 4; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c1Array;
    }

    @FXML
    private void clickColumn2(MouseEvent event) {
        for (int i = 4; i < 7; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c2Array;
    }

    @FXML
    private void clickColumn3(MouseEvent event) {
        for (int i = 7; i < 10; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c3Array;
    }

    @FXML
    private void clickColumn4(MouseEvent event) {
        for (int i = 10; i < 13; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c4Array;
    }

    @FXML
    private void clickColumn5(MouseEvent event) {
        for (int i = 13; i < 16; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c5Array;
    }

    @FXML
    private void clickColumn6(MouseEvent event) {
        for (int i = 16; i < 19; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c6Array;
    }

    @FXML
    private void clickColumn7(MouseEvent event) {
        for (int i = 19; i < 22; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c7Array;
    }

    @FXML
    private void clickColumn8(MouseEvent event) {
        for (int i = 22; i < 25; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c8Array;
    }

    @FXML
    private void clickColumn9(MouseEvent event) {
        for (int i = 25; i < 28; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c9Array;
    }

    @FXML
    private void clickColumn10(MouseEvent event) {
        for (int i = 28; i < 31; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c10Array;
    }

    @FXML
    private void clickColumn11(MouseEvent event) {
        for (int i = 31; i < 34; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c11Array;
    }

    @FXML
    private void clickColumn12(MouseEvent event) {
        for (int i = 34; i < 37; i++) {
            c1Array.add(i);
            System.out.println(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c12Array;
    }

    @FXML
    private void clickFiveBet(MouseEvent event) {
        fiveArray.add(37);
        for (int i = 0; i < 4; i++) {
            fiveArray.add(i);
        }
        ArrayIdentify = 3;
        isNumber = false;
        betArray = fiveArray;
    }

    //-------------------------------------------------------------------
    //Confirm your bet and pass off the numbers
    //-------------------------------------------------------------------
    @FXML
    private void clickPlaceBet(MouseEvent event) {
        if (radioTable.isSelected() == true) {
            clickTable();
        } else {
            clickNeighbor();
        }

    }

    //-------------------------------------------------------------------
    public void clickTable() {
        String betIntFromPlayerString = numberField.getText();
        if ("".equals(betIntFromPlayerString)) {
            betIntFromPlayerString = "1";
        }
        betIntFromPlayer = Integer.parseInt(betIntFromPlayerString);
        betIntFromPlayer = betIntFromPlayer == 0 ? betIntFromPlayer + 1 : betIntFromPlayer;
        boolean availableMoneySuffice = tables.checkForAvailableMoney(playerBalance, betIntFromPlayer);
        if (availableMoneySuffice == false) {
            errorMessage.setText("Sie haben zu wenig Geld verfügbar");
            return;
        }
        errorMessage.setText("");
        if (betString.getText().trim().isEmpty()) {
            stringBet.set("0");
        }
        wheels.generateRandom();
        String wheelLocatioon = "/images/Roulette/rouletteWheelFast.gif";
        rouletteWheel.setImage(new Image(wheelLocatioon));
        PauseTransition transition = new PauseTransition(Duration.seconds(3));
        transition.setOnFinished(x -> rouletteWheel.setImage(new Image(wheelLocatioon)));
        transition.play();
        int random = wheels.getRandomNumber();
        String randomString = String.valueOf(random);
        randomNumberLabel.setText(randomString);
        if (isNumber == true) {
            placeBet();
            return;
        }
        placeBetArray(ArrayIdentify);
    }

    public void clickNeighbor() {

    }

    private PlayRoulette playRoulette;

    public void setPlayRoulette(PlayRoulette playRoulette) {
        this.playRoulette = playRoulette;
    }

    @FXML
    private void rouletteVerlassen(ActionEvent event) throws IOException {
        this.mainApplication.startRoulette();
    }

    @FXML
    private void getHelp(ActionEvent event) throws IOException {
        tables.getHelp();
    }

    public StringProperty stringBetProperty() {
        return stringBet;
    }

    private void checkForZero(int betIntFromPlayer) {

    }

    public int getPlayerBalance() {
        return playerBalance;
    }

}
