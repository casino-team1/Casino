/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Nick Flückiger
 */
public class BaccaraRulesController implements Initializable {

    @FXML
    private Label gameText;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gameText.setAlignment(Pos.BASELINE_CENTER);
        this.gameText.setText(
                "Bei Baccara kann der Spieler drei Aktionen tätigen. Diese sind: \n*    Jetons auf den Spieler setzen\n*    Jetons auf den Dealer setzen\n*     Jetons auf ein Unentschieden setzen.\n\nBis auf das setzen der Jetons"
                + "hat der Spieler keinen Einfluss auf den Ausgang des Spieles.\nDie Karten für Spieler und Dealer werden nach den Regeln von Baccara gezogen.\nDas Ziel von Baccara ist es, mit den gegebenen Karten näher an den Kartenwert 9 heranzukommen.\n"
                + "Folgend sind die Regeln der Kartenziehung."
        );
    }

}
