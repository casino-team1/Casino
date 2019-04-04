/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Yatzy;

import com.team1.casino.MainApp;
import com.team1.casino.Spiel;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

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
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/YatzyFXMLMenu.fxml"));
            PlayYatzy yatzy = new PlayYatzy();
            
        } catch (IOException e) {
        }
    }
}
