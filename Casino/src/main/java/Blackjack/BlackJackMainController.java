/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.Controller.LoginController;
import com.team1.casino.MainApp;
import com.team1.casino.Model.CasinoLoginModel;
import com.team1.casino.User.Util.UserCentral;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author albio
 */
public class BlackJackMainController implements Initializable {

    private MainApp main;
    @FXML
    private Label balanceLabel;

    public void setMain(MainApp main) {
        this.main = main;
    }

    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonZurueck;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        balanceLabel.setText("Konto: " + UserCentral.getInstance().getUser().getCurrentBalance() + "$");
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BlackJackGameFXML.fxml"));
            Parent root;
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = this.main.getStage();
            stage.setTitle("Black Jack");
            stage.setScene(scene);
            stage.show();
            BlackJackGameFXMLController controller = loader.getController();
            controller.setMain(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void zurueck(ActionEvent event) throws IOException {
        this.main.displayMainMenu();
    }

}
