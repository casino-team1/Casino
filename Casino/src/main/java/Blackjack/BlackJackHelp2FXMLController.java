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
public class BlackJackHelp2FXMLController implements Initializable {
    
    private MainApp main = new MainApp();
    private Stage stage;
    
    @FXML
    private Button buttonZurueck;
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void zurueck(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackHelp1FXML.fxml"));
        Parent root;
        root = (Parent) loader.load();
        Stage stageHelp = new Stage();
        this.stage.setTitle("Hilfe");
        this.stage.setScene(new Scene(root, 776, 529));
        stage.setResizable(false);
        BlackJackHelp1FXMLController cont = loader.getController();
        cont.setStage(stage);
        stage.show();
    }

    @FXML
    private void weiter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackHelp3FXML.fxml"));
        Parent root;
        root = (Parent) loader.load();
        Stage stageHelp = new Stage();
        this.stage.setTitle("Hilfe");
        this.stage.setScene(new Scene(root, 750, 520));
        stage.setResizable(false);
        BlackJackHelp3FXMLController cont = loader.getController();
        cont.setStage(stage);
        stage.show();
    }    
}
