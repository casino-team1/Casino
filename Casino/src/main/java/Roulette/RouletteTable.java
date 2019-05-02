package Roulette;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author Lukas Gilgen Schule
 */
public class RouletteTable {

    private boolean containsNumber = true;

    public int getAmountOfBet(int parameterInt) {
        int betAmount = 0;

        return betAmount;
    }

    public boolean checkForAvailableMoney(int playerBalance, int betAmount) {
        if (playerBalance < betAmount) {
            return false;
        } else {
            return true;
        }
    }

    public void getHelp() throws IOException {
        //Create help stage
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RouletteHelpFXML.fxml"));
        Stage stageHelp = new Stage();
        stageHelp.setTitle("Help");
        stageHelp.setScene(new Scene(root, 1433, 755));
        stageHelp.setResizable(false);
        stageHelp.show();
    }
    
    public boolean checkValid(String stringBox1) {

        for (int i = 0; i < 37; i++) {
            String e = Integer.toString(i);
            if (e.equals(stringBox1)) {
                containsNumber = true;
                break;
            } else if ("00".equals(stringBox1)) {
                containsNumber = true;
                break;
            }
            else if ("".equals(stringBox1)) {
                containsNumber = true;
                break;
            }
            else {
                containsNumber = false;
            }
        }
        if (containsNumber == true) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void addNeighborNumbers(String numberEntered, ComboBox neighborField) {

        ArrayList<Integer> row1Array = new ArrayList<>();
        ArrayList<Integer> row2Array = new ArrayList<>();
        ArrayList<Integer> row3Array = new ArrayList<>();

        //Creating an arrayList that contains all Numbers in row 1
        for (int i = 1; i < 37; i++) {
            row1Array.add(i);
            i += 2;
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

        if (numberEntered.equals("00")) {
            neighborField.getItems().add("3");
            neighborField.getItems().add("2");
            neighborField.getItems().add("0");
        } else if (numberEntered.equals("0")) {
            neighborField.getItems().add("00");
            neighborField.getItems().add("1");
            neighborField.getItems().add("2");
        } else {

            int numberEnteredInt = Integer.parseInt(numberEntered);
            String neighborNumberPlus3 = String.valueOf(numberEnteredInt + 3);
            String neighborNumberPlus1 = String.valueOf(numberEnteredInt + 1);
            String neighborNumberMinus3 = String.valueOf(numberEnteredInt - 3);
            String neighborNumberMinus1 = String.valueOf(numberEnteredInt - 1);

            if (row3Array.contains(numberEnteredInt) == true) {
                if (numberEnteredInt != 3 && numberEnteredInt != 36) {
                    neighborField.getItems().add(neighborNumberPlus3);
                    neighborField.getItems().add(neighborNumberMinus3);
                    neighborField.getItems().add(neighborNumberMinus1);
                } else if (numberEnteredInt == 3) {
                    neighborField.getItems().add(neighborNumberPlus3);
                    neighborField.getItems().add(neighborNumberMinus1);
                } else {
                    neighborField.getItems().add(neighborNumberMinus1);
                    neighborField.getItems().add(neighborNumberMinus3);
                }

            } else if (row2Array.contains(numberEnteredInt) == true) {
                if (numberEnteredInt != 2 && numberEnteredInt != 35) {
                    neighborField.getItems().add(neighborNumberPlus3);
                    neighborField.getItems().add(neighborNumberPlus1);
                    neighborField.getItems().add(neighborNumberMinus3);
                    neighborField.getItems().add(neighborNumberMinus1);
                } else if (numberEnteredInt == 2) {
                    neighborField.getItems().add(neighborNumberPlus3);
                    neighborField.getItems().add(neighborNumberPlus1);
                    neighborField.getItems().add(neighborNumberMinus1);
                } else {
                    neighborField.getItems().add(neighborNumberPlus1);
                    neighborField.getItems().add(neighborNumberMinus3);
                    neighborField.getItems().add(neighborNumberMinus1);
                }

            } else if (row1Array.contains(numberEnteredInt) == true) {
                if (numberEnteredInt != 1 && numberEnteredInt != 34) {
                    neighborField.getItems().add(neighborNumberPlus3);
                    neighborField.getItems().add(neighborNumberPlus1);
                    neighborField.getItems().add(neighborNumberMinus3);
                } else if (numberEnteredInt == 1) {
                    neighborField.getItems().add(neighborNumberPlus3);
                    neighborField.getItems().add(neighborNumberPlus1);
                } else {
                    neighborField.getItems().add(neighborNumberPlus1);
                    neighborField.getItems().add(neighborNumberMinus3);
                }
            }
        }
        neighborField.getSelectionModel().selectFirst();
    }

    /**
     *
     * @param neighborField
     */
    public void clear(ComboBox neighborField) {
        neighborField.getItems().clear();
    }
}
