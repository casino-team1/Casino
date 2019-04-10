/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Roulette;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lukas Gilgen Schule
 */
public class RouletteWheel {    
    private int randomNumber;
    private boolean result;

    
    public void decideResult(int parameterBet){
        if (parameterBet == randomNumber){
            System.out.println("Good Job");
        }
        else{
            System.out.println("Better Luck next time");
        }
    }
    public void decideResult(ArrayList<Integer> parameterArrayBet, int ArrayIdentity){
        if (parameterArrayBet.contains(randomNumber)){
            System.out.println("Good Job");
            if (ArrayIdentity == 1) {
                
            }
            else if (ArrayIdentity == 2) {
                
            }
            else if (ArrayIdentity == 3) {
                
            }
            else if (ArrayIdentity == 4) {
                
            }
            else if (ArrayIdentity == 5) {
                
            }
            else{
                
            }
        }
        else{
            System.out.println("Better Luck next time");
        }
    }
    
    public boolean getResult() {
        return result;
    }
    
    public void generateRandom(){
        this.randomNumber = new Random().nextInt(38);
    }
}
