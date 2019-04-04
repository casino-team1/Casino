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

/**
 * FXML Controller class
 *
 * @author Erik Hess
 */
public class YatzyFXMLMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Yatzy yatzy;
    @FXML
    private Button btnstart;
    
    public void setYatzy(Yatzy yatzy){
        this.yatzy = yatzy;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void startYatzy() {
        
    }
    
    public void endGame() {
        
    }

    @FXML
    private void btnstartPress(ActionEvent event) {
        this.yatzy.displayGame();
    }

    
}
