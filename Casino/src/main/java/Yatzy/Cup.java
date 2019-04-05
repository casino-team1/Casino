/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.util.ArrayList;

/**
 *
 * @author Erik Hess
 */
public class Cup {

    ArrayList<Dice> dicearray = new ArrayList<>();

    public ArrayList<Dice> getDicearray() {
        return dicearray;
    }

    public void trowDices() {

        
        Dice dice = new Dice();
        
        dicearray.add(dice);

        
    }
}
