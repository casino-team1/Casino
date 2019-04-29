/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Blackjack;

import com.team1.casino.Controller.CasinoController;
import com.team1.casino.Model.CasinoModel;
import com.team1.casino.MainApp;
import com.team1.casino.Spiel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Nick Flückiger
 */
public class Blackjack extends Spiel {

    public Blackjack(MainApp mainApplication) {
        super(mainApplication);
    }

    @Override
    public void startGame() {
        Stage stage = super.getMainApp().getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackMainFXML.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Blackjack.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("Black Jack");
        BlackJackMainController controller = loader.getController();
        controller.setMain(super.getMainApp());
        stage.setScene(scene);
        stage.show();
    }
}
