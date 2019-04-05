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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private boolean secondthrow = true;
    private ArrayList<String> keeparray = new ArrayList<>();
    private ArrayList<Dice> keep = new ArrayList<>();
    private ArrayList<Dice> gearray = new ArrayList<>();
    private ArrayList<Dice> bearray = new ArrayList<>();
    
    Cup cup = new Cup();
    
    
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
    private Button btnthrowdices;
    @FXML
    private Label lbltest;
    @FXML
    private ImageView ge1;
    @FXML
    private ImageView ge2;
    @FXML
    private ImageView ge3;
    @FXML
    private ImageView ge4;
    @FXML
    private ImageView ge5;
    @FXML
    private ImageView be1;
    @FXML
    private ImageView be2;
    @FXML
    private ImageView be3;
    @FXML
    private ImageView be4;
    @FXML
    private ImageView be5;
    
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

    public ArrayList<String> getkeeparray() {
        return keeparray;
    }

    @FXML
    private void pressthrowdices(ActionEvent event) {
        if(firstthrow == true) {   
        
        firstthrow = false;
        btnthrowdices.setText("Zweiter Wurf");
        }
        else if(secondthrow == true && firstthrow == false)
        {
            keeparray = new ArrayList<String>(Arrays.asList(txtkeep.getText().split("")));
            
            for(int i = 0; i < keeparray.size(); i++) {
            Dice dice = new Dice();
            dice.setValue(Integer.parseInt(keeparray.get(i)));
            keep.add(dice);
            }
            cup.setKeep(keep);
            cup.throwDices();
            
            
            
            
            secondthrow = false;
            btnthrowdices.setText("Dritter Wurf");
        }
        else {
            keeparray = new ArrayList<String>(Arrays.asList(txtkeep.getText().split("")));
            
            for(int i = 0; i < keeparray.size(); i++) {
            Dice dice = new Dice();
            dice.setValue(Integer.parseInt(keeparray.get(i)));
            keep.add(dice);
            }
            cup.setKeep(keep);
            cup.throwDices();
            
            Rules rules = new Rules();
            rules.calculateResult(cup.getDicearray());
            lbltest.setText("" + rules.getResult());
        }
        String output = "";
        for(int i = 0; i < 5; i++){
        output += cup.getDicearray().get(i).getValue();
        lbldices.setText(output);
        gearray.addAll(cup.getDicearray());
        }
        
        
    }

    @FXML
    private void pressGe1(MouseEvent event) {
        int value;
        value = gearray.get(0).getValue();
    }

    @FXML
    private void pressGe2(MouseEvent event) {
        int value;
        value = gearray.get(1).getValue();
    }

    @FXML
    private void pressGe3(MouseEvent event) {
        int value;
        value = gearray.get(2).getValue();
    }

    @FXML
    private void pressGe4(MouseEvent event) {
        int value;
        value = gearray.get(3).getValue();
    }

    @FXML
    private void pressGe5(MouseEvent event) {
        int value;
        value = gearray.get(4).getValue();
    }

    @FXML
    private void pressBe1(MouseEvent event) {
        int value;
        
    }

    @FXML
    private void pressBe2(MouseEvent event) {
    }

    @FXML
    private void pressBe3(MouseEvent event) {
    }

    @FXML
    private void pressBe4(MouseEvent event) {
    }

    @FXML
    private void pressBe5(MouseEvent event) {
    }
        

    
    
    
    
}
