/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Schule
 */
public class YatzyhelpFXMLController implements Initializable {

    @FXML
    private Button btnclose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pressbtnclose(ActionEvent event) {
        Stage stage = (Stage) btnclose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void exitbtnclose(MouseEvent event) {
        btnclose.setStyle("-fx-background-color: rgba(1, 1, 1, 0); -fx-border-color: black; -fx-border-width: 3;");
    }

    @FXML
    private void enterbtnclose(MouseEvent event) {
        btnclose.setStyle("-fx-background-color: rgba(1, 1, 1, .1); -fx-border-color: black; -fx-border-width: 3;");
    }
    
}
