/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roulette;

import java.util.ArrayList;

/**
 *
 * @author Lukas Gilgen Schule
 */
public class RouletteWheel {

    private int result;
    private ArrayList<Integer> resultArray = new ArrayList<>();

    RouletteFXMLController control = new RouletteFXMLController();
    private boolean isNumber = control.getIsNumber();

    public int getResult() {
        return result;
    }

    public ArrayList<Integer> getResultArray() {
        return resultArray;
    }

    public void setResult(int result) {
        if (isNumber == true) {
            this.result = result;
        }
    }
}
