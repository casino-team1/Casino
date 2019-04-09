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
    private boolean secondthrow = true;
    private boolean lockdices = false;
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
    private Label sclblup9;
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
    private Label lblup9;
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
    @FXML
    private Label sclbllo9;

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

    public ArrayList<String> getkeeparray() {
        return keeparray;
    }

    @FXML
    private void pressthrowdices(ActionEvent event) {
        assignImages();
        if (firstthrow == true) {
            
            firstthrow = false;
            btnthrowdices.setText("Zweiter Wurf");
        } else if (secondthrow == true && firstthrow == false) {
                       
            secondthrow = false;
            btnthrowdices.setText("Dritter Wurf");
        } else {
            
            Rules rules = new Rules();
            rules.calculateResult(cup.getDicearray());
            lbltest.setText("" + rules.getResult());
            btnthrowdices.setText("Figur Wählen");
            btnthrowdices.setDisable(true);
            lockdices = true;
        }
        
        keep.clear();
        keep.addAll(bearray);
        bearray.clear();
        gearray.clear();
        cup.setKeep(keep);
        cup.throwDices();
        gearray.addAll(keep);
        gearray.addAll(cup.getDicearray());
        assignImages();
        
        
        
        String s = "";
        for (int i = 0; i < keep.size(); i++) {
            s += keep.get(i).getValue();
        }
        lbltest.setText(s);
        

    }

    @FXML
    private void pressGe1(MouseEvent event) {
        if(lockdices == false) {
            bearray.add(gearray.get(0));
            gearray.remove(0);
            assignImages();
        }
    }

    @FXML
    private void pressGe2(MouseEvent event) {
        bearray.add(gearray.get(1));
        gearray.remove(1);
        assignImages();
    }

    @FXML
    private void pressGe3(MouseEvent event) {
        if(lockdices == false) {
        bearray.add(gearray.get(2));
        gearray.remove(2);
        assignImages();
        }
    }

    @FXML
    private void pressGe4(MouseEvent event) {
        if(lockdices == false) {
        bearray.add(gearray.get(3));
        gearray.remove(3);
        assignImages();
        }
    }

    @FXML
    private void pressGe5(MouseEvent event) {
        if(lockdices == false) {
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

    public String getImage(int value) {
        String path = "";

        switch (value) {
            case 1:
                path = "images/Yatzy/D1.png";
                break;
            case 2:
                path = "images/Yatzy/D2.png";
                break;
            case 3:
                path = "images/Yatzy/D3.png";
                break;
            case 4:
                path = "images/Yatzy/D4.png";
                break;
            case 5:
                path = "images/Yatzy/D5.png";
                break;
            case 6:
                path = "images/Yatzy/D6.png";
                break;
            default:
                break;
        }
        return path;
    }

    public void assignImages() {

        if (gearray.size() < 1) {
            ge1.setVisible(false);
        } else {
            this.ge1.setImage(new Image(getImage(gearray.get(0).getValue())));
            ge1.setVisible(true);
        }

        if (gearray.size() < 2) {
            ge2.setVisible(false);
        } else {
            this.ge2.setImage(new Image(getImage(gearray.get(1).getValue())));
            ge2.setVisible(true);
        }

        if (gearray.size() < 3) {
            ge3.setVisible(false);
        } else {
            this.ge3.setImage(new Image(getImage(gearray.get(2).getValue())));
            ge3.setVisible(true);
        }

        if (gearray.size() < 4) {
            ge4.setVisible(false);
        } else {
            this.ge4.setImage(new Image(getImage(gearray.get(3).getValue())));
            ge4.setVisible(true);
        }

        if (gearray.size() < 5) {
            ge5.setVisible(false);
        } else {
            this.ge5.setImage(new Image(getImage(gearray.get(4).getValue())));
            ge5.setVisible(true);
        }

        if (bearray.size() < 1) {
            be1.setVisible(false);
        } else {
            this.be1.setImage(new Image(getImage(bearray.get(0).getValue())));
            be1.setVisible(true);
        }

        if (bearray.size() < 2) {
            be2.setVisible(false);
        } else {
            this.be2.setImage(new Image(getImage(bearray.get(1).getValue())));
            be2.setVisible(true);
        }

        if (bearray.size() < 3) {
            be3.setVisible(false);
        } else {
            this.be3.setImage(new Image(getImage(bearray.get(2).getValue())));
            be3.setVisible(true);
        }

        if (bearray.size() < 4) {
            be4.setVisible(false);
        } else {
            this.be4.setImage(new Image(getImage(bearray.get(3).getValue())));
            be4.setVisible(true);
        }

        if (bearray.size() < 5) {
            be5.setVisible(false);
        } else {
            this.be5.setImage(new Image(getImage(bearray.get(4).getValue())));
            be5.setVisible(true);
        }
    }

    @FXML
    private void presshelp(ActionEvent event) {
    }

    @FXML
    private void pressendgame(ActionEvent event) {
    }
    
    
    

    @FXML
    private void presslblup1(MouseEvent event) {
        lbltest.setText("hello world");
    }

    @FXML
    private void presslblup2(MouseEvent event) {
    }

    @FXML
    private void presslblup3(MouseEvent event) {
    }

    @FXML
    private void presslblup4(MouseEvent event) {
    }

    @FXML
    private void presslblup5(MouseEvent event) {
    }

    @FXML
    private void presslblup6(MouseEvent event) {
    }

    @FXML
    private void presslbllo1(MouseEvent event) {
    }

    @FXML
    private void presslbllo2(MouseEvent event) {
    }

    @FXML
    private void presslbllo3(MouseEvent event) {
    }

    @FXML
    private void presslbllo4(MouseEvent event) {
    }

    @FXML
    private void presslbllo5(MouseEvent event) {
    }

    @FXML
    private void presslbllo6(MouseEvent event) {
    }
}
