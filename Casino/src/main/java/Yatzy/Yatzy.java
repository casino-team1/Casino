/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Yatzy;

import com.team1.casino.Controller.CasinoController;
import com.team1.casino.Model.CasinoModel;
import com.team1.casino.MainApp;
import com.team1.casino.Spiel;
import java.io.IOException;
import java.util.HashSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Erik Hess
 */
public class Yatzy extends Spiel {

    public Yatzy(MainApp mainApplication) {
        super(mainApplication);
    }

    @Override
    public void startGame() {
        try {
            Stage stage = super.getMainApp().getStage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/YatzyFXMLMenu.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.setTitle("Casino Central");
            stage.setScene(scene);
            stage.show();
            YatzyFXMLMenuController menu = loader.getController();
            menu.setYatzy(this);
        } catch (IOException e) {
        }
        
        
    }
    
    public void displayGame() {
        PlayYatzy play = new PlayYatzy(super.getMainApp());
        play.startGame();
    }
}
