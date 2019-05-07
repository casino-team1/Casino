/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import com.team1.casino.MainApp;
import com.team1.casino.User.Util.PlayerCentral;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
        balanceLabel.setText("Konto: " + PlayerCentral.getInstance().getUser().getCurrentChipBalance());
    }
    
    public void setMain(MainApp main) {
        this.main = main;
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
            stage.centerOnScreen();
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

    @FXML
    private void exitButtonZurueck(MouseEvent event) {
        buttonZurueck.setStyle("-fx-background-color: rgba(1, 1, 1, 0); -fx-border-color: black; -fx-border-width: 3;");
    }

    @FXML
    private void enterButtonZurueck(MouseEvent event) {
        buttonZurueck.setStyle("-fx-background-color: rgba(1, 1, 1, .1); -fx-border-color: black; -fx-border-width: 3;");
    }

}
