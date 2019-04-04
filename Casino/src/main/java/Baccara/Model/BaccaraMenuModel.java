/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package Baccara.Model;

import Baccara.BaccaraHandler;

/**
 *
 * @author Nick Flückiger
 */
public class BaccaraMenuModel extends BaccaraModel {

    public BaccaraMenuModel(BaccaraHandler baccaraGame) {
        super(baccaraGame);
    }

    public void backToMainMenu() throws Exception {
        super.getBaccaraGame().displayMainMenu();
    }

}
