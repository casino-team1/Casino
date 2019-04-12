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

    public int decideResult(int parameterBet, int betAmount) {
        if (parameterBet == randomNumber) {
            System.out.println("Good Job");
            //Has to be 35, in order to account for bet placed
            betAmount = betAmount * 35;
            result = true;
        } else {
            System.out.println("Better Luck next time");
            betAmount = 0 - betAmount;
            result = false;
        }
        return betAmount;
    }

    public int decideResult(ArrayList<Integer> parameterArrayBet, int ArrayIdentity, int betAmount) {
        if (parameterArrayBet.contains(randomNumber)) {
            System.out.println("Good Job");
            result = true;
            if (ArrayIdentity == 1) {
                betAmount = betAmount;                
            } 
            else if (ArrayIdentity == 2) {
                betAmount = betAmount * 2;
            } 
            else if (ArrayIdentity == 3) {
                betAmount = betAmount * 5;
            } 
            else if (ArrayIdentity == 4) {
                betAmount = betAmount * 7;
            } 
            else if (ArrayIdentity == 5) {
                betAmount = betAmount * 10;
            } 
            else { //ArrayIdentity == 6
                betAmount = betAmount * 16;
            }
        } else {
            System.out.println("Better Luck next time");
            result = false;
            betAmount = 0 - betAmount;
        }
        return betAmount;
    }

    public boolean getResult() {
        return result;
    }

    public void generateRandom() {
        this.randomNumber = new Random().nextInt(38);
    }

    public int getRandomNumber() {
        return randomNumber;
    }
    
}
