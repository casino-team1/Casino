/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Erik Hess
 */
public class Rules {
    
    private int result;
    
    
    public int getResult() {
        return result;
    }
    
    public void calculateResult(ArrayList<Dice> dicearray) {
        HashMap<Integer, Integer> dicemap = new HashMap<>();
        int i = 0;
        for(Dice d : dicearray) {
            
            dicemap.put(dicearray.get(i).getValue(), i);
            
            
            i++;  
        }
    }

    
}
