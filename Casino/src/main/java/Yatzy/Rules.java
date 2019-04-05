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
    
    private int result = 0;
    
    
    public int getResult() {
        return result;
    }
    
    public void calculateResult(ArrayList<Dice> dicearray) {
        HashMap<Integer, Integer> dicemap = new HashMap<>();
        dicemap.put(1, 0);
        dicemap.put(2, 0);
        dicemap.put(3, 0);
        dicemap.put(4, 0);
        dicemap.put(5, 0);
        dicemap.put(6, 0);
        for(Dice d : dicearray) {    
            if(dicemap.containsKey(d.getValue())) {
                dicemap.put(d.getValue(), dicemap.get(d.getValue()) + 1);
            }
            else {
                dicemap.put(d.getValue(), 1);
            }
        }
        
        for(int i = 1; i < dicemap.size() + 1; i++) {
            if(null != dicemap.get(i)) switch (dicemap.get(i)) {
                case 2:
                    result += i + i;
                    break;
                case 3:
                    result += i + i + i;
                    break;
                case 4:
                    result = i + i + i + i;
                    break;
                case 5: 
                    result = 50;
                    break;
                default:
                    break;
            }         
        }
        
        if(dicemap.get(1) == 1 && dicemap.get(2) == 1 && dicemap.get(3) == 1 && dicemap.get(4) == 1 && dicemap.get(5) == 1) {
            result = 15;
        }
        else if(dicemap.get(2) == 1 && dicemap.get(3) == 1 && dicemap.get(4) == 1 && dicemap.get(5) == 1 && dicemap.get(6) == 1) {
            result = 20;
        }
        
    }

    
}
