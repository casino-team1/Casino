/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Yatzy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Nick Flückiger
 */
public class Yatzy {

    public Yatzy() throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/YatzyFXML.fxml"));
        
        
        PlayYatzy yatzy = new PlayYatzy();   
        
    }
}
