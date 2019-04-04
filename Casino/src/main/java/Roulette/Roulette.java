/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Roulette;

import com.team1.casino.MainApp;
import com.team1.casino.Spiel;
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
        System.out.println("Roulette is working");
        Stage stageRoulette = super.getMainApp().getStage();
    }
}
