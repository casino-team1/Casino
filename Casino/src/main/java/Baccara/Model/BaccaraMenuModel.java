/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Model;

import Baccara.BaccaraHandler;
import com.team1.casino.Player.Player;
import com.team1.casino.Player.PlayerCentral;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraMenuModel extends BaccaraModel {

    private Player player = PlayerCentral.getInstance().getPlayer();

    public BaccaraMenuModel(BaccaraHandler baccaraGame) {
        super(baccaraGame);
    }

    public void backToMainMenu() throws Exception {
        super.getBaccaraGame().displayMainMenu();
    }

    public void startBaccara() {
        super.getBaccaraGame().displayGame();
    }

    public Player getPlayer() {
        return this.player;
    }
}
