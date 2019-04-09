/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roulette;

import Baccara.Controller.BaccaraMenuViewController;
import com.team1.casino.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.util.Date;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lukas Gilgen Schule
 */
public class RouletteFXMLController implements Initializable {

    //Deklarationen für jedes Feld auf dem Roulettetisch
    //-------------------------------------------------------------------
    @FXML
    private Pane pane36;
    @FXML
    private Pane pane21;
    @FXML
    private Pane pane33;
    @FXML
    private Pane pane30;
    @FXML
    private Pane pane27;
    @FXML
    private Pane pane24;
    @FXML
    private Pane pane18;
    @FXML
    private Pane pane15;
    @FXML
    private Pane pane12;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane35;
    @FXML
    private Pane pane32;
    @FXML
    private Pane pane29;
    @FXML
    private Pane pane26;
    @FXML
    private Pane pane23;
    @FXML
    private Pane pane20;
    @FXML
    private Pane pane17;
    @FXML
    private Pane pane14;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane34;
    @FXML
    private Pane pane31;
    @FXML
    private Pane pane28;
    @FXML
    private Pane pane25;
    @FXML
    private Pane pane22;
    @FXML
    private Pane pane19;
    @FXML
    private Pane pane16;
    @FXML
    private Pane pane13;
    @FXML
    private Pane pane10;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane0;
    //-------------------------------------------------------------------

    @FXML
    private ImageView rouletteBoard1;

    //Deklarationen für Zahlengruppen auf dem Roulettetisch
    //-------------------------------------------------------------------
    @FXML
    private Pane paneManque;
    @FXML
    private Pane paneImpair;
    @FXML
    private Pane paneRed;
    @FXML
    private Pane panePasse;
    @FXML
    private Pane panePair;
    @FXML
    private Pane paneBlack;
    //-------------------------------------------------------------------

    private boolean isNumber;
    private boolean betIsPlaced = false;
    private int betInt = 0;
    RouletteWheel wheels = new RouletteWheel();
    RouletteTable tables = new RouletteTable();
    ArrayList<Integer> betArray = new ArrayList<>();

    ArrayList<Integer> redArray = new ArrayList<>();
    ArrayList<Integer> blackArray = new ArrayList<>();

    ArrayList<Integer> row1Array = new ArrayList<>();
    ArrayList<Integer> row2Array = new ArrayList<>();
    ArrayList<Integer> row3Array = new ArrayList<>();
    @FXML
    private Pane row3;
    @FXML
    private Pane row2;
    @FXML
    private Pane row1;
    @FXML
    private Button rouletteVerlassen;
    @FXML
    private RadioButton radioTable;
    @FXML
    private Button getHelp;

    public boolean getIsNumber() {
        return isNumber;
    }

    @FXML
    private ImageView placeYourBet;
    @FXML
    private ImageView rouletteWheel;

    //Spinner to select bet amount
    //-------------------------------------------------------------------
    @FXML
    private Spinner<Integer> betragSpinner;
    //-------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        //  !!This is dependant on the red array!!
        for (int i = 1; i < 37; i++) {
            if (redArray.contains(i)) {
            } else {
                blackArray.add(i);
            }
        }
        //Creating an arrayList that contains all Numbers in row 1
        System.out.println("");
        System.out.println("Row 1 here");
        for (int i = 1; i < 37; i++) {
            System.out.println(i);
            row1Array.add(i);
            i++;
            i++;
        }
        //Creating an arrayList that contains all Numbers in row 2
        System.out.println("");
        System.out.println("");
        for (int i = 1; i < 37; i++) {
            i++;
            System.out.println(i);
            row2Array.add(i);
            i++;
        }
        //Creating an arrayList that contains all Numbers in row 3
        System.out.println("");
        System.out.println("");
        for (int i = 1; i < 37; i++) {
            i++;
            i++;
            System.out.println(i);
            row3Array.add(i);
        }

        //Setting value for bet spinner
        //-------------------------------------------------------------------
        SpinnerValueFactory<Integer> betSpin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000000, 1);
        this.betragSpinner.setValueFactory(betSpin);
        //-------------------------------------------------------------------
    }

    public void placeBet() {
        System.out.println(betInt);
        wheels.decideResult(betInt);
    }

    public void placeBetArray() {
        for (int i : betArray) {
            System.out.println(i);
        }
        wheels.decideResult(betArray);
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
    }

    @FXML
    private void clickPane21(MouseEvent event) {
        betInt = 21;
        isNumber = true;

    }

    @FXML
    private void clickPane33(MouseEvent event) {
        betInt = 33;
        isNumber = true;

    }

    @FXML
    private void clickPane30(MouseEvent event) {
        betInt = 30;
        isNumber = true;

    }

    @FXML
    private void clickPane27(MouseEvent event) {
        betInt = 27;
        isNumber = true;

    }

    @FXML
    private void clickPane24(MouseEvent event) {
        betInt = 24;
        isNumber = true;
    }

    @FXML
    private void clickPane18(MouseEvent event) {
        betInt = 18;
        isNumber = true;
    }

    @FXML
    private void clickPane15(MouseEvent event) {
        betInt = 15;
        isNumber = true;
    }

    @FXML
    private void clickPane12(MouseEvent event) {
        betInt = 12;
        isNumber = true;
    }

    @FXML
    private void clickPane9(MouseEvent event) {
        betInt = 9;
        isNumber = true;
    }

    @FXML
    private void clickPane6(MouseEvent event) {
        betInt = 6;
        isNumber = true;
    }

    @FXML
    private void clickPane3(MouseEvent event) {
        betInt = 3;
        isNumber = true;
    }

    @FXML
    private void clickPane35(MouseEvent event) {
        betInt = 35;
        isNumber = true;
    }

    @FXML
    private void clickPane32(MouseEvent event) {
        betInt = 32;
        isNumber = true;
    }

    @FXML
    private void clickPane29(MouseEvent event) {
        betInt = 29;
        isNumber = true;
    }

    @FXML
    private void clickPane26(MouseEvent event) {
        betInt = 26;
        isNumber = true;
    }

    @FXML
    private void clickPane23(MouseEvent event) {
        betInt = 23;
        isNumber = true;
    }

    @FXML
    private void clickPane20(MouseEvent event) {
        betInt = 20;
        isNumber = true;
    }

    @FXML
    private void clickPane17(MouseEvent event) {
        betInt = 17;
        isNumber = true;
    }

    @FXML
    private void clickPane14(MouseEvent event) {
        betInt = 14;
        isNumber = true;
    }

    @FXML
    private void clickPane11(MouseEvent event) {
        betInt = 11;
        isNumber = true;
    }

    @FXML
    private void clickPane8(MouseEvent event) {
        betInt = 8;
        isNumber = true;
    }

    @FXML
    private void clickPane5(MouseEvent event) {
        betInt = 5;
        isNumber = true;
    }

    @FXML
    private void clickPane2(MouseEvent event) {
        betInt = 2;
        isNumber = true;
    }

    @FXML
    private void clickPane34(MouseEvent event) {
        betInt = 34;
        isNumber = true;
    }

    @FXML
    private void clickPane31(MouseEvent event) {
        betInt = 31;
        isNumber = true;
    }

    @FXML
    private void clickPane28(MouseEvent event) {
        betInt = 28;
        isNumber = true;
    }

    @FXML
    private void clickPane25(MouseEvent event) {
        betInt = 25;
        isNumber = true;
    }

    @FXML
    private void clickPane22(MouseEvent event) {
        betInt = 22;
        isNumber = true;
    }

    @FXML
    private void clickPane19(MouseEvent event) {
        betInt = 19;
        isNumber = true;
    }

    @FXML
    private void clickPane16(MouseEvent event) {
        betInt = 16;
        isNumber = true;
    }

    @FXML
    private void clickPane13(MouseEvent event) {
        betInt = 13;
        isNumber = true;
    }

    @FXML
    private void clickPane10(MouseEvent event) {
        betInt = 10;
        isNumber = true;
    }

    @FXML
    private void clickPane7(MouseEvent event) {
        betInt = 7;
        isNumber = true;
    }

    @FXML
    private void clickPane4(MouseEvent event) {
        betInt = 4;
        isNumber = true;
    }

    @FXML
    private void clickPane1(MouseEvent event) {
        betInt = 1;
        isNumber = true;
    }

    @FXML
    private void clickPane0(MouseEvent event) {
        betInt = 0;
        isNumber = true;
    }
    //-------------------------------------------------------------------

    //Methoden für jede Gruppe von Zahlen auf dem Tisch
    //-------------------------------------------------------------------
    @FXML
    private void clickPaneManque(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 1; i < 19; i++) {
            intArray.add(i);
//            System.out.println(i);
        }
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPanePasse(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 19; i < 37; i++) {
            intArray.add(i);
//            System.out.println(i);
        }
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPaneImpair(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            i++;
//            System.out.println(i);
            intArray.add(i);
        }
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPanePair(MouseEvent event) {
        ArrayList<Integer> intArray = new ArrayList<>();
        for (int i = 1; i < 37; i++) {
            i++;
//            System.out.println(i);
            intArray.add(i);
        }
        betArray = intArray;
        isNumber = false;
    }

    @FXML
    private void clickPaneRed(MouseEvent event) {
        for (int i = 0; i < redArray.size(); i++) {
            System.out.println(redArray.get(i));

        }
        betArray = redArray;
        isNumber = false;
    }

    @FXML
    private void clickPaneBlack(MouseEvent event) {
//        for (int i = 0; i < blackArray.size(); i++) {
//            System.out.println(blackArray.get(i));
//
//        }
        betArray = blackArray;
        isNumber = false;
    }

    @FXML
    private void clickRow3(MouseEvent event) {
//        for (int i = 0; i < row3Array.size(); i++) {
//            System.out.println(row3Array.get(i));
//        }
        betArray = row3Array;
        isNumber = false;
    }

    @FXML
    private void clickRow2(MouseEvent event) {
//        for (int i = 0; i < row2Array.size(); i++) {
//            System.out.println(row2Array.get(i));
//        }
        betArray = row2Array;
        isNumber = false;
    }

    @FXML
    private void clickRow1(MouseEvent event) {
//        for (int i = 0; i < row1Array.size(); i++) {
//            System.out.println(row1Array.get(i));
//        }
        isNumber = false;
        betArray = row1Array;
    }

    //-------------------------------------------------------------------
    //Confirm your bet and pass off the numbers
    //-------------------------------------------------------------------
    @FXML
    private void clickPlaceBet(MouseEvent event) {
        rouletteWheel.setImage(new Image("/images/Roulette/rouletteWheelFast.gif"));
        PauseTransition transition = new PauseTransition(Duration.seconds(3));
        transition.setOnFinished(x -> rouletteWheel.setImage(new Image("/images/Roulette/rouletteWheel.png")));
        transition.play();

        wheels.generateRandom();

        if (isNumber == true) {
            placeBet();
        } else {
            placeBetArray();
        }
    }
    //-------------------------------------------------------------------

    @FXML
    private void rouletteVerlassen(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuRouletteFXML.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage = MainApp.centerStageInScreen(stage, scene);
        stage.setTitle("Baccara Menu");
        stage.setScene(scene);
        stage.show();
        MainMenuRouletteFXMLController controller = loader.getController();
    }
    @FXML
    private void getHelp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RouletteHelpFXML.fxml"));
        Stage stageHelp = new Stage();
        stageHelp.setTitle("Help");
        stageHelp.setScene(new Scene(root, 450, 450));
        stageHelp.show();
    }
}
