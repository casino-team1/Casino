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
        String gameRules = "Bei Baccara kann der Spieler drei Aktionen tätigen. Diese sind: \n*    Jetons auf den Spieler setzen\n*    Jetons auf den Dealer setzen\n*    Jetons auf ein Unentschieden setzen.\n\nBis auf das setzen der Jetons"
                + "hat der Spieler keinen Einfluss auf den Ausgang des Spieles.\nDie Karten für Spieler und Dealer werden nach den Regeln von Baccara gezogen.\nDas Ziel von Baccara ist es, mit den gegebenen Karten näher an den Kartenwert 9 heranzukommen.\n"
                + "Folgend sind die Regeln der Kartenziehung.\n";
        gameRules += "Spieler \n\n";
        gameRules += "* Hat der Spieler einen Kartenwert von 8 oder 9, so werden keine weiteren Karten gezogen.\n* Hat der Spieler einen Kartenwert von 6 oder 7, so zieht er keine Karten mehr, der Banker könnte allerdings\n"
                + "* Hat der einen Kartenwert von 0,1,2,3,4 oder 5, so muss er eine Karte ziehen.\n\n\n";
        gameRules += "Banker \n\n";

        gameRules += "Hat dem Spieler eine dritte Karte gegeben: \n\n";
        gameRules += "* Hat der Banker den Kartenwert 8 oder 9, so werden keine weiteren Karten gezogen\n"
                + "* Hat der Banker einen Kartenwert von 7, so zieht der Banker keine Karte mehr.\n"
                + "* Hat er einen Kartenwert von 0,1 oder 2, muss er eine Karte ziehen\n"
                + "* Hat er einen Kartenwert von ";

        gameRules += "Hat dem Spieler keine dritte Karte gegeben:\n\n";
        gameRules += "* Hat der Banker einen Kartenwert von 0,1,2,3,4 oder 5 so muss er eine Karte ziehen\n";
        gameRules += "* Hat der Banker einen Kartenwert von 6 oder 7, zieht er keine weiter Karte\n";
        gameRules += "* Hat der Banker einen Kartenwert von 8 oder 9, werden keine weiteren Karten gezogen";
        this.gameText.setText(gameRules);
    }
}
