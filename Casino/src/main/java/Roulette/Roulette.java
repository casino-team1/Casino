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

    Stage stage;

    public Roulette(MainApp mainApplication) {
        super(mainApplication);
    }

    public void displayGame() {
        PlayRoulette play = new PlayRoulette(super.getMainApp());
        play.startGame();
    }

    @Override
    public void startGame() {
        //System.out.println("Main Menu Roulette is working");
        Stage mainStageRoulette = super.getMainApp().getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuRouletteFXML.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Roulette.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        mainStageRoulette.setTitle("Roulette Wheel");
        mainStageRoulette.setScene(scene);
        mainStageRoulette.show();
        MainMenuRouletteFXMLController controller = loader.getController();
        controller.setMainApplication(super.getMainApp());
        controller.setPlayRoulette(this);
    }
}
