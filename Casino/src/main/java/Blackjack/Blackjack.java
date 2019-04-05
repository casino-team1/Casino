/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Blackjack;

import com.team1.casino.MainApp;
import com.team1.casino.Spiel;

/**
 *
 * @author Nick Flückiger
 */
public class Blackjack extends Spiel {
    BlackJackSpielerModel spieler;
    BlackJackDealerModel dealer;
    
    public Blackjack(MainApp mainApplication) {
        super(mainApplication);
    }

    @Override
    public void startGame() {
        getDealer();
        getSpieler();
        System.out.println("BlackJack");
    }
    
    public BlackJackSpielerModel getSpieler(){
        return null;  
    }
    
    public BlackJackDealerModel getDealer(){
        return null;  
    }
}
