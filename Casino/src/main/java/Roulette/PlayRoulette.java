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
public class PlayRoulette extends Spiel{

    Stage stage = super.getMainApp().getStage();
    
    public PlayRoulette(MainApp mainApplication) {
        super(mainApplication);
    }
    
    @Override
    public void startGame(){
        
        System.out.println("Roulette is working");
        Stage stageRoulette = super.getMainApp().getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RouletteFXML.fxml"));
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
