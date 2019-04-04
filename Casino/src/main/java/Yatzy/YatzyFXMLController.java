/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Erik Hess
 */
public class YatzyFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private boolean firstthrow = true;
    private ArrayList<String> keeparray = new ArrayList<>();
    private PlayYatzy yatzy;
    @FXML
    private Label lbldicelbl;
    @FXML
    private Label lbldices;
    @FXML
    private Label lblkeep;
    @FXML
    private TextField txtkeep;
    @FXML
    private Button btntrowdices;
    @FXML
    private Button btnconfirmkeep;
    
    public void setYatzy(PlayYatzy yatzy){
        this.yatzy = yatzy;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void endGame() {
        
    }
    
    public void needHelp() {
        
    }

    @FXML
    private void presstrowdices(ActionEvent event) {
        if(firstthrow == true) {
        Cup cup = new Cup();
        cup.trowDices();
        firstthrow = false;
        btntrowdices.setText("Zweiter Wurf");
        }
        else
        {
            
        }
        
        
    }

    @FXML
    private void pressconfirmkeep(ActionEvent event) {
        keeparray = new ArrayList<String>(Arrays.asList(txtkeep.getText().split("")));
    }
    
    public ArrayList<String> getkeeparray() {
        return keeparray;
    }
    
    
    
}
