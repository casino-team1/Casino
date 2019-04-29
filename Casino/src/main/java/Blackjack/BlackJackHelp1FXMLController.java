/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author albio
 */
public class BlackJackHelp1FXMLController implements Initializable {

    private MainApp main;
    @FXML
    private Button buttonBeenden;
    @FXML
    private Button buttonWeiter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
        public void setMain(MainApp main) {
        this.main = main;
    }

    @FXML
    private void weiter(ActionEvent event) {
        try {                  
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackHelp2FXML.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            setMain(main);
            Stage stage = this.main.getStage();
            stage.setTitle("Hilfe");
            stage.setScene(scene);
            stage.show();
            BlackJackHelp2FXMLController controller = loader.getController();
            controller.setMain(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void beenden(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackFXML.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            setMain(main);
            Stage stage = this.main.getStage();
            stage.setTitle("Black Jack");
            stage.setScene(scene);
            stage.show();
            BlackJackFXMLController controller = loader.getController();
            controller.setMain(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
