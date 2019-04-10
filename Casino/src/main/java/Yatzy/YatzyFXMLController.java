/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private boolean secondthrow = false;
    private boolean lockdices = false;
    private boolean locklabels = true;
    private boolean finish = false;
    private boolean newround = false;
    private ArrayList<Dice> keep = new ArrayList<>();
    private ArrayList<Dice> gearray = new ArrayList<>();
    private ArrayList<Dice> bearray = new ArrayList<>();
    private ArrayList<Dice> finalarray = new ArrayList<>(); 

    Cup cup = new Cup(); 
    Images img = new Images();
    Rules rules = new Rules();

    private PlayYatzy yatzy;
    @FXML
    private Button btnthrowdices;
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
    @FXML
    private Button btnhelp;
    @FXML
    private Button btnendgame;
    @FXML
    private Label sclblup1;
    @FXML
    private Label sclblup2;
    @FXML
    private Label sclblup3;
    @FXML
    private Label sclblup4;
    @FXML
    private Label sclblup5;
    @FXML
    private Label sclblup6;
    @FXML
    private Label sclblup7;
    @FXML
    private Label sclblup8;
    @FXML
    private Label lblup1;
    @FXML
    private Label lblup2;
    @FXML
    private Label lblup3;
    @FXML
    private Label lblup4;
    @FXML
    private Label lblup5;
    @FXML
    private Label lblup6;
    @FXML
    private Label lblup7;
    @FXML
    private Label lblup8;
    @FXML
    private Label sclbllo1;
    @FXML
    private Label sclbllo2;
    @FXML
    private Label sclbllo3;
    @FXML
    private Label sclbllo4;
    @FXML
    private Label sclbllo5;
    @FXML
    private Label sclbllo6;
    @FXML
    private Label sclbllo7;
    @FXML
    private Label sclbllo8;
    @FXML
    private Label sclbllo9;
    @FXML
    private Label lbllo1;
    @FXML
    private Label lbllo2;
    @FXML
    private Label lbllo3;
    @FXML
    private Label lbllo4;
    @FXML
    private Label lbllo5;
    @FXML
    private Label lbllo6;
    @FXML
    private Label lbllo7;
    @FXML
    private Label lbllo8;
    @FXML
    private Label lbllo9;
    
    public void setYatzy(PlayYatzy yatzy) {
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

    public ArrayList<Dice> getFinalArray() {
        return finalarray;
    }

    @FXML
    private void pressthrowdices(ActionEvent event) {
        
        //the displayed images get updated
        assignImages();
        
        //if a new game was startet, after the previous one has been finished
        if(newround == true) {
            
            //makes the next throw the first throw
            newround = false;
            btnthrowdices.setText("Würfel werfen");
            
            //makes the dices unusable
            lockdices = true;
            
            //makes the figure lables unusable
            locklabels = true;             
            
            //reseting everything for a new game
            keep.clear();
            gearray.clear();
            gearray.clear();
            finalarray.clear();
            firstthrow = true;
            secondthrow = false;
            finish = false;
            rules.setLowerbool(false);
            rules.setUpperbool(false);
            rules.setLowercounter(0);
            rules.setUppercounter(0);
            rules.setLowertotal(0);
            rules.setUppertotal(0);
            
            sclblup1.setText("");
            sclblup2.setText("");
            sclblup3.setText("");
            sclblup4.setText("");
            sclblup5.setText("");
            sclblup6.setText("");
            sclblup7.setText("");
            sclblup8.setText("");
            sclbllo1.setText("");
            sclbllo2.setText("");
            sclbllo3.setText("");
            sclbllo4.setText("");
            sclbllo5.setText("");
            sclbllo6.setText("");
            sclbllo7.setText("");
            sclbllo8.setText("");
            sclbllo9.setText("");
            
            //the displayed images get updated
            assignImages();
        }  
        
        //the first dicethrow
        else if (firstthrow == true) {
            
            //makes the next throw the second throw
            firstthrow = false;
            secondthrow = true;
            btnthrowdices.setText("Zweiter Wurf");
            
            //makes the dices usable
            lockdices = false;
            
            //generates an array of five dices
            cup.throwDices();
            
            //attribute for the toprow gets the generated dices
            gearray.addAll(cup.getDicearray());
            
            //the displayed images get updated
            assignImages();   
        } 
        
        //the second dicethrow
        else if (secondthrow == true) {
                
            //makes the next throw the third throw
            secondthrow = false;
            btnthrowdices.setText("Dritter Wurf");
            
            keep.clear();
            keep.addAll(bearray);
            gearray.clear();
            cup.setKeep(bearray);
            cup.throwDices();
            gearray.addAll(cup.getDicearray());           
            assignImages();  
        } 
        
        //the third dicethrow
        else {
            
            btnthrowdices.setText("Figur Wählen");
            btnthrowdices.setDisable(true);
            //makes the dices unusable
            lockdices = true;
            //makes the figure lables usable
            locklabels = false;
            
            keep.clear();
            keep.addAll(bearray);
            gearray.clear();
            cup.setKeep(bearray);
            cup.throwDices();
            gearray.addAll(cup.getDicearray());
            gearray.addAll(bearray);
            bearray.clear();
            finalarray.clear();
            finalarray.addAll(gearray);
            
            //the final dices get sorted from lowest to highest
            Collections.sort(gearray, new Comparator<Dice>() {
            @Override
            public int compare(Dice dice2, Dice dice1)
            {
            return  dice2.getValue() - dice1.getValue();
            }
            });  
            assignImages();
        }

        
        


    }
    
    @FXML
    private void presshelp(ActionEvent event) {
    }

    @FXML
    private void pressendgame(ActionEvent event) {
    }

    @FXML
    private void pressGe1(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(0));
            gearray.remove(0);
            assignImages();
        }
    }

    @FXML
    private void pressGe2(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(1));
            gearray.remove(1);
            assignImages();
        }
    }

    @FXML
    private void pressGe3(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(2));
            gearray.remove(2);
            assignImages();
        }
    }

    @FXML
    private void pressGe4(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(3));
            gearray.remove(3);
            assignImages();
        }
    }

    @FXML
    private void pressGe5(MouseEvent event) {
        if (lockdices == false) {
            bearray.add(gearray.get(4));
            gearray.remove(4);
            assignImages();
        }
    }

    @FXML
    private void pressBe1(MouseEvent event) {
        gearray.add(bearray.get(0));
        bearray.remove(0);
        assignImages();
    }

    @FXML
    private void pressBe2(MouseEvent event) {
        gearray.add(bearray.get(1));
        bearray.remove(1);
        assignImages();
    }

    @FXML
    private void pressBe3(MouseEvent event) {
        gearray.add(bearray.get(2));
        bearray.remove(2);
        assignImages();
    }

    @FXML
    private void pressBe4(MouseEvent event) {
        gearray.add(bearray.get(3));
        bearray.remove(3);
        assignImages();
    }

    @FXML
    private void pressBe5(MouseEvent event) {
        gearray.add(bearray.get(4));
        bearray.remove(4);
        assignImages();
    }

    @FXML
    private void presslblup1(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclblup1.getText().equals("") || sclblup1.getText() == null) {
            sclblup1.setText(Integer.toString(rules.createSubresult("EINER", finalarray)));   
            newTurn();
        } else {
            notPressable(lblup1);
        }

    }

    @FXML
    private void presslblup2(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclblup2.getText().equals("") || sclblup2.getText() == null) {
            sclblup2.setText(Integer.toString(rules.createSubresult("ZWEIER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup2);
        }
    }

    @FXML
    private void presslblup3(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclblup3.getText().equals("") || sclblup3.getText() == null) {
            sclblup3.setText(Integer.toString(rules.createSubresult("DREIER", finalarray)));           
            newTurn();
        } else {
            notPressable(lblup3);
        }
    }

    @FXML
    private void presslblup4(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclblup4.getText().equals("") || sclblup4.getText() == null) {
            sclblup4.setText(Integer.toString(rules.createSubresult("VIERER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup4);
        }
    }

    @FXML
    private void presslblup5(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclblup5.getText().equals("") || sclblup5.getText() == null) {
            sclblup5.setText(Integer.toString(rules.createSubresult("FÜNFER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup5);
        }
    }

    @FXML
    private void presslblup6(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclblup6.getText().equals("") || sclblup6.getText() == null) {
            sclblup6.setText(Integer.toString(rules.createSubresult("SECHSER", finalarray)));
            newTurn();
        } else {
            notPressable(lblup6);
        }
    }

    @FXML
    private void presslbllo1(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo1.getText().equals("") || sclbllo1.getText() == null) {
            sclbllo1.setText(Integer.toString(rules.createSubresult("DREI GLEICHE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo1);
        }
    }

    @FXML
    private void presslbllo2(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo2.getText().equals("") || sclbllo2.getText() == null) {
            sclbllo2.setText(Integer.toString(rules.createSubresult("VIER GLEICHE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo2);
        }
    }

    @FXML
    private void presslbllo3(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo3.getText().equals("") || sclbllo3.getText() == null) { 
            sclbllo3.setText(Integer.toString(rules.createSubresult("FULL HOUSE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo3);
        }
    }

    @FXML
    private void presslbllo4(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo4.getText().equals("") || sclbllo4.getText() == null) {        
            sclbllo4.setText(Integer.toString(rules.createSubresult("KLEINE STRASSE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo4);
        }
    }

    @FXML
    private void presslbllo5(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo5.getText().equals("") || sclbllo5.getText() == null) {
            sclbllo5.setText(Integer.toString(rules.createSubresult("GROSSE STRASSE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo5);
        }
    }

    @FXML
    private void presslbllo6(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo6.getText().equals("") || sclbllo6.getText() == null) {
            sclbllo6.setText(Integer.toString(rules.createSubresult("YATZY", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo6);
        }
    }

    @FXML
    private void presslbllo7(MouseEvent event) {
        if (locklabels == true) {
        }       
        else if (sclbllo7.getText().equals("") || sclbllo7.getText() == null) {
            sclbllo7.setText(Integer.toString(rules.createSubresult("CHANCE", finalarray)));
            newTurn();
        } else {
            notPressable(lbllo7);
        }
    }

    @FXML
    private void exitlblup1(MouseEvent event) {
        exithover(lblup1);
    }

    @FXML
    private void enterlblup1(MouseEvent event) {
        enterhover(lblup1);
    }

    @FXML
    private void exitlblup2(MouseEvent event) {
        exithover(lblup2);
    }

    @FXML
    private void enterlblup2(MouseEvent event) {
        enterhover(lblup2);
    }

    @FXML
    private void exitlblup3(MouseEvent event) {
        exithover(lblup3);
    }

    @FXML
    private void enterlblup3(MouseEvent event) {
        enterhover(lblup3);
    }

    @FXML
    private void exitlblup4(MouseEvent event) {
        exithover(lblup4);
    }

    @FXML
    private void enterlblup4(MouseEvent event) {
        enterhover(lblup4);
    }

    @FXML
    private void exitlblup5(MouseEvent event) {
        exithover(lblup5);
    }

    @FXML
    private void enterlblup5(MouseEvent event) {
        enterhover(lblup5);
    }

    @FXML
    private void exitlblup6(MouseEvent event) {
        exithover(lblup6);
    }

    @FXML
    private void enterlblup6(MouseEvent event) {
        enterhover(lblup6);
    }

    @FXML
    private void exitlbllo1(MouseEvent event) {
        exithover(lbllo1);
    }

    @FXML
    private void enterlbllo1(MouseEvent event) {
        enterhover(lbllo1);
    }

    @FXML
    private void exitlbllo2(MouseEvent event) {
        exithover(lbllo2);
    }

    @FXML
    private void enterlbllo2(MouseEvent event) {
        enterhover(lbllo2);
    }

    @FXML
    private void exitlbllo3(MouseEvent event) {
        exithover(lbllo3);
    }

    @FXML
    private void enterlbllo3(MouseEvent event) {
        enterhover(lbllo3);
    }

    @FXML
    private void exitlbllo4(MouseEvent event) {
        exithover(lbllo4);
    }

    @FXML
    private void enterlbllo4(MouseEvent event) {
        enterhover(lbllo4);
    }

    @FXML
    private void exitlbllo5(MouseEvent event) {
        exithover(lbllo5);
    }

    @FXML
    private void enterlbllo5(MouseEvent event) {
        enterhover(lbllo5);
    }

    @FXML
    private void exitlbllo6(MouseEvent event) {
        exithover(lbllo6);
    }

    @FXML
    private void enterlbllo6(MouseEvent event) {
        enterhover(lbllo6);
    }

    @FXML
    private void exitlbllo7(MouseEvent event) {
        exithover(lbllo7);
    }

    @FXML
    private void enterlbllo7(MouseEvent event) {
        enterhover(lbllo7);
    }

    public void assignImages() {

        if (gearray.size() < 1) {
            ge1.setVisible(false);
        } else {
            this.ge1.setImage(new Image(img.getImage(gearray.get(0).getValue())));
            ge1.setVisible(true);
        }

        if (gearray.size() < 2) {
            ge2.setVisible(false);
        } else {
            this.ge2.setImage(new Image(img.getImage(gearray.get(1).getValue())));
            ge2.setVisible(true);
        }

        if (gearray.size() < 3) {
            ge3.setVisible(false);
        } else {
            this.ge3.setImage(new Image(img.getImage(gearray.get(2).getValue())));
            ge3.setVisible(true);
        }

        if (gearray.size() < 4) {
            ge4.setVisible(false);
        } else {
            this.ge4.setImage(new Image(img.getImage(gearray.get(3).getValue())));
            ge4.setVisible(true);
        }

        if (gearray.size() < 5) {
            ge5.setVisible(false);
        } else {
            this.ge5.setImage(new Image(img.getImage(gearray.get(4).getValue())));
            ge5.setVisible(true);
        }

        if (bearray.size() < 1) {
            be1.setVisible(false);
        } else {
            this.be1.setImage(new Image(img.getImage(bearray.get(0).getValue())));
            be1.setVisible(true);
        }

        if (bearray.size() < 2) {
            be2.setVisible(false);
        } else {
            this.be2.setImage(new Image(img.getImage(bearray.get(1).getValue())));
            be2.setVisible(true);
        }

        if (bearray.size() < 3) {
            be3.setVisible(false);
        } else {
            this.be3.setImage(new Image(img.getImage(bearray.get(2).getValue())));
            be3.setVisible(true);
        }

        if (bearray.size() < 4) {
            be4.setVisible(false);
        } else {
            this.be4.setImage(new Image(img.getImage(bearray.get(3).getValue())));
            be4.setVisible(true);
        }

        if (bearray.size() < 5) {
            be5.setVisible(false);
        } else {
            this.be5.setImage(new Image(img.getImage(bearray.get(4).getValue())));
            be5.setVisible(true);
        }
    }
    
    public void enterhover(Label lbl) {
        lbl.setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-color: white;");
    }

    public void exithover(Label lbl) {
        lbl.setStyle("-fx-border-color: black; -fx-border-width: 1;");
    }

    public void notPressable(Label lbl) {
        lbl.setStyle("-fx-border-color: black;-fx-border-width: 3; -fx-background-color: red;");
    }

    public ArrayList<Dice> getGearray() {
        return gearray;
    }
    
    public void newTurn(){
        
        rules.totalChecker();
        if(rules.isUpperbool() == true) {
            sclblup7.setText(Integer.toString(rules.getUppertotal()));
            sclblup8.setText(Integer.toString(rules.getBonusUppertotal()));
        }
        if(rules.isLowerbool() == true) {
            sclbllo8.setText(Integer.toString(rules.getLowertotal()));
        }
        if(rules.isUpperbool() == true && rules.isLowerbool() == true) {
            sclbllo9.setText(Integer.toString(rules.getTotal()));
            
            finish = true;      
        }
        
        if(finish == false) {
            btnthrowdices.setDisable(false);
            btnthrowdices.setText("Würfel werfen");
            locklabels = true;
            keep.clear();
            gearray.clear();
            bearray.clear();      
            firstthrow = true;
            secondthrow = true;
        }
        else {
            btnthrowdices.setDisable(false);
            newround = true;  
            
            btnthrowdices.setText("Neues Spiel");
        }
    }
            
    
}
