/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Erik Hess
 */
public class Cup {

    private final ArrayList<Dice> dicearray = new ArrayList<>();
    private ArrayList<Dice> keep = new ArrayList<>();

    public ArrayList<Dice> getDicearray() {
        return dicearray;
    }
  

    public void setKeep(ArrayList<Dice> keep) {
        this.keep = keep;
    }

    public ArrayList<Dice> getKeep() {
        return keep;
    }
    
    public void throwDices() {

        dicearray.clear();
        for(int i = 0; i < 5 - keep.size(); i++) {
            
        Dice dice = new Dice();
        Random rand = new Random(); 
        dice.setValue(rand.nextInt(5) + 1); 
        dicearray.add(dice);
        }
      
        dicearray.addAll(keep);
 
    }
}
