/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Lukas Gilgen Schule
 */
public class PlayRoulette extends Spiel {

    Stage stage;

    public PlayRoulette(MainApp mainApplication) {
        super(mainApplication);
        this.stage = mainApplication.getStage();
        this.stage.setResizable(false);
    }
    private Roulette rouellte;

    public void setRoulette(Roulette rouellte) {
        this.rouellte = rouellte;
    }

    @Override
    public void startGame() {
        //System.out.println("Roulette is working");
        Stage stageRoulette = super.getMainApp().getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RouletteFXML.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Roulette.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stageRoulette.setTitle("Roulette Wheel");
        stageRoulette.setScene(scene);
        stageRoulette.show();
    }
}
