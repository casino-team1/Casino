/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Controller;

import Baccara.Model.BaccaraMenuModel;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class BaccaraMenuViewController implements Initializable, Observer {

    @FXML
    private Button baccaraButton;
    @FXML
    private ImageView spadeIcon;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Text baccaraText;
    @FXML
    private ImageView cardView;
    @FXML
    private ImageView cardView1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private BaccaraMenuModel menuModel;

    public void setMenuModel(BaccaraMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void startBaccara(ActionEvent event) {
        this.menuModel.startBaccara();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        this.menuModel.backToMainMenu();
    }

}
