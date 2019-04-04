/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

/**
 *
 * @author Erik Hess
 */
public class Dice {

    private int value;
    private boolean keep;

    public int getValue() {
        return value;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }
}
