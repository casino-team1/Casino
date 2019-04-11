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
public class RouletteTable {

    public int getAmountOfBet(int parameterInt){
        int betAmount = 0;
        
        return betAmount;
    }

    public boolean checkForAvailableMoney(int playerBalance, int betAmount){
        if (playerBalance < betAmount){
            return false;
        }
        else {
            return true;
        }
    }
}
