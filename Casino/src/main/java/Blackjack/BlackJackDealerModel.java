/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

import java.util.ArrayList;

/**
 *
 * @author albio
 */
public class BlackJackDealerModel {
    private ArrayList<String> kartenDealer = new ArrayList<>();
    boolean gewonnen = false;
    
    public void stand(){
        
    }
    
    public void hit(){
        
    }
    
    public void austeilen(){
        
    }

    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }

    public boolean isGewonnen() {
        return gewonnen;
    }
    
    public ArrayList<String> getKarten(){
        return kartenDealer;
    }
}
