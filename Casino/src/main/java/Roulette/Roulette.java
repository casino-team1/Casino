/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Roulette;

import com.team1.casino.MainApp;
import com.team1.casino.Spiel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nick Flückiger
 */
public class Roulette extends Spiel {

    public Roulette(MainApp mainApplication) {
        super(mainApplication);
    }

    @Override
    public void startGame() {
        System.out.println("Main Menu Roulette is working");
        Stage stageRoulette = super.getMainApp().getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuRouletteFXML.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Roulette.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stageRoulette.setTitle("Roulette Wheel");
        stageRoulette.setScene(scene);
        stageRoulette.show();
    }
}
