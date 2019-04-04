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
    private int bet;
    private ArrayList<Integer> betArray = new ArrayList<>();
    
    public void setBet(int b){
        this.bet = b;
    }
    
    public int getBet(){
        return bet;
    }
    
    public ArrayList<Integer> getBetArray(){
        return betArray;
    }
    
}
