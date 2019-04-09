/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

/**
 *
 * @author Schule
 */
public class Images {
    
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
    
    
}
