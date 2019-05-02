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
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane coverPane;

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

    ArrayList<Integer> neighborArray = new ArrayList<>();

    ArrayList<Integer> Q1Array = new ArrayList<>();
    ArrayList<Integer> Q2Array = new ArrayList<>();
    ArrayList<Integer> Q3Array = new ArrayList<>();
    ArrayList<Integer> Q4Array = new ArrayList<>();
    ArrayList<Integer> Q5Array = new ArrayList<>();
    ArrayList<Integer> Q6Array = new ArrayList<>();
    ArrayList<Integer> Q7Array = new ArrayList<>();
    ArrayList<Integer> Q8Array = new ArrayList<>();
    ArrayList<Integer> Q9Array = new ArrayList<>();
    ArrayList<Integer> Q10Array = new ArrayList<>();
    ArrayList<Integer> Q11Array = new ArrayList<>();
    ArrayList<Integer> Q12Array = new ArrayList<>();
    ArrayList<Integer> Q13Array = new ArrayList<>();
    ArrayList<Integer> Q14Array = new ArrayList<>();
    ArrayList<Integer> Q15Array = new ArrayList<>();
    ArrayList<Integer> Q16Array = new ArrayList<>();
    ArrayList<Integer> Q17Array = new ArrayList<>();
    ArrayList<Integer> Q18Array = new ArrayList<>();
    ArrayList<Integer> Q19Array = new ArrayList<>();
    ArrayList<Integer> Q20Array = new ArrayList<>();
    ArrayList<Integer> Q21Array = new ArrayList<>();
    ArrayList<Integer> Q22Array = new ArrayList<>();

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
    @FXML
    private Label balanceLabel;

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
            playerBalance = UserCentral.getInstance().getUser().getCurrentChipBalance();
        } else {
            playerBalance = 1000;
        }
        String playerBalanceString = String.valueOf(playerBalance);
        intBetBindLabel.setText(playerBalanceString);

        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}(\\d{0,2})?")) {
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
                    neighborField2.setEditable(false);
                    neighborField2.setOpacity(1);
                }
                tables.clear(neighborField2);
                tables.addNeighborNumbers(neighborString, neighborField2);

                String stringFromField1 = neighborField1.getText();
                String stringFromField2 = String.valueOf(neighborField2.getValue());

                stringBet.set(stringFromField1 + ", " + stringFromField2);

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
        stringBet.set("1 - 18");
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
        stringBet.set("19 - 36");
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
        stringBet.set("Ungerade");
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
        stringBet.set("Gerade");
    }

    @FXML
    private void clickPaneRed(MouseEvent event) {
        ArrayIdentify = 1;
        betArray = redArray;
        isNumber = false;
        stringBet.set("Rot");
    }

    @FXML
    private void clickPaneBlack(MouseEvent event) {
        ArrayIdentify = 1;
        betArray = blackArray;
        isNumber = false;
        stringBet.set("Schwarz");
    }

    @FXML
    private void clickRow3(MouseEvent event) {
        ArrayIdentify = 2;
        betArray = row3Array;
        isNumber = false;
        stringBet.set("Reihe 3");
    }

    @FXML
    private void clickRow2(MouseEvent event) {
        ArrayIdentify = 2;
        betArray = row2Array;
        isNumber = false;
        stringBet.set("Reihe 2");
    }

    @FXML
    private void clickRow1(MouseEvent event) {
        ArrayIdentify = 2;
        isNumber = false;
        betArray = row1Array;
        stringBet.set("Reihe 1");
    }

    @FXML
    private void clickPaneD1(MouseEvent event) {
        for (int i = 1; i < 13; i++) {
            d1Array.add(i);
        }
        ArrayIdentify = 2;
        isNumber = false;
        betArray = d1Array;
        stringBet.set("Duzend 1");
    }

    @FXML
    private void clickPaneD2(MouseEvent event) {
        for (int i = 13; i < 25; i++) {
            d2Array.add(i);
        }
        ArrayIdentify = 2;
        isNumber = false;
        betArray = d2Array;
        stringBet.set("Duzend 2");
    }

    @FXML
    private void clickPaneD3(MouseEvent event) {
        for (int i = 25; i < 37; i++) {
            d3Array.add(i);
        }
        ArrayIdentify = 2;
        isNumber = false;
        betArray = d3Array;
        stringBet.set("Duzend 3");
    }

    @FXML
    private void clickColumn1(MouseEvent event) {
        for (int i = 1; i < 4; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c1Array;
        stringBet.set("1, 2, 3");
    }

    @FXML
    private void clickColumn2(MouseEvent event) {
        for (int i = 4; i < 7; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c2Array;
        stringBet.set("4, 5, 6");
    }

    @FXML
    private void clickColumn3(MouseEvent event) {
        for (int i = 7; i < 10; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c3Array;
        stringBet.set("7, 8, 9");
    }

    @FXML
    private void clickColumn4(MouseEvent event) {
        for (int i = 10; i < 13; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c4Array;
        stringBet.set("10, 11, 12");
    }

    @FXML
    private void clickColumn5(MouseEvent event) {
        for (int i = 13; i < 16; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c5Array;
        stringBet.set("13, 14, 15");
    }

    @FXML
    private void clickColumn6(MouseEvent event) {
        for (int i = 16; i < 19; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c6Array;
        stringBet.set("16, 17, 18");
    }

    @FXML
    private void clickColumn7(MouseEvent event) {
        for (int i = 19; i < 22; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c7Array;
        stringBet.set("19, 20, 21");
    }

    @FXML
    private void clickColumn8(MouseEvent event) {
        for (int i = 22; i < 25; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c8Array;
        stringBet.set("22, 23, 24");
    }

    @FXML
    private void clickColumn9(MouseEvent event) {
        for (int i = 25; i < 28; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c9Array;
        stringBet.set("25, 26, 27");
    }

    @FXML
    private void clickColumn10(MouseEvent event) {
        for (int i = 28; i < 31; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c10Array;
        stringBet.set("28, 29, 30");
    }

    @FXML
    private void clickColumn11(MouseEvent event) {
        for (int i = 31; i < 34; i++) {
            c1Array.add(i);
        }
        ArrayIdentify = 5;
        isNumber = false;
        betArray = c11Array;
        stringBet.set("31, 32, 33");
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
        stringBet.set("34, 35, 36");
    }

    @FXML
    private void clickPaneQ1(MouseEvent event) {
        Q1Array.add(1);
        Q1Array.add(2);
        Q1Array.add(4);
        Q1Array.add(5);

        betArray = Q1Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("1, 2, 4, 5");
    }

    @FXML
    private void clickPaneQ2(MouseEvent event) {
        Q2Array.add(2);
        Q2Array.add(3);
        Q2Array.add(5);
        Q2Array.add(6);

        betArray = Q2Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("2, 3, 5, 6");
    }

    @FXML
    private void clickPaneQ3(MouseEvent event) {
        Q3Array.add(4);
        Q3Array.add(5);
        Q3Array.add(7);
        Q3Array.add(8);

        betArray = Q3Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("4, 5, 7, 8");
    }

    @FXML
    private void clickPaneQ4(MouseEvent event) {
        Q4Array.add(5);
        Q4Array.add(6);
        Q4Array.add(8);
        Q4Array.add(9);

        betArray = Q4Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("5, 6, 8, 9");
    }

    @FXML
    private void clickPaneQ5(MouseEvent event) {
        Q5Array.add(7);
        Q5Array.add(8);
        Q5Array.add(10);
        Q5Array.add(11);

        betArray = Q5Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("7, 8, 10, 11");
    }

    @FXML
    private void clickPaneQ6(MouseEvent event) {
        Q6Array.add(8);
        Q6Array.add(9);
        Q6Array.add(11);
        Q6Array.add(12);

        betArray = Q6Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("8, 9, 11, 12");
    }

    @FXML
    private void clickPaneQ7(MouseEvent event) {
        Q7Array.add(10);
        Q7Array.add(11);
        Q7Array.add(13);
        Q7Array.add(14);

        betArray = Q7Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("10, 11, 13, 14");
    }

    @FXML
    private void clickPaneQ8(MouseEvent event) {
        Q8Array.add(11);
        Q8Array.add(12);
        Q8Array.add(14);
        Q8Array.add(15);

        betArray = Q8Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("11, 12, 14, 15");
    }

    @FXML
    private void clickPaneQ9(MouseEvent event) {
        Q9Array.add(13);
        Q9Array.add(14);
        Q9Array.add(16);
        Q9Array.add(17);

        betArray = Q9Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("13, 14, 16, 17");
    }

    @FXML
    private void clickPaneQ10(MouseEvent event) {
        Q10Array.add(14);
        Q10Array.add(15);
        Q10Array.add(17);
        Q10Array.add(18);

        betArray = Q10Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("14, 15, 17, 18");
    }

    @FXML
    private void clickPaneQ11(MouseEvent event) {
        Q11Array.add(16);
        Q11Array.add(17);
        Q11Array.add(19);
        Q11Array.add(20);

        betArray = Q11Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("16, 17, 19, 20");
    }

    @FXML
    private void clickPaneQ12(MouseEvent event) {
        Q12Array.add(17);
        Q12Array.add(18);
        Q12Array.add(20);
        Q12Array.add(21);

        betArray = Q12Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("17, 18, 20, 21");
    }

    @FXML
    private void clickPaneQ13(MouseEvent event) {
        Q13Array.add(19);
        Q13Array.add(20);
        Q13Array.add(22);
        Q13Array.add(23);

        betArray = Q13Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("19, 20, 22, 23");
    }

    @FXML
    private void clickPaneQ14(MouseEvent event) {
        Q14Array.add(20);
        Q14Array.add(21);
        Q14Array.add(23);
        Q14Array.add(24);

        betArray = Q14Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("20, 21, 23, 24");
    }

    @FXML
    private void clickPaneQ15(MouseEvent event) {
        Q15Array.add(22);
        Q15Array.add(23);
        Q15Array.add(25);
        Q15Array.add(26);

        betArray = Q15Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("22, 23, 25, 26");
    }

    @FXML
    private void clickPaneQ16(MouseEvent event) {
        Q16Array.add(23);
        Q16Array.add(24);
        Q16Array.add(26);
        Q16Array.add(27);

        betArray = Q16Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("23, 24, 26, 27");
    }

    @FXML
    private void clickPaneQ17(MouseEvent event) {
        Q17Array.add(25);
        Q17Array.add(26);
        Q17Array.add(28);
        Q17Array.add(29);

        betArray = Q17Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("25, 26, 28, 29");
    }

    @FXML
    private void clickPaneQ18(MouseEvent event) {
        Q18Array.add(26);
        Q18Array.add(27);
        Q18Array.add(29);
        Q18Array.add(30);

        betArray = Q18Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("26, 27, 29, 30");
    }

    @FXML
    private void clickPaneQ19(MouseEvent event) {
        Q19Array.add(28);
        Q19Array.add(29);
        Q19Array.add(31);
        Q19Array.add(32);

        betArray = Q19Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("28, 29, 31, 32");
    }

    @FXML
    private void clickPaneQ20(MouseEvent event) {
        Q20Array.add(29);
        Q20Array.add(30);
        Q20Array.add(32);
        Q20Array.add(33);

        betArray = Q20Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("29, 30, 32, 33");
    }

    @FXML
    private void clickPaneQ21(MouseEvent event) {
        Q21Array.add(31);
        Q21Array.add(32);
        Q21Array.add(34);
        Q21Array.add(35);

        betArray = Q21Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("31, 32, 34, 35");
    }

    @FXML
    private void clickPaneQ22(MouseEvent event) {
        Q22Array.add(32);
        Q22Array.add(33);
        Q22Array.add(35);
        Q22Array.add(36);

        betArray = Q22Array;
        ArrayIdentify = 4;
        isNumber = false;
        stringBet.set("32, 33, 35, 36");
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
        stringBet.set("Five Bet");
    }

    //-------------------------------------------------------------------
    //Confirm your bet and pass off the numbers
    //-------------------------------------------------------------------
    @FXML
    private void clickPlaceBet(MouseEvent event) {
        String betIntFromPlayerString = numberField.getText();

        if ("".equals(betIntFromPlayerString)) {
            betIntFromPlayerString = "1";
        }
        betIntFromPlayer = Integer.parseInt(betIntFromPlayerString);
        betIntFromPlayer = betIntFromPlayer == 0 ? betIntFromPlayer + 1 : betIntFromPlayer;
        boolean availableMoneySuffice = tables.checkForAvailableMoney(playerBalance, betIntFromPlayer);
        if (availableMoneySuffice == false) {
            errorMessage.setText("Sie haben zu wenig Geld verfügbar");
        } else {
            errorMessage.setText("");
            if (betString.getText().trim().isEmpty()) {
                stringBet.set("0");
            }
            wheels.generateRandom();
            rouletteWheel.setImage(new Image("/images/Roulette/rouletteWheelFast.gif"));
            PauseTransition transition = new PauseTransition(Duration.seconds(3));
            transition.setOnFinished(x -> rouletteWheel.setImage(new Image("/images/Roulette/rouletteWheel.png")));
            transition.play();
            int random = wheels.getRandomNumber();
            String randomString = String.valueOf(random);
            if (random == 37) {
                randomNumberLabel.setText("00");
            } else {
                randomNumberLabel.setText(randomString);

            }

            if (radioTable.isSelected() == true) {
                clickTable();
            } else {
                clickNeighbor();

            }

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
    //    -------------------------------------------------------------------

    public void clickTable() {

        if (isNumber == true) {
            placeBet();
        } else {
            placeBetArray(ArrayIdentify);
        }
        errorMessage.setText("");
    }

    public void clickNeighbor() {
        String stringFromField1 = neighborField1.getText();
        

        if (!stringFromField1.equals("")) {
            String stringFromField2 = neighborField2.getValue();
            
            int intFromF1 = Integer.parseInt(stringFromField1);
            int intFromF2 = Integer.parseInt(stringFromField2);

            neighborArray.add(intFromF1);
            neighborArray.add(intFromF2);

            betArray = neighborArray;

            ArrayIdentify = 6;

            placeBetArray(ArrayIdentify);
            errorMessage.setText("");
        } else {
            errorMessage.setText("Geben sie zwei Zahlen an");
            String wheelLocatioon = "/images/Roulette/rouletteWheel.png";
            rouletteWheel.setImage(new Image(wheelLocatioon));
        }
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

    public int getPlayerBalance() {
        return playerBalance;
    }

    @FXML
    private void setPaneNeighbor(ActionEvent event) {
        coverPane.setPrefSize(221, 72);
        coverPane.setLayoutX(717);
        coverPane.setLayoutY(498);
    }

    @FXML
    private void setPaneBoard(ActionEvent event) {
        coverPane.setPrefSize(630, 385);
        coverPane.setLayoutX(641);
        coverPane.setLayoutY(118);
    }

}
