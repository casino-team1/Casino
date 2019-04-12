package Roulette;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lukas Gilgen Schule
 */
public class RouletteTable {

    public int getAmountOfBet(int parameterInt){
        int betAmount = 0;
        
        return betAmount;
    }

    public boolean checkForAvailableMoney(int playerBalance, int betAmount){
        if (playerBalance < betAmount){
            return false;
        }
        else {
            return true;
        }
    }
    public void getHelp () throws IOException{
        //Create help stage
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RouletteHelpFXML.fxml"));
        Stage stageHelp = new Stage();
        stageHelp.setTitle("Help");
        stageHelp.setScene(new Scene(root, 1433, 665));
        stageHelp.setResizable(false);
        stageHelp.show();
    }
    public void checkValid(String stringBox1, String stringBox2){
        int intBox1 = Integer.parseInt(stringBox1);
        int intBox2 = Integer.parseInt(stringBox2);
        
        
    }
}
