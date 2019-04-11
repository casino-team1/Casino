/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import com.team1.casino.User.Util.UserCentral;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Schule
 */
public class YatzybetFXMLController implements Initializable {

    private int input;
    private int betnum;
    Rules rules = new Rules();
    
    @FXML
    private TextField txtbet;
    @FXML
    private Button btnbet;
    @FXML
    private Button btncancel;
    @FXML
    private Label lblerror;
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pressbtnbet(ActionEvent event) {
        boolean error = false;
        try {
            input = Integer.parseInt(txtbet.getText());
            if(input%10 == 0 && error == false) {
                betnum = input;
                //UserCentral.getInstance().getUser().setCurrentBalance(UserCentral.getInstance().getUser().getCurrentBalance() + betnum);
                Stage stage = (Stage) btncancel.getScene().getWindow();
                stage.close();
            }
            else {
                lblerror.setText("Der Betrag muss zehnstellig sein");
            }
        
        } catch(NumberFormatException e){
            lblerror.setText("Der Betrag muss eine Zahl sein");
            error = true;
        }
    }

    @FXML
    private void pressbtncancel(ActionEvent event) {
        Stage stage = (Stage) btncancel.getScene().getWindow();
        stage.close();
    }

    
  
}
